package com.tado.android.installation.connectwifi;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallStatusPollingController;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.installation.HardwareDevice;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.Constants;
import com.tado.android.utils.UserConfig;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import retrofit2.Call;
import retrofit2.Response;

public class WaitForServerConnectionActivity extends ACInstallationBaseActivity {
    private boolean connectionTimeoutPossible = false;
    @BindView(2131297078)
    TextView errorTextview;
    Handler handler = null;
    private boolean noInternetTimeoutPossible = false;
    private AtomicBoolean pollRequestOpen = new AtomicBoolean(false);

    class C09431 implements Runnable {
        C09431() {
        }

        public void run() {
            WaitForServerConnectionActivity.this.noInternetTimeoutPossible = true;
        }
    }

    class C09442 implements Runnable {
        C09442() {
        }

        public void run() {
            WaitForServerConnectionActivity.this.connectionTimeoutPossible = true;
        }
    }

    class C09453 implements Runnable {
        C09453() {
        }

        public void run() {
            WaitForServerConnectionActivity.this.pollStatus();
            WaitForServerConnectionActivity.this.handler.postDelayed(this, Constants.CHECK_FOR_SERVER_CONNECTION_TIMEOUT);
        }
    }

    class C09464 extends TadoCallback<List<HardwareDevice>> {
        C09464() {
        }

        public void onResponse(Call<List<HardwareDevice>> call, Response<List<HardwareDevice>> response) {
            super.onResponse(call, response);
            if (!response.isSuccessful() || response.body() == null || ((List) response.body()).isEmpty()) {
                WaitForServerConnectionActivity.this.pollRequestOpen.set(false);
                return;
            }
            HardwareDevice device = (HardwareDevice) ((List) response.body()).get(0);
            WaitForServerConnectionActivity.this.pollRequestOpen.set(false);
            if (device != null && device.getConnectionState() != null && device.getConnectionState().isConnected().booleanValue()) {
                InstallationProcessController.startActivity(WaitForServerConnectionActivity.this, DeviceConnectionSuccessfulActivity.class, true);
            }
        }

        public void onFailure(Call<List<HardwareDevice>> call, Throwable t) {
            WaitForServerConnectionActivity.this.pollRequestOpen.set(false);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.fragment_wait_for_server_connection);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_connectWifi_serverConnection_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_connectWifi_serverConnection_connecting_connectingLabel);
        this.errorTextview.setText(C0676R.string.installation_sacc_connectWifi_serverConnection_connecting_checkConnectionLabel);
        this.textView.setText(C0676R.string.installation_sacc_connectWifi_serverConnection_connecting_message);
        this.centerImageOverlay.setImageResource(C0676R.drawable.connect_to_wifi_animation);
        this.centerImageOverlay.setVisibility(0);
        unbindFromWiFi();
    }

    public void unbindFromWiFi() {
        if (VERSION.SDK_INT >= 21) {
            ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
            if (VERSION.SDK_INT >= 23) {
                cm.bindProcessToNetwork(null);
            } else {
                ConnectivityManager.setProcessDefaultNetwork(null);
            }
        }
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        ((AnimationDrawable) this.centerImageOverlay.getDrawable()).start();
    }

    protected void onResume() {
        super.onResume();
        this.handler = new Handler();
        this.handler.postDelayed(new C09431(), 20000);
        this.handler.postDelayed(new C09442(), 30000);
        this.handler.postDelayed(new C09453(), Constants.CHECK_FOR_SERVER_CONNECTION_TIMEOUT);
    }

    public void pollStatus() {
        boolean networkAvailable = isNetworkAvailable();
        if (this.noInternetTimeoutPossible) {
            showInternetError(networkAvailable);
        }
        if (this.connectionTimeoutPossible && networkAvailable) {
            showTroubleShooting();
        }
        if (InstallStatusPollingController.getInstallationProcessController().isResetWifiCredentials()) {
            InstallStatusPollingController.getInstallationProcessController().pollInstallationStatus(this);
        } else if (this.pollRequestOpen.compareAndSet(false, true)) {
            RestServiceGenerator.getTadoInstallationRestService().listDevicesToInstall(UserConfig.getHomeId(), Integer.valueOf(InstallationProcessController.getInstallationProcessController().getInstallationId())).enqueue(new C09464());
        }
    }

    private void showInternetError(boolean networkAvailable) {
        if (networkAvailable) {
            this.errorTextview.setVisibility(8);
        } else {
            this.errorTextview.setVisibility(0);
        }
    }

    private void showTroubleShooting() {
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_connectWifi_serverConnection_connecting_longConnectionTimeout_title);
        this.textView.setText(C0676R.string.installation_sacc_connectWifi_serverConnection_connecting_longConnectionTimeout_message);
        findViewById(C0676R.id.troubleshooting).setVisibility(0);
    }

    public void troubleshoot(View view) {
        InstallationProcessController.startActivity((Activity) this, TroubleshootDeviceConnectionActivity.class, false);
    }

    protected void onPause() {
        super.onPause();
        if (this.handler != null) {
            this.handler.removeCallbacksAndMessages(null);
        }
    }

    private boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
