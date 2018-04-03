package com.tado.android.installation;

import android.app.Activity;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import com.tado.android.app.TadoApplication;
import com.tado.android.controllers.NavigationController;
import com.tado.android.installation.connectwifi.DeviceConnectionSuccessfulActivity;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.AirConditioningControl;
import com.tado.android.rest.model.installation.ConnectionState;
import com.tado.android.rest.model.installation.HardwareDevice;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.Snitcher.Builder;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import retrofit2.Call;
import retrofit2.Response;

public class InstallStatusPollingController {
    private static final String LOG_TAG = InstallStatusPollingController.class.getSimpleName();
    private static InstallStatusPollingController mInstallStatusPollingController;
    private Handler handler = null;
    private Date lastConnectionTimestamp = null;
    private Activity mCallingActivity;
    private AtomicBoolean pollRequestOpen = new AtomicBoolean(false);
    private boolean resetWifiCredentials = false;
    private int zoneId = -1;

    class C08091 implements Runnable {
        C08091() {
        }

        public void run() {
            InstallStatusPollingController.this.pollStatus();
            InstallStatusPollingController.this.handler.postDelayed(this, 20000);
        }
    }

    class C08102 extends TadoCallback<AirConditioningControl> {
        C08102() {
        }

        public void onResponse(Call<AirConditioningControl> call, Response<AirConditioningControl> response) {
            super.onResponse(call, response);
            if (response.isSuccessful()) {
                AirConditioningControl control = (AirConditioningControl) response.body();
                InstallStatusPollingController.this.stopPolling();
                if (control.getWirelessRemote().getConnectionState() == null || !control.getWirelessRemote().getConnectionState().isConnected().booleanValue() || InstallStatusPollingController.this.lastConnectionTimestamp == null || !control.getWirelessRemote().getConnectionState().getTimestamp().after(InstallStatusPollingController.this.lastConnectionTimestamp)) {
                    InstallStatusPollingController.this.lastConnectionTimestamp = control.getWirelessRemote().getConnectionState().getTimestamp();
                } else {
                    NavigationController.navigateToDeviceConnected(InstallStatusPollingController.this.mCallingActivity);
                }
            }
        }

        public void onFailure(Call<AirConditioningControl> call, Throwable t) {
            super.onFailure(call, t);
            InstallStatusPollingController.this.pollRequestOpen.set(false);
        }
    }

    class C08113 extends TadoCallback<List<HardwareDevice>> {
        C08113() {
        }

        public void onResponse(Call<List<HardwareDevice>> call, Response<List<HardwareDevice>> response) {
            super.onResponse(call, response);
            if (response.isSuccessful() && response.body() != null && !((List) response.body()).isEmpty()) {
                HardwareDevice device = (HardwareDevice) ((List) response.body()).get(0);
                ConnectionState connectionState = device.getConnectionState();
                Builder start = Snitcher.start();
                String str = "InstallStatusPollingController";
                String str2 = "Getting fist device: %s, connected: %s timestamp: %s";
                Object[] objArr = new Object[3];
                objArr[0] = device.getShortSerialNo();
                objArr[1] = connectionState != null ? connectionState.isConnected() : "null";
                objArr[2] = connectionState != null ? connectionState.getTimestamp() : "null";
                start.log(str, str2, objArr);
                if (device.getConnectionState() != null && device.getConnectionState().isConnected().booleanValue() && device.getConnectionState().getTimestamp() != null) {
                    InstallStatusPollingController.this.pollRequestOpen.set(false);
                    InstallationProcessController.startActivity(InstallStatusPollingController.this.mCallingActivity, DeviceConnectionSuccessfulActivity.class, true);
                }
            }
        }

        public void onFailure(Call<List<HardwareDevice>> call, Throwable t) {
            super.onFailure(call, t);
            InstallStatusPollingController.this.pollRequestOpen.set(false);
        }
    }

    private InstallStatusPollingController() {
    }

    public static InstallStatusPollingController getInstallationProcessController() {
        if (mInstallStatusPollingController == null) {
            synchronized (InstallStatusPollingController.class) {
                if (mInstallStatusPollingController == null) {
                    mInstallStatusPollingController = new InstallStatusPollingController();
                }
            }
        }
        return mInstallStatusPollingController;
    }

    public void pollInstallationStatus(Activity activity) {
        stopPolling();
        this.mCallingActivity = activity;
        this.handler = new Handler();
        pollStatus();
        this.handler.postDelayed(new C08091(), 20000);
    }

    private void pollStatus() {
        WifiManager wifiManager = (WifiManager) TadoApplication.getTadoAppContext().getApplicationContext().getSystemService("wifi");
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiManager.isWifiEnabled() && Util.isCorrectSsid(wifiInfo.getSSID())) {
            Snitcher.start().log(2, LOG_TAG, "Connected to Smart AC - ommitting polling", new Object[0]);
        } else if (!this.pollRequestOpen.compareAndSet(false, true)) {
        } else {
            if (this.resetWifiCredentials) {
                callZoneControl();
            } else {
                callListDevicesToInstall();
            }
        }
    }

    private void callZoneControl() {
        RestServiceGenerator.getTadoRestService().getZoneControl(UserConfig.getHomeId(), this.zoneId, RestServiceGenerator.getCredentialsMap()).enqueue(new C08102());
    }

    private void callListDevicesToInstall() {
        RestServiceGenerator.getTadoInstallationRestService().listDevicesToInstall(UserConfig.getHomeId(), Integer.valueOf(InstallationProcessController.getInstallationProcessController().getInstallationId())).enqueue(new C08113());
    }

    public void stopPolling() {
        if (this.handler != null) {
            this.handler.removeCallbacksAndMessages(null);
            this.pollRequestOpen.set(false);
        }
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
        this.resetWifiCredentials = zoneId != -1;
    }

    public boolean isResetWifiCredentials() {
        return this.resetWifiCredentials;
    }

    public void doneResetWifiCredentials() {
        UserConfig.setDeviceSsid("");
        this.zoneId = -1;
        this.resetWifiCredentials = false;
        this.lastConnectionTimestamp = null;
    }
}
