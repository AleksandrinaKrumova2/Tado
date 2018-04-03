package com.tado.android.installation.positioning;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity_ViewBinding;

public class DevicePositionConnectionActivity_ViewBinding<T extends DevicePositionConnectionActivity> extends ACInstallationBaseActivity_ViewBinding<T> {
    @UiThread
    public DevicePositionConnectionActivity_ViewBinding(T target, View source) {
        super(target, source);
        target.mStartSending = (Button) Utils.findRequiredViewAsType(source, C0676R.id.start_sending, "field 'mStartSending'", Button.class);
    }

    public void unbind() {
        DevicePositionConnectionActivity target = (DevicePositionConnectionActivity) this.target;
        super.unbind();
        target.mStartSending = null;
    }
}
