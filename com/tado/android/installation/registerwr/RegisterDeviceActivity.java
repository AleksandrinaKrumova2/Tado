package com.tado.android.installation.registerwr;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.BindView;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.installation.AcInstallation;
import com.tado.android.rest.model.installation.AcInstallationInput;
import com.tado.android.rest.model.installation.AcInstallationInput.TypeEnum;
import com.tado.android.rest.model.installation.AcInstallationInputWirelessRemote;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.Constants;
import com.tado.android.utils.UserConfig;
import retrofit2.Call;
import retrofit2.Response;

public class RegisterDeviceActivity extends ACInstallationBaseActivity {
    @BindView(2131296910)
    EditText editAuthCode;
    @BindView(2131296911)
    EditText editSerialNo;
    @BindView(2131296405)
    ImageView imageView;

    class C09501 extends TadoCallback<AcInstallation> {
        C09501() {
        }

        public void onResponse(Call<AcInstallation> call, Response<AcInstallation> response) {
            super.onResponse(call, response);
            RegisterDeviceActivity.this.dismissLoadingUI();
            if (response.isSuccessful()) {
                AcInstallation installationStatus = (AcInstallation) response.body();
                if (installationStatus != null) {
                    InstallationProcessController.getInstallationProcessController().goToScreenForInstallationProcessStatus(RegisterDeviceActivity.this, installationStatus);
                } else {
                    InstallationProcessController.getInstallationProcessController().detectStatus(RegisterDeviceActivity.this);
                }
            } else if (!this.handled) {
                if (this.serverError != null) {
                    String code = this.serverError.getCode();
                    Object obj = -1;
                    switch (code.hashCode()) {
                        case -1605575619:
                            if (code.equals(Constants.SERVER_ERROR_WRONG_DEVICE_TYPE)) {
                                obj = 3;
                                break;
                            }
                            break;
                        case -1101953289:
                            if (code.equals(Constants.SERVER_ERROR_AUTH_CODE_INVALID)) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 402034827:
                            if (code.equals(Constants.SERVER_ERROR_ALREADY_REGISTERED)) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 1553320047:
                            if (code.equals(Constants.SERVER_ERROR_NOT_FOUND)) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                        case 1:
                            RegisterDeviceActivity.this.editAuthCode.setError(RegisterDeviceActivity.this.getString(C0676R.string.installation_sacc_registerDevice_registration_errors_wrongAuthCodeError));
                            return;
                        case 2:
                            RegisterDeviceActivity.this.editSerialNo.setError(RegisterDeviceActivity.this.getString(C0676R.string.installation_sacc_registerDevice_registration_errors_deviceAlreadyRegisteredError));
                            return;
                        case 3:
                            RegisterDeviceActivity.this.editSerialNo.setError(RegisterDeviceActivity.this.getString(C0676R.string.installation_sacc_registerDevice_registration_errors_deviceNotSACCError));
                            break;
                    }
                    InstallationProcessController.handleError(RegisterDeviceActivity.this, this.serverError, (Call) call, response.code());
                    return;
                }
                InstallationProcessController.showConnectionErrorRetrofit(RegisterDeviceActivity.this, call, this);
            }
        }

        public void onFailure(Call<AcInstallation> call, Throwable t) {
            super.onFailure(call, t);
            RegisterDeviceActivity.this.dismissLoadingUI();
            InstallationProcessController.showConnectionErrorRetrofit(RegisterDeviceActivity.this, call, this);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.fragment_register_device);
        this.titleBarTextview.setText(getString(C0676R.string.installation_sacc_registerDevice_registration_title));
        this.titleTemplateTextview.setText(getString(C0676R.string.installation_sacc_registerDevice_registration_message));
        this.proceedButton.setText(getString(C0676R.string.installation_sacc_registerDevice_registration_confirmButton));
        this.imageView.setImageResource(C0676R.drawable.register_device);
    }

    public void proceedClick(View view) {
        String serialNo = this.editSerialNo.getText().toString();
        String authCode = this.editAuthCode.getText().toString();
        boolean validInputs = true;
        if (serialNo.isEmpty()) {
            validInputs = false;
            this.editSerialNo.setError(getString(C0676R.string.installation_sacc_registerDevice_registration_errors_serialNoFieldEmptyError));
        }
        if (authCode.isEmpty()) {
            validInputs = false;
            this.editAuthCode.setError(getString(C0676R.string.installation_sacc_registerDevice_registration_errors_authCodeFieldEmptyError));
        }
        if (validInputs) {
            startInstallationRequest(serialNo, authCode);
        }
    }

    public void startInstallationRequest(String serialNo, String authCode) {
        AcInstallationInput input = new AcInstallationInput();
        AcInstallationInputWirelessRemote remote = new AcInstallationInputWirelessRemote();
        remote.setSerialNo(serialNo);
        remote.setAuthKey(authCode);
        input.setWirelessRemote(remote);
        input.setType(TypeEnum.G1);
        input.setRevision(Integer.valueOf(6));
        showLoadingUI();
        RestServiceGenerator.getTadoInstallationRestService().startInstallation(UserConfig.getHomeId(), input).enqueue(new C09501());
    }
}
