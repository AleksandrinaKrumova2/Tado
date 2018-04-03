package com.tado.android.installation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.client.APICall;
import com.tado.android.client.APICallListener;
import com.tado.android.client.LocalAPICall;
import com.tado.android.dialogs.AlertChoiceDialogListener;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.entities.GLocation;
import com.tado.android.entities.GeocodingResponse;
import com.tado.android.requests.GetGoogleGeocodingRequest;
import com.tado.android.responses.GetGoogleGeocodingResponse;
import com.tado.android.responses.Response;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserInterfaceUtilsKt;
import com.tado.android.views.progressbar.ProgressBarComponent;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class CreateHomeEnterAddressActivity extends AppCompatActivity implements APICallListener {
    private static final float CITY_LEVEL = 10.0f;
    private static final float COUNTRY_LEVEL = 5.0f;
    protected static final String INTENT_ADDRESS_1 = "address1";
    protected static final String INTENT_ADDRESS_2 = "address2";
    protected static final String INTENT_CITY = "city";
    protected static final String INTENT_COUNTRY = "country";
    protected static final String INTENT_ZIP_CODE = "zipCode";
    private static final int MAP_CIRCLE_RADIUS = 150;
    private static final int MAP_CIRCLE_STROKE_WIDTH = 4;
    private static final int REQUEST_CODE = 1;
    private static final float STREET_LEVEL = 16.0f;
    private static final String TAG = CreateHomeEnterAddressActivity.class.getSimpleName();
    private static Handler geoCoderHandler;
    private static GeoCoderRunnable geoCoderRunnable;
    @BindView(2131296690)
    EditText address1;
    @BindView(2131296691)
    EditText address2;
    private Address addressFromLocation = null;
    private GoogleApiClient apiClient;
    @BindView(2131296692)
    EditText city;
    private ArrayList<String> countriesList;
    private final HashMap<String, String> countriesMap = new HashMap();
    @BindView(2131296962)
    Button country;
    boolean countryPrefilledOrChangedByUser = false;
    @BindView(2131296557)
    TextView determinedLocation;
    private boolean errorUsingGeocoder = false;
    private Location locationFromEnteredAdress;
    @BindView(2131296725)
    LinearLayout locationLayout;
    private Location locationOnMap;
    private GoogleMap mMap;
    @BindView(2131296870)
    Button proceedButton;
    @BindView(2131296880)
    ProgressBarComponent progressBar;
    @BindView(2131297146)
    TextView titleBar;
    @BindView(2131297148)
    TextView titleTemplate;
    @BindView(2131297149)
    Toolbar toolbar;
    @BindView(2131297196)
    Spinner usernameSpinner;
    @BindView(2131296694)
    EditText zipcode;

    class C07991 implements OnMapReadyCallback {
        C07991() {
        }

        public void onMapReady(GoogleMap googleMap) {
            CreateHomeEnterAddressActivity.this.mMap = googleMap;
            CreateHomeEnterAddressActivity.this.mMap.getUiSettings().setZoomControlsEnabled(false);
            CreateHomeEnterAddressActivity.this.mMap.getUiSettings().setScrollGesturesEnabled(false);
            CreateHomeEnterAddressActivity.this.mMap.getUiSettings().setRotateGesturesEnabled(false);
            CreateHomeEnterAddressActivity.this.mMap.getUiSettings().setZoomGesturesEnabled(false);
            CreateHomeEnterAddressActivity.this.mMap.setMapType(1);
            if (ContextCompat.checkSelfPermission(CreateHomeEnterAddressActivity.this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(CreateHomeEnterAddressActivity.this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                CreateHomeEnterAddressActivity.this.onPermissionGranted();
            } else if (!ActivityCompat.shouldShowRequestPermissionRationale(CreateHomeEnterAddressActivity.this, "android.permission.ACCESS_FINE_LOCATION")) {
                ActivityCompat.requestPermissions(CreateHomeEnterAddressActivity.this, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 0);
            }
        }
    }

    class C08002 implements OnConnectionFailedListener {
        C08002() {
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        }
    }

    class C08013 implements ConnectionCallbacks {
        C08013() {
        }

        public void onConnected(@Nullable Bundle bundle) {
            CreateHomeEnterAddressActivity.this.determineLocation();
        }

        public void onConnectionSuspended(int i) {
        }
    }

    class C08024 implements OnMyLocationButtonClickListener {
        C08024() {
        }

        public boolean onMyLocationButtonClick() {
            CreateHomeEnterAddressActivity.this.determineLocation();
            return false;
        }
    }

    class C08035 implements OnMapReadyCallback {
        C08035() {
        }

        public void onMapReady(GoogleMap googleMap) {
            CreateHomeEnterAddressActivity.this.mMap = googleMap;
            CreateHomeEnterAddressActivity.this.setUpMap();
        }
    }

    class C08046 implements AlertChoiceDialogListener {
        C08046() {
        }

        public void OnOKClicked() {
            CreateHomeEnterAddressActivity.this.proceedClick(null);
        }

        public void OnCancelClicked() {
        }
    }

    private enum AddressResult {
        IO_EXCEPTION,
        ILLEGAL_ARGUMENTS_EXCEPTION,
        OK,
        NO_ADDRESS
    }

    private static class GeoCoderRunnable implements Runnable {
        WeakReference<Context> context;
        String finalLocation;
        private final GetLocationTask task;

        GeoCoderRunnable(String location, GetLocationTask task, Context context) {
            this.finalLocation = location;
            this.task = task;
            this.context = new WeakReference(context);
        }

        public void run() {
            this.task.execute(new Object[]{this.finalLocation, this.context.get()});
        }
    }

    private class GetAddressTask extends AsyncTask<Location, Void, GetAddressTaskResult> {
        WeakReference<ProgressDialog> progressDialog;

        GetAddressTask(ProgressDialog progressDialog) {
            this.progressDialog = new WeakReference(progressDialog);
        }

        protected void onPreExecute() {
            super.onPreExecute();
            if (this.progressDialog.get() != null) {
                ((ProgressDialog) this.progressDialog.get()).show();
            }
        }

        protected GetAddressTaskResult doInBackground(Location... params) {
            Geocoder geocoder = new Geocoder(TadoApplication.getTadoAppContext(), Locale.getDefault());
            Location location = params[0];
            try {
                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if (addresses == null || addresses.size() <= 0) {
                    return new GetAddressTaskResult(null, AddressResult.NO_ADDRESS);
                }
                return new GetAddressTaskResult((Address) addresses.get(0), AddressResult.OK);
            } catch (IOException e) {
                Snitcher.start().toCrashlytics().logException(CreateHomeEnterAddressActivity.TAG, e);
                return new GetAddressTaskResult(null, AddressResult.IO_EXCEPTION);
            } catch (IllegalArgumentException e2) {
                Snitcher.start().toCrashlytics().logException(CreateHomeEnterAddressActivity.TAG, e2);
                return new GetAddressTaskResult(null, AddressResult.ILLEGAL_ARGUMENTS_EXCEPTION);
            }
        }

        protected void onPostExecute(GetAddressTaskResult result) {
            super.onPostExecute(result);
            if (this.progressDialog.get() != null) {
                ((ProgressDialog) this.progressDialog.get()).dismiss();
            }
            Address address = result.getAddress();
            if (result.getStatus() != AddressResult.OK || address == null) {
                CreateHomeEnterAddressActivity.this.addressFromLocation = null;
                Toast.makeText(CreateHomeEnterAddressActivity.this, CreateHomeEnterAddressActivity.this.getString(C0676R.string.createHome_homeAddress_myLocation_errors_noAddressForLocationError), 0).show();
                return;
            }
            CreateHomeEnterAddressActivity.this.addressFromLocation = address;
            String addressString = "";
            if (CreateHomeEnterAddressActivity.this.addressFromLocation.getMaxAddressLineIndex() > 0) {
                addressString = addressString + CreateHomeEnterAddressActivity.this.addressFromLocation.getAddressLine(0);
            } else if (CreateHomeEnterAddressActivity.this.addressFromLocation.getMaxAddressLineIndex() == 0 && CreateHomeEnterAddressActivity.this.addressFromLocation.getAddressLine(0).contains(",")) {
                addressString = addressString + CreateHomeEnterAddressActivity.this.addressFromLocation.getAddressLine(0).split(",")[0];
            }
            addressString = ((((((addressString + (CreateHomeEnterAddressActivity.this.addressFromLocation.getMaxAddressLineIndex() > 0 ? CreateHomeEnterAddressActivity.this.addressFromLocation.getAddressLine(0) : "")) + ", ") + CreateHomeEnterAddressActivity.this.addressFromLocation.getPostalCode()) + " ") + CreateHomeEnterAddressActivity.this.addressFromLocation.getLocality()) + ", ") + CreateHomeEnterAddressActivity.this.addressFromLocation.getCountryName();
            if (!CreateHomeEnterAddressActivity.this.countryPrefilledOrChangedByUser && CreateHomeEnterAddressActivity.this.countriesMap.containsKey(CreateHomeEnterAddressActivity.this.addressFromLocation.getCountryName())) {
                CreateHomeEnterAddressActivity.this.country.setText(CreateHomeEnterAddressActivity.this.addressFromLocation.getCountryName());
            }
            ((TextView) CreateHomeEnterAddressActivity.this.findViewById(C0676R.id.determined_location)).setText(addressString);
            CreateHomeEnterAddressActivity.this.locationLayout.setVisibility(0);
        }
    }

    private class GetAddressTaskResult {
        private Address address;
        private AddressResult status;

        GetAddressTaskResult(Address address, AddressResult status) {
            this.address = address;
            this.status = status;
        }

        public Address getAddress() {
            return this.address;
        }

        public AddressResult getStatus() {
            return this.status;
        }
    }

    private class GetLocationTask extends AsyncTask<Object, Void, GetAddressTaskResult> {
        private GetLocationTask() {
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected GetAddressTaskResult doInBackground(Object... params) {
            try {
                List<Address> addresses = new Geocoder((Context) params[1], Locale.getDefault()).getFromLocationName(params[0], 1);
                if (addresses == null || addresses.size() <= 0) {
                    return new GetAddressTaskResult(null, AddressResult.NO_ADDRESS);
                }
                return new GetAddressTaskResult((Address) addresses.get(0), AddressResult.OK);
            } catch (IOException e) {
                Snitcher.start().toCrashlytics().logException(CreateHomeEnterAddressActivity.TAG, e);
                return new GetAddressTaskResult(null, AddressResult.IO_EXCEPTION);
            }
        }

        protected void onPostExecute(GetAddressTaskResult result) {
            super.onPostExecute(result);
            Address address = result.getAddress();
            if (result.getStatus() == AddressResult.OK && address != null) {
                if (CreateHomeEnterAddressActivity.this.locationFromEnteredAdress == null) {
                    CreateHomeEnterAddressActivity.this.locationFromEnteredAdress = new Location("");
                }
                CreateHomeEnterAddressActivity.this.locationFromEnteredAdress.setLatitude(address.getLatitude());
                CreateHomeEnterAddressActivity.this.locationFromEnteredAdress.setLongitude(address.getLongitude());
                CreateHomeEnterAddressActivity.this.locationOnMap = CreateHomeEnterAddressActivity.this.locationFromEnteredAdress;
                CreateHomeEnterAddressActivity.this.setUpMapIfNeeded();
            } else if (result.getStatus() == AddressResult.IO_EXCEPTION) {
                CreateHomeEnterAddressActivity.this.errorUsingGeocoder = true;
            }
            CreateHomeEnterAddressActivity.this.proceedButton.setEnabled(true);
        }
    }

    private class TextChangeWatcher implements TextWatcher {
        private EditText editText;

        TextChangeWatcher(EditText editText) {
            this.editText = editText;
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            this.editText.setError(null);
            CreateHomeEnterAddressActivity.this.userHasEditedLocation();
        }

        public void afterTextChanged(Editable s) {
        }
    }

    protected void initTexts(String title, String addressTitle, String proceedButtonText) {
        try {
            UserInterfaceUtilsKt.setTextOrHide(this.titleBar, title);
            UserInterfaceUtilsKt.setTextOrHide(this.titleTemplate, addressTitle);
            UserInterfaceUtilsKt.setTextOrHide(this.proceedButton, proceedButtonText);
            this.toolbar.setVisibility(this.titleBar.getVisibility());
        } catch (Exception e) {
            Crashlytics.logException(e);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_create_home_enter_address);
        ButterKnife.bind((Activity) this);
        initTexts(getString(C0676R.string.createHome_title), getString(C0676R.string.createHome_homeAddress_enterAddressLabel), getString(C0676R.string.createHome_homeAddress_nextButton));
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(C0676R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(new C07991());
        }
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.address1.addTextChangedListener(new TextChangeWatcher(this.address1));
        this.address2.addTextChangedListener(new TextChangeWatcher(this.address1));
        this.zipcode.addTextChangedListener(new TextChangeWatcher(this.address1));
        this.city.addTextChangedListener(new TextChangeWatcher(this.address1));
        this.address1.setText(bundle.getString("address1", ""));
        this.address2.setText(bundle.getString("address2", ""));
        this.zipcode.setText(bundle.getString(INTENT_ZIP_CODE, ""));
        this.city.setText(bundle.getString("city", ""));
        initCountriesWithISO3Country(bundle.getString("country", ""));
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == 0) {
            onPermissionGranted();
        }
    }

    private void onPermissionGranted() {
        this.apiClient = new Builder(this).addApi(LocationServices.API).addConnectionCallbacks(new C08013()).addOnConnectionFailedListener(new C08002()).build();
        this.apiClient.connect();
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            this.mMap.setMyLocationEnabled(true);
        }
        this.mMap.setOnMyLocationButtonClickListener(new C08024());
    }

    private void determineLocation() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            Location lastSeenLocation = LocationServices.FusedLocationApi.getLastLocation(this.apiClient);
            if (lastSeenLocation != null) {
                this.locationOnMap = lastSeenLocation;
                setUpMapIfNeeded();
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage(getResources().getString(C0676R.string.createHome_homeAddress_myLocation_loadingLabel));
                progressDialog.setCanceledOnTouchOutside(false);
                new GetAddressTask(progressDialog).execute(new Location[]{this.locationOnMap});
            }
        }
    }

    private void setUpMapIfNeeded() {
        if (this.mMap == null) {
            ((SupportMapFragment) getSupportFragmentManager().findFragmentById(C0676R.id.map)).getMapAsync(new C08035());
        } else {
            updateMap();
        }
    }

    private void setUpMap() {
        this.mMap.getUiSettings().setZoomControlsEnabled(false);
        this.mMap.getUiSettings().setScrollGesturesEnabled(false);
        this.mMap.setMapType(1);
        updateMap();
    }

    private void updateMap() {
        this.mMap.clear();
        MarkerOptions marker = new MarkerOptions();
        if (this.locationOnMap == null && (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0)) {
            this.mMap.setMyLocationEnabled(true);
            this.locationOnMap = LocationServices.FusedLocationApi.getLastLocation(this.apiClient);
        }
        if (this.locationOnMap != null) {
            LatLng position = new LatLng(this.locationOnMap.getLatitude(), this.locationOnMap.getLongitude());
            marker.position(position);
            marker.icon(BitmapDescriptorFactory.defaultMarker());
            CircleOptions circleOptions = new CircleOptions().center(position).radius(150.0d).fillColor(ContextCompat.getColor(this, C0676R.color.map_circle_fill)).strokeColor(ContextCompat.getColor(this, C0676R.color.map_circle_stroke)).strokeWidth(4.0f);
            this.mMap.addMarker(marker);
            this.mMap.addCircle(circleOptions);
            this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, getMapZoom()));
        }
    }

    private void userHasEditedLocation() {
        this.locationFromEnteredAdress = null;
        String location = this.address1.getText().toString() + ", " + this.address2.getText().toString() + ", " + this.zipcode.getText().toString() + ", " + this.city.getText().toString();
        String countryString = this.country.getText().toString();
        if (countryString.compareTo(getString(C0676R.string.createHome_homeAddress_countryField)) != 0) {
            location = location + ", " + countryString;
        }
        if (Geocoder.isPresent()) {
            if (geoCoderHandler == null || geoCoderRunnable == null) {
                geoCoderHandler = new Handler(Looper.getMainLooper());
            } else {
                geoCoderHandler.removeCallbacks(geoCoderRunnable);
            }
            this.proceedButton.setEnabled(false);
            geoCoderRunnable = new GeoCoderRunnable(location, new GetLocationTask(), this);
            geoCoderHandler.postDelayed(geoCoderRunnable, 100);
        }
    }

    protected void onStop() {
        if (!(geoCoderHandler == null || geoCoderRunnable == null)) {
            geoCoderHandler.removeCallbacks(geoCoderRunnable);
        }
        super.onStop();
    }

    public void prefillLocationData(View view) {
        if (this.addressFromLocation != null) {
            if (this.addressFromLocation.getMaxAddressLineIndex() > 0) {
                this.address1.setText(this.addressFromLocation.getAddressLine(0));
            } else if (this.addressFromLocation.getMaxAddressLineIndex() == 0 && this.addressFromLocation.getAddressLine(0).contains(",")) {
                this.address1.setText(this.addressFromLocation.getAddressLine(0).split(",")[0]);
            } else {
                this.address1.setText("");
            }
            this.city.setText(this.addressFromLocation.getLocality());
            initCountries(this.addressFromLocation.getCountryName());
            this.zipcode.setText(this.addressFromLocation.getPostalCode());
            this.locationLayout.setVisibility(8);
        }
    }

    private void initCountriesMap() {
        String[] countries = Locale.getISOCountries();
        this.countriesList = new ArrayList();
        for (String countryTwoLetterCode : countries) {
            Locale locale = new Locale("", countryTwoLetterCode);
            this.countriesList.add(locale.getDisplayCountry());
            this.countriesMap.put(locale.getDisplayCountry(), locale.getISO3Country());
        }
    }

    private void initCountries(String defaultCountry) {
        initCountriesMap();
        if (this.countriesMap.containsKey(defaultCountry)) {
            this.country.setText(defaultCountry);
        }
    }

    private void initCountriesWithISO3Country(String iso3Country) {
        initCountriesMap();
        if (!TextUtils.isEmpty(iso3Country)) {
            for (String key : this.countriesMap.keySet()) {
                if (((String) this.countriesMap.get(key)).compareTo(iso3Country) == 0) {
                    this.country.setText(key);
                    this.countryPrefilledOrChangedByUser = true;
                }
            }
        }
    }

    private float getMapZoom() {
        if (!this.address1.getText().toString().equals("")) {
            return STREET_LEVEL;
        }
        if (this.city.getText().toString().equals("") && this.zipcode.getText().toString().equals("")) {
            return COUNTRY_LEVEL;
        }
        return 10.0f;
    }

    public void proceedClick(View view) {
        boolean validInputs = true;
        String address1String = this.address1.getText().toString();
        if (address1String.isEmpty()) {
            validInputs = false;
            this.address1.setError(getString(C0676R.string.createHome_homeAddress_errors_addressLine1FieldEmptyErrorLabel));
        }
        String zipCodeString = this.zipcode.getText().toString();
        if (zipCodeString.isEmpty()) {
            validInputs = false;
            this.zipcode.setError(getString(C0676R.string.createHome_homeAddress_errors_postcodeFieldEmpyErrorLabel));
        }
        String cityString = this.city.getText().toString();
        if (cityString.isEmpty()) {
            validInputs = false;
            this.city.setError(getString(C0676R.string.createHome_homeAddress_errors_cityFieldEmptyErrorLabel));
        }
        String countryString = this.country.getText().toString();
        if (countryString.compareTo(getString(C0676R.string.createHome_homeAddress_countryField)) == 0) {
            validInputs = false;
            this.country.setError(getString(C0676R.string.createHome_homeAddress_errors_countryFieldEmptyErrorLabel));
        }
        if (Geocoder.isPresent() && !this.errorUsingGeocoder && this.locationFromEnteredAdress == null) {
            validInputs = false;
            showNoAddressAlert();
        }
        if (!validInputs) {
            return;
        }
        if (this.locationFromEnteredAdress != null || (Geocoder.isPresent() && !this.errorUsingGeocoder)) {
            proceed(address1String, this.address2.getText().toString(), zipCodeString, cityString, (String) this.countriesMap.get(countryString), this.locationFromEnteredAdress.getLatitude(), this.locationFromEnteredAdress.getLongitude());
            return;
        }
        String addressString = this.address1.getText().toString() + ", " + this.address2.getText().toString() + ", " + this.zipcode.getText().toString() + ", " + this.city.getText().toString();
        if (countryString.compareTo(getString(C0676R.string.createHome_homeAddress_countryField)) != 0) {
            addressString = addressString + ", " + countryString;
        }
        try {
            LocalAPICall ac = new LocalAPICall(new GetGoogleGeocodingRequest(addressString, getPackageManager().getApplicationInfo(getPackageName(), 128).metaData.getString("com.google.android.geo.SERVER_KEY")), this);
            ac.setAPICallListener(this);
            ac.setmShowLoaderDialog(true);
            ac.execute(new Void[0]);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void proceed(String addressLine1, String addressLine2, String zipCode, String city, String country, double latitude, double longitude) {
        Intent intent = new Intent(this, CreateHomeChooseNameActivity.class);
        intent.putExtras(getIntent());
        intent.putExtra("address1", addressLine1);
        intent.putExtra("address2", addressLine2);
        intent.putExtra(CreateHomeContactDetailsActivity.INTENT_ZIPCODE, zipCode);
        intent.putExtra("city", city);
        intent.putExtra("country", country);
        intent.putExtra("latitude", String.valueOf(latitude));
        intent.putExtra("longitude", String.valueOf(longitude));
        InstallationProcessController.startActivity((Activity) this, intent, false);
    }

    public void selectCountry(View view) {
        Intent intent = new Intent(this, ListCountriesActivity.class);
        intent.putExtra("countries", this.countriesList);
        startActivityForResult(intent, 1);
    }

    protected void onDestroy() {
        if (this.mMap != null && (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0)) {
            this.mMap.setMyLocationEnabled(false);
        }
        if (this.apiClient != null) {
            this.apiClient.disconnect();
        }
        super.onDestroy();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == -1 && data.getExtras() != null) {
            String countrString = data.getExtras().getString("country");
            this.country.setError(null);
            Button button = this.country;
            if (countrString == null) {
                countrString = "";
            }
            button.setText(countrString);
            this.countryPrefilledOrChangedByUser = true;
            userHasEditedLocation();
        }
    }

    public void onProcessResponse(APICall call, Response response) {
        if (response instanceof GetGoogleGeocodingResponse) {
            GLocation location = ((GetGoogleGeocodingResponse) response).getGeocodingResponse().getResults()[0].getGeometry().getLocation();
            if (this.locationFromEnteredAdress == null) {
                this.locationFromEnteredAdress = new Location("");
            }
            this.locationFromEnteredAdress.setLatitude(location.getLat());
            this.locationFromEnteredAdress.setLongitude(location.getLng());
            proceedClick(null);
        }
    }

    public void onCallFailed(APICall call, Response response) {
        if (response instanceof GetGoogleGeocodingResponse) {
            GeocodingResponse geocodingResponse = ((GetGoogleGeocodingResponse) response).getGeocodingResponse();
            String status = geocodingResponse.getStatus();
            Object obj = -1;
            switch (status.hashCode()) {
                case -813482689:
                    if (status.equals("ZERO_RESULTS")) {
                        obj = null;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    showNoAddressAlert();
                    return;
                default:
                    InstallationProcessController.showGenericError(this, geocodingResponse.getStatus() + " - " + geocodingResponse.getError_message(), call.getRequest(), response);
                    return;
            }
        }
        InstallationProcessController.showConnectionError(this, call.getRequest(), this);
    }

    private void showNoAddressAlert() {
        AlertDialogs.showCancelRetryAlert(getString(C0676R.string.createHome_homeAddress_errors_addressNotLocated_title), getString(C0676R.string.createHome_homeAddress_errors_addressNotLocated_message), getString(C0676R.string.createHome_homeAddress_errors_addressNotLocated_retryButton), getString(C0676R.string.createHome_homeAddress_errors_addressNotLocated_cancelButton), this, new C08046());
    }
}
