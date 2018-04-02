package com.firebase.jobdispatcher;

import android.app.Service;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import android.util.Pair;
import com.firebase.jobdispatcher.Job.Builder;
import com.firebase.jobdispatcher.JobTrigger.ContentUriTrigger;

public class GooglePlayReceiver extends Service implements JobFinishedCallback {
    @VisibleForTesting
    static final String ACTION_EXECUTE = "com.google.android.gms.gcm.ACTION_TASK_READY";
    @VisibleForTesting
    static final String ACTION_INITIALIZE = "com.google.android.gms.gcm.SERVICE_ACTION_INITIALIZE";
    private static final String ERROR_NO_DATA = "No data provided, terminating";
    private static final String ERROR_NULL_INTENT = "Null Intent passed, terminating";
    private static final String ERROR_UNKNOWN_ACTION = "Unknown action received, terminating";
    static final String TAG = "FJD.GooglePlayReceiver";
    private static final SimpleArrayMap<String, SimpleArrayMap<String, JobCallback>> callbacks = new SimpleArrayMap(1);
    private static final JobCoder prefixedCoder = new JobCoder("com.firebase.jobdispatcher.");
    private final GooglePlayCallbackExtractor callbackExtractor = new GooglePlayCallbackExtractor();
    @VisibleForTesting
    Driver driver;
    private ExecutionDelegator executionDelegator;
    private int latestStartId;
    @VisibleForTesting
    Messenger serviceMessenger;
    @VisibleForTesting
    ValidationEnforcer validationEnforcer;

    @VisibleForTesting
    static void clearCallbacks() {
        synchronized (callbacks) {
            callbacks.clear();
        }
    }

    private static void sendResultSafely(JobCallback callback, int result) {
        try {
            callback.jobFinished(result);
        } catch (Throwable e) {
            Log.e(TAG, "Encountered error running callback", e.getCause());
        }
    }

    public final int onStartCommand(Intent intent, int flags, int startId) {
        try {
            super.onStartCommand(intent, flags, startId);
            if (intent == null) {
                Log.w(TAG, ERROR_NULL_INTENT);
                synchronized (callbacks) {
                    this.latestStartId = startId;
                    if (callbacks.isEmpty()) {
                        stopSelf(this.latestStartId);
                    }
                }
            } else {
                String action = intent.getAction();
                if (ACTION_EXECUTE.equals(action)) {
                    getExecutionDelegator().executeJob(prepareJob(intent));
                    synchronized (callbacks) {
                        this.latestStartId = startId;
                        if (callbacks.isEmpty()) {
                            stopSelf(this.latestStartId);
                        }
                    }
                } else if (ACTION_INITIALIZE.equals(action)) {
                    synchronized (callbacks) {
                        this.latestStartId = startId;
                        if (callbacks.isEmpty()) {
                            stopSelf(this.latestStartId);
                        }
                    }
                } else {
                    Log.e(TAG, ERROR_UNKNOWN_ACTION);
                    synchronized (callbacks) {
                        this.latestStartId = startId;
                        if (callbacks.isEmpty()) {
                            stopSelf(this.latestStartId);
                        }
                    }
                }
            }
            return 2;
        } catch (Throwable th) {
            synchronized (callbacks) {
                this.latestStartId = startId;
                if (callbacks.isEmpty()) {
                    stopSelf(this.latestStartId);
                }
            }
        }
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        if (intent == null || VERSION.SDK_INT < 21 || !ACTION_EXECUTE.equals(intent.getAction())) {
            return null;
        }
        return getServiceMessenger().getBinder();
    }

    private synchronized Messenger getServiceMessenger() {
        if (this.serviceMessenger == null) {
            this.serviceMessenger = new Messenger(new GooglePlayMessageHandler(Looper.getMainLooper(), this));
        }
        return this.serviceMessenger;
    }

    synchronized ExecutionDelegator getExecutionDelegator() {
        if (this.executionDelegator == null) {
            this.executionDelegator = new ExecutionDelegator(this, this);
        }
        return this.executionDelegator;
    }

