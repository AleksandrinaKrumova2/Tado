package io.fabric.sdk.android;

public class SilentLogger implements Logger {
    private int logLevel = 7;

    public boolean isLoggable(String tag, int level) {
        return false;
    }

    public void mo3689d(String tag, String text, Throwable throwable) {
    }

    public void mo3700v(String tag, String text, Throwable throwable) {
    }

    public void mo3694i(String tag, String text, Throwable throwable) {
    }

    public void mo3702w(String tag, String text, Throwable throwable) {
    }

    public void mo3691e(String tag, String text, Throwable throwable) {
    }

    public void mo3688d(String tag, String text) {
    }

    public void mo3699v(String tag, String text) {
    }

    public void mo3693i(String tag, String text) {
    }

    public void mo3701w(String tag, String text) {
    }

    public void mo3690e(String tag, String text) {
    }

    public void log(int priority, String tag, String msg) {
    }

    public void log(int priority, String tag, String msg, boolean forceLog) {
    }

    public int getLogLevel() {
        return this.logLevel;
    }

    public void setLogLevel(int logLevel) {
    }
}
