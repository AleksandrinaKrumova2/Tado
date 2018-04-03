package com.tado.android.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Pair;
import com.tado.C0676R;
import com.tado.android.controllers.ZoneController;
import com.tado.android.mvp.model.LowBatteryRepository;
import com.tado.android.rest.model.Zone;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.settings.notifications.NotificationPreferenceActivity;
import com.tado.android.utils.Snitcher;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationUtil {
    public static final String BATTERY_CHANNEL = "battery-channel";
    public static final String GENERAL_CHANNEL = "general-channel";
    public static final String LOCATION_CHANNEL = "location-channel";
    private static final int NOTIFICATION_DELAY = 2000;
    private static final String TAG = "NotificationUtil";
    protected static final Map<Integer, BaseNotification> notifications = createNotifications();
    private static String previousReason = "";

    private static Map<Integer, BaseNotification> createNotifications() {
        Map<Integer, BaseNotification> notifications = new HashMap();
        notifications.put(Integer.valueOf(1), new NoInternetNotification());
        notifications.put(Integer.valueOf(2), new LocationSettingsNotification());
        notifications.put(Integer.valueOf(3), new BatteryOptimizationNotification());
        notifications.put(Integer.valueOf(4), new NoRecentLocationPostNotification());
        return notifications;
    }

    public static void updateAllNotifications(Context ctx, String reason) {
        if (!TextUtils.isEmpty(reason) && reason.compareTo(previousReason) != 0) {
            updateAllNotifications(ctx);
            previousReason = reason;
        }
    }

    public static void updateAllNotifications(final Context ctx) {
        for (final BaseNotification baseNotification : notifications.values()) {
            if (baseNotification.shouldShow(ctx)) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        if (baseNotification.shouldShow(ctx)) {
                            baseNotification.notify(ctx);
                        }
                    }
                }, 2000);
            } else {
                baseNotification.dismiss(ctx);
            }
        }
    }

    public static int notifyLogout(Context context) {
        LogoutNotification notification = new LogoutNotification();
        if (notification.shouldShow(context)) {
            notification.notify(context);
        }
        return notification.getNotificationId();
    }

    public static int notifyLocationHistory(Context context, List<Location> locations) {
        LocationHistoryNotification notification = new LocationHistoryNotification(locations);
        if (notification.shouldShow(context)) {
            notification.notify(context);
        } else {
            dissmissLocationHistoryNotification(context);
        }
        return notification.getNotificationId();
    }

    public static void dissmissLocationHistoryNotification(Context context) {
        NotificationManagerCompat.from(context).cancel(LocationHistoryNotification.NOTIFICATION_ID);
    }

    public static void showBatteryNotifications(Context context, LowBatteryRepository repository) {
        for (String id : repository.getNotifications()) {
            Pair<Zone, GenericHardwareDevice> zoneDevicePair = ZoneController.INSTANCE.findDeviceBySerialNumber(id);
            if (zoneDevicePair != null) {
                LowBatteryNotification lowBatteryNotification = new LowBatteryNotification(repository, zoneDevicePair);
                if (isLowBatteryNotificationEnabled(PreferenceManager.getDefaultSharedPreferences(context)) && lowBatteryNotification.shouldShow(context)) {
                    lowBatteryNotification.notify(context);
                    repository.setNotificationShown(id);
                    Snitcher.start().log(3, TAG, "Notification shown: " + lowBatteryNotification.getNotificationId(), new Object[0]);
                } else {
                    lowBatteryNotification.dismiss(context);
                    Snitcher.start().log(3, TAG, "Notification dismissed: " + lowBatteryNotification.getNotificationId(), new Object[0]);
                }
            }
        }
    }

    public static void createChannels(Context context) {
        if (VERSION.SDK_INT >= 26) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(new NotificationChannel(GENERAL_CHANNEL, context.getString(C0676R.string.notifications_notficationChannels_general_title), 3));
                NotificationChannel channel = new NotificationChannel(LOCATION_CHANNEL, context.getString(C0676R.string.notifications_notficationChannels_location_title), 3);
                channel.setDescription(context.getString(C0676R.string.notifications_notficationChannels_location_description, new Object[]{context.getString(C0676R.string.app_name)}));
                notificationManager.createNotificationChannel(channel);
                channel = new NotificationChannel(BATTERY_CHANNEL, context.getString(C0676R.string.notifications_notficationChannels_battery_title), 4);
                channel.setDescription(context.getString(C0676R.string.notifications_notficationChannels_battery_description, new Object[]{context.getString(C0676R.string.app_name)}));
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    public static boolean isLowBatteryNotificationEnabled(SharedPreferences sharedPref) {
        return sharedPref.getBoolean(NotificationPreferenceActivity.KEY_LOW_BATTERY_NOTIFICATION, true);
    }

    public static boolean isAwayModeNotificationEnabled(SharedPreferences sharedPref) {
        return sharedPref.getBoolean(NotificationPreferenceActivity.KEY_AWAY_MODE_NOTIFICATION, true);
    }

    public static boolean isHomeModeNotificationEnabled(SharedPreferences sharedPref) {
        return sharedPref.getBoolean(NotificationPreferenceActivity.KEY_HOME_MODE_NOTIFICATION, true);
    }

    public static boolean isOpenWindowDetectionNotificationEnabled(SharedPreferences sharedPref) {
        return sharedPref.getBoolean(NotificationPreferenceActivity.KEY_OWD_NOTIFICATION, true);
    }

    public static boolean isEnergySavingsReportUpdateNotificationEnabled(SharedPreferences sharedPref) {
        return sharedPref.getBoolean(NotificationPreferenceActivity.KEY_ESR_NOTIFICATION, true);
    }
}
