package com.tado.android.login;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class LoginActivity_ViewBinding<T extends LoginActivity> implements Unbinder {
    protected T target;

    @UiThread
    public LoginActivity_ViewBinding(T target, View source) {
        this.target = target;
        target.tvPassword = (EditText) Utils.findRequiredViewAsType(source, C0676R.id.password, "field 'tvPassword'", EditText.class);
        target.showHidePassword = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.show_password, "field 'showHidePassword'", TextView.class);
        target.createAccount = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.button_create_account, "field 'createAccount'", TextView.class);
        target.titleView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.title_bar_textview, "field 'titleView'", TextView.class);
        target.editTextUsername = (EditText) Utils.findRequiredViewAsType(source, C0676R.id.username, "field 'editTextUsername'", EditText.class);
        target.spinnerUsername = (Spinner) Utils.findRequiredViewAsType(source, C0676R.id.username_spinner, "field 'spinnerUsername'", Spinner.class);
        target.loginButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.proceed_button, "field 'loginButton'", Button.class);
        target.serverSelect = (RelativeLayout) Utils.findRequiredViewAsType(source, C0676R.id.server_select, "field 'serverSelect'", RelativeLayout.class);
        target.invitationMessage = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.login_invitation_message, "field 'invitationMessage'", TextView.class);
        target.demoButton = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.demo, "field 'demoButton'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.tvPassword = null;
        target.showHidePassword = null;
        target.createAccount = null;
        target.titleView = null;
        target.editTextUsername = null;
        target.spinnerUsername = null;
        target.loginButton = null;
        target.serverSelect = null;
        target.invitationMessage = null;
        target.demoButton = null;
        this.target = null;
    }
}
