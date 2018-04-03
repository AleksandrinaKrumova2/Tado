package com.tado.android.fcm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.messaging.RemoteMessage.Notification;
import com.google.gson.Gson;
import com.tado.android.app.TadoApplication;
import com.tado.android.controllers.ZoneController;
import com.tado.android.entities.ZoneOrderInput;
import com.tado.android.fcm.model.EnergySavingsReportUpdate;
import com.tado.android.notifications.EnergySavingsReportNotificationReceiver;
import com.tado.android.notifications.NotificationFormatterKt;
import com.tado.android.notifications.nonpremium.OpenWindowDetectedNotification;
import com.tado.android.notifications.nonpremium.SwitchToAwayNotification;
import com.tado.android.notifications.nonpremium.SwitchToHomeNotification;
import com.tado.android.rest.model.HomeLicense;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.Snitcher.Builder;
import com.tado.android.utils.UserConfig;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.AsyncKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\u0012\u0010\n\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\u001c\u0010\u000b\u001a\u00020\u00042\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\rH\u0002J\u0012\u0010\u000e\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\u0012\u0010\u000f\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002¨\u0006\u0010"}, d2 = {"Lcom/tado/android/fcm/FCMService;", "Lcom/google/firebase/messaging/FirebaseMessagingService;", "()V", "onMessageReceived", "", "remoteMessage", "Lcom/google/firebase/messaging/RemoteMessage;", "parseESRData", "data", "", "parseLicenseData", "parseMessage", "message", "", "parseZoneData", "parseZoneReorderData", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: FCMService.kt */
public final class FCMService extends FirebaseMessagingService {
    public void onMessageReceived(@NotNull RemoteMessage remoteMessage) {
        Object obj = null;
        Intrinsics.checkParameterIsNotNull(remoteMessage, "remoteMessage");
        try {
            Builder start = Snitcher.start();
            String str = "FCMService";
            StringBuilder append = new StringBuilder().append("From: ");
            String from = remoteMessage.getFrom();
            if (from == null) {
                Intrinsics.throwNpe();
            }
            start.log(3, str, append.append(from).toString(), new Object[0]);
            Map data = remoteMessage.getData();
            Intrinsics.checkExpressionValueIsNotNull(data, "remoteMessage.data");
            if (!data.isEmpty()) {
                obj = 1;
            }
            if (obj != null) {
                Snitcher.start().log(3, "FCMService", "PushMessage data payload: " + remoteMessage.getData(), new Object[0]);
                Map data2 = remoteMessage.getData();
                Intrinsics.checkExpressionValueIsNotNull(data2, "remoteMessage.data");
                parseMessage(data2);
            }
            if (remoteMessage.getNotification() != null) {
                start = Snitcher.start();
                str = "FCMService";
                append = new StringBuilder().append("PushMessage Notification Body:  ");
                Notification notification = remoteMessage.getNotification();
                String body = notification != null ? notification.getBody() : null;
                if (body == null) {
                    Intrinsics.throwNpe();
                }
                start.log(3, str, append.append(body).toString(), new Object[0]);
            }
        } catch (Exception e) {
            Snitcher.start().toCrashlytics().logException("FCMService", "Exception parsing remote message", e);
        }
    }

    private final void parseMessage(Map<String, String> message) {
        Snitcher.start().log(3, "FCMService", "Received message type: " + ((String) message.get("type")), new Object[0]);
        String str = (String) message.get("type");
        if (str != null) {
            switch (str.hashCode()) {
                case -1027704857:
                    if (str.equals("non-premium-geofencing-away-hide")) {
                        new SwitchToAwayNotification().dismiss(TadoApplication.getTadoAppContext());
                        return;
                    }
                    return;
                case -1027377758:
                    if (str.equals("non-premium-geofencing-away-show")) {
                        new SwitchToAwayNotification().notify(TadoApplication.getTadoAppContext());
                        return;
                    }
                    return;
                case -365038442:
                    if (str.equals("non-premium-geofencing-home-hide")) {
                        new SwitchToHomeNotification().dismiss(TadoApplication.getTadoAppContext());
                        return;
                    }
                    return;
                case -364711343:
                    if (str.equals("non-premium-geofencing-home-show")) {
                        new SwitchToHomeNotification().notify(TadoApplication.getTadoAppContext());
                        return;
                    }
                    return;
                case 815697013:
                    if (str.equals("non-premium-open-window-state-notify")) {
                        parseZoneData((String) message.get("data"));
                        return;
                    }
                    return;
                case 1276548009:
                    if (str.equals("energy-savings-report-available")) {
                        parseESRData((String) message.get("data"));
                        return;
                    }
                    return;
                case 1466033646:
                    if (str.equals("zones-order-update")) {
                        parseZoneReorderData((String) message.get("data"));
                        return;
                    }
                    return;
                case 2111308757:
                    if (str.equals("license-update")) {
                        parseLicenseData((String) message.get("data"));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private final void parseZoneReorderData(String data) {
        List list = new Gson().fromJson(data, new FCMService$parseZoneReorderData$type$1().getType());
        Intrinsics.checkExpressionValueIsNotNull(list, "Gson().fromJson(data, type)");
        Iterable<Number> $receiver$iv = list;
        Collection destination$iv$iv = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault($receiver$iv, 10));
        for (Number item$iv$iv : $receiver$iv) {
            destination$iv$iv.add(new ZoneOrderInput(item$iv$iv.intValue()));
        }
        ZoneController.INSTANCE.updateZoneListOrder((List) destination$iv$iv);
        Context applicationContext = getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
        AsyncKt.runOnUiThread(applicationContext, (Function1) FCMService$parseZoneReorderData$1.INSTANCE);
    }

    private final void parseZoneData(String data) {
        List list = new Gson().fromJson(data, new FCMService$parseZoneData$type$1().getType());
        Intrinsics.checkExpressionValueIsNotNull(list, "Gson().fromJson(data, type)");
        new OpenWindowDetectedNotification(list).notify(TadoApplication.getTadoAppContext());
    }

    private final void parseLicenseData(String data) {
        HomeLicense homeLicense = new Gson().fromJson(data, new FCMService$parseLicenseData$type$1().getType());
        Intrinsics.checkExpressionValueIsNotNull(homeLicense, "Gson().fromJson(data, type)");
        UserConfig.setLicense(homeLicense.getLicense());
    }

    private final void parseESRData(String data) {
        EnergySavingsReportUpdate energySavingsReportUpdate = new Gson().fromJson(data, new FCMService$parseESRData$type$1().getType());
        Intrinsics.checkExpressionValueIsNotNull(energySavingsReportUpdate, "Gson().fromJson(data, type)");
        energySavingsReportUpdate = energySavingsReportUpdate;
        Context context = TadoApplication.getTadoAppContext();
        AlarmManager alarmManager = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (alarmManager == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.AlarmManager");
        }
        alarmManager.set(0, NotificationFormatterKt.getNotificationTimeMillis(energySavingsReportUpdate.getPreferredNotificationTime()), PendingIntent.getBroadcast(context, 0, EnergySavingsReportNotificationReceiver.getIntent(context, energySavingsReportUpdate), 0));
    }
}
