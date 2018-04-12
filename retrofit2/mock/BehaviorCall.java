package retrofit2.mock;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

final class BehaviorCall<T> implements Call<T> {
    final ExecutorService backgroundExecutor;
    final NetworkBehavior behavior;
    volatile boolean canceled;
    final Call<T> delegate;
    private volatile boolean executed;
    private volatile Future<?> task;

    BehaviorCall(NetworkBehavior behavior, ExecutorService backgroundExecutor, Call<T> delegate) {
        this.behavior = behavior;
        this.backgroundExecutor = backgroundExecutor;
        this.delegate = delegate;
    }

    public Call<T> clone() {
        return new BehaviorCall(this.behavior, this.backgroundExecutor, this.delegate.clone());
    }

    public Request request() {
        return this.delegate.request();
    }

    public void enqueue(final Callback<T> callback) {
        if (callback == null) {
            throw new NullPointerException("callback == null");
        }
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already executed");
            }
            this.executed = true;
        }
        this.task = this.backgroundExecutor.submit(new Runnable() {

            class C14101 implements Callback<T> {
                C14101() {
                }

                public void onResponse(Call<T> call, Response<T> response) {
                    if (C14111.this.delaySleep()) {
                        callback.onResponse(call, response);
                    }
                }

                public void onFailure(Call<T> call, Throwable t) {
                    if (C14111.this.delaySleep()) {
                        callback.onFailure(call, t);
                    }
                }
            }

            boolean delaySleep() {
                long sleepMs = BehaviorCall.this.behavior.calculateDelay(TimeUnit.MILLISECONDS);
                if (sleepMs > 0) {
                    try {
                        Thread.sleep(sleepMs);
                    } catch (InterruptedException e) {
                        callback.onFailure(BehaviorCall.this, new IOException("canceled"));
                        return false;
                    }
                }
                return true;
            }

            public void run() {
                if (BehaviorCall.this.canceled) {
                    callback.onFailure(BehaviorCall.this, new IOException("canceled"));
                } else if (BehaviorCall.this.behavior.calculateIsFailure()) {
                    if (delaySleep()) {
                        callback.onFailure(BehaviorCall.this, BehaviorCall.this.behavior.failureException());
                    }
                } else if (!BehaviorCall.this.behavior.calculateIsError()) {
                    BehaviorCall.this.delegate.enqueue(new C14101());
                } else if (delaySleep()) {
                    callback.onResponse(BehaviorCall.this, BehaviorCall.this.behavior.createErrorResponse());
                }
            }
        });
    }

    public synchronized boolean isExecuted() {
        return this.executed;
    }

    public Response<T> execute() throws IOException {
        final AtomicReference<Response<T>> responseRef = new AtomicReference();
        final AtomicReference<Throwable> failureRef = new AtomicReference();
        final CountDownLatch latch = new CountDownLatch(1);
        enqueue(new Callback<T>() {
            public void onResponse(Call<T> call, Response<T> response) {
                responseRef.set(response);
                latch.countDown();
            }

            public void onFailure(Call<T> call, Throwable t) {
                failureRef.set(t);
                latch.countDown();
            }
        });
        try {
            latch.await();
            Response<T> response = (Response) responseRef.get();
            if (response != null) {
                return response;
            }
            Throwable failure = (Throwable) failureRef.get();
            if (failure instanceof RuntimeException) {
                throw ((RuntimeException) failure);
            } else if (failure instanceof IOException) {
                throw ((IOException) failure);
            } else {
                throw new RuntimeException(failure);
            }
        } catch (InterruptedException e) {
            throw new IOException("canceled");
        }
    }

    public void cancel() {
        this.canceled = true;
        Future<?> task = this.task;
        if (task != null) {
            task.cancel(true);
        }
    }

    public boolean isCanceled() {
        return this.canceled;
    }
}