    @NonNull
    private synchronized Driver getGooglePlayDriver() {
        if (this.driver == null) {
            this.driver = new GooglePlayDriver(getApplicationContext());
        }
        return this.driver;
    }

    @VisibleForTesting
    synchronized void setGooglePlayDriver(Driver driver) {
        this.driver = driver;
    }

    @NonNull
    private synchronized ValidationEnforcer getValidationEnforcer() {
        if (this.validationEnforcer == null) {
            this.validationEnforcer = new ValidationEnforcer(getGooglePlayDriver().getValidator());
        }
        return this.validationEnforcer;
    }

    @VisibleForTesting
    synchronized void setValidationEnforcer(ValidationEnforcer validationEnforcer) {
        this.validationEnforcer = validationEnforcer;
    }

    @Nullable
    @VisibleForTesting
    JobInvocation prepareJob(Intent intent) {
        Bundle intentExtras = intent.getExtras();
        if (intentExtras == null) {
            Log.e(TAG, ERROR_NO_DATA);
            return null;
        }
        Pair<JobCallback, Bundle> extraction = this.callbackExtractor.extractCallback(intentExtras);
        if (extraction != null) {
            return prepareJob((JobCallback) extraction.first, (Bundle) extraction.second);
        }
        Log.i(TAG, "no callback found");
        return null;
    }

