package com.tado.android.installation.positioning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.installation.HardwareDevice;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.UserConfig;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class DevicePositionActivity extends ACInstallationBaseActivity {

    class C09471 extends TadoCallback<List<HardwareDevice>> {
        C09471() {
        }

        public void onResponse(Call<List<HardwareDevice>> call, Response<List<HardwareDevice>> response) {
            super.onResponse(call, response);
            DevicePositionActivity.this.dismissLoadingUI();
            if (!response.isSuccessful() || ((List) response.body()).isEmpty()) {
                DevicePositionActivity.this.handleServerError(this.serverError, DevicePositionActivity.this, call, response.code(), this);
                return;
            }
            HardwareDevice device = (HardwareDevice) ((List) response.body()).get(0);
            Intent intent = new Intent(DevicePositionActivity.this, DevicePositionConnectionActivity.class);
            intent.putExtra("serialNo", device.getShortSerialNo());
            InstallationProcessController.startActivity(DevicePositionActivity.this, intent, false);
        }

        public void onFailure(Call<List<HardwareDevice>> call, Throwable t) {
            super.onFailure(call, t);
            DevicePositionActivity.this.dismissLoadingUI();
            InstallationProcessController.showConnectionErrorRetrofit(DevicePositionActivity.this, call, this);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_device_position);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_positionDevice_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_positionDevice_positionForRoomTemperature_title);
        this.textView.setText(C0676R.string.installation_sacc_positionDevice_positionForRoomTemperature_message);
        this.centerImage.setImageResource(C0676R.drawable.ic_positioning);
        this.proceedButton.setText(C0676R.string.installation_sacc_positionDevice_positionForRoomTemperature_confirmButton);
    }

    public void proceedClick(View view) {
        retrieveSerialNoAndProceed();
    }

    private void retrieveSerialNoAndProceed() {
        showLoadingUI();
        RestServiceGenerator.getTadoInstallationRestService().listDevicesToInstall(UserConfig.getHomeId(), Integer.valueOf(InstallationProcessController.getInstallationProcessController().getInstallationId())).enqueue(new C09471());
    }
}
