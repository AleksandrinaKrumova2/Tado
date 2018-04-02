package com.firebase.jobdispatcher;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.firebase.jobdispatcher.IRemoteJobService.Stub;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@VisibleForTesting
class JobServiceConnection implements ServiceConnection {
    private IRemoteJobService binder;
    private final IJobCallback callback;
    private final Context context;
    private final Map<JobInvocation, Boolean> jobStatuses = new HashMap();
    private boolean wasUnbound = false;

    JobServiceConnection(IJobCallback callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    public synchronized void onServiceConnected(ComponentName name, IBinder service) {
        if (wasUnbound()) {
            Log.w("FJD.ExternalReceiver", "Connection have been used already.");
        } else {
            this.binder = Stub.asInterface(service);
            Set<JobInvocation> startedJobs = new HashSet();
            for (Entry<JobInvocation, Boolean> entry : this.jobStatuses.entrySet()) {
                if (Boolean.FALSE.equals(entry.getValue())) {
                    try {
                        this.binder.start(encodeJob((JobParameters) entry.getKey()), this.callback);
                        startedJobs.add(entry.getKey());
                    } catch (RemoteException remoteException) {
                        Log.e("FJD.ExternalReceiver", "Failed to start job " + entry.getKey(), remoteException);
                        unbind();
                    }
                }
            }
            for (JobInvocation invocation : startedJobs) {
                this.jobStatuses.put(invocation, Boolean.valueOf(true));
            }
        }
    }

    public synchronized void onServiceDisconnected(ComponentName name) {
        unbind();
    }

    synchronized boolean wasUnbound() {
        return this.wasUnbound;
    }

    synchronized boolean isConnected() {
        return this.binder != null;
    }

    synchronized void onStop(JobInvocation jobInvocation, boolean needToSendResult) {
        if (wasUnbound()) {
            Log.w("FJD.ExternalReceiver", "Can't send stop request because service was unbound.");
        } else {
            if (Boolean.TRUE.equals(this.jobStatuses.remove(jobInvocation)) && isConnected()) {
                stopJob(needToSendResult, jobInvocation);
            }
            if (!needToSendResult && this.jobStatuses.isEmpty()) {
                unbind();
            }
        }
    }

    private synchronized void stopJob(boolean needToSendResult, JobInvocation jobInvocation) {
        try {
            this.binder.stop(encodeJob(jobInvocation), needToSendResult);
        } catch (RemoteException remoteException) {
            Log.e("FJD.ExternalReceiver", "Failed to stop a job", remoteException);
            unbind();
        }
    }

    synchronized void unbind() {
        if (!wasUnbound()) {
            this.binder = null;
            this.wasUnbound = true;
            try {
                this.context.unbindService(this);
            } catch (IllegalArgumentException e) {
                Log.w("FJD.ExternalReceiver", "Error unbinding service: " + e.getMessage());
            }
        }
    }

    synchronized void onJobFinished(JobInvocation jobInvocation) {
        this.jobStatuses.remove(jobInvocation);
        if (this.jobStatuses.isEmpty()) {
            unbind();
        }
    }

    synchronized boolean startJob(JobInvocation jobInvocation) {
        boolean connected;
        connected = isConnected();
        if (connected) {
            if (Boolean.TRUE.equals((Boolean) this.jobStatuses.get(jobInvocation))) {
                Log.w("FJD.ExternalReceiver", "Received an execution request for already running job " + jobInvocation);
                stopJob(false, jobInvocation);
            }
            try {
                this.binder.start(encodeJob(jobInvocation), this.callback);
            } catch (RemoteException e) {
                Log.e("FJD.ExternalReceiver", "Failed to start the job " + jobInvocation, e);
                unbind();
                connected = false;
            }
        }
        this.jobStatuses.put(jobInvocation, Boolean.valueOf(connected));
        return connected;
    }

    private static Bundle encodeJob(JobParameters job) {
        return GooglePlayReceiver.getJobCoder().encode(job, new Bundle());
    }

    @VisibleForTesting
    synchronized boolean hasJobInvocation(JobInvocation jobInvocation) {
        return this.jobStatuses.containsKey(jobInvocation);
    }
}
