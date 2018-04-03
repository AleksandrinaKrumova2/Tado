package com.tado.android.installation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.crashlytics.android.Crashlytics;
import com.google.gson.GsonBuilder;
import com.tado.C0676R;
import com.tado.android.ManageUsersActivity;
import com.tado.android.app.TadoApplication;
import com.tado.android.client.APICall;
import com.tado.android.client.APICallListener;
import com.tado.android.controllers.NavigationController;
import com.tado.android.controllers.ZoneController;
import com.tado.android.dialogs.AlertChoiceDialogListener;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.dialogs.AlertWarningDialogListener;
import com.tado.android.entities.InstallationInfo;
import com.tado.android.entities.InstallationStatus;
import com.tado.android.entities.RecordCommandHelper;
import com.tado.android.entities.ServerError;
import com.tado.android.installation.InstallationProcessZoneStatus.InstallationProcessZoneStatusEnum;
import com.tado.android.installation.complexteaching.ComplexTeachingController;
import com.tado.android.installation.connectwifi.DeviceConnectionSuccessfulActivity;
import com.tado.android.mvp.model.HomeWifiLocalDataSource;
import com.tado.android.mvp.model.HomeWifiPreferencesHelper;
import com.tado.android.mvp.model.HomeWifiRepository;
import com.tado.android.requests.Request;
import com.tado.android.rest.callback.RetryCallback;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.callback.presenters.GeneralErrorAlertPresenter;
import com.tado.android.rest.model.DeviceInput;
import com.tado.android.rest.model.DevicesResponseWrapper;
import com.tado.android.rest.model.HomeInfo;
import com.tado.android.rest.model.HomeLocation;
import com.tado.android.rest.model.InstallationsResponseWrapper;
import com.tado.android.rest.model.MobileDevice;
import com.tado.android.rest.model.ResponseError;
import com.tado.android.rest.model.ResponseWrapper;
import com.tado.android.rest.model.User;
import com.tado.android.rest.model.UserHome;
import com.tado.android.rest.model.Zone;
import com.tado.android.rest.model.ZoneListWrapper;
import com.tado.android.rest.model.ZonesResponseWrapper;
import com.tado.android.rest.model.hvac.BridgeReplacementInstallation;
import com.tado.android.rest.model.hvac.BridgeReplacementRequest;
import com.tado.android.rest.model.installation.AcCommandSet;
import com.tado.android.rest.model.installation.AcInstallation;
import com.tado.android.rest.model.installation.AcInstallation.StateEnum;
import com.tado.android.rest.model.installation.AcSpecs;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.rest.model.installation.HardwareDevice;
import com.tado.android.rest.model.installation.Installation;
import com.tado.android.rest.model.installation.Installation.TypeEnum;
import com.tado.android.rest.model.installation.InstallationStateTransitionResult;
import com.tado.android.rest.model.installation.OnOffCandidate;
import com.tado.android.rest.model.installation.RevisionInput;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.Constants;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.TimeUtils;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.joda.time.DateTime;
import retrofit2.Call;
import retrofit2.Response;

public class InstallationProcessController {
    private static final String LOG_TAG = "InstallationProcessCtrl";
    private static InstallationProcessController mInstallationProcessController;
    private AcSpecs acSpecs = null;
    private List<OnOffCandidate> confirmedCommandSetCandidateList;
    private List<GenericHardwareDevice> devices;
    private InstallationInfo installationInfo = new InstallationInfo();
    private List<Installation> installations;
    private WeakReference<Activity> mCallingActivity = new WeakReference(null);
    private AcCommandSet mSelectedAcCommandSet;
    private boolean mShowLoadingDialog = true;
    private RecordCommandHelper[] recordCommandHelpers;
    private Set<String> untestedKeys;
    private List<Zone> zones;

    class C08142 extends TadoCallback<List<MobileDevice>> {
        C08142() {
        }

        public void onResponse(Call<List<MobileDevice>> call, Response<List<MobileDevice>> response) {
            super.onResponse(call, response);
            Activity activity = (Activity) InstallationProcessController.this.mCallingActivity.get();
            if (activity != null) {
                if (response.isSuccessful()) {
                    Intent openManageUsers = new Intent(activity, ManageUsersActivity.class);
                    openManageUsers.putParcelableArrayListExtra("usersArray", (ArrayList) response.body());
                    Crashlytics.log("Opening activity ManageUsersActivity");
                    InstallationProcessController.startActivity(activity, openManageUsers, true);
                    return;
                }
                InstallationProcessController.showConnectionError(activity, null, null);
            }
        }

        public void onFailure(Call<List<MobileDevice>> call, Throwable t) {
            super.onFailure(call, t);
            if (((Activity) InstallationProcessController.this.mCallingActivity.get()) != null) {
                InstallationProcessController.showConnectionError((Activity) InstallationProcessController.this.mCallingActivity.get(), null, null);
            }
        }
    }

    class C08229 extends TadoCallback<User> {
        C08229() {
        }

