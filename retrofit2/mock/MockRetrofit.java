package retrofit2.mock;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import retrofit2.Retrofit;

public final class MockRetrofit {
    private final NetworkBehavior behavior;
    private final ExecutorService executor;
    private final Retrofit retrofit;

    public static final class Builder {
        private NetworkBehavior behavior;
        private ExecutorService executor;
        private final Retrofit retrofit;

        public Builder(Retrofit retrofit) {
            if (retrofit == null) {
                throw new NullPointerException("retrofit == null");
            }
            this.retrofit = retrofit;
        }

        public Builder networkBehavior(NetworkBehavior behavior) {
            if (behavior == null) {
                throw new NullPointerException("behavior == null");
            }
            this.behavior = behavior;
            return this;
        }

        public Builder backgroundExecutor(ExecutorService executor) {
            if (executor == null) {
                throw new NullPointerException("executor == null");
            }
            this.executor = executor;
            return this;
        }

        public MockRetrofit build() {
            if (this.behavior == null) {
                this.behavior = NetworkBehavior.create();
            }
            if (this.executor == null) {
                this.executor = Executors.newCachedThreadPool();
            }
            return new MockRetrofit(this.retrofit, this.behavior, this.executor);
        }
    }

    MockRetrofit(Retrofit retrofit, NetworkBehavior behavior, ExecutorService executor) {
        this.retrofit = retrofit;
        this.behavior = behavior;
        this.executor = executor;
    }

    public Retrofit retrofit() {
        return this.retrofit;
    }

    public NetworkBehavior networkBehavior() {
        return this.behavior;
    }

    public Executor backgroundExecutor() {
        return this.executor;
    }

    public <T> BehaviorDelegate<T> create(Class<T> service) {
        return new BehaviorDelegate(this.retrofit, this.behavior, this.executor, service);
    }
}
