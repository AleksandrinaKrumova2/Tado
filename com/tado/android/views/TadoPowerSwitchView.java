package com.tado.android.views;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import com.tado.C0676R;
import com.tado.android.rest.model.Zone.TypeEnum;
import com.wdullaer.materialdatetimepicker.Utils;

public class TadoPowerSwitchView extends SwitchCompat {
    TypeEnum zoneType;

    public TadoPowerSwitchView(Context context) {
        super(context);
    }

    public TadoPowerSwitchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TadoPowerSwitchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        if (TypeEnum.HEATING == this.zoneType) {
            setTextAndLeftDrawable(C0676R.string.components_heatingSettingSelector_powerLabel, C0676R.drawable.ic_heating);
        } else if (TypeEnum.HOT_WATER == this.zoneType) {
            setTextAndLeftDrawable(C0676R.string.components_hotWaterSettingSelector_powerLabel, C0676R.drawable.zone_list_device_hot_water);
        } else if (TypeEnum.AIR_CONDITIONING == this.zoneType) {
            setTextAndLeftDrawable(C0676R.string.components_acSettingSelector_powerLabel, C0676R.drawable.ac_power);
        }
    }

    private void setTextAndLeftDrawable(@StringRes int textResource, @DrawableRes int drawableResource) {
        setText(textResource);
        Drawable icon = ContextCompat.getDrawable(getContext(), drawableResource);
        icon.mutate().setColorFilter(ContextCompat.getColor(getContext(), C0676R.color.cooling_grey), Mode.SRC_ATOP);
        setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
        setCompoundDrawablePadding(Utils.dpToPx(8.0f, getResources()));
    }

    public void setZoneType(TypeEnum zoneType) {
        this.zoneType = zoneType;
        initView();
    }
}
