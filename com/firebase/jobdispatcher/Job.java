package com.firebase.jobdispatcher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class Job implements JobParameters {
    private final int[] constraints;
    private final Bundle extras;
    private final int lifetime;
    private final boolean recurring;
    private final boolean replaceCurrent;
    private final RetryStrategy retryStrategy;
    private final String service;
    private final String tag;
    private final JobTrigger trigger;

    public static final class Builder implements JobParameters {
        private int[] constraints;
        private Bundle extras;
        private int lifetime = 1;
        private boolean recurring = false;
        private boolean replaceCurrent = false;
        private RetryStrategy retryStrategy = RetryStrategy.DEFAULT_EXPONENTIAL;
        private String serviceClassName;
        private String tag;
        private JobTrigger trigger = Trigger.NOW;
        private final ValidationEnforcer validator;

        Builder(ValidationEnforcer validator) {
            this.validator = validator;
        }

        Builder(ValidationEnforcer validator, JobParameters job) {
            this.validator = validator;
            this.tag = job.getTag();
            this.serviceClassName = job.getService();
            this.trigger = job.getTrigger();
            this.recurring = job.isRecurring();
            this.lifetime = job.getLifetime();
            this.constraints = job.getConstraints();
            this.extras = job.getExtras();
            this.retryStrategy = job.getRetryStrategy();
        }

        public Builder addConstraint(int constraint) {
            int[] newConstraints = new int[(this.constraints == null ? 1 : this.constraints.length + 1)];
            if (!(this.constraints == null || this.constraints.length == 0)) {
                System.arraycopy(this.constraints, 0, newConstraints, 0, this.constraints.length);
            }
            newConstraints[newConstraints.length - 1] = constraint;
            this.constraints = newConstraints;
            return this;
        }

        public Builder setReplaceCurrent(boolean replaceCurrent) {
            this.replaceCurrent = replaceCurrent;
            return this;
        }

        public Job build() {
            this.validator.ensureValid((JobParameters) this);
            return new Job();
        }

        @NonNull
        public String getService() {
            return this.serviceClassName;
        }

        public Builder setService(Class<? extends JobService> serviceClass) {
            this.serviceClassName = serviceClass == null ? null : serviceClass.getName();
            return this;
        }

        Builder setServiceName(String serviceClassName) {
            this.serviceClassName = serviceClassName;
            return this;
        }

        @NonNull
        public String getTag() {
            return this.tag;
        }

        public Builder setTag(String tag) {
            this.tag = tag;
            return this;
        }

        @NonNull
        public JobTrigger getTrigger() {
            return this.trigger;
        }

        public Builder setTrigger(JobTrigger trigger) {
            this.trigger = trigger;
            return this;
        }

        public int getLifetime() {
            return this.lifetime;
        }

        public Builder setLifetime(int lifetime) {
            this.lifetime = lifetime;
            return this;
        }

        public boolean isRecurring() {
            return this.recurring;
        }

        public Builder setRecurring(boolean recurring) {
            this.recurring = recurring;
            return this;
        }

        public int[] getConstraints() {
            return this.constraints == null ? new int[0] : this.constraints;
        }

        public Builder setConstraints(int... constraints) {
            this.constraints = constraints;
            return this;
        }

        @Nullable
        public Bundle getExtras() {
            return this.extras;
        }

        public Builder setExtras(Bundle extras) {
            this.extras = extras;
            return this;
        }

        @NonNull
        public RetryStrategy getRetryStrategy() {
            return this.retryStrategy;
        }

        public Builder setRetryStrategy(RetryStrategy retryStrategy) {
            this.retryStrategy = retryStrategy;
            return this;
        }

        public boolean shouldReplaceCurrent() {
            return this.replaceCurrent;
        }

        @Nullable
        public TriggerReason getTriggerReason() {
            return null;
        }
    }

    private Job(Builder builder) {
        this.service = builder.serviceClassName;
        this.extras = builder.extras == null ? null : new Bundle(builder.extras);
        this.tag = builder.tag;
        this.trigger = builder.trigger;
        this.retryStrategy = builder.retryStrategy;
        this.lifetime = builder.lifetime;
        this.recurring = builder.recurring;
        this.constraints = builder.constraints != null ? builder.constraints : new int[0];
        this.replaceCurrent = builder.replaceCurrent;
    }

    @NonNull
    public int[] getConstraints() {
        return this.constraints;
    }

    @Nullable
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

    @Nullable
    public TriggerReason getTriggerReason() {
        return null;
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
    public String getService() {
        return this.service;
    }
}
