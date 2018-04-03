package com.tado.android.demo.marketing;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lcom/tado/android/demo/marketing/MarketingScreenState;", "", "screenSeen", "", "alertShown", "(ZZ)V", "getAlertShown", "()Z", "setAlertShown", "(Z)V", "getScreenSeen", "setScreenSeen", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: MarketingAlertsManager.kt */
public final class MarketingScreenState {
    private boolean alertShown;
    private boolean screenSeen;

    public MarketingScreenState() {
        this(false, false, 3, null);
    }

    public MarketingScreenState(boolean screenSeen, boolean alertShown) {
        this.screenSeen = screenSeen;
        this.alertShown = alertShown;
    }

    public /* synthetic */ MarketingScreenState(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        this(z, z2);
    }

    public final boolean getAlertShown() {
        return this.alertShown;
    }

    public final boolean getScreenSeen() {
        return this.screenSeen;
    }

    public final void setAlertShown(boolean <set-?>) {
        this.alertShown = <set-?>;
    }

    public final void setScreenSeen(boolean <set-?>) {
        this.screenSeen = <set-?>;
    }
}
