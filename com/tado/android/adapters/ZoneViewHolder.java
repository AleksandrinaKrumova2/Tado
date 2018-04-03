package com.tado.android.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.menu.ZoneItem;
import com.tado.android.times.view.model.ModeEnum;
import com.tado.android.utils.ColorFactory;
import com.tado.android.utils.ResourceFactory;
import com.tado.android.views.TadoStateTemperatureView;

public class ZoneViewHolder extends ViewHolder {
    View mLayout;
    TextView mZoneModeOrSetPointTextView;
    TextView mZoneNameTextView;
    ImageView mZoneNoConnection;
    TadoStateTemperatureView mZoneTadoTemperatureState;

    public ZoneViewHolder(View itemView) {
        super(itemView);
        this.mLayout = itemView.findViewById(C0676R.id.drawer_zone_list_item_layout);
        this.mZoneNoConnection = (ImageView) itemView.findViewById(C0676R.id.drawer_zone_item_no_connection);
        this.mZoneNameTextView = (TextView) itemView.findViewById(C0676R.id.drawer_zone_item_name);
        this.mZoneTadoTemperatureState = (TadoStateTemperatureView) itemView.findViewById(C0676R.id.drawer_zone_item_tado_state_temperature);
        this.mZoneModeOrSetPointTextView = (TextView) itemView.findViewById(C0676R.id.drawer_zone_item_mode_name);
    }

    public void bind(ZoneItem item) {
        int mNoConnectionVisibility;
        int mTemperatureLayoutVisibility;
        Drawable modeDrawable;
        Context context = this.mZoneNameTextView.getContext();
        this.mZoneNameTextView.setText(item.getZoneName());
        this.mZoneTadoTemperatureState.setTemperatureAndPrecision(item.getTemperatureValue(), item.getPrecisionValue());
        this.mLayout.setBackgroundColor(ColorFactory.getZoneItemBackgroundColor(item));
        this.mZoneNameTextView.setCompoundDrawablesWithIntrinsicBounds(ResourceFactory.getScaledTintedVectorDrawable(context, item.getZoneImageResource(), C0676R.color.white, 22), null, null, null);
        if (item.isOnline()) {
            mNoConnectionVisibility = 8;
            mTemperatureLayoutVisibility = 0;
            this.mZoneModeOrSetPointTextView.setVisibility(0);
        } else {
            mNoConnectionVisibility = 0;
            mTemperatureLayoutVisibility = 8;
            this.mZoneModeOrSetPointTextView.setVisibility(4);
        }
        if (item.isHotWaterZone()) {
            mTemperatureLayoutVisibility = 8;
            modeDrawable = ContextCompat.getDrawable(context, C0676R.drawable.ic_hot_water);
            this.mZoneModeOrSetPointTextView.setText(item.getTemperatureSetting());
        } else if (item.isHeatingZone()) {
            if (item.isPower()) {
                modeDrawable = ContextCompat.getDrawable(context, C0676R.drawable.ic_temperature);
            } else {
                modeDrawable = ContextCompat.getDrawable(context, C0676R.drawable.ic_heating);
            }
            this.mZoneModeOrSetPointTextView.setText(item.getTemperatureSetting());
        } else {
            modeDrawable = ContextCompat.getDrawable(context, ResourceFactory.getModeDrawableIcon(ModeEnum.getModeFromString(item.getMode()), item.isPower()));
            if (item.getTemperatureSetting() != null) {
                this.mZoneModeOrSetPointTextView.setText(item.getTemperatureSetting());
            } else {
                this.mZoneModeOrSetPointTextView.setText(item.getMode());
            }
        }
        if (!item.isPower()) {
            this.mZoneModeOrSetPointTextView.setText(context.getString(ResourceFactory.getSettingsDisplayOffBaconId(item.getZoneType())));
        }
        if (!item.isCoolingZone() && (item.getTemperatureSetting() == null || item.getTemperatureSetting().isEmpty())) {
            modeDrawable = null;
        }
        this.mZoneModeOrSetPointTextView.setCompoundDrawablesWithIntrinsicBounds(modeDrawable, null, null, null);
        this.mZoneNoConnection.setVisibility(mNoConnectionVisibility);
        this.mZoneTadoTemperatureState.setVisibility(mTemperatureLayoutVisibility);
    }
}
