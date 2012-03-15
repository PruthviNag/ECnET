package com.marakana.ecnet;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.p2p.WifiP2pDevice;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

public class PeersViewActivity extends Activity {
	private static final String TAG = "PeersViewActivity";
	ArrayList<WifiP2pDevice> peers;
	ListView deviceList;
	DeviceListAdapter adapter;
	Context context;
	TextView textView;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peersview);
        deviceList = (ListView) findViewById(R.id.deviceList);
        Log.d(TAG, "Getting the bundle data from the intent");
        Bundle mbund = getIntent().getExtras();
        Log.d(TAG, "Adding content to the textView");
        String cMessage = mbund.getString("command");
        textView = (TextView) findViewById(R.id.peersView);
        textView.setText(cMessage);
    }
    
    public void onPause() {
    	
    }
    
    public void onResume() {
    	super.onResume();
    	//creating and setting adapter to the list view
    	adapter = new DeviceListAdapter(peers);
    	deviceList.setAdapter(adapter);
    }
    
    public void onDestroy() {
    	super.onDestroy();
    }
}