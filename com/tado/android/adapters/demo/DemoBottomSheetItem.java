package com.tado.android.adapters.demo;

import android.app.Activity;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import com.tado.android.analytics.AnalyticsConstants.Events;
import com.tado.android.analytics.AnalyticsHelper;
import java.lang.ref.WeakReference;

public abstract class DemoBottomSheetItem {
    protected WeakReference<AppCompatActivity> activity;
    @DrawableRes
    private int iconRes;
    private String name;
    protected String trackingString;

    public abstract void onClick();

    public DemoBottomSheetItem(String name, @DrawableRes int iconRes, AppCompatActivity activity, String trackingString) {
        this.name = name;
        this.iconRes = iconRes;
        this.activity = new WeakReference(activity);
        this.trackingString = trackingString;
    }

    public String getName() {
        return this.name;
    }

    public int getIconRes() {
        return this.iconRes;
    }

    protected void trackOnClick() {
        if (this.activity.get() != null) {
            AnalyticsHelper.trackEvent((Activity) this.activity.get(), Events.DEMO, this.trackingString);
        }
    }

    public boolean shouldCloseParentOnClick() {
        return false;
    }
}