        public void onResponse(Call<User> call, Response<User> response) {
            super.onResponse(call, response);
            if (response.isSuccessful()) {
                User user = (User) response.body();
                if (!user.getHomes().isEmpty()) {
                    UserHome userHome = (UserHome) user.getHomes().get(0);
                    UserConfig.setHomeId(userHome.getId());
                    UserConfig.setHomeName(userHome.getName());
                    if (InstallationProcessController.this.mCallingActivity.get() != null) {
                        if (HomeWifiRepository.INSTANCE.getLocalDataSource() == null) {
                            HomeWifiRepository.INSTANCE.setLocalDataSource(new HomeWifiLocalDataSource(new HomeWifiPreferencesHelper((Context) InstallationProcessController.this.mCallingActivity.get(), userHome.getId())));
                        }
                        HomeWifiRepository.INSTANCE.migrateLegacyHomeWifiConfiguration(UserConfig.getLegacyConfiguration());
                    }
                }
                InstallationProcessController.this.getHomeInfo();
                return;
            }
            Util.clearUserData();
            InstallationProcessController.this.detectStatus((Activity) InstallationProcessController.this.mCallingActivity.get(), InstallationProcessController.this.mShowLoadingDialog);
        }

        public void onFailure(Call<User> call, Throwable t) {
            super.onFailure(call, t);
            if (InstallationProcessController.this.mCallingActivity.get() != null) {
                InstallationProcessController.showRetryError((Activity) InstallationProcessController.this.mCallingActivity.get(), this, call);
            }
        }
    }

    public interface UiCallback {
        void onRequestFailed();

        void onRequestFinished(Response response, ServerError serverError);
    }

    private InstallationProcessController() {
    }

    public static InstallationProcessController getInstallationProcessController() {
        if (mInstallationProcessController == null) {
            synchronized (InstallationProcessController.class) {
                if (mInstallationProcessController == null) {
                    mInstallationProcessController = new InstallationProcessController();
                }
            }
        }
        return mInstallationProcessController;
    }

    private Installation getInstallation() {
        return getInstallationInfo().getCurrentInstallation();
    }

    public InstallationProcessController setCallingActivity(Activity callingActivity) {
        this.mCallingActivity = new WeakReference(callingActivity);
        return this;
    }

    public int getInstallationId() {
        return getInstallation().getId().intValue();
    }

    public void detectStatus(Activity activity, boolean showLoadingDialog) {
        this.mShowLoadingDialog = showLoadingDialog;
        detectStatus(activity);
    }

    public void detectStatus(Activity activity) {
        if (activity != null) {
            this.mCallingActivity = new WeakReference(activity);
            if (UserConfig.getUsername().isEmpty() || UserConfig.getPassword().isEmpty()) {
                goToScreenForInstallationStatus((Activity) this.mCallingActivity.get(), Constants.INSTALLATION_STATUS_NO_USER);
            } else {
                detectInstallationStatus();
            }
        }
    }

    public void goToScreenForInstallationProcessStatus(Activity caller, @NonNull AcInstallation installationStatus) {
        if (caller != null) {
            if (installationStatus.getState() == StateEnum.COMPLETED) {
                removeInstallation(installationStatus.getId().intValue());
            } else {
                setInstallation(installationStatus);
            }
            Snitcher.start().log(LOG_TAG, "goToScreenForInstallationProcessStatus: %s", installationStatus.getState().toString());
            this.mCallingActivity = new WeakReference(caller);
            loadInstallationInformationState(installationStatus);
            if (installationStatus.getState() == StateEnum.CONNECT_WIFI) {
                RestServiceGenerator.getTadoInstallationRestService().listDevicesToInstall(UserConfig.getHomeId(), installationStatus.getId()).enqueue(new RetryCallback<List<HardwareDevice>>(new GeneralErrorAlertPresenter((Context) this.mCallingActivity.get())) {
                    public void onResponse(Call<List<HardwareDevice>> call, Response<List<HardwareDevice>> response) {
                        super.onResponse(call, response);
                        if (response.isSuccessful()) {
                            HardwareDevice device = (HardwareDevice) ((List) response.body()).get(0);
                            UserConfig.setSerialNo(device.getShortSerialNo());
                            DateTime thresholdTimestamp = DateTime.now().minusMinutes(2);
                            if (device.getConnectionState() != null && device.getConnectionState().isConnected().booleanValue() && device.getConnectionState().getTimestamp().after(thresholdTimestamp.toDate())) {
                                InstallationProcessController.this.goToScreenForDeviceConnected();
                            } else {
                                InstallationProcessController.this.goToScreenForInstallationProcessStatus((Activity) InstallationProcessController.this.mCallingActivity.get(), StateEnum.CONNECT_WIFI);
                            }
                        }
                    }
                });
            } else {
                goToScreenForInstallationProcessStatus((Activity) this.mCallingActivity.get(), installationStatus.getState());
            }
        }
    }

    private void detectInstallationStatus() {
        getMe();
    }

    private void goToScreenForCompletedInstallation() {
        getZoneCapabilities();
        if (UserConfig.getNickname() != null) {
            NavigationController.showMainScreen((Context) this.mCallingActivity.get());
        } else {
            retrieveMobileDevices(this.installationInfo);
        }
    }

    private void retrieveMobileDevices(InstallationInfo installationInfo) {
        RestServiceGenerator.getTadoRestService().getMobileDevices(UserConfig.getHomeId(), RestServiceGenerator.getCredentialsMap()).enqueue(new C08142());
    }

    private void getZoneCapabilities() {
        ZoneController.INSTANCE.callZoneCapabilities(UserConfig.getCurrentZone().intValue());
    }

