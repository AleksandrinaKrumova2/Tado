package com.tado.android.notifications;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Pair;
import com.tado.C0676R;
import com.tado.android.mvp.model.LowBatteryRepository;
import com.tado.android.rest.model.Zone;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.settings.zonesettings.DeviceDetailsActivity;
import io.fabric.sdk.android.services.common.AbstractSpiCall;

class LowBatteryNotification extends BaseNotification {
    private GenericHardwareDevice device;
    private int notificationID;
    private LowBatteryRepository repository;
    private Zone zone;

    LowBatteryNotification(LowBatteryRepository repository, Pair<Zone, GenericHardwareDevice> subject) {
        this.repository = repository;
        this.zone = (Zone) subject.first;
        this.device = (GenericHardwareDevice) subject.second;
        try {
            this.notificationID = (this.zone.getId() * AbstractSpiCall.DEFAULT_TIMEOUT) + Integer.parseInt(this.device.getShortSerialNo().substring(this.device.getShortSerialNo().length() - 4));
        } catch (Exception e) {
            this.notificationID = this.zone.getId() * AbstractSpiCall.DEFAULT_TIMEOUT;
        }
    }

    public int getNotificationId() {
        return this.notificationID;
    }

    public boolean shouldShow(Context ctx) {
        return this.repository.shouldShowNotification(this.device.getSerialNo());
    }

    private Intent createNotificationIntent(Context context) {
        return DeviceDetailsActivity.getIntent(context, this.device, this.zone.getType(), this.zone.getId());
    }

    public Notification getNotification(Context ctx) {
        return new Builder(ctx, NotificationUtil.BATTERY_CHANNEL).setContentTitle(ctx.getString(C0676R.string.notifications_lowBattery_title, new Object[]{this.zone.getName()})).setContentText(getNotificationTextForId(ctx, C0676R.string.notifications_lowBattery_message)).setSmallIcon(C0676R.drawable.ic_low_battery).setOnlyAlertOnce(true).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(ctx, 0, createNotificationIntent(ctx), 134217728)).build();
    }
}
