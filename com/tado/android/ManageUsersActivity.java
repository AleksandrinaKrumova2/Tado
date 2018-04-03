package com.tado.android;

import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.v4.content.ContextCompat;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.crashlytics.android.Crashlytics;
import com.tado.C0676R;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.app.TadoApplication;
import com.tado.android.controllers.ZoneController;
import com.tado.android.dialogs.AlertChoiceDialogListener;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.location.PermissionFeatureIntroActivity;
import com.tado.android.rest.callback.RetryCallback;
import com.tado.android.rest.callback.presenters.GeneralErrorAlertPresenter;
import com.tado.android.rest.model.LegacyMobileDevice;
import com.tado.android.rest.model.MobileDevice;
import com.tado.android.rest.model.MobileDeviceDeviceMetadata;
import com.tado.android.rest.model.MobileDeviceInput;
import com.tado.android.rest.model.MobileDeviceMetadata;
import com.tado.android.rest.model.MobileDeviceSettings;
import com.tado.android.rest.model.MobileDeviceTadoAppMetadata;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.settings.model.PresenceDetectionSettings;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import com.tado.android.views.RegisteredUsers;
import com.tado.android.views.RegisteredUsersOnClickListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.UUID;
import retrofit2.Call;
import retrofit2.Response;

public class ManageUsersActivity extends AppCompatActivity implements RegisteredUsersOnClickListener {
    private Button createUser;
    private ArrayList<MobileDevice> mMobileDevices = new ArrayList();
    private EditText nicknameInput;

    class C06861 implements OnClickListener {
        C06861() {
        }

        public void onClick(View v) {
            ManageUsersActivity.this.proceedClick(v);
        }
    }

    class C06872 implements TextWatcher {
        C06872() {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (ManageUsersActivity.this.findMobileUser(s.toString().trim())) {
                ManageUsersActivity.this.nicknameInput.setTextColor(ContextCompat.getColor(ManageUsersActivity.this.nicknameInput.getContext(), C0676R.color.holo_red_light));
                ManageUsersActivity.this.createUser.setText(ManageUsersActivity.this.getString(C0676R.string.signin_deviceName_claimButton));
                return;
            }
            ManageUsersActivity.this.nicknameInput.setTextColor(ContextCompat.getColor(ManageUsersActivity.this.nicknameInput.getContext(), 17170444));
            ManageUsersActivity.this.createUser.setText(ManageUsersActivity.this.getString(C0676R.string.signin_deviceName_createButton));
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void afterTextChanged(Editable s) {
        }
    }

    class C06905 implements AlertChoiceDialogListener {
        C06905() {
        }

        public void OnOKClicked() {
            String nickname = ManageUsersActivity.this.nicknameInput.getText().toString().trim();
            UserConfig.setMobileDeviceId(ManageUsersActivity.this.getId(nickname));
            ManageUsersActivity.this.mobileDeviceCreated(ManageUsersActivity.this.createMobileDeviceInput(nickname), UserConfig.getMobileDeviceId(), nickname);
        }

        public void OnCancelClicked() {
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_manage_users);
        if (getIntent().getParcelableArrayListExtra("usersArray") != null) {
            this.mMobileDevices = getIntent().getParcelableArrayListExtra("usersArray");
        }
        AnalyticsHelper.trackEvent(getApplication(), Screen.DEVICE_NAME, "NumberOfDevices", Long.valueOf((long) this.mMobileDevices.size()));
        if (this.mMobileDevices != null) {
            Collections.sort(this.mMobileDevices);
        }
        if (TadoApplication.isInternal()) {
            skipInteraction();
        }
        RegisteredUsers registeredUsers = new RegisteredUsers(this, (FrameLayout) findViewById(C0676R.id.present_users_content_holder), this.mMobileDevices, this);
        try {
            ((TextView) findViewById(C0676R.id.title_bar_textview)).setText(getString(C0676R.string.signin_deviceName_title));
            ((TextView) findViewById(C0676R.id.title_template_textview)).setText(getString(C0676R.string.signin_deviceName_promptLabel));
            ((ImageView) findViewById(C0676R.id.center_image)).setImageResource(C0676R.drawable.devices);
        } catch (NullPointerException e) {
            Crashlytics.logException(e);
        }
        this.createUser = (Button) findViewById(C0676R.id.proceed_button);
        this.createUser.setText(getString(C0676R.string.signin_deviceName_createButton));
        this.createUser.setOnClickListener(new C06861());
        this.nicknameInput = (EditText) findViewById(C0676R.id.etName);
        this.nicknameInput.addTextChangedListener(new C06872());
    }

    private void skipInteraction() {
        String android_id = Secure.getString(getContentResolver(), "android_id");
        if (android_id == null) {
            android_id = UUID.randomUUID().toString();
        }
        String nickname = String.format("%s %s", new Object[]{Build.MODEL, android_id});
        if (findMobileUser(nickname)) {
            UserConfig.setMobileDeviceId(getId(nickname));
            mobileDeviceCreated(createMobileDeviceInput(nickname), UserConfig.getMobileDeviceId(), nickname);
            return;
        }
        createMobileDevice(createMobileDeviceInput(nickname));
    }

    private boolean findMobileUser(String nickname) {
        return Collections.binarySearch(this.mMobileDevices, new MobileDevice(nickname)) > -1;
    }

