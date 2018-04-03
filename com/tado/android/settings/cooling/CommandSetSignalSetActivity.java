package com.tado.android.settings.cooling;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.tado.C0676R;
import com.tado.android.alert_dialogs.CustomDialog;
import com.tado.android.alert_dialogs.CustomDialog.CustomDialogButton;
import com.tado.android.alert_dialogs.CustomDialog.CustomDialogText;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.controllers.ZoneController;
import com.tado.android.entities.ConfirmedCommandSet;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.AcDriver;
import com.tado.android.rest.model.AcDriverTypeEnum;
import com.tado.android.rest.model.CommandConfiguration;
import com.tado.android.rest.model.DriverSelection;
import com.tado.android.rest.model.installation.CommandTypeEnum;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.rest.service.TadoApiService;
import com.tado.android.utils.UserConfig;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class CommandSetSignalSetActivity extends AppCompatActivity implements AcConfigurationUpdatePollingHelperInterface {
    public static final String KEY_DEDICATED_ON_COMMAND = "DEDICATED_ON_COMMAND";
    public static final String KEY_ZONE_CODE = "ZONE_CODE";
    private AcConfigurationUpdatePollingHelper configurationHelper;
    CustomDialog dialog;
    private List<AcDriver> listData;
    private ListView listView;
    private boolean mDedicatedOnControl;
    private int mPosition = 0;
    private Toolbar mToolbar;
    private int mZoneCode;
    private int mZoneId;
    private TadoApiService tadoApiService = RestServiceGenerator.getTadoRestService();

    class C11051 implements OnClickListener {
        C11051() {
        }

        public void onClick(View v) {
            CommandSetSignalSetActivity.this.onCloseClick();
        }
    }

    class C11062 extends TadoCallback<Void> {
        C11062() {
        }

        public void onResponse(Call<Void> call, Response<Void> response) {
            super.onResponse(call, response);
            if (!response.isSuccessful()) {
                CommandSetSignalSetActivity.this.showChangeCommandSetFailureDialog();
            } else if (CommandSetSignalSetActivity.this.configurationHelper != null) {
                CommandSetSignalSetActivity.this.configurationHelper.callDeviceCheckStatus();
            }
        }

        public void onFailure(Call<Void> call, Throwable t) {
            super.onFailure(call, t);
            CommandSetSignalSetActivity.this.showChangeCommandSetFailureDialog();
        }
    }

    class C11073 implements OnClickListener {
        C11073() {
        }

        public void onClick(View v) {
            CommandSetSignalSetActivity.this.callPutCommanadSet();
            CommandSetSignalSetActivity.this.dialog.showProgressBar(CommandSetSignalSetActivity.this.getString(C0676R.string.settings_zoneSettings_airConditioning_commandSet_updating_title));
        }
    }

    class C11084 extends TadoCallback<List<AcDriver>> {
        C11084() {
        }

        public void onResponse(Call<List<AcDriver>> call, Response<List<AcDriver>> response) {
            super.onResponse(call, response);
            if (response.isSuccessful()) {
                CommandSetSignalSetActivity.this.processConfirmedCommandSets((List) response.body());
            }
        }
    }

    class C11106 implements Comparator<AcDriver> {
        C11106() {
        }

        public int compare(AcDriver lhs, AcDriver rhs) {
            return lhs.getCommandSet().getCode().intValue() - rhs.getCommandSet().getCode().intValue();
        }
    }

    class C11117 implements OnClickListener {
        C11117() {
        }

        public void onClick(View v) {
            CommandSetSignalSetActivity.this.configurationHelper = new AcConfigurationUpdatePollingHelper(CommandSetSignalSetActivity.this.mZoneId, CommandSetSignalSetActivity.this);
            CommandSetSignalSetActivity.this.callPutCommanadSet();
            CommandSetSignalSetActivity.this.dialog.showProgressBar(CommandSetSignalSetActivity.this.getString(C0676R.string.settings_zoneSettings_airConditioning_commandSet_updating_title));
        }
    }

    class C11128 implements OnClickListener {
        C11128() {
        }

        public void onClick(View v) {
            CommandSetSignalSetActivity.this.dialog.dismiss();
        }
    }

    class C11139 implements OnClickListener {
        C11139() {
        }

        public void onClick(View v) {
            CommandSetSignalSetActivity.this.callPutCommanadSet();
            CommandSetSignalSetActivity.this.dialog.showProgressBar(CommandSetSignalSetActivity.this.getString(C0676R.string.settings_zoneSettings_airConditioning_commandSet_updating_title));
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_command_set_signal_set);
        this.mToolbar = (Toolbar) findViewById(C0676R.id.toolbar);
        this.mToolbar.setTitle((int) C0676R.string.settings_zoneSettings_airConditioning_commandSet_title);
        this.mToolbar.setNavigationIcon((int) C0676R.drawable.ic_close_white_24dp);
        setSupportActionBar(this.mToolbar);
        this.mToolbar.setNavigationOnClickListener(new C11051());
        this.listView = (ListView) findViewById(C0676R.id.command_set_list_view);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mZoneId = extras.getInt(AcSetupSettingsActivity.KEY_SELECTED_ZONE_ID);
            this.mDedicatedOnControl = extras.getBoolean(KEY_DEDICATED_ON_COMMAND);
            this.mZoneCode = extras.getInt(KEY_ZONE_CODE);
        }
    }

    protected void onResume() {
        super.onResume();
        AnalyticsHelper.trackPageView(getApplication(), Screen.COMMANDSET_LIST);
        callGetCommandSetsApi();
    }

    protected void onPause() {
        super.onPause();
        if (this.configurationHelper != null) {
            this.configurationHelper.cleanup();
        }
    }

    private void callZoneCapabilities() {
        ZoneController.INSTANCE.callZoneCapabilities(UserConfig.getCurrentZone().intValue());
    }

    private void callPutCommanadSet() {
        if (this.listData != null && !this.listData.isEmpty()) {
            CommandConfiguration configuration = new CommandConfiguration();
            ConfirmedCommandSet confirmedCommandSet = new ConfirmedCommandSet();
            confirmedCommandSet.setCommandType(CommandTypeEnum.AC_SETTING.toString());
            confirmedCommandSet.setCode(((AcDriver) this.listData.get(this.mPosition)).getCommandSet().getCode().intValue());
            configuration.setAcCommandSet(confirmedCommandSet);
            configuration.setDedicatedOnCommand(this.mDedicatedOnControl);
            DriverSelection driverSelection = new DriverSelection();
            driverSelection.setDiscriminator(((AcDriver) this.listData.get(this.mPosition)).getDiscriminator());
            this.tadoApiService.updateAcDriver(UserConfig.getHomeId(), this.mZoneId, driverSelection, RestServiceGenerator.getCredentialsMap()).enqueue(new C11062());
        }
    }

    private void showChangeCommandSetFailureDialog() {
        if (this.dialog != null) {
            this.dialog.resetLayout();
            this.dialog.setTextEnum(CustomDialogText.CUSTOM_DIALOG_ONE_TEXT_PARAGRAPH);
            this.dialog.setButtonEnum(CustomDialogButton.CUSTOM_DIALOG_ONE_BUTTON);
            this.dialog.setCancelButtonVisible(true);
            this.dialog.setTitle(getString(C0676R.string.app_name));
            this.dialog.setBodyText1(getString(C0676R.string.settings_zoneSettings_airConditioning_commandSet_errors_serverError_message));
            this.dialog.setButton1Text(getString(C0676R.string.settings_zoneSettings_airConditioning_commandSet_errors_serverError_retryButton));
            this.dialog.setButton1Listener(new C11073());
        }
    }

    private void callGetCommandSetsApi() {
        this.tadoApiService.getAcDrivers(UserConfig.getHomeId(), this.mZoneId, RestServiceGenerator.getCredentialsMap()).enqueue(new C11084());
    }

    private void processConfirmedCommandSets(List<AcDriver> acDriverList) {
        this.listData = acDriverList;
        if (this.listData != null && !this.listData.isEmpty()) {
            sortCommandSets();
            int code = this.mZoneCode;
            Iterator<AcDriver> iterator = this.listData.iterator();
            while (iterator.hasNext()) {
                if (AcDriverTypeEnum.CLOSED_LOOP_CONTROL == ((AcDriver) iterator.next()).getType()) {
                    iterator.remove();
                }
            }
            for (int i = 0; i < this.listData.size(); i++) {
                if (code == ((AcDriver) this.listData.get(i)).getCommandSet().getCode().intValue()) {
                    this.mPosition = i;
                }
            }
            final AcCommandSetArrayAdapter adapter = new AcCommandSetArrayAdapter(this, C0676R.layout.ac_command_set_list_item, this.listData, this.mPosition);
            this.listView.setAdapter(adapter);
            this.listView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    adapter.setSelected(position);
                    CommandSetSignalSetActivity.this.mPosition = position;
                }
            });
        }
    }

    private void sortCommandSets() {
        Collections.sort(this.listData, new C11106());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0676R.menu.menu_action_save, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0676R.id.action_save) {
            return super.onOptionsItemSelected(item);
        }
        showUpdateCommandSetDialog();
        return true;
    }

    private void showUpdateCommandSetDialog() {
        this.dialog = new CustomDialog(this, CustomDialogText.CUSTOM_DIALOG_ONE_TEXT_PARAGRAPH, CustomDialogButton.CUSTOM_DIALOG_ONE_BUTTON);
        this.dialog.setCancelable(false);
        this.dialog.setTitle(getString(C0676R.string.settings_zoneSettings_airConditioning_commandSet_confirm_title));
        this.dialog.setBodyText1(getString(C0676R.string.settings_zoneSettings_airConditioning_commandSet_confirm_descriptionLabel));
        this.dialog.setButton1Text(getString(C0676R.string.settings_zoneSettings_airConditioning_commandSet_confirm_confirmButton));
        this.dialog.setButton1Listener(new C11117());
        this.dialog.setCancelButtonListener(new C11128());
        this.dialog.show();
    }

    private void onCloseClick() {
        onBackPressed();
    }

    public void onSuccess() {
        if (this.dialog != null) {
            this.dialog.dismiss();
        }
        onBackPressed();
    }

    public void onFailed() {
        if (this.dialog != null) {
            this.dialog.resetLayout();
            this.dialog.setTextEnum(CustomDialogText.CUSTOM_DIALOG_ONE_TEXT_PARAGRAPH);
            this.dialog.setButtonEnum(CustomDialogButton.CUSTOM_DIALOG_ONE_BUTTON);
            this.dialog.setCancelButtonVisible(false);
            this.dialog.setTitle(getString(C0676R.string.app_name));
            this.dialog.setBodyText1(getString(C0676R.string.settings_zoneSettings_airConditioning_commandSet_errors_deviceUpdateFailed_message));
            this.dialog.setButton1Text(getString(C0676R.string.settings_zoneSettings_airConditioning_commandSet_errors_deviceUpdateFailed_retryButton));
            this.dialog.setButton1Listener(new C11139());
        }
    }

    public void onInProgress() {
        this.dialog.showProgressBar(getString(C0676R.string.settings_zoneSettings_airConditioning_commandSet_updating_title));
    }

    public void onBackPressed() {
        if (this.configurationHelper == null || !(this.configurationHelper == null || this.configurationHelper.isUpdateInProgress())) {
            super.onBackPressed();
        }
    }
}
