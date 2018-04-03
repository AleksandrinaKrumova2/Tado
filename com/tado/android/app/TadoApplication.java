package com.tado.android.app;

import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.Crashlytics.Builder;
import com.crashlytics.android.core.CrashlyticsCore;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import com.tado.BuildConfig;
import com.tado.C0676R;
import com.tado.android.controllers.OnVersionResult;
import com.tado.android.controllers.RateAppUtil;
import com.tado.android.controllers.VersionCheckerUtil;
import com.tado.android.controllers.VersionState;
import com.tado.android.location.ConnectivityReceiver;
import com.tado.android.location.LocationAcquisitionMode;
import com.tado.android.location.LocationConfiguration;
import com.tado.android.location.LocationManager;
import com.tado.android.location.LocationTrigger;
import com.tado.android.location.lifeline.FirebaseLocationCheckScheduler;
import com.tado.android.location.lifeline.ILocationCheckScheduler;
import com.tado.android.location.lifeline.StandardLocationCheckScheduler;
import com.tado.android.location.playservices.LocationApiPlayServices;
import com.tado.android.location.updates.FirebaseLocationUpdateScheduler;
import com.tado.android.location.updates.ILocationUpdateScheduler;
import com.tado.android.location.updates.StandardLocationUpdateScheduler;
import com.tado.android.notifications.NotificationUtil;
import com.tado.android.utils.Constants;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.TadoBus;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import io.fabric.sdk.android.Fabric;
import java.util.List;

public class TadoApplication extends MultiDexApplication {
    private static Context context;
    private static GoogleAnalytics googleAnalytics;
    public static LocationManager locationManager;
    private static Bus mBus;
    private static Tracker tracker;

