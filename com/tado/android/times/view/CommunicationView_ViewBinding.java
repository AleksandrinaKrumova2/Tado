package com.tado.android.times.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class CommunicationView_ViewBinding<T extends CommunicationView> implements Unbinder {
    protected T target;

    @UiThread
    public CommunicationView_ViewBinding(T target, View source) {
        this.target = target;
        target.leftImage = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.banner_left, "field 'leftImage'", ImageView.class);
        target.rightImage = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.banner_right, "field 'rightImage'", ImageView.class);
        target.bgImage = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.banner_bg, "field 'bgImage'", ImageView.class);
        target.textView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.banner_text, "field 'textView'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.leftImage = null;
        target.rightImage = null;
        target.bgImage = null;
        target.textView = null;
        this.target = null;
    }
}
