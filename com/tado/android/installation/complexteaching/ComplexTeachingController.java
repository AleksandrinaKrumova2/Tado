package com.tado.android.installation.complexteaching;

import android.app.Activity;
import android.content.Intent;
import com.tado.C0676R;
import com.tado.android.entities.ACModeRecording;
import com.tado.android.entities.ACModeRecordingCapabilities;
import com.tado.android.entities.ACModeRecordingCapability;
import com.tado.android.entities.ACSettingCommandSetRecording;
import com.tado.android.entities.RemoteControl;
import com.tado.android.entities.TeachingMode;
import com.tado.android.entities.TeachingRun;
import com.tado.android.entities.TeachingStep;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.installation.teaching.SetACToSettingActivity;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.installation.ModeEnum;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.times.view.model.CoolingSwingStateEnum;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Response;

public class ComplexTeachingController {
    private static ComplexTeachingController complexTeachingController = null;
    private ACModeRecording[] acModeRecordings = null;
    private Map<String, ACModeRecordingCapability[]> capabilitiesToRecord;
    private ACModeRecording currentAcModeRecording;
    ACModeRecordingCapabilities currentCapabilities;
    boolean currentHasFanSpeeds;
    boolean currentHasSwing;
    boolean currentHasTemperatures;
    private int currentRunIndex;
    private int currentStepIndex;
    private TeachingMode currentTeachingMode;
    private boolean firstTeachingMode = true;
    private Map<String, ACModeRecordingCapability[]> recordedCapabilities;
    private Map<String, ACModeRecordingCapabilities> recordingCapabilitiesMap;
    private int recordingId;
    private String temperatureUnit;

    private ComplexTeachingController() {
    }

    public static ComplexTeachingController getComplexTeachingController() {
        if (complexTeachingController == null) {
            synchronized (ComplexTeachingController.class) {
                if (complexTeachingController == null) {
                    complexTeachingController = new ComplexTeachingController();
                }
            }
        }
        return complexTeachingController;
    }

    private void goToNextCLCCapabilitiesScreen(Activity context) {
        if (this.currentCapabilities == null) {
            return;
        }
        if (!this.currentHasTemperatures || this.currentCapabilities.getTemperatures() != null) {
            startRecording(context);
        } else if (ModeEnum.COOL.toString().equalsIgnoreCase(this.currentAcModeRecording.getMode())) {
            Intent intent = new Intent(context, ChooseMinTempForModeActivity.class);
            intent.putExtra(ChooseMinTempForModeActivity.MAX_TEMP_EXTRA, getTemperatureUnit().equalsIgnoreCase(RemoteControl.UNIT_CELSIUS) ? 31 : 89);
            InstallationProcessController.startActivity(context, intent, false);
        } else if (ModeEnum.HEAT.toString().equalsIgnoreCase(this.currentAcModeRecording.getMode())) {
            InstallationProcessController.startActivity(context, new Intent(context, ChooseMaxTempForModeActivity.class), false);
        }
    }

    public void goToNextCapabilitiesScreen(Activity context) {
        goToNextCapabilitiesScreen(context, false);
    }

