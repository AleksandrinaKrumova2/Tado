package com.tado.android.menu;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.utils.UserConfig;

public class DrawerLogoViewHolder extends ViewHolder {
    ImageView mIconView;
    TextView mVersionView;

    public DrawerLogoViewHolder(View itemView) {
        super(itemView);
        this.mIconView = (ImageView) itemView.findViewById(C0676R.id.drawer_logo_item_image_view);
        this.mVersionView = (TextView) itemView.findViewById(C0676R.id.drawer_logo_item_version_view);
    }

    public void bind(DrawerLogoItem item) {
        this.mIconView.setImageLevel(UserConfig.getPartner().getCode());
        this.mVersionView.setText(item.getVersion());
    }
}
