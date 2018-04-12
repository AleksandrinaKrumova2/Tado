package retrofit2.mock;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import retrofit2.Call;
import retrofit2.Retrofit;

public final class BehaviorDelegate<T> {
    private final NetworkBehavior behavior;
    private final ExecutorService executor;
    final Retrofit retrofit;
    private final Class<T> service;

    BehaviorDelegate(Retrofit retrofit, NetworkBehavior behavior, ExecutorService executor, Class<T> service) {
        this.retrofit = retrofit;
        this.behavior = behavior;
        this.executor = executor;
        this.service = service;
    }

    public T returningResponse(Object response) {
        return returning(Calls.response(response));
    }

    public <R> T returning(Call<R> call) {
        final Call<R> behaviorCall = new BehaviorCall(this.behavior, this.executor, call);
        return Proxy.newProxyInstance(this.service.getClassLoader(), new Class[]{this.service}, new InvocationHandler() {
            public T invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return BehaviorDelegate.this.retrofit.callAdapter(method.getGenericReturnType(), method.getAnnotations()).adapt(behaviorCall);
            }
        });
    }
}
