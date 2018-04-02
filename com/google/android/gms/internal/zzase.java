package com.google.android.gms.internal;

import android.app.job.JobParameters;

final class zzase implements zzarj {
    final /* synthetic */ Integer zzdyw;
    private /* synthetic */ zzaqc zzdyx;
    final /* synthetic */ zzarv zzdyy;
    final /* synthetic */ JobParameters zzdyz;
    final /* synthetic */ zzasd zzdza;

    zzase(zzasd com_google_android_gms_internal_zzasd, Integer num, zzaqc com_google_android_gms_internal_zzaqc, zzarv com_google_android_gms_internal_zzarv, JobParameters jobParameters) {
        this.zzdza = com_google_android_gms_internal_zzasd;
        this.zzdyw = num;
        this.zzdyx = com_google_android_gms_internal_zzaqc;
        this.zzdyy = com_google_android_gms_internal_zzarv;
        this.zzdyz = jobParameters;
    }

    public final void zzd(Throwable th) {
        this.zzdza.mHandler.post(new zzasf(this));
    }
}
