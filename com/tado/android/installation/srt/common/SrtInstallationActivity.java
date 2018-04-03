package com.tado.android.installation.srt.common;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tado.C0676R;
import com.tado.android.controllers.ZoneController;
import com.tado.android.installation.ChooseProductActivity;
import com.tado.android.installation.InstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.installation.barcode.BarcodeActivity;
import com.tado.android.installation.common.CongratulationsFragment;
import com.tado.android.installation.common.CongratulationsScreenCallback;
import com.tado.android.installation.srt.view.fragments.SrtAssignZoneFragment;
import com.tado.android.installation.srt.view.fragments.SrtHvacInstallationFragment;
import com.tado.android.installation.srt.view.fragments.SrtRegisterBarcodeDeviceFragment;
import com.tado.android.installation.srt.view.fragments.SrtRegisterDeviceFragment;
import com.tado.android.installation.srt.view.fragments.SrtRegisterDeviceFragment.DeviceTypeEnum;
import com.tado.android.installation.unfinished.UnfinishedInstallationDetailsActivity;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.DeviceType;
import com.tado.android.rest.model.Zone;
import com.tado.android.rest.model.hvac.BridgeReplacementInstallation;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.settings.users.PeoplePreferenceActivity;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class SrtInstallationActivity extends InstallationBaseActivity implements SrtNavigationCallback, CongratulationsScreenCallback {
    public static final String KEY_SRT_DEVICE = "keySrtDevice";
    private Integer assignedZoneId = null;
    private List<GenericHardwareDevice> devices;
    @BindView(2131296349)
    Button mBackButton;
    @BindView(2131296374)
    View mNavigationBar;
    @BindView(2131296815)
    Button mNextButton;
    private SrtNavigationInterface mSrtNavigation;
    @NonNull
    private OnClickListener onBackClick = new C09631();
    @NonNull
    private OnClickListener onNextClick = new C09642();
    private boolean registerManually = false;
    @BindView(2131297149)
    Toolbar toolbar;

    class C09631 implements OnClickListener {
        C09631() {
        }

        public void onClick(View v) {
            if (SrtInstallationActivity.this.mSrtNavigation != null) {
                SrtInstallationActivity.this.mSrtNavigation.onBack();
            }
            SrtInstallationActivity.this.mNextButton.setEnabled(true);
        }
    }

    class C09642 implements OnClickListener {
        C09642() {
        }

        public void onClick(View v) {
            if (SrtInstallationActivity.this.mSrtNavigation != null) {
                SrtInstallationActivity.this.mSrtNavigation.onNext();
            }
        }
    }

    class C09653 extends TadoCallback<List<GenericHardwareDevice>> {
        C09653() {
        }

        public void onResponse(Call<List<GenericHardwareDevice>> call, Response<List<GenericHardwareDevice>> response) {
            super.onResponse(call, response);
            if (response.isSuccessful()) {
                SrtInstallationActivity.this.devices = (List) response.body();
                SrtInstallationActivity.this.updateSrtDevice(SrtInstallationActivity.this.devices);
                GenericHardwareDevice gateway = new DevicesWrapper(SrtInstallationActivity.this.devices).getFirstNonGW01GatewayDevice();
                if (gateway == null) {
                    if (SrtInstallationActivity.this.registerManually) {
                        SrtInstallationActivity.this.loadSrtFragment(SrtRegisterDeviceFragment.newGatewayInstance(false));
                    } else {
                        SrtInstallationActivity.this.loadSrtFragment(SrtRegisterBarcodeDeviceFragment.newGatewayInstance(false));
                    }
                } else if (gateway.getConnectionState().isConnected().booleanValue()) {
                    GenericHardwareDevice valveDevice = InstallationProcessController.getInstallationProcessController().getInstallationInfo().getCurrentUninstalledDevice();
                    if (valveDevice != null) {
                        if (valveDevice.getConnectionState().isConnected().booleanValue() && valveDevice.getMountingState().isCalibrated()) {
                            SrtInstallationActivity.this.handleAssignToZoneOrFinishInstallation();
                        } else {
                            SrtInstallationActivity.this.loadSrtFragment(SrtHvacInstallationFragment.newInstance(valveDevice));
                        }
                    } else if (SrtInstallationActivity.this.registerManually) {
                        SrtInstallationActivity.this.loadSrtFragment(SrtRegisterDeviceFragment.newValveInstance());
                    } else {
                        SrtInstallationActivity.this.loadSrtFragment(SrtRegisterBarcodeDeviceFragment.newValveInstance());
                    }
                } else {
                    SrtInstallationActivity.this.loadSrtFragment(SrtHvacInstallationFragment.newInstance(gateway));
                }
            }
        }

        public void onFailure(Call<List<GenericHardwareDevice>> call, Throwable t) {
            super.onFailure(call, t);
            SrtInstallationActivity.this.onServerCallFailure();
        }
    }

    class C09664 extends TadoCallback<List<Zone>> {
        C09664() {
        }

        public void onResponse(Call<List<Zone>> call, Response<List<Zone>> response) {
            super.onResponse(call, response);
            if (response.isSuccessful()) {
                GenericHardwareDevice valveDevice = InstallationProcessController.getInstallationProcessController().getInstallationInfo().getCurrentUninstalledDevice();
                for (Zone zone : (List) response.body()) {
                    for (GenericHardwareDevice zoneDevice : zone.getDevices()) {
                        if (zoneDevice.getShortSerialNo().equals(valveDevice.getShortSerialNo())) {
                            SrtInstallationActivity.this.onAssignedZone(zone.getId());
                            return;
                        }
                    }
                }
                SrtInstallationActivity.this.loadSrtFragment(SrtAssignZoneFragment.newInstance((List) response.body(), valveDevice));
            }
        }

        public void onFailure(Call<List<Zone>> call, Throwable t) {
            super.onFailure(call, t);
            SrtInstallationActivity.this.onServerCallFailure();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_srt_installation);
        ButterKnife.bind((Activity) this);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.mNextButton.setOnClickListener(this.onNextClick);
        this.mBackButton.setOnClickListener(this.onBackClick);
        if (this.mNextButton.getCompoundDrawables()[2] != null) {
            this.mNextButton.getCompoundDrawables()[2].setColorFilter(ContextCompat.getColor(this, C0676R.color.ac_home), Mode.SRC_ATOP);
        }
        if (this.mBackButton.getCompoundDrawables()[0] != null) {
            this.mBackButton.getCompoundDrawables()[0].setColorFilter(ContextCompat.getColor(this, C0676R.color.ac_home), Mode.SRC_ATOP);
        }
        if (getIntent().hasExtra(KEY_SRT_DEVICE)) {
            InstallationProcessController.getInstallationProcessController().getInstallationInfo().setCurrentUninstalledDevice((GenericHardwareDevice) getIntent().getSerializableExtra(KEY_SRT_DEVICE));
        }
        detectStep();
    }

    protected void loadSrtFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(C0676R.id.fragment_placeholder, fragment, fragment.getTag()).commitAllowingStateLoss();
        if (fragment instanceof SrtNavigationInterface) {
            this.mSrtNavigation = (SrtNavigationInterface) fragment;
            this.mNavigationBar.setVisibility(0);
        } else {
            this.mSrtNavigation = null;
            this.mNavigationBar.setVisibility(8);
        }
        invalidateOptionsMenu();
    }

    public void onBackFinishHvacStep() {
        super.onBackPressed();
    }

    public void onNextFinishHvacStep() {
        detectStep();
    }

    public void onBackStep() {
        super.onBackPressed();
    }

    public void onRegisteredDevice(GenericHardwareDevice device) {
        if (device.isValve()) {
            InstallationProcessController.getInstallationProcessController().getInstallationInfo().setCurrentUninstalledDevice(device);
            invalidateOptionsMenu();
            InstallationProcessController.getInstallationProcessController().registerDevice(device);
        }
        setNextButtonState(true);
        loadSrtFragment(SrtHvacInstallationFragment.newInstance(device));
    }

    public void onAssignedZone(int zoneId) {
        this.assignedZoneId = Integer.valueOf(zoneId);
        invalidateOptionsMenu();
        InstallationProcessController.getInstallationProcessController().setCallingActivity(this).assignDeviceToZone(this.assignedZoneId.intValue(), InstallationProcessController.getInstallationProcessController().getInstallationInfo().getCurrentUninstalledDevice());
        loadSrtFragment(CongratulationsFragment.newInstance());
    }

    public void onAddNewDevice() {
        cleanupAndLoadZones();
        Intent intent = new Intent(this, ChooseProductActivity.class);
        intent.setFlags(335544320);
        startActivity(intent);
    }

    public void onFinishInstallation() {
        cleanupAndLoadZones();
        InstallationProcessController.getInstallationProcessController().detectStatus(this);
    }

    public void invitePeople() {
        startActivity(new Intent(this, PeoplePreferenceActivity.class));
    }

    private void cleanupAndLoadZones() {
        cleanUpInstallation();
        prepareZones();
    }

    private void cleanUpInstallation() {
        RestServiceGenerator.destroyHvacToolClient();
        InstallationProcessController.getInstallationProcessController().getInstallationInfo().setCurrentUninstalledDevice(null);
    }

    private void prepareZones() {
        ZoneController.INSTANCE.selectZone(this.assignedZoneId.intValue());
        ZoneController.INSTANCE.callGetZoneList();
    }

    public void onServerCallFailure() {
        Snackbar.make(this.mNavigationBar, (int) C0676R.string.errors_noInternetConnection_message, 0).show();
    }

    public void setNextButtonState(boolean enabled) {
        this.mNextButton.setEnabled(enabled);
        if (this.mNextButton.getCompoundDrawables()[2] != null) {
            this.mNextButton.getCompoundDrawables()[2].setColorFilter(ContextCompat.getColor(this, enabled ? C0676R.color.ac_home : C0676R.color.disabled_text_color), Mode.SRC_ATOP);
        }
    }

    public void onBackPressed() {
        if (this.mSrtNavigation != null) {
            this.mSrtNavigation.onBack();
        }
    }

    public void onReplacementCreated(BridgeReplacementInstallation bridgeReplacement) {
    }

    public void setNextButtonText(@StringRes int resId) {
        if (this.mNextButton != null) {
            this.mNextButton.setText(resId);
        }
    }

    protected void detectStep() {
        RestServiceGenerator.getTadoRestService().getDevices(UserConfig.getHomeId(), RestServiceGenerator.getCredentialsMap()).enqueue(new C09653());
    }

    private void handleAssignToZoneOrFinishInstallation() {
        RestServiceGenerator.getTadoRestService().getZones(UserConfig.getHomeId(), RestServiceGenerator.getCredentialsMap()).enqueue(new C09664());
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case -1:
                try {
                    onRegisteredDevice((GenericHardwareDevice) data.getSerializableExtra(BarcodeActivity.EXTRA_HARDWARE_DEVICE));
                    return;
                } catch (Exception e) {
                    Snitcher.start().toCrashlytics().logException("Error registering device", e);
                    return;
                }
            case 0:
                if (data != null && data.getExtras().containsKey(SrtHvacInstallationFragment.KEY_DEVICE_TYPE)) {
                    this.registerManually = true;
                    if (DeviceTypeEnum.GATEWAY == ((DeviceTypeEnum) data.getSerializableExtra(SrtHvacInstallationFragment.KEY_DEVICE_TYPE))) {
                        loadSrtFragment(SrtRegisterDeviceFragment.newGatewayInstance(false));
                        return;
                    } else {
                        loadSrtFragment(SrtRegisterDeviceFragment.newValveInstance());
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }

    private void updateSrtDevice(List<GenericHardwareDevice> devices) {
        GenericHardwareDevice valveDevice = InstallationProcessController.getInstallationProcessController().getInstallationInfo().getCurrentUninstalledDevice();
        if (valveDevice != null) {
            for (GenericHardwareDevice device : devices) {
                if (valveDevice.getShortSerialNo().equalsIgnoreCase(device.getShortSerialNo())) {
                    InstallationProcessController.getInstallationProcessController().getInstallationInfo().setCurrentUninstalledDevice(device);
                    return;
                }
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (this.assignedZoneId == null) {
            menu.add(0, 0, 0, C0676R.string.installation_menu_supendInstallationButton).setIcon(C0676R.drawable.ic_more_vert_black_24dp).setShowAsAction(2);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, UnfinishedInstallationDetailsActivity.class);
        GenericHardwareDevice valveDevice = InstallationProcessController.getInstallationProcessController().getInstallationInfo().getCurrentUninstalledDevice();
        if (valveDevice == null) {
            GenericHardwareDevice temp = new GenericHardwareDevice();
            temp.setDeviceType(DeviceType.VA01);
            intent.putExtra(KEY_SRT_DEVICE, temp);
        } else {
            intent.putExtra(KEY_SRT_DEVICE, valveDevice);
        }
        startActivity(intent);
        return true;
    }
}
