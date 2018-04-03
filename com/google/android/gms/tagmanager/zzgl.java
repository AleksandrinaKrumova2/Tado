package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.HitBuilders.HitBuilder;
import com.google.android.gms.analytics.HitBuilders.ScreenViewBuilder;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbs;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.tado.android.installation.CreateHomeContactDetailsActivity;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class zzgl extends zzgi {
    private static final String ID = zzbg.UNIVERSAL_ANALYTICS.toString();
    private static final String zzkku = zzbh.ACCOUNT.toString();
    private static final String zzkkv = zzbh.ANALYTICS_PASS_THROUGH.toString();
    private static final String zzkkw = zzbh.ENABLE_ECOMMERCE.toString();
    private static final String zzkkx = zzbh.ECOMMERCE_USE_DATA_LAYER.toString();
    private static final String zzkky = zzbh.ECOMMERCE_MACRO_DATA.toString();
    private static final String zzkkz = zzbh.ANALYTICS_FIELDS.toString();
    private static final String zzkla = zzbh.TRACK_TRANSACTION.toString();
    private static final String zzklb = zzbh.TRANSACTION_DATALAYER_MAP.toString();
    private static final String zzklc = zzbh.TRANSACTION_ITEM_DATALAYER_MAP.toString();
    private static final List<String> zzkld = Arrays.asList(new String[]{ProductAction.ACTION_DETAIL, ProductAction.ACTION_CHECKOUT, "checkout_option", "click", ProductAction.ACTION_ADD, ProductAction.ACTION_REMOVE, ProductAction.ACTION_PURCHASE, ProductAction.ACTION_REFUND});
    private static final Pattern zzkle = Pattern.compile("dimension(\\d+)");
    private static final Pattern zzklf = Pattern.compile("metric(\\d+)");
    private static Map<String, String> zzklg;
    private static Map<String, String> zzklh;
    private final DataLayer zzkde;
    private final Set<String> zzkli;
    private final zzgg zzklj;

    public zzgl(Context context, DataLayer dataLayer) {
        this(context, dataLayer, new zzgg(context));
    }

    private zzgl(Context context, DataLayer dataLayer, zzgg com_google_android_gms_tagmanager_zzgg) {
        super(ID, new String[0]);
        this.zzkde = dataLayer;
        this.zzklj = com_google_android_gms_tagmanager_zzgg;
        this.zzkli = new HashSet();
        this.zzkli.add("");
        this.zzkli.add("0");
        this.zzkli.add("false");
    }

    private final void zza(Tracker tracker, Map<String, zzbs> map) {
        String zzme = zzme("transactionId");
        if (zzme == null) {
            zzdj.m10e("Cannot find transactionId in data layer.");
            return;
        }
        List<Map> linkedList = new LinkedList();
        try {
            Map zzh;
            Map zzi = zzi((zzbs) map.get(zzkkz));
            zzi.put("&t", "transaction");
            zzbs com_google_android_gms_internal_zzbs = (zzbs) map.get(zzklb);
            if (com_google_android_gms_internal_zzbs != null) {
                zzh = zzh(com_google_android_gms_internal_zzbs);
            } else {
                if (zzklg == null) {
                    zzh = new HashMap();
                    zzh.put("transactionId", "&ti");
                    zzh.put("transactionAffiliation", "&ta");
                    zzh.put("transactionTax", "&tt");
                    zzh.put("transactionShipping", "&ts");
                    zzh.put("transactionTotal", "&tr");
                    zzh.put("transactionCurrency", "&cu");
                    zzklg = zzh;
                }
                zzh = zzklg;
            }
            for (Entry entry : r0.entrySet()) {
                zzd(zzi, (String) entry.getValue(), zzme((String) entry.getKey()));
            }
            linkedList.add(zzi);
            List<Map> zzmf = zzmf("transactionProducts");
            if (zzmf != null) {
                for (Map zzh2 : zzmf) {
                    if (zzh2.get(CreateHomeContactDetailsActivity.INTENT_NAME) == null) {
                        zzdj.m10e("Unable to send transaction item hit due to missing 'name' field.");
                        return;
                    }
                    Map zzi2 = zzi((zzbs) map.get(zzkkz));
                    zzi2.put("&t", "item");
                    zzi2.put("&ti", zzme);
                    zzbs com_google_android_gms_internal_zzbs2 = (zzbs) map.get(zzklc);
                    Map zzh3;
                    if (com_google_android_gms_internal_zzbs2 != null) {
                        zzh3 = zzh(com_google_android_gms_internal_zzbs2);
                    } else {
                        if (zzklh == null) {
                            zzh3 = new HashMap();
                            zzh3.put(CreateHomeContactDetailsActivity.INTENT_NAME, "&in");
                            zzh3.put("sku", "&ic");
                            zzh3.put("category", "&iv");
                            zzh3.put(Param.PRICE, "&ip");
                            zzh3.put(Param.QUANTITY, "&iq");
                            zzh3.put(Param.CURRENCY, "&cu");
                            zzklh = zzh3;
                        }
                        zzh3 = zzklh;
                    }
                    for (Entry entry2 : r1.entrySet()) {
                        zzd(zzi2, (String) entry2.getValue(), (String) zzh2.get(entry2.getKey()));
                    }
                    linkedList.add(zzi2);
                }
            }
            for (Map zzh22 : linkedList) {
                tracker.send(zzh22);
            }
        } catch (Throwable e) {
            zzdj.zzb("Unable to send transaction", e);
        }
    }

    private static Product zzaa(Map<String, Object> map) {
        String str;
        Product product = new Product();
        Object obj = map.get(ToolTipRelativeLayout.ID);
        if (obj != null) {
            product.setId(String.valueOf(obj));
        }
        obj = map.get(CreateHomeContactDetailsActivity.INTENT_NAME);
        if (obj != null) {
            product.setName(String.valueOf(obj));
        }
        obj = map.get("brand");
        if (obj != null) {
            product.setBrand(String.valueOf(obj));
        }
        obj = map.get("category");
        if (obj != null) {
            product.setCategory(String.valueOf(obj));
        }
        obj = map.get("variant");
        if (obj != null) {
            product.setVariant(String.valueOf(obj));
        }
        obj = map.get(Param.COUPON);
        if (obj != null) {
            product.setCouponCode(String.valueOf(obj));
        }
        obj = map.get("position");
        if (obj != null) {
            product.setPosition(zzar(obj).intValue());
        }
        obj = map.get(Param.PRICE);
        if (obj != null) {
            product.setPrice(zzaq(obj).doubleValue());
        }
        obj = map.get(Param.QUANTITY);
        if (obj != null) {
            product.setQuantity(zzar(obj).intValue());
        }
        for (String str2 : map.keySet()) {
            String str22;
            Matcher matcher = zzkle.matcher(str22);
            if (matcher.matches()) {
                try {
                    product.setCustomDimension(Integer.parseInt(matcher.group(1)), String.valueOf(map.get(str22)));
                } catch (NumberFormatException e) {
                    str = "illegal number in custom dimension value: ";
                    str22 = String.valueOf(str22);
                    zzdj.zzcu(str22.length() != 0 ? str.concat(str22) : new String(str));
                }
            } else {
                matcher = zzklf.matcher(str22);
                if (matcher.matches()) {
                    try {
                        product.setCustomMetric(Integer.parseInt(matcher.group(1)), zzar(map.get(str22)).intValue());
                    } catch (NumberFormatException e2) {
                        str = "illegal number in custom metric value: ";
                        str22 = String.valueOf(str22);
                        zzdj.zzcu(str22.length() != 0 ? str.concat(str22) : new String(str));
                    }
                }
            }
        }
        return product;
    }

    private static Double zzaq(Object obj) {
        String str;
        String valueOf;
        if (obj instanceof String) {
            try {
                return Double.valueOf((String) obj);
            } catch (NumberFormatException e) {
                str = "Cannot convert the object to Double: ";
                valueOf = String.valueOf(e.getMessage());
                throw new RuntimeException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        } else if (obj instanceof Integer) {
            return Double.valueOf(((Integer) obj).doubleValue());
        } else {
            if (obj instanceof Double) {
                return (Double) obj;
            }
            str = "Cannot convert the object to Double: ";
            valueOf = String.valueOf(obj.toString());
            throw new RuntimeException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
    }

    private static Integer zzar(Object obj) {
        String str;
        String valueOf;
        if (obj instanceof String) {
            try {
                return Integer.valueOf((String) obj);
            } catch (NumberFormatException e) {
                str = "Cannot convert the object to Integer: ";
                valueOf = String.valueOf(e.getMessage());
                throw new RuntimeException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        } else if (obj instanceof Double) {
            return Integer.valueOf(((Double) obj).intValue());
        } else {
            if (obj instanceof Integer) {
                return (Integer) obj;
            }
            str = "Cannot convert the object to Integer: ";
            valueOf = String.valueOf(obj.toString());
            throw new RuntimeException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
    }

    private static void zzd(Map<String, String> map, String str, String str2) {
        if (str2 != null) {
            map.put(str, str2);
        }
    }

    private static Map<String, String> zzh(zzbs com_google_android_gms_internal_zzbs) {
        Object zzg = zzgk.zzg(com_google_android_gms_internal_zzbs);
        if (!(zzg instanceof Map)) {
            return null;
        }
        Map map = (Map) zzg;
        Map<String, String> linkedHashMap = new LinkedHashMap();
        for (Entry entry : map.entrySet()) {
            linkedHashMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return linkedHashMap;
    }

    private static boolean zzh(Map<String, zzbs> map, String str) {
        zzbs com_google_android_gms_internal_zzbs = (zzbs) map.get(str);
        return com_google_android_gms_internal_zzbs == null ? false : zzgk.zzf(com_google_android_gms_internal_zzbs).booleanValue();
    }

    private final Map<String, String> zzi(zzbs com_google_android_gms_internal_zzbs) {
        if (com_google_android_gms_internal_zzbs == null) {
            return new HashMap();
        }
        Map<String, String> zzh = zzh(com_google_android_gms_internal_zzbs);
        if (zzh == null) {
            return new HashMap();
        }
        String str = (String) zzh.get("&aip");
        if (str != null && this.zzkli.contains(str.toLowerCase())) {
            zzh.remove("&aip");
        }
        return zzh;
    }

    private final String zzme(String str) {
        Object obj = this.zzkde.get(str);
        return obj == null ? null : obj.toString();
    }

    private final List<Map<String, String>> zzmf(String str) {
        Object obj = this.zzkde.get(str);
        if (obj == null) {
            return null;
        }
        if (obj instanceof List) {
            for (Object obj2 : (List) obj) {
                if (!(obj2 instanceof Map)) {
                    throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");
                }
            }
            return (List) obj;
        }
        throw new IllegalArgumentException("transactionProducts should be of type List.");
    }

    public final /* bridge */ /* synthetic */ boolean zzbdp() {
        return super.zzbdp();
    }

    public final /* bridge */ /* synthetic */ String zzbew() {
        return super.zzbew();
    }

    public final /* bridge */ /* synthetic */ Set zzbex() {
        return super.zzbex();
    }

    public final /* bridge */ /* synthetic */ zzbs zzv(Map map) {
        return super.zzv(map);
    }

    public final void zzx(Map<String, zzbs> map) {
        String str;
        Tracker zzlz = this.zzklj.zzlz("_GTM_DEFAULT_TRACKER_");
        zzlz.enableAdvertisingIdCollection(zzh(map, "collect_adid"));
        if (zzh(map, zzkkw)) {
            Object obj;
            Map map2;
            HitBuilder screenViewBuilder = new ScreenViewBuilder();
            Map zzi = zzi((zzbs) map.get(zzkkz));
            screenViewBuilder.setAll(zzi);
            if (zzh(map, zzkkx)) {
                obj = this.zzkde.get("ecommerce");
                map2 = obj instanceof Map ? (Map) obj : null;
            } else {
                obj = zzgk.zzg((zzbs) map.get(zzkky));
                map2 = obj instanceof Map ? (Map) obj : null;
            }
            if (map2 != null) {
                Map map3;
                List<Map> list;
                ProductAction productAction;
                ProductAction productAction2;
                String str2 = (String) zzi.get("&cu");
                if (str2 == null) {
                    str2 = (String) map2.get("currencyCode");
                }
                if (str2 != null) {
                    screenViewBuilder.set("&cu", str2);
                }
                obj = map2.get("impressions");
                if (obj instanceof List) {
                    for (Map map4 : (List) obj) {
                        try {
                            screenViewBuilder.addImpression(zzaa(map4), (String) map4.get("list"));
                        } catch (RuntimeException e) {
                            str = "Failed to extract a product from DataLayer. ";
                            str2 = String.valueOf(e.getMessage());
                            zzdj.m10e(str2.length() != 0 ? str.concat(str2) : new String(str));
                        }
                    }
                }
                List list2 = map2.containsKey("promoClick") ? (List) ((Map) map2.get("promoClick")).get("promotions") : map2.containsKey("promoView") ? (List) ((Map) map2.get("promoView")).get("promotions") : null;
                if (r0 != null) {
                    String str3;
                    for (Map map42 : r0) {
                        try {
                            Promotion promotion = new Promotion();
                            str3 = (String) map42.get(ToolTipRelativeLayout.ID);
                            if (str3 != null) {
                                promotion.setId(String.valueOf(str3));
                            }
                            str3 = (String) map42.get(CreateHomeContactDetailsActivity.INTENT_NAME);
                            if (str3 != null) {
                                promotion.setName(String.valueOf(str3));
                            }
                            str3 = (String) map42.get("creative");
                            if (str3 != null) {
                                promotion.setCreative(String.valueOf(str3));
                            }
                            str2 = (String) map42.get("position");
                            if (str2 != null) {
                                promotion.setPosition(String.valueOf(str2));
                            }
                            screenViewBuilder.addPromotion(promotion);
                        } catch (RuntimeException e2) {
                            str3 = "Failed to extract a promotion from DataLayer. ";
                            str2 = String.valueOf(e2.getMessage());
                            zzdj.m10e(str2.length() != 0 ? str3.concat(str2) : new String(str3));
                        }
                    }
                    if (map2.containsKey("promoClick")) {
                        screenViewBuilder.set("&promoa", "click");
                        obj = null;
                        if (obj != null) {
                            for (String str22 : zzkld) {
                                if (map2.containsKey(str22)) {
                                    map3 = (Map) map2.get(str22);
                                    list = (List) map3.get("products");
                                    if (list != null) {
                                        for (Map zzi2 : list) {
                                            try {
                                                screenViewBuilder.addProduct(zzaa(zzi2));
                                            } catch (RuntimeException e3) {
                                                str = "Failed to extract a product from DataLayer. ";
                                                String valueOf = String.valueOf(e3.getMessage());
                                                zzdj.m10e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                                            }
                                        }
                                    }
                                    try {
                                        if (map3.containsKey("actionField")) {
                                            productAction = new ProductAction(str22);
                                        } else {
                                            map3 = (Map) map3.get("actionField");
                                            productAction2 = new ProductAction(str22);
                                            obj = map3.get(ToolTipRelativeLayout.ID);
                                            if (obj != null) {
                                                productAction2.setTransactionId(String.valueOf(obj));
                                            }
                                            obj = map3.get(Param.AFFILIATION);
                                            if (obj != null) {
                                                productAction2.setTransactionAffiliation(String.valueOf(obj));
                                            }
                                            obj = map3.get(Param.COUPON);
                                            if (obj != null) {
                                                productAction2.setTransactionCouponCode(String.valueOf(obj));
                                            }
                                            obj = map3.get("list");
                                            if (obj != null) {
                                                productAction2.setProductActionList(String.valueOf(obj));
                                            }
                                            obj = map3.get("option");
                                            if (obj != null) {
                                                productAction2.setCheckoutOptions(String.valueOf(obj));
                                            }
                                            obj = map3.get("revenue");
                                            if (obj != null) {
                                                productAction2.setTransactionRevenue(zzaq(obj).doubleValue());
                                            }
                                            obj = map3.get(Param.TAX);
                                            if (obj != null) {
                                                productAction2.setTransactionTax(zzaq(obj).doubleValue());
                                            }
                                            obj = map3.get(Param.SHIPPING);
                                            if (obj != null) {
                                                productAction2.setTransactionShipping(zzaq(obj).doubleValue());
                                            }
                                            obj = map3.get("step");
                                            if (obj != null) {
                                                productAction2.setCheckoutStep(zzar(obj).intValue());
                                            }
                                            productAction = productAction2;
                                        }
                                        screenViewBuilder.setProductAction(productAction);
                                    } catch (RuntimeException e22) {
                                        str3 = "Failed to extract a product action from DataLayer. ";
                                        str22 = String.valueOf(e22.getMessage());
                                        zzdj.m10e(str22.length() != 0 ? str3.concat(str22) : new String(str3));
                                    }
                                }
                            }
                        }
                    } else {
                        screenViewBuilder.set("&promoa", Promotion.ACTION_VIEW);
                    }
                }
                int i = 1;
                if (obj != null) {
                    for (String str222 : zzkld) {
                        if (map2.containsKey(str222)) {
                            map3 = (Map) map2.get(str222);
                            list = (List) map3.get("products");
                            if (list != null) {
                                while (r3.hasNext()) {
                                    screenViewBuilder.addProduct(zzaa(zzi2));
                                }
                            }
                            if (map3.containsKey("actionField")) {
                                productAction = new ProductAction(str222);
                            } else {
                                map3 = (Map) map3.get("actionField");
                                productAction2 = new ProductAction(str222);
                                obj = map3.get(ToolTipRelativeLayout.ID);
                                if (obj != null) {
                                    productAction2.setTransactionId(String.valueOf(obj));
                                }
                                obj = map3.get(Param.AFFILIATION);
                                if (obj != null) {
                                    productAction2.setTransactionAffiliation(String.valueOf(obj));
                                }
                                obj = map3.get(Param.COUPON);
                                if (obj != null) {
                                    productAction2.setTransactionCouponCode(String.valueOf(obj));
                                }
                                obj = map3.get("list");
                                if (obj != null) {
                                    productAction2.setProductActionList(String.valueOf(obj));
                                }
                                obj = map3.get("option");
                                if (obj != null) {
                                    productAction2.setCheckoutOptions(String.valueOf(obj));
                                }
                                obj = map3.get("revenue");
                                if (obj != null) {
                                    productAction2.setTransactionRevenue(zzaq(obj).doubleValue());
                                }
                                obj = map3.get(Param.TAX);
                                if (obj != null) {
                                    productAction2.setTransactionTax(zzaq(obj).doubleValue());
                                }
                                obj = map3.get(Param.SHIPPING);
                                if (obj != null) {
                                    productAction2.setTransactionShipping(zzaq(obj).doubleValue());
                                }
                                obj = map3.get("step");
                                if (obj != null) {
                                    productAction2.setCheckoutStep(zzar(obj).intValue());
                                }
                                productAction = productAction2;
                            }
                            screenViewBuilder.setProductAction(productAction);
                        }
                    }
                }
            }
            zzlz.send(screenViewBuilder.build());
        } else if (zzh(map, zzkkv)) {
            zzlz.send(zzi((zzbs) map.get(zzkkz)));
        } else if (zzh(map, zzkla)) {
            zza(zzlz, map);
        } else {
            zzdj.zzcu("Ignoring unknown tag.");
        }
    }
}
