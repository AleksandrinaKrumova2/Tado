package com.tado.android.adapters.demo;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;

public class DemoBottomSheetURLItem extends DemoBottomSheetItem {
    private String uri;

    public DemoBottomSheetURLItem(String name, @DrawableRes int iconRes, String uri, AppCompatActivity activity, String trackingString) {
        super(name, iconRes, activity, trackingString);
        this.uri = uri;
    }

    public String getUri() {
        return this.uri;
    }

    public void onClick() {
        if (this.activity.get() != null) {
            trackOnClick();
            ((AppCompatActivity) this.activity.get()).startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.uri)));
        }
    }
}
