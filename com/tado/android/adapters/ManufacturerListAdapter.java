package com.tado.android.adapters;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.rest.model.installation.Manufacturer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class ManufacturerListAdapter extends ArrayAdapter<Manufacturer> {
    private ArrayList<Manufacturer> manufacturerArrayList = new ArrayList();
    private List<Manufacturer> manufacturerArrayListFiltered;

    public ManufacturerListAdapter(Context context, int resource, int textViewResourceId, List<Manufacturer> manufacturerList) {
        super(context, resource, textViewResourceId, manufacturerList);
        this.manufacturerArrayListFiltered = manufacturerList;
        this.manufacturerArrayList.addAll(this.manufacturerArrayListFiltered);
    }

    public int getCount() {
        return this.manufacturerArrayListFiltered.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Manufacturer manufacturer = (Manufacturer) this.manufacturerArrayListFiltered.get(position);
        View v = convertView;
        if (v == null) {
            v = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(C0676R.layout.wifi_list_item, null);
        }
        TextView lbl = (TextView) v.findViewById(C0676R.id.wifi_list_item_text);
        lbl.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        lbl.setText(manufacturer.getName());
        return v;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        this.manufacturerArrayListFiltered.clear();
        if (charText.length() == 0) {
            this.manufacturerArrayListFiltered.addAll(this.manufacturerArrayList);
        } else {
            Iterator it = this.manufacturerArrayList.iterator();
            while (it.hasNext()) {
                Manufacturer m = (Manufacturer) it.next();
                if (m.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    this.manufacturerArrayListFiltered.add(m);
                }
            }
        }
        notifyDataSetChanged();
    }
}
