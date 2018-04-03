package com.tado.android.settings.zonesettings;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.otto.Subscribe;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.controllers.ZoneController;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.entities.ServerError;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.installation.InstallationProcessController.UiCallback;
import com.tado.android.installation.srt.view.fragments.SrtAssignZoneFragment;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.Zone;
import com.tado.android.rest.model.Zone.TypeEnum;
import com.tado.android.rest.model.ZoneState;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.settings.mainsettings.SettingsActivity2;
import com.tado.android.utils.ResourceFactory;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import com.tado.databinding.ActivityDeviceDetailsBinding;
import retrofit2.Call;
import retrofit2.Response;

public class DeviceDetailsActivity extends AppCompatActivity implements SrtAssignCallback {
    public static final String KEY_DEVICE = "key_device";
    public static final String KEY_ZONE_ID = "key_zone_id";
    public static final String KEY_ZONE_TYPE = "key_zone_type";
    private static final String TAG = "DeviceDetails";
    private boolean allowBackNavigation = true;
    @BindView(2131296380)
    Button assignButton;
    @BindView(2131297061)
    TextView description;
    private GenericHardwareDevice device;
    @BindView(2131296385)
    Button identifyButton;
    private int oldZoneId;
    @BindView(2131296681)
    ImageView productImage;
    @BindView(2131297070)
    TextView productName;
    @BindView(2131296389)
    Button removeDeviceButton;
    boolean removeOldZone = false;
    @BindView(2131297060)
    TextView textBatteryDescription;

    class C11491 implements OnClickListener {
        C11491() {
        }

        public void onClick(View v) {
            DeviceDetailsActivity.this.identify(DeviceDetailsActivity.this.device.getShortSerialNo());
        }
    }

    class C11502 implements OnClickListener {
        C11502() {
        }

        public void onClick(View v) {
            DeviceDetailsActivity.this.getSupportFragmentManager().beginTransaction().add(16908290, SrtAssignZoneFragment.newInstance(ZoneController.INSTANCE.getZoneList(), DeviceDetailsActivity.this.device), "assign").addToBackStack("assign").commit();
        }
    }

    class C11533 implements OnClickListener {

        class C11511 implements DialogInterface.OnClickListener {
            C11511() {
            }

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }

        class C11522 implements DialogInterface.OnClickListener {
            C11522() {
            }

            public void onClick(DialogInterface dialog, int which) {
                DeviceDetailsActivity.this.removeDeviceFromHome(dialog, false, DeviceDetailsActivity.this.device);
            }
        }

        C11533() {
        }

        public void onClick(View v) {
            new Builder(DeviceDetailsActivity.this).setTitle((int) C0676R.string.settings_zoneSettings_devices_removeDevice_title).setMessage(Util.getText(DeviceDetailsActivity.this.getApplicationContext(), C0676R.string.settings_zoneSettings_devices_removeDevice_message, UserConfig.getHomeName())).setPositiveButton((int) C0676R.string.settings_zoneSettings_devices_removeDevice_confirmButton, new C11522()).setNegativeButton((int) C0676R.string.settings_zoneSettings_devices_removeDevice_cancelButton, new C11511()).setCancelable(false).show();
        }
    }

    class C11575 extends TadoCallback<Void> {
        C11575() {
        }

        public void onResponse(Call<Void> call, Response<Void> response) {
            super.onResponse(call, response);
            if (response.isSuccessful()) {
                AlertDialogs.showSimpleWarning(DeviceDetailsActivity.this.getString(C0676R.string.settings_zoneSettings_devices_deviceDetails_identifyButton), DeviceDetailsActivity.this.getString(C0676R.string.settings_zoneSettings_devices_deviceDetails_identifyTriggeredLabel), DeviceDetailsActivity.this.getString(C0676R.string.ok), DeviceDetailsActivity.this, null);
            } else {
                DeviceDetailsActivity.this.showErrorMessage(DeviceDetailsActivity.this.getString(C0676R.string.cannot_apply_changes));
            }
        }

        public void onFailure(Call<Void> call, Throwable t) {
            super.onFailure(call, t);
            DeviceDetailsActivity.this.showErrorMessage(DeviceDetailsActivity.this.getString(C0676R.string.cannot_apply_changes));
        }
    }

