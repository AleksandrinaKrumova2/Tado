package com.tado.android.settings;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class BetaAccessActivity_ViewBinding<T extends BetaAccessActivity> implements Unbinder {
    protected T target;

    @UiThread
    public BetaAccessActivity_ViewBinding(T target, View source) {
        this.target = target;
        target.mFirstName = (EditText) Utils.findRequiredViewAsType(source, C0676R.id.beta_first_name, "field 'mFirstName'", EditText.class);
        target.mLastName = (EditText) Utils.findRequiredViewAsType(source, C0676R.id.beta_last_name, "field 'mLastName'", EditText.class);
        target.mEmail = (EditText) Utils.findRequiredViewAsType(source, C0676R.id.beta_email, "field 'mEmail'", EditText.class);
        target.mSendRequestButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.beta_send_request_button, "field 'mSendRequestButton'", Button.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mFirstName = null;
        target.mLastName = null;
        target.mEmail = null;
        target.mSendRequestButton = null;
        this.target = null;
    }
}
