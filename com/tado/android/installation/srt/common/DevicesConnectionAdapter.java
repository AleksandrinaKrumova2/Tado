package com.tado.android.installation.srt.common;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.utils.ResourceFactory;
import java.util.List;

class DevicesConnectionAdapter extends Adapter<DeviceViewHolder> {
    private List<GenericHardwareDevice> devices;
    private boolean failedState = false;

    static class DeviceViewHolder extends ViewHolder {
        ImageView deviceConnectionStateImageView;
        ProgressBar deviceConnectionStateProgress;
        TextView deviceNameTextView;
        ImageView deviceProductImageView;
        TextView serialTextView;

        DeviceViewHolder(View itemView) {
            super(itemView);
            this.deviceNameTextView = (TextView) itemView.findViewById(C0676R.id.unfinished_installation_item_name);
            this.serialTextView = (TextView) itemView.findViewById(C0676R.id.unfinished_installation_item_serial_number);
            this.deviceConnectionStateProgress = (ProgressBar) itemView.findViewById(C0676R.id.device_reconnection_progress);
            this.deviceConnectionStateImageView = (ImageView) itemView.findViewById(C0676R.id.device_reconnection_result);
            this.deviceProductImageView = (ImageView) itemView.findViewById(C0676R.id.unfinished_installation_item_icon);
        }
    }

    DevicesConnectionAdapter(List<GenericHardwareDevice> devices) {
        this.devices = devices;
    }

    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DeviceViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0676R.layout.item_reconnect_device, parent, false));
    }

    public void onBindViewHolder(DeviceViewHolder holder, int position) {
        boolean showProgressIndicator;
        int i;
        int i2 = 4;
        GenericHardwareDevice device = (GenericHardwareDevice) this.devices.get(position);
        holder.deviceNameTextView.setText(ResourceFactory.getDeviceName(device.getDeviceType()));
        holder.serialTextView.setText(device.getShortSerialNo());
        holder.deviceProductImageView.setImageResource(ResourceFactory.getImageResourceForDeviceType(device.getDeviceType()));
        if (device.getConnectionState().isConnected().booleanValue() || this.failedState) {
            showProgressIndicator = false;
        } else {
            showProgressIndicator = true;
        }
        ProgressBar progressBar = holder.deviceConnectionStateProgress;
        if (showProgressIndicator) {
            i = 0;
        } else {
            i = 4;
        }
        progressBar.setVisibility(i);
        ImageView imageView = holder.deviceConnectionStateImageView;
        if (!showProgressIndicator) {
            i2 = 0;
        }
        imageView.setVisibility(i2);
        if (device.getConnectionState().isConnected().booleanValue()) {
            holder.deviceConnectionStateImageView.setImageResource(C0676R.drawable.ic_check_mark_green);
        } else if (this.failedState) {
            holder.deviceConnectionStateImageView.setImageResource(C0676R.drawable.ic_exclamation_mark);
        }
    }

    public int getItemCount() {
        return this.devices.size();
    }

    void updateDevicesState(List<GenericHardwareDevice> newDevicesList, boolean failedState) {
        this.failedState = failedState;
        this.devices = newDevicesList;
        notifyDataSetChanged();
    }
}
