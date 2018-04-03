package com.squareup.otto;

import android.os.Looper;

public interface ThreadEnforcer {
    public static final ThreadEnforcer ANY = new C06581();
    public static final ThreadEnforcer MAIN = new C06592();

    static class C06581 implements ThreadEnforcer {
        C06581() {
        }

        public void enforce(Bus bus) {
        }
    }

    static class C06592 implements ThreadEnforcer {
        C06592() {
        }

        public void enforce(Bus bus) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                throw new IllegalStateException("Event bus " + bus + " accessed from non-main thread " + Looper.myLooper());
            }
        }
    }

    void enforce(Bus bus);
}
