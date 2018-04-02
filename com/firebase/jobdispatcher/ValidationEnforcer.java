package com.firebase.jobdispatcher;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.List;

public class ValidationEnforcer implements JobValidator {
    private final JobValidator validator;

    public static final class ValidationException extends RuntimeException {
        private final List<String> errors;

        public ValidationException(String msg, @NonNull List<String> errors) {
            super(msg + ": " + TextUtils.join("\n  - ", errors));
            this.errors = errors;
        }

        public List<String> getErrors() {
            return this.errors;
        }
    }

    public ValidationEnforcer(JobValidator validator) {
        this.validator = validator;
    }

    @Nullable
    public List<String> validate(JobParameters job) {
        return this.validator.validate(job);
    }

    @Nullable
    public List<String> validate(JobTrigger trigger) {
        return this.validator.validate(trigger);
    }

    @Nullable
    public List<String> validate(RetryStrategy retryStrategy) {
        return this.validator.validate(retryStrategy);
    }

    public final boolean isValid(JobParameters job) {
        return validate(job) == null;
    }

    public final boolean isValid(JobTrigger trigger) {
        return validate(trigger) == null;
    }

    public final boolean isValid(RetryStrategy retryStrategy) {
        return validate(retryStrategy) == null;
    }

    public final void ensureValid(JobParameters job) {
        ensureNoErrors(validate(job));
    }

    public final void ensureValid(JobTrigger trigger) {
        ensureNoErrors(validate(trigger));
    }

    public final void ensureValid(RetryStrategy retryStrategy) {
        ensureNoErrors(validate(retryStrategy));
    }

    private static void ensureNoErrors(List<String> errors) {
        if (errors != null) {
            throw new ValidationException("JobParameters is invalid", errors);
        }
    }
}
