package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import okhttp3.Call.Factory;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.BuiltInConverters.ToStringConverter;

public final class Retrofit {
    final List<CallAdapter$Factory> adapterFactories;
    final HttpUrl baseUrl;
    final Factory callFactory;
    @Nullable
    final Executor callbackExecutor;
    final List<Converter$Factory> converterFactories;
    private final Map<Method, ServiceMethod<?, ?>> serviceMethodCache = new ConcurrentHashMap();
    final boolean validateEagerly;

    public static final class Builder {
        private final List<CallAdapter$Factory> adapterFactories;
        private HttpUrl baseUrl;
        @Nullable
        private Factory callFactory;
        @Nullable
        private Executor callbackExecutor;
        private final List<Converter$Factory> converterFactories;
        private final Platform platform;
        private boolean validateEagerly;

        Builder(Platform platform) {
            this.converterFactories = new ArrayList();
            this.adapterFactories = new ArrayList();
            this.platform = platform;
            this.converterFactories.add(new BuiltInConverters());
        }

        public Builder() {
            this(Platform.get());
        }

        Builder(Retrofit retrofit) {
            this.converterFactories = new ArrayList();
            this.adapterFactories = new ArrayList();
            this.platform = Platform.get();
            this.callFactory = retrofit.callFactory;
            this.baseUrl = retrofit.baseUrl;
            this.converterFactories.addAll(retrofit.converterFactories);
            this.adapterFactories.addAll(retrofit.adapterFactories);
            this.adapterFactories.remove(this.adapterFactories.size() - 1);
            this.callbackExecutor = retrofit.callbackExecutor;
            this.validateEagerly = retrofit.validateEagerly;
        }

        public Builder client(OkHttpClient client) {
            return callFactory((Factory) Utils.checkNotNull(client, "client == null"));
        }

        public Builder callFactory(Factory factory) {
            this.callFactory = (Factory) Utils.checkNotNull(factory, "factory == null");
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            Utils.checkNotNull(baseUrl, "baseUrl == null");
            HttpUrl httpUrl = HttpUrl.parse(baseUrl);
            if (httpUrl != null) {
                return baseUrl(httpUrl);
            }
            throw new IllegalArgumentException("Illegal URL: " + baseUrl);
        }

        public Builder baseUrl(HttpUrl baseUrl) {
            Utils.checkNotNull(baseUrl, "baseUrl == null");
            List<String> pathSegments = baseUrl.pathSegments();
            if ("".equals(pathSegments.get(pathSegments.size() - 1))) {
                this.baseUrl = baseUrl;
                return this;
            }
            throw new IllegalArgumentException("baseUrl must end in /: " + baseUrl);
        }

        public Builder addConverterFactory(Converter$Factory factory) {
            this.converterFactories.add(Utils.checkNotNull(factory, "factory == null"));
            return this;
        }

        public Builder addCallAdapterFactory(CallAdapter$Factory factory) {
            this.adapterFactories.add(Utils.checkNotNull(factory, "factory == null"));
            return this;
        }

        public Builder callbackExecutor(Executor executor) {
            this.callbackExecutor = (Executor) Utils.checkNotNull(executor, "executor == null");
            return this;
        }

        public Builder validateEagerly(boolean validateEagerly) {
            this.validateEagerly = validateEagerly;
            return this;
        }

        public Retrofit build() {
            if (this.baseUrl == null) {
                throw new IllegalStateException("Base URL required.");
            }
            Factory callFactory = this.callFactory;
            if (callFactory == null) {
                callFactory = new OkHttpClient();
            }
            Executor callbackExecutor = this.callbackExecutor;
            if (callbackExecutor == null) {
                callbackExecutor = this.platform.defaultCallbackExecutor();
            }
            List<CallAdapter$Factory> adapterFactories = new ArrayList(this.adapterFactories);
            adapterFactories.add(this.platform.defaultCallAdapterFactory(callbackExecutor));
            return new Retrofit(callFactory, this.baseUrl, new ArrayList(this.converterFactories), adapterFactories, callbackExecutor, this.validateEagerly);
        }
    }

    Retrofit(Factory callFactory, HttpUrl baseUrl, List<Converter$Factory> converterFactories, List<CallAdapter$Factory> adapterFactories, @Nullable Executor callbackExecutor, boolean validateEagerly) {
        this.callFactory = callFactory;
        this.baseUrl = baseUrl;
        this.converterFactories = Collections.unmodifiableList(converterFactories);
        this.adapterFactories = Collections.unmodifiableList(adapterFactories);
        this.callbackExecutor = callbackExecutor;
        this.validateEagerly = validateEagerly;
    }

