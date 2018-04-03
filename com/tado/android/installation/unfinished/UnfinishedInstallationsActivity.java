package com.tado.android.installation.unfinished;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.tado.C0676R;
import com.tado.android.installation.ChooseProductActivity;
import com.tado.android.installation.InstallConnectorKitActivity;
import com.tado.android.installation.InstallEkOverviewActivity;
import com.tado.android.installation.InstallHeatingOverviewActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.installation.srt.common.SrtInstallationActivity;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.utils.Snitcher;
import java.util.List;

public class UnfinishedInstallationsActivity extends AppCompatActivity {
    @BindView(2131297170)
    RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_unfinished_installations);
        ButterKnife.bind((Activity) this);
        setupActionBar();
    }

    public void setupActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle((CharSequence) "");
    }

    protected void onResume() {
        super.onResume();
        init();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (upIntent == null) {
                    return false;
                }
                if (NavUtils.shouldUpRecreateTask(this, upIntent) || isTaskRoot()) {
                    TaskStackBuilder.create(this).addNextIntentWithParentStack(upIntent).startActivities();
                } else {
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick({2131297166})
    public void onAddDeviceClick(View view) {
        startActivity(new Intent(this, ChooseProductActivity.class));
    }

    private void init() {
        final List<GenericHardwareDevice> devices = InstallationProcessController.getInstallationProcessController().getInstallationInfo().getUninstalledDevices();
        UnfinishedInstallationsRecyclerViewAdapter adapter = new UnfinishedInstallationsRecyclerViewAdapter(devices, new OnClickListener() {
            public void onClick(View v) {
                int position = UnfinishedInstallationsActivity.this.recyclerView.getChildAdapterPosition(v);
                if (position == -1) {
                    Snitcher.start().toCrashlytics().log(6, UnfinishedInstallationsActivity.class.getCanonicalName(), "Trying to access a position that does not exist %s", v.toString());
                    return;
                }
                GenericHardwareDevice device = (GenericHardwareDevice) devices.get(position);
                if (device.isValve()) {
                    Intent intent = new Intent(UnfinishedInstallationsActivity.this, SrtInstallationActivity.class);
                    intent.putExtra(SrtInstallationActivity.KEY_SRT_DEVICE, device);
                    UnfinishedInstallationsActivity.this.startActivity(intent);
                    UnfinishedInstallationsActivity.this.finish();
                } else if (device.isBoilerUnit()) {
                    InstallationProcessController.startActivity(UnfinishedInstallationsActivity.this, InstallEkOverviewActivity.class, false);
                } else if (device.isRoomUnit()) {
                    InstallationProcessController.startActivity(UnfinishedInstallationsActivity.this, InstallHeatingOverviewActivity.class, false);
                } else if (device.isTemperatureSensor() || device.isOldGenerationSmartThermostat()) {
                    InstallationProcessController.startActivity(UnfinishedInstallationsActivity.this, InstallConnectorKitActivity.class, false);
                } else if (device.isCooling() || device.isGateway()) {
                    InstallationProcessController.getInstallationProcessController().handleInstallation(UnfinishedInstallationsActivity.this, InstallationProcessController.getInstallationProcessController().getInstallationInfo().getUnfinishedInstallation(device));
                }
            }
        });
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(adapter);
    }
}
