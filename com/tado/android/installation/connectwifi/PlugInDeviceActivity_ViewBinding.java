package com.tado.android.installation.connectwifi;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity_ViewBinding;

public class PlugInDeviceActivity_ViewBinding<T extends PlugInDeviceActivity> extends ACInstallationBaseActivity_ViewBinding<T> {
    @UiThread
    public PlugInDeviceActivity_ViewBinding(T target, View source) {
        super(target, source);
        target.pluginDeviceTextview = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.plugin_device_textview, "field 'pluginDeviceTextview'", TextView.class);
    }

    public void unbind() {
        PlugInDeviceActivity target = (PlugInDeviceActivity) this.target;
        super.unbind();
        target.pluginDeviceTextview = null;
    }
}
