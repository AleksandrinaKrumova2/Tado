package com.tado.android.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.crashlytics.android.Crashlytics;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;

public class Snitcher {
    private static final String TAG = "TADO";

    public static class Builder extends Snitcher {
        int level;
        String tag;
        boolean toCrashlytics;
        boolean toLogcat;
        boolean toLogger;

        private Builder() {
            this.toLogger = false;
            this.toCrashlytics = false;
            this.toLogcat = true;
            this.level = 4;
            this.tag = Snitcher.TAG;
        }

        public Builder toLogger() {
            this.toLogger = true;
            return this;
        }

        public Builder toCrashlytics() {
            this.toCrashlytics = true;
            return this;
        }

        public Builder hideLogcat() {
            this.toLogcat = false;
            return this;
        }

        public void log(String msg, Object... args) {
            log(this.level, msg, args);
        }

        public void log(int level, @NonNull String msg, Object... args) {
            log(level, Snitcher.TAG, msg, args);
        }

        public void log(String tag, @NonNull String msg, Object... args) {
            log(this.level, tag, msg, args);
        }

        public void log(int level, String tag, @NonNull String msg, Object... args) {
            String toPrint = getMsg(tag, msg, args);
            String validatedTag = getTag(tag);
            if (this.toLogger) {
                GeolocationLogger.addSimpleLog(getLoggerMsg(level, validatedTag, toPrint));
            }
            if (this.toCrashlytics) {
                Crashlytics.log(level, validatedTag, toPrint);
            }
            if (this.toLogcat && shouldLog() && Log.isLoggable(validatedTag, level)) {
                Log.println(level, validatedTag, toPrint);
            }
        }

        public void logException(Throwable e) {
            logException(Snitcher.TAG, null, e);
        }

        public void logException(@NonNull String msg, Throwable e) {
            logException(Snitcher.TAG, msg, e);
        }

        public void logException(String tag, @Nullable String msg, Throwable e) {
            StringBuilder sb = new StringBuilder();
            if (e != null) {
                if (e.getMessage() != null) {
                    sb.append(e.getMessage());
                }
                for (StackTraceElement trace : e.getStackTrace()) {
                    sb.append(trace.toString()).append('\n');
                }
            }
            if (msg != null) {
                sb.insert(0, msg + '\n');
            }
            if (this.toLogger) {
                GeolocationLogger.addSimpleLog(sb.toString());
            }
            if (this.toCrashlytics) {
                CrashlyticsLogHelper.logException(e);
            }
            if (this.toLogcat && shouldLog()) {
                Log.println(6, getTag(tag), sb.toString());
            }
        }

        private boolean shouldLog() {
            boolean shouldLog = true;
            try {
                shouldLog = TadoApplication.getTadoAppContext().getResources().getBoolean(C0676R.bool.logger);
            } catch (Exception e) {
            }
            return shouldLog;
        }

        private String getMsg(String tag, String msg, Object... args) {
            String toPrint = msg;
            if (msg != null) {
                toPrint = msg;
                if (args != null) {
                    try {
                        if (args.length > 0) {
                            toPrint = String.format(msg, args);
                        }
                    } catch (Exception e) {
                    }
                }
            }
            toPrint = trace(Thread.currentThread().getStackTrace()) + ":" + toPrint;
            return toPrint;
        }

        private String getLoggerMsg(int level, String tag, String toPrint) {
            String loggerPrint;
            if (level == 6) {
                loggerPrint = "***** " + toPrint + " *****";
            } else {
                loggerPrint = toPrint;
            }
            long threadID = Thread.currentThread().getId();
            StringBuilder sb = new StringBuilder();
            sb.append(threadID).append(":");
            if (tag != null) {
                sb.append(tag).append(".");
            }
            sb.append(loggerPrint);
            return sb.toString();
        }

        private String getTag(String newTag) {
            if (newTag != null) {
                this.tag = newTag.substring(0, Math.min(23, newTag.length()));
            } else {
                this.tag = Snitcher.TAG;
            }
            return this.tag;
        }

        private static String trace(StackTraceElement[] e) {
            for (StackTraceElement s : e) {
                if (!s.getMethodName().equals("getThreadStackTrace") && !s.getMethodName().equals("getStackTrace") && !s.getMethodName().equals("log") && !s.getMethodName().equals("getMsg")) {
                    return s.getMethodName();
                }
            }
            return "";
        }
    }

    public static Builder start() {
        return new Builder();
    }
}
