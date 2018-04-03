package com.tado.android.installation.complexteaching;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tado.C0676R;
import com.tado.android.alert_dialogs.CustomDialog;
import com.tado.android.alert_dialogs.CustomDialog.CustomDialogButton;
import com.tado.android.alert_dialogs.CustomDialog.CustomDialogText;
import com.tado.android.control_panel.CoolingControlHelper;
import com.tado.android.entities.ACModeRecordingCapabilities;
import com.tado.android.entities.ACModeRecordingCapability;
import com.tado.android.entities.ACSetting;
import com.tado.android.entities.TeachingMode;
import com.tado.android.entities.TeachingRun;
import com.tado.android.entities.TeachingRunSummary;
import com.tado.android.entities.TemperatureRange;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.installation.clc.RecordHeatModeActivity;
import com.tado.android.installation.common.RecordingBaseActivity;
import com.tado.android.installation.teaching.SetACToSettingActivity;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.installation.AcInstallation;
import com.tado.android.rest.model.installation.AcSetupPhase;
import com.tado.android.rest.model.installation.AcSetupPhase.PhaseEnum;
import com.tado.android.rest.model.installation.InstallationStateTransitionResult;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.times.view.model.CoolingFanSpeedEnum;
import com.tado.android.times.view.model.CoolingPowerEnum;
import com.tado.android.times.view.model.CoolingSwingStateEnum;
import com.tado.android.times.view.model.ModeEnum;
import com.tado.android.utils.Constants;
import com.tado.android.utils.UserConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Response;

public class TestACSettingCommandsActivity extends RecordingBaseActivity {
    private Map<Integer, ModeEnum> buttonMap = new HashMap();
    @BindView(2131296397)
    LinearLayout buttonsAndLines;
    private Map<String, ACModeRecordingCapabilities> capabilities;
    private ACSetting currentACSetting;
    private boolean firstTime = true;
    private boolean isCLCRecording;
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
    private ACSetting prevACSetting;
    @BindView(2131296880)
    View progressBar;
    private int selectedState = -1;

    class C09172 implements OnClickListener {
        C09172() {
        }

        public void onClick(View v) {
            TestACSettingCommandsActivity.this.buttonListener(1);
        }
    }

    class C09183 implements OnClickListener {
        C09183() {
        }

        public void onClick(View v) {
            TestACSettingCommandsActivity.this.buttonListener(2);
        }
    }

    class C09194 implements OnClickListener {
        C09194() {
        }

        public void onClick(View v) {
            TestACSettingCommandsActivity.this.buttonListener(3);
        }
    }

    class C09205 implements OnClickListener {
        C09205() {
        }

        public void onClick(View v) {
            TestACSettingCommandsActivity.this.buttonListener(4);
        }
    }

    class C09216 implements OnClickListener {
        C09216() {
        }

        public void onClick(View v) {
            TestACSettingCommandsActivity.this.buttonListener(5);
        }
    }

