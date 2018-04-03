package com.tado.android.installation.connectwifi;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity_ViewBinding;

public class TroubleshootDeviceConnectionActivity_ViewBinding<T extends TroubleshootDeviceConnectionActivity> extends ACInstallationBaseActivity_ViewBinding<T> {
    @UiThread
    public TroubleshootDeviceConnectionActivity_ViewBinding(T target, View source) {
        super(target, source);
        target.additionalTextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.additional_text, "field 'additionalTextView'", TextView.class);
    }

    public void unbind() {
        TroubleshootDeviceConnectionActivity target = (TroubleshootDeviceConnectionActivity) this.target;
        super.unbind();
        target.additionalTextView = null;
    }
}
