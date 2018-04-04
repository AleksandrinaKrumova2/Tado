package org.joda.time.tz;

public class ZoneInfoLogger {
    static ThreadLocal<Boolean> cVerbose = new C14001();

    static class C14001 extends ThreadLocal<Boolean> {
        C14001() {
        }

        protected Boolean initialValue() {
            return Boolean.FALSE;
        }
    }

    public static boolean verbose() {
        return ((Boolean) cVerbose.get()).booleanValue();
    }

    public static void set(boolean z) {
        cVerbose.set(Boolean.valueOf(z));
    }
}
