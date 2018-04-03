package com.tado.android.installation.acsetup;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity_ViewBinding;

public class CommandSetUpdateActivity_ViewBinding<T extends CommandSetUpdateActivity> extends ACInstallationBaseActivity_ViewBinding<T> {
    @UiThread
    public CommandSetUpdateActivity_ViewBinding(T target, View source) {
        super(target, source);
        target.troubleshootingTextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.troubleshooting, "field 'troubleshootingTextView'", TextView.class);
    }

    public void unbind() {
        CommandSetUpdateActivity target = (CommandSetUpdateActivity) this.target;
        super.unbind();
        target.troubleshootingTextView = null;
    }
}