    class C09227 implements OnCheckedChangeListener {
        C09227() {
        }

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            TestACSettingCommandsActivity.this.currentACSetting.setPower(CoolingPowerEnum.getCoolingPowerString(isChecked));
            if (isChecked) {
                TestACSettingCommandsActivity.this.copyACSetting(TestACSettingCommandsActivity.this.prevACSetting, TestACSettingCommandsActivity.this.currentACSetting);
            } else {
                TestACSettingCommandsActivity.this.currentACSetting.setMode(null);
                TestACSettingCommandsActivity.this.currentACSetting.setTemperature(null);
                TestACSettingCommandsActivity.this.currentACSetting.setSwing(null);
                TestACSettingCommandsActivity.this.currentACSetting.setFanSpeed(null);
                ComplexTeachingController state = ComplexTeachingController.getComplexTeachingController();
                ModeEnum currentMode = ModeEnum.getModeFromString(state.getCurrentTeachingMode().getMode());
                TestACSettingCommandsActivity.this.prevACSetting = TestACSettingCommandsActivity.this.initACSettingForRun(currentMode.getMode(), TestACSettingCommandsActivity.this.prevACSetting, state.getCurrentTeachingMode().getRuns()[state.getCurrentRunIndex()]);
                TestACSettingCommandsActivity.this.currentACSetting = TestACSettingCommandsActivity.this.initACSettingForRun(currentMode.getMode(), TestACSettingCommandsActivity.this.currentACSetting, state.getCurrentTeachingMode().getRuns()[state.getCurrentRunIndex()]);
                TestACSettingCommandsActivity.this.currentACSetting.setPower(CoolingPowerEnum.getCoolingPowerString(isChecked));
            }
            TestACSettingCommandsActivity.this.initComponentAvailability(isChecked);
            TestACSettingCommandsActivity.this.initButtonsLayoutEnabledState(TestACSettingCommandsActivity.this.selectedState);
            TestACSettingCommandsActivity.this.initButtonsLayoutSelectedState(TestACSettingCommandsActivity.this.selectedState);
            TestACSettingCommandsActivity.this.updateControlPanelForState(TestACSettingCommandsActivity.this.currentACSetting);
            TestACSettingCommandsActivity.this.postAcSetting(TestACSettingCommandsActivity.this.currentACSetting);
        }
    }

    class C09238 extends TadoCallback<Void> {
        C09238() {
        }

        public void onResponse(Call<Void> call, Response<Void> response) {
            super.onResponse(call, response);
            TestACSettingCommandsActivity.this.showWaitingLayout(false);
            if (!response.isSuccessful()) {
                if (this.serverError != null) {
                    InstallationProcessController.handleError(TestACSettingCommandsActivity.this, this.serverError, (Call) call, response.code());
                } else {
                    InstallationProcessController.showConnectionErrorRetrofit(TestACSettingCommandsActivity.this, call, this);
                }
            }
        }

        public void onFailure(Call<Void> call, Throwable t) {
            super.onFailure(call, t);
            TestACSettingCommandsActivity.this.showWaitingLayout(false);
            InstallationProcessController.showConnectionErrorRetrofit(TestACSettingCommandsActivity.this, call, this);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_test_ac_setting_commands);
        ButterKnife.bind((Activity) this);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_setupAC_testACSettingCommands_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_setupAC_testACSettingCommands_testing_title);
        this.proceedButton.setText(C0676R.string.installation_sacc_setupAC_testACSettingCommands_testing_confirmButton);
        this.isCLCRecording = InstallationProcessController.getInstallationProcessController().getInstallationInfo().isCLCRecording();
        initLayout();
    }

    private void initLayout() {
        ComplexTeachingController state = ComplexTeachingController.getComplexTeachingController();
        this.capabilities = getCapabilitiesForTeachingRun(state.getRecordedCapabilities(), state.getCurrentTeachingMode().getMode(), state.getCurrentTeachingMode().getRuns()[state.getCurrentRunIndex()]);
        enableModes(this.capabilities);
        ModeEnum currentMode = ModeEnum.getModeFromString(state.getCurrentTeachingMode().getMode());
        this.selectedState = CoolingControlHelper.getKeyFromValue(this.buttonMap, currentMode);
        this.prevACSetting = initACSettingForRun(currentMode.getMode(), this.prevACSetting, state.getCurrentTeachingMode().getRuns()[state.getCurrentRunIndex()]);
        this.currentACSetting = initACSettingForRun(currentMode.getMode(), this.currentACSetting, state.getCurrentTeachingMode().getRuns()[state.getCurrentRunIndex()]);
        setModesLayout();
        initControlPanelLayout(this.currentACSetting);
    }

    private void highlightNewFunctionality(String mode) {
        int prevSwingCount;
        int i = 1;
        ComplexTeachingController state = ComplexTeachingController.getComplexTeachingController();
        Map<String, ACModeRecordingCapabilities> previousCapabilities = state.getRecordingCapabilitiesMap();
        int prevFanCount;
        if (previousCapabilities == null || previousCapabilities.get(mode) == null || ((ACModeRecordingCapabilities) previousCapabilities.get(mode)).getFanSpeeds() == null) {
            prevFanCount = 0;
        } else {
            prevFanCount = ((ACModeRecordingCapabilities) previousCapabilities.get(mode)).getFanSpeeds().length;
        }
        if (previousCapabilities == null || previousCapabilities.get(mode) == null || ((ACModeRecordingCapabilities) previousCapabilities.get(mode)).getSwings() == null) {
            prevSwingCount = 0;
        } else {
            prevSwingCount = ((ACModeRecordingCapabilities) previousCapabilities.get(mode)).getSwings().length;
        }
        TeachingRunSummary summary = state.getCurrentTeachingMode().getRuns()[state.getCurrentRunIndex()].getSummary();
        animateView(findViewById(C0676R.id.power_highlight));
        if (summary.getTemperatures() != null) {
            animateView(findViewById(C0676R.id.temp_down_highlight));
            animateView(findViewById(C0676R.id.temp_up_highlight));
        }
        if (summary.getFanSpeed() != null && ((ACModeRecordingCapabilities) this.capabilities.get(mode)).getFanSpeeds().length > 1 && ((ACModeRecordingCapabilities) this.capabilities.get(mode)).getFanSpeeds().length > prevFanCount) {
            animateView(findViewById(C0676R.id.fan_up_highlight));
            animateView(findViewById(C0676R.id.fan_down_highlight));
        }
        if (summary.getSwing() != null) {
            int i2;
            if (((ACModeRecordingCapabilities) this.capabilities.get(mode)).getSwings().length > 1) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            if (((ACModeRecordingCapabilities) this.capabilities.get(mode)).getSwings().length <= prevSwingCount) {
                i = 0;
            }
            if ((i2 & i) != 0) {
                animateView(findViewById(C0676R.id.swing_highlight));
            }
        }
        ComplexTeachingController.getComplexTeachingController().setRecordingCapabilitiesMap(this.capabilities);
    }

    private void animateView(final View view) {
        AlphaAnimation animation1 = new AlphaAnimation(0.0f, 0.7f);
        animation1.setDuration(Constants.WAIT_FOR_COMMANDSET_TEST);
        animation1.setStartOffset(300);
        animation1.setAnimationListener(new AnimationListener() {

            class C09151 implements AnimationListener {
                C09151() {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(8);
                }

                public void onAnimationRepeat(Animation animation) {
                }
            }

            public void onAnimationStart(Animation animation) {
                view.setVisibility(0);
            }

            public void onAnimationEnd(Animation animation) {
                AlphaAnimation animation2 = new AlphaAnimation(0.7f, 0.0f);
                animation2.setDuration(Constants.WAIT_FOR_COMMANDSET_TEST);
                animation2.setAnimationListener(new C09151());
                view.startAnimation(animation2);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        view.startAnimation(animation1);
    }

    private ACSetting initACSettingForRun(String mode, ACSetting setting, TeachingRun teachingRun) {
        if (setting == null) {
            setting = new ACSetting();
        }
        if (mode == null) {
            setting.setPower("OFF");
        } else {
            setting.setPower("ON");
            setting.setMode(mode);
            ACModeRecordingCapabilities modeCapabilities = (ACModeRecordingCapabilities) this.capabilities.get(mode);
            TemperatureRange temperatureRange = modeCapabilities.getTemperatures();
            if (temperatureRange != null) {
                setting.setTemperature(Integer.valueOf(temperatureRange.getMin()));
            }
            String[] fanSpeeds = modeCapabilities.getFanSpeeds();
            if (fanSpeeds != null && fanSpeeds.length > 0) {
                if (teachingRun == null) {
                    setting.setFanSpeed(fanSpeeds[0]);
                } else {
                    setting.setFanSpeed(fanSpeeds[getIndexOfString(teachingRun.getSummary().getFanSpeed(), fanSpeeds)]);
                }
            }
            String[] swings = modeCapabilities.getSwings();
            if (swings != null && swings.length > 0) {
                if (teachingRun == null) {
                    setting.setSwing(swings[0]);
                } else {
                    setting.setSwing(swings[getIndexOfString(teachingRun.getSummary().getSwing(), swings)]);
                }
            }
        }
        return setting;
    }

    private ACSetting initACSetting(String mode, ACSetting setting) {
        return initACSettingForRun(mode, setting, null);
    }

    private int getIndexOfString(String searchString, String[] stringArray) {
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].equalsIgnoreCase(searchString)) {
                return i;
            }
        }
        return 0;
    }

    private void enableModes(Map<String, ACModeRecordingCapabilities> capabilities) {
        int i = 1;
        if (this.isCLCRecording) {
            this.buttonsAndLines.setVisibility(8);
            return;
        }
        if (capabilities.containsKey(ModeEnum.COOL.getMode())) {
            int index = 1 + 1;
            this.buttonMap.put(Integer.valueOf(1), ModeEnum.COOL);
            i = index;
        }
        if (capabilities.containsKey(ModeEnum.DRY.getMode())) {
            index = i + 1;
            this.buttonMap.put(Integer.valueOf(i), ModeEnum.DRY);
            i = index;
        }
        if (capabilities.containsKey(ModeEnum.FAN.getMode())) {
            index = i + 1;
            this.buttonMap.put(Integer.valueOf(i), ModeEnum.FAN);
            i = index;
        }
        if (capabilities.containsKey(ModeEnum.AUTO.getMode())) {
            index = i + 1;
            this.buttonMap.put(Integer.valueOf(i), ModeEnum.AUTO);
            i = index;
        }
        if (capabilities.containsKey(ModeEnum.HEAT.getMode())) {
            index = i + 1;
            this.buttonMap.put(Integer.valueOf(i), ModeEnum.HEAT);
            i = index;
        }
    }

    private void setModesLayout() {
        if (this.buttonMap.get(Integer.valueOf(1)) != null) {
            CoolingControlHelper.initModeButtonLayout(this.mFirstButtonLayout, this.mFirstButton, this.mFirstButtonText, (ModeEnum) this.buttonMap.get(Integer.valueOf(1)));
        }
        if (this.buttonMap.get(Integer.valueOf(2)) != null) {
            CoolingControlHelper.initModeButtonLayout(this.mSecondButtonLayout, this.mSecondButton, this.mSecondButtonText, (ModeEnum) this.buttonMap.get(Integer.valueOf(2)));
        }
        if (this.buttonMap.get(Integer.valueOf(3)) != null) {
            CoolingControlHelper.initModeButtonLayout(this.mThirdButtonLayout, this.mThirdButton, this.mThirdButtonText, (ModeEnum) this.buttonMap.get(Integer.valueOf(3)));
        }
        if (this.buttonMap.get(Integer.valueOf(4)) != null) {
            CoolingControlHelper.initModeButtonLayout(this.mFourthButtonLayout, this.mFourthButton, this.mFourthButtonText, (ModeEnum) this.buttonMap.get(Integer.valueOf(4)));
        }
        if (this.buttonMap.get(Integer.valueOf(5)) != null) {
            CoolingControlHelper.initModeButtonLayout(this.mFifthButtonLayout, this.mFifthButton, this.mFifthButtonText, (ModeEnum) this.buttonMap.get(Integer.valueOf(5)));
        }
    }

    private Map<String, ACModeRecordingCapabilities> getCapabilitiesForTeachingRun(Map<String, ACModeRecordingCapability[]> recordedCapabilities, String mode, TeachingRun teachingRun) {
        Map<String, ACModeRecordingCapabilities> aggregatedCapabilities = new HashMap();
        for (String key : recordedCapabilities.keySet()) {
            aggregatedCapabilities.put(key, null);
        }
        TeachingRunSummary summary = teachingRun.getSummary();
        ACModeRecordingCapabilities capabilitiesPerMode = new ACModeRecordingCapabilities();
        if (summary != null) {
            capabilitiesPerMode.setTemperatures(summary.getTemperatures());
            if (summary.getFanSpeed() != null) {
                capabilitiesPerMode.setFanSpeeds(new String[]{summary.getFanSpeed()});
            }
            if (summary.getSwing() != null) {
                capabilitiesPerMode.setSwings(new String[]{summary.getSwing()});
            }
        }
        aggregatedCapabilities.put(mode, capabilitiesPerMode);
        return aggregatedCapabilities;
    }

    private Map<String, ACModeRecordingCapabilities> getAggegratedCapabilities(Map<String, ACModeRecordingCapability[]> recordedCapabilities) {
        Map<String, ACModeRecordingCapabilities> aggregatedCapabilities = new HashMap();
        for (String key : recordedCapabilities.keySet()) {
            ACModeRecordingCapabilities capabilitiesPerMode = new ACModeRecordingCapabilities();
            ACModeRecordingCapability[] capabilitiesArray = (ACModeRecordingCapability[]) recordedCapabilities.get(key);
            for (ACModeRecordingCapability capability : capabilitiesArray) {
                if (capability.getTemperatures() != null) {
                    capabilitiesPerMode.setTemperatures(capability.getTemperatures());
                }
                if (capability.getFanSpeed() != null) {
                    ArrayList<String> fanSpeeds;
                    if (capabilitiesPerMode.getFanSpeeds() != null) {
                        fanSpeeds = new ArrayList(Arrays.asList(capabilitiesPerMode.getFanSpeeds()));
                    } else {
                        fanSpeeds = new ArrayList();
                    }
                    String fanSpeed = capability.getFanSpeed();
                    if (!fanSpeeds.contains(fanSpeed)) {
                        int index = fanSpeeds.size();
                        for (int j = 0; j < fanSpeeds.size(); j++) {
                            if (CoolingFanSpeedEnum.getCoolingFanSpeed((String) fanSpeeds.get(j)).compareTo(CoolingFanSpeedEnum.getCoolingFanSpeed(fanSpeed)) >= 0) {
                                index = j;
                            }
                        }
                        fanSpeeds.add(index, fanSpeed);
                        capabilitiesPerMode.setFanSpeeds((String[]) fanSpeeds.toArray(new String[fanSpeeds.size()]));
                    }
                }
                if (capability.getSwing() != null) {
                    ArrayList<String> swingStates;
                    if (capabilitiesPerMode.getSwings() != null) {
                        swingStates = new ArrayList(Arrays.asList(capabilitiesPerMode.getSwings()));
                    } else {
                        swingStates = new ArrayList();
                    }
                    if (!swingStates.contains(capability.getSwing())) {
                        swingStates.add(capability.getSwing());
                    }
                    capabilitiesPerMode.setSwings((String[]) swingStates.toArray(new String[swingStates.size()]));
                }
            }
            aggregatedCapabilities.put(key, capabilitiesPerMode);
        }
        return aggregatedCapabilities;
    }

    private void initButtonListeners() {
        this.mFirstButton.setOnClickListener(new C09172());
        this.mSecondButton.setOnClickListener(new C09183());
        this.mThirdButton.setOnClickListener(new C09194());
        this.mFourthButton.setOnClickListener(new C09205());
        this.mFifthButton.setOnClickListener(new C09216());
    }

    private void initControlPanelLayout(ACSetting acSetting) {
        this.mPowerSwitch.setOnCheckedChangeListener(new C09227());
        initComponentAvailability(this.mPowerSwitch.isChecked());
        initButtonsLayoutEnabledState(this.selectedState);
        initButtonListeners();
        updateControlPanelForState(acSetting);
    }

    private void copyACSetting(ACSetting from, ACSetting to) {
        to.setMode(from.getMode());
        to.setTemperature(from.getTemperature());
        to.setSwing(from.getSwing());
        to.setFanSpeed(from.getFanSpeed());
    }

    private void updateControlPanelForState(ACSetting acSetting) {
        boolean enableControls;
        if (acSetting.getMode() != null) {
            enableControls = true;
        } else {
            enableControls = false;
        }
        setTemperatureLayoutEnabled(enableControls);
        setFanLayoutEnabled(enableControls);
        setSwingLayoutEnabled(enableControls);
        setModesLayout();
        if (acSetting.getMode() != null) {
            setTemperatureLayoutVisibility(acSetting);
            setFanLayoutVisibility(acSetting);
            setSwingLayoutVisibility(acSetting);
        } else if (acSetting.getMode() != null || this.prevACSetting.getMode() == null) {
            setTemperatureLayoutVisibility(acSetting);
            setFanLayoutVisibility(acSetting);
            setSwingLayoutVisibility(acSetting);
        } else {
            setTemperatureLayoutVisibility(this.prevACSetting);
            setTemperatureLayoutEnabled(false);
            setFanLayoutVisibility(this.prevACSetting);
            setFanLayoutEnabled(false);
            setSwingLayoutVisibility(this.prevACSetting);
            setSwingLayoutEnabled(false);
        }
        this.mPowerSwitch.setChecked(CoolingPowerEnum.getBooleanValue(acSetting.getPower()));
    }

    public void initComponentAvailability(boolean isOn) {
        boolean z = true;
        CoolingControlHelper.enableButton(this.mFirstButton, isButtonEnabled(1, isOn));
        CoolingControlHelper.enableButton(this.mSecondButton, isButtonEnabled(2, isOn));
        CoolingControlHelper.enableButton(this.mThirdButton, isButtonEnabled(3, isOn));
        CoolingControlHelper.enableButton(this.mFourthButton, isButtonEnabled(4, isOn));
        CoolingControlHelper.enableButton(this.mFifthButton, isButtonEnabled(5, isOn));
        this.mFirstButtonText.setEnabled(isButtonEnabled(1, isOn));
        this.mSecondButtonText.setEnabled(isButtonEnabled(2, isOn));
        this.mThirdButtonText.setEnabled(isButtonEnabled(3, isOn));
        this.mFourthButtonText.setEnabled(isButtonEnabled(4, isOn));
        this.mFifthButtonText.setEnabled(isButtonEnabled(5, isOn));
        setTemperatureLayoutEnabled(isOn);
        if (this.currentACSetting == null || this.currentACSetting.getMode() == null) {
            setSwingLayoutEnabled(isOn);
            setFanLayoutEnabled(isOn);
            return;
        }
        boolean z2;
        String[] swings = ((ACModeRecordingCapabilities) this.capabilities.get(this.currentACSetting.getMode())).getSwings();
        boolean hasSwings;
        if (swings == null || swings.length <= 1) {
            hasSwings = false;
        } else {
            hasSwings = true;
        }
        if (isOn && hasSwings) {
            z2 = true;
        } else {
            z2 = false;
        }
        setSwingLayoutEnabled(z2);
        String[] fansSpeeds = ((ACModeRecordingCapabilities) this.capabilities.get(this.currentACSetting.getMode())).getFanSpeeds();
        boolean hasFanSpeeds;
        if (fansSpeeds == null || fansSpeeds.length <= 1) {
            hasFanSpeeds = false;
        } else {
            hasFanSpeeds = true;
        }
        if (!(isOn && hasFanSpeeds)) {
            z = false;
        }
        setFanLayoutEnabled(z);
    }

    private boolean isButtonEnabled(int buttonId, boolean isOn) {
        return isOn && buttonId == this.selectedState;
    }

    private void buttonListener(int id) {
        if (this.selectedState == id) {
            this.selectedState = id;
            initACSetting(((ModeEnum) this.buttonMap.get(Integer.valueOf(id))).getMode(), this.currentACSetting);
            initControlPanelLayout(this.currentACSetting);
            initButtonsLayoutSelectedState(id);
            postAcSetting(this.currentACSetting);
        }
    }

    public void initButtonsLayoutEnabledState(int selected) {
        boolean z;
        boolean z2 = true;
        this.mFirstButton.setEnabled(selected == 1);
        FloatingActionButton floatingActionButton = this.mSecondButton;
        if (selected == 2) {
            z = true;
        } else {
            z = false;
        }
        floatingActionButton.setEnabled(z);
        floatingActionButton = this.mThirdButton;
        if (selected == 3) {
            z = true;
        } else {
            z = false;
        }
        floatingActionButton.setEnabled(z);
        floatingActionButton = this.mFourthButton;
        if (selected == 4) {
            z = true;
        } else {
            z = false;
        }
        floatingActionButton.setEnabled(z);
        FloatingActionButton floatingActionButton2 = this.mFifthButton;
        if (selected != 5) {
            z2 = false;
        }
        floatingActionButton2.setEnabled(z2);
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

    private void setSwingLayoutVisibility(ACSetting acSetting) {
        boolean swingEnabled = true;
        if (acSetting.getMode() != null) {
            String[] swings = ((ACModeRecordingCapabilities) this.capabilities.get(acSetting.getMode())).getSwings();
            if (swings != null) {
                if (swings.length <= 1) {
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

    private void setFanLayoutVisibility(ACSetting acSetting) {
        int visibility;
        boolean enableFanSpeed = true;
        if (acSetting.getMode() != null) {
            String[] fanSpeeds = ((ACModeRecordingCapabilities) this.capabilities.get(acSetting.getMode())).getFanSpeeds();
            if (fanSpeeds != null) {
                visibility = 0;
                setFanSpeedValue(acSetting);
                if (fanSpeeds.length <= 1) {
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
        if (acSetting.getFanSpeed() == null || !acSetting.getFanSpeed().equalsIgnoreCase(CoolingFanSpeedEnum.AUTO.name())) {
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

    private void setTemperatureLayoutVisibility(ACSetting acSetting) {
        int visibility;
        if (acSetting.getMode() != null) {
            TemperatureRange temperatureRange = ((ACModeRecordingCapabilities) this.capabilities.get(acSetting.getMode())).getTemperatures();
            if (temperatureRange == null || temperatureRange.isSingleValue()) {
                visibility = 8;
                this.currentACSetting.setTemperature(null);
            } else {
                visibility = 0;
                this.mControlPanelTemperature.setText(acSetting.getTemperature() + getResources().getString(C0676R.string.degree_symbol));
            }
        } else {
            visibility = 8;
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
        Integer newValue = Integer.valueOf(this.currentACSetting.getTemperature().intValue() + value);
        if (newValue.intValue() >= ((ACModeRecordingCapabilities) this.capabilities.get(this.currentACSetting.getMode())).getTemperatures().getMin() && newValue.intValue() <= ((ACModeRecordingCapabilities) this.capabilities.get(this.currentACSetting.getMode())).getTemperatures().getMax()) {
            this.currentACSetting.setTemperature(newValue);
            postAcSetting(this.currentACSetting);
        }
        this.mControlPanelTemperature.setText(this.currentACSetting.getTemperature() + getResources().getString(C0676R.string.degree_symbol));
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
        String[] fanSpeedArray = ((ACModeRecordingCapabilities) this.capabilities.get(this.currentACSetting.getMode())).getFanSpeeds();
        int currentFanSpeedIndex = -1;
        if (fanSpeedArray != null && fanSpeedArray.length > 1) {
            for (int i = 0; i < fanSpeedArray.length; i++) {
                if (this.currentACSetting.getFanSpeed().equalsIgnoreCase(fanSpeedArray[i])) {
                    currentFanSpeedIndex = i;
                    break;
                }
            }
            if (currentFanSpeedIndex != -1 && currentFanSpeedIndex + value >= 0 && currentFanSpeedIndex + value < fanSpeedArray.length) {
                this.currentACSetting.setFanSpeed(fanSpeedArray[currentFanSpeedIndex + value]);
                setFanSpeedValue(this.currentACSetting);
                postAcSetting(this.currentACSetting);
            }
        }
    }

    private void setFanSpeedValue(ACSetting acSetting) {
        int id;
        if (acSetting.getFanSpeed().equalsIgnoreCase(CoolingFanSpeedEnum.getStringValue(CoolingFanSpeedEnum.AUTO))) {
            id = -1;
        } else if (acSetting.getFanSpeed().equalsIgnoreCase(CoolingFanSpeedEnum.getStringValue(CoolingFanSpeedEnum.LOW))) {
            id = C0676R.drawable.control_panel_fan_speed_low;
        } else if (acSetting.getFanSpeed().equalsIgnoreCase(CoolingFanSpeedEnum.getStringValue(CoolingFanSpeedEnum.MEDIUM))) {
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

    private void postAcSetting(ACSetting acSetting) {
        RestServiceGenerator.getTadoInstallationRestService().testRecordAcSettingCommand(Long.valueOf((long) ComplexTeachingController.getComplexTeachingController().getRecordingId()), acSetting).enqueue(new C09238());
    }

    private void showWaitingLayout(boolean waitingForSignal) {
        boolean z = !waitingForSignal && this.mPowerSwitch.isChecked();
        initComponentAvailability(z);
        initButtonsLayoutSelectedState(this.selectedState);
        if (this.firstTime && !waitingForSignal && this.mPowerSwitch.isChecked()) {
            this.firstTime = false;
            highlightNewFunctionality(ComplexTeachingController.getComplexTeachingController().getCurrentTeachingMode().getMode());
        }
        if (waitingForSignal) {
            this.progressBar.setVisibility(0);
        } else {
            this.progressBar.setVisibility(4);
        }
    }

    public void proceedClick(View view) {
        callCheckAcSettingsForMode();
    }

    public void restartRecording(View view) {
        String str;
        String str2 = null;
        String mode = ComplexTeachingController.getComplexTeachingController().getCurrentTeachingMode().getMode();
        final CustomDialog dialog = new CustomDialog(this, CustomDialogText.CUSTOM_DIALOG_ONE_TEXT_PARAGRAPH, CustomDialogButton.CUSTOM_DIALOG_THREE_BUTTONS);
        dialog.setTitle(getString(C0676R.string.installation_sacc_setupAC_testACSettingCommands_testing_commandsDoNotWork_title));
        dialog.setBodyText1(getString(C0676R.string.installation_sacc_setupAC_testACSettingCommands_testing_commandsDoNotWork_message));
        if (this.isCLCRecording) {
            str = null;
        } else {
            str = getString(C0676R.string.installation_sacc_setupAC_testACSettingCommands_testing_commandsDoNotWork_reteachRunButton);
        }
        dialog.setButton1Text(str);
        dialog.setButton1Listener(new OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
                ComplexTeachingController state = ComplexTeachingController.getComplexTeachingController();
                state.getCurrentTeachingMode().getRuns()[state.getCurrentRunIndex()].setRecordingState("FAILED");
                ComplexTeachingController.getComplexTeachingController().setCurrentStepIndex(0);
                if (state.getCurrentTeachingMode().getRuns().length > 1) {
                    InstallationProcessController.startActivity(TestACSettingCommandsActivity.this, TeachingRunsOverviewActivity.class, true);
                } else {
                    InstallationProcessController.startActivity(TestACSettingCommandsActivity.this, SetACToSettingActivity.class, true);
                }
            }
        });
        dialog.setButton2Text(getString(C0676R.string.installation_sacc_setupAC_testACSettingCommands_testing_commandsDoNotWork_reteachModeButton, new Object[]{mode}));
        dialog.setButton2Listener(new OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
                TestACSettingCommandsActivity.this.restartMode(!TestACSettingCommandsActivity.this.isCLCRecording);
            }
        });
        if (!this.isCLCRecording) {
            str2 = getString(C0676R.string.installation_sacc_setupAC_testACSettingCommands_testing_commandsDoNotWork_startOverButton);
        }
        dialog.setButton3Text(str2);
        dialog.setButton3Listener(new OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
                TestACSettingCommandsActivity.this.restartAll(v);
            }
        });
        dialog.setCancelButtonText(getString(C0676R.string.installation_sacc_setupAC_testACSettingCommands_testing_commandsDoNotWork_cancelButton));
        dialog.setCancelButtonListener(new OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void restartMode(boolean showAlert) {
        if (showAlert) {
            showRestartModeWarning();
        } else {
            reteachModeCall();
        }
    }

    public void showRestartModeWarning() {
        String mode = ComplexTeachingController.getComplexTeachingController().getCurrentTeachingMode().getMode();
        final CustomDialog dialog = new CustomDialog(this, CustomDialogText.CUSTOM_DIALOG_ONE_TEXT_PARAGRAPH, CustomDialogButton.CUSTOM_DIALOG_ONE_BUTTON);
        dialog.setTitle(getString(C0676R.string.installation_sacc_setupAC_testACSettingCommands_testing_commandsDoNotWork_reteachMode_title));
        dialog.setBodyText1(getString(C0676R.string.installation_sacc_setupAC_testACSettingCommands_testing_commandsDoNotWork_reteachMode_message, new Object[]{mode}));
        dialog.setButton1Text(getString(C0676R.string.installation_sacc_setupAC_testACSettingCommands_testing_commandsDoNotWork_reteachMode_confirmButton, new Object[]{mode}));
        dialog.setButton1Listener(new OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
                TestACSettingCommandsActivity.this.reteachModeCall();
            }
        });
        dialog.setCancelButtonText(getString(C0676R.string.installation_sacc_setupAC_testACSettingCommands_testing_commandsDoNotWork_reteachMode_cancelButton));
        dialog.setCancelButtonListener(new OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void reteachModeCall() {
        ComplexTeachingController state = ComplexTeachingController.getComplexTeachingController();
        state.restartMode();
        RestServiceGenerator.getTadoInstallationRestService().startAcModeRecording(Long.valueOf((long) ComplexTeachingController.getComplexTeachingController().getRecordingId()), com.tado.android.rest.model.installation.ModeEnum.valueOf(state.getCurrentAcModeRecording().getMode()), state.getCurrentCapabilities()).enqueue(new TadoCallback<TeachingMode>() {
            public void onResponse(Call<TeachingMode> call, Response<TeachingMode> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    if (TestACSettingCommandsActivity.this.isCLCRecording) {
                        ComplexTeachingController.getComplexTeachingController().goToNextCapabilitiesScreen(TestACSettingCommandsActivity.this, true);
                    } else {
                        InstallationProcessController.startActivity(TestACSettingCommandsActivity.this, ModeOptionsActivity.class, true);
                    }
                } else if (this.serverError != null) {
                    InstallationProcessController.handleError(TestACSettingCommandsActivity.this, this.serverError, (Call) call, response.code());
                } else {
                    InstallationProcessController.showConnectionErrorRetrofit(TestACSettingCommandsActivity.this, call, this);
                }
            }

            public void onFailure(Call<TeachingMode> call, Throwable t) {
                super.onFailure(call, t);
                InstallationProcessController.showConnectionErrorRetrofit(TestACSettingCommandsActivity.this, call, this);
            }
        });
    }

    public void restartAll(View view) {
        final CustomDialog dialog = new CustomDialog(this, CustomDialogText.CUSTOM_DIALOG_ONE_TEXT_PARAGRAPH, CustomDialogButton.CUSTOM_DIALOG_ONE_BUTTON);
        dialog.setTitle(getString(C0676R.string.installation_sacc_setupAC_testACSettingCommands_testing_commandsDoNotWork_startOver_title));
        dialog.setBodyText1(getString(C0676R.string.installation_sacc_setupAC_testACSettingCommands_testing_commandsDoNotWork_startOver_message));
        dialog.setButton1Text(getString(C0676R.string.installation_sacc_setupAC_testACSettingCommands_testing_commandsDoNotWork_startOver_confirmButton));
        dialog.setButton1Listener(new OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
                TestACSettingCommandsActivity.this.startOverCall();
            }
        });
        dialog.setCancelButtonText(getString(C0676R.string.installation_sacc_setupAC_testACSettingCommands_testing_commandsDoNotWork_startOver_cancelButton));
        dialog.setCancelButtonListener(new OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void startOverCall() {
        RestServiceGenerator.getTadoInstallationRestService().restartAcSetupPhase(UserConfig.getHomeId(), Integer.valueOf(InstallationProcessController.getInstallationProcessController().getInstallationId()), new AcSetupPhase(PhaseEnum.AC_SETTING_RECORDING)).enqueue(new TadoCallback<InstallationStateTransitionResult>() {
            public void onResponse(Call<InstallationStateTransitionResult> call, Response<InstallationStateTransitionResult> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    AcInstallation installation = ((InstallationStateTransitionResult) response.body()).getInstallation();
                    if (installation != null) {
                        InstallationProcessController.getInstallationProcessController().goToScreenForInstallationProcessStatus(TestACSettingCommandsActivity.this, installation);
                    } else {
                        InstallationProcessController.getInstallationProcessController().detectStatus(TestACSettingCommandsActivity.this);
                    }
                } else if (this.serverError != null) {
                    InstallationProcessController.handleError(TestACSettingCommandsActivity.this, this.serverError, (Call) call, response.code());
                } else {
                    InstallationProcessController.showConnectionErrorRetrofit(TestACSettingCommandsActivity.this, call, this);
                }
            }

            public void onFailure(Call<InstallationStateTransitionResult> call, Throwable t) {
                super.onFailure(call, t);
                InstallationProcessController.showConnectionErrorRetrofit(TestACSettingCommandsActivity.this, call, this);
            }
        });
    }

    private void callCheckAcSettingsForMode() {
        RestServiceGenerator.getTadoInstallationRestService().showAcModeRecording(Long.valueOf((long) ComplexTeachingController.getComplexTeachingController().getRecordingId()), com.tado.android.rest.model.installation.ModeEnum.valueOf(ComplexTeachingController.getComplexTeachingController().getCurrentTeachingMode().getMode())).enqueue(new TadoCallback<TeachingMode>() {
            public void onResponse(Call<TeachingMode> call, Response<TeachingMode> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    if (TestACSettingCommandsActivity.this.isCLCRecording) {
                        TestACSettingCommandsActivity.this.determineNextCLCStep();
                        return;
                    }
                    Intent intent;
                    TeachingMode currentTeachingMode = (TeachingMode) response.body();
                    ComplexTeachingController.getComplexTeachingController().setCurrentTeachingMode(currentTeachingMode);
                    if (currentTeachingMode.getRecordingState().equalsIgnoreCase("FINISHED")) {
                        intent = new Intent(TestACSettingCommandsActivity.this, TeachingModesOverviewActivity.class);
                    } else {
                        intent = new Intent(TestACSettingCommandsActivity.this, TeachingRunsOverviewActivity.class);
                    }
                    InstallationProcessController.startActivity(TestACSettingCommandsActivity.this, intent, true);
                } else if (this.serverError != null) {
                    InstallationProcessController.handleError(TestACSettingCommandsActivity.this, this.serverError, (Call) call, response.code());
                } else {
                    InstallationProcessController.showConnectionErrorRetrofit(TestACSettingCommandsActivity.this, call, this);
                }
            }

            public void onFailure(Call<TeachingMode> call, Throwable t) {
                super.onFailure(call, t);
                InstallationProcessController.showConnectionErrorRetrofit(TestACSettingCommandsActivity.this, call, this);
            }
        });
    }

    private void determineNextCLCStep() {
        if (ModeEnum.HEAT.toString().equalsIgnoreCase(ComplexTeachingController.getComplexTeachingController().getCurrentAcModeRecording().getMode())) {
            endCLCTeaching();
        } else {
            InstallationProcessController.startActivity((Activity) this, new Intent(this, RecordHeatModeActivity.class), true);
        }
    }
}
