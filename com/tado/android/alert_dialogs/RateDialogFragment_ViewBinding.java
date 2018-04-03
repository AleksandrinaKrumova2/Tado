package com.tado.android.alert_dialogs;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class RateDialogFragment_ViewBinding<T extends RateDialogFragment> implements Unbinder {
    protected T target;

    @UiThread
    public RateDialogFragment_ViewBinding(T target, View source) {
        this.target = target;
        target.mFirstButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.rate_first_button, "field 'mFirstButton'", Button.class);
        target.mTitleView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.rate_title, "field 'mTitleView'", TextView.class);
        target.mCancelButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.rate_cancel_button, "field 'mCancelButton'", Button.class);
        target.mUpButton = (ImageButton) Utils.findRequiredViewAsType(source, C0676R.id.rate_up_button, "field 'mUpButton'", ImageButton.class);
        target.mDownButton = (ImageButton) Utils.findRequiredViewAsType(source, C0676R.id.rate_down_button, "field 'mDownButton'", ImageButton.class);
        target.mOpinionLayout = Utils.findRequiredView(source, C0676R.id.rate_opinion_layout, "field 'mOpinionLayout'");
        target.mRateDownContainer = Utils.findRequiredView(source, C0676R.id.rate_down_container, "field 'mRateDownContainer'");
        target.mRateUpContainer = Utils.findRequiredView(source, C0676R.id.rate_up_container, "field 'mRateUpContainer'");
        target.mRateUpImage = Utils.findRequiredView(source, C0676R.id.rate_up_image, "field 'mRateUpImage'");
        target.mRateDownImage = Utils.findRequiredView(source, C0676R.id.rate_down_image, "field 'mRateDownImage'");
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mFirstButton = null;
        target.mTitleView = null;
        target.mCancelButton = null;
        target.mUpButton = null;
        target.mDownButton = null;
        target.mOpinionLayout = null;
        target.mRateDownContainer = null;
        target.mRateUpContainer = null;
        target.mRateUpImage = null;
        target.mRateDownImage = null;
        this.target = null;
    }
}
