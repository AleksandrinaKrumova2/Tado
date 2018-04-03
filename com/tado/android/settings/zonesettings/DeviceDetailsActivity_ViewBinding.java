package com.tado.android.settings.zonesettings;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class DeviceDetailsActivity_ViewBinding<T extends DeviceDetailsActivity> implements Unbinder {
    protected T target;

    @UiThread
    public DeviceDetailsActivity_ViewBinding(T target, View source) {
        this.target = target;
        target.productImage = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.image_device, "field 'productImage'", ImageView.class);
        target.productName = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.text_product_name, "field 'productName'", TextView.class);
        target.description = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.text_description, "field 'description'", TextView.class);
        target.identifyButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.button_identify, "field 'identifyButton'", Button.class);
        target.assignButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.button_asign, "field 'assignButton'", Button.class);
        target.removeDeviceButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.button_remove_device, "field 'removeDeviceButton'", Button.class);
        target.textBatteryDescription = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.text_battery, "field 'textBatteryDescription'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.productImage = null;
        target.productName = null;
        target.description = null;
        target.identifyButton = null;
        target.assignButton = null;
        target.removeDeviceButton = null;
        target.textBatteryDescription = null;
        this.target = null;
    }
}
