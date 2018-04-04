package io.fabric.sdk.android;

public interface Logger {
    void mo3688d(String str, String str2);

    void mo3689d(String str, String str2, Throwable th);

    void mo3690e(String str, String str2);

    void mo3691e(String str, String str2, Throwable th);

    int getLogLevel();

    void mo3693i(String str, String str2);

    void mo3694i(String str, String str2, Throwable th);

    boolean isLoggable(String str, int i);

    void log(int i, String str, String str2);

    void log(int i, String str, String str2, boolean z);

    void setLogLevel(int i);

    void mo3699v(String str, String str2);

    void mo3700v(String str, String str2, Throwable th);

    void mo3701w(String str, String str2);

    void mo3702w(String str, String str2, Throwable th);
}
