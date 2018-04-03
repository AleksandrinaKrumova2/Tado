package com.tado.android.installation.complexteaching;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity_ViewBinding;

public class ChooseMinTempForModeActivity_ViewBinding<T extends ChooseMinTempForModeActivity> extends ACInstallationBaseActivity_ViewBinding<T> {
    @UiThread
    public ChooseMinTempForModeActivity_ViewBinding(T target, View source) {
        super(target, source);
        target.mCommandImage = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.command_image, "field 'mCommandImage'", ImageView.class);
        target.mInputTemp = (EditText) Utils.findRequiredViewAsType(source, C0676R.id.max_min_temp_input, "field 'mInputTemp'", EditText.class);
    }

    public void unbind() {
        ChooseMinTempForModeActivity target = (ChooseMinTempForModeActivity) this.target;
        super.unbind();
        target.mCommandImage = null;
        target.mInputTemp = null;
    }
}
