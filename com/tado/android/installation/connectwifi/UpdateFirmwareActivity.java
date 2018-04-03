package com.tado.android.installation.connectwifi;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.installation.AcInstallation;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.Constants;
import com.tado.android.utils.UserConfig;
import java.util.concurrent.atomic.AtomicBoolean;
import retrofit2.Call;
import retrofit2.Response;

public class UpdateFirmwareActivity extends ACInstallationBaseActivity {
    private Handler handler = null;
    private AtomicBoolean pollRequestOpen = new AtomicBoolean(false);
    @BindView(2131297165)
    TextView troubleshooting;

    class C09391 implements Runnable {
        C09391() {
        }

        public void run() {
            UpdateFirmwareActivity.this.troubleshooting.setVisibility(0);
        }
    }

    class C09402 implements Runnable {
        C09402() {
        }

        public void run() {
            UpdateFirmwareActivity.this.pollStatus();
            UpdateFirmwareActivity.this.handler.postDelayed(this, 3000);
        }
    }

    class C09413 extends TadoCallback<AcInstallation> {
        C09413() {
        }

        public void onResponse(Call<AcInstallation> call, Response<AcInstallation> response) {
            super.onResponse(call, response);
            UpdateFirmwareActivity.this.pollRequestOpen.set(false);
            UpdateFirmwareActivity.this.dismissLoadingUI();
            if (response.isSuccessful() && ((AcInstallation) response.body()).getAcInstallationInformation() != null && ((AcInstallation) response.body()).getAcInstallationInformation().getWirelessRemoteHasRequiredFirmware().booleanValue()) {
                InstallationProcessController.startActivity(UpdateFirmwareActivity.this, UpdateFirmwareSuccessfulActivity.class, true);
            }
        }

        public void onFailure(Call<AcInstallation> call, Throwable t) {
            super.onFailure(call, t);
            UpdateFirmwareActivity.this.dismissLoadingUI();
            UpdateFirmwareActivity.this.pollRequestOpen.set(false);
            InstallationProcessController.showConnectionErrorRetrofit(UpdateFirmwareActivity.this, call, this);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.fragment_update_firmware);
        this.titleBarTextview.setText(getString(C0676R.string.installation_sacc_firmwareUpdate_title));
        this.titleTemplateTextview.setText(getString(C0676R.string.installation_sacc_firmwareUpdate_message));
        this.textView.setText(getString(C0676R.string.installation_sacc_firmwareUpdate_description));
        this.centerImageOverlay.setImageResource(C0676R.drawable.device_download);
        this.centerImageOverlay.setVisibility(0);
    }

    protected void onResume() {
        super.onResume();
        this.handler = new Handler();
        this.handler.postDelayed(new C09391(), Constants.WAIT_FOR_FIRMWARE_UPDATE_TIMEOUT);
        this.handler.postDelayed(new C09402(), 3000);
    }

    private boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void pollStatus() {
        boolean networkAvailable = isNetworkAvailable();
        showLoadingUI();
        if (networkAvailable && this.pollRequestOpen.compareAndSet(false, true)) {
            RestServiceGenerator.getTadoInstallationRestService().showInstallation(UserConfig.getHomeId(), Integer.valueOf(InstallationProcessController.getInstallationProcessController().getInstallationId())).enqueue(new C09413());
        }
    }

    public void troubleshoot(View view) {
        Intent i = new Intent("android.intent.action.VIEW");
        i.setData(Uri.parse(getString(C0676R.string.installation_sacc_firmwareUpdate_troubleshootingURL)));
        startActivity(i);
    }

    protected void onPause() {
        super.onPause();
        if (this.handler != null) {
            this.handler.removeCallbacksAndMessages(null);
        }
    }
}
