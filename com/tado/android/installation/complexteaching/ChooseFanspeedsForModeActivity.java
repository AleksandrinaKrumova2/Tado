package com.tado.android.installation.complexteaching;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.BindView;
import com.tado.C0676R;
import com.tado.android.dialogs.AlertChoiceDialogListener;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.entities.ACModeRecordingCapabilities;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.times.view.model.CoolingFanSpeedEnum;
import java.util.LinkedList;
import java.util.List;

public class ChooseFanspeedsForModeActivity extends ACInstallationBaseActivity implements OnCheckedChangeListener {
    private List<String> fanSpeeds;
    private boolean hasAutoFanSpeed = false;
    @BindView(2131296889)
    RadioButton mRadioButton1;
    @BindView(2131296890)
    RadioButton mRadioButton2;
    @BindView(2131296891)
    RadioButton mRadioButton3;
    @BindView(2131296892)
    RadioButton mRadioButtonAuto;
    @BindView(2131296636)
    RadioGroup mRadioButtonGroup;
    @BindView(2131296893)
    RadioButton mRadioButtonMore;

    class C08941 implements AlertChoiceDialogListener {
        C08941() {
        }

        public void OnOKClicked() {
            ChooseFanspeedsForModeActivity.this.proceed(C0676R.id.radioButtonMore);
        }

        public void OnCancelClicked() {
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_mode_fanspeeds);
        this.titleBarTextview.setText(getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_title));
        initViews();
    }

    private void initViews() {
        ACModeRecordingCapabilities acModeRecordingCapabilities = ComplexTeachingController.getComplexTeachingController().getCurrentCapabilities();
        this.fanSpeeds = new LinkedList();
        for (String fanSpeed : acModeRecordingCapabilities.getFanSpeeds()) {
            this.fanSpeeds.add(fanSpeed);
        }
        if (this.fanSpeeds.contains(CoolingFanSpeedEnum.getStringValue(CoolingFanSpeedEnum.AUTO))) {
            this.hasAutoFanSpeed = true;
        }
        if (this.hasAutoFanSpeed) {
            this.titleTemplateTextview.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_fanspeedCount_fanSpeedsWithAutoLabel);
            this.mRadioButtonAuto.setVisibility(0);
        } else {
            this.titleTemplateTextview.setText(getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_fanspeedCount_fanSpeedsWithoutAutoLabel));
        }
        this.mRadioButtonAuto.setOnCheckedChangeListener(this);
        this.mRadioButton1.setOnCheckedChangeListener(this);
        this.mRadioButton2.setOnCheckedChangeListener(this);
        this.mRadioButton3.setOnCheckedChangeListener(this);
        this.mRadioButtonMore.setOnCheckedChangeListener(this);
        this.proceedButton.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_fanspeedCount_confirmButton);
        this.proceedButton.setEnabled(false);
    }

    public void proceedClick(View view) {
        int radioButtonID = this.mRadioButtonGroup.getCheckedRadioButtonId();
        if (radioButtonID == C0676R.id.radioButtonMore) {
            showAlert();
        } else {
            proceed(radioButtonID);
        }
    }

    private void proceed(int radioButtonID) {
        ComplexTeachingController state = ComplexTeachingController.getComplexTeachingController();
        switch (radioButtonID) {
            case C0676R.id.radioButton1:
                this.fanSpeeds.add(CoolingFanSpeedEnum.getStringValue(CoolingFanSpeedEnum.MEDIUM));
                break;
            case C0676R.id.radioButton2:
                this.fanSpeeds.add(CoolingFanSpeedEnum.getStringValue(CoolingFanSpeedEnum.LOW));
                this.fanSpeeds.add(CoolingFanSpeedEnum.getStringValue(CoolingFanSpeedEnum.HIGH));
                break;
            case C0676R.id.radioButton3:
            case C0676R.id.radioButtonMore:
                this.fanSpeeds.add(CoolingFanSpeedEnum.getStringValue(CoolingFanSpeedEnum.LOW));
                this.fanSpeeds.add(CoolingFanSpeedEnum.getStringValue(CoolingFanSpeedEnum.MEDIUM));
                this.fanSpeeds.add(CoolingFanSpeedEnum.getStringValue(CoolingFanSpeedEnum.HIGH));
                break;
        }
        state.getCurrentCapabilities().setFanSpeeds((String[]) this.fanSpeeds.toArray(new String[this.fanSpeeds.size()]));
        state.goToNextCapabilitiesScreen(this);
    }

    private void showAlert() {
        AlertDialogs.showChoiceAlert(getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_fanspeedCount_moreFanspeeds_title), getText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_fanspeedCount_moreFanspeeds_message), getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_fanspeedCount_moreFanspeeds_confirmButton), getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_fanspeedCount_moreFanspeeds_cancelButton), this, new C08941());
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            this.proceedButton.setEnabled(true);
        }
    }
}
