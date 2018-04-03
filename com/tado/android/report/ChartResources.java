package com.tado.android.report;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.report.ChartToolbarViewElement.ToolbarButtonTypeEnum;
import com.tado.android.rest.model.CallForHeatEnum;
import com.tado.android.rest.model.StripeTypeEnum;
import com.tado.android.rest.model.StripeValue;
import com.tado.android.rest.model.installation.CoolingZoneSetting;
import com.tado.android.rest.model.installation.GenericZoneSetting;
import com.tado.android.rest.model.installation.ModeEnum;
import com.tado.android.utils.ColorFactory;
import com.tado.android.utils.ColorFactory.ColorPair;
import com.tado.android.utils.Constants;
import com.tado.android.utils.ResourceFactory;
import com.tado.android.utils.UserConfig;
import java.util.ArrayList;
import java.util.List;

class ChartResources {
    ChartResources() {
    }

    public static Bitmap getBitmap(@DrawableRes int resource, int bitmapSize, Context context) {
        Options options = new Options();
        options.outHeight = bitmapSize;
        options.outWidth = bitmapSize;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(context.getResources(), resource, options);
    }

    private static Bitmap getBitmapFromVectorResource(@DrawableRes int resource, Context context) {
        Drawable drawable = ResourceFactory.getVectorSupportDrawable(context, resource);
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap getScaledCompatBitmap(@DrawableRes int resource, Context context, int size) {
        return Bitmap.createScaledBitmap(getCompatBitmap(resource, context), size, size, true);
    }

    public static Bitmap getCompatBitmap(@DrawableRes int resource, Context context) {
        Drawable drawable = ContextCompat.getDrawable(context, resource);
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        return getBitmapFromVectorResource(resource, context);
    }

    public static Bitmap getSettingsModeBitmap(GenericZoneSetting setting, Context context) {
        int resourceId;
        if (setting instanceof CoolingZoneSetting) {
            CoolingZoneSetting coolingZoneSetting = (CoolingZoneSetting) setting;
            if (!coolingZoneSetting.getPowerBoolean()) {
                resourceId = C0676R.drawable.ic_off;
            } else if (ModeEnum.AUTO == coolingZoneSetting.getMode()) {
                resourceId = C0676R.drawable.ic_ac_mode_auto;
            } else if (ModeEnum.DRY == coolingZoneSetting.getMode()) {
                resourceId = C0676R.drawable.ic_ac_mode_dry;
            } else if (ModeEnum.HEAT == coolingZoneSetting.getMode()) {
                resourceId = C0676R.drawable.ic_ac_mode_heat;
            } else if (ModeEnum.FAN == coolingZoneSetting.getMode()) {
                resourceId = C0676R.drawable.ic_ac_mode_fan;
            } else {
                resourceId = C0676R.drawable.ic_ac_mode_cool;
            }
        } else if (setting.getPowerBoolean()) {
            resourceId = C0676R.drawable.ic_heat_temp;
        } else {
            resourceId = C0676R.drawable.ic_heat_off;
        }
        return getScaledCompatBitmap(resourceId, context, (int) ChartUtils.getDIPValue(12.0f));
    }

    public static int getCallForHeatColor(CallForHeatEnum callForHeatEnum) {
        float transparencyPercentage;
        if (CallForHeatEnum.LOW == callForHeatEnum) {
            transparencyPercentage = 0.2f;
        } else if (CallForHeatEnum.MEDIUM == callForHeatEnum) {
            transparencyPercentage = 0.4f;
        } else if (CallForHeatEnum.HIGH == callForHeatEnum) {
            transparencyPercentage = 0.6f;
        } else {
            transparencyPercentage = 0.0f;
        }
        return ColorUtils.setAlphaComponent(ContextCompat.getColor(TadoApplication.getTadoAppContext(), C0676R.color.report_call_for_heat_base), (int) (255.0f * transparencyPercentage));
    }

    public static List<Bitmap> getModeIcons(StripeTypeEnum stripeTypeEnum, Context context) {
        List<Bitmap> modeIcons = new ArrayList();
        if (StripeTypeEnum.HOME == stripeTypeEnum) {
            modeIcons.add(getCompatBitmap(C0676R.drawable.ic_home, context));
        } else if (StripeTypeEnum.AWAY == stripeTypeEnum) {
            modeIcons.add(getCompatBitmap(C0676R.drawable.ic_away, context));
        } else if (StripeTypeEnum.OVERLAY_ACTIVE == stripeTypeEnum) {
            modeIcons.add(getCompatBitmap(C0676R.drawable.ic_manual, context));
        } else if (StripeTypeEnum.MEASURING_DEVICE_DISCONNECTED == stripeTypeEnum) {
            modeIcons.add(getCompatBitmap(C0676R.drawable.ic_no_remote_control, context));
        } else if (StripeTypeEnum.OPEN_WINDOW_DETECTED == stripeTypeEnum) {
            modeIcons.add(getCompatBitmap(C0676R.drawable.ic_owd, context));
        } else if (StripeTypeEnum.HOME_LOCATION_BASED_CONTROL_OFF == stripeTypeEnum) {
            modeIcons.add(getCompatBitmap(C0676R.drawable.ic_home, context));
            modeIcons.add(getCompatBitmap(C0676R.drawable.ic_lbc_off, context));
        }
        return modeIcons;
    }

    public static ColorPair getStripeColor(StripeValue stripe) {
        boolean hasOverlay;
        boolean isAway;
        StripeTypeEnum type = stripe.getType();
        if (StripeTypeEnum.OVERLAY_ACTIVE == type) {
            hasOverlay = true;
        } else {
            hasOverlay = false;
        }
        if (StripeTypeEnum.AWAY == type) {
            isAway = true;
        } else {
            isAway = false;
        }
        if (StripeTypeEnum.MEASURING_DEVICE_DISCONNECTED == type || StripeTypeEnum.DAY_REPORT_UNAVAILABLE == type) {
            return new ColorPair(TadoApplication.getTadoAppContext(), C0676R.color.report_no_remote_inactive, C0676R.color.report_no_remote_active);
        }
        if (StripeTypeEnum.OPEN_WINDOW_DETECTED == type) {
            return new ColorPair(TadoApplication.getTadoAppContext(), C0676R.color.temp_off_bg, C0676R.color.temp_off);
        }
        if (stripe.getSetting() != null || StripeTypeEnum.AWAY == type) {
            return ColorFactory.getZoneStateBackgroundColorPair(stripe.getSetting(), false, hasOverlay, isAway, UserConfig.getCurrentZone().intValue());
        }
        return new ColorPair(TadoApplication.getTadoAppContext(), C0676R.color.report_no_remote_inactive, C0676R.color.report_no_remote_active);
    }

    public static Paint getHumidityLinePaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(ContextCompat.getColor(TadoApplication.getTadoAppContext(), C0676R.color.humidity_dash_line));
        paint.setStrokeWidth(ChartUtils.getDIPValue(2.0f));
        paint.setStrokeJoin(Join.ROUND);
        paint.setStyle(Style.STROKE);
        paint.setPathEffect(new DashPathEffect(new float[]{Constants.MAX_OFFSET_CELSIUS, 5.0f}, 0.0f));
        return paint;
    }

    public static Bitmap getToolbarButtonBitmap(ToolbarButtonTypeEnum type) {
        if (type == ToolbarButtonTypeEnum.HUMIDITY) {
            return getCompatBitmap(C0676R.drawable.ic_humidity, TadoApplication.getTadoAppContext());
        }
        if (type == ToolbarButtonTypeEnum.AC_ACTIVITY) {
            return getCompatBitmap(C0676R.drawable.zone_list_ac_power, TadoApplication.getTadoAppContext());
        }
        if (type == ToolbarButtonTypeEnum.CALL_FOR_HEAT) {
            return getCompatBitmap(C0676R.drawable.ic_heat_request, TadoApplication.getTadoAppContext());
        }
        if (type == ToolbarButtonTypeEnum.HOT_WATER) {
            return getScaledCompatBitmap(C0676R.drawable.toolbar_button_hotwater_normal, TadoApplication.getTadoAppContext(), (int) ChartUtils.getDIPValue(44.0f));
        }
        return getCompatBitmap(C0676R.drawable.ic_sunshine, TadoApplication.getTadoAppContext());
    }
}
