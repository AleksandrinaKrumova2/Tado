package com.tado.android.installation;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class ACInstallationBaseActivity_ViewBinding<T extends ACInstallationBaseActivity> implements Unbinder {
    protected T target;

    @UiThread
    public ACInstallationBaseActivity_ViewBinding(T target, View source) {
        this.target = target;
        target.titleBarTextview = (TextView) Utils.findOptionalViewAsType(source, C0676R.id.title_bar_textview, "field 'titleBarTextview'", TextView.class);
        target.titleTemplateTextview = (TextView) Utils.findOptionalViewAsType(source, C0676R.id.title_template_textview, "field 'titleTemplateTextview'", TextView.class);
        target.textView = (TextView) Utils.findOptionalViewAsType(source, C0676R.id.textview, "field 'textView'", TextView.class);
        target.proceedButton = (Button) Utils.findOptionalViewAsType(source, C0676R.id.proceed_button, "field 'proceedButton'", Button.class);
        target.centerImage = (ImageView) Utils.findOptionalViewAsType(source, C0676R.id.center_image, "field 'centerImage'", ImageView.class);
        target.centerImageOverlay = (ImageView) Utils.findOptionalViewAsType(source, C0676R.id.center_image_overlay, "field 'centerImageOverlay'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.titleBarTextview = null;
        target.titleTemplateTextview = null;
        target.textView = null;
        target.proceedButton = null;
        target.centerImage = null;
        target.centerImageOverlay = null;
        this.target = null;
    }
}
