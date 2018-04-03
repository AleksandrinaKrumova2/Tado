package com.tado.android.mvp.model;

import android.databinding.ObservableArrayMap;
import com.google.gson.Gson;
import com.tado.android.location.LocationUtil;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0017J\u0018\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0017J\u0012\u0010\u0011\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0002J\u0014\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\tH\u0017J\u0014\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u000bH\u0017J\u0010\u0010\u0015\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006H\u0017J\u0010\u0010\u0016\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006H\u0017R\u000e\u0010\u0005\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/tado/android/mvp/model/HomeWifiLocalDataSource;", "Lcom/tado/android/mvp/model/HomeWifiDataSource;", "localDataSource", "Lcom/tado/android/mvp/model/LocalPersistenceHelper;", "(Lcom/tado/android/mvp/model/LocalPersistenceHelper;)V", "HOME_WIFIS", "", "IGNORED_WIFIS", "homeWifis", "Landroid/databinding/ObservableArrayMap;", "ignoredWifis", "", "addHomeWifi", "", "homeBSSID", "homeSSID", "addIgnoredWifi", "assertLocalDatasourceNotNull", "disk", "getHomeWifis", "getIgnoredWifis", "removeHomeWifi", "removeIgnoredWifi", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: HomeWifiLocalDataSource.kt */
public final class HomeWifiLocalDataSource implements HomeWifiDataSource {
    private final String HOME_WIFIS = "homeWifis";
    private final String IGNORED_WIFIS = "ignoredWifis";
    private ObservableArrayMap<String, String> homeWifis;
    private Map<String, String> ignoredWifis;
    private final LocalPersistenceHelper localDataSource;

    @org.jetbrains.annotations.NotNull
    public synchronized android.databinding.ObservableArrayMap<java.lang.String, java.lang.String> getHomeWifis() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.tado.android.mvp.model.HomeWifiLocalDataSource.getHomeWifis():android.databinding.ObservableArrayMap<java.lang.String, java.lang.String>
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
	at jadx.core.dex.nodes.MethodNode.addJump(MethodNode.java:370)
	at jadx.core.dex.nodes.MethodNode.initJumps(MethodNode.java:356)
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:106)
	... 7 more
