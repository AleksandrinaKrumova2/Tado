package com.tado.android;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.os.EnvironmentCompat;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.common.GoogleApiAvailability;
import com.tado.BuildConfig;
import com.tado.C0676R;
import com.tado.android.app.DeepLinkingManager;
import com.tado.android.demo.DemoUtils;
import com.tado.android.dialogs.AlertChoiceDialogListener;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.dialogs.AlertWarningDialogListener;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.mvp.model.HomeWifiLocalDataSource;
import com.tado.android.mvp.model.HomeWifiPreferencesHelper;
import com.tado.android.mvp.model.HomeWifiRepository;
import com.tado.android.premium.data.PremiumUpsellingHelper;
import com.tado.android.premium.data.PremiumUpsellingRepository;
import com.tado.android.premium.data.UpsellingLocalDataSource;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.MobileDevice;
import com.tado.android.rest.model.MobileDeviceDeviceMetadata;
import com.tado.android.rest.model.MobileDeviceMetadata;
import com.tado.android.rest.model.MobileDeviceTadoAppMetadata;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.Constants;
import com.tado.android.utils.DisplaySizeHelper;
import com.tado.android.utils.Server;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import retrofit2.Call;
import retrofit2.Response;

public class SplashScreenActivity extends FragmentActivity {
    private static final String DIALOG_ERROR = "dialog_error";
    private static final String END_POINT = "endpoint";
    private static final String PASSWORD = "password";
    public static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final int REQUEST_RESOLVE_ERROR = 1001;
    private static final String SENDER_ID = "34242713352";
    private static final int SPLASH_DELAY = 300;
    private static final String TAG = SplashScreenActivity.class.getCanonicalName();
    private static final String USERNAME = "username";
    private int RECONNECT_TRIES;
    private ErrorDialogFragment dialogFragment;
    private Handler handler;
    private AtomicBoolean initCalled = new AtomicBoolean(false);
    private boolean isIntentHandled;
    private boolean mResolvingError = false;
    private String regid;
    private WifiInfo wifiInfo;
    private WifiReceiver wifiReceiver = null;

    class C06921 implements AlertChoiceDialogListener {
        C06921() {
        }

        public void OnOKClicked() {
            SplashScreenActivity.this.initCalled.set(false);
            SplashScreenActivity.this.init();
        }

        public void OnCancelClicked() {
            SplashScreenActivity.this.finish();
        }
    }

    class C06942 implements Runnable {
        C06942() {
        }

        public void run() {
            final C06942 thisRunnable = this;
            WifiManager wifiManager = (WifiManager) SplashScreenActivity.this.getApplicationContext().getSystemService("wifi");
            SplashScreenActivity.this.wifiInfo = wifiManager.getConnectionInfo();
            if (SplashScreenActivity.this.checkWifiConnection(wifiManager, SplashScreenActivity.this.wifiInfo)) {
                SplashScreenActivity.access$504(SplashScreenActivity.this);
                if (SplashScreenActivity.this.RECONNECT_TRIES > 5) {
                    SplashScreenActivity.this.RECONNECT_TRIES = 0;
                    Snitcher.start().toLogger().toCrashlytics().log(4, SplashScreenActivity.TAG, "Connected to tado device wifi", new Object[0]);
                    AlertDialogs.showWarning(SplashScreenActivity.this.getString(C0676R.string.errors_connectedToTadoWifi_title), SplashScreenActivity.this.getString(C0676R.string.errors_connectedToTadoWifi_message), SplashScreenActivity.this.getString(C0676R.string.errors_connectedToTadoWifi_confirmButton), SplashScreenActivity.this, new AlertWarningDialogListener() {
                        public void OnOKClicked() {
                            SplashScreenActivity.this.handler.postDelayed(thisRunnable, 2000);
                        }
                    });
                    return;
                }
                SplashScreenActivity.this.removeWifiConnection(wifiManager, SplashScreenActivity.this.wifiInfo);
                SplashScreenActivity.this.handler.postDelayed(thisRunnable, 2000);
                return;
            }
            SplashScreenActivity.this.init();
        }
    }

