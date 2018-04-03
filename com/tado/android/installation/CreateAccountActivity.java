package com.tado.android.installation;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tado.C0676R;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.client.APICall;
import com.tado.android.client.APICallListener;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.entities.Account;
import com.tado.android.entities.AccountResponse;
import com.tado.android.entities.ServerError;
import com.tado.android.login.LoginActivity;
import com.tado.android.requests.CreateAccountRequest;
import com.tado.android.requests.Request;
import com.tado.android.responses.CreateAccountResponse;
import com.tado.android.responses.Response;
import com.tado.android.rest.model.Invitation;
import com.tado.android.utils.Constants;
import com.tado.android.utils.TextValidator;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;

public class CreateAccountActivity extends AppCompatActivity implements APICallListener {
    private static final int MINIMUM_PW_CHAR_LENGTH = 6;
    @BindView(2131296528)
    EditText editConfirmPassword;
    @BindView(2131296529)
    EditText editEmail;
    @BindView(2131296530)
    EditText editName;
    @BindView(2131296531)
    EditText editPassword;
    private Invitation invitation;
    @BindView(2131296532)
    TextView invitationMessage;
    private String mPassword;
    @BindView(2131296870)
    Button proceedButton;
    @BindView(2131296971)
    TextView showHideConfirmPassword;
    @BindView(2131296972)
    TextView showHidePassword;
    @BindView(2131297146)
    TextView titleBar;

    class C07903 implements OnFocusChangeListener {
        C07903() {
        }

        public void onFocusChange(View v, boolean hasFocus) {
            String password = CreateAccountActivity.this.editPassword.getText().toString();
            if (hasFocus && !password.isEmpty()) {
                CreateAccountActivity.this.showHidePassword.setVisibility(0);
                CreateAccountActivity.this.editPassword.setError(null);
            } else if (!hasFocus) {
                CreateAccountActivity.this.showHidePassword.setVisibility(4);
                CreateAccountActivity.this.editPassword.setError(null);
                CreateAccountActivity.this.editConfirmPassword.setError(null);
                if (password.length() < 6) {
                    CreateAccountActivity.this.editPassword.setError(CreateAccountActivity.this.getString(C0676R.string.createUserAccount_errors_passwordFieldInvalidErrorLabel));
                }
                String confirmPassword = CreateAccountActivity.this.editConfirmPassword.getText().toString();
                if (confirmPassword.length() > 0 && password.compareTo(confirmPassword) != 0) {
                    CreateAccountActivity.this.editConfirmPassword.setError(CreateAccountActivity.this.getString(C0676R.string.createUserAccount_errors_passwordConfirmationFieldNoMatchErrorLabel));
                }
            }
        }
    }

    class C07914 implements TextWatcher {
        C07914() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {
            if (CreateAccountActivity.this.editPassword.getText().toString().isEmpty()) {
                CreateAccountActivity.this.showHidePassword.setVisibility(4);
            } else {
                CreateAccountActivity.this.showHidePassword.setVisibility(0);
            }
        }
    }

    class C07925 implements OnFocusChangeListener {
        C07925() {
        }

        public void onFocusChange(View v, boolean hasFocus) {
            String confirmPassword = CreateAccountActivity.this.editConfirmPassword.getText().toString();
            if (hasFocus && !confirmPassword.isEmpty()) {
                CreateAccountActivity.this.showHideConfirmPassword.setVisibility(0);
                CreateAccountActivity.this.editConfirmPassword.setError(null);
            } else if (!hasFocus) {
                CreateAccountActivity.this.showHideConfirmPassword.setVisibility(4);
                CreateAccountActivity.this.editConfirmPassword.setError(null);
                String password = CreateAccountActivity.this.editPassword.getText().toString();
                if (confirmPassword.length() > 0 && password.compareTo(confirmPassword) != 0) {
                    CreateAccountActivity.this.editConfirmPassword.setError(CreateAccountActivity.this.getString(C0676R.string.createUserAccount_errors_passwordConfirmationFieldNoMatchErrorLabel));
                }
            }
        }
    }

    class C07936 implements TextWatcher {
        C07936() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {
            if (CreateAccountActivity.this.editConfirmPassword.getText().toString().isEmpty()) {
                CreateAccountActivity.this.showHideConfirmPassword.setVisibility(4);
            } else {
                CreateAccountActivity.this.showHideConfirmPassword.setVisibility(0);
            }
        }
    }