    @Nullable
    JobInvocation prepareJob(JobCallback callback, Bundle bundle) {
        JobInvocation job = prefixedCoder.decodeIntentBundle(bundle);
        if (job == null) {
            Log.e(TAG, "unable to decode job");
            sendResultSafely(callback, 2);
            return null;
        }
        synchronized (callbacks) {
            SimpleArrayMap<String, JobCallback> map = (SimpleArrayMap) callbacks.get(job.getService());
            if (map == null) {
                map = new SimpleArrayMap(1);
                callbacks.put(job.getService(), map);
            }
            map.put(job.getTag(), callback);
        }
        return job;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onJobFinished(@android.support.annotation.NonNull com.firebase.jobdispatcher.JobInvocation r7, int r8) {
        /*
        r6 = this;
        r3 = callbacks;
        monitor-enter(r3);
        r2 = callbacks;	 Catch:{ all -> 0x009c }
        r4 = r7.getService();	 Catch:{ all -> 0x009c }
        r1 = r2.get(r4);	 Catch:{ all -> 0x009c }
        r1 = (android.support.v4.util.SimpleArrayMap) r1;	 Catch:{ all -> 0x009c }
        if (r1 != 0) goto L_0x0020;
    L_0x0011:
        r2 = callbacks;	 Catch:{ all -> 0x003b }
        r2 = r2.isEmpty();	 Catch:{ all -> 0x003b }
        if (r2 == 0) goto L_0x001e;
    L_0x0019:
        r2 = r6.latestStartId;	 Catch:{ all -> 0x003b }
        r6.stopSelf(r2);	 Catch:{ all -> 0x003b }
    L_0x001e:
        monitor-exit(r3);	 Catch:{ all -> 0x003b }
    L_0x001f:
        return;
    L_0x0020:
        r2 = r7.getTag();	 Catch:{ all -> 0x009c }
        r0 = r1.remove(r2);	 Catch:{ all -> 0x009c }
        r0 = (com.firebase.jobdispatcher.JobCallback) r0;	 Catch:{ all -> 0x009c }
        if (r0 != 0) goto L_0x003e;
    L_0x002c:
        r2 = callbacks;	 Catch:{ all -> 0x003b }
        r2 = r2.isEmpty();	 Catch:{ all -> 0x003b }
        if (r2 == 0) goto L_0x0039;
    L_0x0034:
        r2 = r6.latestStartId;	 Catch:{ all -> 0x003b }
        r6.stopSelf(r2);	 Catch:{ all -> 0x003b }
    L_0x0039:
        monitor-exit(r3);	 Catch:{ all -> 0x003b }
        goto L_0x001f;
    L_0x003b:
        r2 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x003b }
        throw r2;
    L_0x003e:
        r2 = r1.isEmpty();	 Catch:{ all -> 0x009c }
        if (r2 == 0) goto L_0x004d;
    L_0x0044:
        r2 = callbacks;	 Catch:{ all -> 0x009c }
        r4 = r7.getService();	 Catch:{ all -> 0x009c }
        r2.remove(r4);	 Catch:{ all -> 0x009c }
    L_0x004d:
        r2 = needsToBeRescheduled(r7, r8);	 Catch:{ all -> 0x009c }
        if (r2 == 0) goto L_0x0065;
    L_0x0053:
        r6.reschedule(r7);	 Catch:{ all -> 0x009c }
    L_0x0056:
        r2 = callbacks;	 Catch:{ all -> 0x003b }
        r2 = r2.isEmpty();	 Catch:{ all -> 0x003b }
        if (r2 == 0) goto L_0x0063;
    L_0x005e:
        r2 = r6.latestStartId;	 Catch:{ all -> 0x003b }
        r6.stopSelf(r2);	 Catch:{ all -> 0x003b }
    L_0x0063:
        monitor-exit(r3);	 Catch:{ all -> 0x003b }
        goto L_0x001f;
    L_0x0065:
        r2 = "FJD.GooglePlayReceiver";
        r4 = 2;
        r2 = android.util.Log.isLoggable(r2, r4);	 Catch:{ all -> 0x009c }
        if (r2 == 0) goto L_0x0098;
    L_0x006f:
        r2 = "FJD.GooglePlayReceiver";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x009c }
        r4.<init>();	 Catch:{ all -> 0x009c }
        r5 = "sending jobFinished for ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x009c }
        r5 = r7.getTag();	 Catch:{ all -> 0x009c }
        r4 = r4.append(r5);	 Catch:{ all -> 0x009c }
        r5 = " = ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x009c }
        r4 = r4.append(r8);	 Catch:{ all -> 0x009c }
        r4 = r4.toString();	 Catch:{ all -> 0x009c }
        android.util.Log.v(r2, r4);	 Catch:{ all -> 0x009c }
    L_0x0098:
        sendResultSafely(r0, r8);	 Catch:{ all -> 0x009c }
        goto L_0x0056;
    L_0x009c:
        r2 = move-exception;
        r4 = callbacks;	 Catch:{ all -> 0x003b }
        r4 = r4.isEmpty();	 Catch:{ all -> 0x003b }
        if (r4 == 0) goto L_0x00aa;
    L_0x00a5:
        r4 = r6.latestStartId;	 Catch:{ all -> 0x003b }
        r6.stopSelf(r4);	 Catch:{ all -> 0x003b }
    L_0x00aa:
        throw r2;	 Catch:{ all -> 0x003b }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.jobdispatcher.GooglePlayReceiver.onJobFinished(com.firebase.jobdispatcher.JobInvocation, int):void");
    }

    private void reschedule(JobInvocation jobInvocation) {
        getGooglePlayDriver().schedule(new Builder(getValidationEnforcer(), jobInvocation).setReplaceCurrent(true).build());
    }

    private static boolean needsToBeRescheduled(JobParameters job, int result) {
        if (job.isRecurring() && (job.getTrigger() instanceof ContentUriTrigger) && result != 1) {
            return true;
        }
        return false;
    }

    static JobCoder getJobCoder() {
        return prefixedCoder;
    }

    static void onSchedule(Job job) {
        synchronized (callbacks) {
            SimpleArrayMap<String, JobCallback> jobs = (SimpleArrayMap) callbacks.get(job.getService());
            if (jobs == null) {
            } else if (((JobCallback) jobs.get(job.getTag())) == null) {
            } else {
                ExecutionDelegator.stopJob(new Builder().setTag(job.getTag()).setService(job.getService()).setTrigger(job.getTrigger()).build(), false);
            }
        }
    }
}
