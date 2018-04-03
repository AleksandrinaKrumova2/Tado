package com.tado.android.rest.mock;

import android.content.Context;
import com.google.gson.Gson;
import com.tado.android.app.TadoApplication;
import com.tado.android.utils.FileUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0005H\u0002J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\u0005J\u0006\u0010\u0016\u001a\u00020\u0015R&\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0017"}, d2 = {"Lcom/tado/android/rest/mock/MockDataSource;", "", "()V", "loadedConfiguration", "", "", "getLoadedConfiguration", "()Ljava/util/Map;", "setLoadedConfiguration", "(Ljava/util/Map;)V", "loadedTemplates", "", "Lcom/tado/android/rest/mock/MockDataPair;", "getLoadedTemplates", "()Ljava/util/List;", "setLoadedTemplates", "(Ljava/util/List;)V", "loadSuite", "Lcom/tado/android/rest/mock/MockDataSuite;", "suiteName", "prepareSuite", "", "reset", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: MockDataSource.kt */
public final class MockDataSource {
    public static final MockDataSource INSTANCE = new MockDataSource();
    @NotNull
    private static Map<String, String> loadedConfiguration = MapsKt__MapsKt.emptyMap();
    @NotNull
    private static List<MockDataPair> loadedTemplates = new ArrayList();

    private MockDataSource() {
    }

    @NotNull
    public final Map<String, String> getLoadedConfiguration() {
        return loadedConfiguration;
    }

    public final void setLoadedConfiguration(@NotNull Map<String, String> <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        loadedConfiguration = <set-?>;
    }

    @NotNull
    public final List<MockDataPair> getLoadedTemplates() {
        return loadedTemplates;
    }

    public final void setLoadedTemplates(@NotNull List<MockDataPair> <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        loadedTemplates = <set-?>;
    }

    private final MockDataSuite loadSuite(String suiteName) {
        try {
            Context context = TadoApplication.getTadoAppContext();
            String json = FileUtils.loadJSONFromAsset(context != null ? context.getAssets() : null, "MobileMockMagic/suites/" + suiteName + ".json");
            if (json != null) {
                String it = json;
                Object fromJson = new Gson().fromJson(json, MockDataSuite.class);
                Intrinsics.checkExpressionValueIsNotNull(fromJson, "Gson().fromJson(json, MockDataSuite::class.java)");
                return (MockDataSuite) fromJson;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new MockDataSuite(MapsKt__MapsKt.emptyMap(), MapsKt__MapsKt.emptyMap());
    }

    public final void reset() {
        loadedTemplates.clear();
    }

    public final void prepareSuite(@NotNull String suiteName) {
        Intrinsics.checkParameterIsNotNull(suiteName, "suiteName");
        MockDataSuite suite = loadSuite(suiteName);
        loadedConfiguration = suite.getConfiguration();
        for (Entry pair : suite.getPairs().entrySet()) {
            loadedTemplates.add(pair.getValue());
        }
    }
}
