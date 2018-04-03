package com.tado.android.installation.complexteaching;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.tado.C0676R;
import com.tado.android.entities.ACModeRecording;
import com.tado.android.entities.RemoteControl;
import com.tado.android.entities.TemperatureRange;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.utils.TextValidator;

public class ChooseMaxTempForModeActivity extends ACInstallationBaseActivity {
    public static final String BUTTON_EXTRA = "button";
    public static final String DESCRIPTION_EXTRA = "description";
    public static final String HINT_EXTRA = "hint";
    public static final String TITLE_EXTRA = "title";
    private String acTemperatureUnit;
    private EditText inputTemp;
    @BindView(2131296421)
    ImageView mCommandImage;
    private String mMode;

    protected void onCreate(Bundle savedInstanceState) {
        String title;
        TextView textView;
        String description;
        String button;
        Button button2;
        String hint;
        EditText editText;
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_choose_max_min_temp_mode);
        this.mMode = "";
        ComplexTeachingController state = ComplexTeachingController.getComplexTeachingController();
        if (state != null) {
            ACModeRecording acModeRecording = state.getCurrentAcModeRecording();
            if (acModeRecording != null) {
                this.mMode = acModeRecording.getMode();
                this.titleBarTextview.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_title);
                title = getIntent().getStringExtra("title");
                textView = this.titleTemplateTextview;
                if (title == null) {
                    title = getString(C0676R.string.installation_sacc_setupAC_clcRecording_maxTemperature_title);
                }
                textView.setText(title);
                description = getIntent().getStringExtra("description");
                if (description == null) {
                    this.textView.setText(description);
                } else {
                    this.textView.setVisibility(8);
                }
                this.mCommandImage.setImageResource(C0676R.drawable.arrow_up);
                button = getIntent().getStringExtra("button");
                button2 = this.proceedButton;
                if (button == null) {
                    button = getString(C0676R.string.installation_sacc_setupAC_clcRecording_maxTemperature_confirmButton);
                }
                button2.setText(button);
                this.proceedButton.setEnabled(false);
                this.inputTemp = (EditText) findViewById(C0676R.id.max_min_temp_input);
                hint = getIntent().getStringExtra("hint");
                editText = this.inputTemp;
                if (hint == null) {
                    hint = getString(C0676R.string.installation_sacc_setupAC_clcRecording_maxTemperature_highestTemperatureField);
                }
                editText.setHint(hint);
                this.acTemperatureUnit = ComplexTeachingController.getComplexTeachingController().getTemperatureUnit();
                this.inputTemp.addTextChangedListener(new TextValidator(this.inputTemp) {
                    public void validate(TextView textView, String text) {
                        try {
                            if (ChooseMaxTempForModeActivity.this.isValidTemperature(ChooseMaxTempForModeActivity.this.acTemperatureUnit, Integer.valueOf(text).intValue())) {
                                ChooseMaxTempForModeActivity.this.inputTemp.setError(null);
                                ChooseMaxTempForModeActivity.this.proceedButton.setEnabled(true);
                                return;
                            }
                            ChooseMaxTempForModeActivity.this.showError(ChooseMaxTempForModeActivity.this.acTemperatureUnit);
                            ChooseMaxTempForModeActivity.this.proceedButton.setEnabled(false);
                        } catch (NumberFormatException e) {
                            ChooseMaxTempForModeActivity.this.showError(ChooseMaxTempForModeActivity.this.acTemperatureUnit);
                            ChooseMaxTempForModeActivity.this.proceedButton.setEnabled(false);
                        }
                    }
                });
            }
        }
        InstallationProcessController.getInstallationProcessController().detectStatus(this);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_title);
        title = getIntent().getStringExtra("title");
        textView = this.titleTemplateTextview;
        if (title == null) {
            title = getString(C0676R.string.installation_sacc_setupAC_clcRecording_maxTemperature_title);
        }
        textView.setText(title);
        description = getIntent().getStringExtra("description");
        if (description == null) {
            this.textView.setVisibility(8);
        } else {
            this.textView.setText(description);
        }
        this.mCommandImage.setImageResource(C0676R.drawable.arrow_up);
        button = getIntent().getStringExtra("button");
        button2 = this.proceedButton;
        if (button == null) {
            button = getString(C0676R.string.installation_sacc_setupAC_clcRecording_maxTemperature_confirmButton);
        }
        button2.setText(button);
        this.proceedButton.setEnabled(false);
        this.inputTemp = (EditText) findViewById(C0676R.id.max_min_temp_input);
        hint = getIntent().getStringExtra("hint");
        editText = this.inputTemp;
        if (hint == null) {
            hint = getString(C0676R.string.installation_sacc_setupAC_clcRecording_maxTemperature_highestTemperatureField);
        }
        editText.setHint(hint);
        this.acTemperatureUnit = ComplexTeachingController.getComplexTeachingController().getTemperatureUnit();
        this.inputTemp.addTextChangedListener(/* anonymous class already generated */);
    }

    public void proceedClick(View view) {
        int maxTemp = Integer.valueOf(this.inputTemp.getText().toString()).intValue();
        boolean isClCRecording = InstallationProcessController.getInstallationProcessController().getInstallationInfo().isCLCRecording();
        if (isClCRecording) {
            ComplexTeachingController state = ComplexTeachingController.getComplexTeachingController();
            state.getCurrentCapabilities().setTemperatures(new TemperatureRange(maxTemp, maxTemp));
            state.goToNextCapabilitiesScreen(this, isClCRecording);
            return;
        }
        Intent intent = new Intent(this, ChooseMinTempForModeActivity.class);
        intent.putExtras(getIntent());
        intent.putExtra("title", getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_minTemperature_title, new Object[]{this.mMode}));
        intent.putExtra("description", getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_minTemperature_message, new Object[]{this.mMode}));
        intent.putExtra("button", getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_minTemperature_confirmButton));
        intent.putExtra("hint", getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_minTemperature_temperatureField));
        intent.putExtra(ChooseMinTempForModeActivity.MAX_TEMP_EXTRA, maxTemp);
        InstallationProcessController.startActivity((Activity) this, intent, false);
    }

    private boolean isValidTemperature(String acTemperatureUnit, int val) {
        if (acTemperatureUnit.compareTo(RemoteControl.UNIT_CELSIUS) == 0 && val >= 16 && val <= 31) {
            return true;
        }
        if (acTemperatureUnit.compareTo(RemoteControl.UNIT_FAHRENHEIT) != 0 || val < 60 || val > 89) {
            return false;
        }
        return true;
    }

    private void showError(String acTemperatureUnit) {
        if (acTemperatureUnit.compareTo(RemoteControl.UNIT_FAHRENHEIT) == 0) {
            this.inputTemp.setError(getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_maxTemperature_errors_invalidFahrenheitTempError));
        } else {
            this.inputTemp.setError(getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_maxTemperature_errors_invalidCelsiusTempError));
        }
    }
}
