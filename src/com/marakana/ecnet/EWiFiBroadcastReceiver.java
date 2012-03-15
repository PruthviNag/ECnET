package com.marakana.ecnet;

import java.util.ArrayList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
import android.util.Log;

public class EWifiBroadcastReceiver extends BroadcastReceiver {
	private WifiP2pManager manager;
	private Channel channel;
	private ECnETActivity activity;
	ArrayList<WifiP2pDevice> peerslist;
	public EWifiBroadcastReceiver(WifiP2pManager manager, Channel channel, ECnETActivity activity) {
		super();
		this.manager = manager;
		this.channel = channel;
		this.activity = activity;
	}
	
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		PeerListListener peerListListener;
		peerListListener = new PeerListListener() {
			public void onPeersAvailable(WifiP2pDeviceList peers) {
				//write and call a class to iterate over the peers and connect to them
				peerslist = (ArrayList<WifiP2pDevice>) peers.getDeviceList();
				return;
			}
		};
		int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
		if(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
			if(state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
				activity.setIsWifiP2pEnabled(true);
			}
			else {
				activity.setIsWifiP2pEnabled(false);
				//and reset the data later write code here
			}
			Log.d(ECnETActivity.TAG, "P2p state changed" + state);
		}
		else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
			//Peers are changed so request new peers from wifi p2p manager
			if(manager != null) {
				manager.requestPeers(channel, peerListListener);
				WifiP2pConfig config = new WifiP2pConfig();
				for(final WifiP2pDevice peer : peerslist) {
					config.deviceAddress = peer.deviceAddress;
					manager.connect(channel, config, new ActionListener() {

					    public void onSuccess() {
					        //success logic
					    	Log.d(ECnETActivity.TAG, "connecting to device " + peer.deviceAddress);
					    	
					    }

					    public void onFailure(int reason) {
					        //failure logic
					    	Log.d(ECnETActivity.TAG, "Failed to connect to the device: " + peer.deviceAddress);
					    }
					});
				}
			}
		}
	}
	
}