    public <T> T create(Class<T> service) {
        Utils.validateServiceInterface(service);
        if (this.validateEagerly) {
            eagerlyValidateMethods(service);
        }
        return Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service}, new 1(this, service));
    }

    private void eagerlyValidateMethods(Class<?> service) {
        Platform platform = Platform.get();
        for (Method method : service.getDeclaredMethods()) {
            if (!platform.isDefaultMethod(method)) {
                loadServiceMethod(method);
            }
        }
    }

    ServiceMethod<?, ?> loadServiceMethod(Method method) {
        ServiceMethod<?, ?> result = (ServiceMethod) this.serviceMethodCache.get(method);
        if (result != null) {
            return result;
        }
        synchronized (this.serviceMethodCache) {
            result = (ServiceMethod) this.serviceMethodCache.get(method);
            if (result == null) {
                result = new retrofit2.ServiceMethod.Builder(this, method).build();
                this.serviceMethodCache.put(method, result);
            }
        }
        return result;
    }

    public Factory callFactory() {
        return this.callFactory;
    }

    public HttpUrl baseUrl() {
        return this.baseUrl;
    }

    public List<CallAdapter$Factory> callAdapterFactories() {
        return this.adapterFactories;
    }

    public CallAdapter<?, ?> callAdapter(Type returnType, Annotation[] annotations) {
        return nextCallAdapter(null, returnType, annotations);
    }

    public CallAdapter<?, ?> nextCallAdapter(@Nullable CallAdapter$Factory skipPast, Type returnType, Annotation[] annotations) {
        int i;
        Utils.checkNotNull(returnType, "returnType == null");
        Utils.checkNotNull(annotations, "annotations == null");
        int start = this.adapterFactories.indexOf(skipPast) + 1;
        int count = this.adapterFactories.size();
        for (i = start; i < count; i++) {
            CallAdapter<?, ?> adapter = ((CallAdapter$Factory) this.adapterFactories.get(i)).get(returnType, annotations, this);
            if (adapter != null) {
                return adapter;
            }
        }
        StringBuilder builder = new StringBuilder("Could not locate call adapter for ").append(returnType).append(".\n");
        if (skipPast != null) {
            builder.append("  Skipped:");
            for (i = 0; i < start; i++) {
                builder.append("\n   * ").append(((CallAdapter$Factory) this.adapterFactories.get(i)).getClass().getName());
            }
            builder.append('\n');
        }
        builder.append("  Tried:");
        count = this.adapterFactories.size();
        for (i = start; i < count; i++) {
            builder.append("\n   * ").append(((CallAdapter$Factory) this.adapterFactories.get(i)).getClass().getName());
        }
        throw new IllegalArgumentException(builder.toString());
    }

    public List<Converter$Factory> converterFactories() {
        return this.converterFactories;
    }

    public <T> Converter<T, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations) {
        return nextRequestBodyConverter(null, type, parameterAnnotations, methodAnnotations);
    }

    public <T> Converter<T, RequestBody> nextRequestBodyConverter(@Nullable Converter$Factory skipPast, Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations) {
        int i;
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(parameterAnnotations, "parameterAnnotations == null");
        Utils.checkNotNull(methodAnnotations, "methodAnnotations == null");
        int start = this.converterFactories.indexOf(skipPast) + 1;
        int count = this.converterFactories.size();
        for (i = start; i < count; i++) {
            Converter<?, RequestBody> converter = ((Converter$Factory) this.converterFactories.get(i)).requestBodyConverter(type, parameterAnnotations, methodAnnotations, this);
            if (converter != null) {
                return converter;
            }
        }
        StringBuilder builder = new StringBuilder("Could not locate RequestBody converter for ").append(type).append(".\n");
        if (skipPast != null) {
            builder.append("  Skipped:");
            for (i = 0; i < start; i++) {
                builder.append("\n   * ").append(((Converter$Factory) this.converterFactories.get(i)).getClass().getName());
            }
            builder.append('\n');
        }
        builder.append("  Tried:");
        count = this.converterFactories.size();
        for (i = start; i < count; i++) {
            builder.append("\n   * ").append(((Converter$Factory) this.converterFactories.get(i)).getClass().getName());
        }
        throw new IllegalArgumentException(builder.toString());
    }

    public <T> Converter<ResponseBody, T> responseBodyConverter(Type type, Annotation[] annotations) {
        return nextResponseBodyConverter(null, type, annotations);
    }

    public <T> Converter<ResponseBody, T> nextResponseBodyConverter(@Nullable Converter$Factory skipPast, Type type, Annotation[] annotations) {
        int i;
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(annotations, "annotations == null");
        int start = this.converterFactories.indexOf(skipPast) + 1;
        int count = this.converterFactories.size();
        for (i = start; i < count; i++) {
            Converter<ResponseBody, ?> converter = ((Converter$Factory) this.converterFactories.get(i)).responseBodyConverter(type, annotations, this);
            if (converter != null) {
                return converter;
            }
        }
        StringBuilder builder = new StringBuilder("Could not locate ResponseBody converter for ").append(type).append(".\n");
        if (skipPast != null) {
            builder.append("  Skipped:");
            for (i = 0; i < start; i++) {
                builder.append("\n   * ").append(((Converter$Factory) this.converterFactories.get(i)).getClass().getName());
            }
            builder.append('\n');
        }
        builder.append("  Tried:");
        count = this.converterFactories.size();
        for (i = start; i < count; i++) {
            builder.append("\n   * ").append(((Converter$Factory) this.converterFactories.get(i)).getClass().getName());
        }
        throw new IllegalArgumentException(builder.toString());
    }

    public <T> Converter<T, String> stringConverter(Type type, Annotation[] annotations) {
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(annotations, "annotations == null");
        int count = this.converterFactories.size();
        for (int i = 0; i < count; i++) {
            Converter<?, String> converter = ((Converter$Factory) this.converterFactories.get(i)).stringConverter(type, annotations, this);
            if (converter != null) {
                return converter;
            }
        }
        return ToStringConverter.INSTANCE;
    }

    @Nullable
    public Executor callbackExecutor() {
        return this.callbackExecutor;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }
}
