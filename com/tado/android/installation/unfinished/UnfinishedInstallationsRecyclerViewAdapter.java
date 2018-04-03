package com.tado.android.installation.unfinished;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.tado.C0676R;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.utils.ResourceFactory;
import java.util.List;

public class UnfinishedInstallationsRecyclerViewAdapter extends Adapter<ViewHolder> {
    List<GenericHardwareDevice> items;
    OnClickListener listener;

    public UnfinishedInstallationsRecyclerViewAdapter(List<GenericHardwareDevice> items, OnClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(C0676R.layout.unfinished_installation_item_layout, parent, false);
        itemView.setOnClickListener(this.listener);
        return new UnfinishedInstallationItemViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        GenericHardwareDevice item = (GenericHardwareDevice) this.items.get(position);
        UnfinishedInstallationItemViewHolder viewHolder = (UnfinishedInstallationItemViewHolder) holder;
        viewHolder.serialNumber.setText(item.getShortSerialNo());
        viewHolder.name.setText(ResourceFactory.getDeviceName(item.getDeviceType()));
        viewHolder.icon.setImageResource(ResourceFactory.getImageResourceForDeviceType(item.getDeviceType()));
        viewHolder.serialNumber.setVisibility(item.getShortSerialNo().isEmpty() ? 8 : 0);
    }

    public int getItemCount() {
        return this.items.size();
    }
}