    private void goToScreenForInstallationStatus(@Nullable Activity activity, String status) {
        if (activity != null) {
            this.mCallingActivity = new WeakReference(activity);
            Snitcher.start().log(LOG_TAG, "goToScreenForInstallationStatus: %s", status);
            Class clazz = (Class) Constants.ACTIVITY_FOR_INSTALLATION_STATUS.get(status);
            if (clazz != null) {
                Crashlytics.log(String.format("Opening activity: %s", new Object[]{clazz.getSimpleName()}));
                startActivity(activity, clazz, true);
                if (status.equals(Constants.INSTALLATION_STATUS_MAIN_SCREEN)) {
                    activity.overridePendingTransition(17432576, 17432577);
                    return;
                }
                return;
            }
            Crashlytics.log(6, LOG_TAG, String.format("No Activity defined for status '%s'", new Object[]{status}));
        }
    }

    public void goToScreenForInstallationProcessStatus(Activity activity, String status) {
        if (activity != null) {
            this.mCallingActivity = new WeakReference(activity);
            Snitcher.start().log(LOG_TAG, "goToScreenForInstallationProcessStatus: %s", status);
            Class clazz = (Class) Constants.ACTIVITY_FOR_PROCESS_STATUS.get(status);
            if (clazz != null) {
                startActivity(activity, clazz, true);
            } else {
                Crashlytics.log(2, LOG_TAG, "No Activity definied for status '" + status + "'");
            }
        }
    }

    private void goToScreenForInstallationProcessStatus(@Nullable Activity activity, StateEnum status) {
        if (activity != null) {
            this.mCallingActivity = new WeakReference(activity);
            Snitcher.start().log(LOG_TAG, "goToScreenForInstallationProcessStatus: %s", status.toString());
            Class clazz = (Class) Constants.ACTIVITY_FOR_PROCESS_STATUSENUM.get(status);
            if (clazz != null) {
                startActivity(activity, clazz, true);
                return;
            }
            Crashlytics.log(2, LOG_TAG, "No Activity definied for status '" + status + "'");
            goToScreenForInstallationProcessStatus(activity, status);
        }
    }

    public void goToScreenForInstallationProcessStatus(@Nullable final Activity activity) {
        if (activity != null) {
            this.mCallingActivity = new WeakReference(activity);
            RestServiceGenerator.getTadoInstallationRestService().showInstallation(UserConfig.getHomeId(), getInstallation().getId()).enqueue(new TadoCallback<AcInstallation>() {
                public void onResponse(Call<AcInstallation> call, Response<AcInstallation> response) {
                    super.onResponse(call, response);
                    if (response.isSuccessful()) {
                        InstallationProcessController.this.goToScreenForInstallationProcessStatus((Activity) InstallationProcessController.this.mCallingActivity.get(), (AcInstallation) response.body());
                    } else {
                        InstallationProcessController.showConnectionErrorRetrofit(activity, call, this);
                    }
                }

                public void onFailure(Call<AcInstallation> call, Throwable t) {
                    super.onFailure(call, t);
                }
            });
        }
    }

    public void goToScreenForDeviceConnected() {
        if (this.mCallingActivity.get() != null) {
            startActivity((Activity) this.mCallingActivity.get(), DeviceConnectionSuccessfulActivity.class, false);
        }
    }

    public static void startActivity(@Nullable Activity context, Class<?> clazz, boolean clearBackStack) {
        if (context != null) {
            startActivity(context, new Intent(context, clazz), clearBackStack);
        }
    }

    public static void startActivity(Activity context, Intent intent, boolean clearBackStack) {
        if (context.getCurrentFocus() != null) {
            Util.hideKeyboard(context, context.getCurrentFocus());
        }
        if (clearBackStack) {
            intent.addFlags(268468224);
        }
        context.startActivity(intent);
        if (clearBackStack) {
            context.finish();
        }
    }

    @Deprecated
    public static void handleError(@Nullable Activity context, ServerError error, Request request, com.tado.android.responses.Response response) {
        handleError(context, error, request.getHttpMethod(), response.getUrl(), response.getStatusCode());
    }

    public static void handleError(@Nullable Activity context, ServerError error, Call<?> call, int code) {
        handleError(context, error, call.request().method(), call.request().url().url().toExternalForm(), code);
    }

    public static void handleError(@Nullable final Activity context, final ServerError error, String httpMethod, String url, int code) {
        if (context != null) {
            String code2 = error.getCode();
            int i = -1;
            switch (code2.hashCode()) {
                case -1214888878:
                    if (code2.equals(Constants.ILLEGAL_INSTALLATION_PROCESS_STATE)) {
                        i = 1;
                        break;
                    }
                    break;
                case 276633166:
                    if (code2.equals(Constants.REVISION_NOT_SUPPORTED)) {
                        i = 0;
                        break;
                    }
                    break;
            }
            switch (i) {
                case 0:
                    NavigationController.showForceUpdateScreen(context);
                    return;
                case 1:
                    String expectedState;
                    InstallationStatus installationStatus = error.getInstallationProcess();
                    if (installationStatus != null) {
                        expectedState = installationStatus.getState().replace(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR, " ");
                    } else {
                        expectedState = "START";
                    }
                    AlertDialogs.showWarning(context.getString(C0676R.string.installation_errors_wrongInstallationStep_title), context.getString(C0676R.string.installation_errors_wrongInstallationStep_message, new Object[]{expectedState}), context.getString(C0676R.string.installation_errors_wrongInstallationStep_confirmButton, new Object[]{expectedState}), context, new AlertWarningDialogListener() {
                        public void OnOKClicked() {
                            InstallationStatus installationStatus = error.getInstallationProcess();
                            if (installationStatus == null || installationStatus.getState().isEmpty()) {
                                InstallationProcessController.getInstallationProcessController().detectStatus(context);
                            } else {
                                InstallationProcessController.getInstallationProcessController().goToScreenForInstallationProcessStatus(context, installationStatus.getState());
                            }
                        }
                    });
                    return;
                default:
                    showGenericError(context, error.getCode() + " - " + error.getTitle(), httpMethod, url, code);
                    return;
            }
        }
    }

