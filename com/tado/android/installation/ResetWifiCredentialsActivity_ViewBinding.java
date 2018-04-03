package com.tado.android.installation;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class ResetWifiCredentialsActivity_ViewBinding<T extends ResetWifiCredentialsActivity> extends ACInstallationBaseActivity_ViewBinding<T> {
    @UiThread
    public ResetWifiCredentialsActivity_ViewBinding(T target, View source) {
        super(target, source);
        target.resetWifiCredentialsText = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.reset_wifi_credentials_textview, "field 'resetWifiCredentialsText'", TextView.class);
    }

    public void unbind() {
        ResetWifiCredentialsActivity target = (ResetWifiCredentialsActivity) this.target;
        super.unbind();
        target.resetWifiCredentialsText = null;
    }
}
