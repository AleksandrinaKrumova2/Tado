package com.tado.android.installation.connectwifi;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity_ViewBinding;

public class UpdateFirmwareActivity_ViewBinding<T extends UpdateFirmwareActivity> extends ACInstallationBaseActivity_ViewBinding<T> {
    @UiThread
    public UpdateFirmwareActivity_ViewBinding(T target, View source) {
        super(target, source);
        target.troubleshooting = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.troubleshooting, "field 'troubleshooting'", TextView.class);
    }

    public void unbind() {
        UpdateFirmwareActivity target = (UpdateFirmwareActivity) this.target;
        super.unbind();
        target.troubleshooting = null;
    }
}
