package com.tado.android.notifications;

import android.app.Notification;
import android.content.Context;
import android.location.Location;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.InboxStyle;
import com.tado.BuildConfig;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.location.LocationUtil;
import java.util.List;

public class LocationHistoryNotification extends BaseNotification {
    public static final int NOTIFICATION_ID = 987;
    private final List<Location> locations;

    public LocationHistoryNotification(List<Location> locations) {
        this.locations = locations;
    }

    protected int getNotificationId() {
        return NOTIFICATION_ID;
    }

    public boolean shouldShow(Context ctx) {
        return false;
    }

    protected Notification getNotification(Context ctx) {
        InboxStyle inboxStyle = new InboxStyle();
        String str = "%s %s/%s";
        Object[] objArr = new Object[3];
        objArr[0] = BuildConfig.VERSION_NAME;
        objArr[1] = TadoApplication.locationManager.getLocationConfiguration().useFused() ? "F" : "-";
        objArr[2] = TadoApplication.locationManager.getLocationConfiguration().useGeofences() ? "G" : "-";
        inboxStyle.setBigContentTitle(String.format(str, objArr));
        for (Location oldLocation : this.locations) {
            inboxStyle.addLine(LocationUtil.formatLocation(oldLocation));
        }
        return new Builder(ctx, "location").setContentTitle("LocationTracker 4.9.3").setGroup("location_tracker").setSmallIcon(C0676R.drawable.logo_white_notification).setPriority(2).setStyle(inboxStyle).build();
    }
}