    class C07947 implements OnTouchListener {
        C07947() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() != 0) {
                return false;
            }
            TextView showHideButton = (TextView) v;
            int start;
            int end;
            if (showHideButton.getText().toString().compareTo(CreateAccountActivity.this.getString(C0676R.string.createUserAccount_showPasswordButton)) == 0) {
                start = CreateAccountActivity.this.editPassword.getSelectionStart();
                end = CreateAccountActivity.this.editPassword.getSelectionEnd();
                CreateAccountActivity.this.editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                CreateAccountActivity.this.editPassword.setSelection(start, end);
                showHideButton.setText(CreateAccountActivity.this.getString(C0676R.string.createUserAccount_hidePasswordButton));
            } else {
                start = CreateAccountActivity.this.editPassword.getSelectionStart();
                end = CreateAccountActivity.this.editPassword.getSelectionEnd();
                CreateAccountActivity.this.editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                CreateAccountActivity.this.editPassword.setSelection(start, end);
                showHideButton.setText(CreateAccountActivity.this.getString(C0676R.string.createUserAccount_showPasswordButton));
            }
            return true;
        }
    }

    class C07958 implements OnTouchListener {
        C07958() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() != 0) {
                return false;
            }
            TextView showHideButton = (TextView) v;
            if (showHideButton.getText().toString().compareTo(CreateAccountActivity.this.getString(C0676R.string.createUserAccount_showPasswordButton)) == 0) {
                CreateAccountActivity.this.editConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                showHideButton.setText(CreateAccountActivity.this.getString(C0676R.string.createUserAccount_hidePasswordButton));
            } else {
                CreateAccountActivity.this.editConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                showHideButton.setText(CreateAccountActivity.this.getString(C0676R.string.createUserAccount_showPasswordButton));
            }
            return true;
        }
    }

    class C07969 implements OnClickListener {
        C07969() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_create_account);
        ButterKnife.bind((Activity) this);
        this.titleBar.setText(getString(C0676R.string.createUserAccount_title));
        this.proceedButton.setText(getString(C0676R.string.createUserAccount_confirmButton));
        this.editName.addTextChangedListener(new TextValidator(this.editName) {
            public void validate(TextView textView, String text) {
                if (text.compareTo("") == 0 || text.indexOf(" ") < 0 || text.indexOf(" ") == text.length() - 1) {
                    textView.setError(CreateAccountActivity.this.getString(C0676R.string.createUserAccount_errors_legacyFullNameFieldEmptyErrorLabel));
                } else {
                    textView.setError(null);
                }
            }
        });
        this.editEmail.addTextChangedListener(new TextValidator(this.editEmail) {
            public void validate(TextView textView, String text) {
                if (text.compareTo("") == 0) {
                    textView.setError(CreateAccountActivity.this.getString(C0676R.string.createUserAccount_errors_emailFieldEmptyErrorLabel));
                } else if (Util.isValidEmail(text)) {
                    textView.setError(null);
                } else {
                    textView.setError(CreateAccountActivity.this.getString(C0676R.string.createUserAccount_errors_emailFieldInvalidErrorLabel));
                }
            }
        });
        this.editPassword.setOnFocusChangeListener(new C07903());
        this.editPassword.addTextChangedListener(new C07914());
        this.editConfirmPassword.setOnFocusChangeListener(new C07925());
        this.editConfirmPassword.addTextChangedListener(new C07936());
        this.showHidePassword.setOnTouchListener(new C07947());
        this.showHideConfirmPassword.setOnTouchListener(new C07958());
        if (getIntent().hasExtra("keyInvitation")) {
            this.invitation = (Invitation) getIntent().getExtras().getParcelable("keyInvitation");
            if (this.invitation == null) {
                return;
            }
            if (this.invitation.isValid()) {
                showInvitationNote();
                prefillEmail(this.invitation.getEmail());
                return;
            }
            showInvitationWarning();
        }
    }

    protected void onResume() {
        super.onResume();
        AnalyticsHelper.trackPageView(getApplication(), Screen.CREATE_ACCOUNT);
    }

    private void showInvitationNote() {
        this.invitationMessage.setText(Util.getText(this, C0676R.string.account_invitation_createAccountDescription, this.invitation.getHome().getName(), this.invitation.getHome().getAddress().getAddressLine1()));
        this.invitationMessage.setVisibility(0);
    }

    private void prefillEmail(String email) {
        this.editEmail.setText(email);
    }

    private void showInvitationWarning() {
        new Builder(this).setTitle(getString(C0676R.string.account_invitation_invalid_title)).setMessage(getText(C0676R.string.account_invitation_invalid_description)).setPositiveButton(getString(C0676R.string.account_invitation_invalid_okButton), new C07969()).setCancelable(false).create().show();
    }

    public void proceedClick(View view) {
        EditText editInputName = (EditText) findViewById(C0676R.id.create_account_input_name);
        String name = editInputName.getText().toString();
        EditText editInputEmail = (EditText) findViewById(C0676R.id.create_account_input_email);
        String email = editInputEmail.getText().toString();
        EditText editInputPassword = (EditText) findViewById(C0676R.id.create_account_input_pw);
        this.mPassword = editInputPassword.getText().toString();
        EditText editInputPasswordConfirmation = (EditText) findViewById(C0676R.id.create_account_input_confirm_pw);
        String confirmPassword = editInputPasswordConfirmation.getText().toString();
        boolean validInputs = true;
        if (name.compareTo("") == 0 || name.indexOf(" ") < 0 || name.indexOf(" ") == name.length() - 1) {
            validInputs = false;
            editInputName.setText("");
        }
        if (email.compareTo("") == 0 || !Util.isValidEmail(email)) {
            validInputs = false;
            editInputEmail.setText("");
        }
        if (this.mPassword.length() < 6) {
            validInputs = false;
            editInputPassword.setError(getString(C0676R.string.createUserAccount_errors_passwordFieldInvalidErrorLabel));
            editInputPassword.setText("");
        }
        if (this.mPassword.compareTo(confirmPassword) != 0) {
            validInputs = false;
            editInputPasswordConfirmation.setError(getString(C0676R.string.createUserAccount_errors_passwordConfirmationFieldNoMatchErrorLabel));
            editInputPasswordConfirmation.setText("");
        }
        if (validInputs) {
            Account account = new Account();
            account.setName(name);
            account.setEmail(email);
            account.setPassword(this.mPassword);
            APICall ac = new APICall(new CreateAccountRequest(Constants.URL_USERS, account), this);
            ac.setmShowLoaderDialog(true);
            ac.setAPICallListener(this);
            ac.execute(new Void[0]);
        }
    }

    public void logIn(View view) {
        AnalyticsHelper.trackEvent(getApplication(), "NewAccount", Screen.SIGNIN);
        Intent intent = new Intent(this, LoginActivity.class);
        if (this.invitation != null) {
            intent.putExtra("keyInvitation", this.invitation);
        }
        InstallationProcessController.startActivity((Activity) this, intent, false);
    }

    public void onProcessResponse(APICall call, Response response) {
        Intent intent;
        AccountResponse accountResponse = ((CreateAccountResponse) response).getAccountResponse();
        UserConfig.setUsername(accountResponse.getUsername());
        UserConfig.setPassword(this.mPassword);
        if (this.invitation == null || !this.invitation.isValid()) {
            intent = new Intent(this, CreateHomeWelcomeActivity.class);
            intent.putExtra(CreateHomeContactDetailsActivity.INTENT_NAME, accountResponse.getName());
            intent.putExtra("email", accountResponse.getEmail());
        } else {
            intent = new Intent(this, InvitationReviewActivity.class);
            intent.putExtra("keyInvitation", this.invitation);
        }
        InstallationProcessController.startActivity((Activity) this, intent, true);
    }

    public void onCallFailed(APICall call, Response response) {
        Request request = call.getRequest();
        if (response == null || ((CreateAccountResponse) response).getAccountResponse() == null || ((CreateAccountResponse) response).getAccountResponse().getErrors() == null) {
            InstallationProcessController.showConnectionError(this, request, this);
            return;
        }
        ServerError error = ((CreateAccountResponse) response).getAccountResponse().getErrors()[0];
        String code = error.getCode();
        int i = -1;
        switch (code.hashCode()) {
            case -1455361627:
                if (code.equals("email.invalid")) {
                    i = 1;
                    break;
                }
                break;
            case 1280441566:
                if (code.equals(Constants.SERVER_ERROR_MAIL_NOT_UNIQUE)) {
                    i = 0;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                this.editEmail.setError(getString(C0676R.string.createUserAccount_errors_emailFieldEmailInUseErrorLabel));
                break;
            case 1:
                this.editEmail.setError(getString(C0676R.string.createUserAccount_errors_emailFieldInvalidErrorLabel));
                break;
            default:
                InstallationProcessController.handleError((Activity) this, error, request, response);
                break;
        }
        final ScrollView scrollView = (ScrollView) ((ViewGroup) findViewById(16908290)).getChildAt(0);
        scrollView.post(new Runnable() {
            public void run() {
                scrollView.smoothScrollTo(0, 0);
            }
        });
    }

    public void termsAndConditions(View view) {
        AlertDialogs.showTermsAndConditionsDialog(getString(C0676R.string.createUserAccount_goToLegalInfoPrompt_title), getString(C0676R.string.createUserAccount_goToLegalInfoPrompt_cancelButton), this, null);
    }
}
