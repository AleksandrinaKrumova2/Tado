package com.tado.android.installation.complexteaching;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity_ViewBinding;

public class ChooseMaxTempForModeActivity_ViewBinding<T extends ChooseMaxTempForModeActivity> extends ACInstallationBaseActivity_ViewBinding<T> {
    @UiThread
    public ChooseMaxTempForModeActivity_ViewBinding(T target, View source) {
        super(target, source);
        target.mCommandImage = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.command_image, "field 'mCommandImage'", ImageView.class);
    }

    public void unbind() {
        ChooseMaxTempForModeActivity target = (ChooseMaxTempForModeActivity) this.target;
        super.unbind();
        target.mCommandImage = null;
    }
}
