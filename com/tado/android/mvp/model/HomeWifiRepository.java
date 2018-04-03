package com.tado.android.mvp.model;

import android.databinding.ObservableArrayMap;
import com.tado.android.utils.LegacyUserConfig;
import com.tado.android.utils.Snitcher;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\u001c\u0010\u000e\u001a\u00020\n2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u0010H\u0002J\u0018\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\u001c\u0010\u0012\u001a\u00020\n2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u0010H\u0002J\u0014\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u0014H\u0016J\u0014\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u0016H\u0016J\u000e\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0019J\u0010\u0010\u001a\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\u001b\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u001c"}, d2 = {"Lcom/tado/android/mvp/model/HomeWifiRepository;", "Lcom/tado/android/mvp/model/HomeWifiDataSource;", "()V", "localDataSource", "Lcom/tado/android/mvp/model/HomeWifiLocalDataSource;", "getLocalDataSource", "()Lcom/tado/android/mvp/model/HomeWifiLocalDataSource;", "setLocalDataSource", "(Lcom/tado/android/mvp/model/HomeWifiLocalDataSource;)V", "addHomeWifi", "", "homeBSSID", "", "homeSSID", "addHomeWifis", "wifis", "", "addIgnoredWifi", "addIgnoredWifis", "getHomeWifis", "Landroid/databinding/ObservableArrayMap;", "getIgnoredWifis", "", "migrateLegacyHomeWifiConfiguration", "legacyConfiguration", "Lcom/tado/android/utils/LegacyUserConfig;", "removeHomeWifi", "removeIgnoredWifi", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: HomeWifiRepository.kt */
public final class HomeWifiRepository implements HomeWifiDataSource {
    public static final HomeWifiRepository INSTANCE = new HomeWifiRepository();
    @Nullable
    private static HomeWifiLocalDataSource localDataSource;

    private HomeWifiRepository() {
    }

    @Nullable
    public final HomeWifiLocalDataSource getLocalDataSource() {
        return localDataSource;
    }

    public final void setLocalDataSource(@Nullable HomeWifiLocalDataSource <set-?>) {
        localDataSource = <set-?>;
    }

    @NotNull
    public ObservableArrayMap<String, String> getHomeWifis() {
        HomeWifiLocalDataSource homeWifiLocalDataSource = localDataSource;
        if (homeWifiLocalDataSource != null) {
            ObservableArrayMap<String, String> homeWifis = homeWifiLocalDataSource.getHomeWifis();
            if (homeWifis != null) {
                return homeWifis;
            }
        }
        return new ObservableArrayMap();
    }

    public void addHomeWifi(@NotNull String homeBSSID, @NotNull String homeSSID) {
        Intrinsics.checkParameterIsNotNull(homeBSSID, "homeBSSID");
        Intrinsics.checkParameterIsNotNull(homeSSID, "homeSSID");
        HomeWifiLocalDataSource homeWifiLocalDataSource = localDataSource;
        if (homeWifiLocalDataSource != null) {
            homeWifiLocalDataSource.addHomeWifi(homeBSSID, homeSSID);
        }
    }

    public void removeHomeWifi(@NotNull String homeBSSID) {
        Intrinsics.checkParameterIsNotNull(homeBSSID, "homeBSSID");
        HomeWifiLocalDataSource homeWifiLocalDataSource = localDataSource;
        if (homeWifiLocalDataSource != null) {
            homeWifiLocalDataSource.removeHomeWifi(homeBSSID);
        }
    }

    @NotNull
    public Map<String, String> getIgnoredWifis() {
        HomeWifiLocalDataSource homeWifiLocalDataSource = localDataSource;
        if (homeWifiLocalDataSource != null) {
            Map<String, String> ignoredWifis = homeWifiLocalDataSource.getIgnoredWifis();
            if (ignoredWifis != null) {
                return ignoredWifis;
            }
        }
        return new HashMap();
    }

    public void addIgnoredWifi(@NotNull String homeBSSID, @NotNull String homeSSID) {
        Intrinsics.checkParameterIsNotNull(homeBSSID, "homeBSSID");
        Intrinsics.checkParameterIsNotNull(homeSSID, "homeSSID");
        HomeWifiLocalDataSource homeWifiLocalDataSource = localDataSource;
        if (homeWifiLocalDataSource != null) {
            homeWifiLocalDataSource.addIgnoredWifi(homeBSSID, homeSSID);
        }
    }

    public void removeIgnoredWifi(@NotNull String homeBSSID) {
        Intrinsics.checkParameterIsNotNull(homeBSSID, "homeBSSID");
        HomeWifiLocalDataSource homeWifiLocalDataSource = localDataSource;
        if (homeWifiLocalDataSource != null) {
            homeWifiLocalDataSource.removeIgnoredWifi(homeBSSID);
        }
    }

    private final void addHomeWifis(Map<String, String> wifis) {
        for (Entry wifi : wifis.entrySet()) {
            addHomeWifi((String) wifi.getKey(), (String) wifi.getValue());
        }
    }

    private final void addIgnoredWifis(Map<String, String> wifis) {
        for (Entry wifi : wifis.entrySet()) {
            addIgnoredWifi((String) wifi.getKey(), (String) wifi.getValue());
        }
    }

    public final void migrateLegacyHomeWifiConfiguration(@NotNull LegacyUserConfig legacyConfiguration) {
        Intrinsics.checkParameterIsNotNull(legacyConfiguration, "legacyConfiguration");
        Snitcher.start().log(3, "HomeWifi", "migrating old home wifi configuration", new Object[0]);
        HomeWifiRepository homeWifiRepository = INSTANCE;
        HashMap homeWifis = legacyConfiguration.getHomeWifis();
        Intrinsics.checkExpressionValueIsNotNull(homeWifis, "legacyConfiguration.homeWifis");
        homeWifiRepository.addHomeWifis(homeWifis);
        legacyConfiguration.removeHomeWifis();
        HomeWifiRepository homeWifiRepository2 = INSTANCE;
        Map ignoredWifis = legacyConfiguration.getIgnoredWifis();
        Intrinsics.checkExpressionValueIsNotNull(ignoredWifis, "legacyConfiguration.ignoredWifis");
        homeWifiRepository2.addIgnoredWifis(ignoredWifis);
        legacyConfiguration.removeIgnoredWifis();
    }
}