    private void showUpgradeInstallationProcessScreen(@Nullable final Activity context, final int installationId) {
        AlertDialogs.showChoiceAlert(context.getString(C0676R.string.installation_error_alert_title), context.getString(C0676R.string.installation_errors_installationIncompatible_message), context.getString(C0676R.string.installation_errors_installationIncompatible_confirmButton), context.getString(C0676R.string.installation_errors_installationIncompatible_cancelButton), context, new AlertChoiceDialogListener() {

            class C08171 extends TadoCallback<Installation> {
                C08171() {
                }

                public void onResponse(Call<Installation> call, Response<Installation> response) {
                    super.onResponse(call, response);
                    AcInstallation acInstallation = (AcInstallation) response.body();
                    if (response.isSuccessful()) {
                        InstallationProcessController.getInstallationProcessController().goToScreenForInstallationProcessStatus(context, acInstallation.getState());
                    } else if (this.serverError != null) {
                        InstallationProcessController.handleError((Activity) InstallationProcessController.this.mCallingActivity.get(), this.serverError, (Call) call, response.code());
                    } else {
                        InstallationProcessController.showConnectionErrorRetrofit((Activity) InstallationProcessController.this.mCallingActivity.get(), call, this);
                    }
                }

                public void onFailure(Call<Installation> call, Throwable t) {
                    super.onFailure(call, t);
                    if (InstallationProcessController.this.mCallingActivity.get() != null) {
                        InstallationProcessController.showConnectionErrorRetrofit((Activity) InstallationProcessController.this.mCallingActivity.get(), call, this);
                    }
                }
            }

            public void OnOKClicked() {
                RevisionInput revisionInput = new RevisionInput();
                revisionInput.setRevision(Integer.valueOf(6));
                RestServiceGenerator.getTadoInstallationRestService().upgradeInstallation(UserConfig.getHomeId(), Integer.valueOf(installationId), revisionInput).enqueue(new C08171());
            }

            public void OnCancelClicked() {
            }
        });
    }

    private void executeAPICall(Request request, @NonNull Activity context, APICallListener listener) {
        APICall ac = new APICall(request, context);
        ac.setmShowLoaderDialog(this.mShowLoadingDialog);
        ac.setAPICallListener(listener);
        ac.execute(new Void[0]);
    }

    public static void showRetryError(final Activity activity, final TadoCallback callback, final Call call) {
        AlertDialogs.showCancelRetryAlert(activity.getString(C0676R.string.errors_noInternetConnection_title), activity.getString(C0676R.string.errors_noInternetConnection_message), activity.getString(C0676R.string.errors_noInternetConnection_confirmButton), activity.getString(C0676R.string.errors_noInternetConnection_cancelButton), activity, new AlertChoiceDialogListener() {
            public void OnOKClicked() {
                callback.retry(call);
            }

            public void OnCancelClicked() {
                activity.finish();
            }
        });
    }

    public static void showRetryError(Activity activity, AlertChoiceDialogListener listener) {
        AlertDialogs.showCancelRetryAlert(activity.getString(C0676R.string.errors_noInternetConnection_title), activity.getString(C0676R.string.errors_noInternetConnection_message), activity.getString(C0676R.string.errors_noInternetConnection_confirmButton), activity.getString(C0676R.string.errors_noInternetConnection_cancelButton), activity, listener);
    }

    public static void showGenericError(@Nullable Activity context, String responseString, String requestHttpMethod, String url, String statusCode) {
        if (context != null) {
            HashMap<Integer, String> content = new HashMap();
            content.put(Integer.valueOf(C0676R.id.generic_error_text), context.getString(C0676R.string.errors_somethingWentWrong_message));
            content.put(Integer.valueOf(C0676R.id.generic_error_date), String.format(context.getString(C0676R.string.errors_somethingWentWrong_dateMessage), new Object[]{TimeUtils.GetUTCdatetimeAsString()}));
            content.put(Integer.valueOf(C0676R.id.generic_error_url), String.format(context.getString(C0676R.string.errors_somethingWentWrong_urlMessage), new Object[]{urlWithoutPassword(url) + " (" + requestHttpMethod + ")"}));
            content.put(Integer.valueOf(C0676R.id.generic_error_status), String.format(context.getString(C0676R.string.errors_somethingWentWrong_statusMessage), new Object[]{statusCode}));
            content.put(Integer.valueOf(C0676R.id.generic_error_response_data), String.format(context.getString(C0676R.string.errors_somethingWentWrong_responseMessage), new Object[]{responseString}));
            AlertDialogs.showGenericError(context.getString(C0676R.string.errors_somethingWentWrong_title), content, context.getString(C0676R.string.ok), context, null);
        }
    }

