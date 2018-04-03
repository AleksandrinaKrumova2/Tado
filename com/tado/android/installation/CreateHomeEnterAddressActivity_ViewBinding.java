package com.tado.android.installation;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.views.progressbar.ProgressBarComponent;

public class CreateHomeEnterAddressActivity_ViewBinding<T extends CreateHomeEnterAddressActivity> implements Unbinder {
    protected T target;

    @UiThread
    public CreateHomeEnterAddressActivity_ViewBinding(T target, View source) {
        this.target = target;
        target.progressBar = (ProgressBarComponent) Utils.findRequiredViewAsType(source, C0676R.id.progressBar, "field 'progressBar'", ProgressBarComponent.class);
        target.titleBar = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.title_bar_textview, "field 'titleBar'", TextView.class);
        target.usernameSpinner = (Spinner) Utils.findRequiredViewAsType(source, C0676R.id.username_spinner, "field 'usernameSpinner'", Spinner.class);
        target.toolbar = (Toolbar) Utils.findRequiredViewAsType(source, C0676R.id.toolbar, "field 'toolbar'", Toolbar.class);
        target.determinedLocation = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.determined_location, "field 'determinedLocation'", TextView.class);
        target.titleTemplate = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.title_template_textview, "field 'titleTemplate'", TextView.class);
        target.address1 = (EditText) Utils.findRequiredViewAsType(source, C0676R.id.input_address1, "field 'address1'", EditText.class);
        target.address2 = (EditText) Utils.findRequiredViewAsType(source, C0676R.id.input_address2, "field 'address2'", EditText.class);
        target.zipcode = (EditText) Utils.findRequiredViewAsType(source, C0676R.id.input_zipcode, "field 'zipcode'", EditText.class);
        target.city = (EditText) Utils.findRequiredViewAsType(source, C0676R.id.input_city, "field 'city'", EditText.class);
        target.country = (Button) Utils.findRequiredViewAsType(source, C0676R.id.select_button, "field 'country'", Button.class);
        target.proceedButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.proceed_button, "field 'proceedButton'", Button.class);
        target.locationLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0676R.id.location_layout, "field 'locationLayout'", LinearLayout.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.progressBar = null;
        target.titleBar = null;
        target.usernameSpinner = null;
        target.toolbar = null;
        target.determinedLocation = null;
        target.titleTemplate = null;
        target.address1 = null;
        target.address2 = null;
        target.zipcode = null;
        target.city = null;
        target.country = null;
        target.proceedButton = null;
        target.locationLayout = null;
        this.target = null;
    }
}
