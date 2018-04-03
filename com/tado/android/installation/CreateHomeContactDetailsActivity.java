package com.tado.android.installation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.entities.Address;
import com.tado.android.entities.HomeSignup;
import com.tado.android.entities.Location;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.HomeInfo;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.Constants;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import retrofit2.Call;
import retrofit2.Response;

public class CreateHomeContactDetailsActivity extends AppCompatActivity {
    public static final String INTENT_ADDRESS1 = "address1";
    public static final String INTENT_ADDRESS2 = "address2";
    public static final String INTENT_CITY = "city";
    public static final String INTENT_COUNTRY = "country";
    public static final String INTENT_EMAIL = "email";
    public static final String INTENT_HOME_NAME = "homeName";
    public static final String INTENT_LATITUDE = "latitude";
    public static final String INTENT_LONGITUDE = "longitude";
    public static final String INTENT_NAME = "name";
    public static final String INTENT_PHONE = "phone";
    public static final String INTENT_ZIPCODE = "zipcode";
    private String homeName;
    EditText mailInput;
    EditText nameInput;
    EditText phoneInput;

    class C07971 implements TextWatcher {
        C07971() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {
            String phoneString = s.toString();
            if (phoneString.isEmpty() || Util.isValidPhone(phoneString)) {
                CreateHomeContactDetailsActivity.this.phoneInput.setError(null);
            } else {
                CreateHomeContactDetailsActivity.this.phoneInput.setError(CreateHomeContactDetailsActivity.this.getString(C0676R.string.createHome_contactDetails_errors_phoneFieldInvalidPhoneError));
            }
        }
    }

    class C07982 extends TadoCallback<HomeInfo> {
        C07982() {
        }

        public void onResponse(Call<HomeInfo> call, Response<HomeInfo> response) {
            super.onResponse(call, response);
            if (response.isSuccessful()) {
                HomeInfo home = (HomeInfo) response.body();
                UserConfig.setHomeName(home.getName());
                UserConfig.setHomeTimezone(home.getDateTimeZone());
                UserConfig.setHomeId(home.getId());
                UserConfig.setPartner(home.getPartner());
                InstallationProcessController.getInstallationProcessController().detectStatus(CreateHomeContactDetailsActivity.this);
            } else if (this.serverError != null) {
                String code = this.serverError.getCode();
                Object obj = -1;
                switch (code.hashCode()) {
                    case 1280441566:
                        if (code.equals(Constants.SERVER_ERROR_MAIL_NOT_UNIQUE)) {
                            obj = null;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case null:
                        if (CreateHomeContactDetailsActivity.this.mailInput != null) {
                            CreateHomeContactDetailsActivity.this.mailInput.setError(CreateHomeContactDetailsActivity.this.getString(C0676R.string.createHome_contactDetails_errors_emailFieldAlreadyInUseError));
                            return;
                        }
                        return;
                    default:
                        InstallationProcessController.handleError(CreateHomeContactDetailsActivity.this, this.serverError, (Call) call, response.code());
                        return;
                }
            }
        }

        public void onFailure(Call<HomeInfo> call, Throwable t) {
            super.onFailure(call, t);
            InstallationProcessController.showConnectionErrorRetrofit(CreateHomeContactDetailsActivity.this, call, this);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_create_home_contact_details);
        ((TextView) findViewById(C0676R.id.title_bar_textview)).setText(getString(C0676R.string.createHome_title));
        ((Button) findViewById(C0676R.id.proceed_button)).setText(getString(C0676R.string.createHome_contactDetails_confirmButton));
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString(INTENT_NAME);
        this.nameInput = (EditText) findViewById(C0676R.id.create_home_contact_name);
        this.nameInput.setText(name);
        String email = bundle.getString("email");
        this.mailInput = (EditText) findViewById(C0676R.id.create_home_contact_email);
        this.mailInput.setText(email);
        this.homeName = bundle.getString(INTENT_HOME_NAME, "");
        TextView contactsTitle = (TextView) findViewById(C0676R.id.title_template_textview);
        if (TextUtils.isEmpty(this.homeName)) {
            contactsTitle.setVisibility(8);
        } else {
            contactsTitle.setText(getString(C0676R.string.createHome_contactDetails_message, new Object[]{this.homeName}));
        }
        this.phoneInput = (EditText) findViewById(C0676R.id.create_home_contact_phone);
        String phoneNumber = bundle.getString(INTENT_PHONE, "");
        if (!TextUtils.isEmpty(phoneNumber)) {
            this.phoneInput.setText(phoneNumber);
        }
        this.phoneInput.addTextChangedListener(new C07971());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0676R.menu.menu_create_home_contact_details, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == C0676R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void proceedClick(View view) {
        boolean validInputs = true;
        String nameString = this.nameInput.getText().toString();
        if (nameString.isEmpty() || nameString.indexOf(" ") < 0 || nameString.indexOf(" ") == nameString.length() - 1) {
            validInputs = false;
            this.nameInput.setError(getString(C0676R.string.createHome_contactDetails_errors_nameFieldInvalidError));
        }
        String mailString = this.mailInput.getText().toString();
        if (mailString.isEmpty()) {
            validInputs = false;
            this.mailInput.setError(getString(C0676R.string.createHome_contactDetails_errors_emailFieldEmptyError));
        } else if (!Util.isValidEmail(mailString)) {
            validInputs = false;
            this.mailInput.setError(getString(C0676R.string.createHome_contactDetails_errors_emailFieldInvalidEmailError));
        }
        EditText phoneInput = (EditText) findViewById(C0676R.id.create_home_contact_phone);
        String phoneString = phoneInput.getText().toString();
        if (phoneString.isEmpty()) {
            validInputs = false;
            phoneInput.setError(getString(C0676R.string.createHome_contactDetails_errors_phoneFieldEmptyError));
        } else if (!Util.isValidPhone(phoneString)) {
            validInputs = false;
            phoneInput.setError(getString(C0676R.string.createHome_contactDetails_errors_phoneFieldInvalidPhoneError));
        }
        if (validInputs) {
            proceed(nameString, phoneString, mailString);
        }
    }

    protected void proceed(String nameString, String phoneString, String mailString) {
        Bundle bundle = getIntent().getExtras();
        String address1 = bundle.getString(INTENT_ADDRESS1);
        String address2 = bundle.getString(INTENT_ADDRESS2);
        String zipcode = bundle.getString(INTENT_ZIPCODE);
        String city = bundle.getString(INTENT_CITY);
        String country = bundle.getString(INTENT_COUNTRY);
        Address address = new Address();
        address.setAddressLine1(address1);
        address.setAddressLine2(address2);
        address.setZipCode(zipcode);
        address.setCity(city);
        address.setCountry(country);
        address.setName(nameString);
        address.setPhone(phoneString);
        address.setEmail(mailString);
        HomeSignup homeSignup = new HomeSignup();
        homeSignup.setAddress(address);
        homeSignup.setName(this.homeName);
        homeSignup.setGeolocation(new Location((double) Float.valueOf(bundle.getString("latitude")).floatValue(), (double) Float.valueOf(bundle.getString("longitude")).floatValue()));
        RestServiceGenerator.getTadoRestService().createHome(homeSignup, RestServiceGenerator.getCredentialsMap()).enqueue(new C07982());
    }
}
