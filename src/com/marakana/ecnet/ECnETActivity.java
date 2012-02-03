package com.marakana.ecnet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ECnETActivity extends Activity implements OnClickListener {
	private static final String TAG = "ECnETActivity";
	EditText editText;
	Button sendButton;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Find views
        editText = (EditText) findViewById(R.id.commandMessage);
        sendButton = (Button) findViewById(R.id.buttonSend);
        
        sendButton.setOnClickListener(this);
    }
    
    //called when send button is clicked
	public void onClick(View v) {
		Bundle mbund = new Bundle();
		Intent sentresIntent = new Intent(this, PeersViewActivity.class);
		mbund.putString("command", editText.getText().toString());
		Log.d(TAG, "binding bundle data to the setresIntent intent");
		sentresIntent.putExtras(mbund);
		Log.d(TAG, "Starting the PeersViewActivity");
		startActivity(sentresIntent);
		
	}
}