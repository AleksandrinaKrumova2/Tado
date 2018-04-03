package com.tado.android.installation.acsetup;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.installation.AcInstallation;
import com.tado.android.rest.model.installation.AcSpecs;
import com.tado.android.rest.model.installation.InstallationStateTransitionResult;
import com.tado.android.rest.model.installation.Manufacturer;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.TextValidator;
import com.tado.android.utils.UserConfig;
import retrofit2.Call;
import retrofit2.Response;

public class TypeInManufacturerActivity extends ACInstallationBaseActivity {
    @BindView(2131296405)
    ImageView centerImage;
    @BindView(2131296693)
    EditText manufacturerInput;
    TadoCallback<InstallationStateTransitionResult> specsListener = new C08732();

    class C08732 extends TadoCallback<InstallationStateTransitionResult> {
        C08732() {
        }

        public void onResponse(Call<InstallationStateTransitionResult> call, Response<InstallationStateTransitionResult> response) {
            super.onResponse(call, response);
            TypeInManufacturerActivity.this.proceedButton.setEnabled(true);
            if (response.isSuccessful()) {
                AcInstallation installation = ((InstallationStateTransitionResult) response.body()).getInstallation();
                if (installation != null) {
                    InstallationProcessController.getInstallationProcessController().goToScreenForInstallationProcessStatus(TypeInManufacturerActivity.this, installation);
                    return;
                } else {
                    InstallationProcessController.getInstallationProcessController().detectStatus(TypeInManufacturerActivity.this);
                    return;
                }
            }
            TypeInManufacturerActivity.this.handleServerError(this.serverError, TypeInManufacturerActivity.this, call, response.code(), TypeInManufacturerActivity.this.specsListener);
        }

        public void onFailure(Call<InstallationStateTransitionResult> call, Throwable t) {
            super.onFailure(call, t);
            TypeInManufacturerActivity.this.proceedButton.setEnabled(true);
            InstallationProcessController.showConnectionErrorRetrofit(TypeInManufacturerActivity.this, call, this);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_type_in_manufacturer);
        this.titleBarTextview.setText(getString(C0676R.string.installation_sacc_acSpecs_title));
        this.titleTemplateTextview.setText(getString(C0676R.string.installation_sacc_acSpecs_customBrand_title));
        this.proceedButton.setText(getString(C0676R.string.installation_sacc_acSpecs_customBrand_confirmButton));
        this.proceedButton.setEnabled(false);
        this.centerImage.setImageResource(C0676R.drawable.ac_brand);
        this.manufacturerInput = (EditText) findViewById(C0676R.id.input_manufacturer);
        this.manufacturerInput.addTextChangedListener(new TextValidator(this.manufacturerInput) {
            public void validate(TextView textView, String text) {
                if (text.isEmpty()) {
                    TypeInManufacturerActivity.this.manufacturerInput.setError(TypeInManufacturerActivity.this.getString(C0676R.string.installation_sacc_acSpecs_customBrand_errors_brandFieldEmptyError));
                    TypeInManufacturerActivity.this.proceedButton.setEnabled(false);
                    return;
                }
                TypeInManufacturerActivity.this.manufacturerInput.setError(null);
                TypeInManufacturerActivity.this.proceedButton.setEnabled(true);
            }
        });
    }

    public void proceedClick(View view) {
        String manufacturerString = this.manufacturerInput.getText().toString();
        if (manufacturerString.isEmpty()) {
            this.manufacturerInput.setError(getString(C0676R.string.installation_sacc_acSpecs_customBrand_errors_brandFieldEmptyError));
            return;
        }
        this.proceedButton.setEnabled(false);
        postACSpecsWithManufacturerNameOnly(manufacturerString);
    }

    private void postACSpecsWithManufacturerNameOnly(String manufacturerString) {
        Manufacturer manufacturer = new Manufacturer(manufacturerString);
        AcSpecs acSpecs = InstallationProcessController.getInstallationProcessController().getAcSpecs();
        acSpecs.setManufacturer(manufacturer);
        RestServiceGenerator.getTadoInstallationRestService().specifyAc(UserConfig.getHomeId(), Integer.valueOf(InstallationProcessController.getInstallationProcessController().getInstallationId()), acSpecs).enqueue(this.specsListener);
    }
}
