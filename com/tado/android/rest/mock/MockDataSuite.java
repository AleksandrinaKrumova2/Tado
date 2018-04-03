package com.tado.android.rest.mock;

import com.tado.android.settings.cooling.AcSetupSettingsActivity;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B-\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\u0007J\u0015\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0015\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J5\u0010\r\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00032\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0004HÖ\u0001R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0014"}, d2 = {"Lcom/tado/android/rest/mock/MockDataSuite;", "", "configuration", "", "", "pairs", "Lcom/tado/android/rest/mock/MockDataPair;", "(Ljava/util/Map;Ljava/util/Map;)V", "getConfiguration", "()Ljava/util/Map;", "getPairs", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: MockDataModels.kt */
public final class MockDataSuite {
    @NotNull
    private final Map<String, String> configuration;
    @NotNull
    private final Map<String, MockDataPair> pairs;

    @NotNull
    public static /* bridge */ /* synthetic */ MockDataSuite copy$default(MockDataSuite mockDataSuite, Map map, Map map2, int i, Object obj) {
        if ((i & 1) != 0) {
            map = mockDataSuite.configuration;
        }
        if ((i & 2) != 0) {
            map2 = mockDataSuite.pairs;
        }
        return mockDataSuite.copy(map, map2);
    }

    @NotNull
    public final Map<String, String> component1() {
        return this.configuration;
    }

    @NotNull
    public final Map<String, MockDataPair> component2() {
        return this.pairs;
    }

    @NotNull
    public final MockDataSuite copy(@NotNull Map<String, String> configuration, @NotNull Map<String, MockDataPair> pairs) {
        Intrinsics.checkParameterIsNotNull(configuration, AcSetupSettingsActivity.KEY_CONFIGURATION);
        Intrinsics.checkParameterIsNotNull(pairs, "pairs");
        return new MockDataSuite(configuration, pairs);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r3) {
        /*
        r2 = this;
        if (r2 == r3) goto L_0x001c;
    L_0x0002:
        r0 = r3 instanceof com.tado.android.rest.mock.MockDataSuite;
        if (r0 == 0) goto L_0x001e;
    L_0x0006:
        r3 = (com.tado.android.rest.mock.MockDataSuite) r3;
        r0 = r2.configuration;
        r1 = r3.configuration;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x001e;
    L_0x0012:
        r0 = r2.pairs;
        r1 = r3.pairs;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x001e;
    L_0x001c:
        r0 = 1;
    L_0x001d:
        return r0;
    L_0x001e:
        r0 = 0;
        goto L_0x001d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.rest.mock.MockDataSuite.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i = 0;
        Map map = this.configuration;
        int hashCode = (map != null ? map.hashCode() : 0) * 31;
        Map map2 = this.pairs;
        if (map2 != null) {
            i = map2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "MockDataSuite(configuration=" + this.configuration + ", pairs=" + this.pairs + ")";
    }

    public MockDataSuite(@NotNull Map<String, String> configuration, @NotNull Map<String, MockDataPair> pairs) {
        Intrinsics.checkParameterIsNotNull(configuration, AcSetupSettingsActivity.KEY_CONFIGURATION);
        Intrinsics.checkParameterIsNotNull(pairs, "pairs");
        this.configuration = configuration;
        this.pairs = pairs;
    }

    @NotNull
    public final Map<String, String> getConfiguration() {
        return this.configuration;
    }

    @NotNull
    public final Map<String, MockDataPair> getPairs() {
        return this.pairs;
    }
}
