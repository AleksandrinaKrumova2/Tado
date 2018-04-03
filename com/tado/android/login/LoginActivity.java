package com.tado.android.login;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tado.C0676R;
import com.tado.android.analytics.AnalyticsConstants.Events;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.controllers.LoggerController;
import com.tado.android.controllers.NavigationController;
import com.tado.android.demo.DemoConfig;
import com.tado.android.demo.DemoUtils;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.dialogs.AlertWarningDialogListener;
import com.tado.android.installation.CreateAccountActivity;
import com.tado.android.installation.InvitationReviewActivity;
import com.tado.android.login.LoginPresenter.LoginView;
import com.tado.android.rest.model.Invitation;
import com.tado.android.utils.Server;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.Util;

public class LoginActivity extends AppCompatActivity implements LoginView {
    @BindView(2131296383)
    public TextView createAccount;
    private OnClickListener createAccountClickListener = new C10309();
    @BindView(2131296548)
    public TextView demoButton;
    @BindView(2131297194)
    public EditText editTextUsername;
    private Invitation invitation;
    @BindView(2131296726)
    TextView invitationMessage;
    private int loggerTabCounter;
    @BindView(2131296870)
    public Button loginButton;
    private ProgressDialog mProgressDialog;
    public LoginPresenter presenter;
    @BindView(2131296965)
    public RelativeLayout serverSelect;
    @BindView(2131296972)
    public TextView showHidePassword;
    public OnClickListener signInClick = new C10298();
    @BindView(2131297196)
    public Spinner spinnerUsername;
    private int tapCounter = 0;
    @BindView(2131297146)
    public TextView titleView;
    @BindView(2131296845)
    public EditText tvPassword;

    class C10232 implements OnClickListener {
        C10232() {
        }

        public void onClick(View v) {
            LoginActivity.this.loggerTabCounter = LoginActivity.this.loggerTabCounter + 1;
            LoginActivity.this.callLoggerAsynchronousTask();
            if (LoginActivity.this.loggerTabCounter > 5 && LoginActivity.this.getResources().getBoolean(C0676R.bool.logger)) {
                LoginActivity.this.loggerTabCounter = 0;
                LoginActivity.this.showLogSelection();
            }
        }
    }

    class C10243 implements OnClickListener {
        C10243() {
        }

        public void onClick(View v) {
            LoginActivity.this.tapCounter = LoginActivity.this.tapCounter + 1;
            LoginActivity.this.callAsynchronousTask();
            if (LoginActivity.this.tapCounter % 5 == 0) {
                LoginActivity.this.showServerSelection();
            }
        }
    }

    class C10254 implements OnFocusChangeListener {
        C10254() {
        }

        public void onFocusChange(View v, boolean hasFocus) {
            String password = LoginActivity.this.tvPassword.getText().toString();
            if (hasFocus && !password.isEmpty()) {
                LoginActivity.this.showHidePassword.setVisibility(0);
            } else if (!hasFocus) {
                LoginActivity.this.showHidePassword.setVisibility(4);
            }
        }
    }

