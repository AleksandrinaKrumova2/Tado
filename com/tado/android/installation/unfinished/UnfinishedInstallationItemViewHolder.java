package com.tado.android.installation.unfinished;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.tado.C0676R;

public class UnfinishedInstallationItemViewHolder extends ViewHolder {
    ImageView icon;
    TextView name;
    TextView serialNumber;

    public UnfinishedInstallationItemViewHolder(View itemView) {
        super(itemView);
        this.icon = (ImageView) itemView.findViewById(C0676R.id.unfinished_installation_item_icon);
        this.name = (TextView) itemView.findViewById(C0676R.id.unfinished_installation_item_name);
        this.serialNumber = (TextView) itemView.findViewById(C0676R.id.unfinished_installation_item_serial_number);
    }
}
