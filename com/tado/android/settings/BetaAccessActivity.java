package com.tado.android.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.gms.common.AccountPicker;
import com.tado.C0676R;
import com.tado.android.alert_dialogs.CustomDialog;
import com.tado.android.alert_dialogs.CustomDialog.CustomDialogButton;
import com.tado.android.alert_dialogs.CustomDialog.CustomDialogText;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.BetaAccessRequest;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.rest.service.TadoApiService;
import com.tado.android.utils.TextValidator;
import com.tado.android.utils.Util;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Response;

public class BetaAccessActivity extends AppCompatActivity {
    CustomDialog dialog;
    @BindView(2131296361)
    EditText mEmail;
    @BindView(2131296363)
    EditText mFirstName;
    @BindView(2131296365)
    EditText mLastName;
    @BindView(2131296366)
    Button mSendRequestButton;

    class C10771 implements OnFocusChangeListener {
        C10771() {
        }

        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                BetaAccessActivity.this.onEmailClick(v);
            }
        }
    }

    class C10815 extends TadoCallback<Void> {
        C10815() {
        }

        public void onResponse(Call<Void> call, Response<Void> response) {
            super.onResponse(call, response);
            BetaAccessActivity.this.mSendRequestButton.setEnabled(true);
            if (response.isSuccessful()) {
                BetaAccessActivity.this.showDialog();
            }
        }
    }

    class C10826 implements OnClickListener {
        C10826() {
        }

        public void onClick(View v) {
            BetaAccessActivity.this.dialog.dismiss();
            BetaAccessActivity.this.onBackPressed();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_beta_access);
        ButterKnife.bind((Activity) this);
        this.mEmail.setOnFocusChangeListener(new C10771());
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle((int) C0676R.string.betaProgram_title);
        initValidators();
    }

    protected void onPause() {
        super.onPause();
        Util.hideKeyboard(this, this.mEmail);
    }

    private void initValidators() {
        this.mFirstName.addTextChangedListener(new TextValidator(this.mFirstName) {
            public void validate(TextView textView, String text) {
                BetaAccessActivity.this.validateEmptyTextView(textView, C0676R.string.betaProgram_errors_emptyFirstNameError);
            }
        });
        this.mLastName.addTextChangedListener(new TextValidator(this.mLastName) {
            public void validate(TextView textView, String text) {
                BetaAccessActivity.this.validateEmptyTextView(textView, C0676R.string.betaProgram_errors_emptyLastNameError);
            }
        });
        this.mEmail.addTextChangedListener(new TextValidator(this.mEmail) {
            public void validate(TextView textView, String text) {
                if (BetaAccessActivity.this.validateEmptyTextView(textView, C0676R.string.betaProgram_errors_emptyEmailError) && !Util.isValidEmail(text)) {
                    textView.setError(BetaAccessActivity.this.getString(C0676R.string.betaProgram_errors_invalidEmailError));
                }
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean validateEmptyTextView(TextView textView, int errorMessage) {
        if (textView.getText().toString().isEmpty()) {
            textView.setError(getString(errorMessage));
            return false;
        }
        textView.setError(null);
        return true;
    }

    public void onSendBetaRequestClick(View view) {
        boolean valid;
        if (validateEmptyTextView(this.mFirstName, C0676R.string.betaProgram_errors_emptyFirstNameError) && validateEmptyTextView(this.mLastName, C0676R.string.betaProgram_errors_emptyLastNameError) && validateEmptyTextView(this.mEmail, C0676R.string.betaProgram_errors_emptyEmailError) && Util.isValidEmail(this.mEmail.getText().toString())) {
            valid = true;
        } else {
            valid = false;
        }
        if (valid) {
            this.mSendRequestButton.setEnabled(false);
            TadoApiService tadoApiService = RestServiceGenerator.getTadoRestService();
            BetaAccessRequest betaAccessRequest = new BetaAccessRequest();
            betaAccessRequest.setEmail(this.mEmail.getText().toString());
            betaAccessRequest.setFirstName(this.mFirstName.getText().toString());
            betaAccessRequest.setLastName(this.mLastName.getText().toString());
            tadoApiService.betaAccessRequest(betaAccessRequest, RestServiceGenerator.getCredentialsMap()).enqueue(new C10815());
        } else if (!Util.isValidEmail(this.mEmail.getText().toString())) {
            this.mEmail.setError(getString(C0676R.string.betaProgram_errors_invalidEmailError));
        }
    }

    private void showDialog() {
        this.dialog = new CustomDialog(this, CustomDialogText.CUSTOM_DIALOG_ONE_TEXT_PARAGRAPH, CustomDialogButton.CUSTOM_DIALOG_ONE_BUTTON);
        this.dialog.setTitle(getString(C0676R.string.betaProgram_acknowledgement_title));
        this.dialog.setBodyText1(getString(C0676R.string.betaProgram_acknowledgement_message));
        this.dialog.setButton1Text(getString(C0676R.string.betaProgram_acknowledgement_confirmButton));
        this.dialog.setButton1Listener(new C10826());
        this.dialog.setCancelButtonVisible(false);
        this.dialog.show();
    }

    public void onEmailClick(View view) {
        ArrayList arrayList = null;
        startActivityForResult(AccountPicker.newChooseAccountIntent(null, arrayList, new String[]{"com.google"}, false, getString(C0676R.string.betaProgram_emailField), null, null, null), 19);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 19 && resultCode == -1) {
            String accountName = data.getStringExtra("authAccount");
            if (accountName != null) {
                this.mEmail.setText(accountName);
            }
        }
    }
}
