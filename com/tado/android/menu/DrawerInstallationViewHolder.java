package com.tado.android.menu;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.utils.ResourceFactory;

public class DrawerInstallationViewHolder extends ViewHolder {
    @BindView(2131296589)
    ImageView icon;
    @BindView(2131296590)
    View layout;
    @BindView(2131296591)
    TextView title;

    public DrawerInstallationViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind((Object) this, itemView);
    }

    public void bind(InstallationDrawerItem item) {
        this.title.setText(ResourceFactory.getInstallationDrawerTitle(item.isStartNewInstallation()));
        this.icon.setImageDrawable(ResourceFactory.getInstallationIcon(TadoApplication.getTadoAppContext(), item.isStartNewInstallation()));
        this.layout.setBackgroundColor(ContextCompat.getColor(TadoApplication.getTadoAppContext(), C0676R.color.manual));
    }
}
