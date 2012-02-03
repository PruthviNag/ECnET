package com.marakana.ecnet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PeersViewActivity extends Activity {
	private static final String TAG = "PeersViewActivity";
	TextView textView;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peersview);
        
        Log.d(TAG, "Getting the bundle data from the intent");
        Bundle mbund = getIntent().getExtras();
        Log.d(TAG, "Adding content to the textView");
        String cMessage = mbund.getString("command");
        textView = (TextView) findViewById(R.id.peersView);
        textView.setText(cMessage);
    }
}