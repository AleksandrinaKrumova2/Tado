package com.tado.android.installation.clc;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.VideoView;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity_ViewBinding;

public class ThermostaticOperationIntroActivity_ViewBinding<T extends ThermostaticOperationIntroActivity> extends ACInstallationBaseActivity_ViewBinding<T> {
    @UiThread
    public ThermostaticOperationIntroActivity_ViewBinding(T target, View source) {
        super(target, source);
        target.videoView = (VideoView) Utils.findRequiredViewAsType(source, C0676R.id.video, "field 'videoView'", VideoView.class);
    }

    public void unbind() {
        ThermostaticOperationIntroActivity target = (ThermostaticOperationIntroActivity) this.target;
        super.unbind();
        target.videoView = null;
    }
}
