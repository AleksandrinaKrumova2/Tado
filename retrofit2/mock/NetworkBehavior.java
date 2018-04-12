package retrofit2.mock;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import okhttp3.ResponseBody;
import retrofit2.Response;

public final class NetworkBehavior {
    private static final int DEFAULT_DELAY_MS = 2000;
    private static final int DEFAULT_ERROR_PERCENT = 0;
    private static final int DEFAULT_FAILURE_PERCENT = 3;
    private static final int DEFAULT_VARIANCE_PERCENT = 40;
    private volatile long delayMs = 2000;
    private volatile Callable<Response<?>> errorFactory = new C14141();
    private volatile int errorPercent = 0;
    private volatile Throwable failureException;
    private volatile int failurePercent = 3;
    private final Random random;
    private volatile int variancePercent = 40;

    class C14141 implements Callable<Response<?>> {
        C14141() {
        }

        public Response<?> call() {
            return Response.error(500, ResponseBody.create(null, new byte[0]));
        }
    }

    public static NetworkBehavior create() {
        return new NetworkBehavior(new Random());
    }

    public static NetworkBehavior create(Random random) {
        if (random != null) {
            return new NetworkBehavior(random);
        }
        throw new NullPointerException("random == null");
    }

    private NetworkBehavior(Random random) {
        this.random = random;
        this.failureException = new MockRetrofitIOException();
        this.failureException.setStackTrace(new StackTraceElement[0]);
    }

    public void setDelay(long amount, TimeUnit unit) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive value.");
        }
        this.delayMs = unit.toMillis(amount);
    }

    public long delay(TimeUnit unit) {
        return TimeUnit.MILLISECONDS.convert(this.delayMs, unit);
    }

    public void setVariancePercent(int variancePercent) {
        checkPercentageValidity(variancePercent, "Variance percentage must be between 0 and 100.");
        this.variancePercent = variancePercent;
    }

    public int variancePercent() {
        return this.variancePercent;
    }

    public void setFailurePercent(int failurePercent) {
        checkPercentageValidity(failurePercent, "Failure percentage must be between 0 and 100.");
        this.failurePercent = failurePercent;
    }

    public int failurePercent() {
        return this.failurePercent;
    }

    public void setFailureException(Throwable exception) {
        if (exception == null) {
            throw new NullPointerException("exception == null");
        }
        this.failureException = exception;
    }

    public Throwable failureException() {
        return this.failureException;
    }

    public int errorPercent() {
        return this.errorPercent;
    }

    public void setErrorPercent(int errorPercent) {
        checkPercentageValidity(errorPercent, "Error percentage must be between 0 and 100.");
        this.errorPercent = errorPercent;
    }

    public void setErrorFactory(Callable<Response<?>> errorFactory) {
        if (errorFactory == null) {
            throw new NullPointerException("errorFactory == null");
        }
        this.errorFactory = errorFactory;
    }

    public Response<?> createErrorResponse() {
        try {
            Response<?> call = (Response) this.errorFactory.call();
            if (call == null) {
                throw new IllegalStateException("Error factory returned null.");
            } else if (!call.isSuccessful()) {
                return call;
            } else {
                throw new IllegalStateException("Error factory returned successful response.");
            }
        } catch (Exception e) {
            throw new IllegalStateException("Error factory threw an exception.", e);
        }
    }

    public boolean calculateIsFailure() {
        return this.random.nextInt(100) < this.failurePercent;
    }

    public boolean calculateIsError() {
        return this.random.nextInt(100) < this.errorPercent;
    }

    public long calculateDelay(TimeUnit unit) {
        float delta = ((float) this.variancePercent) / 100.0f;
        float lowerBound = 1.0f - delta;
        return TimeUnit.MILLISECONDS.convert((long) (((float) this.delayMs) * (lowerBound + (this.random.nextFloat() * ((1.0f + delta) - lowerBound)))), unit);
    }

    private static void checkPercentageValidity(int percentage, String message) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException(message);
        }
    }
}
