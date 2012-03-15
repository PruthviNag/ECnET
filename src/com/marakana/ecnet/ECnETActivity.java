package com.marakana.ecnet;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.ChannelListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ECnETActivity extends Activity implements OnClickListener, ChannelListener {
	
	public static final String TAG = "ECnETActivity";
	private WifiP2pManager manager;
	private Channel channel;
	private final IntentFilter intentFilter = new IntentFilter();
	private BroadcastReceiver receiver = null;
	private boolean isWifiP2pEnabled = false;
	EditText editText;
	Button sendButton;
	
	/* this is used to set iswifiP2pEnabled if WifiDirect is enabled */
	public void setIsWifiP2pEnabled(boolean isWifiP2pEnabled) {
		this.isWifiP2pEnabled = isWifiP2pEnabled;
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        wifiP2p();
        
        //Find views
        editText = (EditText) findViewById(R.id.commandMessage);
        sendButton = (Button) findViewById(R.id.buttonSend);
        
        sendButton.setOnClickListener(this);
    }
    
    /* Add necessary intent values and initialize mangaer and channel */
    public void wifiP2p() {
    	intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        manager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        channel = manager.initialize(this, getMainLooper(), null);

    }
    
    public void onResume() {
    	super.onResume();
    	receiver = new EWifiBroadcastReceiver(manager, channel, this);
    	registerReceiver(receiver, intentFilter);
    }
    
    public void onPause() {
    	super.onPause();
    	unregisterReceiver(receiver);
    }
    
    //called when send button is clicked
	public void onClick(View v) {
		if(isWifiP2pEnabled) {
			manager.discoverPeers(channel, new WifiP2pManager.ActionListener() {
		
				public void onFailure(int reason) {
					Log.d(ECnETActivity.TAG, "Peers discover failed: " + reason);
					Toast.makeText(ECnETActivity.this, "Discovery Failed : " + reason, Toast.LENGTH_SHORT).show();
					
				}
		
				public void onSuccess() {
					Log.d(ECnETActivity.TAG, "Disovering peers initiated...");
					
				}
				
			});
			
			
			Bundle mbund = new Bundle();
			Intent sentresIntent = new Intent(this, PeersViewActivity.class);
			mbund.putString("command", editText.getText().toString());
			Log.d(TAG, "binding bundle data to the setresIntent intent");
			sentresIntent.putExtras(mbund);
			Log.d(TAG, "Starting the PeersViewActivity");
			startActivity(sentresIntent);
		}
		else {
			Toast.makeText(ECnETActivity.this, "Please Enable wifi P2p", Toast.LENGTH_SHORT).show();
		}
		
	}

	public void onChannelDisconnected() {
		// TODO Auto-generated method stub
		
	}
}