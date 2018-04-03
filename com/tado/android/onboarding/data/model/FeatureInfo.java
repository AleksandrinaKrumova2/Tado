package com.tado.android.onboarding.data.model;

import android.graphics.drawable.Drawable;

public class FeatureInfo {
    transient Drawable drawable;
    Link link;
    String text;
    String title;

    public static class Link {
        public String text;
        public String url;

        public Link(String text, String url) {
            this.text = text;
            this.url = url;
        }
    }

    private FeatureInfo() {
    }

    public String getTitle() {
        return this.title;
    }

    public String getText() {
        return this.text;
    }

    public Link getLink() {
        return this.link;
    }

    public Drawable getDrawable() {
        return this.drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public FeatureInfo(String title, String text, Link link, Drawable drawable) {
        this.title = title;
        this.text = text;
        this.link = link;
        this.drawable = drawable;
    }
}
