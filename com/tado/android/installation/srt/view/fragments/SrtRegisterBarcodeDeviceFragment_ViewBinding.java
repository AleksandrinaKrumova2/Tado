package com.tado.android.installation.srt.view.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class SrtRegisterBarcodeDeviceFragment_ViewBinding<T extends SrtRegisterBarcodeDeviceFragment> implements Unbinder {
    protected T target;

    @UiThread
    public SrtRegisterBarcodeDeviceFragment_ViewBinding(T target, View source) {
        this.target = target;
        target.messageView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.register_device_message, "field 'messageView'", TextView.class);
        target.imageView = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.register_device_image, "field 'imageView'", ImageView.class);
        target.titleView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.register_device_title, "field 'titleView'", TextView.class);
        target.scrollView = (ScrollView) Utils.findRequiredViewAsType(source, C0676R.id.register_device_layout, "field 'scrollView'", ScrollView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.messageView = null;
        target.imageView = null;
        target.titleView = null;
        target.scrollView = null;
        this.target = null;
    }
}
