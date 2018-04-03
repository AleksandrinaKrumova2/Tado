package com.nhaarman.supertooltips;

import android.graphics.Typeface;
import android.view.View;

public class ToolTip {
    private AnimationType mAnimationType = AnimationType.FROM_MASTER_VIEW;
    private int mColor = 0;
    private View mContentView = null;
    private boolean mItemsLimitExceded;
    private boolean mShouldShowShadow;
    private CharSequence mText = null;
    private int mTextColor;
    private int mTextResId = 0;
    private Typeface mTypeface = null;

    public enum AnimationType {
        FROM_MASTER_VIEW,
        FROM_TOP,
        NONE
    }

    public ToolTip withText(CharSequence text) {
        this.mText = text;
        this.mTextResId = 0;
        return this;
    }

    public ToolTip withText(int resId) {
        this.mTextResId = resId;
        this.mText = null;
        return this;
    }

    public ToolTip withText(int resId, Typeface tf) {
        this.mTextResId = resId;
        this.mText = null;
        withTypeface(tf);
        return this;
    }

    public ToolTip withColor(int color) {
        this.mColor = color;
        return this;
    }

    public ToolTip withTextColor(int color) {
        this.mTextColor = color;
        return this;
    }

    public ToolTip withContentView(View view) {
        this.mContentView = view;
        return this;
    }

    public ToolTip withAnimationType(AnimationType animationType) {
        this.mAnimationType = animationType;
        return this;
    }

    public ToolTip withShadow() {
        this.mShouldShowShadow = true;
        return this;
    }

    public ToolTip withoutShadow() {
        this.mShouldShowShadow = false;
        return this;
    }

    public void withTypeface(Typeface typeface) {
        this.mTypeface = typeface;
    }

    public CharSequence getText() {
        return this.mText;
    }

    public int getTextResId() {
        return this.mTextResId;
    }

    public int getColor() {
        return this.mColor;
    }

    public int getTextColor() {
        return this.mTextColor;
    }

    public View getContentView() {
        return this.mContentView;
    }

    public AnimationType getAnimationType() {
        return this.mAnimationType;
    }

    public boolean shouldShowShadow() {
        return this.mShouldShowShadow;
    }

    public Typeface getTypeface() {
        return this.mTypeface;
    }

    public boolean isItemsLimitExceded() {
        return this.mItemsLimitExceded;
    }

    public void setItemsLimitExceded(boolean itemsLimitExceded) {
        this.mItemsLimitExceded = itemsLimitExceded;
    }
}
