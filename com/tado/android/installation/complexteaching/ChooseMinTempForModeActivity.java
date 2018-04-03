package com.tado.android.installation.complexteaching;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.tado.C0676R;
import com.tado.android.entities.ACModeRecordingCapabilities;
import com.tado.android.entities.RemoteControl;
import com.tado.android.entities.TemperatureRange;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.utils.TextValidator;

public class ChooseMinTempForModeActivity extends ACInstallationBaseActivity {
    public static final String BUTTON_EXTRA = "button";
    public static final String DESCRIPTION_EXTRA = "description";
    public static final String HINT_EXTRA = "hint";
    public static final String MAX_TEMP_EXTRA = "tempMax";
    public static final String TITLE_EXTRA = "title";
    private String acTemperatureUnit;
    @BindView(2131296421)
    ImageView mCommandImage;
    @BindView(2131296731)
    EditText mInputTemp;
    private int maxTemp;
    private int minTemp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_choose_max_min_temp_mode);
        initViews();
    }

    private void initViews() {
        String mode = ComplexTeachingController.getComplexTeachingController().getCurrentAcModeRecording().getMode();
        this.titleBarTextview.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_title);
        String title = getIntent().getStringExtra("title");
        TextView textView = this.titleTemplateTextview;
        if (title == null) {
            title = getString(C0676R.string.installation_sacc_setupAC_clcRecording_minTemperature_title);
        }
        textView.setText(title);
        String description = getIntent().getStringExtra("description");
        if (description != null) {
            this.textView.setText(description);
        } else {
            this.textView.setVisibility(8);
        }
        this.mCommandImage.setImageResource(C0676R.drawable.arrow_down);
        String button = getIntent().getStringExtra("button");
        Button button2 = this.proceedButton;
        if (button == null) {
            button = getString(C0676R.string.installation_sacc_setupAC_clcRecording_minTemperature_confirmButton);
        }
        button2.setText(button);
        this.proceedButton.setEnabled(false);
        String hint = getIntent().getStringExtra("hint");
        EditText editText = this.mInputTemp;
        if (hint == null) {
            hint = getString(C0676R.string.installation_sacc_setupAC_clcRecording_minTemperature_temperatureField);
        }
        editText.setHint(hint);
        this.acTemperatureUnit = ComplexTeachingController.getComplexTeachingController().getTemperatureUnit();
        this.maxTemp = getIntent().getExtras().getInt(MAX_TEMP_EXTRA);
        this.mInputTemp.addTextChangedListener(getInputTempWatcher());
    }

    @NonNull
    private TextValidator getInputTempWatcher() {
        return new TextValidator(this.mInputTemp) {
            public void validate(TextView textView, String text) {
                try {
                    int val = Integer.valueOf(text).intValue();
                    if (ChooseMinTempForModeActivity.this.isValidTemperature(ChooseMinTempForModeActivity.this.acTemperatureUnit, val)) {
                        ChooseMinTempForModeActivity.this.mInputTemp.setError(null);
                        ChooseMinTempForModeActivity.this.proceedButton.setEnabled(true);
                        ChooseMinTempForModeActivity.this.minTemp = val;
                        return;
                    }
                    ChooseMinTempForModeActivity.this.showError(ChooseMinTempForModeActivity.this.acTemperatureUnit);
                    ChooseMinTempForModeActivity.this.proceedButton.setEnabled(false);
                } catch (NumberFormatException e) {
                    ChooseMinTempForModeActivity.this.showError(ChooseMinTempForModeActivity.this.acTemperatureUnit);
                    ChooseMinTempForModeActivity.this.proceedButton.setEnabled(false);
                }
            }
        };
    }

    public void proceedClick(View view) {
        ComplexTeachingController state = ComplexTeachingController.getComplexTeachingController();
        ACModeRecordingCapabilities acModeRecordingCapabilities = state.getCurrentCapabilities();
        boolean isCLCRecording = InstallationProcessController.getInstallationProcessController().getInstallationInfo().isCLCRecording();
        acModeRecordingCapabilities.setTemperatures(new TemperatureRange(this.minTemp, isCLCRecording ? this.minTemp : this.maxTemp));
        state.goToNextCapabilitiesScreen(this, isCLCRecording);
    }

    private boolean isValidTemperature(String acTemperatureUnit, int val) {
        if (acTemperatureUnit.compareTo(RemoteControl.UNIT_CELSIUS) == 0 && val >= 15 && val <= this.maxTemp - 1) {
            return true;
        }
        if (acTemperatureUnit.compareTo(RemoteControl.UNIT_FAHRENHEIT) != 0 || val < 59 || val > this.maxTemp - 1) {
            return false;
        }
        return true;
    }

    private void showError(String acTemperatureUnit) {
        if (acTemperatureUnit.compareTo(RemoteControl.UNIT_FAHRENHEIT) == 0) {
            this.mInputTemp.setError(getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_minTemperature_errors_invalidFahrenheitTempError, new Object[]{Integer.valueOf(this.maxTemp - 1)}));
            return;
        }
        this.mInputTemp.setError(getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_minTemperature_errors_invalidCelsiusTempError, new Object[]{Integer.valueOf(this.maxTemp - 1)}));
    }
}
