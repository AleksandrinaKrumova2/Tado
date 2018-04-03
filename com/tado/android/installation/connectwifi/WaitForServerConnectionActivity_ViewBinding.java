package com.tado.android.installation.connectwifi;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity_ViewBinding;

public class WaitForServerConnectionActivity_ViewBinding<T extends WaitForServerConnectionActivity> extends ACInstallationBaseActivity_ViewBinding<T> {
    @UiThread
    public WaitForServerConnectionActivity_ViewBinding(T target, View source) {
        super(target, source);
        target.errorTextview = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.textview_error, "field 'errorTextview'", TextView.class);
    }

    public void unbind() {
        WaitForServerConnectionActivity target = (WaitForServerConnectionActivity) this.target;
        super.unbind();
        target.errorTextview = null;
    }
}