    class C07301 implements OnSharedPreferenceChangeListener {
        C07301() {
        }

        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals(Constants.ANALYTICS_OPT_OUT_PREFERENCE)) {
                TadoApplication.googleAnalytics.setAppOptOut(sharedPreferences.getBoolean(key, false));
            }
        }
    }

    class C07323 extends BroadcastReceiver {
        C07323() {
        }

        public void onReceive(Context context, Intent intent) {
            Snitcher.start().toLogger().log(3, "Tado-IDLE", "ACTION_DEVICE_IDLE_MODE_CHANGED isIdle %b", Boolean.valueOf(Util.isDeviceIdle(context)));
            if (!UserConfig.isLocationBasedControlEnabled()) {
                TadoApplication.locationManager.stopTracking();
            } else if (!Util.isDeviceIdle(context)) {
                TadoApplication.locationManager.reset();
                TadoApplication.locationManager.startTrackingIfEnabled();
                TadoApplication.locationManager.postLastKnownLocation(LocationAcquisitionMode.LAST_KNOWN_LOCATION, LocationTrigger.IDLE);
                NotificationUtil.updateAllNotifications(context);
            }
        }
    }

    class C07334 extends BroadcastReceiver {
        C07334() {
        }

        public void onReceive(Context context, Intent intent) {
            Snitcher.start().toLogger().log(3, "Tado-IDLE", "ACTION_POWER_SAVE_MODE_CHANGED isPowerSave %b", Boolean.valueOf(Util.isInPowerSavingMode(context)));
            if (!Util.isInPowerSavingMode(context)) {
                NotificationUtil.updateAllNotifications(context);
            }
        }
    }

    class C07345 extends BroadcastReceiver {
        C07345() {
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.location.PROVIDERS_CHANGED".matches(intent.getAction())) {
                android.location.LocationManager locationManager = (android.location.LocationManager) context.getSystemService("location");
                if (locationManager != null) {
                    List<String> providers = locationManager.getProviders(true);
                    NotificationUtil.updateAllNotifications(context);
                    Snitcher.start().toLogger().log(5, "LocationApi", "Location settings changed. Providers enabled: %s", providers.toString());
                }
            }
        }
    }

    class C07356 extends BroadcastReceiver {
        C07356() {
        }

        public void onReceive(Context context, Intent intent) {
            Snitcher.start().toLogger().log(4, "TimeChanged", ">>>>> Device time has changed", new Object[0]);
        }
    }

    public void onCreate() {
        super.onCreate();
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            boolean z;
            ILocationUpdateScheduler locationUpdateScheduler;
            ILocationCheckScheduler locationCheckScheduler;
            IntentFilter filter;
            LeakCanary.install(this);
            final SharedPreferences userPrefs = PreferenceManager.getDefaultSharedPreferences(this);
            googleAnalytics = GoogleAnalytics.getInstance(this);
            userPrefs.registerOnSharedPreferenceChangeListener(new C07301());
            GoogleAnalytics googleAnalytics = googleAnalytics;
            if (getResources().getBoolean(C0676R.bool.analytics)) {
                z = false;
            } else {
                z = true;
            }
            googleAnalytics.setAppOptOut(z);
            googleAnalytics.setDryRun(false);
            Crashlytics crashlyticsKit = new Builder().core(new CrashlyticsCore.Builder().disabled(false).build()).build();
            Fabric.with(this, crashlyticsKit);
            new VersionCheckerUtil().checkVersion(BuildConfig.VERSION_NAME, userPrefs.getString("app_version", ""), new OnVersionResult() {
                public void onMajorVersionUpdated(@NonNull String oldVersion, @NonNull String newVersion) {
                    Crashlytics.setString("app_updated", "updated from " + oldVersion);
                    RateAppUtil.versionState = VersionState.MAYOR_UPDATE;
                    userPrefs.edit().putString("app_version", newVersion).apply();
                }

                public void onMinorVersionUpdated(@NonNull String oldVersion, @NonNull String newVersion) {
                    Crashlytics.setString("app_updated", "updated from " + oldVersion);
                    RateAppUtil.versionState = VersionState.MINOR_UPDATE;
                    userPrefs.edit().putString("app_version", newVersion).apply();
                }

                public void onCleanInstall(@NonNull String newVersion) {
                    Crashlytics.setString("app_updated", "new install " + newVersion);
                    RateAppUtil.versionState = VersionState.NEW_INSTALL;
                    userPrefs.edit().putString("app_version", newVersion).apply();
                }

                public void onVersionNotUpdated(@NonNull String version) {
                    Crashlytics.setString("app_updated", "not updated");
                    RateAppUtil.versionState = VersionState.NOT_UPDATED;
                }
            });
            context = getApplicationContext();
            LocationConfiguration configuration = new LocationConfiguration(UserConfig.getLocationConfiguration());
            if (VERSION.SDK_INT >= 21) {
                JobScheduler scheduler = (JobScheduler) context.getSystemService("jobscheduler");
                locationUpdateScheduler = new StandardLocationUpdateScheduler(context, scheduler);
                locationCheckScheduler = new StandardLocationCheckScheduler(context, scheduler);
            } else {
                FirebaseJobDispatcher jobDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
                locationUpdateScheduler = new FirebaseLocationUpdateScheduler(jobDispatcher);
                locationCheckScheduler = new FirebaseLocationCheckScheduler(jobDispatcher);
            }
            locationManager = new LocationManager(configuration, new LocationApiPlayServices(this), locationCheckScheduler, locationUpdateScheduler);
            NotificationUtil.createChannels(this);
            if (VERSION.SDK_INT >= 23) {
                filter = new IntentFilter();
                filter.addAction("android.os.action.DEVICE_IDLE_MODE_CHANGED");
                registerReceiver(new C07323(), filter);
            }
            if (VERSION.SDK_INT >= 21) {
                filter = new IntentFilter();
                filter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
                registerReceiver(new C07334(), filter);
            }
            registerReceiver(new C07345(), new IntentFilter("android.location.PROVIDERS_CHANGED"));
            registerReceiver(new ConnectivityReceiver(), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            registerReceiver(new ConnectivityReceiver(), new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED"));
            registerReceiver(new ConnectivityReceiver(), new IntentFilter("android.intent.action.AIRPLANE_MODE"));
            registerReceiver(new C07356(), new IntentFilter("android.intent.action.TIME_SET"));
            if (isInternal() && UserConfig.getUsername().isEmpty()) {
                UserConfig.setUsername(getString(C0676R.string.test_username));
                UserConfig.setPassword(getString(C0676R.string.test_password));
                UserConfig.setHomeId(47254);
            }
        }
    }

    public static boolean isInternal() {
        return "release".equalsIgnoreCase("location");
    }

    public static Bus getBus() {
        if (mBus == null) {
            mBus = new TadoBus(ThreadEnforcer.ANY);
        }
        return mBus;
    }

    public static Context getTadoAppContext() {
        return context;
    }

    public static float getDensityFactor() {
        WindowManager manager = (WindowManager) context.getSystemService("window");
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        return metrics.density;
    }

    public synchronized Tracker getDefaultTracker() {
        if (tracker == null) {
            tracker = googleAnalytics.newTracker((int) C0676R.xml.global_tracker);
            tracker.setAnonymizeIp(true);
        }
        return tracker;
    }
}
