package com.tado.android.utils;

import com.crashlytics.android.Crashlytics;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLPeerUnverifiedException;

public class CrashlyticsLogHelper {
    private static final List<Class> IGNORE_EXCEPTION_LIST = new C12491();

    static class C12491 extends ArrayList<Class> {
        C12491() {
            add(UnknownHostException.class);
            add(ConnectException.class);
            add(SocketTimeoutException.class);
            add(SSLException.class);
            add(SSLPeerUnverifiedException.class);
        }
    }

    private static boolean ignoreException(Throwable t) {
        for (Class clazz : IGNORE_EXCEPTION_LIST) {
            if (clazz.isInstance(t)) {
                return true;
            }
        }
        return false;
    }

    public static void logException(Throwable t) {
        if (ignoreException(t)) {
            Crashlytics.log(6, Crashlytics.TAG, t.getMessage());
        } else {
            Crashlytics.logException(t);
        }
    }
}
