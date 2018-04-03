package com.tado.android.rest.service;

import com.tado.android.entities.ACModeRecordingCapabilities;
import com.tado.android.entities.ACSetting;
import com.tado.android.entities.ACSettingCommandSetRecording;
import com.tado.android.entities.InProgessRecordingState;
import com.tado.android.entities.TeachingMode;
import com.tado.android.entities.TeachingStep;
import com.tado.android.rest.model.hvac.BridgeReplacementInstallation;
import com.tado.android.rest.model.hvac.BridgeReplacementRequest;
import com.tado.android.rest.model.installation.AcCommandSet;
import com.tado.android.rest.model.installation.AcCommandSetId;
import com.tado.android.rest.model.installation.AcCommandSetTest;
import com.tado.android.rest.model.installation.AcInstallation;
import com.tado.android.rest.model.installation.AcInstallationInput;
import com.tado.android.rest.model.installation.AcSettingCommandSetRecordingInput;
import com.tado.android.rest.model.installation.AcSetupPhase;
import com.tado.android.rest.model.installation.AcSpecs;
import com.tado.android.rest.model.installation.AvailableCommandSets;
import com.tado.android.rest.model.installation.CandidateRating;
import com.tado.android.rest.model.installation.CommandTableUpload;
import com.tado.android.rest.model.installation.DevicePositionInput;
import com.tado.android.rest.model.installation.HardwareDevice;
import com.tado.android.rest.model.installation.Installation;
import com.tado.android.rest.model.installation.InstallationStateTransitionResult;
import com.tado.android.rest.model.installation.Manufacturer;
import com.tado.android.rest.model.installation.ModeEnum;
import com.tado.android.rest.model.installation.OnOffCandidateList;
import com.tado.android.rest.model.installation.RevisionInput;
import com.tado.android.rest.model.installation.TestOnOff;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface InstallationApi {
    @DELETE("/api/v2/homes/{home_id}/installations/{installation_id}")
    Call<Void> cancelInstallation(@Path("home_id") int i, @Path("installation_id") Integer num);

    @POST("api/v2/homes/{home_id}/installations/{installation_id}/bridge/replacement/confirmation")
    Call<BridgeReplacementInstallation> completeBridgeReplacement(@Path("home_id") int i, @Path("installation_id") int i2);

    @POST("/api/v2/homes/{home_id}/installations/{installation_id}/acSetup/acSettingRecording/confirmation")
    Call<InstallationStateTransitionResult> confirmAcSettingCommandSetRecording(@Path("home_id") int i, @Path("installation_id") Integer num, @Body Object obj);

    @POST("api/v2/homes/{home_id}/installations/{installation_id}/bridge/connection/confirmation")
    Call<BridgeReplacementInstallation> confirmBridgeConnection(@Path("home_id") int i, @Path("installation_id") int i2);

    @POST("/api/v2/homes/{home_id}/installations/{installation_id}/acSetup/clcRecording/confirmation")
    Call<InstallationStateTransitionResult> confirmCLCRecording(@Path("home_id") int i, @Path("installation_id") Integer num, @Body Object obj);

    @POST("/api/v2/homes/{home_id}/installations/{installation_id}/acSetup/commandTableUpload/confirmation")
    Call<InstallationStateTransitionResult> confirmCommandTableUpload(@Path("home_id") int i, @Path("installation_id") Integer num, @Body Object obj);

    @POST("/api/v2/homes/{home_id}/installations/{installation_id}/wirelessRemote/position/confirmation")
    Call<InstallationStateTransitionResult> confirmDevicePosition(@Path("home_id") int i, @Path("installation_id") Integer num, @Body Object obj);

    @POST("/api/v2/homes/{home_id}/installations/{installation_id}/acSetup/onOffCandidates/confirmation")
    Call<InstallationStateTransitionResult> confirmOnOffReduction(@Path("home_id") int i, @Path("installation_id") Integer num, @Body Object obj);

    @POST("/api/v2/homes/{home_id}/installations/{installation_id}/wirelessRemote/firmwareUpdate/confirmation")
    Call<InstallationStateTransitionResult> confirmWirelessRemoteFirmwareUpdate(@Path("home_id") int i, @Path("installation_id") Integer num, @Body Object obj);

    @POST("/api/v2/homes/{home_id}/installations/{installation_id}/wirelessRemote/installation/confirmation")
    Call<InstallationStateTransitionResult> confirmWirelessRemoteInstallation(@Path("home_id") int i, @Path("installation_id") Integer num);

    @POST("/api/v2/homes/{home_id}/installations/{installation_id}/acSetup/acSettingRecording")
    Call<InstallationStateTransitionResult> createAcSettingCommandSetRecording(@Path("home_id") int i, @Path("installation_id") Integer num, @Body AcSettingCommandSetRecordingInput acSettingCommandSetRecordingInput);

    @POST("/api/v2/homes/{home_id}/installations")
    Call<BridgeReplacementInstallation> createBridgeReplacementInstallation(@Path("home_id") int i, @Body BridgeReplacementRequest bridgeReplacementRequest);

    @GET("/api/v1/hvac/acCommandSets/{command_type}/{code}")
    Call<AcCommandSet> getAcCommandSet(@Path("command_type") String str, @Path("code") int i);

    @GET("/api/v2/homes/{home_id}/installations/{installation_id}")
    Call<BridgeReplacementInstallation> getBridgeInstallationState(@Path("home_id") int i, @Path("installation_id") Integer num);

    @GET("/api/v2/homes/{home_id}/installations/{installation_id}/acSetup/availableCommandSets")
    Call<AvailableCommandSets> listAvailableCommandSets(@Path("home_id") int i, @Path("installation_id") Integer num);

    @GET("/api/v2/homes/{home_id}/installations/{installation_id}/devices")
    Call<List<HardwareDevice>> listDevicesToInstall(@Path("home_id") int i, @Path("installation_id") Integer num);

    @GET("/api/v2/homes/{home_id}/installations")
    Call<List<Installation>> listInstallations(@Path("home_id") int i);

    @GET("/api/v1/hvac/acManufacturers")
    Call<List<Manufacturer>> listManufacturers();

    @GET("/api/v2/homes/{home_id}/installations/{installation_id}/acSetup/onOffCandidates")
    Call<OnOffCandidateList> listOnOffCandidates(@Path("home_id") int i, @Path("installation_id") Integer num);

    @PUT("/api/v2/homes/{home_id}/installations/{installation_id}/acSetup/onOffCandidates/{candidate_id}")
    Call<OnOffCandidateList> rateCandidate(@Path("home_id") int i, @Path("installation_id") Integer num, @Path("candidate_id") Long l, @Body CandidateRating candidateRating);

    @DELETE("/api/v2/homes/{home_id}/installations/{installation_id}/acSetup")
    Call<InstallationStateTransitionResult> restartAcSetup(@Path("home_id") int i, @Path("installation_id") Integer num);

    @PUT("/api/v2/homes/{home_id}/installations/{installation_id}/acSetup/phase")
    Call<InstallationStateTransitionResult> restartAcSetupPhase(@Path("home_id") int i, @Path("installation_id") Integer num, @Body AcSetupPhase acSetupPhase);

    @POST("/api/v1/acLearning/acSettingCommandSetRecording/{recording_id}/{ac_mode}/runs/{recording_run_index}/steps/{recording_step_index}/recordingState")
    Call<Void> resumeAcSettingCommandSetRecordingRun(@Path("recording_id") Long l, @Path("ac_mode") ModeEnum modeEnum, @Path("recording_run_index") int i, @Path("recording_step_index") int i2, @Body InProgessRecordingState inProgessRecordingState);

    @POST("/api/v2/homes/{home_id}/installations/{installation_id}/acSetup/commandSet")
    Call<InstallationStateTransitionResult> selectCommandSet(@Path("home_id") int i, @Path("installation_id") Integer num);

    @POST("/api/v2/homes/{home_id}/installations/{installation_id}/acSetup/commandSet")
    Call<InstallationStateTransitionResult> selectCommandSet(@Path("home_id") int i, @Path("installation_id") Integer num, @Body AcCommandSetId acCommandSetId);

    @GET("/api/v1/acLearning/acSettingCommandSetRecording/{recording_id}/{ac_mode}")
    Call<TeachingMode> showAcModeRecording(@Path("recording_id") Long l, @Path("ac_mode") ModeEnum modeEnum);

    @GET("/api/v1/acLearning/acSettingCommandSetRecording/{recording_id}")
    Call<ACSettingCommandSetRecording> showAcSettingCommandSetRecording(@Path("recording_id") Long l);

    @GET("/api/v1/acLearning/acSettingCommandSetRecording/{recording_id}/{ac_mode}/runs/{recording_run_index}/steps/{recording_step_index}")
    Call<TeachingStep> showAcSettingCommandSetRecordingStep(@Path("recording_id") Long l, @Path("ac_mode") ModeEnum modeEnum, @Path("recording_run_index") int i, @Path("recording_step_index") int i2);

    @GET("/api/v2/homes/{home_id}/installations/{installation_id}/acSetup/commandTableUpload")
    Call<CommandTableUpload> showCommandTableUpload(@Path("home_id") int i, @Path("installation_id") Integer num);

    @GET("/api/v2/homes/{home_id}/installations/{installation_id}")
    Call<AcInstallation> showInstallation(@Path("home_id") int i, @Path("installation_id") Integer num);

    @POST("/api/v2/homes/{home_id}/installations/{installation_id}/acSetup/acSpecs")
    Call<InstallationStateTransitionResult> specifyAc(@Path("home_id") int i, @Path("installation_id") Integer num, @Body AcSpecs acSpecs);

    @PUT("/api/v2/homes/{home_id}/installations/{installation_id}/wirelessRemote/position")
    Call<InstallationStateTransitionResult> specifyDevicePosition(@Path("home_id") int i, @Path("installation_id") Integer num, @Body DevicePositionInput devicePositionInput);

    @PUT("/api/v1/acLearning/acSettingCommandSetRecording/{recording_id}/{ac_mode}")
    Call<TeachingMode> startAcModeRecording(@Path("recording_id") Long l, @Path("ac_mode") ModeEnum modeEnum, @Body ACModeRecordingCapabilities aCModeRecordingCapabilities);

    @POST("/api/v1/acLearning/acSettingCommandSetRecording/{recording_id}/{ac_mode}/runs/{recording_run_index}/recordingState")
    Call<Void> startAcSettingCommandSetRecordingRun(@Path("recording_id") Long l, @Path("ac_mode") ModeEnum modeEnum, @Path("recording_run_index") Integer num, @Body InProgessRecordingState inProgessRecordingState);

    @POST("/api/v2/homes/{home_id}/installations")
    Call<AcInstallation> startInstallation(@Path("home_id") int i, @Body AcInstallationInput acInstallationInput);

    @POST("/api/v2/homes/{home_id}/installations/{installation_id}/acSetup/onOffCandidates/{candidate_id}/test")
    Call<Void> startOnOffCandidateTest(@Path("home_id") int i, @Path("installation_id") Integer num, @Path("candidate_id") Long l, @Body TestOnOff testOnOff);

    @DELETE("/api/v2/homes/{home_id}/installations/{installation_id}/acSetup/onOffCandidates/{candidate_id}/test")
    Call<Void> stopOnOffCandidateTest(@Path("home_id") int i, @Path("installation_id") Integer num, @Path("candidate_id") Long l);

    @POST("/api/v2/homes/{home_id}/installations/{installation_id}/acSetup/commandTest")
    Call<Void> testAcCommand(@Path("home_id") int i, @Path("installation_id") Integer num, @Body AcCommandSetTest acCommandSetTest);

    @PUT("api/v1/acLearning/acSettingCommandSetRecording/{recording_id}/recordedCommandTest")
    Call<Void> testRecordAcSettingCommand(@Path("recording_id") Long l, @Body ACSetting aCSetting);

    @PUT("/api/v2/homes/{home_id}/installations/{installation_id}/revision")
    Call<Installation> upgradeInstallation(@Path("home_id") int i, @Path("installation_id") Integer num, @Body RevisionInput revisionInput);
}
