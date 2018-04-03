package com.tado.android.alert_dialogs;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class RestrictionDialogFragment_ViewBinding<T extends RestrictionDialogFragment> implements Unbinder {
    protected T target;

    @UiThread
    public RestrictionDialogFragment_ViewBinding(T target, View source) {
        this.target = target;
        target.mCloseView = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.dialog_restriction_close_image, "field 'mCloseView'", ImageView.class);
        target.mContentView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.dialog_restriction_content, "field 'mContentView'", TextView.class);
        target.mTitleView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.dialog_restriction_title, "field 'mTitleView'", TextView.class);
        target.mContentImageView = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.dialog_restriction_content_image, "field 'mContentImageView'", ImageView.class);
        target.mTitleBarBackground = Utils.findRequiredView(source, C0676R.id.dialog_restriction_title_bar_background, "field 'mTitleBarBackground'");
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mCloseView = null;
        target.mContentView = null;
        target.mTitleView = null;
        target.mContentImageView = null;
        target.mTitleBarBackground = null;
        this.target = null;
    }
}