*/
        /*
        r0 = this;
        monitor-enter(r11);
        r8 = r11.homeWifis;	 Catch:{ all -> 0x0058 }
        if (r8 != 0) goto L_0x0085;	 Catch:{ all -> 0x0058 }
    L_0x0005:
        r8 = r11.localDataSource;	 Catch:{ all -> 0x0058 }
        r9 = r11.HOME_WIFIS;	 Catch:{ all -> 0x0058 }
        r10 = "{}";	 Catch:{ all -> 0x0058 }
        r7 = r8.getString(r9, r10);	 Catch:{ all -> 0x0058 }
        r8 = new com.tado.android.mvp.model.HomeWifiLocalDataSource$getHomeWifis$type$1;	 Catch:{ all -> 0x0058 }
        r8.<init>();	 Catch:{ all -> 0x0058 }
        r6 = r8.getType();	 Catch:{ all -> 0x0058 }
        r8 = new com.google.gson.Gson;	 Catch:{ all -> 0x0058 }
        r8.<init>();	 Catch:{ all -> 0x0058 }
        r1 = r8.fromJson(r7, r6);	 Catch:{ all -> 0x0058 }
        r1 = (java.util.Map) r1;	 Catch:{ all -> 0x0058 }
        r8 = new android.databinding.ObservableArrayMap;	 Catch:{ all -> 0x0058 }
        r8.<init>();	 Catch:{ all -> 0x0058 }
        r11.homeWifis = r8;	 Catch:{ all -> 0x0058 }
        if (r1 == 0) goto L_0x0085;	 Catch:{ all -> 0x0058 }
    L_0x002d:
        r5 = r1;	 Catch:{ all -> 0x0058 }
        r8 = r5.keySet();	 Catch:{ all -> 0x0058 }
        r8 = (java.lang.Iterable) r8;	 Catch:{ all -> 0x0058 }
        r2 = new java.util.ArrayList;	 Catch:{ all -> 0x0058 }
        r2.<init>();	 Catch:{ all -> 0x0058 }
        r2 = (java.util.Collection) r2;	 Catch:{ all -> 0x0058 }
        r8 = r8.iterator();	 Catch:{ all -> 0x0058 }
    L_0x0040:
        r9 = r8.hasNext();	 Catch:{ all -> 0x0058 }
        if (r9 == 0) goto L_0x005b;	 Catch:{ all -> 0x0058 }
    L_0x0046:
        r4 = r8.next();	 Catch:{ all -> 0x0058 }
        r0 = r4;	 Catch:{ all -> 0x0058 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0058 }
        r5 = r0;	 Catch:{ all -> 0x0058 }
        r9 = com.tado.android.location.LocationUtil.isValidBSSID(r5);	 Catch:{ all -> 0x0058 }
        if (r9 == 0) goto L_0x0040;	 Catch:{ all -> 0x0058 }
    L_0x0054:
        r2.add(r4);	 Catch:{ all -> 0x0058 }
        goto L_0x0040;
    L_0x0058:
        r8 = move-exception;
        monitor-exit(r11);
        throw r8;
    L_0x005b:
        r2 = (java.util.List) r2;	 Catch:{ all -> 0x0058 }
        r2 = (java.lang.Iterable) r2;	 Catch:{ all -> 0x0058 }
        r8 = r2.iterator();	 Catch:{ all -> 0x0058 }
        r9 = r8.hasNext();	 Catch:{ all -> 0x0058 }
        if (r9 == 0) goto L_0x0082;	 Catch:{ all -> 0x0058 }
    L_0x006a:
        r3 = r8.next();	 Catch:{ all -> 0x0058 }
        r0 = r3;	 Catch:{ all -> 0x0058 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0058 }
        r5 = r0;	 Catch:{ all -> 0x0058 }
        r9 = r11.homeWifis;	 Catch:{ all -> 0x0058 }
        if (r9 != 0) goto L_0x0079;	 Catch:{ all -> 0x0058 }
        kotlin.jvm.internal.Intrinsics.throwNpe();	 Catch:{ all -> 0x0058 }
        r10 = r1.get(r5);	 Catch:{ all -> 0x0058 }
        r9.put(r5, r10);	 Catch:{ all -> 0x0058 }
        goto L_0x0064;	 Catch:{ all -> 0x0058 }
    L_0x0085:
        r8 = r11.homeWifis;	 Catch:{ all -> 0x0058 }
        if (r8 != 0) goto L_0x0092;	 Catch:{ all -> 0x0058 }
        r8 = new kotlin.TypeCastException;	 Catch:{ all -> 0x0058 }
        r9 = "null cannot be cast to non-null type android.databinding.ObservableArrayMap<kotlin.String, kotlin.String>";	 Catch:{ all -> 0x0058 }
        r8.<init>(r9);	 Catch:{ all -> 0x0058 }
        throw r8;	 Catch:{ all -> 0x0058 }
        monitor-exit(r11);
        return r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.mvp.model.HomeWifiLocalDataSource.getHomeWifis():android.databinding.ObservableArrayMap<java.lang.String, java.lang.String>");
    }

    public HomeWifiLocalDataSource(@NotNull LocalPersistenceHelper localDataSource) {
        Intrinsics.checkParameterIsNotNull(localDataSource, "localDataSource");
        this.localDataSource = localDataSource;
    }

    public synchronized void addHomeWifi(@NotNull String homeBSSID, @NotNull String homeSSID) {
        Intrinsics.checkParameterIsNotNull(homeBSSID, "homeBSSID");
        Intrinsics.checkParameterIsNotNull(homeSSID, "homeSSID");
        assertLocalDatasourceNotNull(this.localDataSource);
        if (LocationUtil.isValidBSSID(homeBSSID)) {
            if (this.homeWifis == null) {
                this.homeWifis = getHomeWifis();
            }
            removeIgnoredWifi(homeBSSID);
            ObservableArrayMap observableArrayMap = this.homeWifis;
            if (observableArrayMap != null) {
                String str = (String) observableArrayMap.put(homeBSSID, UserConfig.getSSIDWithoutQuotes(homeSSID));
            }
            LocalPersistenceHelper localPersistenceHelper = this.localDataSource;
            String str2 = this.HOME_WIFIS;
            String toJson = new Gson().toJson(this.homeWifis);
            Intrinsics.checkExpressionValueIsNotNull(toJson, "Gson().toJson(homeWifis)");
            localPersistenceHelper.putString(str2, toJson);
            Snitcher.start().log(3, "HomeWifi", "adding home Wifi: " + homeBSSID + " (" + homeSSID + ')', new Object[0]);
        }
    }

    public synchronized void removeHomeWifi(@NotNull String homeBSSID) {
        Intrinsics.checkParameterIsNotNull(homeBSSID, "homeBSSID");
        assertLocalDatasourceNotNull(this.localDataSource);
        if (this.homeWifis == null) {
            this.homeWifis = getHomeWifis();
        }
        ObservableArrayMap observableArrayMap = this.homeWifis;
        if (observableArrayMap == null) {
            Intrinsics.throwNpe();
        }
        if (observableArrayMap.containsKey(homeBSSID)) {
            observableArrayMap = this.homeWifis;
            if (observableArrayMap == null) {
                Intrinsics.throwNpe();
            }
            observableArrayMap.remove(homeBSSID);
            LocalPersistenceHelper localPersistenceHelper = this.localDataSource;
            String str = this.HOME_WIFIS;
            String toJson = new Gson().toJson(this.homeWifis);
            Intrinsics.checkExpressionValueIsNotNull(toJson, "Gson().toJson(homeWifis)");
            localPersistenceHelper.putString(str, toJson);
            Snitcher.start().log(3, "HomeWifi", "removed home Wifi: " + homeBSSID, new Object[0]);
        }
    }

    @NotNull
    public synchronized Map<String, String> getIgnoredWifis() {
        Map<String, String> map;
        if (this.ignoredWifis == null) {
            this.ignoredWifis = (Map) new Gson().fromJson(this.localDataSource.getString(this.IGNORED_WIFIS, "{}"), new HomeWifiLocalDataSource$getIgnoredWifis$type$1().getType());
        }
        map = this.ignoredWifis;
        if (map == null) {
            Intrinsics.throwNpe();
        }
        return map;
    }

    public synchronized void addIgnoredWifi(@NotNull String homeBSSID, @NotNull String homeSSID) {
        Intrinsics.checkParameterIsNotNull(homeBSSID, "homeBSSID");
        Intrinsics.checkParameterIsNotNull(homeSSID, "homeSSID");
        assertLocalDatasourceNotNull(this.localDataSource);
        if (this.ignoredWifis == null) {
            this.ignoredWifis = getIgnoredWifis();
        }
        removeHomeWifi(homeBSSID);
        if (LocationUtil.isValidBSSID(homeBSSID)) {
            String sSIDWithoutQuotes;
            Map map = this.ignoredWifis;
            if (map != null) {
                sSIDWithoutQuotes = UserConfig.getSSIDWithoutQuotes(homeSSID);
                Intrinsics.checkExpressionValueIsNotNull(sSIDWithoutQuotes, "getSSIDWithoutQuotes(homeSSID)");
                String str = (String) map.put(homeBSSID, sSIDWithoutQuotes);
            }
            LocalPersistenceHelper localPersistenceHelper = this.localDataSource;
            sSIDWithoutQuotes = this.IGNORED_WIFIS;
            String toJson = new Gson().toJson(this.ignoredWifis);
            Intrinsics.checkExpressionValueIsNotNull(toJson, "Gson().toJson(ignoredWifis)");
            localPersistenceHelper.putString(sSIDWithoutQuotes, toJson);
            Snitcher.start().log(3, "HomeWifi", "ignoring home Wifi: " + homeBSSID + " (" + homeSSID + ')', new Object[0]);
        }
    }

    public synchronized void removeIgnoredWifi(@NotNull String homeBSSID) {
        Intrinsics.checkParameterIsNotNull(homeBSSID, "homeBSSID");
        assertLocalDatasourceNotNull(this.localDataSource);
        if (this.ignoredWifis != null) {
            Map map = this.ignoredWifis;
            if (map == null) {
                Intrinsics.throwNpe();
            }
            if (map.containsKey(homeBSSID)) {
                map = this.ignoredWifis;
                if (map == null) {
                    Intrinsics.throwNpe();
                }
                map.remove(homeBSSID);
                LocalPersistenceHelper localPersistenceHelper = this.localDataSource;
                String str = this.IGNORED_WIFIS;
                String toJson = new Gson().toJson(this.ignoredWifis);
                Intrinsics.checkExpressionValueIsNotNull(toJson, "Gson().toJson(ignoredWifis)");
                localPersistenceHelper.putString(str, toJson);
                Snitcher.start().log(3, "HomeWifi", "removed ignored home Wifi: " + homeBSSID, new Object[0]);
            }
        }
    }

    private final void assertLocalDatasourceNotNull(LocalPersistenceHelper disk) {
        if (disk == null) {
            throw new IllegalStateException("local datasource is not set");
        }
    }
}