    public static void showGenericError(@Nullable Activity context, String responseString, Request request, com.tado.android.responses.Response response) {
        showGenericError(context, responseString, request.getHttpMethod(), response.getUrl(), String.valueOf(response.getStatusCode()));
    }

    public static void showGenericError(@Nullable Activity context, String httpMethod, String responseString, String url, int code) {
        showGenericError(context, responseString, httpMethod, url, String.valueOf(code));
    }

    public static void showConnectionError(@Nullable final Activity context, final Request request, final APICallListener listener) {
        if (context != null && !context.isFinishing()) {
            AlertDialogs.showCancelRetryAlert(context.getString(C0676R.string.installation_error_alert_title), context.getString(C0676R.string.could_not_connect_to_server), context.getString(C0676R.string.alert_retry), context.getString(C0676R.string.alert_cancel), context, new AlertChoiceDialogListener() {
                public void OnOKClicked() {
                    if (request != null) {
                        InstallationProcessController.getInstallationProcessController().executeAPICall(request, context, listener);
                    }
                }

                public void OnCancelClicked() {
                }
            });
        }
    }

    public static void showConnectionErrorRetrofit(@Nullable Activity context, final Call<?> request, final TadoCallback listener) {
        if (context != null && !context.isFinishing()) {
            AlertDialogs.showCancelRetryAlert(context.getString(C0676R.string.installation_error_alert_title), context.getString(C0676R.string.could_not_connect_to_server), context.getString(C0676R.string.alert_retry), context.getString(C0676R.string.alert_cancel), context, new AlertChoiceDialogListener() {
                public void OnOKClicked() {
                    if (request != null) {
                        request.clone().enqueue(listener);
                    }
                }

                public void OnCancelClicked() {
                }
            });
        }
    }

    public static String urlWithoutPassword(String url) {
        int start = url.indexOf("&password=");
        int end = url.indexOf("&", start + 1);
        if (start < 0) {
            return url;
        }
        String response = url.substring(0, start);
        if (end > 0) {
            response = response + url.substring(end);
        }
        return response;
    }

    public AcSpecs getAcSpecs() {
        if (this.acSpecs == null) {
            this.acSpecs = new AcSpecs();
        }
        return this.acSpecs;
    }

    public void setAcSpecs(AcSpecs acSpecs) {
        this.acSpecs = acSpecs;
    }

    public RecordCommandHelper[] getRecordCommandHelpers() {
        return this.recordCommandHelpers;
    }

    public void setRecordCommandHelpers(RecordCommandHelper[] recordCommandHelpers) {
        this.recordCommandHelpers = recordCommandHelpers;
    }

    public List<OnOffCandidate> getConfirmedCommandSetCandidateList() {
        return this.confirmedCommandSetCandidateList;
    }

    public void setConfirmedCommandSetCandidateList(List<OnOffCandidate> confirmedCommandSetCandidateList) {
        this.confirmedCommandSetCandidateList = confirmedCommandSetCandidateList;
    }

    public AcCommandSet getSelectedAcCommandSet() {
        return this.mSelectedAcCommandSet;
    }

    public void setSelectedAcCommandSet(AcCommandSet selectedAcCommandSet) {
        this.mSelectedAcCommandSet = selectedAcCommandSet;
    }

    public Set<String> getUntestedKeys() {
        return this.untestedKeys;
    }

    public void addUntestedKey(String key) {
        if (this.untestedKeys == null) {
            this.untestedKeys = new HashSet();
        }
        this.untestedKeys.add(key);
    }

    public void removeUntestedKey(String key) {
        if (this.untestedKeys != null) {
            this.untestedKeys.remove(key);
        }
    }

    public void getMe() {
        RestServiceGenerator.getTadoRestService().getMe(RestServiceGenerator.getCredentialsMap()).enqueue(new C08229());
    }

    private void getHomeInfo() {
        if (!UserConfig.getUsername().isEmpty()) {
            RestServiceGenerator.getTadoRestService().getHome(UserConfig.getHomeId(), RestServiceGenerator.getCredentialsMap()).enqueue(new TadoCallback<HomeInfo>() {

                class C08121 implements AlertChoiceDialogListener {
                    C08121() {
                    }

                    public void OnOKClicked() {
                        InstallationProcessController.this.ensureInitInstallationInfo(true, false, this);
                    }

                    public void OnCancelClicked() {
                        if (InstallationProcessController.this.mCallingActivity.get() != null) {
                            ((Activity) InstallationProcessController.this.mCallingActivity.get()).finish();
                        }
                    }
                }

                public void onResponse(Call<HomeInfo> call, Response<HomeInfo> response) {
                    super.onResponse(call, response);
                    if (response.isSuccessful()) {
                        HomeInfo homeInfo = (HomeInfo) response.body();
                        UserConfig.setPartner(homeInfo.getPartner());
                        UserConfig.setHomeTimezone(homeInfo.getDateTimeZone());
                        UserConfig.setTemperatureUnit(homeInfo.getTemperatureUnit());
                        UserConfig.setChristmasModeEnabled(Boolean.valueOf(homeInfo.isChristmasModeEnabled()));
                        UserConfig.setHomeLocation(new HomeLocation(homeInfo.getGeolocation(), homeInfo.getAwayRadiusInMeters()));
                        UserConfig.setHomeCreation(homeInfo.getDateCreated().getTime());
                        UserConfig.setLicense(homeInfo.getLicense());
                        if (homeInfo.getAddress() != null) {
                            UserConfig.setHomeCountry(homeInfo.getAddress().getCountry());
                        }
                        InstallationProcessController.this.ensureInitInstallationInfo(true, false, new C08121());
                        return;
                    }
                    InstallationProcessController.this.goToScreenForInstallationStatus((Activity) InstallationProcessController.this.mCallingActivity.get(), response.code() == 404 ? Constants.INSTALLATION_STATUS_HOME_NOT_FOUND : Constants.INSTALLATION_STATUS_START);
                }

                public void onFailure(Call<HomeInfo> call, Throwable t) {
                    super.onFailure(call, t);
                    if (InstallationProcessController.this.mCallingActivity.get() != null) {
                        InstallationProcessController.showRetryError((Activity) InstallationProcessController.this.mCallingActivity.get(), this, call);
                    }
                }
            });
        }
    }

