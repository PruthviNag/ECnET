package com.marakana.ecnet;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.p2p.WifiP2pDevice;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DeviceListAdapter extends BaseAdapter {

    private ArrayList<WifiP2pDevice> peers;
    Activity activity;
    /**
     * @param context
     * @param textViewResourceId
     * @param objects
     */
    public DeviceListAdapter(ArrayList<WifiP2pDevice> peers) {
        //super(context, textViewResourceId, objects);
        this.peers = peers;

    }
    
    private static String getDeviceStatus(int deviceStatus) {
        Log.d(ECnETActivity.TAG, "Peer status :" + deviceStatus);
        switch (deviceStatus) {
            case WifiP2pDevice.AVAILABLE:
                return "Available";
            case WifiP2pDevice.INVITED:
                return "Invited";
            case WifiP2pDevice.CONNECTED:
                return "Connected";
            case WifiP2pDevice.FAILED:
                return "Failed";
            case WifiP2pDevice.UNAVAILABLE:
                return "Unavailable";
            default:
                return "Unknown";

        }
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) activity.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.devicelistrow, null);
        }
        WifiP2pDevice device = peers.get(position);
        if (device != null) {
            TextView deviceName = (TextView) v.findViewById(R.id.deviceName);
            TextView deviceAddress = (TextView) v.findViewById(R.id.deviceAddress);
            TextView deviceStatus = (TextView) v.findViewById(R.id.deviceStatus);
            if (deviceName != null) {
                deviceName.setText(device.deviceName);
            }
            if (deviceAddress != null) {
            	deviceAddress.setText(device.deviceAddress);
            }
            if (deviceStatus != null) {
                deviceStatus.setText(getDeviceStatus(device.status));
            }
        }

        return v;

    }

	public int getCount() {
		return peers.size();
	}

	public Object getItem(int position) {
		return peers.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

}
