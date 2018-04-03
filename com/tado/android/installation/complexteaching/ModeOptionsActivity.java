package com.tado.android.installation.complexteaching;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindView;
import com.tado.C0676R;
import com.tado.android.entities.ACModeRecording;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.times.view.model.ModeEnum;

public class ModeOptionsActivity extends ACInstallationBaseActivity {
    @BindView(2131296629)
    CheckBox fanCheckbox;
    @BindView(2131296997)
    CheckBox swingCheckbox;
    @BindView(2131297028)
    CheckBox tempCheckbox;
    @BindView(2131297033)
    public LinearLayout temperatureLayout;

    protected void onCreate(Bundle savedInstanceState) {
        ImageView swingImage;
        Drawable drawable;
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_mode_options);
        String mode = "";
        ComplexTeachingController state = ComplexTeachingController.getComplexTeachingController();
        if (state != null) {
            ACModeRecording acModeRecording = state.getCurrentAcModeRecording();
            if (acModeRecording != null) {
                mode = acModeRecording.getMode();
                this.titleBarTextview.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_title);
                this.titleTemplateTextview.setText(getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_modeCapabilities_title, new Object[]{mode}));
                if (mode.equalsIgnoreCase(ModeEnum.COOL.getMode()) || mode.equalsIgnoreCase(ModeEnum.HEAT.getMode())) {
                    this.temperatureLayout.setVisibility(0);
                }
                getResources().getDrawable(C0676R.drawable.control_panel_swing).clearColorFilter();
                swingImage = (ImageView) findViewById(C0676R.id.swing_image);
                drawable = swingImage.getDrawable();
                drawable.clearColorFilter();
                swingImage.setImageDrawable(drawable);
                this.proceedButton.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_modeCapabilities_confirmButton);
            }
        }
        InstallationProcessController.getInstallationProcessController().detectStatus(this);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_title);
        this.titleTemplateTextview.setText(getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_modeCapabilities_title, new Object[]{mode}));
        this.temperatureLayout.setVisibility(0);
        getResources().getDrawable(C0676R.drawable.control_panel_swing).clearColorFilter();
        swingImage = (ImageView) findViewById(C0676R.id.swing_image);
        drawable = swingImage.getDrawable();
        drawable.clearColorFilter();
        swingImage.setImageDrawable(drawable);
        this.proceedButton.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_modeCapabilities_confirmButton);
    }

    public void proceedClick(View view) {
        ComplexTeachingController state = ComplexTeachingController.getComplexTeachingController();
        boolean hasTemperatures = this.temperatureLayout.getVisibility() == 0 && this.tempCheckbox.isChecked();
        state.setCurrentHasTemperatures(hasTemperatures);
        state.setCurrentHasFanSpeeds(this.fanCheckbox.isChecked());
        state.setCurrentHasSwing(this.swingCheckbox.isChecked());
        state.goToNextCapabilitiesScreen(this);
    }
}