    private boolean initInstallationInfo(boolean nextStep, boolean reload) {
        Exception e;
        ExecutorService pool = Executors.newFixedThreadPool(3);
        List<Callable<Object>> tasks = new ArrayList();
        if (this.zones == null || this.zones.isEmpty() || reload) {
            tasks.add(ZonesResponseWrapper.getCallable());
        }
        if (this.installations == null || this.installations.isEmpty() || reload) {
            tasks.add(InstallationsResponseWrapper.getCallable());
        }
        if (this.devices == null || this.devices.isEmpty() || reload) {
            tasks.add(DevicesResponseWrapper.Companion.callable());
        }
        try {
            for (Future result : pool.invokeAll(tasks)) {
                ResponseWrapper resultResponse = (ResponseWrapper) result.get();
                if (resultResponse instanceof ZonesResponseWrapper) {
                    ZonesResponseWrapper zonesResponseWrapper = (ZonesResponseWrapper) resultResponse;
                    if (zonesResponseWrapper.getResponse().isSuccessful()) {
                        this.zones = (List) zonesResponseWrapper.getResponse().body();
                        ZoneController.INSTANCE.setZoneListWrapper(new ZoneListWrapper(this.zones));
                    }
                } else if (resultResponse instanceof InstallationsResponseWrapper) {
                    InstallationsResponseWrapper installationsResponseWrapper = (InstallationsResponseWrapper) resultResponse;
                    if (installationsResponseWrapper.getResponse().isSuccessful()) {
                        this.installations = (List) installationsResponseWrapper.getResponse().body();
                    }
                } else if (resultResponse instanceof DevicesResponseWrapper) {
                    DevicesResponseWrapper devicesResponseWrapper = (DevicesResponseWrapper) resultResponse;
                    if (devicesResponseWrapper.getResponse().isSuccessful()) {
                        this.devices = (List) devicesResponseWrapper.getResponse().body();
                    }
                } else if (resultResponse == null) {
                    pool.shutdown();
                    return false;
                }
            }
            if (this.zones == null || this.installations == null || this.devices == null) {
                pool.shutdown();
                return false;
            }
            this.installationInfo.init(this.zones, this.installations, this.devices);
            if (nextStep) {
                if (!this.zones.isEmpty() || !this.installations.isEmpty()) {
                    goToScreenForCompletedInstallation();
                } else if (UserConfig.getNickname() != null) {
                    NavigationController.showMainScreen((Context) this.mCallingActivity.get());
                } else {
                    retrieveMobileDevices(this.installationInfo);
                }
            }
            pool.shutdown();
            return true;
        } catch (Exception e2) {
            e = e2;
        } catch (Exception e22) {
            e = e22;
        }
        try {
            Snitcher.start().toCrashlytics().logException(e);
            return false;
        } finally {
            pool.shutdown();
        }
    }

    private void handleZoneOrInstallationError(Call<?> call, Response<?> response) throws IOException {
        ServerError[] serverErrors = ((ResponseError) new GsonBuilder().create().fromJson(response.errorBody().string(), ResponseError.class)).errors;
        if (serverErrors != null && serverErrors.length > 0) {
            handleError((Activity) this.mCallingActivity.get(), serverErrors[0], (Call) call, response.code());
        }
    }

