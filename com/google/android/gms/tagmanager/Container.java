package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzbq;
import com.google.android.gms.internal.zzbr;
import com.google.android.gms.internal.zzbs;
import com.google.android.gms.internal.zzdja;
import com.google.android.gms.internal.zzdje;
import com.google.android.gms.internal.zzdji;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Container {
    private final Context mContext;
    private final String zzkdd;
    private final DataLayer zzkde;
    private zzfc zzkdf;
    private Map<String, FunctionCallMacroCallback> zzkdg = new HashMap();
    private Map<String, FunctionCallTagCallback> zzkdh = new HashMap();
    private volatile long zzkdi;
    private volatile String zzkdj = "";

    public interface FunctionCallMacroCallback {
        Object getValue(String str, Map<String, Object> map);
    }

    public interface FunctionCallTagCallback {
        void execute(String str, Map<String, Object> map);
    }

    class zza implements zzan {
        private /* synthetic */ Container zzkdk;

        private zza(Container container) {
            this.zzkdk = container;
        }

        public final Object zzd(String str, Map<String, Object> map) {
            FunctionCallMacroCallback zzlb = this.zzkdk.zzlb(str);
            return zzlb == null ? null : zzlb.getValue(str, map);
        }
    }

    class zzb implements zzan {
        private /* synthetic */ Container zzkdk;

        private zzb(Container container) {
            this.zzkdk = container;
        }

        public final Object zzd(String str, Map<String, Object> map) {
            FunctionCallTagCallback zzlc = this.zzkdk.zzlc(str);
            if (zzlc != null) {
                zzlc.execute(str, map);
            }
            return zzgk.zzbgr();
        }
    }

    Container(Context context, DataLayer dataLayer, String str, long j, zzbr com_google_android_gms_internal_zzbr) {
        this.mContext = context;
        this.zzkde = dataLayer;
        this.zzkdd = str;
        this.zzkdi = j;
        Object obj = com_google_android_gms_internal_zzbr.zzyi;
        if (obj == null) {
            throw new NullPointerException();
        }
        try {
            zza(zzdja.zza(obj));
        } catch (zzdji e) {
            String valueOf = String.valueOf(obj);
            String com_google_android_gms_internal_zzdji = e.toString();
            zzdj.m10e(new StringBuilder((String.valueOf(valueOf).length() + 46) + String.valueOf(com_google_android_gms_internal_zzdji).length()).append("Not loading resource: ").append(valueOf).append(" because it is invalid: ").append(com_google_android_gms_internal_zzdji).toString());
        }
        if (com_google_android_gms_internal_zzbr.zzyh != null) {
            zza(com_google_android_gms_internal_zzbr.zzyh);
        }
    }

    Container(Context context, DataLayer dataLayer, String str, long j, zzdje com_google_android_gms_internal_zzdje) {
        this.mContext = context;
        this.zzkde = dataLayer;
        this.zzkdd = str;
        this.zzkdi = 0;
        zza(com_google_android_gms_internal_zzdje);
    }

    private final void zza(zzdje com_google_android_gms_internal_zzdje) {
        this.zzkdj = com_google_android_gms_internal_zzdje.getVersion();
        String str = this.zzkdj;
        zzei.zzbfo().zzbfp().equals(zza.CONTAINER_DEBUG);
        zzdje com_google_android_gms_internal_zzdje2 = com_google_android_gms_internal_zzdje;
        zza(new zzfc(this.mContext, com_google_android_gms_internal_zzdje2, this.zzkde, new zza(), new zzb(), new zzdr()));
        if (getBoolean("_gtm.loadEventEnabled")) {
            this.zzkde.pushEvent("gtm.load", DataLayer.mapOf("gtm.id", this.zzkdd));
        }
    }

    private final synchronized void zza(zzfc com_google_android_gms_tagmanager_zzfc) {
        this.zzkdf = com_google_android_gms_tagmanager_zzfc;
    }

    private final void zza(zzbq[] com_google_android_gms_internal_zzbqArr) {
        List arrayList = new ArrayList();
        for (Object add : com_google_android_gms_internal_zzbqArr) {
            arrayList.add(add);
        }
        zzbdu().zzam(arrayList);
    }

    private final synchronized zzfc zzbdu() {
        return this.zzkdf;
    }

    public boolean getBoolean(String str) {
        zzfc zzbdu = zzbdu();
        if (zzbdu == null) {
            zzdj.m10e("getBoolean called for closed container.");
            return zzgk.zzbgp().booleanValue();
        }
        try {
            return zzgk.zzf((zzbs) zzbdu.zzlw(str).getObject()).booleanValue();
        } catch (Exception e) {
            String message = e.getMessage();
            zzdj.m10e(new StringBuilder(String.valueOf(message).length() + 66).append("Calling getBoolean() threw an exception: ").append(message).append(" Returning default value.").toString());
            return zzgk.zzbgp().booleanValue();
        }
    }

    public String getContainerId() {
        return this.zzkdd;
    }

    public double getDouble(String str) {
        zzfc zzbdu = zzbdu();
        if (zzbdu == null) {
            zzdj.m10e("getDouble called for closed container.");
            return zzgk.zzbgo().doubleValue();
        }
        try {
            return zzgk.zze((zzbs) zzbdu.zzlw(str).getObject()).doubleValue();
        } catch (Exception e) {
            String message = e.getMessage();
            zzdj.m10e(new StringBuilder(String.valueOf(message).length() + 65).append("Calling getDouble() threw an exception: ").append(message).append(" Returning default value.").toString());
            return zzgk.zzbgo().doubleValue();
        }
    }

    public long getLastRefreshTime() {
        return this.zzkdi;
    }

    public long getLong(String str) {
        zzfc zzbdu = zzbdu();
        if (zzbdu == null) {
            zzdj.m10e("getLong called for closed container.");
            return zzgk.zzbgn().longValue();
        }
        try {
            return zzgk.zzd((zzbs) zzbdu.zzlw(str).getObject()).longValue();
        } catch (Exception e) {
            String message = e.getMessage();
            zzdj.m10e(new StringBuilder(String.valueOf(message).length() + 63).append("Calling getLong() threw an exception: ").append(message).append(" Returning default value.").toString());
            return zzgk.zzbgn().longValue();
        }
    }

    public String getString(String str) {
        zzfc zzbdu = zzbdu();
        if (zzbdu == null) {
            zzdj.m10e("getString called for closed container.");
            return zzgk.zzbgr();
        }
        try {
            return zzgk.zzb((zzbs) zzbdu.zzlw(str).getObject());
        } catch (Exception e) {
            String message = e.getMessage();
            zzdj.m10e(new StringBuilder(String.valueOf(message).length() + 65).append("Calling getString() threw an exception: ").append(message).append(" Returning default value.").toString());
            return zzgk.zzbgr();
        }
    }

    public boolean isDefault() {
        return getLastRefreshTime() == 0;
    }

    public void registerFunctionCallMacroCallback(String str, FunctionCallMacroCallback functionCallMacroCallback) {
        if (functionCallMacroCallback == null) {
            throw new NullPointerException("Macro handler must be non-null");
        }
        synchronized (this.zzkdg) {
            this.zzkdg.put(str, functionCallMacroCallback);
        }
    }

    public void registerFunctionCallTagCallback(String str, FunctionCallTagCallback functionCallTagCallback) {
        if (functionCallTagCallback == null) {
            throw new NullPointerException("Tag callback must be non-null");
        }
        synchronized (this.zzkdh) {
            this.zzkdh.put(str, functionCallTagCallback);
        }
    }

    final void release() {
        this.zzkdf = null;
    }

    public void unregisterFunctionCallMacroCallback(String str) {
        synchronized (this.zzkdg) {
            this.zzkdg.remove(str);
        }
    }

    public void unregisterFunctionCallTagCallback(String str) {
        synchronized (this.zzkdh) {
            this.zzkdh.remove(str);
        }
    }

    public final String zzbdt() {
        return this.zzkdj;
    }

    final FunctionCallMacroCallback zzlb(String str) {
        FunctionCallMacroCallback functionCallMacroCallback;
        synchronized (this.zzkdg) {
            functionCallMacroCallback = (FunctionCallMacroCallback) this.zzkdg.get(str);
        }
        return functionCallMacroCallback;
    }

    public final FunctionCallTagCallback zzlc(String str) {
        FunctionCallTagCallback functionCallTagCallback;
        synchronized (this.zzkdh) {
            functionCallTagCallback = (FunctionCallTagCallback) this.zzkdh.get(str);
        }
        return functionCallTagCallback;
    }

    public final void zzld(String str) {
        zzbdu().zzld(str);
    }
}
