package com.tado.android.demo.marketing;

import android.content.Context;
import com.tado.android.rest.model.Zone.TypeEnum;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joda.time.DateTime;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000bJ\b\u0010\u0011\u001a\u00020\u0012H\u0002J\u0006\u0010\u0013\u001a\u00020\u000eJ\u000e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0016J\u0018\u0010\u0017\u001a\u00020\u000e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0018\u001a\u00020\u0019J\u001a\u0010\u001a\u001a\u00020\u000e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0010\u001a\u00020\u000bH\u0002J\u0006\u0010\u001b\u001a\u00020\u000eR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/tado/android/demo/marketing/MarketingAlertsManager;", "", "()V", "dialog", "Lcom/tado/android/demo/marketing/AbstractMarketingDialog;", "inDemo", "", "startDemoTimestamp", "Lorg/joda/time/DateTime;", "state", "Ljava/util/HashMap;", "Lcom/tado/android/demo/marketing/MarketingAlertTypeEnum;", "Lcom/tado/android/demo/marketing/MarketingScreenState;", "cancelDelayedAlerts", "", "featureSeen", "type", "millisSinceDemoStart", "", "reset", "showAlert", "context", "Landroid/content/Context;", "showDelayedAlert", "zoneType", "Lcom/tado/android/rest/model/Zone$TypeEnum;", "showMarketingAlert", "startDemo", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: MarketingAlertsManager.kt */
public final class MarketingAlertsManager {
    public static final MarketingAlertsManager INSTANCE = new MarketingAlertsManager();
    private static AbstractMarketingDialog dialog;
    private static boolean inDemo;
    private static DateTime startDemoTimestamp;
    private static HashMap<MarketingAlertTypeEnum, MarketingScreenState> state = new HashMap();

    public final void showAlert(@org.jetbrains.annotations.NotNull android.content.Context r1) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.tado.android.demo.marketing.MarketingAlertsManager.showAlert(android.content.Context):void
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
        r5 = "context";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r5);
        r5 = inDemo;
        if (r5 != 0) goto L_0x000b;
    L_0x000a:
        return;
    L_0x000b:
        r0 = state;
        r0 = (java.util.Map) r0;
        r5 = r0.entrySet();
        r5 = r5.iterator();
        r6 = r5.hasNext();
        if (r6 == 0) goto L_0x0043;
    L_0x001d:
        r2 = r5.next();
        r2 = (java.util.Map.Entry) r2;
        r1 = r2;
        r4 = r1.getKey();
        r4 = (com.tado.android.demo.marketing.MarketingAlertTypeEnum) r4;
        r3 = r1.getValue();
        r3 = (com.tado.android.demo.marketing.MarketingScreenState) r3;
        r6 = r3.getScreenSeen();
        if (r6 == 0) goto L_0x0041;
        r6 = r3.getAlertShown();
        if (r6 != 0) goto L_0x0041;
        r6 = INSTANCE;
        r6.showMarketingAlert(r8, r4);
        goto L_0x0017;
        goto L_0x000a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.demo.marketing.MarketingAlertsManager.showAlert(android.content.Context):void");
    }

    private MarketingAlertsManager() {
    }

    public final void startDemo() {
        DateTime now = DateTime.now();
        Intrinsics.checkExpressionValueIsNotNull(now, "DateTime.now()");
        startDemoTimestamp = now;
        inDemo = true;
    }

    public final void featureSeen(@NotNull MarketingAlertTypeEnum type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        if (!state.containsKey(type)) {
            state.put(type, new MarketingScreenState(false, false, 3, null));
        }
    }

    public final void showDelayedAlert(@Nullable Context context, @NotNull TypeEnum zoneType) {
        Intrinsics.checkParameterIsNotNull(zoneType, "zoneType");
        MarketingAlertTypeEnum type = TypeEnum.AIR_CONDITIONING == zoneType ? MarketingAlertTypeEnum.AC_ZONE : MarketingAlertTypeEnum.REQUEST_CONSULTATION;
        if (inDemo) {
            MarketingScreenState marketingScreenState = (MarketingScreenState) state.get(type);
            if (marketingScreenState == null || !marketingScreenState.getAlertShown()) {
                featureSeen(type);
                showMarketingAlert(context, type);
            }
        }
    }

    public final void cancelDelayedAlerts() {
        AbstractMarketingDialog abstractMarketingDialog = dialog;
        if (abstractMarketingDialog != null) {
            abstractMarketingDialog.cancel();
        }
    }

    public final void reset() {
        inDemo = false;
        state.clear();
        AbstractMarketingDialog abstractMarketingDialog = dialog;
        if (abstractMarketingDialog != null) {
            abstractMarketingDialog.cancel();
        }
        dialog = (AbstractMarketingDialog) null;
    }

    private final long millisSinceDemoStart() {
        DateTime now = DateTime.now();
        DateTime dateTime = startDemoTimestamp;
        if (dateTime == null) {
            Intrinsics.throwUninitializedPropertyAccessException("startDemoTimestamp");
        }
        now = now.minus(dateTime.getMillis());
        Intrinsics.checkExpressionValueIsNotNull(now, "DateTime.now().minus(startDemoTimestamp.millis)");
        return now.getMillis();
    }

    private final void showMarketingAlert(Context context, MarketingAlertTypeEnum type) {
        if (context != null) {
            Context it = context;
            AbstractMarketingDialog abstractMarketingDialog = dialog;
            if (abstractMarketingDialog != null) {
                abstractMarketingDialog.cancel();
            }
            dialog = MarketingDialogFactory.Companion.create(context, type, INSTANCE.millisSinceDemoStart(), new MarketingAlertsManager$showMarketingAlert$$inlined$let$lambda$1(context, type));
            AbstractMarketingDialog abstractMarketingDialog2 = dialog;
            if (abstractMarketingDialog2 != null) {
                abstractMarketingDialog2.present(new MarketingAlertsManager$showMarketingAlert$$inlined$let$lambda$2(context, type));
            }
        }
    }
}
