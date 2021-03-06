package com.wdullaer.materialdatetimepicker;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.support.annotation.AttrRes;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import com.nhaarman.supertooltips.ToolTipView;
import java.util.Calendar;

public class Utils {
    public static final int FULL_ALPHA = 255;
    public static final int PULSE_ANIMATOR_DURATION = 544;
    public static final int SELECTED_ALPHA = 255;
    public static final int SELECTED_ALPHA_THEME_DARK = 255;

    public static boolean isJellybeanOrLater() {
        return VERSION.SDK_INT >= 16;
    }

    @SuppressLint({"NewApi"})
    public static void tryAccessibilityAnnounce(View view, CharSequence text) {
        if (isJellybeanOrLater() && view != null && text != null) {
            view.announceForAccessibility(text);
        }
    }

    public static ObjectAnimator getPulseAnimator(View labelToAnimate, float decreaseRatio, float increaseRatio) {
        Keyframe k0 = Keyframe.ofFloat(0.0f, 1.0f);
        Keyframe k1 = Keyframe.ofFloat(0.275f, decreaseRatio);
        Keyframe k2 = Keyframe.ofFloat(0.69f, increaseRatio);
        Keyframe k3 = Keyframe.ofFloat(1.0f, 1.0f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofKeyframe(ToolTipView.SCALE_X_COMPAT, new Keyframe[]{k0, k1, k2, k3});
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofKeyframe(ToolTipView.SCALE_Y_COMPAT, new Keyframe[]{k0, k1, k2, k3});
        ObjectAnimator pulseAnimator = ObjectAnimator.ofPropertyValuesHolder(labelToAnimate, new PropertyValuesHolder[]{scaleX, scaleY});
        pulseAnimator.setDuration(544);
        return pulseAnimator;
    }

    public static int dpToPx(float dp, Resources resources) {
        return (int) TypedValue.applyDimension(1, dp, resources.getDisplayMetrics());
    }

    public static int darkenColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] = hsv[2] * 0.8f;
        return Color.HSVToColor(hsv);
    }

    public static int getAccentColorFromThemeIfAvailable(Context context) {
        TypedValue typedValue = new TypedValue();
        if (VERSION.SDK_INT >= 21) {
            context.getTheme().resolveAttribute(16843829, typedValue, true);
            return typedValue.data;
        }
        int colorAccentResId = context.getResources().getIdentifier("colorAccent", "attr", context.getPackageName());
        if (colorAccentResId == 0 || !context.getTheme().resolveAttribute(colorAccentResId, typedValue, true)) {
            return ContextCompat.getColor(context, R.color.mdtp_accent_color);
        }
        return typedValue.data;
    }

    public static boolean isDarkTheme(Context context, boolean current) {
        return resolveBoolean(context, R.attr.mdtp_theme_dark, current);
    }

    private static boolean resolveBoolean(Context context, @AttrRes int attr, boolean fallback) {
        TypedArray a = context.getTheme().obtainStyledAttributes(new int[]{attr});
        try {
            boolean z = a.getBoolean(0, fallback);
            return z;
        } finally {
            a.recycle();
        }
    }

    public static Calendar trimToMidnight(Calendar calendar) {
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar;
    }
}
