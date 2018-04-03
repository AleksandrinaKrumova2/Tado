package com.tado.android.rest.model;

import com.tado.android.mvp.model.TadoModeEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\nJ$\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/tado/android/rest/model/HomeState;", "", "presence", "Lcom/tado/android/mvp/model/TadoModeEnum;", "showHomePresenceSwitchButton", "", "(Lcom/tado/android/mvp/model/TadoModeEnum;Ljava/lang/Boolean;)V", "getPresence", "()Lcom/tado/android/mvp/model/TadoModeEnum;", "getShowHomePresenceSwitchButton", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "copy", "(Lcom/tado/android/mvp/model/TadoModeEnum;Ljava/lang/Boolean;)Lcom/tado/android/rest/model/HomeState;", "equals", "other", "hashCode", "", "toString", "", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: HomeState.kt */
public final class HomeState {
    @NotNull
    private final TadoModeEnum presence;
    @Nullable
    private final Boolean showHomePresenceSwitchButton;

    @NotNull
    public static /* bridge */ /* synthetic */ HomeState copy$default(HomeState homeState, TadoModeEnum tadoModeEnum, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            tadoModeEnum = homeState.presence;
        }
        if ((i & 2) != 0) {
            bool = homeState.showHomePresenceSwitchButton;
        }
        return homeState.copy(tadoModeEnum, bool);
    }

    @NotNull
    public final TadoModeEnum component1() {
        return this.presence;
    }

    @Nullable
    public final Boolean component2() {
        return this.showHomePresenceSwitchButton;
    }

    @NotNull
    public final HomeState copy(@NotNull TadoModeEnum presence, @Nullable Boolean showHomePresenceSwitchButton) {
        Intrinsics.checkParameterIsNotNull(presence, "presence");
        return new HomeState(presence, showHomePresenceSwitchButton);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r3) {
        /*
        r2 = this;
        if (r2 == r3) goto L_0x001c;
    L_0x0002:
        r0 = r3 instanceof com.tado.android.rest.model.HomeState;
        if (r0 == 0) goto L_0x001e;
    L_0x0006:
        r3 = (com.tado.android.rest.model.HomeState) r3;
        r0 = r2.presence;
        r1 = r3.presence;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x001e;
    L_0x0012:
        r0 = r2.showHomePresenceSwitchButton;
        r1 = r3.showHomePresenceSwitchButton;
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
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.rest.model.HomeState.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i = 0;
        TadoModeEnum tadoModeEnum = this.presence;
        int hashCode = (tadoModeEnum != null ? tadoModeEnum.hashCode() : 0) * 31;
        Boolean bool = this.showHomePresenceSwitchButton;
        if (bool != null) {
            i = bool.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "HomeState(presence=" + this.presence + ", showHomePresenceSwitchButton=" + this.showHomePresenceSwitchButton + ")";
    }

    public HomeState(@NotNull TadoModeEnum presence, @Nullable Boolean showHomePresenceSwitchButton) {
        Intrinsics.checkParameterIsNotNull(presence, "presence");
        this.presence = presence;
        this.showHomePresenceSwitchButton = showHomePresenceSwitchButton;
    }

    @NotNull
    public final TadoModeEnum getPresence() {
        return this.presence;
    }

    @Nullable
    public final Boolean getShowHomePresenceSwitchButton() {
        return this.showHomePresenceSwitchButton;
    }
}
