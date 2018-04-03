package com.tado.android.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tado.C0676R;
import com.tado.android.controllers.CapabilitiesController;
import com.tado.android.rest.model.Temperature;
import com.tado.android.rest.model.Zone.TypeEnum;
import com.tado.android.rest.model.ZoneSetting;
import com.tado.android.rest.model.installation.FanSpeedEnum;
import com.tado.android.rest.model.installation.SwingEnum;
import com.tado.android.times.view.model.ModeEnum;
import com.tado.android.views.TadoAcModeButtonLayout.OnAcModeChangedListener;
import com.tado.android.views.TadoFanSpeedPickerView.OnFanSpeedValueChangedListener;
import com.tado.android.views.TadoSwingButton.OnSwingStateChanged;
import com.tado.android.views.TadoTemperaturePickerView.OnTemperatureValueChangedListener;
import java.util.HashMap;
import java.util.Map;

public class TadoZoneSettingsView extends LinearLayout {
    private int backgroundColorResource;
    private ZoneSetting currentSetting;
    @BindView(2131297217)
    View mFirstLine;
    @BindView(2131297218)
    View mFrostProtectionLayout;
    @BindView(2131297220)
    TadoPowerSwitchView mPowerSwitch;
    @BindView(2131297221)
    View mSecondLine;
    @BindView(2131297219)
    View mSettingsLayout;
    @BindView(2131297215)
    TadoAcModeButtonLayout mTadoAcModeButtonsLayout;
    @BindView(2131297216)
    TadoFanSpeedPickerView mTadoFanSpeedPickerView;
    @BindView(2131297222)
    TadoSwingButton mTadoSwingButton;
    @BindView(2131297223)
    TadoTemperaturePickerView mTadoTemperaturePickerView;
    @BindView(2131297026)
    View mView;
    private OnZoneSettingChangedListener onZoneSettingChangedListener;
    private float settingsLayoutHeight;
    private Map<ModeEnum, ZoneSetting> stateMap;
    private TadoZoneSettingViewConfiguration viewConfig;

    public interface OnZoneSettingChangedListener {
        void onZoneSettingChanged(ZoneSetting zoneSetting);
    }

