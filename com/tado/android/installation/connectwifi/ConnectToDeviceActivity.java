package com.tado.android.installation.connectwifi;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.ConnectivityManager.NetworkCallback;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.NetworkRequest.Builder;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.client.APICall;
import com.tado.android.client.APICallListener;
import com.tado.android.client.LocalAPICallUtilities;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.dialogs.AlertWarningDialogListener;
import com.tado.android.entities.DeviceWifiList;
import com.tado.android.entities.Wifi;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallStatusPollingController;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.installation.ResetWifiCredentialsActivity;
import com.tado.android.requests.GetWifiListFromDeviceRequest;
import com.tado.android.responses.GetDeviceInstallationResponse;
import com.tado.android.responses.GetWifiListFromDeviceResponse;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.installation.HardwareDevice;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.Constants;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class ConnectToDeviceActivity extends ACInstallationBaseActivity implements APICallListener, AlertWarningDialogListener {
    private NetworkCallback callback;
    private ConnectivityManager cm;
    private Handler handler = null;
    private WifiReceiver wifiReceiver = null;

    class C09251 extends TadoCallback<List<HardwareDevice>> {
        C09251() {
        }

        public void onResponse(Call<List<HardwareDevice>> call, Response<List<HardwareDevice>> response) {
            super.onResponse(call, response);
            ConnectToDeviceActivity.this.dismissLoadingUI();
            if (response.isSuccessful() && response.body() != null && !((List) response.body()).isEmpty()) {
                Wifi wifi = ((HardwareDevice) ((List) response.body()).get(0)).getAccessPointWifi();
                if (wifi == null || wifi.getSsid() == null || wifi.getSsid().isEmpty()) {
                    AlertDialogs.showWarning(ConnectToDeviceActivity.this.getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_errors_title), ConnectToDeviceActivity.this.getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_errors_saccWifiConnectionError), ConnectToDeviceActivity.this.getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_errors_retryButton), ConnectToDeviceActivity.this, ConnectToDeviceActivity.this);
                    return;
                }
                UserConfig.setDeviceSsid(wifi.getSsid());
                ConnectToDeviceActivity.this.wifiReceiver = new WifiReceiver();
                ConnectToDeviceActivity.this.startWifi();
            }
        }

        public void onFailure(Call<List<HardwareDevice>> call, Throwable t) {
            super.onFailure(call, t);
            ConnectToDeviceActivity.this.dismissLoadingUI();
        }
    }

    class C09262 extends NetworkCallback {
        C09262() {
        }

        public void onAvailable(Network network) {
            if (VERSION.SDK_INT >= 23) {
                ConnectToDeviceActivity.this.cm.bindProcessToNetwork(network);
                return;
            }
            ConnectToDeviceActivity.this.cm;
            ConnectivityManager.setProcessDefaultNetwork(network);
        }
    }

    class C09273 implements Runnable {
        C09273() {
        }

        public void run() {
            if (ConnectToDeviceActivity.this.wifiReceiver != null) {
                ConnectToDeviceActivity.this.wifiReceiver.addAndEnableWifi(ConnectToDeviceActivity.this.getApplicationContext());
            } else {
                ConnectToDeviceActivity.this.wifiReceiver = new WifiReceiver();
            }
            ConnectToDeviceActivity.this.handler.postDelayed(this, Constants.WIFI_CONNECTION_RETRY_TIMEOUT);
        }
    }

    class C09284 implements Runnable {
        C09284() {
        }

        public void run() {
            ConnectToDeviceActivity.this.connectionFailed();
        }
    }

    public static class ConnectionFailedFragment extends Fragment {
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(C0676R.layout.fragment_connect_to_device_failed, container, false);
            ((TextView) rootView.findViewById(C0676R.id.title_template_textview)).setText(C0676R.string.installation_sacc_connectWifi_wifiSetup_deviceNotFound_message);
            ((TextView) rootView.findViewById(C0676R.id.connect_to_device_textview)).setText(getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_deviceNotFound_description, UserConfig.getSerialNo()));
            ((Button) rootView.findViewById(C0676R.id.proceed_button)).setText(getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_deviceNotFound_confirmButton));
            return rootView;
        }
    }

    public static class ConnectionSuccessfulFragment extends Fragment {
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(C0676R.layout.fragment_connect_to_device_successful, container, false);
            ((TextView) rootView.findViewById(C0676R.id.title_template_textview)).setText(C0676R.string.installation_sacc_connectWifi_wifiSetup_connectedToDevice_message);
            ((Button) rootView.findViewById(C0676R.id.proceed_button)).setText(C0676R.string.installation_sacc_connectWifi_wifiSetup_connectedToDevice_confirmButton);
            ImageView imageViewOverlay = (ImageView) rootView.findViewById(C0676R.id.center_image_overlay);
            imageViewOverlay.setImageResource(C0676R.drawable.device_ap_connected);
            imageViewOverlay.setVisibility(0);
            return rootView;
        }
    }

    public static class PlaceholderFragment extends Fragment {
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(C0676R.layout.fragment_connect_to_device, container, false);
            ((TextView) rootView.findViewById(C0676R.id.title_template_textview)).setText(C0676R.string.installation_sacc_connectWifi_wifiSetup_connectingToDevice_message);
            ((TextView) rootView.findViewById(C0676R.id.connect_to_device_textview)).setText(C0676R.string.installation_sacc_connectWifi_wifiSetup_connectingToDevice_description);
            ImageView imageViewOverlay = (ImageView) rootView.findViewById(C0676R.id.center_image_overlay);
            imageViewOverlay.setImageResource(C0676R.drawable.device_hi);
            imageViewOverlay.setVisibility(0);
            return rootView;
        }
    }

    private class WifiReceiver extends BroadcastReceiver {
        private String mSSID;
        private int unsuccessfullConnectionCounter;
        private boolean wifiQuotationInverted;

        public WifiReceiver(String serialNo) {
            this.wifiQuotationInverted = false;
            this.unsuccessfullConnectionCounter = 0;
            if (serialNo.length() < 4) {
                UserConfig.setDeviceSsid(String.format("tado%s", new Object[]{serialNo.substring(serialNo.length() - 4)}));
                this.mSSID = UserConfig.getDeviceSsid(this.wifiQuotationInverted);
            } else {
                UserConfig.setDeviceSsid(String.format("tado%s", new Object[]{serialNo.substring(serialNo.length() - 4)}));
                this.mSSID = UserConfig.getDeviceSsid(this.wifiQuotationInverted);
            }
        }

        public WifiReceiver() {
            this.wifiQuotationInverted = false;
            this.unsuccessfullConnectionCounter = 0;
            this.mSSID = UserConfig.getDeviceSsid(this.wifiQuotationInverted);
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.net.wifi.STATE_CHANGE") && ((NetworkInfo) intent.getParcelableExtra("networkInfo")).isConnected()) {
                if (Util.isCorrectSsid(((WifiInfo) intent.getParcelableExtra("wifiInfo")).getSSID())) {
                    WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
                    if (wifiManager.pingSupplicant()) {
                        ConnectToDeviceActivity.this.unregisterWifiReceiver();
                        ConnectToDeviceActivity.this.connectionSuccessful();
                    } else {
                        wifiManager.reassociate();
                    }
                } else {
                    this.unsuccessfullConnectionCounter++;
                    if (this.unsuccessfullConnectionCounter % 5 == 0) {
                        this.wifiQuotationInverted = !this.wifiQuotationInverted;
                        this.mSSID = UserConfig.getDeviceSsid(this.wifiQuotationInverted);
                    }
                    addAndEnableWifi(context);
                }
            }
            if (action.equals("android.net.wifi.WIFI_STATE_CHANGED") && intent.getIntExtra("wifi_state", 4) == 3) {
                addAndEnableWifi(context);
            }
        }

        public void addAndEnableWifi(Context context) {
            WifiConfiguration wifiConfig = new WifiConfiguration();
            wifiConfig.SSID = this.mSSID;
            wifiConfig.allowedKeyManagement.set(0);
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            wifiManager.setWifiEnabled(true);
            if (wifiManager.isWifiEnabled()) {
                int netId = wifiManager.addNetwork(wifiConfig);
                wifiManager.disconnect();
                boolean enable = wifiManager.enableNetwork(netId, true);
                wifiManager.reconnect();
                return;
            }
            wifiManager.setWifiEnabled(true);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_connect_to_device);
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(ResetWifiCredentialsActivity.RESET_WIFI_CREDENTIALS_ZONE_ID)) {
            InstallStatusPollingController.getInstallationProcessController().setZoneId(getIntent().getExtras().getInt(ResetWifiCredentialsActivity.RESET_WIFI_CREDENTIALS_ZONE_ID));
        }
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add((int) C0676R.id.container, new PlaceholderFragment()).commit();
        }
    }

    protected void onResume() {
        super.onResume();
        getSupportFragmentManager().beginTransaction().replace(C0676R.id.container, new PlaceholderFragment()).commitAllowingStateLoss();
        enableWifi();
        ((TextView) findViewById(C0676R.id.title_bar_textview)).setText(getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_title));
    }

    protected void onPause() {
        super.onPause();
        if (!(this.cm == null || this.callback == null)) {
            this.cm.unregisterNetworkCallback(this.callback);
        }
        unregisterWifiReceiver();
        if (this.handler != null) {
            this.handler.removeCallbacksAndMessages(null);
        }
    }

    public void connectionSuccessful() {
        if (this.handler != null) {
            this.handler.removeCallbacksAndMessages(null);
        }
        if (this.handler != null) {
            this.handler.removeCallbacksAndMessages(null);
        }
        getSupportFragmentManager().beginTransaction().replace(C0676R.id.container, new ConnectionSuccessfulFragment()).commitAllowingStateLoss();
    }

    public void connectionFailed() {
        getSupportFragmentManager().beginTransaction().replace(C0676R.id.container, new ConnectionFailedFragment()).commitAllowingStateLoss();
    }

    private void unregisterWifiReceiver() {
        if (this.wifiReceiver != null) {
            try {
                unregisterReceiver(this.wifiReceiver);
            } catch (IllegalArgumentException e) {
            }
            this.wifiReceiver = null;
        }
    }

    private void enableWifi() {
        unregisterWifiReceiver();
        if (!UserConfig.getDeviceSsid().isEmpty()) {
            this.wifiReceiver = new WifiReceiver();
            startWifi();
        } else if (!UserConfig.getDeviceSsid().isEmpty() || UserConfig.getSerialNo().isEmpty()) {
            sendRequest();
        } else {
            this.wifiReceiver = new WifiReceiver(UserConfig.getSerialNo());
            startWifi();
        }
    }

    private void sendRequest() {
        showLoadingUI();
        RestServiceGenerator.getTadoInstallationRestService().listDevicesToInstall(UserConfig.getHomeId(), Integer.valueOf(InstallationProcessController.getInstallationProcessController().getInstallationId())).enqueue(new C09251());
    }

    public void bindToWiFi(Context context) {
        if (VERSION.SDK_INT >= 21) {
            this.cm = (ConnectivityManager) context.getSystemService("connectivity");
            NetworkRequest request = new Builder().addTransportType(1).build();
            this.callback = new C09262();
            this.cm.registerNetworkCallback(request, this.callback);
        }
    }

    private void startWifi() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.supplicant.CONNECTION_CHANGE");
        intentFilter.addAction("android.net.wifi.supplicant.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        registerReceiver(this.wifiReceiver, intentFilter);
        ((WifiManager) getApplicationContext().getSystemService("wifi")).setWifiEnabled(true);
        bindToWiFi(getApplicationContext());
        this.handler = new Handler();
        this.handler.postDelayed(new C09273(), Constants.WIFI_CONNECTION_RETRY_TIMEOUT);
        this.handler.postDelayed(new C09284(), 30000);
    }

    public void troubleshoot(View view) {
        Intent i = new Intent("android.intent.action.VIEW");
        i.setData(Uri.parse(getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_deviceNotFound_troubleshootingURL)));
        startActivity(i);
    }

    public void proceedClick(View view) {
        Fragment f = getSupportFragmentManager().findFragmentById(C0676R.id.container);
        if (f instanceof ConnectionFailedFragment) {
            getSupportFragmentManager().beginTransaction().replace(C0676R.id.container, new PlaceholderFragment()).commit();
            enableWifi();
        } else if (f instanceof ConnectionSuccessfulFragment) {
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService("wifi");
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if (wifiManager.isWifiEnabled() && Util.isCorrectSsid(wifiInfo.getSSID())) {
                LocalAPICallUtilities.executeRequestOnWiFi((ConnectivityManager) getSystemService("connectivity"), new GetWifiListFromDeviceRequest(), this, this);
                return;
            }
            AlertDialogs.showWarning(getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_errors_title), getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_errors_notConnectedToTadoWifiError, new Object[]{UserConfig.getDeviceSsid()}), getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_errors_retryButton), this, this);
        }
    }

    public void onProcessResponse(APICall call, com.tado.android.responses.Response response) {
        if (response instanceof GetWifiListFromDeviceResponse) {
            Intent intent;
            DeviceWifiList deviceWifiList = ((GetWifiListFromDeviceResponse) response).getDeviceWifiList();
            if (deviceWifiList != null) {
                ArrayList<Wifi> wifiArrayList = deviceWifiList.getCleanedArrayList();
                if (wifiArrayList != null) {
                    intent = new Intent(this, SelectWifiNetworkActivity.class);
                    intent.putExtra("wifiList", wifiArrayList);
                    InstallationProcessController.startActivity((Activity) this, intent, false);
                    return;
                }
            }
            intent = new Intent(this, SelectWifiNetworkActivity.class);
            intent.putExtra("wifiList", new ArrayList());
            InstallationProcessController.startActivity((Activity) this, intent, false);
        }
    }

    public void onCallFailed(APICall call, com.tado.android.responses.Response response) {
        if (response instanceof GetWifiListFromDeviceResponse) {
            AlertDialogs.showWarning(getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_errors_title), getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_errors_saccWifiConnectionError), getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_errors_retryButton), this, null);
        } else if (response instanceof GetDeviceInstallationResponse) {
            InstallationProcessController.showConnectionError(this, call.getRequest(), this);
        } else {
            InstallationProcessController.showConnectionError(this, call.getRequest(), this);
        }
    }

    public void OnOKClicked() {
        onResume();
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