    public static Intent getIntent(Context context, GenericHardwareDevice device, TypeEnum zoneType, int zoneId) {
        Intent intent = new Intent(context, DeviceDetailsActivity.class);
        intent.putExtra(KEY_DEVICE, device);
        intent.putExtra(KEY_ZONE_TYPE, zoneType);
        intent.putExtra(KEY_ZONE_ID, zoneId);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypeEnum zoneType = (TypeEnum) getIntent().getSerializableExtra(KEY_ZONE_TYPE);
        ActivityDeviceDetailsBinding binding = (ActivityDeviceDetailsBinding) DataBindingUtil.setContentView(this, C0676R.layout.activity_device_details);
        ButterKnife.bind((Activity) this);
        this.device = (GenericHardwareDevice) getIntent().getSerializableExtra(KEY_DEVICE);
        this.oldZoneId = getIntent().getIntExtra(KEY_ZONE_ID, -1);
        if (this.device == null || zoneType == null) {
            finish();
        }
        binding.setDevice(this.device);
        binding.setZoneType(zoneType);
        setupActionBar(this.device.getShortSerialNo());
        this.productImage.setImageResource(ResourceFactory.getImageResourceForDeviceType(this.device.getDeviceType()));
        this.identifyButton.setOnClickListener(new C11491());
        this.assignButton.setOnClickListener(new C11502());
        this.removeDeviceButton.setOnClickListener(new C11533());
        Button button = this.removeDeviceButton;
        int i = (this.device.isValve() || this.device.isCooling()) ? 0 : 8;
        button.setVisibility(i);
        this.textBatteryDescription.setText(this.device.isValve() ? getText(C0676R.string.settings_zoneSettings_devices_deviceDetails_battery_vaReplacementInstructions) : getText(C0676R.string.settings_zoneSettings_devices_deviceDetails_battery_ruReplacementInstructions));
        this.textBatteryDescription.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void setupActionBar(String serialNo) {
        getSupportActionBar().setTitle((CharSequence) serialNo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void removeDeviceFromHome(final DialogInterface dialog, final boolean forceRemove, final GenericHardwareDevice device) {
        updateButtonsState(false);
        InstallationProcessController.getInstallationProcessController().removeDeviceFromHome(forceRemove, device, new UiCallback() {

            class C11541 implements DialogInterface.OnClickListener {
                C11541() {
                }

                public void onClick(DialogInterface dialog, int which) {
                    DeviceDetailsActivity.this.updateButtonsState(true);
                    dialog.dismiss();
                }
            }

            class C11552 implements DialogInterface.OnClickListener {
                C11552() {
                }

                public void onClick(DialogInterface dialog, int which) {
                    DeviceDetailsActivity.this.removeDeviceFromHome(dialog, true, device);
                }
            }

            public void onRequestFinished(Response response, ServerError serverError) {
                DeviceDetailsActivity.this.updateButtonsState(true);
                if (response.isSuccessful()) {
                    if (forceRemove) {
                        ZoneController.INSTANCE.removeZone(DeviceDetailsActivity.this.oldZoneId);
                        Intent intent = new Intent(DeviceDetailsActivity.this, SettingsActivity2.class);
                        intent.addFlags(67108864);
                        DeviceDetailsActivity.this.startActivity(intent);
                    } else {
                        DeviceDetailsActivity.this.finish();
                    }
                    dialog.dismiss();
                } else if (response.code() == 412) {
                    new Builder(DeviceDetailsActivity.this).setTitle((int) C0676R.string.settings_zoneSettings_devices_removeDevice_removeLastDevice_title).setMessage((int) C0676R.string.settings_zoneSettings_devices_removeDevice_removeLastDevice_message).setPositiveButton((int) C0676R.string.settings_zoneSettings_devices_removeDevice_removeLastDevice_confirmButton, new C11552()).setNegativeButton((int) C0676R.string.settings_zoneSettings_devices_removeDevice_removeLastDevice_cancelButton, new C11541()).setCancelable(false).show();
                } else {
                    DeviceDetailsActivity.this.showErrorMessage(DeviceDetailsActivity.this.getString(C0676R.string.cannot_apply_changes));
                }
            }

            public void onRequestFailed() {
                DeviceDetailsActivity.this.updateButtonsState(true);
                DeviceDetailsActivity.this.showErrorMessage(DeviceDetailsActivity.this.getString(C0676R.string.cannot_apply_changes));
            }
        });
    }

    private void updateButtonsState(boolean enabled) {
        this.removeDeviceButton.setEnabled(enabled);
        this.identifyButton.setEnabled(enabled);
        this.assignButton.setEnabled(enabled);
    }

    private void identify(String serialNumber) {
        if (serialNumber != null) {
            RestServiceGenerator.getTadoRestService().identifyDevice(serialNumber, RestServiceGenerator.getCredentialsMap()).enqueue(new C11575());
        }
    }

    private void showErrorMessage(String msg) {
        Snitcher.start().toCrashlytics().log("DeviceDetailsActivity", msg, new Object[0]);
        Snackbar.make(findViewById(16908290), (CharSequence) msg, 0).show();
    }

    public void onAssignedZone(Zone zone, boolean forced) {
        this.removeOldZone = forced;
        TadoApplication.getBus().register(this);
        ZoneController.INSTANCE.callGetZoneList();
        ZoneController.INSTANCE.selectZone(zone);
    }

    @Subscribe
    public void getZoneState(ZoneState state) {
        TadoApplication.getBus().unregister(this);
        if (this.removeOldZone) {
            ZoneController.INSTANCE.removeZone(this.oldZoneId);
        }
        Intent intent = new Intent(this, SettingsActivity2.class);
        intent.addFlags(67108864);
        startActivity(intent);
    }

    public void onServerCallFailure() {
        showErrorMessage(getString(C0676R.string.errors_noInternetConnection_message));
    }

    public void allowBackNavigation(boolean allow) {
        this.allowBackNavigation = allow;
    }

    public void onBackPressed() {
        if (this.allowBackNavigation) {
            super.onBackPressed();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
