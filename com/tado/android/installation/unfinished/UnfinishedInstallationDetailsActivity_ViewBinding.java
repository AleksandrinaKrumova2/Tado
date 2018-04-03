package com.tado.android.installation.unfinished;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class UnfinishedInstallationDetailsActivity_ViewBinding<T extends UnfinishedInstallationDetailsActivity> implements Unbinder {
    protected T target;
    private View view2131296385;
    private View view2131296389;
    private View view2131296391;
    private View view2131296396;

    @UiThread
    public UnfinishedInstallationDetailsActivity_ViewBinding(final T target, View source) {
        this.target = target;
        target.titleTextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.text_title, "field 'titleTextView'", TextView.class);
        target.deviceImageView = (ImageView) Utils.findOptionalViewAsType(source, C0676R.id.image_device, "field 'deviceImageView'", ImageView.class);
        target.deviceNameTextView = (TextView) Utils.findOptionalViewAsType(source, C0676R.id.text_device_name, "field 'deviceNameTextView'", TextView.class);
        target.serialTextView = (TextView) Utils.findOptionalViewAsType(source, C0676R.id.text_serial_number, "field 'serialTextView'", TextView.class);
        View view = Utils.findRequiredView(source, C0676R.id.button_identify, "field 'identifyButton'");
        target.identifyButton = (Button) Utils.castView(view, C0676R.id.button_identify, "field 'identifyButton'", Button.class);
        this.view2131296385 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onIdentifyClick(p0);
            }
        });
        view = Utils.findRequiredView(source, C0676R.id.button_resetac, "field 'resetAcButton' and method 'onResetAcClick'");
        target.resetAcButton = (Button) Utils.castView(view, C0676R.id.button_resetac, "field 'resetAcButton'", Button.class);
        this.view2131296391 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onResetAcClick(p0);
            }
        });
        view = Utils.findRequiredView(source, C0676R.id.button_suspend, "field 'suspendButton' and method 'onSuspendClick'");
        target.suspendButton = (Button) Utils.castView(view, C0676R.id.button_suspend, "field 'suspendButton'", Button.class);
        this.view2131296396 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onSuspendClick(p0);
            }
        });
        view = Utils.findRequiredView(source, C0676R.id.button_remove_device, "field 'removeButton' and method 'onRemoveDeviceClick'");
        target.removeButton = (Button) Utils.castView(view, C0676R.id.button_remove_device, "field 'removeButton'", Button.class);
        this.view2131296389 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onRemoveDeviceClick(p0);
            }
        });
        target.helpTextView = (TextView) Utils.findOptionalViewAsType(source, C0676R.id.text_help, "field 'helpTextView'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.titleTextView = null;
        target.deviceImageView = null;
        target.deviceNameTextView = null;
        target.serialTextView = null;
        target.identifyButton = null;
        target.resetAcButton = null;
        target.suspendButton = null;
        target.removeButton = null;
        target.helpTextView = null;
        this.view2131296385.setOnClickListener(null);
        this.view2131296385 = null;
        this.view2131296391.setOnClickListener(null);
        this.view2131296391 = null;
        this.view2131296396.setOnClickListener(null);
        this.view2131296396 = null;
        this.view2131296389.setOnClickListener(null);
        this.view2131296389 = null;
        this.target = null;
    }
}
