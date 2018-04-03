package com.tado.android.installation.positioning;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tado.C0676R;
import com.tado.android.client.APICall;
import com.tado.android.client.APICallListener;
import com.tado.android.controllers.ZoneController;
import com.tado.android.entities.EmptyResponse;
import com.tado.android.entities.ServerError;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.CongratulationsActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.requests.PostStartBeepingRequest;
import com.tado.android.requests.Request;
import com.tado.android.requests.StopBeepingRequest;
import com.tado.android.responses.GetEmptyResponse;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.installation.AcInstallation;
import com.tado.android.rest.model.installation.DevicePositionInput.DevicePositioningEnum;
import com.tado.android.rest.model.installation.InstallationStateTransitionResult;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;
import retrofit2.Call;
import retrofit2.Response;

public class DevicePositionConnectionActivity extends ACInstallationBaseActivity implements APICallListener {
    private static final int SENDING_TIMEOUT = 30000;
    private DevicePositioningEnum devicePosition;
    private CountDownTimer mSendingTimer;
    @BindView(2131296991)
    Button mStartSending;
    private String serialNo;

    class C09492 extends TadoCallback<InstallationStateTransitionResult> {
        C09492() {
        }

        public void onResponse(Call<InstallationStateTransitionResult> call, Response<InstallationStateTransitionResult> response) {
            super.onResponse(call, response);
            DevicePositionConnectionActivity.this.dismissLoadingUI();
            if (response.isSuccessful()) {
                AcInstallation installation = ((InstallationStateTransitionResult) response.body()).getInstallation();
                if (installation != null) {
                    InstallationProcessController.getInstallationProcessController().goToScreenForInstallationProcessStatus(DevicePositionConnectionActivity.this, installation);
                    return;
                } else {
                    InstallationProcessController.getInstallationProcessController().detectStatus(DevicePositionConnectionActivity.this);
                    return;
                }
            }
            DevicePositionConnectionActivity.this.handleServerError(this.serverError, DevicePositionConnectionActivity.this, call, response.code(), this);
        }

        public void onFailure(Call<InstallationStateTransitionResult> call, Throwable t) {
            super.onFailure(call, t);
            DevicePositionConnectionActivity.this.dismissLoadingUI();
            InstallationProcessController.showConnectionErrorRetrofit(DevicePositionConnectionActivity.this, call, this);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_device_position_connection);
        ButterKnife.bind((Activity) this);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_positionDevice_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_positionDevice_findPosition_title);
        this.textView.setText(C0676R.string.installation_sacc_positionDevice_findPosition_message);
        this.centerImage.setImageResource(C0676R.drawable.make_sure_ac);
        this.proceedButton.setText(C0676R.string.installation_sacc_positionDevice_findPosition_confirmButton);
        this.serialNo = getIntent().getStringExtra("serialNo");
    }

    protected void onResume() {
        super.onResume();
        this.proceedButton.setVisibility(4);
        this.mStartSending.setText(getString(C0676R.string.installation_sacc_positionDevice_findPosition_sendSignalButton));
        this.mStartSending.setEnabled(true);
    }

    public void onBackPressed() {
        stopSending();
        super.onBackPressed();
    }

    public void proceedClick(View view) {
        stopSending();
        if (this.mSendingTimer != null) {
            this.mSendingTimer.cancel();
        }
        confirmDevicePosition();
    }

    private void stopSending() {
        APICall ac = new APICall(new StopBeepingRequest(this.serialNo != null ? this.serialNo : UserConfig.getSerialNo()), this);
        ac.setAPICallListener(this);
        ac.setmShowLoaderDialog(false);
        ac.execute(new Void[0]);
    }

    public void startSending(View view) {
        this.proceedButton.setVisibility(0);
        this.mStartSending.setText(C0676R.string.installation_sacc_positionDevice_findPosition_sendingSignalLabel);
        this.mStartSending.setEnabled(false);
        startTimer();
        APICall ac = new APICall(new PostStartBeepingRequest(this.serialNo != null ? this.serialNo : UserConfig.getSerialNo()), this);
        ac.setAPICallListener(this);
        ac.setmShowLoaderDialog(true);
        ac.execute(new Void[0]);
    }

    public void onProcessResponse(APICall call, com.tado.android.responses.Response response) {
        Snitcher.start().log(2, DevicePositionConnectionActivity.class.getSimpleName(), "SUCC", new Object[0]);
    }

    public void onCallFailed(APICall call, com.tado.android.responses.Response response) {
        resetSendingButton();
        if (this.mSendingTimer != null) {
            this.mSendingTimer.cancel();
        }
        Request request = call.getRequest();
        if (response instanceof GetEmptyResponse) {
            EmptyResponse emptyResponse = ((GetEmptyResponse) response).getEmptyResponse();
            if (emptyResponse != null) {
                ServerError[] errors = emptyResponse.getErrors();
                if (errors != null) {
                    InstallationProcessController.handleError((Activity) this, errors[0], request, response);
                    return;
                }
            }
            InstallationProcessController.showConnectionError(this, request, this);
            return;
        }
        InstallationProcessController.showConnectionError(this, request, this);
    }

    private void resetSendingButton() {
        this.mStartSending.setText(getString(C0676R.string.installation_sacc_positionDevice_findPosition_resendSignalsButton));
        this.mStartSending.setEnabled(true);
    }

    private void startTimer() {
        if (this.mSendingTimer == null) {
            this.mSendingTimer = new CountDownTimer(30000, 30000) {
                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    DevicePositionConnectionActivity.this.stopSending();
                    DevicePositionConnectionActivity.this.resetSendingButton();
                }
            };
        }
        this.mSendingTimer.start();
    }

    private void confirmDevicePosition() {
        showLoadingUI();
        RestServiceGenerator.getTadoInstallationRestService().confirmDevicePosition(UserConfig.getHomeId(), Integer.valueOf(InstallationProcessController.getInstallationProcessController().getInstallationId()), new Object()).enqueue(new C09492());
    }

    private void goToCongratutlationsScreen() {
        ZoneController.INSTANCE.callGetZoneList();
        InstallationProcessController.startActivity((Activity) this, CongratulationsActivity.class, true);
    }
}
