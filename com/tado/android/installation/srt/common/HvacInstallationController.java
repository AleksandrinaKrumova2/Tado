package com.tado.android.installation.srt.common;

import android.os.Handler;
import android.support.annotation.NonNull;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.DeviceType;
import com.tado.android.rest.model.HardwareDeviceCapabilities;
import com.tado.android.rest.model.hvac.ContentTypeEnum;
import com.tado.android.rest.model.hvac.HvacStepStatus;
import com.tado.android.rest.model.hvac.InstallFlowStep;
import com.tado.android.rest.model.hvac.InstallationFlow;
import com.tado.android.rest.model.hvac.MountingState.MountingStateEnum;
import com.tado.android.rest.model.hvac.State;
import com.tado.android.rest.model.hvac.StateLookup;
import com.tado.android.rest.model.hvac.StateLookup.StateLookupEnum;
import com.tado.android.rest.model.hvac.SubStep;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import retrofit2.Call;
import retrofit2.Response;

public class HvacInstallationController {
    private static final String CHARACTERISTICS_CAPABILITIES = "characteristics.capabilities";
    private static final String CONNECTION_STATE = "state.connectionState.value";
    private static final String CONNECTION_STATE_VALUE = "connectionState.value";
    private static final String COUNTRY = "state.country";
    private static final int DEVICE_STATE_POLLING_TIMER = 3000;
    private static final String DEVICE_TYPE = "deviceType";
    private static final String ENVIRONMENT = "environment";
    private static final String ENVIRONMENT_PRODUCTION = "production";
    private static final String INSTALL_FLOW_ID = "installFlowId";
    private static final String LOCALE = "state.locale";
    private static final String MOUNTING_STATE = "state.mountingState.value";
    private static final String MOUNTING_STATE_VALUE = "mountingState.value";
    private static final String ORDER = "order";
    private static final String PREVIOUS = "previous";
    private static final String PREVIOUS_STEP_ID = "state.previousStepId";
    private static final String REPLACEMENT_FROM = "state.replacementFrom";
    private static final String STACK = "state.stack";
    private static final String SUPPORTS_RADIO_ENCRYPTION_ACCESS = "state.supportsRadioEncryptionKeyAccess";
    private static final String UPGRADE_FROM_GW01 = "state.upgradeFromGW01";
    private GenericHardwareDevice device;
    private Timer deviceStatePollingTimer;
    private boolean initialStateCall;
    private HvacStepCallback listener;
    private List<SubStep> mCurentSubSteps;
    private State mDeviceState;
    private InstallationFlow mInstallationFlow;
    private GenericHardwareDevice replacedDevice;

    class C09532 implements Runnable {
        C09532() {
        }

        public void run() {
            if (Util.isNetworkAvailable()) {
                HvacInstallationController.this.getDevices();
            }
        }
    }

    class C09554 extends TadoCallback<List<GenericHardwareDevice>> {
        C09554() {
        }

        public void onResponse(Call<List<GenericHardwareDevice>> call, Response<List<GenericHardwareDevice>> response) {
            super.onResponse(call, response);
            if (response.isSuccessful()) {
                for (GenericHardwareDevice device : (List) response.body()) {
                    if (HvacInstallationController.this.device.getShortSerialNo().equals(device.getShortSerialNo())) {
                        HvacInstallationController.this.getState(device);
                        if (HvacInstallationController.this.mCurentSubSteps != null) {
                            HvacInstallationController.this.processDeviceProperty(HvacInstallationController.this.mCurentSubSteps, HvacInstallationController.this.mDeviceState);
                        }
                        if (HvacInstallationController.this.initialStateCall) {
                            HvacInstallationController.this.getHvacFlowStep(HvacInstallationController.this.mInstallationFlow, HvacInstallationController.this.mDeviceState, false);
                            HvacInstallationController.this.initialStateCall = false;
                        }
                    }
                }
                if (HvacInstallationController.this.initialStateCall) {
                    HvacInstallationController.this.getHvacFlowStep(HvacInstallationController.this.mInstallationFlow, HvacInstallationController.this.mDeviceState, false);
                    HvacInstallationController.this.initialStateCall = false;
                }
            }
        }

        public void onFailure(Call<List<GenericHardwareDevice>> call, Throwable t) {
            super.onFailure(call, t);
            HvacInstallationController.this.listener.onFailure();
        }
    }

