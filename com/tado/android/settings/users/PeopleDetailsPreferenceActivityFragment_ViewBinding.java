package com.tado.android.settings.users;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class PeopleDetailsPreferenceActivityFragment_ViewBinding<T extends PeopleDetailsPreferenceActivityFragment> implements Unbinder {
    protected T target;

    @UiThread
    public PeopleDetailsPreferenceActivityFragment_ViewBinding(T target, View source) {
        this.target = target;
        target.name = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.user_details_name, "field 'name'", TextView.class);
        target.email = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.user_details_email, "field 'email'", TextView.class);
        target.description = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.user_details_description, "field 'description'", TextView.class);
        target.revokeButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.user_details_revoke_button, "field 'revokeButton'", Button.class);
        target.resendButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.user_details_resend_button, "field 'resendButton'", Button.class);
        target.mobileDevicesSeparatorLine = Utils.findRequiredView(source, C0676R.id.user_details_mobile_devices_separator_line, "field 'mobileDevicesSeparatorLine'");
        target.mobileDevicesTitle = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.user_details_mobile_devices_title, "field 'mobileDevicesTitle'", TextView.class);
        target.mobileDevicesRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0676R.id.user_details_mobile_device_list, "field 'mobileDevicesRecyclerView'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.name = null;
        target.email = null;
        target.description = null;
        target.revokeButton = null;
        target.resendButton = null;
        target.mobileDevicesSeparatorLine = null;
        target.mobileDevicesTitle = null;
        target.mobileDevicesRecyclerView = null;
        this.target = null;
    }
}