    class C06953 extends TadoCallback<MobileDevice> {
        C06953() {
        }

        public void onResponse(Call<MobileDevice> call, Response<MobileDevice> response) {
            super.onResponse(call, response);
            if (response.isSuccessful()) {
                UserConfig.setMobileDeviceId(((MobileDevice) response.body()).getId());
            }
        }

        public void onFailure(Call<MobileDevice> call, Throwable t) {
            super.onFailure(call, t);
        }
    }

    class C06964 extends TadoCallback<Void> {
        C06964() {
        }
    }

    public static class ErrorDialogFragment extends DialogFragment {
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return GoogleApiAvailability.getInstance().getErrorDialog(getActivity(), getArguments().getInt(SplashScreenActivity.DIALOG_ERROR), 1001);
        }

        public void onDismiss(DialogInterface dialog) {
            ((SplashScreenActivity) getActivity()).onDialogDismissed();
        }
    }

    private class Hider extends AsyncTask<Void, Void, Void> {
        private Hider() {
        }

        protected Void doInBackground(Void... arg0) {
            try {
                long startTime = System.currentTimeMillis();
                SplashScreenActivity.this.updateOldAppData();
                SplashScreenActivity.this.updateDeviceInfo();
                Thread.sleep(Math.max(300 - (System.currentTimeMillis() - startTime), 0), 0);
            } catch (InterruptedException e) {
                Snitcher.start().logException(e);
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            SplashScreenActivity.this.overridePendingTransition(17432576, 17432577);
            SplashScreenActivity.this.isIntentHandled = new DeepLinkingManager().parseIntent(SplashScreenActivity.this.getIntent(), SplashScreenActivity.this);
            if (!SplashScreenActivity.this.isIntentHandled) {
                if (UserConfig.getNickname() == null && (UserConfig.getUsername().isEmpty() || UserConfig.getPassword().isEmpty())) {
                    SplashScreenActivity.this.startActivity(new Intent(SplashScreenActivity.this, PromoActivity.class));
                    SplashScreenActivity.this.finish();
                    return;
                }
                InstallationProcessController.getInstallationProcessController().detectStatus(SplashScreenActivity.this, false);
            }
        }
    }

    private class WifiReceiver extends BroadcastReceiver {
        private WifiReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            WifiManager wifiManager = (WifiManager) SplashScreenActivity.this.getApplicationContext().getSystemService("wifi");
            if (intent.getAction().equals("android.net.wifi.STATE_CHANGE")) {
                SplashScreenActivity.this.wifiInfo = wifiManager.getConnectionInfo();
                if (!SplashScreenActivity.this.checkWifiConnection(wifiManager, SplashScreenActivity.this.wifiInfo)) {
                    SplashScreenActivity.this.init();
                }
            }
        }
    }

    static /* synthetic */ int access$504(SplashScreenActivity x0) {
        int i = x0.RECONNECT_TRIES + 1;
        x0.RECONNECT_TRIES = i;
        return i;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(C0676R.layout.activity_splash_screen);
        resizeLogo();
        if (UserConfig.getHomeId() != 1 && HomeWifiRepository.INSTANCE.getLocalDataSource() == null) {
            HomeWifiRepository.INSTANCE.setLocalDataSource(new HomeWifiLocalDataSource(new HomeWifiPreferencesHelper(this, UserConfig.getHomeId())));
        }
        if (PremiumUpsellingRepository.INSTANCE.getLocalDataSource() == null) {
            PremiumUpsellingRepository.INSTANCE.setLocalDataSource(new UpsellingLocalDataSource(new PremiumUpsellingHelper(this)));
        }
    }

    protected void onResume() {
        super.onResume();
        if (DemoUtils.isInDemoMode()) {
            DemoUtils.exitDemo();
        }
        if (checkPlayServices()) {
            checkAndRemoveWifi();
        }
    }

    private void init() {
        Crashlytics.log("init()");
        if (this.wifiReceiver != null) {
            unregisterReceiver(this.wifiReceiver);
            this.wifiReceiver = null;
        }
        if (this.handler != null) {
            this.handler.removeCallbacksAndMessages(null);
        }
        boolean proceed = true;
        if (this.initCalled.compareAndSet(false, true)) {
            if (!(UserConfig.getUsername() == null || UserConfig.getPassword() == null || UserConfig.getHomeId() == -1 || UserConfig.getMobileDeviceId() == -1)) {
                proceed = InstallationProcessController.getInstallationProcessController().setCallingActivity(this).ensureInitInstallationInfo(false, true, new C06921());
            }
            if (proceed) {
                new Hider().execute(new Void[0]);
            }
        }
    }

    private void checkAndRemoveWifi() {
        Crashlytics.log("checkAndRemoveWifi()");
        this.handler = new Handler(Looper.getMainLooper());
        this.handler.post(new C06942());
    }

    private void removeWifiConnection(WifiManager wifiManager, WifiInfo wifiInfo) {
        if (this.wifiReceiver == null) {
            this.wifiReceiver = new WifiReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.STATE_CHANGE");
            intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
            registerReceiver(this.wifiReceiver, intentFilter);
        }
        wifiManager.removeNetwork(wifiInfo.getNetworkId());
        wifiManager.reassociate();
    }

    private boolean checkWifiConnection(WifiManager wifiManager, WifiInfo wifiInfo) {
        return wifiManager.isWifiEnabled() && Util.isCorrectSsid(wifiInfo.getSSID());
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != 0) {
            Snitcher.start().toLogger().toCrashlytics().log(4, TAG, "Play services not availabe or outdated.", new Object[0]);
            if (googleApiAvailability.isUserResolvableError(resultCode)) {
                Dialog errorDialog = googleApiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST);
                errorDialog.setCancelable(false);
                errorDialog.show();
                Snitcher.start().toLogger().toCrashlytics().log(4, TAG, "Showing Play Services update dialog", new Object[0]);
                return false;
            }
            showErrorDialog(resultCode);
            this.mResolvingError = true;
            Snitcher.start().toLogger().toCrashlytics().log(4, TAG, "Show play services error dialog", new Object[0]);
            return false;
        }
        try {
            int playServicesVersion = getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
            Snitcher.start().toLogger().toCrashlytics().log(4, TAG, "Play services OK version %d \n %s", Integer.valueOf(playServicesVersion), Util.getOperatingSystemVersion());
        } catch (Exception e) {
        }
        Snitcher.start().log(4, TAG, "Play services OK", new Object[0]);
        return true;
    }

    private void showErrorDialog(int errorCode) {
        this.dialogFragment = new ErrorDialogFragment();
        Bundle args = new Bundle();
        args.putInt(DIALOG_ERROR, errorCode);
        this.dialogFragment.setArguments(args);
        this.dialogFragment.setCancelable(false);
        this.dialogFragment.show(getSupportFragmentManager(), "errordialog");
    }

    public void onDialogDismissed() {
        this.mResolvingError = false;
        finish();
    }

    private void updateOldAppData() {
        Snitcher.start().toLogger().toCrashlytics().log(4, TAG, "Update Old App Data", new Object[0]);
        checkMobileDeviceId();
        SharedPreferences oldPrefs = getSharedPreferences(BuildConfig.FLAVOR, 0);
        if (oldPrefs != null && oldPrefs.contains("username") && oldPrefs.contains("password") && oldPrefs.contains(END_POINT)) {
            UserConfig.setUsername(oldPrefs.getString("username", ""));
            UserConfig.setPassword(oldPrefs.getString("password", ""));
            if (oldPrefs.getString(END_POINT, "").contains("my.tado")) {
                UserConfig.setServerAddress(Server.getProduction());
            } else {
                UserConfig.setServerAddress(Server.getPreproduction());
            }
            UserConfig.setNickname(oldPrefs.getString("username", ""));
            oldPrefs.edit().clear().apply();
        }
        for (String bssid : UserConfig.getLegacyConfiguration().getOldBSSIDs()) {
            if (!HomeWifiRepository.INSTANCE.getHomeWifis().containsKey(bssid)) {
                HomeWifiRepository.INSTANCE.addHomeWifi(bssid, "");
            }
        }
        UserConfig.getLegacyConfiguration().clearOldBSSIDs();
        setServerAddress();
    }

    private void checkMobileDeviceId() {
        if (!UserConfig.getUsername().isEmpty() && !UserConfig.getPassword().isEmpty() && UserConfig.getMobileDeviceId() == -1 && UserConfig.getHomeId() != -1) {
            callTransitionalGetMobileDeviceId();
        }
    }

    private void callTransitionalGetMobileDeviceId() {
        Map<String, String> options = RestServiceGenerator.getCredentialsMap();
        options.put(Constants.TRANSITIONAL_APP_USERNAME, UserConfig.getUsername());
        RestServiceGenerator.getTadoRestService().getMobileDeviceTransitional(UserConfig.getHomeId(), options).enqueue(new C06953());
    }

    private void setServerAddress() {
        String storedServerAddress = UserConfig.getServerAddress();
        if (storedServerAddress == null || storedServerAddress.equals("")) {
            storedServerAddress = Server.getProduction();
        }
        UserConfig.setServerAddress(storedServerAddress);
        Crashlytics.log(String.format("Set server address %s", new Object[]{storedServerAddress}));
    }

    private void resizeLogo() {
        ImageView iv = (ImageView) findViewById(C0676R.id.imageViewLogo);
        LayoutParams params = iv.getLayoutParams();
        params.width = (int) (0.4d * ((double) DisplaySizeHelper.getScreenSize(getWindowManager()).x));
        iv.setLayoutParams(params);
    }

    private void updateDeviceInfo() {
        if (!UserConfig.getUsername().isEmpty() && !UserConfig.getPassword().isEmpty() && UserConfig.getMobileDeviceId() != -1) {
            Crashlytics.log("UpdateDeviceInfo");
            MobileDeviceMetadata metadata = new MobileDeviceMetadata();
            MobileDeviceDeviceMetadata device = new MobileDeviceDeviceMetadata();
            device.setModel(Util.getDeviceName());
            device.setPlatform("Android");
            device.setOsVersion(VERSION.RELEASE);
            device.setLocale(Util.getSupportedLanguage());
            metadata.setDevice(device);
            MobileDeviceTadoAppMetadata tadoApp = new MobileDeviceTadoAppMetadata();
            try {
                tadoApp.setVersion(getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
            } catch (NameNotFoundException e) {
                tadoApp.setVersion(EnvironmentCompat.MEDIA_UNKNOWN);
                Crashlytics.logException(e);
            }
            metadata.setTadoApp(tadoApp);
            updateDeviceMetadata(metadata);
        }
    }

    private void updateDeviceMetadata(MobileDeviceMetadata metadata) {
        RestServiceGenerator.getTadoRestService().updateMobileDeviceMetadata(UserConfig.getHomeId(), UserConfig.getMobileDeviceId(), metadata, RestServiceGenerator.getCredentialsMap()).enqueue(new C06964());
    }

    protected void onPause() {
        super.onPause();
        if (this.dialogFragment != null) {
            this.dialogFragment.dismiss();
            this.dialogFragment = null;
        }
        if (this.wifiReceiver != null) {
            unregisterReceiver(this.wifiReceiver);
            this.wifiReceiver = null;
        }
        if (this.handler != null) {
            this.handler.removeCallbacksAndMessages(null);
        }
    }

    protected void onStop() {
        super.onStop();
        if (this.isIntentHandled) {
            finish();
        }
    }
}
