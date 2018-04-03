package com.squareup.duktape;

import android.support.annotation.Keep;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Keep
public final class DuktapeException extends RuntimeException {
    private static final String STACK_TRACE_CLASS_NAME = "JavaScript";
    private static final Pattern STACK_TRACE_PATTERN = Pattern.compile("\\s*at ([^\\s^\\[]+) \\(([^\\s]+):(\\d+)\\).*$");

    public DuktapeException(String detailMessage) {
        super(getErrorMessage(detailMessage));
        addDuktapeStack(this, detailMessage);
    }

    static void addDuktapeStack(Throwable throwable, String detailMessage) {
        String[] lines = detailMessage.split("\n", -1);
        if (lines.length > 1) {
            List<StackTraceElement> elements = new ArrayList();
            boolean spliced = false;
            for (StackTraceElement stackTraceElement : throwable.getStackTrace()) {
                if (!spliced && stackTraceElement.isNativeMethod() && stackTraceElement.getClassName().equals(Duktape.class.getName()) && stackTraceElement.getMethodName().equals("evaluate")) {
                    for (int i = 1; i < lines.length; i++) {
                        StackTraceElement jsElement = toStackTraceElement(lines[i]);
                        if (jsElement != null) {
                            elements.add(jsElement);
                        }
                    }
                    spliced = true;
                }
                elements.add(stackTraceElement);
            }
            throwable.setStackTrace((StackTraceElement[]) elements.toArray(new StackTraceElement[elements.size()]));
        }
    }

    private static String getErrorMessage(String detailMessage) {
        int end = detailMessage.indexOf(10);
        if (end > 0) {
            return detailMessage.substring(0, end);
        }
        return detailMessage;
    }

    private static StackTraceElement toStackTraceElement(String s) {
        Matcher m = STACK_TRACE_PATTERN.matcher(s);
        if (m.matches()) {
            return new StackTraceElement(STACK_TRACE_CLASS_NAME, m.group(1), m.group(2), Integer.parseInt(m.group(3)));
        }
        return null;
    }
}