    class C12841 implements OnCheckedChangeListener {
        C12841() {
        }

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (TadoZoneSettingsView.this.viewConfig.isHeatingZone()) {
                TadoZoneSettingsView.this.handleHeatingTemperatureLayout(isChecked);
            }
            if (isChecked) {
                if (TadoZoneSettingsView.this.currentSetting.getMode() == null) {
                    TadoZoneSettingsView.this.currentSetting.setMode(TadoZoneSettingsView.this.viewConfig.getDefaultMode().name());
                }
                if (!TadoZoneSettingsView.this.currentSetting.getPowerBoolean()) {
                    TadoZoneSettingsView.this.getSavedState(ModeEnum.valueOf(TadoZoneSettingsView.this.currentSetting.getMode()), TadoZoneSettingsView.this.currentSetting);
                }
            } else if (!(TadoZoneSettingsView.this.currentSetting == null || TadoZoneSettingsView.this.currentSetting.getMode() == null)) {
                TadoZoneSettingsView.this.saveState(ModeEnum.valueOf(TadoZoneSettingsView.this.currentSetting.getMode()), TadoZoneSettingsView.this.currentSetting);
            }
            TadoZoneSettingsView.this.currentSetting.setPowerBoolean(isChecked);
            TadoZoneSettingsView.this.enableComponents(isChecked);
            TadoZoneSettingsView.this.updateViewValues(TadoZoneSettingsView.this.currentSetting);
            TadoZoneSettingsView.this.updateViewState(TadoZoneSettingsView.this.currentSetting);
        }
    }

    class C12852 implements OnTemperatureValueChangedListener {
        C12852() {
        }

        public void onTemperatureValueChanged(float value) {
            if (TadoZoneSettingsView.this.currentSetting.getTemperature() != null) {
                TadoZoneSettingsView.this.currentSetting.getTemperature().setTemperatureValue(value);
                TadoZoneSettingsView.this.updateViewState(TadoZoneSettingsView.this.currentSetting);
            }
        }
    }

    class C12863 implements OnAcModeChangedListener {
        C12863() {
        }

        public void onAcModeChangedListener(ModeEnum mode) {
            TadoZoneSettingsView.this.saveState(ModeEnum.valueOf(TadoZoneSettingsView.this.currentSetting.getMode()), TadoZoneSettingsView.this.currentSetting);
            TadoZoneSettingsView.this.currentSetting = TadoZoneSettingsView.this.getSelectedModeState(mode.name());
            TadoZoneSettingsView.this.currentSetting.setPowerBoolean(TadoZoneSettingsView.this.mPowerSwitch.isChecked());
            TadoZoneSettingsView.this.initControlPanelLayout(TadoZoneSettingsView.this.currentSetting);
            TadoZoneSettingsView.this.updateViewState(TadoZoneSettingsView.this.currentSetting);
        }
    }

    class C12874 implements OnSwingStateChanged {
        C12874() {
        }

        public void onSwingStateChanged(SwingEnum swingState) {
            TadoZoneSettingsView.this.currentSetting.setSwing(swingState.name());
            TadoZoneSettingsView.this.updateViewState(TadoZoneSettingsView.this.currentSetting);
        }
    }

    class C12885 implements OnFanSpeedValueChangedListener {
        C12885() {
        }

        public void onFanSpeedValueChanged(FanSpeedEnum speedEnum) {
            if (TadoZoneSettingsView.this.currentSetting != null) {
                TadoZoneSettingsView.this.currentSetting.setFanSpeed(speedEnum.name());
                TadoZoneSettingsView.this.updateViewState(TadoZoneSettingsView.this.currentSetting);
            }
        }
    }

    public TadoZoneSettingsView(Context context) {
        super(context);
        init(null);
    }

    public TadoZoneSettingsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TadoZoneSettingsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public TadoZoneSettingsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public void enableComponents(boolean isOn) {
        this.mTadoAcModeButtonsLayout.setEnabled(isOn);
        this.mTadoFanSpeedPickerView.setEnabled(isOn);
        this.mTadoSwingButton.setEnabled(isOn);
        this.mTadoTemperaturePickerView.setEnabled(isOn);
    }

    public void setSettingsLayoutHeight(int settingsLayoutHeight) {
        this.settingsLayoutHeight = (float) settingsLayoutHeight;
        initSettingsLayoutHeight();
    }

    public void setOnZoneSettingChangedListener(OnZoneSettingChangedListener onZoneSettingChangedListener) {
        this.onZoneSettingChangedListener = onZoneSettingChangedListener;
    }

    public void initViewModel(TadoZoneSettingViewConfiguration zoneSettingViewConfiguration, ZoneSetting zoneSetting) {
        this.currentSetting = zoneSetting;
        this.viewConfig = zoneSettingViewConfiguration;
        initViewState(this.currentSetting);
        initListeners();
    }

    public void initStateMap(Map<ModeEnum, ZoneSetting> preFillState) {
        this.stateMap = preFillState;
    }

    private void init(AttributeSet attributeSet) {
        ButterKnife.bind(LayoutInflater.from(getContext()).inflate(C0676R.layout.tado_zone_settings_view, this), (View) this);
        if (attributeSet != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(attributeSet, C0676R.styleable.TadoZoneSettingsView, 0, 0);
            try {
                this.backgroundColorResource = a.getColor(0, ContextCompat.getColor(getContext(), C0676R.color.white));
                this.settingsLayoutHeight = a.getDimension(1, 150.0f);
            } finally {
                a.recycle();
            }
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mView.setBackgroundColor(this.backgroundColorResource);
        initSettingsLayoutHeight();
    }

    private void initSettingsLayoutHeight() {
        new LayoutParams(-1, -2).height = (int) this.settingsLayoutHeight;
    }

    public void hidePowerSwitch() {
        this.mPowerSwitch.setVisibility(8);
    }

    private void prepareHeatingLayout() {
        this.mSecondLine.setVisibility(8);
        this.mTadoAcModeButtonsLayout.setVisibility(8);
        this.mTadoSwingButton.setVisibility(8);
        this.mTadoFanSpeedPickerView.setVisibility(8);
    }

    private void prepareHotWaterLayout() {
        this.mSecondLine.setVisibility(8);
        this.mTadoAcModeButtonsLayout.setVisibility(8);
        this.mTadoSwingButton.setVisibility(8);
        this.mTadoFanSpeedPickerView.setVisibility(8);
        if (this.viewConfig.isTemperatureSupported(getModeEnum(this.currentSetting))) {
            this.mTadoTemperaturePickerView.setVisibility(0);
            return;
        }
        this.mTadoTemperaturePickerView.setVisibility(8);
        this.mFrostProtectionLayout.setVisibility(8);
        this.mSettingsLayout.setVisibility(8);
        this.mFirstLine.setVisibility(8);
        this.mSecondLine.setVisibility(8);
    }

    private void handleHeatingTemperatureLayout(boolean power) {
        if (power) {
            this.mTadoTemperaturePickerView.setVisibility(0);
            this.mFrostProtectionLayout.setVisibility(8);
            return;
        }
        this.mTadoTemperaturePickerView.setVisibility(8);
        this.mFrostProtectionLayout.setVisibility(0);
    }

    private ModeEnum getModeEnum(ZoneSetting zoneSetting) {
        return ModeEnum.valueOf(zoneSetting.getMode());
    }

    private void setSwingLayoutVisibility(ZoneSetting setting) {
        ModeEnum selectedMode = ModeEnum.getModeFromString(setting.getMode());
        if (this.viewConfig.isSwingSupported(selectedMode)) {
            this.mTadoSwingButton.setSwingState(setting.getSwing() != null ? SwingEnum.valueOf(setting.getSwing()) : this.viewConfig.getDefaultSwing(selectedMode));
            this.mTadoSwingButton.setVisibility(0);
            this.mTadoSwingButton.setEnabled(setting.getPowerBoolean());
            return;
        }
        this.mTadoSwingButton.setVisibility(8);
        setting.setSwing(null);
    }

    private void setFanLayoutVisibility(ZoneSetting setting) {
        int visibility;
        ModeEnum selectedMode = ModeEnum.getModeFromString(setting.getMode());
        if (this.viewConfig.isFanSupported(selectedMode)) {
            visibility = 0;
            this.mTadoFanSpeedPickerView.setCurrentFanSpeed(setting.getFanSpeed() != null ? FanSpeedEnum.valueOf(setting.getFanSpeed()) : this.viewConfig.getDefaultFanSpeed(selectedMode));
            this.mTadoFanSpeedPickerView.setCurrentMode(getModeEnum(setting));
        } else {
            visibility = 8;
            setting.setFanSpeed(null);
        }
        this.mTadoFanSpeedPickerView.setVisibility(visibility);
    }

    private void setTemperatureLayoutVisibility(ZoneSetting setting) {
        int visibility;
        ModeEnum selectedMode = ModeEnum.getModeFromString(setting.getMode());
        if (this.viewConfig.isTemperatureSupported(selectedMode)) {
            visibility = 0;
            setTemperatureTextValue(setting.getTemperature() != null ? setting.getTemperature().getTemperatureValue() : this.viewConfig.getDefaultTemperature(selectedMode));
        } else {
            visibility = 8;
            setting.setTemperature(null);
        }
        this.mTadoTemperaturePickerView.setVisibility(visibility);
    }

    private void initListeners() {
        this.mTadoAcModeButtonsLayout.setOnAcModeChangedListener(getOnAcModeChangedListener());
        this.mTadoTemperaturePickerView.setOnTemperatureValueChangedListener(getTemperatureViewPickerListener());
        this.mTadoFanSpeedPickerView.setOnFanSpeedValueChangedListener(getOnFanSpeedValueChangedListener());
        this.mTadoSwingButton.setOnSwingStateChangedListener(getOnSwingStateChangedListener());
        this.mPowerSwitch.setOnCheckedChangeListener(getListenerPowerSwitchListener());
    }

    @NonNull
    private OnCheckedChangeListener getListenerPowerSwitchListener() {
        return new C12841();
    }

    @NonNull
    private OnTemperatureValueChangedListener getTemperatureViewPickerListener() {
        return new C12852();
    }

    @NonNull
    private OnAcModeChangedListener getOnAcModeChangedListener() {
        return new C12863();
    }

    @NonNull
    private OnSwingStateChanged getOnSwingStateChangedListener() {
        return new C12874();
    }

    @NonNull
    private OnFanSpeedValueChangedListener getOnFanSpeedValueChangedListener() {
        return new C12885();
    }

    private ZoneSetting getSelectedModeState(String mode) {
        if (this.currentSetting == null) {
            this.currentSetting = new ZoneSetting();
        }
        this.currentSetting.setMode(mode);
        getSavedState(ModeEnum.valueOf(mode), this.currentSetting);
        if (!this.viewConfig.isTemperatureSupported(ModeEnum.valueOf(mode))) {
            this.currentSetting.setTemperature(null);
        }
        if (!this.viewConfig.isFanSupported(ModeEnum.getModeFromString(mode))) {
            this.currentSetting.setFanSpeed(null);
        }
        if (!this.viewConfig.isSwingSupported(ModeEnum.getModeFromString(mode))) {
            this.currentSetting.setSwing(null);
        }
        return this.currentSetting;
    }

    private void initControlPanelLayout(ZoneSetting setting) {
        this.mPowerSwitch.setZoneType(TypeEnum.valueOf(setting.getType()));
        if (this.currentSetting.getMode() == null) {
            this.currentSetting.setMode(this.viewConfig.getDefaultMode().name());
        }
        updateViewsWithSetting(setting);
    }

    private void getDefaultSetting(ZoneSetting setting, TadoZoneSettingViewConfiguration config) {
        if (setting.getMode() == null) {
            setting.setMode(config.getDefaultMode().name());
        }
        if (setting.getTemperature() == null && config.isTemperatureSupported(getModeEnum(setting))) {
            setting.setTemperature(Temperature.fromValue(config.getDefaultTemperature(ModeEnum.valueOf(setting.getMode()))));
        }
        if (setting.getFanSpeed() == null && config.isFanSupported(getModeEnum(setting))) {
            FanSpeedEnum fanSpeed = config.getDefaultFanSpeed(ModeEnum.valueOf(setting.getMode()));
            if (fanSpeed != null) {
                setting.setFanSpeed(fanSpeed.name());
            }
        }
        if (setting.getSwing() == null && config.isSwingSupported(getModeEnum(setting))) {
            SwingEnum swingEnum = config.getDefaultSwing(ModeEnum.valueOf(setting.getMode()));
            if (swingEnum != null) {
                setting.setSwing(swingEnum.name());
            }
        }
        setting.setPowerBoolean(config.getDefaultPower(config.getDefaultMode()));
    }

    private void updateViewsWithSetting(ZoneSetting setting) {
        boolean enableControls = setting.getMode() != null && setting.getPowerBoolean();
        updateViewVisibility(setting);
        updateViewValues(setting);
        enableComponents(enableControls);
    }

    private void updateViewValues(ZoneSetting setting) {
        if (setting.getMode() != null) {
            this.mTadoAcModeButtonsLayout.setCurrentMode(getModeEnum(setting));
        }
        if (setting.getFanSpeed() != null) {
            this.mTadoFanSpeedPickerView.setCurrentFanSpeed(FanSpeedEnum.valueOf(setting.getFanSpeed()));
        }
        if (setting.getSwing() != null) {
            this.mTadoSwingButton.setSwingState(SwingEnum.valueOf(setting.getSwing()));
        }
        if (!(setting.getTemperature() == null || setting.getMode() == null)) {
            ModeEnum mode = getModeEnum(setting);
            this.mTadoTemperaturePickerView.setMinValue(this.viewConfig.getTemperatureMin(mode));
            this.mTadoTemperaturePickerView.setMaxValue(this.viewConfig.getTemperatureMax(mode));
            this.mTadoTemperaturePickerView.setStep(this.viewConfig.getTemperatureStep(mode));
            this.mTadoTemperaturePickerView.setCurrentTemperature(CapabilitiesController.INSTANCE.getTemperatureValue(setting.getTemperature()));
        }
        this.mPowerSwitch.setChecked(setting.getPowerBoolean());
    }

    private void setTemperatureTextValue(float value) {
        this.mTadoTemperaturePickerView.setCurrentTemperature(value);
    }

    private void updateViewVisibility(ZoneSetting setting) {
        if (setting.getMode() != null) {
            setTemperatureLayoutVisibility(setting);
            setFanLayoutVisibility(setting);
            setSwingLayoutVisibility(setting);
            if (setting.getMode().equalsIgnoreCase(ModeEnum.HEATING.getMode())) {
                prepareHeatingLayout();
                handleHeatingTemperatureLayout(setting.getPowerBoolean());
            } else if (setting.getMode().equalsIgnoreCase(ModeEnum.HOT_WATER.getMode())) {
                prepareHotWaterLayout();
            }
        }
    }

    private void initViewState(ZoneSetting currentSetting) {
        if (this.viewConfig.isCoolingZone()) {
            this.mTadoAcModeButtonsLayout.setSupportedModes(this.viewConfig.getSupportedModes());
        }
        initControlPanelLayout(currentSetting);
    }

    private void updateViewState(ZoneSetting zoneSetting) {
        this.currentSetting = zoneSetting;
        if (this.onZoneSettingChangedListener != null) {
            this.onZoneSettingChangedListener.onZoneSettingChanged(zoneSetting);
        }
    }

    private void saveState(ModeEnum mode, ZoneSetting setting) {
        if (mode != null && setting != null) {
            if (this.stateMap == null) {
                this.stateMap = new HashMap();
            }
            if (this.stateMap.containsKey(mode)) {
                this.stateMap.remove(mode);
            }
            ZoneSetting settingState = new ZoneSetting();
            settingState.setFanSpeed(setting.getFanSpeed());
            settingState.setSwing(setting.getSwing());
            if (setting.getTemperature() != null) {
                settingState.setTemperature(Temperature.fromValue(setting.getTemperature().getTemperatureValue()));
            }
            this.stateMap.put(mode, settingState);
        }
    }

    private void getSavedState(ModeEnum mode, ZoneSetting settingState) {
        ZoneSetting state;
        if (this.stateMap == null) {
            this.stateMap = new HashMap();
        }
        if (this.stateMap.containsKey(mode)) {
            state = (ZoneSetting) this.stateMap.get(mode);
        } else {
            state = new ZoneSetting();
            getDefaultSetting(state, this.viewConfig);
        }
        settingState.setSwing(state.getSwing());
        if (!(state == null || state.getTemperature() == null)) {
            settingState.setTemperature(Temperature.fromValue(state.getTemperature().getTemperatureValue()));
        }
        settingState.setFanSpeed(state.getFanSpeed());
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        enableComponents(enabled);
        this.mPowerSwitch.setEnabled(enabled);
    }
}
