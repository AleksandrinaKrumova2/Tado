package com.tado.android.settings.cooling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.rest.model.AcDriver;
import com.tado.android.rest.model.installation.ModeEnum;
import com.tado.android.rest.model.installation.TemperatureUnitEnum;
import java.util.List;

public class AcCommandSetArrayAdapter extends ArrayAdapter<AcDriver> {
    private List<AcDriver> items;
    private int selectedItem;

    private class AcCommandSetView {
        private TextView acCommandSetName;
        private TextView fifthMode;
        private TextView firstMode;
        private TextView fourthMode;
        private RadioButton radioButton;
        private TextView secondMode;
        private TextView temperatureUnit;
        private TextView thirdMode;

        private AcCommandSetView() {
        }

        public TextView getAcCommandSetName() {
            return this.acCommandSetName;
        }

        public void setAcCommandSetName(TextView acCommandSetName) {
            this.acCommandSetName = acCommandSetName;
        }

        public TextView getTemperatureUnit() {
            return this.temperatureUnit;
        }

        public void setTemperatureUnit(TextView temperatureUnit) {
            this.temperatureUnit = temperatureUnit;
        }

        public TextView getFirstMode() {
            return this.firstMode;
        }

        public void setFirstMode(TextView firstMode) {
            this.firstMode = firstMode;
        }

        public TextView getSecondMode() {
            return this.secondMode;
        }

        public void setSecondMode(TextView secondMode) {
            this.secondMode = secondMode;
        }

        public TextView getThirdMode() {
            return this.thirdMode;
        }

        public void setThirdMode(TextView thirdMode) {
            this.thirdMode = thirdMode;
        }

        public TextView getFourthMode() {
            return this.fourthMode;
        }

        public void setFourthMode(TextView fourthMode) {
            this.fourthMode = fourthMode;
        }

        public TextView getFifthMode() {
            return this.fifthMode;
        }

        public void setFifthMode(TextView fifthMode) {
            this.fifthMode = fifthMode;
        }

        public RadioButton getRadioButton() {
            return this.radioButton;
        }

        public void setRadioButton(RadioButton radioButton) {
            this.radioButton = radioButton;
        }
    }

    public AcCommandSetArrayAdapter(Context context, int resource, List<AcDriver> items, int selectedItem) {
        super(context, resource);
        this.items = items;
        this.selectedItem = selectedItem;
    }

    public int getCount() {
        return this.items.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        AcCommandSetView acCommandSetView;
        boolean z;
        AcDriver item = (AcDriver) this.items.get(position);
        if (convertView == null || !(convertView == null || convertView.getTag() == null)) {
            convertView = LayoutInflater.from(getContext()).inflate(C0676R.layout.ac_command_set_list_item, null);
            acCommandSetView = new AcCommandSetView();
            acCommandSetView.setAcCommandSetName((TextView) convertView.findViewById(C0676R.id.ac_command_set_list_name));
            acCommandSetView.setTemperatureUnit((TextView) convertView.findViewById(C0676R.id.ac_command_set_list_temperature_unit));
            acCommandSetView.setFirstMode((TextView) convertView.findViewById(C0676R.id.ac_command_set_list_first_mode));
            acCommandSetView.setSecondMode((TextView) convertView.findViewById(C0676R.id.ac_command_set_list_second_mode));
            acCommandSetView.setThirdMode((TextView) convertView.findViewById(C0676R.id.ac_command_set_list_third_mode));
            acCommandSetView.setFourthMode((TextView) convertView.findViewById(C0676R.id.ac_command_set_list_fourth_mode));
            acCommandSetView.setFifthMode((TextView) convertView.findViewById(C0676R.id.ac_command_set_list_fifth_mode));
            acCommandSetView.setRadioButton((RadioButton) convertView.findViewById(C0676R.id.ac_command_set_list_radio_button));
            convertView.setTag(acCommandSetView);
        } else {
            acCommandSetView = (AcCommandSetView) convertView.getTag();
        }
        String temperatureUnit = "C";
        if (item.getCommandSet().getTemperatureUnit() == TemperatureUnitEnum.FAHRENHEIT) {
            temperatureUnit = "F";
        }
        acCommandSetView.getTemperatureUnit().setText(temperatureUnit + getContext().getString(C0676R.string.degree_symbol));
        acCommandSetView.getAcCommandSetName().setText(item.getCommandSet().getName());
        RadioButton radioButton = acCommandSetView.getRadioButton();
        if (position == this.selectedItem) {
            z = true;
        } else {
            z = false;
        }
        radioButton.setChecked(z);
        if (item.getModes() != null) {
            for (ModeEnum mode : item.getModes()) {
                if (mode == ModeEnum.COOL) {
                    acCommandSetView.getFirstMode().setVisibility(0);
                } else if (mode == ModeEnum.FAN) {
                    acCommandSetView.getSecondMode().setVisibility(0);
                } else if (mode == ModeEnum.HEAT) {
                    acCommandSetView.getThirdMode().setVisibility(0);
                } else if (mode == ModeEnum.AUTO) {
                    acCommandSetView.getFourthMode().setVisibility(0);
                } else if (mode == ModeEnum.DRY) {
                    acCommandSetView.getFifthMode().setVisibility(0);
                }
            }
        }
        return convertView;
    }

    public AcDriver getItem(int position) {
        return (AcDriver) this.items.get(position);
    }

    public void setSelected(int selected) {
        this.selectedItem = selected;
        notifyDataSetChanged();
    }
}
