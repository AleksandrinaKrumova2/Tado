package com.tado.android.menu;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class DrawerInstallationViewHolder_ViewBinding<T extends DrawerInstallationViewHolder> implements Unbinder {
    protected T target;

    @UiThread
    public DrawerInstallationViewHolder_ViewBinding(T target, View source) {
        this.target = target;
        target.layout = Utils.findRequiredView(source, C0676R.id.drawer_installation_item_layout, "field 'layout'");
        target.title = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.drawer_installation_item_name, "field 'title'", TextView.class);
        target.icon = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.drawer_installation_item_icon, "field 'icon'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.layout = null;
        target.title = null;
        target.icon = null;
        this.target = null;
    }
}
