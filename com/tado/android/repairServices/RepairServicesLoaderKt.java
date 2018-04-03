package com.tado.android.repairServices;

import android.app.Activity;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.gson.Gson;
import com.tado.android.utils.FileUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"loadRepairServices", "Lcom/tado/android/repairServices/RepairServicesCountryMap;", "view", "Landroid/app/Activity;", "4.9.3-1500409030_tadoRelease"}, k = 2, mv = {1, 1, 9})
/* compiled from: RepairServicesLoader.kt */
public final class RepairServicesLoaderKt {
    @Nullable
    public static final RepairServicesCountryMap loadRepairServices(@NotNull Activity view) {
        Intrinsics.checkParameterIsNotNull(view, Promotion.ACTION_VIEW);
        try {
            String json = FileUtils.loadJSONFromAsset(view.getAssets(), "repair_services_urls.json");
            if (json != null) {
                String it = json;
                return (RepairServicesCountryMap) new Gson().fromJson(json, RepairServicesCountryMap.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
