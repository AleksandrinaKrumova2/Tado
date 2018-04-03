package com.tado.android.adapters;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.entities.TeachingRun;
import com.tado.android.entities.TeachingRunSummary;
import com.tado.android.entities.TemperatureRange;
import com.tado.android.installation.complexteaching.ComplexTeachingController;
import com.tado.android.times.view.model.CoolingFanSpeedEnum;
import com.tado.android.utils.Constants;
import java.util.ArrayList;

public class TeachingRunAdapter extends ArrayAdapter<TeachingRun> {
    private int highlightIndex;
    private ArrayList<TeachingRun> mTeachingRunList;

    public TeachingRunAdapter(Context context, int resource, int textViewResourceId, ArrayList<TeachingRun> teachingRunList, int highlightIndex) {
        super(context, resource, textViewResourceId, teachingRunList);
        this.mTeachingRunList = teachingRunList;
        this.highlightIndex = highlightIndex;
    }

    public int getCount() {
        return this.mTeachingRunList.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        TeachingRun teachingRun = (TeachingRun) this.mTeachingRunList.get(position);
        TeachingRunSummary summary = teachingRun.getSummary();
        View v = convertView;
        if (v == null) {
            v = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(C0676R.layout.teaching_run_item, null);
        }
        LinearLayout temperatureLayout = (LinearLayout) v.findViewById(C0676R.id.temperature_range_layout);
        ImageView temperatureRangeIconImageView = (ImageView) v.findViewById(C0676R.id.temperature_range_icon_imageview);
        TextView temperatureRangeTextView = (TextView) v.findViewById(C0676R.id.temperature_range_textview);
        LinearLayout fanSpeedLayout = (LinearLayout) v.findViewById(C0676R.id.fan_speed_layout);
        ImageView fanSpeedIconImageView = (ImageView) v.findViewById(C0676R.id.fan_speed_icon_imageview);
        ImageView fanSpeedImageView = (ImageView) v.findViewById(C0676R.id.fan_speed_imageview);
        TextView fanSpeedAutoTextView = (TextView) v.findViewById(C0676R.id.fan_speed_auto_textview);
        LinearLayout swingLayout = (LinearLayout) v.findViewById(C0676R.id.swing_layout);
        ImageView swingIconImageView = (ImageView) v.findViewById(C0676R.id.swing_icon_imageview);
        TextView swingTextView = (TextView) v.findViewById(C0676R.id.swing_textview);
        ImageView completedImageView = (ImageView) v.findViewById(C0676R.id.completed_imageview);
        boolean completed = teachingRun.getRecordingState().compareTo("FINISHED") == 0;
        int foregroundColor = completed ? ViewCompat.MEASURED_STATE_MASK : -7829368;
        if (position == this.highlightIndex) {
            v.setBackgroundColor(ContextCompat.getColor(v.getContext(), C0676R.color.cooling_installation_process_highlight_color));
            foregroundColor = -1;
        }
        temperatureRangeTextView.setTextColor(foregroundColor);
        swingTextView.setTextColor(foregroundColor);
        temperatureRangeIconImageView.setColorFilter(foregroundColor, Mode.SRC_ATOP);
        fanSpeedIconImageView.setColorFilter(foregroundColor, Mode.SRC_ATOP);
        fanSpeedImageView.setColorFilter(foregroundColor, Mode.SRC_ATOP);
        fanSpeedAutoTextView.setTextColor(foregroundColor);
        swingIconImageView.setColorFilter(foregroundColor, Mode.SRC_ATOP);
        TemperatureRange temperatureRange = summary.getTemperatures();
        if (temperatureRange == null) {
            temperatureLayout.setVisibility(8);
        } else {
            String tempUnitString = ComplexTeachingController.getComplexTeachingController().getTemperatureUnit();
            String tempUnitSymbol = "";
            if (tempUnitString.equalsIgnoreCase(Constants.CELSIUS)) {
                tempUnitSymbol = "C";
            } else if (tempUnitString.equalsIgnoreCase(Constants.FAHRENHEIT)) {
                tempUnitSymbol = "F";
            }
            temperatureRangeTextView.setText(temperatureRange.toString() + tempUnitSymbol);
        }
        if (summary.getFanSpeed() == null) {
            fanSpeedLayout.setVisibility(8);
        } else {
            String fanSpeed = teachingRun.getSummary().getFanSpeed();
            if (fanSpeed.compareTo(CoolingFanSpeedEnum.getStringValue(CoolingFanSpeedEnum.HIGH)) == 0) {
                fanSpeedImageView.setImageResource(C0676R.drawable.fan_speed_high);
            } else if (fanSpeed.compareTo(CoolingFanSpeedEnum.getStringValue(CoolingFanSpeedEnum.MEDIUM)) == 0) {
                fanSpeedImageView.setImageResource(C0676R.drawable.fan_speed_mid);
            } else if (fanSpeed.compareTo(CoolingFanSpeedEnum.getStringValue(CoolingFanSpeedEnum.LOW)) == 0) {
                fanSpeedImageView.setImageResource(C0676R.drawable.fan_speed_low);
            } else {
                fanSpeedImageView.setVisibility(8);
                fanSpeedAutoTextView.setVisibility(0);
            }
        }
        String swing = summary.getSwing();
        if (swing == null) {
            swingLayout.setVisibility(8);
        } else {
            swingTextView.setText(swing);
        }
        if (!completed) {
            completedImageView.setVisibility(4);
        }
        return v;
    }

    private int getColor(boolean completed) {
        if (completed) {
            return ViewCompat.MEASURED_STATE_MASK;
        }
        return -1;
    }
}