    public void goToNextCapabilitiesScreen(Activity context, boolean isCLCRecording) {
        if (isCLCRecording) {
            goToNextCLCCapabilitiesScreen(context);
        } else if (this.currentCapabilities == null) {
        } else {
            if (this.currentHasTemperatures && this.currentCapabilities.getTemperatures() == null) {
                Intent intent = new Intent(context, ChooseMaxTempForModeActivity.class);
                String mode = getCurrentAcModeRecording().getMode();
                intent.putExtra("title", context.getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_maxTemperature_title, new Object[]{mode}));
                intent.putExtra("description", context.getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_maxTemperature_message, new Object[]{mode}));
                intent.putExtra("button", context.getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_maxTemperature_confirmButton));
                intent.putExtra("hint", context.getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_maxTemperature_highestTemperatureField));
                InstallationProcessController.startActivity(context, intent, false);
            } else if (this.currentHasFanSpeeds && this.currentCapabilities.getFanSpeeds() == null) {
                InstallationProcessController.startActivity(context, ChooseAutoFanSpeedForModeActivity.class, false);
            } else {
                startRecording(context);
            }
        }
    }

    private void startRecording(final Activity tmpContext) {
        RestServiceGenerator.getTadoInstallationRestService().startAcModeRecording(Long.valueOf((long) this.recordingId), ModeEnum.valueOf(this.currentAcModeRecording.getMode()), this.currentCapabilities).enqueue(new TadoCallback<TeachingMode>() {
            public void onResponse(Call<TeachingMode> call, Response<TeachingMode> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    TeachingMode teachingMode = (TeachingMode) response.body();
                    ComplexTeachingController.getComplexTeachingController().setCurrentTeachingMode(teachingMode);
                    if (teachingMode.getRuns().length > 1) {
                        InstallationProcessController.startActivity(tmpContext, TeachingRunsOverviewActivity.class, false);
                    } else {
                        InstallationProcessController.startActivity(tmpContext, SetACToSettingActivity.class, false);
                    }
                } else if (this.serverError != null) {
                    InstallationProcessController.handleError(tmpContext, this.serverError, (Call) call, response.code());
                } else {
                    InstallationProcessController.showConnectionErrorRetrofit(tmpContext, call, this);
                }
            }

            public void onFailure(Call<TeachingMode> call, Throwable t) {
                super.onFailure(call, t);
                InstallationProcessController.showConnectionErrorRetrofit(tmpContext, call, this);
            }
        });
    }

    private int getFirstNotFinishedRecording() {
        for (int i = 0; i < this.acModeRecordings.length; i++) {
            if (this.acModeRecordings[i].getRecordingState().compareTo("FINISHED") != 0) {
                return i;
            }
        }
        return -1;
    }

    public int determineCurrentRun() {
        TeachingRun[] runs = this.currentTeachingMode.getRuns();
        for (int i = 0; i < runs.length; i++) {
            if (runs[i].getRecordingState().compareTo("FINISHED") != 0) {
                return i;
            }
        }
        return 0;
    }

    public int determineCurrentStep(int currentRun) {
        TeachingStep[] steps = this.currentTeachingMode.getRuns()[currentRun].getSteps();
        int j = 0;
        while (j < steps.length) {
            if (steps[j].getRecordingState().compareTo("FINISHED") == 0) {
                j++;
            } else if (j == 1) {
                return 0;
            } else {
                return j;
            }
        }
        return 0;
    }

    private ACModeRecording getAcModeRecordingForModeString(String mode) {
        for (ACModeRecording acModeRecording : this.acModeRecordings) {
            if (acModeRecording.getMode().compareTo(mode) == 0) {
                return acModeRecording;
            }
        }
        return null;
    }

    public void initalize(ACSettingCommandSetRecording acSettingCommandSetRecording) {
        setRecordingId(acSettingCommandSetRecording.getId());
        setAcModeRecordings(acSettingCommandSetRecording.getAcModeRecordings());
        setTemperatureUnit(acSettingCommandSetRecording.getTemperatureUnit());
        setRecordedCapabilities(acSettingCommandSetRecording.getRecordedCapabilities());
        this.capabilitiesToRecord = acSettingCommandSetRecording.getCapabilitiesToRecord();
    }

    public void initializeCLCRecording(ACSettingCommandSetRecording acSettingCommandSetRecording, com.tado.android.times.view.model.ModeEnum modeEnum, boolean resetAllModes) {
        initalize(acSettingCommandSetRecording);
        setCurrentAcModeRecording(getACModeRecordingForModeString(modeEnum.toString()));
        restartMode();
        setCurrentHasTemperatures(true);
        setCurrentHasFanSpeeds(false);
        setCurrentHasSwing(false);
    }

    public void setCurrentHasSwing(boolean currentHasSwing) {
        this.currentHasSwing = currentHasSwing;
        if (currentHasSwing) {
            this.currentCapabilities.setSwings(new String[]{CoolingSwingStateEnum.getCoolingSwingString(true), CoolingSwingStateEnum.getCoolingSwingString(false)});
            return;
        }
        this.currentCapabilities.setSwings(new String[0]);
    }

    public void setCurrentTeachingMode(TeachingMode currentTeachingMode) {
        this.currentTeachingMode = currentTeachingMode;
        if (currentTeachingMode.getRecordingState().compareTo("NOT_STARTED") == 0) {
            this.currentRunIndex = 0;
            this.currentStepIndex = 0;
            return;
        }
        this.currentRunIndex = determineCurrentRun();
        this.currentStepIndex = determineCurrentStep(this.currentRunIndex);
    }

    public int getRecordingId() {
        return this.recordingId;
    }

    public void setRecordingId(int recordingId) {
        this.recordingId = recordingId;
    }

    public int getCurrentStepIndex() {
        return this.currentStepIndex;
    }

    public void setCurrentStepIndex(int currentStepIndex) {
        this.currentStepIndex = currentStepIndex;
    }

    public ACModeRecording[] getAcModeRecordings() {
        return this.acModeRecordings;
    }

    public static void setComplexTeachingController(ComplexTeachingController complexTeachingController) {
        complexTeachingController = complexTeachingController;
    }

    public int getCurrentRunIndex() {
        return this.currentRunIndex;
    }

    public void setCurrentRunIndex(int currentRunIndex) {
        this.currentRunIndex = currentRunIndex;
    }

    public boolean isCurrentHasTemperatures() {
        return this.currentHasTemperatures;
    }

    public void setCurrentHasTemperatures(boolean currentHasTemperatures) {
        this.currentHasTemperatures = currentHasTemperatures;
    }

    public boolean isCurrentHasFanSpeeds() {
        return this.currentHasFanSpeeds;
    }

    public void setCurrentHasFanSpeeds(boolean currentHasFanSpeeds) {
        this.currentHasFanSpeeds = currentHasFanSpeeds;
    }

    public boolean isCurrentHasSwing() {
        return this.currentHasSwing;
    }

    public ACModeRecordingCapabilities getCurrentCapabilities() {
        return this.currentCapabilities;
    }

    public void setCurrentCapabilities(ACModeRecordingCapabilities currentCapabilities) {
        this.currentCapabilities = currentCapabilities;
    }

    public TeachingMode getCurrentTeachingMode() {
        return this.currentTeachingMode;
    }

    public ACModeRecording getCurrentAcModeRecording() {
        return this.currentAcModeRecording;
    }

    public void setCurrentAcModeRecording(ACModeRecording currentAcModeRecording) {
        this.currentAcModeRecording = currentAcModeRecording;
        if (currentAcModeRecording.getRecordingState().compareTo("NOT_STARTED") == 0) {
            this.currentCapabilities = new ACModeRecordingCapabilities();
        }
    }

    public void setAcModeRecordings(ACModeRecording[] acModeRecordings) {
        this.acModeRecordings = acModeRecordings;
    }

    public String getTemperatureUnit() {
        return this.temperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public Map<String, ACModeRecordingCapability[]> getRecordedCapabilities() {
        return this.recordedCapabilities;
    }

    public void setRecordedCapabilities(Map<String, ACModeRecordingCapability[]> recordedCapabilities) {
        this.recordedCapabilities = recordedCapabilities;
    }

    public void restartMode() {
        this.currentCapabilities = new ACModeRecordingCapabilities();
        if (this.currentTeachingMode != null) {
            this.currentTeachingMode.setRecordingState("NOT_STARTED");
        }
        if (this.currentAcModeRecording != null) {
            this.currentAcModeRecording.setRecordingState("NOT_STARTED");
        }
    }

    public Map<String, ACModeRecordingCapabilities> getRecordingCapabilitiesMap() {
        return this.recordingCapabilitiesMap;
    }

    public void setRecordingCapabilitiesMap(Map<String, ACModeRecordingCapabilities> recordingCapabilitiesMap) {
        this.recordingCapabilitiesMap = recordingCapabilitiesMap;
    }

    public Map<String, ACModeRecordingCapability[]> getCapabilitiesToRecord() {
        return this.capabilitiesToRecord;
    }

    public void setCapabilitiesToRecord(Map<String, ACModeRecordingCapability[]> capabilitiesToRecord) {
        this.capabilitiesToRecord = capabilitiesToRecord;
    }

    public static ACModeRecording getACModeRecordingForModeString(String mode) {
        for (ACModeRecording acModeRecording : getComplexTeachingController().getAcModeRecordings()) {
            if (acModeRecording.getMode().equalsIgnoreCase(mode)) {
                return acModeRecording;
            }
        }
        return null;
    }
}