    protected void onResume() {
        super.onResume();
        AnalyticsHelper.trackPageView(getApplication(), Screen.DEVICE_NAME);
    }

    public void proceedClick(View view) {
        if (this.createUser.getText().toString().equals(getString(C0676R.string.signin_deviceName_createButton))) {
            AnalyticsHelper.trackEvent(getApplication(), Screen.DEVICE_NAME, "DeviceNameExists", String.valueOf(false));
            createMobileDevice(createMobileDeviceInput(this.nicknameInput.getText().toString().trim()));
        } else if (this.createUser.getText().toString().equals(getString(C0676R.string.signin_deviceName_claimButton))) {
            AnalyticsHelper.trackEvent(getApplication(), Screen.DEVICE_NAME, "DeviceNameExists", String.valueOf(true));
            showClaimNotification();
        }
    }

    private void createMobileDevice(final MobileDeviceInput mobileDeviceInput) {
        RestServiceGenerator.getTadoRestService().createMobileDevice(UserConfig.getHomeId(), mobileDeviceInput, RestServiceGenerator.getCredentialsMap()).enqueue(new RetryCallback<MobileDevice>(new GeneralErrorAlertPresenter(this)) {
            public void onResponse(Call<MobileDevice> call, Response<MobileDevice> response) {
                if (response.isSuccessful()) {
                    UserConfig.setNickname(((MobileDevice) response.body()).getName());
                    ManageUsersActivity.this.mobileDeviceCreated(mobileDeviceInput, ((MobileDevice) response.body()).getId(), ManageUsersActivity.this.nicknameInput.getText().toString().trim());
                    UserConfig.setMobileDeviceId(((MobileDevice) response.body()).getId());
                }
                super.onResponse(call, response);
            }
        });
    }

    private void mobileDeviceCreated(MobileDeviceInput mobileDeviceInput, int mobileDeviceId, final String nickname) {
        RestServiceGenerator.getTadoRestService().createMobileDeviceWithLegacyAuth(UserConfig.getHomeId(), mobileDeviceId, mobileDeviceInput, RestServiceGenerator.getCredentialsMap()).enqueue(new RetryCallback<LegacyMobileDevice>(new GeneralErrorAlertPresenter(this)) {
            public void onResponse(Call<LegacyMobileDevice> call, Response<LegacyMobileDevice> response) {
                if (response.isSuccessful()) {
                    UserConfig.setUsername(((LegacyMobileDevice) response.body()).getUsername());
                    UserConfig.setPassword(((LegacyMobileDevice) response.body()).getPassword());
                    UserConfig.setNickname(nickname);
                    ZoneController.INSTANCE.callGetZoneList();
                    ManageUsersActivity.this.navigateNext();
                }
                super.onResponse(call, response);
            }
        });
    }

    private void navigateNext() {
        Intent intent;
        if (getResources().getBoolean(C0676R.bool.isTablet)) {
            PresenceDetectionSettings.updatePresenceDetectionSetting(false, null);
        }
        if (getResources().getBoolean(C0676R.bool.isTablet) || UserConfig.isPermissionIntroShown().booleanValue()) {
            intent = new Intent(this, MainZoneActivity.class);
            overridePendingTransition(17432576, 17432577);
        } else {
            intent = new Intent(this, PermissionFeatureIntroActivity.class);
        }
        intent.addFlags(268435456);
        startActivity(intent);
        finish();
    }

    public void OnClickListener(MobileDevice mobileDevice) {
        this.nicknameInput.setText(mobileDevice.getName());
    }

    private void showClaimNotification() {
        AlertDialogs.showChoiceAlert("", getString(C0676R.string.signin_deviceName_claimDeviceConfirmation_message), getString(C0676R.string.signin_deviceName_claimDeviceConfirmation_confirmButton), getString(C0676R.string.signin_deviceName_claimDeviceConfirmation_cancelButton), this, new C06905());
    }

    private MobileDeviceInput createMobileDeviceInput(String nickname) {
        MobileDeviceInput mobileDeviceInput = new MobileDeviceInput();
        mobileDeviceInput.setName(nickname);
        mobileDeviceInput.setSettings(new MobileDeviceSettings(true));
        MobileDeviceMetadata metadata = new MobileDeviceMetadata();
        MobileDeviceDeviceMetadata device = new MobileDeviceDeviceMetadata();
        device.setModel(Util.getDeviceName());
        device.setPlatform("Android");
        device.setOsVersion(VERSION.RELEASE);
        device.setLocale(Util.getSupportedLanguage());
        metadata.setDevice(device);
        MobileDeviceTadoAppMetadata tadoApp = new MobileDeviceTadoAppMetadata();
        try {
            tadoApp.setVersion(getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
        } catch (NameNotFoundException e) {
            tadoApp.setVersion(EnvironmentCompat.MEDIA_UNKNOWN);
            e.printStackTrace();
        }
        metadata.setTadoApp(tadoApp);
        mobileDeviceInput.setMetadata(metadata);
        return mobileDeviceInput;
    }

    private int getId(String name) {
        if (name == null || name.isEmpty()) {
            return -1;
        }
        Iterator it = this.mMobileDevices.iterator();
        while (it.hasNext()) {
            MobileDevice mobileDevice = (MobileDevice) it.next();
            if (name.equalsIgnoreCase(mobileDevice.getName())) {
                return mobileDevice.getId();
            }
        }
        return -1;
    }
}
