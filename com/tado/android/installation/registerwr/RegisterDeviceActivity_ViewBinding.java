package com.tado.android.installation.registerwr;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity_ViewBinding;

public class RegisterDeviceActivity_ViewBinding<T extends RegisterDeviceActivity> extends ACInstallationBaseActivity_ViewBinding<T> {
    @UiThread
    public RegisterDeviceActivity_ViewBinding(T target, View source) {
        super(target, source);
        target.imageView = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.center_image, "field 'imageView'", ImageView.class);
        target.editSerialNo = (EditText) Utils.findRequiredViewAsType(source, C0676R.id.register_device_input_serial_no, "field 'editSerialNo'", EditText.class);
        target.editAuthCode = (EditText) Utils.findRequiredViewAsType(source, C0676R.id.register_device_input_auth_code, "field 'editAuthCode'", EditText.class);
    }

    public void unbind() {
        RegisterDeviceActivity target = (RegisterDeviceActivity) this.target;
        super.unbind();
        target.imageView = null;
        target.editSerialNo = null;
        target.editAuthCode = null;
    }
}
