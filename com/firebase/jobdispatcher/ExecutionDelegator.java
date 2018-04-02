package com.firebase.jobdispatcher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import com.firebase.jobdispatcher.IJobCallback.Stub;

class ExecutionDelegator {
    static final String TAG = "FJD.ExternalReceiver";
    private static final SimpleArrayMap<String, JobServiceConnection> serviceConnections = new SimpleArrayMap();
    private final Context context;
    private final IJobCallback execCallback = new C05571();
    private final JobFinishedCallback jobFinishedCallback;

    class C05571 extends Stub {
        C05571() {
        }

        public void jobFinished(Bundle invocationData, int result) {
            Builder invocation = GooglePlayReceiver.getJobCoder().decode(invocationData);
            if (invocation == null) {
                Log.wtf(ExecutionDelegator.TAG, "jobFinished: unknown invocation provided");
            } else {
                ExecutionDelegator.this.onJobFinishedMessage(invocation.build(), result);
            }
        }
    }

    interface JobFinishedCallback {
        void onJobFinished(@NonNull JobInvocation jobInvocation, int i);
    }

    @VisibleForTesting
    static JobServiceConnection getJobServiceConnection(String serviceName) {
        JobServiceConnection jobServiceConnection;
        synchronized (serviceConnections) {
            jobServiceConnection = (JobServiceConnection) serviceConnections.get(serviceName);
        }
        return jobServiceConnection;
    }

    @VisibleForTesting
    static void cleanServiceConnections() {
        synchronized (serviceConnections) {
            serviceConnections.clear();
        }
    }

    ExecutionDelegator(Context context, JobFinishedCallback jobFinishedCallback) {
        this.context = context;
        this.jobFinishedCallback = jobFinishedCallback;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void executeJob(com.firebase.jobdispatcher.JobInvocation r7) {
        /*
        r6 = this;
        if (r7 != 0) goto L_0x0003;
    L_0x0002:
        return;
    L_0x0003:
        r3 = serviceConnections;
        monitor-enter(r3);
        r2 = serviceConnections;	 Catch:{ all -> 0x0028 }
        r4 = r7.getService();	 Catch:{ all -> 0x0028 }
        r0 = r2.get(r4);	 Catch:{ all -> 0x0028 }
        r0 = (com.firebase.jobdispatcher.JobServiceConnection) r0;	 Catch:{ all -> 0x0028 }
        if (r0 == 0) goto L_0x002b;
    L_0x0014:
        r2 = r0.wasUnbound();	 Catch:{ all -> 0x0028 }
        if (r2 != 0) goto L_0x002b;
    L_0x001a:
        r2 = r0.hasJobInvocation(r7);	 Catch:{ all -> 0x0028 }
        if (r2 == 0) goto L_0x003d;
    L_0x0020:
        r2 = r0.isConnected();	 Catch:{ all -> 0x0028 }
        if (r2 != 0) goto L_0x003d;
    L_0x0026:
        monitor-exit(r3);	 Catch:{ all -> 0x0028 }
        goto L_0x0002;
    L_0x0028:
        r2 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0028 }
        throw r2;
    L_0x002b:
        r0 = new com.firebase.jobdispatcher.JobServiceConnection;	 Catch:{ all -> 0x0028 }
        r2 = r6.execCallback;	 Catch:{ all -> 0x0028 }
        r4 = r6.context;	 Catch:{ all -> 0x0028 }
        r0.<init>(r2, r4);	 Catch:{ all -> 0x0028 }
        r2 = serviceConnections;	 Catch:{ all -> 0x0028 }
        r4 = r7.getService();	 Catch:{ all -> 0x0028 }
        r2.put(r4, r0);	 Catch:{ all -> 0x0028 }
    L_0x003d:
        r1 = r0.startJob(r7);	 Catch:{ all -> 0x0028 }
        if (r1 != 0) goto L_0x0071;
    L_0x0043:
        r2 = r6.context;	 Catch:{ all -> 0x0028 }
        r4 = r6.createBindIntent(r7);	 Catch:{ all -> 0x0028 }
        r5 = 1;
        r2 = r2.bindService(r4, r0, r5);	 Catch:{ all -> 0x0028 }
        if (r2 != 0) goto L_0x0071;
    L_0x0050:
        r2 = "FJD.ExternalReceiver";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0028 }
        r4.<init>();	 Catch:{ all -> 0x0028 }
        r5 = "Unable to bind to ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0028 }
        r5 = r7.getService();	 Catch:{ all -> 0x0028 }
        r4 = r4.append(r5);	 Catch:{ all -> 0x0028 }
        r4 = r4.toString();	 Catch:{ all -> 0x0028 }
        android.util.Log.e(r2, r4);	 Catch:{ all -> 0x0028 }
        r0.unbind();	 Catch:{ all -> 0x0028 }
    L_0x0071:
        monitor-exit(r3);	 Catch:{ all -> 0x0028 }
        goto L_0x0002;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.jobdispatcher.ExecutionDelegator.executeJob(com.firebase.jobdispatcher.JobInvocation):void");
    }

    @NonNull
    private Intent createBindIntent(JobParameters jobParameters) {
        Intent execReq = new Intent("com.firebase.jobdispatcher.ACTION_EXECUTE");
        execReq.setClassName(this.context, jobParameters.getService());
        return execReq;
    }

    static void stopJob(JobInvocation job, boolean needToSendResult) {
        synchronized (serviceConnections) {
            JobServiceConnection jobServiceConnection = (JobServiceConnection) serviceConnections.get(job.getService());
            if (jobServiceConnection != null) {
                jobServiceConnection.onStop(job, needToSendResult);
                if (jobServiceConnection.wasUnbound()) {
                    serviceConnections.remove(job.getService());
                }
            }
        }
    }

    private void onJobFinishedMessage(JobInvocation jobInvocation, int result) {
        synchronized (serviceConnections) {
            JobServiceConnection jobServiceConnection = (JobServiceConnection) serviceConnections.get(jobInvocation.getService());
            if (jobServiceConnection != null) {
                jobServiceConnection.onJobFinished(jobInvocation);
                if (jobServiceConnection.wasUnbound()) {
                    serviceConnections.remove(jobInvocation.getService());
                }
            }
        }
        this.jobFinishedCallback.onJobFinished(jobInvocation, result);
    }
}
