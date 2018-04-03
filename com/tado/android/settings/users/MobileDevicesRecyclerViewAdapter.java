package com.tado.android.settings.users;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.tado.C0676R;
import com.tado.android.rest.model.MobileDevice;
import com.tado.android.utils.ResourceFactory;
import fi.iki.elonen.NanoHTTPD;
import java.util.Iterator;
import java.util.List;

public class MobileDevicesRecyclerViewAdapter extends Adapter<ViewHolder> {
    List<MobileDevice> items;
    OnClickListener listener;

    public MobileDevicesRecyclerViewAdapter(List<MobileDevice> items, OnClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(C0676R.layout.mobile_device_layout, parent, false);
        itemView.setOnClickListener(this.listener);
        return new MobileDevicesViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        MobileDevice mobileDevice = (MobileDevice) this.items.get(position);
        MobileDevicesViewHolder viewHolder = (MobileDevicesViewHolder) holder;
        Context context = viewHolder.mobileDeviceName.getContext();
        viewHolder.mobileDeviceName.setText(mobileDevice.getName());
        viewHolder.mobileDeviceName.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, C0676R.drawable.settings_device), null, getRightDrawable(context, mobileDevice), null);
    }

    public int getItemCount() {
        return this.items.size();
    }

    @Nullable
    private Drawable getRightDrawable(Context context, MobileDevice mobileDevice) {
        if (!mobileDevice.getSettings().isGeoTrackingEnabled()) {
            return ResourceFactory.getTintedDrawable(context, C0676R.drawable.location_based_controll_off, C0676R.color.app_bar_settings_background);
        }
        if ((mobileDevice.getLocation() == null || !mobileDevice.getLocation().isStale()) && (mobileDevice.getLocation() != null || !mobileDevice.getSettings().isGeoTrackingEnabled())) {
            return null;
        }
        Drawable scaled;
        if (VERSION.SDK_INT >= 21) {
            scaled = new ScaleDrawable(ResourceFactory.getVectorDrawable(context, C0676R.drawable.ic_question_mark), 5, 1.0f, 1.0f);
        } else {
            scaled = new ScaleDrawable(ResourceFactory.getTintedDrawable(context, C0676R.drawable.ic_question_mark, C0676R.color.ac_red), 5, 1.0f, 1.0f);
        }
        scaled.setLevel(NanoHTTPD.SOCKET_READ_TIMEOUT);
        return scaled;
    }

    public void removeMobileDevice(int deviceId) {
        Iterator<MobileDevice> iterator = this.items.iterator();
        while (iterator.hasNext()) {
            if (((MobileDevice) iterator.next()).getId() == deviceId) {
                iterator.remove();
                notifyDataSetChanged();
                return;
            }
        }
    }

    public void updateMobileDeviceGeotracking(int id, boolean geoTrackingEnabled) {
        for (MobileDevice device : this.items) {
            if (device.getId() == id) {
                device.getSettings().setGeoTrackingEnabled(geoTrackingEnabled);
            }
        }
        notifyDataSetChanged();
    }
}