    class C10265 implements OnEditorActionListener {
        C10265() {
        }

        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId != 4) {
                return false;
            }
            LoginActivity.this.signInClick.onClick(null);
            return true;
        }
    }

    class C10276 implements TextWatcher {
        C10276() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {
            if (LoginActivity.this.tvPassword.getText().toString().isEmpty()) {
                LoginActivity.this.showHidePassword.setVisibility(4);
            } else {
                LoginActivity.this.showHidePassword.setVisibility(0);
            }
        }
    }

    class C10287 implements OnTouchListener {
        C10287() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() != 0) {
                return false;
            }
            TextView showHideButton = (TextView) v;
            int start;
            int end;
            if (((Boolean) LoginActivity.this.tvPassword.getTag(C0676R.id.login_password_visible)).booleanValue()) {
                start = LoginActivity.this.tvPassword.getSelectionStart();
                end = LoginActivity.this.tvPassword.getSelectionEnd();
                LoginActivity.this.tvPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                LoginActivity.this.tvPassword.setSelection(start, end);
                showHideButton.setText(LoginActivity.this.getString(C0676R.string.signin_credentialsForm_showPasswordButton));
                LoginActivity.this.tvPassword.setTag(C0676R.id.login_password_visible, Boolean.valueOf(false));
                AnalyticsHelper.trackEvent(LoginActivity.this.getApplication(), Screen.SIGNIN, Screen.SIGNIN, "HidePassword");
            } else {
                start = LoginActivity.this.tvPassword.getSelectionStart();
                end = LoginActivity.this.tvPassword.getSelectionEnd();
                LoginActivity.this.tvPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                LoginActivity.this.tvPassword.setSelection(start, end);
                showHideButton.setText(LoginActivity.this.getString(C0676R.string.signin_credentialsForm_hidePasswordButton));
                LoginActivity.this.tvPassword.setTag(C0676R.id.login_password_visible, Boolean.valueOf(true));
                AnalyticsHelper.trackEvent(LoginActivity.this.getApplication(), Screen.SIGNIN, Screen.SIGNIN, "ShowPassword", Long.valueOf((long) LoginActivity.this.tvPassword.getText().length()));
            }
            return true;
        }
    }

    class C10298 implements OnClickListener {
        C10298() {
        }

        public void onClick(View v) {
            LoginActivity.this.presenter.signIn(LoginActivity.this.editTextUsername.getText().toString(), LoginActivity.this.tvPassword.getText().toString(), LoginActivity.this);
        }
    }

    class C10309 implements OnClickListener {
        C10309() {
        }

        public void onClick(View v) {
            AnalyticsHelper.trackEvent(LoginActivity.this.getApplication(), Screen.SIGNIN, Screen.CREATE_ACCOUNT);
            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
            if (LoginActivity.this.invitation != null) {
                intent.putExtra("keyInvitation", LoginActivity.this.invitation);
            }
            LoginActivity.this.startActivity(intent);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_login);
        ButterKnife.bind((Activity) this);
        this.presenter = new LoginPresenter(this);
        this.titleView.setText(C0676R.string.signin_credentialsForm_title);
        this.tvPassword.setTag(C0676R.id.login_password_visible, Boolean.valueOf(false));
        this.loginButton.setText(getString(C0676R.string.signin_credentialsForm_confirmButton));
        DemoUtils.showHideDemoButton(this.demoButton, this);
        try {
            ((ViewGroup) this.spinnerUsername.getParent()).removeView(this.spinnerUsername);
        } catch (Exception e) {
            Snitcher.start().toCrashlytics().logException(e);
        }
        this.loginButton.setOnClickListener(this.signInClick);
        this.createAccount.setOnClickListener(this.createAccountClickListener);
        this.loggerTabCounter = 0;
        this.titleView.setOnClickListener(new C10232());
        this.tapCounter = 0;
        this.serverSelect.setOnClickListener(new C10243());
        this.tvPassword.setOnFocusChangeListener(new C10254());
        this.tvPassword.setOnEditorActionListener(new C10265());
        if (this.tvPassword != null) {
            this.tvPassword.addTextChangedListener(new C10276());
        }
        this.showHidePassword.setOnTouchListener(new C10287());
        if (getIntent().hasExtra("keyInvitation")) {
            this.invitation = (Invitation) getIntent().getExtras().getParcelable("keyInvitation");
            this.presenter.handleInvitation(this.invitation);
        }
    }

    protected void onResume() {
        super.onResume();
        AnalyticsHelper.trackPageView(getApplication(), Screen.SIGNIN);
    }

    protected void onPause() {
        super.onPause();
        if (this.mProgressDialog != null) {
            this.mProgressDialog.dismiss();
            this.mProgressDialog = null;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        this.presenter.unbindView();
    }

    public void callAsynchronousTask() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                try {
                    LoginActivity.this.tapCounter = 0;
                } catch (Exception e) {
                }
            }
        }, 2000);
    }

    public void callLoggerAsynchronousTask() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                try {
                    LoginActivity.this.loggerTabCounter = 0;
                } catch (Exception e) {
                }
            }
        }, 2000);
    }

    public void showDialogNoInternet() {
        AlertDialogs.showSimpleWarning("", getResources().getString(C0676R.string.login_login_noconnection), getString(C0676R.string.ok), this, null);
    }

    public void showDialogIncorrectCredentials() {
        AlertDialogs.showSimpleWarning(getString(C0676R.string.app_name), getString(C0676R.string.signin_credentialsForm_errors_legacyInvalidCredentialsError), getString(C0676R.string.ok), this, new AlertWarningDialogListener() {
            public void OnOKClicked() {
            }
        });
    }

    public void enterDemoMode(View view) {
        view.setEnabled(false);
        showProgressDialog();
        AnalyticsHelper.trackEvent((Activity) this, Events.DEMO, "startDemo", Integer.toString(DemoConfig.getStartCounter()));
        NavigationController.enterDemoMode(this);
    }

    public void forgotPassword(View view) {
        NavigationController.navigateToForgotPassword(this, Uri.parse(getString(C0676R.string.signin_credentialsForm_forgotPasswordURL)));
    }

    public void showProgressDialog() {
        this.mProgressDialog = new ProgressDialog(this);
        this.mProgressDialog.setMessage(getString(C0676R.string.loading_title));
        this.mProgressDialog.show();
    }

    public void dismissProgressDialog() {
        if (this.mProgressDialog != null) {
            this.mProgressDialog.dismiss();
            this.mProgressDialog = null;
        }
    }

    public void showServerSelection() {
        Dialog dialog = new Server().getServerSelectionDialog(this, this.presenter);
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
    }

    public void showLogSelection() {
        LoggerController.INSTANCE.showSendLogsDialog(this, this.titleView, true);
    }

    public void showInvitationNote(String homeName, String address) {
        this.invitationMessage.setText(Util.getText(this, C0676R.string.account_invitation_signInDescription, homeName, address));
        this.invitationMessage.setVisibility(0);
    }

    public void prefillEmail(String email) {
        this.editTextUsername.setText(email);
    }

    public void showInvitationScreen(Invitation invitation) {
        Intent intent = new Intent(this, InvitationReviewActivity.class);
        intent.putExtra("keyInvitation", invitation);
        startActivity(intent);
    }
}
