package com.tado.android.installation;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class CreateAccountActivity_ViewBinding<T extends CreateAccountActivity> implements Unbinder {
    protected T target;

    @UiThread
    public CreateAccountActivity_ViewBinding(T target, View source) {
        this.target = target;
        target.editName = (EditText) Utils.findRequiredViewAsType(source, C0676R.id.create_account_input_name, "field 'editName'", EditText.class);
        target.editEmail = (EditText) Utils.findRequiredViewAsType(source, C0676R.id.create_account_input_email, "field 'editEmail'", EditText.class);
        target.editPassword = (EditText) Utils.findRequiredViewAsType(source, C0676R.id.create_account_input_pw, "field 'editPassword'", EditText.class);
        target.editConfirmPassword = (EditText) Utils.findRequiredViewAsType(source, C0676R.id.create_account_input_confirm_pw, "field 'editConfirmPassword'", EditText.class);
        target.showHidePassword = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.show_password, "field 'showHidePassword'", TextView.class);
        target.showHideConfirmPassword = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.show_confirm_password, "field 'showHideConfirmPassword'", TextView.class);
        target.titleBar = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.title_bar_textview, "field 'titleBar'", TextView.class);
        target.proceedButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.proceed_button, "field 'proceedButton'", Button.class);
        target.invitationMessage = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.create_account_invitation_message, "field 'invitationMessage'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.editName = null;
        target.editEmail = null;
        target.editPassword = null;
        target.editConfirmPassword = null;
        target.showHidePassword = null;
        target.showHideConfirmPassword = null;
        target.titleBar = null;
        target.proceedButton = null;
        target.invitationMessage = null;
        this.target = null;
    }
}
