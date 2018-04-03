package com.tado.android.installation.acsetup;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity_ViewBinding;

public class PrePointToAcActivity_ViewBinding<T extends PrePointToAcActivity> extends ACInstallationBaseActivity_ViewBinding<T> {
    @UiThread
    public PrePointToAcActivity_ViewBinding(T target, View source) {
        super(target, source);
        target.centerImage = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.center_image, "field 'centerImage'", ImageView.class);
    }

    public void unbind() {
        PrePointToAcActivity target = (PrePointToAcActivity) this.target;
        super.unbind();
        target.centerImage = null;
    }
}
