package com.tado.android.analytics;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.HitBuilders.EventBuilder;
import com.google.android.gms.analytics.HitBuilders.ScreenViewBuilder;
import com.google.android.gms.analytics.Tracker;
import com.tado.android.app.TadoApplication;

public class AnalyticsHelper {
    private static final boolean SHOW_TOASTS_FOR_EVENTS = false;
    private static final boolean SHOW_TOASTS_FOR_PAGEVIEWS = false;

    public static void trackPageView(Activity activity, String pageName) {
        if (activity != null) {
            trackPageView(activity.getApplication(), pageName);
        }
    }

    @Deprecated
    public static void trackPageView(@NonNull Application application, String pageName) {
        if (!(application instanceof TadoApplication)) {
            throw new IllegalArgumentException("application parameter has to be an instance of TadoApplication");
        } else if (pageName == null) {
            throw new IllegalArgumentException("pageName parameter can't be null");
        } else {
            try {
                Tracker mTracker = ((TadoApplication) application).getDefaultTracker();
                mTracker.setScreenName(pageName);
                mTracker.send(new ScreenViewBuilder().build());
            } catch (Exception e) {
                Crashlytics.logException(e);
            }
        }
    }

    @Deprecated
    public static void trackEvent(Application application, String category, String action) {
        trackEvent(application, category, action, null, null);
    }

    @Deprecated
    public static void trackEvent(Application application, String category, String action, String label) {
        trackEvent(application, category, action, label, null);
    }

    @Deprecated
    public static void trackEvent(Application application, String category, String action, Long value) {
        trackEvent(application, category, action, null, value);
    }

    public static void trackEvent(Activity activity, String category, String action, String label, Long value) {
        if (activity != null) {
            trackEvent(activity.getApplication(), category, action, label, value);
        }
    }

    public static void trackEvent(Activity activity, String category, String action, Long value) {
        trackEvent(activity, category, action, null, value);
    }

    public static void trackEvent(Activity activity, String category, String action) {
        trackEvent(activity, category, action, null, null);
    }

    public static void trackEvent(Activity activity, String category, String action, String label) {
        trackEvent(activity, category, action, label, null);
    }

    @Deprecated
    public static void trackEvent(Application application, String category, String action, String label, Long value) {
        if (application instanceof TadoApplication) {
            try {
                Tracker mTracker = ((TadoApplication) application).getDefaultTracker();
                EventBuilder eventBuilder = new EventBuilder();
                if (category == null) {
                    throw new NullPointerException("category parameter can't be null");
                }
                eventBuilder.setCategory(category);
                if (action == null) {
                    throw new NullPointerException("action parameter can't be null");
                }
                eventBuilder.setAction(action);
                if (label != null) {
                    eventBuilder.setLabel(label);
                }
                if (value != null) {
                    eventBuilder.setValue(value.longValue());
                }
                mTracker.send(eventBuilder.build());
                return;
            } catch (Exception e) {
                Crashlytics.logException(e);
                return;
            }
        }
        throw new IllegalArgumentException("application parameter has to be an instance of TadoApplication");
    }
}
