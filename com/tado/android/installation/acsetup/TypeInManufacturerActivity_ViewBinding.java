package com.tado.android.installation.acsetup;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity_ViewBinding;

public class TypeInManufacturerActivity_ViewBinding<T extends TypeInManufacturerActivity> extends ACInstallationBaseActivity_ViewBinding<T> {
    @UiThread
    public TypeInManufacturerActivity_ViewBinding(T target, View source) {
        super(target, source);
        target.manufacturerInput = (EditText) Utils.findRequiredViewAsType(source, C0676R.id.input_manufacturer, "field 'manufacturerInput'", EditText.class);
        target.centerImage = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.center_image, "field 'centerImage'", ImageView.class);
    }

    public void unbind() {
        TypeInManufacturerActivity target = (TypeInManufacturerActivity) this.target;
        super.unbind();
        target.manufacturerInput = null;
        target.centerImage = null;
    }
}
