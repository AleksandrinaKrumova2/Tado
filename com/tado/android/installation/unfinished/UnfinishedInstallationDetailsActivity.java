package com.tado.android.installation.unfinished;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import butterknife.Unbinder;
import com.tado.C0676R;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.entities.InstallationInfo;
import com.tado.android.entities.ServerError;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.installation.InstallationProcessController.UiCallback;
import com.tado.android.installation.srt.common.SrtInstallationActivity;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.installation.AcInstallation.StateEnum;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.rest.model.installation.Installation;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.ResourceFactory;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;
import retrofit2.Call;
import retrofit2.Response;

public class UnfinishedInstallationDetailsActivity extends AppCompatActivity {
    public static final String TAG = "UnfinishedInstDet";
    protected GenericHardwareDevice device;
    @Nullable
    @BindView(2131296681)
    protected ImageView deviceImageView;
    @Nullable
    @BindView(2131297062)
    protected TextView deviceNameTextView;
    @Nullable
    @BindView(2131297067)
    protected TextView helpTextView;
    @BindView(2131296385)
    protected Button identifyButton;
    @BindView(2131296389)
    protected Button removeButton;
    @BindView(2131296391)
    protected Button resetAcButton;
    @Nullable
    @BindView(2131297071)
    protected TextView serialTextView;
    @BindView(2131296396)
    protected Button suspendButton;
    @BindView(2131297072)
    protected TextView titleTextView;
    protected Unbinder unbinder;

    class C09893 extends TadoCallback<Void> {
        C09893() {
        }

        public void onResponse(Call<Void> call, Response<Void> response) {
            super.onResponse(call, response);
            if (response.isSuccessful()) {
                AlertDialogs.showSimpleWarning(UnfinishedInstallationDetailsActivity.this.getString(C0676R.string.installation_menu_identifyButton), UnfinishedInstallationDetailsActivity.this.getString(C0676R.string.installation_menu_identifyDeviceTriggeredLabel), UnfinishedInstallationDetailsActivity.this.getString(C0676R.string.ok), UnfinishedInstallationDetailsActivity.this, null);
            } else {
                UnfinishedInstallationDetailsActivity.this.showErrorMessage(UnfinishedInstallationDetailsActivity.this.getString(C0676R.string.cannot_apply_changes));
            }
        }

        public void onFailure(Call<Void> call, Throwable t) {
            super.onFailure(call, t);
            UnfinishedInstallationDetailsActivity.this.showErrorMessage(UnfinishedInstallationDetailsActivity.this.getString(C0676R.string.cannot_apply_changes));
        }
    }

    class C09904 implements OnClickListener {
        C09904() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }

    class C09915 implements OnClickListener {
        C09915() {
        }

        public void onClick(DialogInterface dialog, int which) {
            UnfinishedInstallationDetailsActivity.this.confirmRemoveFromHome();
            dialog.dismiss();
        }
    }

    class C09926 implements UiCallback {
        C09926() {
        }

        public void onRequestFinished(Response response, ServerError serverError) {
            if (response.isSuccessful()) {
                UnfinishedInstallationDetailsActivity.this.cleanupInstallation();
                InstallationInfo installationInfo = InstallationProcessController.getInstallationProcessController().getInstallationInfo();
                if (installationInfo != null) {
                    installationInfo.getUninstalledDevices().remove(UnfinishedInstallationDetailsActivity.this.device);
                    if (installationInfo.getUninstalledDevices() != null && installationInfo.getUninstalledDevices().size() > 0) {
                        Intent intent = new Intent(UnfinishedInstallationDetailsActivity.this, UnfinishedInstallationsActivity.class);
                        intent.setFlags(67239936);
                        UnfinishedInstallationDetailsActivity.this.startActivity(intent);
                        UnfinishedInstallationDetailsActivity.this.finish();
                        return;
                    }
                }
                InstallationProcessController.getInstallationProcessController().detectStatus(UnfinishedInstallationDetailsActivity.this);
                return;
            }
            UnfinishedInstallationDetailsActivity.this.showErrorMessage(UnfinishedInstallationDetailsActivity.this.getString(C0676R.string.cannot_apply_changes));
        }

        public void onRequestFailed() {
            UnfinishedInstallationDetailsActivity.this.showErrorMessage(UnfinishedInstallationDetailsActivity.this.getString(C0676R.string.cannot_apply_changes));
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        this.unbinder = ButterKnife.bind((Activity) this);
        this.device = (GenericHardwareDevice) getIntent().getSerializableExtra(SrtInstallationActivity.KEY_SRT_DEVICE);
        if (this.device == null) {
            this.device = getDeviceFromInstallationInfo(InstallationProcessController.getInstallationProcessController().getInstallationInfo());
        }
        if (this.device != null) {
            bind(this.device);
        } else {
            finish();
        }
    }

    protected int getLayoutId() {
        return C0676R.layout.activity_unfinished_installation_details;
    }

