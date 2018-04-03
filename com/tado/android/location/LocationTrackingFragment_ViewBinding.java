package com.tado.android.location;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class LocationTrackingFragment_ViewBinding<T extends LocationTrackingFragment> implements Unbinder {
    protected T target;
    private View view2131296411;
    private View view2131296412;
    private View view2131296413;
    private View view2131296414;
    private View view2131296415;

    @UiThread
    public LocationTrackingFragment_ViewBinding(final T target, View source) {
        this.target = target;
        View view = Utils.findRequiredView(source, C0676R.id.check_notification, "field 'notificationCheckBox' and method 'onCheckedChanged'");
        target.notificationCheckBox = (CheckBox) Utils.castView(view, C0676R.id.check_notification, "field 'notificationCheckBox'", CheckBox.class);
        this.view2131296413 = view;
        ((CompoundButton) view).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton p0, boolean p1) {
                target.onCheckedChanged(p0, p1);
            }
        });
        view = Utils.findRequiredView(source, C0676R.id.check_posted, "field 'postedCheckBox' and method 'onCheckedChanged'");
        target.postedCheckBox = (CheckBox) Utils.castView(view, C0676R.id.check_posted, "field 'postedCheckBox'", CheckBox.class);
        this.view2131296414 = view;
        ((CompoundButton) view).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton p0, boolean p1) {
                target.onCheckedChanged(p0, p1);
            }
        });
        view = Utils.findRequiredView(source, C0676R.id.check_failed, "field 'failedCheckBox' and method 'onCheckedChanged'");
        target.failedCheckBox = (CheckBox) Utils.castView(view, C0676R.id.check_failed, "field 'failedCheckBox'", CheckBox.class);
        this.view2131296411 = view;
        ((CompoundButton) view).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton p0, boolean p1) {
                target.onCheckedChanged(p0, p1);
            }
        });
        view = Utils.findRequiredView(source, C0676R.id.check_filtered, "field 'filterCheckBox' and method 'onCheckedChanged'");
        target.filterCheckBox = (CheckBox) Utils.castView(view, C0676R.id.check_filtered, "field 'filterCheckBox'", CheckBox.class);
        this.view2131296412 = view;
        ((CompoundButton) view).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton p0, boolean p1) {
                target.onCheckedChanged(p0, p1);
            }
        });
        view = Utils.findRequiredView(source, C0676R.id.check_unknown, "field 'unknownCheckBox' and method 'onCheckedChanged'");
        target.unknownCheckBox = (CheckBox) Utils.castView(view, C0676R.id.check_unknown, "field 'unknownCheckBox'", CheckBox.class);
        this.view2131296415 = view;
        ((CompoundButton) view).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton p0, boolean p1) {
                target.onCheckedChanged(p0, p1);
            }
        });
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.notificationCheckBox = null;
        target.postedCheckBox = null;
        target.failedCheckBox = null;
        target.filterCheckBox = null;
        target.unknownCheckBox = null;
        ((CompoundButton) this.view2131296413).setOnCheckedChangeListener(null);
        this.view2131296413 = null;
        ((CompoundButton) this.view2131296414).setOnCheckedChangeListener(null);
        this.view2131296414 = null;
        ((CompoundButton) this.view2131296411).setOnCheckedChangeListener(null);
        this.view2131296411 = null;
        ((CompoundButton) this.view2131296412).setOnCheckedChangeListener(null);
        this.view2131296412 = null;
        ((CompoundButton) this.view2131296415).setOnCheckedChangeListener(null);
        this.view2131296415 = null;
        this.target = null;
    }
}
