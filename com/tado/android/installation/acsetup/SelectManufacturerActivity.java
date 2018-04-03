package com.tado.android.installation.acsetup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.installation.AcInstallation;
import com.tado.android.rest.model.installation.InstallationStateTransitionResult;
import com.tado.android.rest.model.installation.Manufacturer;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.UserConfig;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class SelectManufacturerActivity extends ACInstallationBaseActivity {
    private static final int REQUEST_CODE = 1;
    @BindView(2131296377)
    TextView brandhSelectionError;
    @BindView(2131296405)
    ImageView center_image;
    private Manufacturer manufacturer;
    private List<Manufacturer> manufacturerList;
    @BindView(2131296964)
    Button selectManufacturer;
    @BindView(2131297165)
    TextView troubleshooting;

    class C08401 extends TadoCallback<List<Manufacturer>> {
        C08401() {
        }

        public void onResponse(Call<List<Manufacturer>> call, Response<List<Manufacturer>> response) {
            super.onResponse(call, response);
            SelectManufacturerActivity.this.dismissLoadingUI();
            if (response.isSuccessful()) {
                SelectManufacturerActivity.this.manufacturerList = (List) response.body();
                return;
            }
            SelectManufacturerActivity.this.handleServerError(this.serverError, SelectManufacturerActivity.this, call, response.code(), this);
        }

        public void onFailure(Call<List<Manufacturer>> call, Throwable t) {
            super.onFailure(call, t);
            SelectManufacturerActivity.this.dismissLoadingUI();
            InstallationProcessController.showConnectionErrorRetrofit(SelectManufacturerActivity.this, call, this);
        }
    }

    class C08412 extends TadoCallback<InstallationStateTransitionResult> {
        C08412() {
        }

        public void onResponse(Call<InstallationStateTransitionResult> call, Response<InstallationStateTransitionResult> response) {
            super.onResponse(call, response);
            SelectManufacturerActivity.this.dismissLoadingUI();
            if (response.isSuccessful()) {
                AcInstallation installation = ((InstallationStateTransitionResult) response.body()).getInstallation();
                if (installation != null) {
                    InstallationProcessController.getInstallationProcessController().goToScreenForInstallationProcessStatus(SelectManufacturerActivity.this, installation);
                    return;
                } else {
                    InstallationProcessController.getInstallationProcessController().detectStatus(SelectManufacturerActivity.this);
                    return;
                }
            }
            SelectManufacturerActivity.this.handleServerError(this.serverError, SelectManufacturerActivity.this, call, response.code(), this);
        }

        public void onFailure(Call<InstallationStateTransitionResult> call, Throwable t) {
            super.onFailure(call, t);
            SelectManufacturerActivity.this.dismissLoadingUI();
            InstallationProcessController.showConnectionErrorRetrofit(SelectManufacturerActivity.this, call, this);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.fragment_select_manufacturer);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_acSpecs_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_acSpecs_brandSelection_title);
        this.proceedButton.setText(C0676R.string.installation_sacc_acSpecs_brandSelection_confirmButton);
        this.proceedButton.setEnabled(false);
        this.troubleshooting.setText(C0676R.string.installation_sacc_acSpecs_brandSelection_customBrandButton);
        this.center_image.setImageResource(C0676R.drawable.ac_brand);
        getManufacturers();
    }

    @OnClick({2131296964})
    public void selectManufacturers() {
        if (this.manufacturerList == null || this.manufacturerList.isEmpty()) {
            getManufacturers();
            return;
        }
        Intent intent = new Intent(this, ListManufacturerActivity.class);
        intent.putExtra("manufacturersList", new ArrayList(this.manufacturerList));
        startActivityForResult(intent, 1);
    }

    public void getManufacturers() {
        showLoadingUI();
        RestServiceGenerator.getTadoInstallationRestService().listManufacturers().enqueue(new C08401());
    }

    public void proceedClick(View view) {
        if (this.manufacturer == null) {
            this.brandhSelectionError.setVisibility(0);
            return;
        }
        InstallationProcessController.getInstallationProcessController().getAcSpecs().setManufacturer(this.manufacturer);
        UserConfig.setAcManufacturerName(this.manufacturer.getName());
        postACSpecs();
    }

    private void postACSpecs() {
        showLoadingUI();
        int installationId = InstallationProcessController.getInstallationProcessController().getInstallationId();
        RestServiceGenerator.getTadoInstallationRestService().specifyAc(UserConfig.getHomeId(), Integer.valueOf(installationId), InstallationProcessController.getInstallationProcessController().getAcSpecs()).enqueue(new C08412());
    }

    public void troubleshoot(View view) {
        InstallationProcessController.startActivity((Activity) this, TypeInManufacturerActivity.class, false);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == -1) {
            int manufacturerId = data.getExtras().getInt("manufacturerId");
            this.manufacturer = new Manufacturer(Integer.valueOf(manufacturerId), data.getExtras().getString("manufacturerName"));
            this.selectManufacturer.setText(this.manufacturer.getName());
            dismissLoadingUI();
        }
    }
}
