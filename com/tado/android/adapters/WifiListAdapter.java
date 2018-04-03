package com.tado.android.adapters;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.entities.Wifi;
import com.tado.android.utils.ResourceFactory;
import java.util.ArrayList;

public class WifiListAdapter extends ArrayAdapter<Wifi> {
    private ArrayList<Wifi> mWifiList;

    public WifiListAdapter(Context context, int resource, int textViewResourceId, ArrayList<Wifi> wifiList) {
        super(context, resource, textViewResourceId, wifiList);
        this.mWifiList = wifiList;
    }

    public int getCount() {
        return this.mWifiList.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Wifi wifi = (Wifi) this.mWifiList.get(position);
        View v = convertView;
        if (v == null) {
            v = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(C0676R.layout.wifi_list_item, null);
        }
        TextView lbl = (TextView) v.findViewById(C0676R.id.wifi_list_item_text);
        lbl.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        lbl.setText(wifi.getSsid());
        ImageView imagePadlock = (ImageView) v.findViewById(C0676R.id.padlock);
        if (wifi.getType().compareTo(Wifi.WIFI_SECURITY_OPEN) == 0) {
            imagePadlock.setVisibility(8);
        } else {
            imagePadlock.setVisibility(0);
        }
        ((ImageView) v.findViewById(C0676R.id.wifi_symbol)).setImageResource(ResourceFactory.getImageResourceForRssi(wifi.getRssi()));
        return v;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        Wifi wifi = (Wifi) this.mWifiList.get(position);
        View v = convertView;
        if (v == null) {
            v = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(C0676R.layout.wifi_list_item, null);
        }
        TextView lbl = (TextView) v.findViewById(C0676R.id.wifi_list_item_text);
        lbl.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        lbl.setText(wifi.getSsid());
        ImageView imagePadlock = (ImageView) v.findViewById(C0676R.id.padlock);
        if (wifi.getType().compareTo(Wifi.WIFI_SECURITY_OPEN) == 0) {
            imagePadlock.setVisibility(8);
        } else {
            imagePadlock.setVisibility(0);
        }
        ImageView imageView = (ImageView) v.findViewById(C0676R.id.wifi_symbol);
        imageView.setImageResource(ResourceFactory.getImageResourceForRssi(wifi.getRssi()));
        imageView.setVisibility(0);
        return v;
    }
}