    public void isInstallationCreatedForZone(final int zoneId) {
        RestServiceGenerator.getTadoInstallationRestService().listInstallations(UserConfig.getHomeId()).enqueue(new TadoCallback<List<Installation>>() {
            public void onResponse(Call<List<Installation>> call, Response<List<Installation>> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    InstallationProcessController.this.postInstallationStateForZone(response, Long.valueOf((long) zoneId));
                } else {
                    TadoApplication.getBus().post(InstallationProcessZoneStatus.create().with(InstallationProcessZoneStatusEnum.ZONE_INSTALLATION_ERROR));
                }
            }

            public void onFailure(Call<List<Installation>> call, Throwable t) {
                super.onFailure(call, t);
                TadoApplication.getBus().post(InstallationProcessZoneStatus.create().with(InstallationProcessZoneStatusEnum.ZONE_INSTALLATION_ERROR));
            }
        });
    }

    @Nullable
    private Integer getInstallationIdForZoneId(long zoneId) {
        List<Installation> installations = getInstallationInfo().getInstallations();
        if (installations.size() > 0) {
            for (Installation installation : installations) {
                if (installation instanceof AcInstallation) {
                    AcInstallation acInstallation = (AcInstallation) installation;
                    if (!(acInstallation.getAcInstallationInformation() == null || acInstallation.getAcInstallationInformation().getCreatedZone() == null || acInstallation.getAcInstallationInformation().getCreatedZone().getId().longValue() != zoneId)) {
                        return acInstallation.getId();
                    }
                }
            }
        }
        return null;
    }

    private void postInstallationStateForZone(Response<List<Installation>> response, Long zoneId) {
        InstallationProcessZoneStatus status;
        if (getInstallationIdForZoneId(zoneId.longValue()) != null) {
            status = InstallationProcessZoneStatus.create().with(InstallationProcessZoneStatusEnum.ZONE_INSTALLATION_COMPLETED);
        } else {
            status = InstallationProcessZoneStatus.create().with(InstallationProcessZoneStatusEnum.ZONE_INSTALLATION_NOT_COMPLETED);
        }
        TadoApplication.getBus().post(status);
    }

    public void restartAcSetup(final Activity activity, Long zoneId) {
        restartAcSetupHandleArguments(zoneId);
        RestServiceGenerator.getTadoInstallationRestService().restartAcSetup(UserConfig.getHomeId(), getInstallationIdForZoneId(zoneId.longValue())).enqueue(new TadoCallback<InstallationStateTransitionResult>() {
            public void onResponse(Call<InstallationStateTransitionResult> call, Response<InstallationStateTransitionResult> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    InstallationProcessController.this.handleInstallation(activity, ((InstallationStateTransitionResult) response.body()).getInstallation());
                } else {
                    TadoApplication.getBus().post(InstallationProcessZoneStatus.create().with(InstallationProcessZoneStatusEnum.ZONE_INSTALLATION_ERROR));
                }
            }

            public void onFailure(Call<InstallationStateTransitionResult> call, Throwable t) {
                super.onFailure(call, t);
                TadoApplication.getBus().post(InstallationProcessZoneStatus.create().with(InstallationProcessZoneStatusEnum.ZONE_INSTALLATION_ERROR));
            }
        });
    }

    public void resetAcSetup(final Activity activity, int installationId) {
        this.acSpecs = null;
        RestServiceGenerator.getTadoInstallationRestService().restartAcSetup(UserConfig.getHomeId(), Integer.valueOf(installationId)).enqueue(new TadoCallback<InstallationStateTransitionResult>() {
            public void onResponse(Call<InstallationStateTransitionResult> call, Response<InstallationStateTransitionResult> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    InstallationProcessController.this.handleInstallation(activity, ((InstallationStateTransitionResult) response.body()).getInstallation());
                } else {
                    TadoApplication.getBus().post(InstallationProcessZoneStatus.create().with(InstallationProcessZoneStatusEnum.ZONE_INSTALLATION_ERROR));
                }
            }

            public void onFailure(Call<InstallationStateTransitionResult> call, Throwable t) {
                super.onFailure(call, t);
                TadoApplication.getBus().post(InstallationProcessZoneStatus.create().with(InstallationProcessZoneStatusEnum.ZONE_INSTALLATION_ERROR));
            }
        });
    }

    private void restartAcSetupHandleArguments(Long zoneId) {
        if (getInstallationIdForZoneId(zoneId.longValue()) == null) {
            String message = "There is no installation id for the zone with id " + zoneId;
            Crashlytics.log(6, LOG_TAG, message);
            throw new IllegalArgumentException(message);
        }
    }

    private List<Zone> getZones() {
        if (this.zones == null) {
            this.zones = ZoneController.INSTANCE.getZoneList();
        }
        return this.zones;
    }

    public void handleInstallation(Activity activity, Installation installation) {
        this.mCallingActivity = new WeakReference(activity);
        if (installation.getType() != null) {
            switch (installation.getType()) {
                case INSTALL_AC_G1:
                    handleAcInstallation((AcInstallation) installation);
                    return;
                case INSTALL_ST_G1:
                case SALE_FITTING_ST_G1:
                case UPGRADE_TO_ST_G1:
                    goToScreenForInstallationStatus((Activity) this.mCallingActivity.get(), Constants.INSTALLATION_STATUS_HEATING_OVERVIEW);
                    return;
                case REPLACE_BRIDGE:
                    handleBridgeReplacement(activity, (BridgeReplacementInstallation) installation);
                    return;
                default:
                    return;
            }
        }
    }

    private void handleAcInstallation(AcInstallation installation) {
        initInstallation(installation);
        if (this.mCallingActivity.get() == null) {
            return;
        }
        if (installation.getRevision().intValue() < 6) {
            showUpgradeInstallationProcessScreen((Activity) this.mCallingActivity.get(), installation.getId().intValue());
        } else if (installation.getRevision().intValue() > 6) {
            NavigationController.showForceUpdateScreen((Context) this.mCallingActivity.get());
        } else {
            goToScreenForInstallationProcessStatus((Activity) this.mCallingActivity.get(), installation);
        }
    }

    private void handleBridgeReplacement(Activity activity, BridgeReplacementInstallation installation) {
        Intent intent = new Intent(activity, InstallInternetBridgeReplacementActivity.class);
        intent.putExtra(InstallInternetBridgeReplacementActivity.KEY_BRIDGE_REPLACEMENT_INFO, installation);
        startActivity(activity, intent, false);
    }

    private void loadInstallationInformationState(AcInstallation installation) {
        initInstallation(installation);
        if (installation.getAcInstallationInformation() != null) {
            this.acSpecs = installation.getAcInstallationInformation().getAcSpecs();
            if (!(this.acSpecs == null || this.acSpecs.getRemoteControl() == null || this.acSpecs.getRemoteControl().getTemperatureUnit() == null)) {
                UserConfig.setTemperatureUnit(this.acSpecs.getRemoteControl().getTemperatureUnit());
            }
            if (installation.getAcInstallationInformation().getAcSettingCommandSetRecording() != null) {
                ComplexTeachingController.getComplexTeachingController().setRecordingId(installation.getAcInstallationInformation().getAcSettingCommandSetRecording().getId().intValue());
            }
        }
    }

    public void setInstallation(Installation installation) {
        initInstallation(installation);
        if (getInstallation() != null && !this.installationInfo.getUnfinishedInstallations().contains(getInstallation())) {
            addOrUpdateInstallation(installation);
        }
    }

    private void addOrUpdateInstallation(Installation installation) {
        removeInstallation(installation.getId().intValue());
        this.installationInfo.getUnfinishedInstallations().add(getInstallation());
    }

    private void removeInstallation(int installationID) {
        Iterator<Installation> iterator = this.installationInfo.getUnfinishedInstallations().iterator();
        while (iterator.hasNext()) {
            if (((Installation) iterator.next()).getId().intValue() == installationID) {
                iterator.remove();
            }
        }
    }

    private void initInstallation(Installation installation) {
        getInstallationInfo().setCurrentInstallation(installation);
    }

    public InstallationInfo getInstallationInfo() {
        return this.installationInfo;
    }

    public Installation getInstallationForId(int id) {
        InstallationInfo installationInformation = getInstallationInfo();
        if (installationInformation != null) {
            for (Installation installation : installationInformation.getUnfinishedInstallations()) {
                if (installation.getId().intValue() == id) {
                    return installation;
                }
            }
        }
        return null;
    }

    public void registerDevice(GenericHardwareDevice device) {
        if (this.installationInfo == null) {
            this.installationInfo = new InstallationInfo();
        }
        this.installationInfo.addUninstalledDevice(device);
    }

    public StateEnum getAcInstallationState() {
        if (getInstallation() != null) {
            return ((AcInstallation) getInstallation()).getState();
        }
        return StateEnum.REGISTER_WR;
    }

    public void assignDeviceToZone(int assignedZoneId, GenericHardwareDevice device) {
        for (Zone zone : getZones()) {
            if (zone.getId() == assignedZoneId) {
                zone.getDevices().add(device);
                getInstallationInfo().getUninstalledDevices().remove(device);
            }
        }
        ensureInitInstallationInfo(false, true, new AlertChoiceDialogListener() {
            public void OnOKClicked() {
                InstallationProcessController.this.ensureInitInstallationInfo(false, true, this);
            }

            public void OnCancelClicked() {
                if (InstallationProcessController.this.mCallingActivity.get() != null) {
                    ((Activity) InstallationProcessController.this.mCallingActivity.get()).finish();
                }
            }
        });
    }

    public boolean ensureInitInstallationInfo(boolean navigateToNextStep, boolean reload, AlertChoiceDialogListener listener) {
        boolean success = initInstallationInfo(navigateToNextStep, reload);
        if (!(success || this.mCallingActivity.get() == null)) {
            showRetryError((Activity) this.mCallingActivity.get(), listener);
        }
        return success;
    }

    public void addZone(Zone zone) {
        if (!getZones().contains(zone)) {
            getZones().add(zone);
        }
    }

    public void callReplaceBridge(DeviceInput bridge, final UiCallback callback) {
        RestServiceGenerator.getTadoInstallationRestService().createBridgeReplacementInstallation(UserConfig.getHomeId(), new BridgeReplacementRequest(TypeEnum.REPLACE_BRIDGE.toString(), Integer.valueOf(1), bridge)).enqueue(new TadoCallback<BridgeReplacementInstallation>() {
            public void onResponse(Call<BridgeReplacementInstallation> call, Response<BridgeReplacementInstallation> response) {
                super.onResponse(call, response);
                callback.onRequestFinished(response, this.serverError);
            }

            public void onFailure(Call<BridgeReplacementInstallation> call, Throwable t) {
                super.onFailure(call, t);
                callback.onRequestFailed();
            }
        });
    }

    public void removeDeviceFromHome(boolean forceRemove, final GenericHardwareDevice device, @NonNull final UiCallback uiCallback) {
        RestServiceGenerator.getTadoRestService().removeDevice(UserConfig.getHomeId(), device.getShortSerialNo(), forceRemove, RestServiceGenerator.getCredentialsMap()).enqueue(new TadoCallback<Void>(new GeneralErrorAlertPresenter((Context) this.mCallingActivity.get())) {
            public void onResponse(Call<Void> call, Response<Void> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    ZoneController.INSTANCE.callGetZoneList();
                    if (InstallationProcessController.this.installationInfo != null) {
                        InstallationProcessController.this.installationInfo.removeDeviceFromHome(device);
                    }
                }
                uiCallback.onRequestFinished(response, this.serverError);
            }

            public void onFailure(Call<Void> call, Throwable t) {
                super.onFailure(call, t);
                uiCallback.onRequestFailed();
            }
        });
    }

    public void clear() {
        mInstallationProcessController = null;
    }
}