    private GenericHardwareDevice getDeviceFromInstallationInfo(InstallationInfo installationInfo) {
        if (installationInfo != null) {
            for (Installation installation : installationInfo.getUnfinishedInstallations()) {
                if (installation != null && installation.getId().intValue() == InstallationProcessController.getInstallationProcessController().getInstallationId()) {
                    if (installation.getDevices().size() > 0) {
                        return (GenericHardwareDevice) installation.getDevices().get(0);
                    }
                    Snitcher.start().toCrashlytics().log(6, TAG, "There are no devices", new Object[0]);
                    finish();
                }
            }
        }
        return null;
    }

    protected void onDestroy() {
        this.unbinder.unbind();
        super.onDestroy();
    }

    private void bind(GenericHardwareDevice device) {
        bindActionBar();
        bindDeviceDisplaySection(device);
        bindActionButtons(device);
        bindHelpSection();
    }

    protected void bindActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    protected void bindDeviceDisplaySection(GenericHardwareDevice device) {
        if (device.getShortSerialNo() != null) {
            this.serialTextView.setText(device.getShortSerialNo());
        }
        this.deviceNameTextView.setText(ResourceFactory.getDeviceName(device.getDeviceType()));
        this.deviceImageView.setImageResource(ResourceFactory.getImageResourceForDeviceType(device.getDeviceType()));
    }

    protected void bindActionButtons(GenericHardwareDevice device) {
        int i;
        int i2 = 0;
        StateEnum acInstallationState = InstallationProcessController.getInstallationProcessController().getAcInstallationState();
        boolean showReset;
        if (!device.isCooling() || acInstallationState.ordinal() <= StateEnum.AC_SPECS.ordinal()) {
            showReset = false;
        } else {
            showReset = true;
        }
        Button button = this.identifyButton;
        if (device.canBeIdentified()) {
            i = 0;
        } else {
            i = 8;
        }
        button.setVisibility(i);
        button = this.resetAcButton;
        if (device.isCooling() && showReset) {
            i = 0;
        } else {
            i = 8;
        }
        button.setVisibility(i);
        Button button2 = this.removeButton;
        if (!(device.isValve() || device.isCooling()) || device.getSerialNo() == null) {
            i2 = 8;
        }
        button2.setVisibility(i2);
    }

    protected void bindHelpSection() {
        this.helpTextView.setMovementMethod(new LinkMovementMethod());
    }

    @OnClick({2131296385})
    @Optional
    public void onIdentifyClick(View view) {
        if (this.device != null) {
            identify(this.device.getShortSerialNo());
        }
    }

    @OnClick({2131296391})
    public void onResetAcClick(final View view) {
        view.setEnabled(false);
        new Builder(this).setTitle((int) C0676R.string.installation_menu_resetACConfirmation_title).setMessage((int) C0676R.string.installation_menu_resetACConfirmation_description).setPositiveButton((int) C0676R.string.installation_menu_resetACConfirmation_confirmButton, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                InstallationProcessController.getInstallationProcessController().resetAcSetup(UnfinishedInstallationDetailsActivity.this, InstallationProcessController.getInstallationProcessController().getInstallationId());
                dialog.dismiss();
                view.setEnabled(true);
            }
        }).setNegativeButton((int) C0676R.string.installation_menu_resetACConfirmation_cancelButton, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                view.setEnabled(true);
            }
        }).setCancelable(false).show();
    }

    private void cleanupInstallation() {
        RestServiceGenerator.destroyHvacToolClient();
        InstallationProcessController.getInstallationProcessController().getInstallationInfo().setCurrentUninstalledDevice(null);
    }

    @OnClick({2131296396})
    public void onSuspendClick(View view) {
        view.setEnabled(false);
        cleanupInstallation();
        InstallationProcessController.getInstallationProcessController().detectStatus(this);
    }

    @OnClick({2131296389})
    public void onRemoveDeviceClick(View view) {
        removeDeviceFromHome();
    }

    private void identify(String serialNumber) {
        if (serialNumber != null) {
            RestServiceGenerator.getTadoRestService().identifyDevice(serialNumber, RestServiceGenerator.getCredentialsMap()).enqueue(new C09893());
        }
    }

    private void removeDeviceFromHome() {
        new Builder(this).setTitle((int) C0676R.string.installation_menu_removeDevice_title).setMessage(getString(C0676R.string.installation_menu_removeDevice_message, new Object[]{UserConfig.getHomeName()})).setPositiveButton((int) C0676R.string.installation_menu_removeDevice_confirmButton, new C09915()).setNegativeButton((int) C0676R.string.installation_menu_removeDevice_cancelButton, new C09904()).setCancelable(false).show();
    }

    private void confirmRemoveFromHome() {
        this.removeButton.setEnabled(false);
        InstallationProcessController.getInstallationProcessController().removeDeviceFromHome(true, this.device, new C09926());
    }

    private void showErrorMessage(String msg) {
        Snitcher.start().toCrashlytics().log("DeviceDetailsActivity", msg, new Object[0]);
        Snackbar.make(findViewById(16908290), (CharSequence) msg, 0).show();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        onBackPressed();
        return true;
    }
}
