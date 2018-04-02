package com.google.android.gms.analytics;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.internal.zzaru;
import com.google.android.gms.internal.zzasl;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.tado.android.utils.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HitBuilders {

    public static class HitBuilder<T extends HitBuilder> {
        private Map<String, String> zzdpb = new HashMap();
        private ProductAction zzdpc;
        private Map<String, List<Product>> zzdpd = new HashMap();
        private List<Promotion> zzdpe = new ArrayList();
        private List<Product> zzdpf = new ArrayList();

        protected HitBuilder() {
        }

        private final T zzh(String str, String str2) {
            if (str2 != null) {
                this.zzdpb.put(str, str2);
            }
            return this;
        }

        public T addImpression(Product product, String str) {
            if (product == null) {
                zzaru.zzcu("product should be non-null");
            } else {
                Object obj;
                if (str == null) {
                    obj = "";
                }
                if (!this.zzdpd.containsKey(obj)) {
                    this.zzdpd.put(obj, new ArrayList());
                }
                ((List) this.zzdpd.get(obj)).add(product);
            }
            return this;
        }

        public T addProduct(Product product) {
            if (product == null) {
                zzaru.zzcu("product should be non-null");
            } else {
                this.zzdpf.add(product);
            }
            return this;
        }

        public T addPromotion(Promotion promotion) {
            if (promotion == null) {
                zzaru.zzcu("promotion should be non-null");
            } else {
                this.zzdpe.add(promotion);
            }
            return this;
        }

        public Map<String, String> build() {
            Map<String, String> hashMap = new HashMap(this.zzdpb);
            if (this.zzdpc != null) {
                hashMap.putAll(this.zzdpc.build());
            }
            int i = 1;
            for (Promotion zzdr : this.zzdpe) {
                hashMap.putAll(zzdr.zzdr(zzd.zzao(i)));
                i++;
            }
            i = 1;
            for (Product zzdr2 : this.zzdpf) {
                hashMap.putAll(zzdr2.zzdr(zzd.zzam(i)));
                i++;
            }
            int i2 = 1;
            for (Entry entry : this.zzdpd.entrySet()) {
                List<Product> list = (List) entry.getValue();
                String zzar = zzd.zzar(i2);
                int i3 = 1;
                for (Product product : list) {
                    String valueOf = String.valueOf(zzar);
                    String valueOf2 = String.valueOf(zzd.zzaq(i3));
                    hashMap.putAll(product.zzdr(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)));
                    i3++;
                }
                if (!TextUtils.isEmpty((CharSequence) entry.getKey())) {
                    String valueOf3 = String.valueOf(zzar);
                    String valueOf4 = String.valueOf("nm");
                    hashMap.put(valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), (String) entry.getKey());
                }
                i2++;
            }
            return hashMap;
        }

        protected String get(String str) {
            return (String) this.zzdpb.get(str);
        }

        public final T set(String str, String str2) {
            if (str != null) {
                this.zzdpb.put(str, str2);
            } else {
                zzaru.zzcu("HitBuilder.set() called with a null paramName.");
            }
            return this;
        }

        public final T setAll(Map<String, String> map) {
            if (map != null) {
                this.zzdpb.putAll(new HashMap(map));
            }
            return this;
        }

        public T setCampaignParamsFromUrl(String str) {
            Object zzej = zzasl.zzej(str);
            if (!TextUtils.isEmpty(zzej)) {
                Map zzeh = zzasl.zzeh(zzej);
                zzh("&cc", (String) zzeh.get("utm_content"));
                zzh("&cm", (String) zzeh.get("utm_medium"));
                zzh("&cn", (String) zzeh.get("utm_campaign"));
                zzh("&cs", (String) zzeh.get("utm_source"));
                zzh("&ck", (String) zzeh.get("utm_term"));
                zzh("&ci", (String) zzeh.get("utm_id"));
                zzh("&anid", (String) zzeh.get("anid"));
                zzh("&gclid", (String) zzeh.get("gclid"));
                zzh("&dclid", (String) zzeh.get("dclid"));
                zzh("&aclid", (String) zzeh.get(Param.ACLID));
                zzh("&gmob_t", (String) zzeh.get("gmob_t"));
            }
            return this;
        }

        public T setCustomDimension(int i, String str) {
            set(zzd.zzai(i), str);
            return this;
        }

        public T setCustomMetric(int i, float f) {
            set(zzd.zzak(i), Float.toString(f));
            return this;
        }

        protected T setHitType(String str) {
            set("&t", str);
            return this;
        }

        public T setNewSession() {
            set("&sc", Constants.INSTALLATION_STATUS_START);
            return this;
        }

        public T setNonInteraction(boolean z) {
            set("&ni", zzasl.zzak(z));
            return this;
        }

        public T setProductAction(ProductAction productAction) {
            this.zzdpc = productAction;
            return this;
        }

        public T setPromotionAction(String str) {
            this.zzdpb.put("&promoa", str);
            return this;
        }
    }

    @Deprecated
    public static class AppViewBuilder extends HitBuilder<AppViewBuilder> {
        public AppViewBuilder() {
            set("&t", "screenview");
        }
    }

    public static class EventBuilder extends HitBuilder<EventBuilder> {
        public EventBuilder() {
            set("&t", "event");
        }

        public EventBuilder(String str, String str2) {
            this();
            setCategory(str);
            setAction(str2);
        }

        public EventBuilder setAction(String str) {
            set("&ea", str);
            return this;
        }

        public EventBuilder setCategory(String str) {
            set("&ec", str);
            return this;
        }

        public EventBuilder setLabel(String str) {
            set("&el", str);
            return this;
        }

        public EventBuilder setValue(long j) {
            set("&ev", Long.toString(j));
            return this;
        }
    }

    public static class ExceptionBuilder extends HitBuilder<ExceptionBuilder> {
        public ExceptionBuilder() {
            set("&t", "exception");
        }

        public ExceptionBuilder setDescription(String str) {
            set("&exd", str);
            return this;
        }

        public ExceptionBuilder setFatal(boolean z) {
            set("&exf", zzasl.zzak(z));
            return this;
        }
    }

    @Deprecated
    public static class ItemBuilder extends HitBuilder<ItemBuilder> {
        public ItemBuilder() {
            set("&t", "item");
        }

        public ItemBuilder setCategory(String str) {
            set("&iv", str);
            return this;
        }

        public ItemBuilder setCurrencyCode(String str) {
            set("&cu", str);
            return this;
        }

        public ItemBuilder setName(String str) {
            set("&in", str);
            return this;
        }

        public ItemBuilder setPrice(double d) {
            set("&ip", Double.toString(d));
            return this;
        }

        public ItemBuilder setQuantity(long j) {
            set("&iq", Long.toString(j));
            return this;
        }

        public ItemBuilder setSku(String str) {
            set("&ic", str);
            return this;
        }

        public ItemBuilder setTransactionId(String str) {
            set("&ti", str);
            return this;
        }
    }

    public static class ScreenViewBuilder extends HitBuilder<ScreenViewBuilder> {
        public ScreenViewBuilder() {
            set("&t", "screenview");
        }
    }

    public static class SocialBuilder extends HitBuilder<SocialBuilder> {
        public SocialBuilder() {
            set("&t", NotificationCompat.CATEGORY_SOCIAL);
        }

        public SocialBuilder setAction(String str) {
            set("&sa", str);
            return this;
        }

        public SocialBuilder setNetwork(String str) {
            set("&sn", str);
            return this;
        }

        public SocialBuilder setTarget(String str) {
            set("&st", str);
            return this;
        }
    }

    public static class TimingBuilder extends HitBuilder<TimingBuilder> {
        public TimingBuilder() {
            set("&t", "timing");
        }

        public TimingBuilder(String str, String str2, long j) {
            this();
            setVariable(str2);
            setValue(j);
            setCategory(str);
        }

        public TimingBuilder setCategory(String str) {
            set("&utc", str);
            return this;
        }

        public TimingBuilder setLabel(String str) {
            set("&utl", str);
            return this;
        }

        public TimingBuilder setValue(long j) {
            set("&utt", Long.toString(j));
            return this;
        }

        public TimingBuilder setVariable(String str) {
            set("&utv", str);
            return this;
        }
    }

    @Deprecated
    public static class TransactionBuilder extends HitBuilder<TransactionBuilder> {
        public TransactionBuilder() {
            set("&t", "transaction");
        }

        public TransactionBuilder setAffiliation(String str) {
            set("&ta", str);
            return this;
        }

        public TransactionBuilder setCurrencyCode(String str) {
            set("&cu", str);
            return this;
        }

        public TransactionBuilder setRevenue(double d) {
            set("&tr", Double.toString(d));
            return this;
        }

        public TransactionBuilder setShipping(double d) {
            set("&ts", Double.toString(d));
            return this;
        }

        public TransactionBuilder setTax(double d) {
            set("&tt", Double.toString(d));
            return this;
        }

        public TransactionBuilder setTransactionId(String str) {
            set("&ti", str);
            return this;
        }
    }
}
