package com.tado.android.mvp.model;

import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0014\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\tH&J\u0014\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\tH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\r"}, d2 = {"Lcom/tado/android/mvp/model/HomeWifiDataSource;", "", "addHomeWifi", "", "homeBSSID", "", "homeSSID", "addIgnoredWifi", "getHomeWifis", "", "getIgnoredWifis", "removeHomeWifi", "removeIgnoredWifi", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: HomeWifiDataSource.kt */
public interface HomeWifiDataSource {
    void addHomeWifi(@NotNull String str, @NotNull String str2);

    void addIgnoredWifi(@NotNull String str, @NotNull String str2);

    @NotNull
    Map<String, String> getHomeWifis();

    @NotNull
    Map<String, String> getIgnoredWifis();

    void removeHomeWifi(@NotNull String str);

    void removeIgnoredWifi(@NotNull String str);
}
