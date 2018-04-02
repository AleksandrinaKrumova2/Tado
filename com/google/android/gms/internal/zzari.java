package com.google.android.gms.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.PersistableBundle;
import android.support.v4.app.NotificationCompat;
import com.google.android.gms.common.internal.zzbq;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;

public final class zzari extends zzaqa {
    private boolean zzdvs;
    private boolean zzdvt;
    private final AlarmManager zzdvu = ((AlarmManager) getContext().getSystemService(NotificationCompat.CATEGORY_ALARM));
    private Integer zzdvv;

    protected zzari(zzaqc com_google_android_gms_internal_zzaqc) {
        super(com_google_android_gms_internal_zzaqc);
    }

    private final int getJobId() {
        if (this.zzdvv == null) {
            String str = SettingsJsonConstants.ANALYTICS_KEY;
            String valueOf = String.valueOf(getContext().getPackageName());
            this.zzdvv = Integer.valueOf((valueOf.length() != 0 ? str.concat(valueOf) : new String(str)).hashCode());
        }
        return this.zzdvv.intValue();
    }

    private final PendingIntent zzzf() {
        Intent intent = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
        intent.setComponent(new ComponentName(getContext(), "com.google.android.gms.analytics.AnalyticsReceiver"));
        return PendingIntent.getBroadcast(getContext(), 0, intent, 0);
    }

    public final void cancel() {
        this.zzdvt = false;
        this.zzdvu.cancel(zzzf());
        if (VERSION.SDK_INT >= 24) {
            JobScheduler jobScheduler = (JobScheduler) getContext().getSystemService("jobscheduler");
            zza("Cancelling job. JobID", Integer.valueOf(getJobId()));
            jobScheduler.cancel(getJobId());
        }
    }

    public final void schedule() {
        zzxf();
        zzbq.zza(this.zzdvs, (Object) "Receiver not registered");
        long zzyt = zzard.zzyt();
        if (zzyt > 0) {
            cancel();
            long elapsedRealtime = zzws().elapsedRealtime() + zzyt;
            this.zzdvt = true;
            if (VERSION.SDK_INT >= 24) {
                zzdu("Scheduling upload with JobScheduler");
                JobScheduler jobScheduler = (JobScheduler) getContext().getSystemService("jobscheduler");
                Builder builder = new Builder(getJobId(), new ComponentName(getContext(), "com.google.android.gms.analytics.AnalyticsJobService"));
                builder.setMinimumLatency(zzyt);
                builder.setOverrideDeadline(zzyt << 1);
                PersistableBundle persistableBundle = new PersistableBundle();
                persistableBundle.putString("action", "com.google.android.gms.analytics.ANALYTICS_DISPATCH");
                builder.setExtras(persistableBundle);
                JobInfo build = builder.build();
                zza("Scheduling job. JobID", Integer.valueOf(getJobId()));
                jobScheduler.schedule(build);
                return;
            }
            zzdu("Scheduling upload with AlarmManager");
            this.zzdvu.setInexactRepeating(2, elapsedRealtime, zzyt, zzzf());
        }
    }

    public final boolean zzdx() {
        return this.zzdvt;
    }

    protected final void zzvf() {
        try {
            cancel();
            if (zzard.zzyt() > 0) {
                ActivityInfo receiverInfo = getContext().getPackageManager().getReceiverInfo(new ComponentName(getContext(), "com.google.android.gms.analytics.AnalyticsReceiver"), 2);
                if (receiverInfo != null && receiverInfo.enabled) {
                    zzdu("Receiver registered for local dispatch.");
                    this.zzdvs = true;
                }
            }
        } catch (NameNotFoundException e) {
        }
    }

    public final boolean zzze() {
        return this.zzdvs;
    }
}
