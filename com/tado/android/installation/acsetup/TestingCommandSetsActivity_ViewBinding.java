package com.tado.android.installation.acsetup;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity_ViewBinding;

public class TestingCommandSetsActivity_ViewBinding<T extends TestingCommandSetsActivity> extends ACInstallationBaseActivity_ViewBinding<T> {
    @UiThread
    public TestingCommandSetsActivity_ViewBinding(T target, View source) {
        super(target, source);
        target.part1TextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.command_set_finding_candidates, "field 'part1TextView'", TextView.class);
        target.part1ImageView = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.command_set_finding_candidates_img, "field 'part1ImageView'", ImageView.class);
        target.redoTextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.troubleshooting, "field 'redoTextView'", TextView.class);
        target.noImageView = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.imageView_no, "field 'noImageView'", ImageView.class);
        target.yesImageView = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.imageView_yes, "field 'yesImageView'", ImageView.class);
        target.animationView = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.animation, "field 'animationView'", ImageView.class);
        target.progressBar = (ProgressBar) Utils.findRequiredViewAsType(source, C0676R.id.progressBar, "field 'progressBar'", ProgressBar.class);
    }

    public void unbind() {
        TestingCommandSetsActivity target = (TestingCommandSetsActivity) this.target;
        super.unbind();
        target.part1TextView = null;
        target.part1ImageView = null;
        target.redoTextView = null;
        target.noImageView = null;
        target.yesImageView = null;
        target.animationView = null;
        target.progressBar = null;
    }
}
