package com.google.android.gms.internal;

import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.analytics.zzh;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class zzapj extends zzh<zzapj> {
    private ProductAction zzdpc;
    private final Map<String, List<Product>> zzdpd = new HashMap();
    private final List<Promotion> zzdpe = new ArrayList();
    private final List<Product> zzdpf = new ArrayList();

    public final String toString() {
        Map hashMap = new HashMap();
        if (!this.zzdpf.isEmpty()) {
            hashMap.put("products", this.zzdpf);
        }
        if (!this.zzdpe.isEmpty()) {
            hashMap.put("promotions", this.zzdpe);
        }
        if (!this.zzdpd.isEmpty()) {
            hashMap.put("impressions", this.zzdpd);
        }
        hashMap.put("productAction", this.zzdpc);
        return zzh.zzl(hashMap);
    }

    public final /* synthetic */ void zzb(zzh com_google_android_gms_analytics_zzh) {
        zzapj com_google_android_gms_internal_zzapj = (zzapj) com_google_android_gms_analytics_zzh;
        com_google_android_gms_internal_zzapj.zzdpf.addAll(this.zzdpf);
        com_google_android_gms_internal_zzapj.zzdpe.addAll(this.zzdpe);
        for (Entry entry : this.zzdpd.entrySet()) {
            String str = (String) entry.getKey();
            for (Product product : (List) entry.getValue()) {
                if (product != null) {
                    Object obj;
                    if (str == null) {
                        obj = "";
                    } else {
                        String str2 = str;
                    }
                    if (!com_google_android_gms_internal_zzapj.zzdpd.containsKey(obj)) {
                        com_google_android_gms_internal_zzapj.zzdpd.put(obj, new ArrayList());
                    }
                    ((List) com_google_android_gms_internal_zzapj.zzdpd.get(obj)).add(product);
                }
            }
        }
        if (this.zzdpc != null) {
            com_google_android_gms_internal_zzapj.zzdpc = this.zzdpc;
        }
    }

    public final ProductAction zzvu() {
        return this.zzdpc;
    }

    public final List<Product> zzvv() {
        return Collections.unmodifiableList(this.zzdpf);
    }

    public final Map<String, List<Product>> zzvw() {
        return this.zzdpd;
    }

    public final List<Promotion> zzvx() {
        return Collections.unmodifiableList(this.zzdpe);
    }
}
