package com.tado.android.installation.acsetup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.rest.model.installation.TemperatureUnitEnum;
import com.tado.android.utils.UserConfig;

public class ChooseTemperatureUnitActivity extends ACInstallationBaseActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_choose_temperature_unit);
        this.titleBarTextview.setText(getString(C0676R.string.installation_sacc_acSpecs_title));
        this.titleTemplateTextview.setText(getString(C0676R.string.installation_sacc_acSpecs_temperatureUnit_title));
    }

    public void chooseCelsius(View view) {
        proceed(TemperatureUnitEnum.CELSIUS);
    }

    public void chooseFahrenheit(View view) {
        proceed(TemperatureUnitEnum.FAHRENHEIT);
    }

    private void proceed(TemperatureUnitEnum unit) {
        InstallationProcessController.getInstallationProcessController().getAcSpecs().getRemoteControl().setTemperatureUnit(unit);
        UserConfig.setTemperatureUnit(unit);
        InstallationProcessController.startActivity((Activity) this, SelectACTypeActivity.class, false);
    }
}
