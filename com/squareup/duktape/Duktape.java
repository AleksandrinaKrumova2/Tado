package com.squareup.duktape;

import android.support.annotation.Keep;
import java.io.Closeable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public final class Duktape implements Closeable {
    private long context;

    private static native Object call(long j, long j2, Object obj, Object[] objArr);

    private static native long createContext();

    private static native void destroyContext(long j);

    private static native Object evaluate(long j, String str, String str2);

    private static native long get(long j, String str, Object[] objArr);

    private static native void set(long j, String str, Object obj, Object[] objArr);

    static {
        System.loadLibrary("duktape");
    }

    public static Duktape create() {
        long context = createContext();
        if (context != 0) {
            return new Duktape(context);
        }
        throw new OutOfMemoryError("Cannot create Duktape instance");
    }

    private Duktape(long context) {
        this.context = context;
    }

    public synchronized Object evaluate(String script, String fileName) {
        return evaluate(this.context, script, fileName);
    }

    public synchronized Object evaluate(String script) {
        return evaluate(this.context, script, "?");
    }

    public synchronized <T> void set(String name, Class<T> type, T object) {
        if (!type.isInterface()) {
            throw new UnsupportedOperationException("Only interfaces can be bound. Received: " + type);
        } else if (type.getInterfaces().length > 0) {
            throw new UnsupportedOperationException(type + " must not extend other interfaces");
        } else if (type.isInstance(object)) {
            LinkedHashMap<String, Method> methods = new LinkedHashMap();
            for (Method method : type.getMethods()) {
                if (methods.put(method.getName(), method) != null) {
                    throw new UnsupportedOperationException(method.getName() + " is overloaded in " + type);
                }
            }
            set(this.context, name, object, methods.values().toArray());
        } else {
            throw new IllegalArgumentException(object.getClass() + " is not an instance of " + type);
        }
    }

    public synchronized <T> T get(String name, Class<T> type) {
        final long instance;
        final Duktape duktape;
        final String str;
        final Class<T> cls;
        synchronized (this) {
            if (!type.isInterface()) {
                throw new UnsupportedOperationException("Only interfaces can be proxied. Received: " + type);
            } else if (type.getInterfaces().length > 0) {
                throw new UnsupportedOperationException(type + " must not extend other interfaces");
            } else {
                LinkedHashMap<String, Method> methods = new LinkedHashMap();
                for (Method method : type.getMethods()) {
                    if (methods.put(method.getName(), method) != null) {
                        throw new UnsupportedOperationException(method.getName() + " is overloaded in " + type);
                    }
                }
                instance = get(this.context, name, methods.values().toArray());
                duktape = this;
                str = name;
                cls = type;
            }
        }
        return Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getDeclaringClass() == Object.class) {
                    return method.invoke(this, args);
                }
                Object access$100;
                synchronized (duktape) {
                    access$100 = Duktape.call(duktape.context, instance, method, args);
                }
                return access$100;
            }

            public String toString() {
                return String.format("DuktapeProxy{name=%s, type=%s}", new Object[]{str, cls.getName()});
            }
        });
    }

    public synchronized void close() {
        if (this.context != 0) {
            long contextToClose = this.context;
            this.context = 0;
            destroyContext(contextToClose);
        }
    }

    protected synchronized void finalize() throws Throwable {
        if (this.context != 0) {
            Logger.getLogger(getClass().getName()).warning("Duktape instance leaked!");
        }
    }

    @Keep
    private static int getLocalTimeZoneOffset(double t) {
        return (int) TimeUnit.MILLISECONDS.toSeconds((long) TimeZone.getDefault().getOffset((long) t));
    }
}
