package com.firebase.jobdispatcher;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

public final class GooglePlayDriver implements Driver {
    private static final String ACTION_SCHEDULE = "com.google.android.gms.gcm.ACTION_SCHEDULE";
    static final String BACKEND_PACKAGE = "com.google.android.gms";
    private static final String BUNDLE_PARAM_COMPONENT = "component";
    private static final String BUNDLE_PARAM_SCHEDULER_ACTION = "scheduler_action";
    private static final String BUNDLE_PARAM_TAG = "tag";
    private static final String BUNDLE_PARAM_TOKEN = "app";
    private static final String INTENT_PARAM_SOURCE = "source";
    private static final String INTENT_PARAM_SOURCE_VERSION = "source_version";
    private static final int JOB_DISPATCHER_SOURCE_CODE = 8;
    private static final int JOB_DISPATCHER_SOURCE_VERSION_CODE = 1;
    private static final String SCHEDULER_ACTION_CANCEL_ALL = "CANCEL_ALL";
    private static final String SCHEDULER_ACTION_CANCEL_TASK = "CANCEL_TASK";
    private static final String SCHEDULER_ACTION_SCHEDULE_TASK = "SCHEDULE_TASK";
    private final boolean available = true;
    private final Context context;
    private final PendingIntent token;
    private final JobValidator validator;
    private final GooglePlayJobWriter writer;

    public GooglePlayDriver(Context context) {
        this.context = context;
        this.token = PendingIntent.getBroadcast(context, 0, new Intent(), 0);
        this.writer = new GooglePlayJobWriter();
        this.validator = new DefaultJobValidator(context);
    }

    public boolean isAvailable() {
        return true;
    }

    public int schedule(@NonNull Job job) {
        GooglePlayReceiver.onSchedule(job);
        this.context.sendBroadcast(createScheduleRequest(job));
        return 0;
    }

    public int cancel(@NonNull String tag) {
        this.context.sendBroadcast(createCancelRequest(tag));
        return 0;
    }

    public int cancelAll() {
        this.context.sendBroadcast(createBatchCancelRequest());
        return 0;
    }

    @NonNull
    protected Intent createCancelRequest(@NonNull String tag) {
        Intent cancelReq = createSchedulerIntent(SCHEDULER_ACTION_CANCEL_TASK);
        cancelReq.putExtra(BUNDLE_PARAM_TAG, tag);
        cancelReq.putExtra(BUNDLE_PARAM_COMPONENT, new ComponentName(this.context, getReceiverClass()));
        return cancelReq;
    }

    @NonNull
    protected Intent createBatchCancelRequest() {
        Intent cancelReq = createSchedulerIntent(SCHEDULER_ACTION_CANCEL_ALL);
        cancelReq.putExtra(BUNDLE_PARAM_COMPONENT, new ComponentName(this.context, getReceiverClass()));
        return cancelReq;
    }

    @NonNull
    protected Class<GooglePlayReceiver> getReceiverClass() {
        return GooglePlayReceiver.class;
    }

    @NonNull
    public JobValidator getValidator() {
        return this.validator;
    }

    @NonNull
    private Intent createScheduleRequest(JobParameters job) {
        Intent scheduleReq = createSchedulerIntent(SCHEDULER_ACTION_SCHEDULE_TASK);
        scheduleReq.putExtras(this.writer.writeToBundle(job, scheduleReq.getExtras()));
        return scheduleReq;
    }

    @NonNull
    private Intent createSchedulerIntent(String schedulerAction) {
        Intent scheduleReq = new Intent(ACTION_SCHEDULE);
        scheduleReq.setPackage("com.google.android.gms");
        scheduleReq.putExtra(BUNDLE_PARAM_SCHEDULER_ACTION, schedulerAction);
        scheduleReq.putExtra("app", this.token);
        scheduleReq.putExtra("source", 8);
        scheduleReq.putExtra(INTENT_PARAM_SOURCE_VERSION, 1);
        return scheduleReq;
    }
}
