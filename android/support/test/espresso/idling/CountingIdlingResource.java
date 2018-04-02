package android.support.test.espresso.idling;

import android.os.SystemClock;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.IdlingResource.ResourceCallback;
import android.text.TextUtils;
import android.util.Log;
import java.util.concurrent.atomic.AtomicInteger;

public final class CountingIdlingResource implements IdlingResource {
    private static final String TAG = "CountingIdlingResource";
    private volatile long becameBusyAt;
    private volatile long becameIdleAt;
    private final AtomicInteger counter;
    private final boolean debugCounting;
    private volatile ResourceCallback resourceCallback;
    private final String resourceName;

    public CountingIdlingResource(String resourceName) {
        this(resourceName, false);
    }

    public CountingIdlingResource(String resourceName, boolean debugCounting) {
        this.counter = new AtomicInteger(0);
        this.becameBusyAt = 0;
        this.becameIdleAt = 0;
        if (TextUtils.isEmpty(resourceName)) {
            throw new IllegalArgumentException("resourceName cannot be empty or null!");
        }
        this.resourceName = resourceName;
        this.debugCounting = debugCounting;
    }

    public String getName() {
        return this.resourceName;
    }

    public boolean isIdleNow() {
        return this.counter.get() == 0;
    }

    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }

    public void increment() {
        int counterVal = this.counter.getAndIncrement();
        if (counterVal == 0) {
            this.becameBusyAt = SystemClock.uptimeMillis();
        }
        if (this.debugCounting) {
            String str = TAG;
            String str2 = this.resourceName;
            Log.i(str, new StringBuilder(String.valueOf(str2).length() + 51).append("Resource: ").append(str2).append(" in-use-count incremented to: ").append(counterVal + 1).toString());
        }
    }

    public void decrement() {
        int counterVal = this.counter.decrementAndGet();
        if (counterVal == 0) {
            if (this.resourceCallback != null) {
                this.resourceCallback.onTransitionToIdle();
            }
            this.becameIdleAt = SystemClock.uptimeMillis();
        }
        if (this.debugCounting) {
            String str;
            String str2;
            if (counterVal == 0) {
                str = TAG;
                str2 = this.resourceName;
                Log.i(str, new StringBuilder(String.valueOf(str2).length() + 65).append("Resource: ").append(str2).append(" went idle! (Time spent not idle: ").append(this.becameIdleAt - this.becameBusyAt).append(")").toString());
            } else {
                str = TAG;
                str2 = this.resourceName;
                Log.i(str, new StringBuilder(String.valueOf(str2).length() + 51).append("Resource: ").append(str2).append(" in-use-count decremented to: ").append(counterVal).toString());
            }
        }
        if (counterVal <= -1) {
            throw new IllegalStateException("Counter has been corrupted! counterVal=" + counterVal);
        }
    }

    public void dumpStateToLogs() {
        StringBuilder message = new StringBuilder("Resource: ").append(this.resourceName).append(" inflight transaction count: ").append(this.counter.get());
        if (0 == this.becameBusyAt) {
            Log.i(TAG, message.append(" and has never been busy!").toString());
            return;
        }
        message.append(" and was last busy at: ").append(this.becameBusyAt);
        if (0 == this.becameIdleAt) {
            Log.w(TAG, message.append(" AND NEVER WENT IDLE!").toString());
            return;
        }
        message.append(" and last went idle at: ").append(this.becameIdleAt);
        Log.i(TAG, message.toString());
    }
}
