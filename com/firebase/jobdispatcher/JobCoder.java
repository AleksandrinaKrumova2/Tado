package com.firebase.jobdispatcher;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import com.firebase.jobdispatcher.JobTrigger.ContentUriTrigger;
import com.firebase.jobdispatcher.JobTrigger.ExecutionWindowTrigger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class JobCoder {
    private static final String JSON_URIS = "uris";
    private static final String JSON_URI_FLAGS = "uri_flags";
    private final String prefix;

    JobCoder(String prefix) {
        this.prefix = prefix;
    }

    @NonNull
    Bundle encode(@NonNull JobParameters jobParameters, @NonNull Bundle data) {
        if (data == null) {
            throw new IllegalArgumentException("Unexpected null Bundle provided");
        }
        Bundle userExtras = jobParameters.getExtras();
        if (userExtras != null) {
            data.putAll(userExtras);
        }
        data.putInt(this.prefix + "persistent", jobParameters.getLifetime());
        data.putBoolean(this.prefix + "recurring", jobParameters.isRecurring());
        data.putBoolean(this.prefix + "replace_current", jobParameters.shouldReplaceCurrent());
        data.putString(this.prefix + "tag", jobParameters.getTag());
        data.putString(this.prefix + NotificationCompat.CATEGORY_SERVICE, jobParameters.getService());
        data.putInt(this.prefix + "constraints", Constraint.compact(jobParameters.getConstraints()));
        encodeTrigger(jobParameters.getTrigger(), data);
        encodeRetryStrategy(jobParameters.getRetryStrategy(), data);
        return data;
    }

    JobInvocation decodeIntentBundle(@NonNull Bundle bundle) {
        if (bundle == null) {
            Log.e("FJD.ExternalReceiver", "Unexpected null Bundle provided");
            return null;
        }
        Bundle taskExtras = bundle.getBundle("extras");
        if (taskExtras == null) {
            return null;
        }
        Builder builder = decode(taskExtras);
        List<Uri> triggeredContentUris = bundle.getParcelableArrayList("triggered_uris");
        if (triggeredContentUris != null) {
            builder.setTriggerReason(new TriggerReason(triggeredContentUris));
        }
        return builder.build();
    }

    @Nullable
    public Builder decode(@NonNull Bundle providedBundle) {
        if (providedBundle == null) {
            throw new IllegalArgumentException("Unexpected null Bundle provided");
        }
        Bundle data = new Bundle(providedBundle);
        boolean recur = data.getBoolean(this.prefix + "recurring");
        boolean replaceCur = data.getBoolean(this.prefix + "replace_current");
        int lifetime = data.getInt(this.prefix + "persistent");
        int[] constraints = Constraint.uncompact(data.getInt(this.prefix + "constraints"));
        JobTrigger trigger = decodeTrigger(data);
        RetryStrategy retryStrategy = decodeRetryStrategy(data);
        String tag = data.getString(this.prefix + "tag");
        String service = data.getString(this.prefix + NotificationCompat.CATEGORY_SERVICE);
        if (tag == null || service == null || trigger == null || retryStrategy == null) {
            return null;
        }
        Builder builder = new Builder();
        builder.setTag(tag);
        builder.setService(service);
        builder.setTrigger(trigger);
        builder.setRetryStrategy(retryStrategy);
        builder.setRecurring(recur);
        builder.setLifetime(lifetime);
        builder.setConstraints(constraints);
        builder.setReplaceCurrent(replaceCur);
        if (!TextUtils.isEmpty(this.prefix)) {
            Iterator<String> keyIterator = data.keySet().iterator();
            while (keyIterator.hasNext()) {
                if (((String) keyIterator.next()).startsWith(this.prefix)) {
                    keyIterator.remove();
                }
            }
        }
        builder.addExtras(data);
        return builder;
    }

    @NonNull
    private JobTrigger decodeTrigger(Bundle data) {
        switch (data.getInt(this.prefix + "trigger_type")) {
            case 1:
                return Trigger.executionWindow(data.getInt(this.prefix + "window_start"), data.getInt(this.prefix + "window_end"));
            case 2:
                return Trigger.NOW;
            case 3:
                return Trigger.contentUriTrigger(Collections.unmodifiableList(convertJsonToObservedUris(data.getString(this.prefix + "observed_uris"))));
            default:
                if (Log.isLoggable("FJD.ExternalReceiver", 3)) {
                    Log.d("FJD.ExternalReceiver", "Unsupported trigger.");
                }
                return null;
        }
    }

    private void encodeTrigger(JobTrigger trigger, Bundle data) {
        if (trigger == Trigger.NOW) {
            data.putInt(this.prefix + "trigger_type", 2);
        } else if (trigger instanceof ExecutionWindowTrigger) {
            ExecutionWindowTrigger t = (ExecutionWindowTrigger) trigger;
            data.putInt(this.prefix + "trigger_type", 1);
            data.putInt(this.prefix + "window_start", t.getWindowStart());
            data.putInt(this.prefix + "window_end", t.getWindowEnd());
        } else if (trigger instanceof ContentUriTrigger) {
            data.putInt(this.prefix + "trigger_type", 3);
            data.putString(this.prefix + "observed_uris", convertObservedUrisToJsonString(((ContentUriTrigger) trigger).getUris()));
        } else {
            throw new IllegalArgumentException("Unsupported trigger.");
        }
    }

    private RetryStrategy decodeRetryStrategy(Bundle data) {
        int policy = data.getInt(this.prefix + "retry_policy");
        if (policy == 1 || policy == 2) {
            return new RetryStrategy(policy, data.getInt(this.prefix + "initial_backoff_seconds"), data.getInt(this.prefix + "maximum_backoff_seconds"));
        }
        return RetryStrategy.DEFAULT_EXPONENTIAL;
    }

    private void encodeRetryStrategy(RetryStrategy retryStrategy, Bundle data) {
        if (retryStrategy == null) {
            retryStrategy = RetryStrategy.DEFAULT_EXPONENTIAL;
        }
        data.putInt(this.prefix + "retry_policy", retryStrategy.getPolicy());
        data.putInt(this.prefix + "initial_backoff_seconds", retryStrategy.getInitialBackoff());
        data.putInt(this.prefix + "maximum_backoff_seconds", retryStrategy.getMaximumBackoff());
    }

    @NonNull
    private static String convertObservedUrisToJsonString(@NonNull List<ObservedUri> uris) {
        JSONObject contentUris = new JSONObject();
        JSONArray jsonFlags = new JSONArray();
        JSONArray jsonUris = new JSONArray();
        for (ObservedUri uri : uris) {
            jsonFlags.put(uri.getFlags());
            jsonUris.put(uri.getUri());
        }
        try {
            contentUris.put(JSON_URI_FLAGS, jsonFlags);
            contentUris.put(JSON_URIS, jsonUris);
            return contentUris.toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    private static List<ObservedUri> convertJsonToObservedUris(@NonNull String contentUrisJson) {
        List<ObservedUri> uris = new ArrayList();
        try {
            JSONObject json = new JSONObject(contentUrisJson);
            JSONArray jsonFlags = json.getJSONArray(JSON_URI_FLAGS);
            JSONArray jsonUris = json.getJSONArray(JSON_URIS);
            int length = jsonFlags.length();
            for (int i = 0; i < length; i++) {
                uris.add(new ObservedUri(Uri.parse(jsonUris.getString(i)), jsonFlags.getInt(i)));
            }
            return uris;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
