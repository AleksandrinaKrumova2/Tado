package com.tado.android.installation.srt.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class DevicePropertyPanel_ViewBinding<T extends DevicePropertyPanel> implements Unbinder {
    protected T target;

    @UiThread
    public DevicePropertyPanel_ViewBinding(T target, View source) {
        this.target = target;
        target.mProgressBar = (ProgressBar) Utils.findRequiredViewAsType(source, C0676R.id.device_property_panel_progress, "field 'mProgressBar'", ProgressBar.class);
        target.mImageView = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.device_property_panel_image, "field 'mImageView'", ImageView.class);
        target.mTextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.device_property_panel_text, "field 'mTextView'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mProgressBar = null;
        target.mImageView = null;
        target.mTextView = null;
        this.target = null;
    }
}
