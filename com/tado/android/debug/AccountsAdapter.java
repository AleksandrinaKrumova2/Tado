package com.tado.android.debug;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class AccountsAdapter extends BaseAdapter {
    private List<DebugAccount> accounts;

    public AccountsAdapter(List<DebugAccount> accounts) {
        this.accounts = accounts;
    }

    public int getCount() {
        return this.accounts.size();
    }

    public Object getItem(int position) {
        return this.accounts.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        TextView layout = (TextView) LayoutInflater.from(parent.getContext()).inflate(17367050, parent, false);
        layout.setText(((DebugAccount) this.accounts.get(position)).name);
        return layout;
    }
}
