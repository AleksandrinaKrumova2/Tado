package com.google.android.gms.tagmanager;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;

final class zzb implements zzd {
    private /* synthetic */ zza zzkct;

    zzb(zza com_google_android_gms_tagmanager_zza) {
        this.zzkct = com_google_android_gms_tagmanager_zza;
    }

    public final Info zzbdo() {
        Info info = null;
        try {
            info = AdvertisingIdClient.getAdvertisingIdInfo(this.zzkct.mContext);
        } catch (Throwable e) {
            zzdj.zzc("IllegalStateException getting Advertising Id Info", e);
        } catch (Throwable e2) {
            zzdj.zzc("GooglePlayServicesRepairableException getting Advertising Id Info", e2);
        } catch (Throwable e22) {
            zzdj.zzc("IOException getting Ad Id Info", e22);
        } catch (Throwable e222) {
            this.zzkct.close();
            zzdj.zzc("GooglePlayServicesNotAvailableException getting Advertising Id Info", e222);
        } catch (Throwable e2222) {
            zzdj.zzc("Unknown exception. Could not get the Advertising Id Info.", e2222);
        }
        return info;
    }
}