    public void detectStatus(GenericHardwareDevice device, final HvacStepCallback listener, GenericHardwareDevice replacedDevice) {
        this.listener = listener;
        this.device = device;
        this.replacedDevice = replacedDevice;
        RestServiceGenerator.getHvacRestService(false).getInstallFlow(getHvacInstallFlowQueryParams(device.getDeviceType())).enqueue(new TadoCallback<InstallationFlow>() {
            public void onResponse(Call<InstallationFlow> call, Response<InstallationFlow> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    HvacInstallationController.this.mInstallationFlow = (InstallationFlow) response.body();
                    HvacInstallationController.this.initialStateCall = true;
                    HvacInstallationController.this.startStatePolling();
                }
            }

            public void onFailure(Call<InstallationFlow> call, Throwable t) {
                super.onFailure(call, t);
                listener.onFailure();
            }
        });
    }

    private void startStatePolling() {
        if (this.deviceStatePollingTimer != null) {
            cancelDeviceStatePollingTimer();
        }
        final Runnable runnable = new C09532();
        this.deviceStatePollingTimer = new Timer();
        final Handler handler = new Handler();
        this.deviceStatePollingTimer.schedule(new TimerTask() {
            public void run() {
                handler.post(runnable);
            }
        }, 0, 3000);
    }

    private void getDevices() {
        RestServiceGenerator.getTadoRestService().getDevices(UserConfig.getHomeId(), RestServiceGenerator.getCredentialsMap()).enqueue(new C09554());
    }

    private List<String> getStateDevicePropertyValue(State state, String type) {
        Object obj = -1;
        switch (type.hashCode()) {
            case -1359289428:
                if (type.equals(CHARACTERISTICS_CAPABILITIES)) {
                    obj = 2;
                    break;
                }
                break;
            case -1299012586:
                if (type.equals(CONNECTION_STATE_VALUE)) {
                    obj = null;
                    break;
                }
                break;
            case -223666965:
                if (type.equals(MOUNTING_STATE_VALUE)) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return Collections.singletonList(state.getConnectionStateValue());
            case 1:
                return Collections.singletonList(state.getMountingStateValue());
            case 2:
                return state.getCharacteristicsCapabilities();
            default:
                return Collections.emptyList();
        }
    }

    private void processDeviceProperty(List<SubStep> subSteps, State state) {
        for (SubStep subStep : subSteps) {
            if (ContentTypeEnum.DEVICE_PROPERTY_PANEL == subStep.getContentType()) {
                boolean found = false;
                String defaultText = "";
                StateLookupEnum defaultType = StateLookupEnum.IN_PROGRESS;
                for (StateLookup stateLookup : subStep.getStateLookups()) {
                    if (stateLookup.isDefaultState().booleanValue()) {
                        defaultText = stateLookup.getText();
                        defaultType = stateLookup.getType();
                    }
                    for (String value : stateLookup.getValues()) {
                        if (getStateDevicePropertyValue(state, subStep.getDeviceProperty()).contains(value)) {
                            this.listener.onDevicePropertyPanelValueChanged(stateLookup.getText(), stateLookup.getType());
                            found = true;
                        }
                    }
                }
                if (!found) {
                    this.listener.onDevicePropertyPanelValueChanged(defaultText, defaultType);
                }
            }
        }
    }

    private State getState(GenericHardwareDevice device) {
        if (this.mDeviceState == null) {
            this.mDeviceState = new State();
            this.mDeviceState.setLocale(Util.getSupportedLanguage());
            this.mDeviceState.setCountry(Locale.getDefault().getCountry());
        }
        if (device.getConnectionState() != null) {
            this.mDeviceState.setConnectionStateValue(device.getConnectionState().isConnected().toString());
        }
        if (device.getMountingState() != null) {
            this.mDeviceState.setMountingStateValue(device.getMountingState().getValue().name());
        }
        if (device.getCharacteristics().getCapabilities() != null) {
            this.mDeviceState.setCharacteristicsCapabilities(device.getCharacteristics().getCapabilities());
        }
        return this.mDeviceState;
    }

    private void getHvacFlowStep(InstallationFlow installationFlow, State state, final boolean isPreviousStep) {
        if (installationFlow != null && state != null) {
            RestServiceGenerator.getHvacRestService(false).getInstallFlowStep(getHvacInstallFlowStepQueryParams(installationFlow, state, isPreviousStep, this.replacedDevice)).enqueue(new TadoCallback<InstallFlowStep>() {
                public void onResponse(Call<InstallFlowStep> call, Response<InstallFlowStep> response) {
                    super.onResponse(call, response);
                    if (response.isSuccessful()) {
                        InstallFlowStep flowStep = (InstallFlowStep) response.body();
                        if (HvacStepStatus.FOUND == flowStep.getStatus()) {
                            HvacInstallationController.this.mDeviceState.setPreviousStepId(flowStep.getState().getPreviousStepId());
                            HvacInstallationController.this.mDeviceState.setStack(flowStep.getState().getStack());
                            HvacInstallationController.this.mCurentSubSteps = (List) flowStep.getStep().getSubSteps().get(0);
                            HvacInstallationController.this.listener.onHvacStep(flowStep.getStep());
                            return;
                        }
                        HvacInstallationController.this.cancelDeviceStatePollingTimer();
                        if (isPreviousStep) {
                            HvacInstallationController.this.listener.onHvacBack();
                        } else {
                            HvacInstallationController.this.listener.onHvacNext();
                        }
                    }
                }

                public void onFailure(Call<InstallFlowStep> call, Throwable t) {
                    super.onFailure(call, t);
                    HvacInstallationController.this.listener.onFailure();
                }
            });
        }
    }

    private void cancelDeviceStatePollingTimer() {
        if (this.deviceStatePollingTimer != null) {
            this.deviceStatePollingTimer.cancel();
            this.deviceStatePollingTimer.purge();
            this.deviceStatePollingTimer = null;
        }
    }

    private Map<String, String> getHvacInstallFlowStepQueryParams(InstallationFlow installationFlow, State state, boolean isPreviousStep, GenericHardwareDevice replacedDevice) {
        Map<String, String> queryParams = new HashMap();
        queryParams.put(INSTALL_FLOW_ID, installationFlow.getInstallFlowId());
        queryParams.put(LOCALE, state.getLocale());
        queryParams.put(COUNTRY, state.getCountry());
        if (state.getConnectionStateValue() != null) {
            queryParams.put(CONNECTION_STATE, state.getConnectionStateValue());
        }
        if (this.device.isValve()) {
            Object mountingStateValue;
            String str = MOUNTING_STATE;
            if (state.getMountingStateValue() != null) {
                mountingStateValue = state.getMountingStateValue();
            } else {
                mountingStateValue = MountingStateEnum.UNMOUNTED.name();
            }
            queryParams.put(str, mountingStateValue);
        }
        if (state.getPreviousStepId() != null) {
            queryParams.put(PREVIOUS_STEP_ID, state.getPreviousStepId().toString());
        }
        if (state.getStack() != null) {
            for (Integer stackId : state.getStack()) {
                queryParams.put(STACK, stackId.toString());
            }
        }
        if (isPreviousStep) {
            queryParams.put(ORDER, PREVIOUS);
        }
        queryParams.put(UPGRADE_FROM_GW01, DeviceType.GW01 == this.device.getDeviceType() ? "true" : "false");
        if (replacedDevice != null) {
            queryParams.put(REPLACEMENT_FROM, replacedDevice.getDeviceType().toString());
        }
        queryParams.put(SUPPORTS_RADIO_ENCRYPTION_ACCESS, String.valueOf(state.getCharacteristicsCapabilities().contains(HardwareDeviceCapabilities.RADIO_ENCRYPTION_KEY_ACCESS.name())));
        return queryParams;
    }

    private Map<String, String> getHvacInstallFlowQueryParams(@NonNull DeviceType deviceType) {
        Map<String, String> queryParams = new HashMap(2);
        queryParams.put(DEVICE_TYPE, deviceType.name());
        queryParams.put(ENVIRONMENT, ENVIRONMENT_PRODUCTION);
        return queryParams;
    }

    public void nextStep() {
        getHvacFlowStep(this.mInstallationFlow, this.mDeviceState, false);
    }

    public void previousStep() {
        getHvacFlowStep(this.mInstallationFlow, this.mDeviceState, true);
    }
}
