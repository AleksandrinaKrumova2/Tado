package com.tado.android.installation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.tado.C0676R;
import com.tado.android.installation.barcode.BarcodeActivity;
import com.tado.android.installation.srt.common.InstallationReconnectDevicesFragment;
import com.tado.android.installation.srt.common.SrtInstallationActivity;
import com.tado.android.installation.srt.view.fragments.SrtHvacInstallationFragment;
import com.tado.android.installation.srt.view.fragments.SrtRegisterBarcodeDeviceFragment;
import com.tado.android.installation.srt.view.fragments.SrtRegisterDeviceFragment;
import com.tado.android.installation.unfinished.UnfinishedInstallationDetailsActivity;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.hvac.BridgeReplacementInstallation;
import com.tado.android.rest.model.hvac.BridgeReplacementState;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;
import java.io.Serializable;
import retrofit2.Call;
import retrofit2.Response;

public class InstallInternetBridgeReplacementActivity extends SrtInstallationActivity {
    public static final String KEY_BRIDGE_REPLACEMENT_INFO = "keyBridgeReplacementInfo";
    private BridgeReplacementInstallation bridgeReplacement = null;
    private boolean registerManually = false;

    class C08061 extends TadoCallback<BridgeReplacementInstallation> {
        C08061() {
        }

        public void onResponse(Call<BridgeReplacementInstallation> call, Response<BridgeReplacementInstallation> response) {
            super.onResponse(call, response);
            if (response.isSuccessful()) {
                InstallInternetBridgeReplacementActivity.this.bridgeReplacement = (BridgeReplacementInstallation) response.body();
                switch (InstallInternetBridgeReplacementActivity.this.bridgeReplacement.getState()) {
                    case CONNECT_BRIDGE:
                        InstallInternetBridgeReplacementActivity.this.loadSrtFragment(SrtHvacInstallationFragment.newInstance((GenericHardwareDevice) InstallInternetBridgeReplacementActivity.this.bridgeReplacement.getDevices().get(0), InstallInternetBridgeReplacementActivity.this.bridgeReplacement.getBridgeReplacementInformation().oldBridge));
                        return;
                    case DEVICES_RECONNECTING:
                        InstallInternetBridgeReplacementActivity.this.loadSrtFragment(InstallationReconnectDevicesFragment.newInstance(InstallInternetBridgeReplacementActivity.this.bridgeReplacement));
                        return;
                    case COMPLETED:
                        InstallationProcessController.getInstallationProcessController().detectStatus(InstallInternetBridgeReplacementActivity.this);
                        return;
                    default:
                        return;
                }
            } else if (response.code() == 412 && InstallInternetBridgeReplacementActivity.this.bridgeReplacement != null && InstallInternetBridgeReplacementActivity.this.bridgeReplacement.getState() == BridgeReplacementState.CONNECT_BRIDGE) {
                InstallInternetBridgeReplacementActivity.this.loadSrtFragment(SrtHvacInstallationFragment.newInstance((GenericHardwareDevice) InstallInternetBridgeReplacementActivity.this.bridgeReplacement.getDevices().get(0), InstallInternetBridgeReplacementActivity.this.bridgeReplacement.getBridgeReplacementInformation().oldBridge));
            }
        }

        public void onFailure(Call<BridgeReplacementInstallation> call, Throwable t) {
            super.onFailure(call, t);
        }
    }

    class C08072 extends TadoCallback<BridgeReplacementInstallation> {
        C08072() {
        }

        public void onResponse(Call<BridgeReplacementInstallation> call, Response<BridgeReplacementInstallation> response) {
            super.onResponse(call, response);
            if (response.isSuccessful()) {
                InstallInternetBridgeReplacementActivity.this.detectStep();
            } else if (response.code() == 412 && InstallInternetBridgeReplacementActivity.this.bridgeReplacement != null && InstallInternetBridgeReplacementActivity.this.bridgeReplacement.getState() == BridgeReplacementState.CONNECT_BRIDGE) {
                InstallInternetBridgeReplacementActivity.this.loadSrtFragment(SrtHvacInstallationFragment.newInstance((GenericHardwareDevice) InstallInternetBridgeReplacementActivity.this.bridgeReplacement.getDevices().get(0), InstallInternetBridgeReplacementActivity.this.bridgeReplacement.getBridgeReplacementInformation().oldBridge));
            }
        }

        public void onFailure(Call<BridgeReplacementInstallation> call, Throwable t) {
            super.onFailure(call, t);
            InstallInternetBridgeReplacementActivity.this.setNextButtonState(true);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        this.bridgeReplacement = (BridgeReplacementInstallation) getIntent().getSerializableExtra(KEY_BRIDGE_REPLACEMENT_INFO);
        if (savedInstanceState != null) {
            this.registerManually = savedInstanceState.getBoolean("registerManually");
            if (this.bridgeReplacement == null) {
                this.bridgeReplacement = (BridgeReplacementInstallation) savedInstanceState.getSerializable("installation");
            }
        }
        super.onCreate(savedInstanceState);
    }

    protected void detectStep() {
        if (this.bridgeReplacement != null) {
            RestServiceGenerator.getTadoInstallationRestService().getBridgeInstallationState(UserConfig.getHomeId(), this.bridgeReplacement.getId()).enqueue(new C08061());
        } else if (this.registerManually) {
            loadSrtFragment(SrtRegisterDeviceFragment.newGatewayInstance(true));
        } else {
            loadSrtFragment(SrtRegisterBarcodeDeviceFragment.newGatewayInstance(true));
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, 100, null);
        switch (resultCode) {
            case -1:
                try {
                    this.bridgeReplacement = (BridgeReplacementInstallation) data.getSerializableExtra(BarcodeActivity.EXTRA_INSTALLATION);
                    onReplacementCreated(this.bridgeReplacement);
                    return;
                } catch (Exception e) {
                    Snitcher.start().toCrashlytics().logException("Error registering device", e);
                    return;
                }
            case 0:
                if (data != null && data.getExtras().containsKey(SrtHvacInstallationFragment.KEY_DEVICE_TYPE)) {
                    this.registerManually = true;
                    loadSrtFragment(SrtRegisterDeviceFragment.newGatewayInstance(true));
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onReplacementCreated(BridgeReplacementInstallation bridgeReplacement) {
        this.bridgeReplacement = bridgeReplacement;
        detectStep();
    }

    public void onNextFinishHvacStep() {
        setNextButtonState(false);
        RestServiceGenerator.getTadoInstallationRestService().confirmBridgeConnection(UserConfig.getHomeId(), this.bridgeReplacement.getId().intValue()).enqueue(new C08072());
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!(this.bridgeReplacement == null || this.bridgeReplacement.getState() == null || this.bridgeReplacement.getState() == BridgeReplacementState.COMPLETED || this.bridgeReplacement.getDevices().size() <= 0)) {
            menu.add(0, 0, 0, C0676R.string.installation_menu_supendInstallationButton).setIcon(C0676R.drawable.ic_more_vert_black_24dp).setShowAsAction(2);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, UnfinishedInstallationDetailsActivity.class);
        if (this.bridgeReplacement != null) {
            intent.putExtra(SrtInstallationActivity.KEY_SRT_DEVICE, (Serializable) this.bridgeReplacement.getDevices().get(0));
        }
        startActivity(intent);
        return true;
    }

    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("registerManually", this.registerManually);
        outState.putSerializable("installation", this.bridgeReplacement);
        super.onSaveInstanceState(outState);
    }
}
