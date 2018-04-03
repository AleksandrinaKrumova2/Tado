package com.tado.android.mvp.model;

import android.util.Pair;
import com.tado.android.rest.model.Zone;
import com.tado.android.rest.model.installation.BatteryStateEnum;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H&J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000fH&J\"\u0010\u0010\u001a\u00020\u00032\u0018\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u00130\u0012H&¨\u0006\u0016"}, d2 = {"Lcom/tado/android/mvp/model/LowBatteryDatasource;", "", "clearNotificationState", "", "serialNumber", "", "getNotifications", "", "getNotificationsToShow", "setNotificationPending", "setNotificationShown", "shouldShowNotification", "", "updateDeviceBatteryStatus", "status", "Lcom/tado/android/rest/model/installation/BatteryStateEnum;", "updateDevices", "devices", "", "Landroid/util/Pair;", "Lcom/tado/android/rest/model/Zone;", "Lcom/tado/android/rest/model/installation/GenericHardwareDevice;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: LowBatteryDatasource.kt */
public interface LowBatteryDatasource {
    void clearNotificationState(@NotNull String str);

    @NotNull
    Set<String> getNotifications();

    @NotNull
    Set<String> getNotificationsToShow();

    void setNotificationPending(@NotNull String str);

    void setNotificationShown(@NotNull String str);

    boolean shouldShowNotification(@NotNull String str);

    void updateDeviceBatteryStatus(@NotNull String str, @NotNull BatteryStateEnum batteryStateEnum);

    void updateDevices(@NotNull List<Pair<Zone, GenericHardwareDevice>> list);
}
