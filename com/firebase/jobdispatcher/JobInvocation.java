package com.firebase.jobdispatcher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import java.util.Arrays;
import org.json.JSONObject;

final class JobInvocation implements JobParameters {
    @NonNull
    private final int[] constraints;
    @NonNull
    private final Bundle extras;
    private final int lifetime;
    private final boolean recurring;
    private final boolean replaceCurrent;
    private final RetryStrategy retryStrategy;
    @NonNull
    private final String service;
    @NonNull
    private final String tag;
    @NonNull
    private final JobTrigger trigger;
    private final TriggerReason triggerReason;

    static final class Builder {
        @NonNull
        private int[] constraints;
        @NonNull
        private final Bundle extras = new Bundle();
        private int lifetime;
        private boolean recurring;
        private boolean replaceCurrent;
        private RetryStrategy retryStrategy;
        @NonNull
        private String service;
        @NonNull
        private String tag;
        @NonNull
        private JobTrigger trigger;
        private TriggerReason triggerReason;

        Builder() {
        }

        JobInvocation build() {
            if (this.tag != null && this.service != null && this.trigger != null) {
                return new JobInvocation();
            }
            throw new IllegalArgumentException("Required fields were not populated.");
        }

        public Builder setTag(@NonNull String mTag) {
            this.tag = mTag;
            return this;
        }

        public Builder setService(@NonNull String mService) {
            this.service = mService;
            return this;
        }

        public Builder setTrigger(@NonNull JobTrigger mTrigger) {
            this.trigger = mTrigger;
            return this;
        }

        public Builder setRecurring(boolean mRecurring) {
            this.recurring = mRecurring;
            return this;
        }

        public Builder setLifetime(int mLifetime) {
            this.lifetime = mLifetime;
            return this;
        }

        public Builder setConstraints(@NonNull int[] mConstraints) {
            this.constraints = mConstraints;
            return this;
        }

        public Builder addExtras(Bundle bundle) {
            if (bundle != null) {
                this.extras.putAll(bundle);
            }
            return this;
        }

        public Builder setRetryStrategy(RetryStrategy mRetryStrategy) {
            this.retryStrategy = mRetryStrategy;
            return this;
        }

        public Builder setReplaceCurrent(boolean mReplaceCurrent) {
            this.replaceCurrent = mReplaceCurrent;
            return this;
        }

        public Builder setTriggerReason(TriggerReason triggerReason) {
            this.triggerReason = triggerReason;
            return this;
        }
    }

    private JobInvocation(Builder builder) {
        this.tag = builder.tag;
        this.service = builder.service;
        this.trigger = builder.trigger;
        this.retryStrategy = builder.retryStrategy;
        this.recurring = builder.recurring;
        this.lifetime = builder.lifetime;
        this.constraints = builder.constraints;
        this.extras = builder.extras;
        this.replaceCurrent = builder.replaceCurrent;
        this.triggerReason = builder.triggerReason;
    }

    @NonNull
    public String getService() {
        return this.service;
    }

    @NonNull
    public String getTag() {
        return this.tag;
    }

    @NonNull
    public JobTrigger getTrigger() {
        return this.trigger;
    }

    public int getLifetime() {
        return this.lifetime;
    }

    public boolean isRecurring() {
        return this.recurring;
    }

    @NonNull
    public int[] getConstraints() {
        return this.constraints;
    }

    @NonNull
    public Bundle getExtras() {
        return this.extras;
    }

    @NonNull
    public RetryStrategy getRetryStrategy() {
        return this.retryStrategy;
    }

    public boolean shouldReplaceCurrent() {
        return this.replaceCurrent;
    }

    public TriggerReason getTriggerReason() {
        return this.triggerReason;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(o.getClass())) {
            return false;
        }
        JobInvocation jobInvocation = (JobInvocation) o;
        if (this.tag.equals(jobInvocation.tag) && this.service.equals(jobInvocation.service)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.tag.hashCode() * 31) + this.service.hashCode();
    }

    public String toString() {
        return "JobInvocation{tag='" + JSONObject.quote(this.tag) + '\'' + ", service='" + this.service + '\'' + ", trigger=" + this.trigger + ", recurring=" + this.recurring + ", lifetime=" + this.lifetime + ", constraints=" + Arrays.toString(this.constraints) + ", extras=" + this.extras + ", retryStrategy=" + this.retryStrategy + ", replaceCurrent=" + this.replaceCurrent + ", triggerReason=" + this.triggerReason + '}';
    }
}
