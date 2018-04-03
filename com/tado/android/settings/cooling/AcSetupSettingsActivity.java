package com.tado.android.settings.cooling;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.tado.C0676R;
import com.tado.android.alert_dialogs.CustomDialog;
import com.tado.android.alert_dialogs.CustomDialog.CustomDialogButton;
import com.tado.android.alert_dialogs.CustomDialog.CustomDialogText;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.rest.callback.RetryCallback;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.callback.presenters.GeneralErrorAlertPresenter;
import com.tado.android.rest.callback.presenters.GeneralErrorSnackbarPresenter;
import com.tado.android.rest.model.AcDriver;
import com.tado.android.rest.model.AcDriverTypeEnum;
import com.tado.android.rest.model.AirConditioningControl;
import com.tado.android.rest.model.CommandConfiguration;
import com.tado.android.rest.model.DriverSelection;
import com.tado.android.rest.model.Hysteresis;
import com.tado.android.rest.model.installation.ModeEnum;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.Constants;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class AcSetupSettingsActivity extends AppCompatActivity implements AcConfigurationUpdatePollingHelperInterface {
    private static final String AC_SETTING = "AC_SETTING";
    private static final int HYSTERESIS_CODE = 432;
    public static final String KEY_AC_DRIVER = "acDriver";
    public static final String KEY_CONFIGURATION = "configuration";
    public static final String KEY_DRIVER_DISC = "driverDisc";
    public static final String KEY_HYSTERESIS = "hysteresis";
    public static final String KEY_MIN_ON_OFF_TIME = "range";
    public static final String KEY_SELECTED_ZONE_ID = "SELECTED_ZONE_ID";
    public static final String KEY_SHOW_RESET_AC_BUTTON = "SHOW_RESET_AC_BUTTON";
    private static final int MIN_ON_OFF_CODE = 58;
    private List<AcDriver> acDriverList;
    @BindView(2131297081)
    View clcLayoutSeparator;
    @BindView(2131297082)
    View complexDriverLayout;
    @BindView(2131297083)
    View complexDriverTitle;
    private CommandConfiguration configuration;
    private AcConfigurationUpdatePollingHelper configurationUpdatePollingHelper;
    private AcDriver currentAcDriver;
    private CustomDialog dialog;
    private int driverDisc;
    private Hysteresis hysteresis;
    @BindView(2131297091)
    View hysteresisLayout;
    @BindView(2131297084)
    TextView info;
    private String mAcCommandSetType;
    @BindView(2131296279)
    TextView mCommandSetName;
    private boolean mDedicatedOnCommand;
    @BindView(2131296275)
    TextView mFifthMode;
    @BindView(2131296276)
    TextView mFirstMode;
    @BindView(2131296277)
    TextView mFourthMode;
    @BindView(2131296278)
    View mRootView;
    @BindView(2131296280)
    TextView mSecondMode;
    @BindView(2131296281)
    TextView mTemperatureUnit;
    @BindView(2131296282)
    TextView mThirdMode;
    private int mZoneCode;
    private int mZoneId;
    @BindView(2131297085)
    View minOnOffLayout;
    private Integer minOnOffTimeInSeconds;
    @BindView(2131297086)
    TextView minOnOffTimeSummary;
    @BindView(2131297092)
    TextView rangeSummary;
    @BindView(2131297088)
    Button reteachButton;
    private boolean showResetAcButton;
    @BindView(2131297089)
    Button switchDriverButton;
    @BindView(2131297090)
    TextView switchDriverInfoText;
    @BindView(2131297094)
    TextView title;

    class C10921 extends TadoCallback<List<AcDriver>> {
        C10921() {
        }

        public void onResponse(Call<List<AcDriver>> call, Response<List<AcDriver>> response) {
            super.onResponse(call, response);
            if (response.isSuccessful()) {
                AcSetupSettingsActivity.this.acDriverList = (List) response.body();
                if (AcDriverTypeEnum.CLOSED_LOOP_CONTROL == AcSetupSettingsActivity.this.currentAcDriver.getType()) {
                    for (AcDriver driver : AcSetupSettingsActivity.this.acDriverList) {
                        if (AcDriverTypeEnum.COMPLEX == driver.getType()) {
                            AcSetupSettingsActivity.this.switchDriverButton.setVisibility(0);
                            return;
                        }
                    }
                } else if (AcSetupSettingsActivity.this.showResetAcButton) {
                    AcSetupSettingsActivity.this.switchDriverButton.setVisibility(0);
                } else {
                    for (AcDriver driver2 : AcSetupSettingsActivity.this.acDriverList) {
                        if (AcDriverTypeEnum.CLOSED_LOOP_CONTROL == driver2.getType()) {
                            AcSetupSettingsActivity.this.switchDriverButton.setVisibility(0);
                            return;
                        }
                    }
                }
            }
        }
    }

    class C10965 implements OnClickListener {
        C10965() {
        }

        public void onClick(DialogInterface dialog, int which) {
            AcSetupSettingsActivity.this.switchToClcDriver();
            dialog.dismiss();
        }
    }

    class C10976 implements OnClickListener {
        C10976() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            AcSetupSettingsActivity.this.switchDriverButton.setEnabled(true);
        }
    }

    class C10998 implements OnClickListener {
        C10998() {
        }

        public void onClick(DialogInterface dialog, int which) {
            AcSetupSettingsActivity.this.switchDriverButton.setEnabled(true);
            dialog.dismiss();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnalyticsHelper.trackPageView(getApplication(), Screen.COOLING_SETTINGS);
        setContentView((int) C0676R.layout.activity_ac_setup_settings);
        ButterKnife.bind((Activity) this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle((int) C0676R.string.settings_zoneSettings_airConditioning_title);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mZoneId = extras.getInt(KEY_SELECTED_ZONE_ID);
            this.showResetAcButton = extras.getBoolean(KEY_SHOW_RESET_AC_BUTTON);
            this.currentAcDriver = (AcDriver) extras.getSerializable(KEY_AC_DRIVER);
            this.configuration = (CommandConfiguration) extras.getSerializable(KEY_CONFIGURATION);
            init(this.configuration, this.currentAcDriver);
        }
    }

    private void init(CommandConfiguration configuration, AcDriver currentAcDriver) {
        this.mDedicatedOnCommand = configuration.isDedicatedOnCommand();
        this.mZoneCode = configuration.getAcCommandSet().getCode();
        this.mAcCommandSetType = configuration.getAcCommandSet().getCommandType();
        this.driverDisc = currentAcDriver.getDiscriminator();
        this.minOnOffTimeInSeconds = currentAcDriver.getMinOnOffTimeInSeconds();
        this.hysteresis = currentAcDriver.getHysteresis();
        initViews(currentAcDriver);
    }

    protected void onResume() {
        super.onResume();
        callGetZoneControl();
        callGetDrivers();
    }

    protected void onPause() {
        super.onPause();
        if (this.configurationUpdatePollingHelper != null) {
            this.configurationUpdatePollingHelper.cleanup();
            this.configurationUpdatePollingHelper = null;
        }
    }

    private void callGetDrivers() {
        RestServiceGenerator.getTadoRestService().getAcDrivers(UserConfig.getHomeId(), this.mZoneId, RestServiceGenerator.getCredentialsMap()).enqueue(new C10921());
    }

    private void callGetZoneControl() {
        RestServiceGenerator.getTadoRestService().getZoneControl(UserConfig.getHomeId(), this.mZoneId, RestServiceGenerator.getCredentialsMap()).enqueue(new RetryCallback<AirConditioningControl>(new GeneralErrorSnackbarPresenter(this.mRootView)) {
            public void onResponse(Call<AirConditioningControl> call, Response<AirConditioningControl> response) {
                if (response.isSuccessful() && AcSetupSettingsActivity.this.currentAcDriver.getDiscriminator() != ((AirConditioningControl) response.body()).getDriver().getDiscriminator()) {
                    AcSetupSettingsActivity.this.currentAcDriver = ((AirConditioningControl) response.body()).getDriver();
                    AcSetupSettingsActivity.this.init(((AirConditioningControl) response.body()).getCommandConfiguration(), AcSetupSettingsActivity.this.currentAcDriver);
                }
                super.onResponse(call, response);
            }
        });
    }

    private void initViews(AcDriver driver) {
        boolean isClosedLoopControlDriver;
        int i;
        int i2 = 8;
        if (driver.getType() == AcDriverTypeEnum.CLOSED_LOOP_CONTROL) {
            isClosedLoopControlDriver = true;
        } else {
            isClosedLoopControlDriver = false;
        }
        this.title.setText(Util.getText(this, isClosedLoopControlDriver ? C0676R.string.settings_zoneSettings_airConditioning_thermostaticControlLabel : C0676R.string.settings_zoneSettings_airConditioning_nonthermostaticControlLabel, new Object[0]));
        this.info.setText(isClosedLoopControlDriver ? C0676R.string.settings_zoneSettings_airConditioning_thermostaticControlInfoLabel : C0676R.string.settings_zoneSettings_airConditioning_nonthermostaticControlInfoLabel);
        this.info.setMovementMethod(LinkMovementMethod.getInstance());
        this.switchDriverInfoText.setText(Util.getText(this, isClosedLoopControlDriver ? C0676R.string.settings_zoneSettings_airConditioning_driverConfigInfoLabel : C0676R.string.settings_zoneSettings_airConditioning_commandSet_descriptionLabel, new Object[0]));
        this.switchDriverInfoText.setMovementMethod(LinkMovementMethod.getInstance());
        this.mCommandSetName.setText(driver.getCommandSet().getName());
        this.switchDriverButton.setText(this.currentAcDriver.getType() == AcDriverTypeEnum.CLOSED_LOOP_CONTROL ? C0676R.string.settings_zoneSettings_airConditioning_switchToNonthermostaticControlButton : C0676R.string.settings_zoneSettings_airConditioning_switchToThermostaticControlButton);
        this.mTemperatureUnit.setText(getTemperatureUnit(driver.getCommandSet().getTemperatureUnit().toString()));
        TextView textView = this.mFirstMode;
        if (driver.getCommandSet().getSupportedModes().contains(ModeEnum.COOL)) {
            i = 0;
        } else {
            i = 8;
        }
        textView.setVisibility(i);
        textView = this.mSecondMode;
        if (driver.getCommandSet().getSupportedModes().contains(ModeEnum.FAN)) {
            i = 0;
        } else {
            i = 8;
        }
        textView.setVisibility(i);
        textView = this.mThirdMode;
        if (driver.getCommandSet().getSupportedModes().contains(ModeEnum.HEAT)) {
            i = 0;
        } else {
            i = 8;
        }
        textView.setVisibility(i);
        textView = this.mFourthMode;
        if (driver.getCommandSet().getSupportedModes().contains(ModeEnum.AUTO)) {
            i = 0;
        } else {
            i = 8;
        }
        textView.setVisibility(i);
        textView = this.mFifthMode;
        if (driver.getCommandSet().getSupportedModes().contains(ModeEnum.DRY)) {
            i = 0;
        } else {
            i = 8;
        }
        textView.setVisibility(i);
        if (isClosedLoopControlDriver) {
            this.rangeSummary.setText(getRangeText());
            this.minOnOffTimeSummary.setText(getMinOnOffTimeText());
        }
        Button button = this.reteachButton;
        if (isClosedLoopControlDriver && this.showResetAcButton) {
            i = 0;
        } else {
            i = 8;
        }
        button.setVisibility(i);
        View view = this.hysteresisLayout;
        if (isClosedLoopControlDriver) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
        view = this.minOnOffLayout;
        if (isClosedLoopControlDriver) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
        view = this.clcLayoutSeparator;
        if (isClosedLoopControlDriver) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
        view = this.complexDriverLayout;
        if (isClosedLoopControlDriver) {
            i = 8;
        } else {
            i = 0;
        }
        view.setVisibility(i);
        View view2 = this.complexDriverTitle;
        if (!isClosedLoopControlDriver) {
            i2 = 0;
        }
        view2.setVisibility(i2);
        this.switchDriverButton.setEnabled(true);
    }

    private CharSequence getMinOnOffTimeText() {
        if (this.minOnOffTimeInSeconds == null) {
            return getString(C0676R.string.settings_zoneSettings_airConditioning_minimumOnOffTimeDisabledLabel);
        }
        return Util.getText(this, C0676R.string.settings_zoneSettings_airConditioning_minimumOnOffTimeEnabledLabel, Integer.valueOf(this.minOnOffTimeInSeconds.intValue() / 60));
    }

    @NonNull
    private String getTemperatureUnit(String temperatureUnit) {
        return (Constants.FAHRENHEIT.equalsIgnoreCase(temperatureUnit) ? "F" : "C") + getString(C0676R.string.degree_symbol);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onAcCommandSetClick(View view) {
        if (AC_SETTING.equalsIgnoreCase(this.mAcCommandSetType)) {
            Intent intent = new Intent(this, CommandSetSignalSetActivity.class);
            intent.putExtra(KEY_SELECTED_ZONE_ID, this.mZoneId);
            intent.putExtra(CommandSetSignalSetActivity.KEY_DEDICATED_ON_COMMAND, this.mDedicatedOnCommand);
            intent.putExtra(CommandSetSignalSetActivity.KEY_ZONE_CODE, this.mZoneCode);
            startActivity(intent);
        }
    }

    @NonNull
    private View.OnClickListener getResetACSetupListener(final CustomDialog dialog) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                InstallationProcessController.getInstallationProcessController().restartAcSetup(AcSetupSettingsActivity.this, Long.valueOf((long) AcSetupSettingsActivity.this.mZoneId));
                dialog.dismiss();
            }
        };
    }

    @OnClick({2131297088})
    public void onReteachClick(View view) {
        final CustomDialog dialog = new CustomDialog(this, CustomDialogText.CUSTOM_DIALOG_ONE_TEXT_PARAGRAPH, CustomDialogButton.CUSTOM_DIALOG_ONE_BUTTON);
        dialog.setTitle(getString(C0676R.string.settings_zoneSettings_airConditioning_reset_confirm_title));
        dialog.setBodyText1(getString(C0676R.string.settings_zoneSettings_airConditioning_reset_confirm_descriptionLabel));
        dialog.setButton1Text(getString(C0676R.string.settings_zoneSettings_airConditioning_reset_confirm_confirmButtonLabel));
        dialog.setButton1Listener(getResetACSetupListener(dialog));
        dialog.setCancelButtonListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @OnClick({2131297089})
    public void onSwitchDriverClick(final View view) {
        CustomDialog dialog = new CustomDialog(this);
        this.switchDriverButton.setEnabled(false);
        if (AcDriverTypeEnum.COMPLEX == this.currentAcDriver.getType()) {
            AlertDialogs.showTwoButtonAlertDialog(this, C0676R.string.settings_zoneSettings_airConditioning_switchToThermostaticControlConfirmation_message, C0676R.string.settings_zoneSettings_airConditioning_switchToThermostaticControlConfirmation_confirmButton, new C10965(), C0676R.string.settings_zoneSettings_airConditioning_switchToThermostaticControlConfirmation_cancelButton, new C10976());
        } else if (AcDriverTypeEnum.CLOSED_LOOP_CONTROL == this.currentAcDriver.getType()) {
            AlertDialogs.showTwoButtonAlertDialog(this, C0676R.string.settings_zoneSettings_airConditioning_switchToNonthermostaticControlConfirmation_message, C0676R.string.settings_zoneSettings_airConditioning_switchToNonthermostaticControlConfirmation_confirmButton, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    AcSetupSettingsActivity.this.onAcCommandSetClick(view);
                    dialog.dismiss();
                }
            }, C0676R.string.settings_zoneSettings_airConditioning_switchToNonthermostaticControlConfirmation_cancelButton, new C10998());
        }
    }

    @OnClick({2131297085})
    public void onOnOffTimeClick(View view) {
        startActivityForResult(getIntentWithExtras(AcMinOnOffTimeActivity.class), 58);
    }

    @OnClick({2131297091})
    public void onTemperatureClick(View view) {
        startActivityForResult(getIntentWithExtras(AcTemperatureRangeActivity.class), HYSTERESIS_CODE);
    }

    private Intent getIntentWithExtras(Class clazz) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(KEY_SELECTED_ZONE_ID, this.mZoneId);
        intent.putExtra(KEY_DRIVER_DISC, this.driverDisc);
        intent.putExtra(KEY_MIN_ON_OFF_TIME, this.minOnOffTimeInSeconds);
        intent.putExtra(KEY_HYSTERESIS, this.hysteresis);
        return intent;
    }

    private void switchToClcDriver() {
        for (AcDriver driver : this.acDriverList) {
            if (AcDriverTypeEnum.CLOSED_LOOP_CONTROL == driver.getType()) {
                DriverSelection driverSelection = new DriverSelection();
                driverSelection.setDiscriminator(driver.getDiscriminator());
                RestServiceGenerator.getTadoRestService().updateAcDriver(UserConfig.getHomeId(), this.mZoneId, driverSelection, RestServiceGenerator.getCredentialsMap()).enqueue(new RetryCallback<Void>(new GeneralErrorAlertPresenter(this)) {
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        super.onResponse(call, response);
                        if (response.isSuccessful()) {
                            AcSetupSettingsActivity.this.configurationUpdatePollingHelper = new AcConfigurationUpdatePollingHelper(AcSetupSettingsActivity.this.mZoneId, AcSetupSettingsActivity.this);
                            AcSetupSettingsActivity.this.configurationUpdatePollingHelper.callDeviceCheckStatus();
                        }
                    }
                });
                return;
            }
        }
        InstallationProcessController.getInstallationProcessController().restartAcSetup(this, Long.valueOf((long) this.mZoneId));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != -1) {
            return;
        }
        if (requestCode == HYSTERESIS_CODE) {
            this.hysteresis = (Hysteresis) data.getSerializableExtra(KEY_HYSTERESIS);
            this.rangeSummary.setText(getRangeText());
        } else if (requestCode == 58) {
            this.minOnOffTimeInSeconds = (Integer) data.getSerializableExtra(KEY_MIN_ON_OFF_TIME);
            this.minOnOffTimeSummary.setText(getMinOnOffTimeText());
        }
    }

    private CharSequence getRangeText() {
        Object[] objArr = new Object[1];
        objArr[0] = String.format("%.1f", new Object[]{Float.valueOf(this.hysteresis.getValue())});
        return Util.getText(this, C0676R.string.settings_zoneSettings_airConditioning_hysteresisValueLabel, objArr);
    }

    public void onSuccess() {
        callGetZoneControl();
        if (this.dialog != null) {
            this.dialog.dismiss();
            this.dialog = null;
        }
    }

    public void onFailed() {
        if (this.dialog != null) {
            this.dialog.resetLayout();
            this.dialog.setTextEnum(CustomDialogText.CUSTOM_DIALOG_ONE_TEXT_PARAGRAPH);
            this.dialog.setButtonEnum(CustomDialogButton.CUSTOM_DIALOG_ONE_BUTTON);
            this.dialog.setCancelButtonVisible(false);
            this.dialog.setTitle(getString(C0676R.string.app_name));
            this.dialog.setBodyText1(getString(C0676R.string.settings_zoneSettings_airConditioning_errors_deviceUpdateFailed_message));
            this.dialog.setButton1Text(getString(C0676R.string.settings_zoneSettings_airConditioning_errors_deviceUpdateFailed_retryButton));
            this.dialog.setButton1Listener(new View.OnClickListener() {
                public void onClick(View v) {
                    AcSetupSettingsActivity.this.switchToClcDriver();
                    AcSetupSettingsActivity.this.dialog.showProgressBar(AcSetupSettingsActivity.this.getString(C0676R.string.settings_zoneSettings_airConditioning_commandSet_updating_title));
                }
            });
        }
    }

    public void onInProgress() {
        this.dialog = new CustomDialog(this);
        this.dialog.setCancelable(false);
        this.dialog.showProgressBar(getString(C0676R.string.settings_zoneSettings_airConditioning_commandSet_updating_title));
        this.dialog.show();
    }

    public void onBackPressed() {
        if (this.configurationUpdatePollingHelper == null || !(this.configurationUpdatePollingHelper == null || this.configurationUpdatePollingHelper.isUpdateInProgress())) {
            super.onBackPressed();
        }
    }
}
