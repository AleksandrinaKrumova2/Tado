package com.tado.android.installation.acsetup;

import android.app.Activity;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tado.C0676R;
import com.tado.android.control_panel.CoolingControlHelper;
import com.tado.android.controllers.CapabilitiesController;
import com.tado.android.dialogs.AlertChoiceDialogListener;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.entities.ACSetting;
import com.tado.android.entities.Temperatures;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.Temperature;
import com.tado.android.rest.model.installation.AcCommandSetId;
import com.tado.android.rest.model.installation.AcCommandSetTest;
import com.tado.android.rest.model.installation.CoolingCapabilities;
import com.tado.android.rest.model.installation.CoolingModeCapabilities;
import com.tado.android.rest.model.installation.CoolingZoneSetting;
import com.tado.android.rest.model.installation.FanSpeedEnum;
import com.tado.android.rest.model.installation.InstallationStateTransitionResult;
import com.tado.android.rest.model.installation.ModeEnum;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.times.view.model.CoolingPowerEnum;
import com.tado.android.times.view.model.CoolingSwingStateEnum;
import com.tado.android.utils.UserConfig;
import java.util.EnumMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class TestCommandSetActivity extends ACInstallationBaseActivity {
    private SparseArray<ModeEnum> buttonMap = new SparseArray();
    private CoolingCapabilities capabilities;
    private CoolingZoneSetting currentACSetting;
    private CoolingModeCapabilities currentAcModeCapability;
    private ModeEnum currentMode;
    TadoCallback<InstallationStateTransitionResult> listener = new C08519();
    @BindView(2131297050)
    TextView mCommandSetName;
    @BindView(2131296459)
    ImageView mControlPanelFanSpeed;
    @BindView(2131296463)
    ImageView mControlPanelFanSpeedIcon;
    @BindView(2131296465)
    TextView mControlPanelFanText;
    @BindView(2131296471)
    Button mControlPanelSwingButton;
    @BindView(2131296472)
    TextView mControlPanelTemperature;
    @BindView(2131296475)
    ImageView mControlPanelTemperatureIcon;
    @BindView(2131296464)
    RelativeLayout mFanLayout;
    @BindView(2131296460)
    ImageView mFanSpeedDown;
    @BindView(2131296466)
    ImageView mFanSpeedUp;
    @BindView(2131296444)
    FloatingActionButton mFifthButton;
    @BindView(2131296445)
    View mFifthButtonLayout;
    @BindView(2131296446)
    TextView mFifthButtonText;
    @BindView(2131296447)
    FloatingActionButton mFirstButton;
    @BindView(2131296448)
    View mFirstButtonLayout;
    @BindView(2131296449)
    TextView mFirstButtonText;
    @BindView(2131296451)
    FloatingActionButton mFourthButton;
    @BindView(2131296452)
    View mFourthButtonLayout;
    @BindView(2131296453)
    TextView mFourthButtonText;
    @BindView(2131296469)
    Switch mPowerSwitch;
    @BindView(2131296458)
    TextView mPowerText;
    @BindView(2131296919)
    View mRepeatExplanationView;
    @BindView(2131296918)
    View mRepeatView;
    @BindView(2131296495)
    FloatingActionButton mSecondButton;
    @BindView(2131296496)
    View mSecondButtonLayout;
    @BindView(2131296497)
    TextView mSecondButtonText;
    @BindView(2131296473)
    ImageView mTemperatureDown;
    @BindView(2131296476)
    RelativeLayout mTemperatureLayout;
    @BindView(2131296477)
    ImageView mTemperatureUp;
    @BindView(2131296499)
    FloatingActionButton mThirdButton;
    @BindView(2131296500)
    View mThirdButtonLayout;
    @BindView(2131296501)
    TextView mThirdButtonText;
    @BindView(2131296880)
    View progressBar;
    private int selectedState = -1;
    private EnumMap<ModeEnum, CoolingZoneSetting> stateMap;

    class C08431 implements OnCheckedChangeListener {
        C08431() {
        }

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            TestCommandSetActivity.this.currentACSetting.setPower(CoolingPowerEnum.getCoolingPowerString(isChecked));
            TestCommandSetActivity.this.initComponentAvailability(isChecked);
            TestCommandSetActivity.this.initButtonsLayoutSelectedState(TestCommandSetActivity.this.selectedState);
            TestCommandSetActivity.this.updateControlPanelForState(TestCommandSetActivity.this.currentACSetting);
        }
    }

    class C08442 implements OnClickListener {
        C08442() {
        }

        public void onClick(View v) {
            TestCommandSetActivity.this.buttonListener(1);
        }
    }

    class C08453 implements OnClickListener {
        C08453() {
        }

        public void onClick(View v) {
            TestCommandSetActivity.this.buttonListener(2);
        }
    }

    class C08464 implements OnClickListener {
        C08464() {
        }

        public void onClick(View v) {
            TestCommandSetActivity.this.buttonListener(3);
        }
    }

    class C08475 implements OnClickListener {
        C08475() {
        }

        public void onClick(View v) {
            TestCommandSetActivity.this.buttonListener(4);
        }
    }

    class C08486 implements OnClickListener {
        C08486() {
        }

        public void onClick(View v) {
            TestCommandSetActivity.this.buttonListener(5);
        }
    }

    class C08497 implements AlertChoiceDialogListener {
        C08497() {
        }

        public void OnOKClicked() {
            AcCommandSetId commandSetId = new AcCommandSetId();
            commandSetId.setCode(InstallationProcessController.getInstallationProcessController().getSelectedAcCommandSet().getCode());
            commandSetId.setCommandType(InstallationProcessController.getInstallationProcessController().getSelectedAcCommandSet().getCommandType());
            TestCommandSetActivity.this.selectCommandSet(commandSetId);
        }

        public void OnCancelClicked() {
        }
    }

    class C08508 extends TadoCallback<Void> {
        C08508() {
        }

        public void onResponse(Call<Void> call, Response<Void> response) {
            super.onResponse(call, response);
            TestCommandSetActivity.this.showWaitingLayout(false);
        }

        public void onFailure(Call<Void> call, Throwable t) {
            super.onFailure(call, t);
            TestCommandSetActivity.this.showWaitingLayout(false);
        }
    }

    class C08519 extends TadoCallback<InstallationStateTransitionResult> {
        C08519() {
        }

        public void onResponse(Call<InstallationStateTransitionResult> call, Response<InstallationStateTransitionResult> response) {
            super.onResponse(call, response);
            TestCommandSetActivity.this.showWaitingLayout(false);
            if (response.isSuccessful()) {
                InstallationProcessController.getInstallationProcessController().goToScreenForInstallationProcessStatus(TestCommandSetActivity.this, ((InstallationStateTransitionResult) response.body()).getInstallation());
                return;
            }
            TestCommandSetActivity.this.handleServerError(this.serverError, TestCommandSetActivity.this, call, response.code(), this);
        }

        public void onFailure(Call<InstallationStateTransitionResult> call, Throwable t) {
            super.onFailure(call, t);
            TestCommandSetActivity.this.showWaitingLayout(false);
            InstallationProcessController.showConnectionErrorRetrofit(TestCommandSetActivity.this, call, this);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_test_command_set);
        ButterKnife.bind((Activity) this);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_setupAC_selectCommandSet_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_setupAC_selectCommandSet_testingCommandSets_title);
        this.proceedButton.setText(C0676R.string.installation_sacc_setupAC_selectCommandSet_testingCommandSets_useCommandSetButton);
    }

    protected void onResume() {
        super.onResume();
        this.stateMap = new EnumMap(ModeEnum.class);
        updateLayout();
        initLayout();
    }

    private void updateLayout() {
        if (InstallationProcessController.getInstallationProcessController().getSelectedAcCommandSet() == null) {
            updateComponents(false);
            this.mCommandSetName.setText(C0676R.string.installation_sacc_setupAC_selectCommandSet_testingCommandSets_chooseCoomandSetButton);
            return;
        }
        updateComponents(true);
        this.mCommandSetName.setText(InstallationProcessController.getInstallationProcessController().getSelectedAcCommandSet().getName());
    }

    private void updateComponents(boolean enabled) {
        this.proceedButton.setEnabled(enabled);
        this.mRepeatView.setEnabled(enabled);
        this.mRepeatExplanationView.setEnabled(enabled);
        this.mPowerSwitch.setEnabled(enabled);
        this.mPowerText.setEnabled(enabled);
        initComponentAvailability(enabled);
    }

    private void initLayout() {
        if (InstallationProcessController.getInstallationProcessController().getSelectedAcCommandSet() == null) {
            initDefaultLayout();
            return;
        }
        this.buttonMap.clear();
        this.capabilities = InstallationProcessController.getInstallationProcessController().getSelectedAcCommandSet().getCapabilities();
        enableModes(this.capabilities);
        this.currentMode = getDefaultMode(this.capabilities);
        this.currentAcModeCapability = getCurrentAcModeCapability(this.currentMode);
        this.selectedState = CoolingControlHelper.getKeyFromValue(this.buttonMap, this.currentMode);
        this.currentACSetting = generateDefaultModeState(this.currentMode, this.currentAcModeCapability);
        setModesLayout();
        initControlPanelLayout(this.currentACSetting);
        initButtonsLayoutSelectedState(this.selectedState);
    }

    private CoolingModeCapabilities getCurrentAcModeCapability(ModeEnum currentMode) {
        if (currentMode == ModeEnum.COOL) {
            return this.capabilities.getCOOL();
        }
        if (currentMode == ModeEnum.FAN) {
            return this.capabilities.getFAN();
        }
        if (currentMode == ModeEnum.DRY) {
            return this.capabilities.getDRY();
        }
        if (currentMode == ModeEnum.AUTO) {
            return this.capabilities.getAUTO();
        }
        return this.capabilities.getHEAT();
    }

    private void initDefaultLayout() {
        this.buttonMap.put(1, ModeEnum.COOL);
        this.buttonMap.put(2, ModeEnum.FAN);
        this.buttonMap.put(3, ModeEnum.DRY);
        this.buttonMap.put(4, ModeEnum.AUTO);
        this.buttonMap.put(5, ModeEnum.HEAT);
        setModesLayout();
        setSwingLayoutEnabled(false);
        setFanLayoutEnabled(false);
        setTemperatureLayoutEnabled(false);
    }

    private CoolingZoneSetting generateDefaultModeState(ModeEnum mode, CoolingModeCapabilities modeCapability) {
        CoolingZoneSetting acSetting = new CoolingZoneSetting();
        acSetting.setMode(mode);
        acSetting.setPower(CoolingPowerEnum.ON.name());
        Temperatures temperatureCapability = modeCapability.getTemperatures();
        if (temperatureCapability != null) {
            acSetting.setTemperature(Temperature.fromValue(temperatureCapability.getMin().getTemperature()));
        }
        List<FanSpeedEnum> fanSpeeds = modeCapability.getFanSpeeds();
        if (fanSpeeds != null && fanSpeeds.size() > 0) {
            acSetting.setFanSpeed((FanSpeedEnum) CapabilitiesController.INSTANCE.sortFanSpeedValuesV2(fanSpeeds).get(0));
        }
        List<String> swings = modeCapability.getSwings();
        if (swings != null && swings.size() > 0) {
            acSetting.setSwing((String) swings.get(0));
        }
        return acSetting;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.tado.android.rest.model.installation.ModeEnum getDefaultMode(com.tado.android.rest.model.installation.CoolingCapabilities r7) {
        /*
        r6 = this;
        r2 = com.tado.android.utils.Constants.DEFAULT_MODE_ORDER_V2;
        r3 = r2.length;
        r1 = 0;
    L_0x0004:
        if (r1 >= r3) goto L_0x0043;
    L_0x0006:
        r0 = r2[r1];
        r4 = com.tado.android.installation.acsetup.TestCommandSetActivity.AnonymousClass10.$SwitchMap$com$tado$android$rest$model$installation$ModeEnum;
        r5 = r0.ordinal();
        r4 = r4[r5];
        switch(r4) {
            case 1: goto L_0x0016;
            case 2: goto L_0x001f;
            case 3: goto L_0x0028;
            case 4: goto L_0x0031;
            case 5: goto L_0x003a;
            default: goto L_0x0013;
        };
    L_0x0013:
        r1 = r1 + 1;
        goto L_0x0004;
    L_0x0016:
        r4 = r7.getCOOL();
        if (r4 == 0) goto L_0x001f;
    L_0x001c:
        r1 = com.tado.android.rest.model.installation.ModeEnum.COOL;
    L_0x001e:
        return r1;
    L_0x001f:
        r4 = r7.getFAN();
        if (r4 == 0) goto L_0x0028;
    L_0x0025:
        r1 = com.tado.android.rest.model.installation.ModeEnum.FAN;
        goto L_0x001e;
    L_0x0028:
        r4 = r7.getDRY();
        if (r4 == 0) goto L_0x0031;
    L_0x002e:
        r1 = com.tado.android.rest.model.installation.ModeEnum.DRY;
        goto L_0x001e;
    L_0x0031:
        r4 = r7.getAUTO();
        if (r4 == 0) goto L_0x003a;
    L_0x0037:
        r1 = com.tado.android.rest.model.installation.ModeEnum.AUTO;
        goto L_0x001e;
    L_0x003a:
        r4 = r7.getHEAT();
        if (r4 == 0) goto L_0x0013;
    L_0x0040:
        r1 = com.tado.android.rest.model.installation.ModeEnum.HEAT;
        goto L_0x001e;
    L_0x0043:
        r1 = 0;
        goto L_0x001e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.installation.acsetup.TestCommandSetActivity.getDefaultMode(com.tado.android.rest.model.installation.CoolingCapabilities):com.tado.android.rest.model.installation.ModeEnum");
    }

    private void updateControlPanelForState(CoolingZoneSetting acSetting) {
        boolean enableControls = acSetting.getMode() != null && CoolingPowerEnum.getBooleanValue(acSetting.getPower());
        setTemperatureLayoutEnabled(enableControls);
        setFanLayoutEnabled(enableControls);
        setSwingLayoutEnabled(enableControls);
        setModesLayout();
        if (acSetting.getMode() != null) {
            setTemperatureLayoutVisibility(acSetting);
            setFanLayoutVisibility(acSetting);
            setSwingLayoutVisibility(acSetting);
        } else {
            setTemperatureLayoutVisibility(acSetting);
            setFanLayoutVisibility(acSetting);
            setSwingLayoutVisibility(acSetting);
        }
        this.mPowerSwitch.setChecked(CoolingPowerEnum.getBooleanValue(acSetting.getPower()));
    }

    private void initControlPanelLayout(CoolingZoneSetting acSetting) {
        this.mPowerSwitch.setOnCheckedChangeListener(new C08431());
        initComponentAvailability(CoolingPowerEnum.getBooleanValue(acSetting.getPower()));
        initButtonListeners();
        updateControlPanelForState(acSetting);
    }

    private void initButtonListeners() {
        this.mFirstButton.setOnClickListener(new C08442());
        this.mSecondButton.setOnClickListener(new C08453());
        this.mThirdButton.setOnClickListener(new C08464());
        this.mFourthButton.setOnClickListener(new C08475());
        this.mFifthButton.setOnClickListener(new C08486());
    }

    private void enableModes(CoolingCapabilities capabilities) {
        int i = 1;
        if (capabilities.getCOOL() != null) {
            int index = 1 + 1;
            this.buttonMap.put(1, ModeEnum.COOL);
            i = index;
        }
        if (capabilities.getDRY() != null) {
            index = i + 1;
            this.buttonMap.put(i, ModeEnum.DRY);
            i = index;
        }
        if (capabilities.getFAN() != null) {
            index = i + 1;
            this.buttonMap.put(i, ModeEnum.FAN);
            i = index;
        }
        if (capabilities.getAUTO() != null) {
            index = i + 1;
            this.buttonMap.put(i, ModeEnum.AUTO);
            i = index;
        }
        if (capabilities.getHEAT() != null) {
            index = i + 1;
            this.buttonMap.put(i, ModeEnum.HEAT);
            i = index;
        }
    }

    private void setModesLayout() {
        if (this.buttonMap.get(1) != null) {
            CoolingControlHelper.initModeButtonLayoutV2(this.mFirstButtonLayout, this.mFirstButton, this.mFirstButtonText, (ModeEnum) this.buttonMap.get(1));
        } else {
            this.mFirstButtonLayout.setVisibility(8);
        }
        if (this.buttonMap.get(2) != null) {
            CoolingControlHelper.initModeButtonLayoutV2(this.mSecondButtonLayout, this.mSecondButton, this.mSecondButtonText, (ModeEnum) this.buttonMap.get(2));
        } else {
            this.mSecondButtonLayout.setVisibility(8);
        }
        if (this.buttonMap.get(3) != null) {
            CoolingControlHelper.initModeButtonLayoutV2(this.mThirdButtonLayout, this.mThirdButton, this.mThirdButtonText, (ModeEnum) this.buttonMap.get(3));
        } else {
            this.mThirdButtonLayout.setVisibility(8);
        }
        if (this.buttonMap.get(4) != null) {
            CoolingControlHelper.initModeButtonLayoutV2(this.mFourthButtonLayout, this.mFourthButton, this.mFourthButtonText, (ModeEnum) this.buttonMap.get(4));
        } else {
            this.mFourthButtonLayout.setVisibility(8);
        }
        if (this.buttonMap.get(5) != null) {
            CoolingControlHelper.initModeButtonLayoutV2(this.mFifthButtonLayout, this.mFifthButton, this.mFifthButtonText, (ModeEnum) this.buttonMap.get(5));
        } else {
            this.mFifthButtonLayout.setVisibility(8);
        }
    }

    public void restartRecording(View view) {
        selectCommandSet(null);
    }

    public void proceedClick(View view) {
        AlertDialogs.getCancelRetryAlert(getString(C0676R.string.installation_sacc_setupAC_selectCommandSet_testingCommandSets_chooseCoomandSetButton), getString(C0676R.string.installation_sacc_setupAC_selectCommandSet_testingCommandSets_confirmCommandSet_message), getString(C0676R.string.installation_sacc_setupAC_selectCommandSet_testingCommandSets_confirmCommandSet_confirmButton), getString(C0676R.string.installation_sacc_setupAC_selectCommandSet_testingCommandSets_confirmCommandSet_cancelButton), this, new C08497()).show();
    }

    public void onSelectCommandSetClick(View view) {
        InstallationProcessController.startActivity((Activity) this, TestConfirmedCommandSetListActivity.class, false);
    }

    public void initComponentAvailability(boolean isOn) {
        CoolingControlHelper.enableButton(this.mFirstButton, isOn);
        CoolingControlHelper.enableButton(this.mSecondButton, isOn);
        CoolingControlHelper.enableButton(this.mThirdButton, isOn);
        CoolingControlHelper.enableButton(this.mFourthButton, isOn);
        CoolingControlHelper.enableButton(this.mFifthButton, isOn);
        this.mFirstButtonText.setEnabled(isOn);
        this.mSecondButtonText.setEnabled(isOn);
        this.mThirdButtonText.setEnabled(isOn);
        this.mFourthButtonText.setEnabled(isOn);
        this.mFifthButtonText.setEnabled(isOn);
        setTemperatureLayoutEnabled(isOn);
        setSwingLayoutEnabled(isOn);
        setFanLayoutEnabled(isOn);
    }

    private void buttonListener(int id) {
        saveState(this.currentMode, this.currentACSetting);
        if (this.selectedState != id) {
            this.selectedState = id;
            this.currentMode = (ModeEnum) this.buttonMap.get(id);
            this.currentAcModeCapability = getCurrentAcModeCapability(this.currentMode);
            getSavedState(this.currentMode, this.currentACSetting);
            initControlPanelLayout(this.currentACSetting);
            initButtonsLayoutSelectedState(id);
            postAcSetting(this.currentACSetting);
        }
    }

    public void initButtonsLayoutSelectedState(int selected) {
        boolean z;
        boolean z2 = true;
        CoolingControlHelper.initButtonLayoutSelectedState(this.mFirstButton, selected == 1);
        FloatingActionButton floatingActionButton = this.mSecondButton;
        if (selected == 2) {
            z = true;
        } else {
            z = false;
        }
        CoolingControlHelper.initButtonLayoutSelectedState(floatingActionButton, z);
        floatingActionButton = this.mThirdButton;
        if (selected == 3) {
            z = true;
        } else {
            z = false;
        }
        CoolingControlHelper.initButtonLayoutSelectedState(floatingActionButton, z);
        floatingActionButton = this.mFourthButton;
        if (selected == 4) {
            z = true;
        } else {
            z = false;
        }
        CoolingControlHelper.initButtonLayoutSelectedState(floatingActionButton, z);
        FloatingActionButton floatingActionButton2 = this.mFifthButton;
        if (selected != 5) {
            z2 = false;
        }
        CoolingControlHelper.initButtonLayoutSelectedState(floatingActionButton2, z2);
    }

    private void setSwingLayoutVisibility(CoolingZoneSetting acSetting) {
        boolean swingEnabled = true;
        if (acSetting.getMode() != null) {
            List<String> swings = this.currentAcModeCapability.getSwings();
            if (swings != null) {
                if (swings.size() <= 1 || !CoolingPowerEnum.getBooleanValue(this.currentACSetting.getPower())) {
                    swingEnabled = false;
                }
                setSwingLayoutEnabled(swingEnabled);
                this.mControlPanelSwingButton.setText(acSetting.getSwing());
                this.mControlPanelSwingButton.setVisibility(0);
                if (this.mPowerSwitch.isChecked()) {
                    setupSwing(CoolingSwingStateEnum.getBooleanValue(acSetting.getSwing()));
                    return;
                }
                return;
            }
            this.mControlPanelSwingButton.setVisibility(8);
            acSetting.setSwing(null);
            return;
        }
        this.mControlPanelSwingButton.setVisibility(8);
        acSetting.setSwing(null);
    }

    private void setSwingLayoutEnabled(boolean enabled) {
        CoolingControlHelper.enableButton(this.mControlPanelSwingButton, enabled);
        if (!enabled) {
            this.mControlPanelSwingButton.setBackgroundColor(ContextCompat.getColor(this, C0676R.color.white));
        } else if (this.currentACSetting != null && this.currentACSetting.getSwing() != null) {
            setupSwing(CoolingSwingStateEnum.getBooleanValue(this.currentACSetting.getSwing()));
        }
    }

    private void setFanLayoutVisibility(CoolingZoneSetting acSetting) {
        int visibility;
        boolean enableFanSpeed = true;
        if (acSetting.getMode() != null) {
            List<FanSpeedEnum> fanSpeeds = this.currentAcModeCapability.getFanSpeeds();
            if (fanSpeeds != null) {
                visibility = 0;
                setFanSpeedValue(acSetting);
                if (fanSpeeds.size() <= 1 || !CoolingPowerEnum.getBooleanValue(this.currentACSetting.getPower())) {
                    enableFanSpeed = false;
                }
                setFanLayoutEnabled(enableFanSpeed);
            } else {
                visibility = 8;
                acSetting.setFanSpeed(null);
            }
        } else {
            visibility = 8;
        }
        findViewById(C0676R.id.control_panel_main_layout_fan_layout).setVisibility(visibility);
        this.mFanSpeedUp.setVisibility(visibility);
        this.mFanSpeedDown.setVisibility(visibility);
        this.mControlPanelFanSpeedIcon.setVisibility(visibility);
        if (acSetting.getFanSpeed() == null || acSetting.getFanSpeed() != FanSpeedEnum.AUTO) {
            if (visibility == 8) {
                this.mControlPanelFanText.setVisibility(visibility);
            }
            this.mControlPanelFanSpeed.setVisibility(visibility);
            return;
        }
        this.mControlPanelFanText.setVisibility(visibility);
    }

    private void setFanLayoutEnabled(boolean enabled) {
        this.mFanSpeedUp.setEnabled(enabled);
        this.mFanSpeedDown.setEnabled(enabled);
        this.mControlPanelFanSpeedIcon.setEnabled(enabled);
        this.mControlPanelFanText.setEnabled(enabled);
        this.mControlPanelFanSpeed.setEnabled(enabled);
        if (enabled) {
            this.mFanSpeedDown.clearColorFilter();
            this.mFanSpeedUp.clearColorFilter();
            this.mControlPanelFanSpeed.clearColorFilter();
            this.mControlPanelFanSpeedIcon.clearColorFilter();
            this.mControlPanelFanText.setTextColor(ContextCompat.getColor(this, C0676R.color.control_panel_dark));
            return;
        }
        this.mFanSpeedDown.setColorFilter(ContextCompat.getColor(this, C0676R.color.control_panel_disabled), Mode.SRC_ATOP);
        this.mFanSpeedUp.setColorFilter(ContextCompat.getColor(this, C0676R.color.control_panel_disabled), Mode.SRC_ATOP);
        this.mControlPanelFanSpeed.setColorFilter(ContextCompat.getColor(this, C0676R.color.control_panel_disabled), Mode.SRC_ATOP);
        this.mControlPanelFanSpeedIcon.setColorFilter(ContextCompat.getColor(this, C0676R.color.control_panel_disabled), Mode.SRC_ATOP);
        this.mControlPanelFanText.setTextColor(ContextCompat.getColor(this, C0676R.color.control_panel_disabled));
    }

    private void setTemperatureLayoutVisibility(CoolingZoneSetting acSetting) {
        int visibility;
        if (acSetting.getMode() == null) {
            visibility = 8;
        } else if (this.currentAcModeCapability.getTemperatures() != null) {
            visibility = 0;
            this.mControlPanelTemperature.setText(((int) acSetting.getTemperature().getTemperatureValue()) + getResources().getString(C0676R.string.degree_symbol));
        } else {
            visibility = 8;
            this.currentACSetting.setTemperature(null);
        }
        findViewById(C0676R.id.control_panel_main_layout_temperature_layout).setVisibility(visibility);
        this.mControlPanelTemperature.setVisibility(visibility);
        this.mTemperatureUp.setVisibility(visibility);
        this.mTemperatureDown.setVisibility(visibility);
        this.mControlPanelTemperatureIcon.setVisibility(visibility);
    }

    private void setTemperatureLayoutEnabled(boolean enabled) {
        this.mControlPanelTemperature.setEnabled(enabled);
        this.mTemperatureUp.setEnabled(enabled);
        this.mTemperatureDown.setEnabled(enabled);
        this.mControlPanelTemperatureIcon.setEnabled(enabled);
        if (enabled) {
            this.mTemperatureDown.clearColorFilter();
            this.mTemperatureUp.clearColorFilter();
            this.mControlPanelTemperatureIcon.clearColorFilter();
            this.mControlPanelTemperature.setTextColor(ContextCompat.getColor(this, C0676R.color.control_panel_dark));
            return;
        }
        this.mTemperatureDown.setColorFilter(ContextCompat.getColor(this, C0676R.color.control_panel_disabled), Mode.SRC_ATOP);
        this.mTemperatureUp.setColorFilter(ContextCompat.getColor(this, C0676R.color.control_panel_disabled), Mode.SRC_ATOP);
        this.mControlPanelTemperatureIcon.setColorFilter(ContextCompat.getColor(this, C0676R.color.control_panel_disabled), Mode.SRC_ATOP);
        this.mControlPanelTemperature.setTextColor(ContextCompat.getColor(this, C0676R.color.control_panel_disabled));
    }

    public void onFanUpClick(View view) {
        updateFanSpeed(1);
    }

    public void onFanDownClick(View view) {
        updateFanSpeed(-1);
    }

    public void onTemperatureUpClick(View view) {
        updateTemperatureBy(1);
    }

    public void onTemperatureDownClick(View view) {
        updateTemperatureBy(-1);
    }

    private void updateTemperatureBy(int value) {
        Integer newValue = Integer.valueOf(((int) this.currentACSetting.getTemperature().getTemperatureValue()) + value);
        Temperatures temperatureCapability = this.currentAcModeCapability.getTemperatures();
        if (((float) newValue.intValue()) >= temperatureCapability.getMin().getTemperature() && ((float) newValue.intValue()) <= temperatureCapability.getMax().getTemperature()) {
            this.currentACSetting.getTemperature().setTemperatureValue((float) newValue.intValue());
            postAcSetting(this.currentACSetting);
        }
        this.mControlPanelTemperature.setText(((int) this.currentACSetting.getTemperature().getTemperatureValue()) + getResources().getString(C0676R.string.degree_symbol));
    }

    public void onSwingClick(View view) {
        if (CoolingSwingStateEnum.getBooleanValue(this.currentACSetting.getSwing())) {
            this.currentACSetting.setSwing(CoolingSwingStateEnum.getCoolingSwingString(false));
        } else {
            this.currentACSetting.setSwing(CoolingSwingStateEnum.getCoolingSwingString(true));
        }
        setupSwing(CoolingSwingStateEnum.getBooleanValue(this.currentACSetting.getSwing()));
        this.mControlPanelSwingButton.setText(this.currentACSetting.getSwing());
        postAcSetting(this.currentACSetting);
    }

    private void setupSwing(boolean swingState) {
        int backgroundColor;
        int textColor;
        int imageId;
        if (swingState) {
            backgroundColor = ContextCompat.getColor(this, C0676R.color.round_mode_button_selected);
            textColor = ContextCompat.getColor(this, C0676R.color.white);
            imageId = C0676R.drawable.list_schedule_swing_swing;
        } else {
            backgroundColor = ContextCompat.getColor(this, C0676R.color.white);
            textColor = ContextCompat.getColor(this, C0676R.color.control_panel_dark);
            imageId = C0676R.drawable.control_panel_swing;
        }
        this.mControlPanelSwingButton.setBackgroundColor(backgroundColor);
        this.mControlPanelSwingButton.setTextColor(textColor);
        this.mControlPanelSwingButton.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(imageId), null, null, null);
    }

    private void updateFanSpeed(int value) {
        List<FanSpeedEnum> fanSpeeds = this.currentAcModeCapability.getFanSpeeds();
        int currentFanSpeedIndex = -1;
        if (fanSpeeds != null && fanSpeeds.size() > 1) {
            fanSpeeds = CapabilitiesController.INSTANCE.sortFanSpeedValuesV2(fanSpeeds);
            for (int i = 0; i < fanSpeeds.size(); i++) {
                if (this.currentACSetting.getFanSpeed() == fanSpeeds.get(i)) {
                    currentFanSpeedIndex = i;
                    break;
                }
            }
            if (currentFanSpeedIndex != -1 && currentFanSpeedIndex + value >= 0 && currentFanSpeedIndex + value < fanSpeeds.size()) {
                this.currentACSetting.setFanSpeed((FanSpeedEnum) fanSpeeds.get(currentFanSpeedIndex + value));
                setFanSpeedValue(this.currentACSetting);
                postAcSetting(this.currentACSetting);
            }
        }
    }

    private void setFanSpeedValue(CoolingZoneSetting acSetting) {
        int id;
        if (acSetting.getFanSpeed() == FanSpeedEnum.AUTO) {
            id = -1;
        } else if (acSetting.getFanSpeed() == FanSpeedEnum.LOW) {
            id = C0676R.drawable.control_panel_fan_speed_low;
        } else if (acSetting.getFanSpeed() == FanSpeedEnum.MIDDLE) {
            id = C0676R.drawable.control_panel_fan_speed_mid;
        } else {
            id = C0676R.drawable.control_panel_fan_speed_high;
        }
        if (id == -1) {
            this.mControlPanelFanSpeed.setVisibility(4);
            this.mControlPanelFanText.setVisibility(0);
            return;
        }
        this.mControlPanelFanSpeed.setVisibility(0);
        this.mControlPanelFanText.setVisibility(4);
        this.mControlPanelFanSpeed.setImageDrawable(getResources().getDrawable(id));
    }

    private void postAcSetting(CoolingZoneSetting acSetting) {
        showWaitingLayout(true);
        int installationId = InstallationProcessController.getInstallationProcessController().getInstallationId();
        AcCommandSetId acCommandSetId = new AcCommandSetId();
        acCommandSetId.setCode(InstallationProcessController.getInstallationProcessController().getSelectedAcCommandSet().getCode());
        acCommandSetId.setCommandType(InstallationProcessController.getInstallationProcessController().getSelectedAcCommandSet().getCommandType());
        AcCommandSetTest acCommandTest = new AcCommandSetTest();
        acCommandTest.setAcSetting(acSetting);
        acCommandTest.setCommandSetId(acCommandSetId);
        RestServiceGenerator.getTadoInstallationRestService().testAcCommand(UserConfig.getHomeId(), Integer.valueOf(installationId), acCommandTest).enqueue(new C08508());
    }

    private void postAcSetting(ACSetting acSetting) {
    }

    private void selectCommandSet(AcCommandSetId commandSetId) {
        showWaitingLayout(true);
        int installationId = InstallationProcessController.getInstallationProcessController().getInstallationId();
        if (commandSetId != null) {
            RestServiceGenerator.getTadoInstallationRestService().selectCommandSet(UserConfig.getHomeId(), Integer.valueOf(installationId), commandSetId).enqueue(this.listener);
        } else {
            RestServiceGenerator.getTadoInstallationRestService().selectCommandSet(UserConfig.getHomeId(), Integer.valueOf(installationId)).enqueue(this.listener);
        }
    }

    private void showWaitingLayout(boolean waitingForSignal) {
        boolean z = !waitingForSignal && this.mPowerSwitch.isChecked();
        initComponentAvailability(z);
        initButtonsLayoutSelectedState(this.selectedState);
        if (waitingForSignal) {
            this.progressBar.setVisibility(0);
        } else {
            this.progressBar.setVisibility(4);
        }
    }

    private void saveState(ModeEnum mode, CoolingZoneSetting acSetting) {
        if (this.stateMap != null) {
            if (this.stateMap.containsKey(mode)) {
                this.stateMap.remove(mode);
            }
            CoolingZoneSetting acSettingState = new CoolingZoneSetting();
            acSettingState.setFanSpeed(acSetting.getFanSpeed());
            acSettingState.setSwing(acSetting.getSwing());
            acSettingState.setTemperature(acSetting.getTemperature());
            acSettingState.setPower(acSetting.getPower());
            acSettingState.setMode(acSetting.getMode());
            if (mode != null) {
                this.stateMap.put(mode, acSettingState);
            }
        }
    }

    private void getSavedState(ModeEnum mode, CoolingZoneSetting acSetting) {
        CoolingZoneSetting state;
        if (this.stateMap == null) {
            this.stateMap = new EnumMap(ModeEnum.class);
        }
        if (this.stateMap.containsKey(mode)) {
            state = (CoolingZoneSetting) this.stateMap.get(mode);
        } else {
            this.currentAcModeCapability = getCurrentAcModeCapability(mode);
            state = generateDefaultModeState(mode, this.currentAcModeCapability);
        }
        acSetting.setFanSpeed(state.getFanSpeed());
        acSetting.setSwing(state.getSwing());
        acSetting.setTemperature(state.getTemperature());
        acSetting.setPower(state.getPower());
        acSetting.setMode(state.getMode());
    }
}
