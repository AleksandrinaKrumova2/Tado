package com.tado.android.mvp.model;

import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.tado.android.rest.model.installation.BatteryStateEnum;
import com.tado.android.utils.Snitcher;
import java.util.HashMap;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u001a\u001a\u00020\u001bJ\u0010\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u0005H\u0016J\u0006\u0010\u001e\u001a\u00020\u001bJ\u000e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00050 H\u0016J\u000e\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00050 H\u0016J\b\u0010\"\u001a\u00020\u001bH\u0002J\u0010\u0010#\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u0005H\u0016J\u0010\u0010$\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u0005H\u0016J\u0010\u0010%\u001a\u00020&2\u0006\u0010\u001d\u001a\u00020\u0005H\u0016J\u0018\u0010'\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u00052\u0006\u0010(\u001a\u00020)H\u0016J\"\u0010*\u001a\u00020\u001b2\u0018\u0010+\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020/0-0,H\u0016R\u0014\u0010\t\u001a\u00020\u0005XD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR6\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\rj\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e`\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u00060"}, d2 = {"Lcom/tado/android/mvp/model/LowBatteryRepository;", "Lcom/tado/android/mvp/model/LowBatteryDatasource;", "context", "Landroid/content/Context;", "username", "", "homeID", "", "(Landroid/content/Context;Ljava/lang/String;I)V", "TAG", "getTAG", "()Ljava/lang/String;", "map", "Ljava/util/HashMap;", "Lcom/tado/android/mvp/model/NotificationState;", "Lkotlin/collections/HashMap;", "getMap", "()Ljava/util/HashMap;", "setMap", "(Ljava/util/HashMap;)V", "preferences", "Landroid/content/SharedPreferences;", "getPreferences", "()Landroid/content/SharedPreferences;", "setPreferences", "(Landroid/content/SharedPreferences;)V", "clear", "", "clearNotificationState", "serialNumber", "dump", "getNotifications", "", "getNotificationsToShow", "persist", "setNotificationPending", "setNotificationShown", "shouldShowNotification", "", "updateDeviceBatteryStatus", "status", "Lcom/tado/android/rest/model/installation/BatteryStateEnum;", "updateDevices", "devices", "", "Landroid/util/Pair;", "Lcom/tado/android/rest/model/Zone;", "Lcom/tado/android/rest/model/installation/GenericHardwareDevice;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: LowBatteryRepository.kt */
public final class LowBatteryRepository implements LowBatteryDatasource {
    @NotNull
    private final String TAG;
    @NotNull
    private HashMap<String, NotificationState> map;
    @NotNull
    private SharedPreferences preferences;

    public LowBatteryRepository(@org.jetbrains.annotations.NotNull android.content.Context r1, @org.jetbrains.annotations.NotNull java.lang.String r2, int r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.tado.android.mvp.model.LowBatteryRepository.<init>(android.content.Context, java.lang.String, int):void
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r0 = this;
        r4 = "context";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r4);
        r4 = "username";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r4);
        r7.<init>();
        r4 = "LowBatteryRepository";
        r7.TAG = r4;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "low-battery-notifications-";
        r4 = r4.append(r5);
        r4 = r4.append(r10);
        r5 = 45;
        r4 = r4.append(r5);
        r4 = r4.append(r9);
        r4 = r4.toString();
        r5 = 0;
        r4 = r8.getSharedPreferences(r4, r5);
        r5 = "context.getSharedPrefere…e\", Context.MODE_PRIVATE)";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5);
        r7.preferences = r4;
        r4 = new java.util.HashMap;
        r4.<init>();
        r7.map = r4;
        r4 = r7.preferences;
        r5 = "status";
        r6 = 0;
        r2 = r4.getString(r5, r6);
        if (r2 == 0) goto L_0x0072;
    L_0x0052:
        r1 = r2;
        r4 = new com.tado.android.mvp.model.LowBatteryRepository$1$type$1;	 Catch:{ Exception -> 0x0073 }
        r4.<init>();	 Catch:{ Exception -> 0x0073 }
        r3 = r4.getType();	 Catch:{ Exception -> 0x0073 }
        r4 = new com.google.gson.Gson;	 Catch:{ Exception -> 0x0073 }
        r4.<init>();	 Catch:{ Exception -> 0x0073 }
        r4 = r4.fromJson(r1, r3);	 Catch:{ Exception -> 0x0073 }
        r5 = "Gson().fromJson(it, type)";	 Catch:{ Exception -> 0x0073 }
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5);	 Catch:{ Exception -> 0x0073 }
        r4 = (java.util.HashMap) r4;	 Catch:{ Exception -> 0x0073 }
        r7.map = r4;	 Catch:{ Exception -> 0x0073 }
    L_0x0072:
        return;
    L_0x0073:
        r0 = move-exception;
        r7.clear();
        goto L_0x0070;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.mvp.model.LowBatteryRepository.<init>(android.content.Context, java.lang.String, int):void");
    }

    public final void dump() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.tado.android.mvp.model.LowBatteryRepository.dump():void
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r0 = this;
        r0 = r9.map;
        r0 = (java.util.Map) r0;
        r3 = r0.entrySet();
        r4 = r3.iterator();
        r3 = r4.hasNext();
        if (r3 == 0) goto L_0x0053;
    L_0x0012:
        r1 = r4.next();
        r1 = (java.util.Map.Entry) r1;
        r2 = r1;
        r5 = com.tado.android.utils.Snitcher.start();
        r6 = 3;
        r7 = r9.TAG;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r8 = "low battery notification for ";
        r8 = r3.append(r8);
        r3 = r2.getKey();
        r3 = (java.lang.String) r3;
        r3 = r8.append(r3);
        r8 = " is ";
        r8 = r3.append(r8);
        r3 = r2.getValue();
        r3 = (com.tado.android.mvp.model.NotificationState) r3;
        r3 = r8.append(r3);
        r3 = r3.toString();
        r8 = 0;
        r8 = new java.lang.Object[r8];
        r5.log(r6, r7, r3, r8);
        goto L_0x000c;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.mvp.model.LowBatteryRepository.dump():void");
    }

    @org.jetbrains.annotations.NotNull
    public java.util.Set<java.lang.String> getNotificationsToShow() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.tado.android.mvp.model.LowBatteryRepository.getNotificationsToShow():java.util.Set<java.lang.String>
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r0 = this;
        r0 = r7.map;
        r0 = (java.util.Map) r0;
        r1 = new java.util.LinkedHashMap;
        r1.<init>();
        r1 = (java.util.Map) r1;
        r4 = r0.entrySet();
        r5 = r4.iterator();
        r4 = r5.hasNext();
        if (r4 == 0) goto L_0x003a;
    L_0x0019:
        r2 = r5.next();
        r2 = (java.util.Map.Entry) r2;
        r3 = r2;
        r4 = r3.getValue();
        r4 = (com.tado.android.mvp.model.NotificationState) r4;
        r6 = com.tado.android.mvp.model.NotificationState.PENDING;
        r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r6);
        if (r4 == 0) goto L_0x0013;
        r4 = r2.getKey();
        r6 = r2.getValue();
        r1.put(r4, r6);
        goto L_0x0013;
        r4 = r1.keySet();
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.mvp.model.LowBatteryRepository.getNotificationsToShow():java.util.Set<java.lang.String>");
    }

    public void updateDevices(@org.jetbrains.annotations.NotNull java.util.List<android.util.Pair<com.tado.android.rest.model.Zone, com.tado.android.rest.model.installation.GenericHardwareDevice>> r1) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.tado.android.mvp.model.LowBatteryRepository.updateDevices(java.util.List):void
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r0 = this;
        r3 = "devices";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r3);
        r0 = r8;
        r0 = (java.lang.Iterable) r0;
        r4 = r0.iterator();
        r3 = r4.hasNext();
        if (r3 == 0) goto L_0x0057;
    L_0x0013:
        r1 = r4.next();
        r2 = r1;
        r2 = (android.util.Pair) r2;
        r3 = r2.second;
        r5 = "it.second";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r5);
        r3 = (com.tado.android.rest.model.installation.GenericHardwareDevice) r3;
        r3 = r3.getBatteryState();
        if (r3 == 0) goto L_0x0055;
        r3 = r2.second;
        r5 = "it.second";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r5);
        r3 = (com.tado.android.rest.model.installation.GenericHardwareDevice) r3;
        r5 = r3.getSerialNo();
        r3 = "it.second.serialNo";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r3);
        r3 = r2.second;
        r6 = "it.second";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r6);
        r3 = (com.tado.android.rest.model.installation.GenericHardwareDevice) r3;
        r3 = r3.getBatteryState();
        r6 = "it.second.batteryState";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r6);
        r7.updateDeviceBatteryStatus(r5, r3);
        goto L_0x000d;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.mvp.model.LowBatteryRepository.updateDevices(java.util.List):void");
    }

    @NotNull
    public final String getTAG() {
        return this.TAG;
    }

    @NotNull
    public final SharedPreferences getPreferences() {
        return this.preferences;
    }

    public final void setPreferences(@NotNull SharedPreferences <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.preferences = <set-?>;
    }

    @NotNull
    public final HashMap<String, NotificationState> getMap() {
        return this.map;
    }

    public final void setMap(@NotNull HashMap<String, NotificationState> <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.map = <set-?>;
    }

    public void updateDeviceBatteryStatus(@NotNull String serialNumber, @NotNull BatteryStateEnum status) {
        Intrinsics.checkParameterIsNotNull(serialNumber, "serialNumber");
        Intrinsics.checkParameterIsNotNull(status, "status");
        switch (status) {
            case NORMAL:
                clearNotificationState(serialNumber);
                return;
            case LOW:
                if (!this.map.containsKey(serialNumber)) {
                    setNotificationShown(serialNumber);
                    return;
                } else if (this.map.containsKey(serialNumber) && Intrinsics.areEqual((NotificationState) this.map.get(serialNumber), NotificationState.NOT_SHOWN)) {
                    setNotificationPending(serialNumber);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    @NotNull
    public Set<String> getNotifications() {
        Set<String> keySet = this.map.keySet();
        Intrinsics.checkExpressionValueIsNotNull(keySet, "map.keys");
        return keySet;
    }

    public boolean shouldShowNotification(@NotNull String serialNumber) {
        Intrinsics.checkParameterIsNotNull(serialNumber, "serialNumber");
        return Intrinsics.areEqual((NotificationState) this.map.get(serialNumber), NotificationState.PENDING);
    }

    public void setNotificationShown(@NotNull String serialNumber) {
        Intrinsics.checkParameterIsNotNull(serialNumber, "serialNumber");
        this.map.put(serialNumber, NotificationState.SHOWN_OR_NOT_INITIALIZED);
        persist();
        Snitcher.start().log(3, this.TAG, "low battery notification for " + serialNumber + " changed to SHOWN", new Object[0]);
    }

    public void setNotificationPending(@NotNull String serialNumber) {
        Intrinsics.checkParameterIsNotNull(serialNumber, "serialNumber");
        this.map.put(serialNumber, NotificationState.PENDING);
        persist();
        Snitcher.start().log(3, this.TAG, "low battery notification for " + serialNumber + " changed to PENDING", new Object[0]);
    }

    public void clearNotificationState(@NotNull String serialNumber) {
        Intrinsics.checkParameterIsNotNull(serialNumber, "serialNumber");
        this.map.put(serialNumber, NotificationState.NOT_SHOWN);
        persist();
        Snitcher.start().log(3, this.TAG, "low battery notification for " + serialNumber + " changed to NOT_SHOWN", new Object[0]);
    }

    public final void clear() {
        this.map.clear();
        persist();
        Snitcher.start().log(3, this.TAG, "Repo cleared", new Object[0]);
    }

    private final void persist() {
        this.preferences.edit().putString("status", new Gson().toJson(this.map)).apply();
    }
}
