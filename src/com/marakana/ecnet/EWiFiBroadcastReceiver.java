package com.marakana.ecnet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;

public class EWiFiBroadcastReceiver extends BroadcastReceiver {
	private WifiP2pManager manager;
	private Channel channel;
	private ECnETActivity activity;
	
	public EWiFiBroadcastReceiver(WifiP2pManager manager, Channel channel, ECnETActivity activity) {
		super();
		this.manager = manager;
		this.channel = channel;
		this.activity = activity;
	}
	
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		
		if(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
			
		}
	}
	
}
