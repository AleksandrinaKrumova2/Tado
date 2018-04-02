package com.firebase.jobdispatcher;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import com.firebase.jobdispatcher.IRemoteJobService.Stub;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;

public abstract class JobService extends Service {
    @VisibleForTesting
    static final String ACTION_EXECUTE = "com.firebase.jobdispatcher.ACTION_EXECUTE";
    public static final int RESULT_FAIL_NORETRY = 2;
    public static final int RESULT_FAIL_RETRY = 1;
    public static final int RESULT_SUCCESS = 0;
    static final String TAG = "FJD.JobService";
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final Stub binder = new C05601();
    private final SimpleArrayMap<String, JobCallback> runningJobs = new SimpleArrayMap(1);

    class C05601 extends Stub {
        C05601() {
        }

        public void start(Bundle invocationData, IJobCallback callback) {
            Builder invocation = GooglePlayReceiver.getJobCoder().decode(invocationData);
            if (invocation == null) {
                Log.wtf(JobService.TAG, "start: unknown invocation provided");
            } else {
                JobService.this.start(invocation.build(), callback);
            }
        }

        public void stop(Bundle invocationData, boolean needToSendResult) {
            Builder invocation = GooglePlayReceiver.getJobCoder().decode(invocationData);
            if (invocation == null) {
                Log.wtf(JobService.TAG, "stop: unknown invocation provided");
            } else {
                JobService.this.stop(invocation.build(), needToSendResult);
            }
        }
    }

    private static final class JobCallback {
        final JobParameters job;
        final IJobCallback remoteCallback;

        private JobCallback(JobParameters job, IJobCallback callback) {
            this.job = job;
            this.remoteCallback = callback;
        }

        void sendResult(int result) {
            try {
                this.remoteCallback.jobFinished(GooglePlayReceiver.getJobCoder().encode(this.job, new Bundle()), result);
            } catch (RemoteException remoteException) {
                Log.e(JobService.TAG, "Failed to send result to driver", remoteException);
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface JobResult {
    }

    @MainThread
    public abstract boolean onStartJob(JobParameters jobParameters);

    @MainThread
    public abstract boolean onStopJob(JobParameters jobParameters);

    void start(final JobParameters job, IJobCallback callback) {
        synchronized (this.runningJobs) {
            if (this.runningJobs.containsKey(job.getTag())) {
                Log.w(TAG, String.format(Locale.US, "Job with tag = %s was already running.", new Object[]{job.getTag()}));
                return;
            }
            this.runningJobs.put(job.getTag(), new JobCallback(job, callback));
            mainHandler.post(new Runnable() {
                public void run() {
                    synchronized (JobService.this.runningJobs) {
                        if (!JobService.this.onStartJob(job)) {
                            JobCallback callback = (JobCallback) JobService.this.runningJobs.remove(job.getTag());
                            if (callback != null) {
                                callback.sendResult(0);
                            }
                        }
                    }
                }
            });
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void stop(final com.firebase.jobdispatcher.JobParameters r5, final boolean r6) {
        /*
        r4 = this;
        r2 = r4.runningJobs;
        monitor-enter(r2);
        r1 = r4.runningJobs;	 Catch:{ all -> 0x0032 }
        r3 = r5.getTag();	 Catch:{ all -> 0x0032 }
        r0 = r1.remove(r3);	 Catch:{ all -> 0x0032 }
        r0 = (com.firebase.jobdispatcher.JobService.JobCallback) r0;	 Catch:{ all -> 0x0032 }
        if (r0 != 0) goto L_0x0026;
    L_0x0011:
        r1 = "FJD.JobService";
        r3 = 3;
        r1 = android.util.Log.isLoggable(r1, r3);	 Catch:{ all -> 0x0032 }
        if (r1 == 0) goto L_0x0024;
    L_0x001b:
        r1 = "FJD.JobService";
        r3 = "Provided job has already been executed.";
        android.util.Log.d(r1, r3);	 Catch:{ all -> 0x0032 }
    L_0x0024:
        monitor-exit(r2);	 Catch:{ all -> 0x0032 }
    L_0x0025:
        return;
    L_0x0026:
        r1 = mainHandler;	 Catch:{ all -> 0x0032 }
        r3 = new com.firebase.jobdispatcher.JobService$3;	 Catch:{ all -> 0x0032 }
        r3.<init>(r5, r6, r0);	 Catch:{ all -> 0x0032 }
        r1.post(r3);	 Catch:{ all -> 0x0032 }
        monitor-exit(r2);	 Catch:{ all -> 0x0032 }
        goto L_0x0025;
    L_0x0032:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0032 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.jobdispatcher.JobService.stop(com.firebase.jobdispatcher.JobParameters, boolean):void");
    }

    public final void jobFinished(@NonNull JobParameters job, boolean needsReschedule) {
        if (job == null) {
            Log.e(TAG, "jobFinished called with a null JobParameters");
            return;
        }
        synchronized (this.runningJobs) {
            JobCallback jobCallback = (JobCallback) this.runningJobs.remove(job.getTag());
            if (jobCallback != null) {
                jobCallback.sendResult(needsReschedule ? 1 : 0);
            }
        }
    }

    public final int onStartCommand(Intent intent, int flags, int startId) {
        stopSelf(startId);
        return 2;
    }

    @Nullable
    public final IBinder onBind(Intent intent) {
        return this.binder;
    }

    @MainThread
    public final boolean onUnbind(Intent intent) {
        synchronized (this.runningJobs) {
            for (int i = this.runningJobs.size() - 1; i >= 0; i--) {
                JobCallback callback = (JobCallback) this.runningJobs.remove(this.runningJobs.keyAt(i));
                if (callback != null) {
                    callback.sendResult(onStopJob(callback.job) ? 1 : 2);
                }
            }
        }
        return super.onUnbind(intent);
    }

    public final void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    public final void onStart(Intent intent, int startId) {
    }

    protected final void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(fd, writer, args);
    }

    public final void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public final void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
    }
}
