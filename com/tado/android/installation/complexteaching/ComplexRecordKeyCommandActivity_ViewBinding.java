package com.tado.android.installation.complexteaching;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity_ViewBinding;
import com.tado.android.views.TemperatureGridView;

public class ComplexRecordKeyCommandActivity_ViewBinding<T extends ComplexRecordKeyCommandActivity> extends ACInstallationBaseActivity_ViewBinding<T> {
    @UiThread
    public ComplexRecordKeyCommandActivity_ViewBinding(T target, View source) {
        super(target, source);
        target.mGridView = (TemperatureGridView) Utils.findRequiredViewAsType(source, C0676R.id.grid_view, "field 'mGridView'", TemperatureGridView.class);
        target.mCommandView = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.command_image, "field 'mCommandView'", ImageView.class);
        target.mTickView = Utils.findRequiredView(source, C0676R.id.tick_image, "field 'mTickView'");
        target.mRemoteView = Utils.findRequiredView(source, C0676R.id.remote_control_image, "field 'mRemoteView'");
        target.mProgressLayout = Utils.findRequiredView(source, C0676R.id.progressBar_layout, "field 'mProgressLayout'");
        target.mRepeatButton = Utils.findRequiredView(source, C0676R.id.repeat, "field 'mRepeatButton'");
    }

    public void unbind() {
        ComplexRecordKeyCommandActivity target = (ComplexRecordKeyCommandActivity) this.target;
        super.unbind();
        target.mGridView = null;
        target.mCommandView = null;
        target.mTickView = null;
        target.mRemoteView = null;
        target.mProgressLayout = null;
        target.mRepeatButton = null;
    }
}
