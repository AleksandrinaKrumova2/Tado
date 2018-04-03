package com.tado.android.settings.users;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MobileDevicesViewHolder extends ViewHolder {
    @BindView(2131296770)
    TextView mobileDeviceName;

    public MobileDevicesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind((Object) this, itemView);
    }
}
