package com.tado.android.settings.cooling;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import com.tado.C0676R;
import com.tado.android.alert_dialogs.CustomDialog;
import com.tado.android.alert_dialogs.CustomDialog.CustomDialogButton;
import com.tado.android.alert_dialogs.CustomDialog.CustomDialogText;
import com.tado.android.rest.callback.RetryCallback;
import com.tado.android.rest.callback.presenters.GeneralErrorAlertPresenter;
import com.tado.android.rest.model.ClosedLoopControlConfig;
import com.tado.android.rest.model.Hysteresis;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.UserConfig;
import retrofit2.Call;
import retrofit2.Response;

public class AcConfigPreferenceFragment extends PreferenceFragment implements AcConfigurationUpdatePollingHelperInterface {
    protected AcConfigurationUpdatePollingHelper configurationHelper;
    protected CustomDialog dialog;
    protected int driverDisc;
    protected Hysteresis hysteresis;
    protected Integer minOnOffTimeInSeconds;
    protected int zoneId;

    class C10861 implements OnClickListener {
        C10861() {
        }

        public void onClick(View v) {
            AcConfigPreferenceFragment.this.updateConfiguration();
            AcConfigPreferenceFragment.this.dialog.showProgressBar(AcConfigPreferenceFragment.this.getString(C0676R.string.settings_zoneSettings_airConditioning_commandSet_updating_title));
        }
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null) {
            this.minOnOffTimeInSeconds = (Integer) bundle.get(AcSetupSettingsActivity.KEY_MIN_ON_OFF_TIME);
            this.hysteresis = (Hysteresis) bundle.getSerializable(AcSetupSettingsActivity.KEY_HYSTERESIS);
            this.zoneId = bundle.getInt(AcSetupSettingsActivity.KEY_SELECTED_ZONE_ID);
            this.driverDisc = bundle.getInt(AcSetupSettingsActivity.KEY_DRIVER_DISC);
        }
    }

    public void onPause() {
        if (this.configurationHelper != null) {
            this.configurationHelper.cleanup();
        }
        super.onPause();
    }

    protected static void setFragmentArguments(int zoneId, int driverDisc, Integer minOnOffTimeInSeconds, Hysteresis hysteresis, AcConfigPreferenceFragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(AcSetupSettingsActivity.KEY_MIN_ON_OFF_TIME, minOnOffTimeInSeconds);
        bundle.putSerializable(AcSetupSettingsActivity.KEY_HYSTERESIS, hysteresis);
        bundle.putInt(AcSetupSettingsActivity.KEY_SELECTED_ZONE_ID, zoneId);
        bundle.putInt(AcSetupSettingsActivity.KEY_DRIVER_DISC, driverDisc);
        fragment.setArguments(bundle);
    }

    public void save() {
        this.configurationHelper = new AcConfigurationUpdatePollingHelper(this.zoneId, this);
        updateConfiguration();
    }

    public void onSuccess() {
        if (isAdded()) {
            if (this.dialog != null) {
                this.dialog.dismiss();
            }
            Intent result = new Intent();
            result.putExtra(AcSetupSettingsActivity.KEY_HYSTERESIS, this.hysteresis);
            result.putExtra(AcSetupSettingsActivity.KEY_MIN_ON_OFF_TIME, this.minOnOffTimeInSeconds);
            getActivity().setResult(-1, result);
            getActivity().onBackPressed();
        }
    }

    public void onFailed() {
        if (isAdded() && this.dialog != null) {
            this.dialog.resetLayout();
            this.dialog.setTextEnum(CustomDialogText.CUSTOM_DIALOG_ONE_TEXT_PARAGRAPH);
            this.dialog.setButtonEnum(CustomDialogButton.CUSTOM_DIALOG_ONE_BUTTON);
            this.dialog.setCancelButtonVisible(false);
            this.dialog.setTitle(getString(C0676R.string.app_name));
            this.dialog.setBodyText1(getString(C0676R.string.settings_zoneSettings_airConditioning_errors_deviceUpdateFailed_message));
            this.dialog.setButton1Text(getString(C0676R.string.settings_zoneSettings_airConditioning_errors_deviceUpdateFailed_retryButton));
            this.dialog.setButton1Listener(new C10861());
        }
    }

    public void onInProgress() {
        if (isAdded()) {
            this.dialog = new CustomDialog(getActivity(), CustomDialogText.CUSTOM_DIALOG_ONE_TEXT_PARAGRAPH, CustomDialogButton.CUSTOM_DIALOG_ONE_BUTTON);
            this.dialog.setCancelable(false);
            this.dialog.showProgressBar(getString(C0676R.string.settings_zoneSettings_airConditioning_commandSet_updating_title));
            this.dialog.show();
        }
    }

    private void updateConfiguration() {
        ClosedLoopControlConfig config = new ClosedLoopControlConfig();
        config.setHysteresis(this.hysteresis);
        config.setMinOnOffTimeInSeconds(this.minOnOffTimeInSeconds);
        RestServiceGenerator.getTadoRestService().updateDriverConfig(UserConfig.getHomeId(), this.zoneId, this.driverDisc, config, RestServiceGenerator.getCredentialsMap()).enqueue(new RetryCallback<Void>(new GeneralErrorAlertPresenter(getActivity())) {
            public void onResponse(Call<Void> call, Response<Void> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    AcConfigPreferenceFragment.this.configurationHelper.callDeviceCheckStatus();
                }
            }
        });
    }

    public boolean shouldGoBack() {
        return this.configurationHelper == null || !(this.configurationHelper == null || this.configurationHelper.isUpdateInProgress());
    }
}
