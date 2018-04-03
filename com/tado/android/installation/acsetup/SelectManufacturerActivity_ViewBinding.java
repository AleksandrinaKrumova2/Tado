package com.tado.android.installation.acsetup;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity_ViewBinding;

public class SelectManufacturerActivity_ViewBinding<T extends SelectManufacturerActivity> extends ACInstallationBaseActivity_ViewBinding<T> {
    private View view2131296964;

    @UiThread
    public SelectManufacturerActivity_ViewBinding(final T target, View source) {
        super(target, source);
        target.troubleshooting = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.troubleshooting, "field 'troubleshooting'", TextView.class);
        target.center_image = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.center_image, "field 'center_image'", ImageView.class);
        View view = Utils.findRequiredView(source, C0676R.id.select_textview, "field 'selectManufacturer' and method 'selectManufacturers'");
        target.selectManufacturer = (Button) Utils.castView(view, C0676R.id.select_textview, "field 'selectManufacturer'", Button.class);
        this.view2131296964 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.selectManufacturers();
            }
        });
        target.brandhSelectionError = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.brand_selection_error, "field 'brandhSelectionError'", TextView.class);
    }

    public void unbind() {
        SelectManufacturerActivity target = (SelectManufacturerActivity) this.target;
        super.unbind();
        target.troubleshooting = null;
        target.center_image = null;
        target.selectManufacturer = null;
        target.brandhSelectionError = null;
        this.view2131296964.setOnClickListener(null);
        this.view2131296964 = null;
    }
}
