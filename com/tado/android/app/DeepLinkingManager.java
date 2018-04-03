package com.tado.android.app;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import com.tado.android.MainZoneActivity;
import com.tado.android.analytics.AnalyticsConstants.Events;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.controllers.ZoneController;
import com.tado.android.premium.PremiumCarouselActivity;
import com.tado.android.report.ReportActivity;
import com.tado.android.rest.model.HomeInfo.LicenseEnum;
import com.tado.android.rest.model.Zone;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.settings.EnergySavingsActivity;
import com.tado.android.settings.appsettings.AppSettingsActivity;
import com.tado.android.settings.users.PeoplePreferenceActivity;
import com.tado.android.settings.zonesettings.ZonePreferenceActivity;
import com.tado.android.times.SmartScheduleActivity;
import com.tado.android.utils.UserConfig;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0016\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\rJ\u001e\u0010\u0011\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\f\u001a\u00020\rH\u0002J\u001e\u0010\u0013\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\f\u001a\u00020\rH\u0002J\u0018\u0010\u0014\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u001e\u0010\u0015\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u0016\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u0017\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0002¨\u0006\u0018"}, d2 = {"Lcom/tado/android/app/DeepLinkingManager;", "", "()V", "getZoneId", "", "pathSegment", "", "(Ljava/lang/String;)Ljava/lang/Integer;", "parseHttpIntent", "", "data", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "parseIntent", "intent", "Landroid/content/Intent;", "parseInvitation", "", "parseSettingsIntent", "parseTadoIntent", "parseZoneIntent", "startEnergySavingsReport", "startUpgradeCarousel", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: DeepLinkingManager.kt */
public final class DeepLinkingManager {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean parseIntent(@org.jetbrains.annotations.NotNull android.content.Intent r3, @org.jetbrains.annotations.NotNull android.content.Context r4) {
        /*
        r2 = this;
        r0 = "intent";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0);
        r0 = "context";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0);
        r0 = "android.intent.action.VIEW";
        r1 = r3.getAction();
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x002f;
    L_0x0019:
        r0 = r3.getData();
        if (r0 == 0) goto L_0x002f;
    L_0x001f:
        r0 = r3.getData();
        r1 = "intent.data";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1);
        r0 = r0.getScheme();
        if (r0 != 0) goto L_0x0031;
    L_0x002f:
        r0 = 0;
    L_0x0030:
        return r0;
    L_0x0031:
        r1 = r0.hashCode();
        switch(r1) {
            case 3213448: goto L_0x0039;
            case 3552184: goto L_0x005b;
            case 99617003: goto L_0x0051;
            default: goto L_0x0038;
        };
    L_0x0038:
        goto L_0x002f;
    L_0x0039:
        r1 = "http";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x002f;
    L_0x0042:
        r0 = r3.getData();
        r1 = "intent.data";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1);
        r0 = r2.parseHttpIntent(r0, r4);
        goto L_0x0030;
    L_0x0051:
        r1 = "https";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x002f;
    L_0x005a:
        goto L_0x0042;
    L_0x005b:
        r1 = "tado";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x002f;
    L_0x0064:
        r0 = r3.getData();
        r1 = "intent.data";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1);
        r0 = r2.parseTadoIntent(r0, r4);
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.app.DeepLinkingManager.parseIntent(android.content.Intent, android.content.Context):boolean");
    }

    private final boolean parseTadoIntent(Uri data, Context context) {
        if (UserConfig.getHomeId() < 0) {
            return false;
        }
        String host = data.getHost();
        if (host == null) {
            return false;
        }
        List pathSegments;
        switch (host.hashCode()) {
            case -588784979:
                if (host.equals("energy-savings-report")) {
                    return startEnergySavingsReport(context);
                }
                return false;
            case 116085319:
                if (!host.equals("zones")) {
                    return false;
                }
                pathSegments = data.getPathSegments();
                Intrinsics.checkExpressionValueIsNotNull(pathSegments, "data.pathSegments");
                return parseZoneIntent(pathSegments, context);
            case 1382417574:
                if (host.equals("premium-upgrade")) {
                    return startUpgradeCarousel(context);
                }
                return false;
            case 1434631203:
                if (!host.equals("settings")) {
                    return false;
                }
                pathSegments = data.getPathSegments();
                Intrinsics.checkExpressionValueIsNotNull(pathSegments, "data.pathSegments");
                return parseSettingsIntent(pathSegments, context);
            default:
                return false;
        }
    }

    private final boolean parseHttpIntent(Uri data, Context context) {
        int i;
        List pathSegments = data.getPathSegments();
        Intrinsics.checkExpressionValueIsNotNull(pathSegments, "data.pathSegments");
        if (pathSegments.isEmpty()) {
            i = 0;
        } else {
            i = 1;
        }
        if (i != 0) {
            String str = (String) data.getPathSegments().get(0);
            if (str != null) {
                switch (str.hashCode()) {
                    case 1195341721:
                        if (str.equals("invitation")) {
                            return parseInvitation(data.getPathSegments().subList(1, data.getPathSegments().size()), context);
                        }
                        break;
                    case 1382417574:
                        if (str.equals("premium-upgrade")) {
                            return startUpgradeCarousel(context);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return false;
    }

    private final boolean parseZoneIntent(List<String> data, Context context) {
        if (!((Collection) data).isEmpty()) {
            Integer zoneId = getZoneId((String) data.get(0));
            if (zoneId == null) {
                return false;
            }
            int zone = zoneId.intValue();
            ZoneController.INSTANCE.selectZone(zone);
            if (data.size() > 1) {
                String subsection = (String) data.get(1);
                switch (subsection.hashCode()) {
                    case -934521548:
                        if (subsection.equals("report")) {
                            boolean z;
                            Zone zone2 = ZoneController.INSTANCE.getZone(zone);
                            if (zone2 == null || !zone2.shouldShowReport()) {
                                z = false;
                            } else if (context == null) {
                                throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
                            } else {
                                AnalyticsHelper.trackEvent((Activity) context, Events.DEEP_LINKING, "open", "report");
                                AnkoInternals.internalStartActivity(context, ReportActivity.class, new Pair[]{TuplesKt.to("zone", Integer.valueOf(zone))});
                                z = true;
                            }
                            return z;
                        }
                        break;
                    case -697920873:
                        if (subsection.equals("schedule")) {
                            if (context == null) {
                                throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
                            }
                            AnalyticsHelper.trackEvent((Activity) context, Events.DEEP_LINKING, "open", "schedule");
                            AnkoInternals.internalStartActivity(context, SmartScheduleActivity.class, new Pair[]{TuplesKt.to("zone", Integer.valueOf(zone))});
                            return true;
                        }
                        break;
                    case 1434631203:
                        if (subsection.equals("settings")) {
                            if (context == null) {
                                throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
                            }
                            AnalyticsHelper.trackEvent((Activity) context, Events.DEEP_LINKING, "open", "zoneSettings");
                            AnkoInternals.internalStartActivity(context, ZonePreferenceActivity.class, new Pair[]{TuplesKt.to(ZonePreferenceActivity.KEY_ZONE_ID, Integer.valueOf(zone))});
                            return true;
                        }
                        break;
                }
            } else if (context == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
            } else {
                AnalyticsHelper.trackEvent((Activity) context, Events.DEEP_LINKING, "open", "mainScreen");
                AnkoInternals.internalStartActivity(context, MainZoneActivity.class, new Pair[]{TuplesKt.to("zone", Integer.valueOf(zone))});
                return true;
            }
        }
        return false;
    }

    private final boolean parseSettingsIntent(List<String> data, Context context) {
        if ((!((Collection) data).isEmpty() ? 1 : 0) != 0) {
            String str = (String) data.get(0);
            switch (str.hashCode()) {
                case -991808881:
                    if (str.equals("people")) {
                        if (context == null) {
                            throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
                        }
                        AnalyticsHelper.trackEvent((Activity) context, Events.DEEP_LINKING, "open", "peopleSettings");
                        AnkoInternals.internalStartActivity(context, PeoplePreferenceActivity.class, new Pair[0]);
                        return true;
                    }
                    break;
                case -530470613:
                    if (str.equals("location-based-control")) {
                        if (context == null) {
                            throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
                        }
                        AnalyticsHelper.trackEvent((Activity) context, Events.DEEP_LINKING, "open", "appSettings");
                        AnkoInternals.internalStartActivity(context, AppSettingsActivity.class, new Pair[0]);
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    private final boolean startUpgradeCarousel(Context context) {
        if (context == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
        }
        AnalyticsHelper.trackEvent((Activity) context, Events.DEEP_LINKING, "open", "premiumUpgrade");
        if (!Intrinsics.areEqual(UserConfig.getLicense(), LicenseEnum.NON_PREMIUM)) {
            return false;
        }
        AnkoInternals.internalStartActivity(context, PremiumCarouselActivity.class, new Pair[0]);
        return true;
    }

    private final boolean startEnergySavingsReport(Context context) {
        if (context == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
        }
        AnalyticsHelper.trackEvent((Activity) context, Events.DEEP_LINKING, "open", "energySavingsReport");
        AnkoInternals.internalStartActivity(context, EnergySavingsActivity.class, new Pair[0]);
        return true;
    }

    private final boolean parseInvitation(List<String> data, Context context) {
        if ((!((Collection) data).isEmpty() ? 1 : null) == null) {
            return false;
        }
        RestServiceGenerator.getTadoRestService().getInvitationDetails((String) CollectionsKt___CollectionsKt.last((List) data)).enqueue(new DeepLinkingManager$parseInvitation$1(context));
        return true;
    }

    private final Integer getZoneId(String pathSegment) {
        Integer zone = StringsKt__StringNumberConversionsKt.toIntOrNull(pathSegment);
        if (zone == null) {
            zone = ZoneController.INSTANCE.getZoneIdByName(pathSegment);
        }
        return ZoneController.INSTANCE.isZoneIdValid(zone != null ? zone.intValue() : -1) ? zone : null;
    }
}
