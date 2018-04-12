package retrofit2.mock;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;
import okhttp3.Request;
import okhttp3.Request$Builder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class Calls {

    static final class DeferredCall<T> implements Call<T> {
        private final Callable<Call<T>> callable;
        private Call<T> delegate;

        DeferredCall(Callable<Call<T>> callable) {
            this.callable = callable;
        }

        private synchronized Call<T> getDelegate() {
            Call<T> delegate;
            delegate = this.delegate;
            if (delegate == null) {
                try {
                    delegate = (Call) this.callable.call();
                } catch (IOException e) {
                    delegate = Calls.failure(e);
                } catch (Exception e2) {
                    throw new IllegalStateException("Callable threw unrecoverable exception", e2);
                }
                this.delegate = delegate;
            }
            return delegate;
        }

        public Response<T> execute() throws IOException {
            return getDelegate().execute();
        }

        public void enqueue(Callback<T> callback) {
            getDelegate().enqueue(callback);
        }

        public boolean isExecuted() {
            return getDelegate().isExecuted();
        }

        public void cancel() {
            getDelegate().cancel();
        }

        public boolean isCanceled() {
            return getDelegate().isCanceled();
        }

        public Call<T> clone() {
            return new DeferredCall(this.callable);
        }

        public Request request() {
            return getDelegate().request();
        }
    }

    static final class FakeCall<T> implements Call<T> {
        private final AtomicBoolean canceled = new AtomicBoolean();
        private final IOException error;
        private final AtomicBoolean executed = new AtomicBoolean();
        private final Response<T> response;

        FakeCall(@Nullable Response<T> response, @Nullable IOException error) {
            Object obj;
            Object obj2 = 1;
            if (response == null) {
                obj = 1;
            } else {
                obj = null;
            }
            if (error != null) {
                obj2 = null;
            }
            if (obj == obj2) {
                throw new AssertionError("Only one of response or error can be set.");
            }
            this.response = response;
            this.error = error;
        }

        public Response<T> execute() throws IOException {
            if (!this.executed.compareAndSet(false, true)) {
                throw new IllegalStateException("Already executed");
            } else if (this.canceled.get()) {
                throw new IOException("canceled");
            } else if (this.response != null) {
                return this.response;
            } else {
                throw this.error;
            }
        }

        public void enqueue(Callback<T> callback) {
            if (callback == null) {
                throw new NullPointerException("callback == null");
            } else if (!this.executed.compareAndSet(false, true)) {
                throw new IllegalStateException("Already executed");
            } else if (this.canceled.get()) {
                callback.onFailure(this, new IOException("canceled"));
            } else if (this.response != null) {
                callback.onResponse(this, this.response);
            } else {
                callback.onFailure(this, this.error);
            }
        }

        public boolean isExecuted() {
            return this.executed.get();
        }

        public void cancel() {
            this.canceled.set(true);
        }

        public boolean isCanceled() {
            return this.canceled.get();
        }

        public Call<T> clone() {
            return new FakeCall(this.response, this.error);
        }

        public Request request() {
            if (this.response != null) {
                return this.response.raw().request();
            }
            return new Request$Builder().url("http://localhost").build();
        }
    }

    public static <T> Call<T> defer(Callable<Call<T>> callable) {
        return new DeferredCall(callable);
    }

    public static <T> Call<T> response(T successValue) {
        return new FakeCall(Response.success(successValue), null);
    }

    public static <T> Call<T> response(Response<T> response) {
        return new FakeCall(response, null);
    }

    public static <T> Call<T> failure(IOException failure) {
        return new FakeCall(null, failure);
    }

    private Calls() {
        throw new AssertionError("No instances.");
    }
}
