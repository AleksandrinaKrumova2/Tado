package com.tado.android.adapters;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.tado.C0676R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class StringListAdapter extends ArrayAdapter<String> {
    private ArrayList<String> arrayList = new ArrayList();
    private List<String> arrayListFiltered;

    public StringListAdapter(Context context, int resource, int textViewResourceId, List<String> stringList) {
        super(context, resource, textViewResourceId, stringList);
        this.arrayListFiltered = stringList;
        this.arrayList.addAll(this.arrayListFiltered);
    }

    public int getCount() {
        return this.arrayListFiltered.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        String str = (String) this.arrayListFiltered.get(position);
        View v = convertView;
        if (v == null) {
            v = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(C0676R.layout.wifi_list_item, null);
        }
        TextView lbl = (TextView) v.findViewById(C0676R.id.wifi_list_item_text);
        lbl.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        lbl.setText(str);
        return v;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        this.arrayListFiltered.clear();
        if (charText.length() == 0) {
            this.arrayListFiltered.addAll(this.arrayList);
        } else {
            Iterator it = this.arrayList.iterator();
            while (it.hasNext()) {
                String s = (String) it.next();
                if (s.toLowerCase(Locale.getDefault()).contains(charText)) {
                    this.arrayListFiltered.add(s);
                }
            }
        }
        notifyDataSetChanged();
    }
}
