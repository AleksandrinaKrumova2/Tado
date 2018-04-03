package com.tado.android.installation.connectwifi;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.adapters.WifiListAdapter;
import com.tado.android.app.TadoApplication;
import com.tado.android.client.APICall;
import com.tado.android.client.APICallListener;
import com.tado.android.client.LocalAPICall;
import com.tado.android.client.LocalAPICallUtilities;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.entities.Wifi;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.requests.PostWifiSetupRequest;
import com.tado.android.responses.PostWifiSetupResponse;
import com.tado.android.responses.Response;
import com.tado.android.utils.TextValidator;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import java.util.ArrayList;

public class SelectWifiNetworkActivity extends DeviceStatePollingActivity implements APICallListener {
    private ArrayList<Wifi> wifiArrayList;

    public static class SelectCustomWifiFragment extends Fragment {
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(C0676R.layout.fragment_select_custom_wifi, container, false);
            ((TextView) rootView.findViewById(C0676R.id.title_bar_textview)).setText(C0676R.string.installation_sacc_connectWifi_wifiSetup_wifiCredentials_title);
            ((TextView) rootView.findViewById(C0676R.id.title_template_textview)).setText(C0676R.string.installation_sacc_connectWifi_wifiSetup_wifiCredentials_customWifi_message);
            ((Button) rootView.findViewById(C0676R.id.proceed_button)).setText(C0676R.string.installation_sacc_connectWifi_wifiSetup_wifiCredentials_confirmButton);
            final EditText editPassword = (EditText) rootView.findViewById(C0676R.id.password);
            final TextView showHidePassword = (TextView) rootView.findViewById(C0676R.id.show_password);
            editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            showHidePassword.setText(C0676R.string.components_passwordField_hideButton);
            ((Spinner) rootView.findViewById(C0676R.id.spinner)).setOnItemSelectedListener(new OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    final String securityString = (String) parent.getSelectedItem();
                    editPassword.setError(null);
                    if (securityString.compareTo(Wifi.WIFI_SECURITY_WEP) == 0 || securityString.compareTo(Wifi.WIFI_SECURITY_WPA) == 0 || securityString.compareTo(Wifi.WIFI_SECURITY_WPA2) == 0) {
                        editPassword.setVisibility(0);
                        editPassword.addTextChangedListener(new TextValidator(editPassword) {
                            public void validate(TextView textView, String text) {
                                if ((securityString.compareTo(Wifi.WIFI_SECURITY_WPA) == 0 || securityString.compareTo(Wifi.WIFI_SECURITY_WPA2) == 0) && text.length() < 8) {
                                    textView.setError(SelectCustomWifiFragment.this.getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_wifiCredentials_customWifi_errors_passwordFieldMinimumLengthError));
                                    showHidePassword.setVisibility(4);
                                    return;
                                }
                                textView.setError(null);
                            }
                        });
                        return;
                    }
                    editPassword.setVisibility(8);
                    editPassword.setText("");
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            editPassword.setOnFocusChangeListener(new OnFocusChangeListener() {
                public void onFocusChange(View v, boolean hasFocus) {
                    String password = editPassword.getText().toString();
                    if (hasFocus && !password.isEmpty()) {
                        showHidePassword.setVisibility(0);
                    } else if (!hasFocus) {
                        showHidePassword.setVisibility(8);
                    }
                }
            });
            editPassword.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                public void afterTextChanged(Editable s) {
                    if (editPassword.getText().toString().isEmpty()) {
                        showHidePassword.setVisibility(8);
                    } else {
                        showHidePassword.setVisibility(0);
                    }
                }
            });
            showHidePassword.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() != 0) {
                        return false;
                    }
                    TextView showHideButton = (TextView) v;
                    int start;
                    int end;
                    if (showHideButton.getText().toString().compareTo(SelectCustomWifiFragment.this.getString(C0676R.string.components_passwordField_showButton)) == 0) {
                        start = editPassword.getSelectionStart();
                        end = editPassword.getSelectionEnd();
                        editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        editPassword.setSelection(start, end);
                        showHideButton.setText(SelectCustomWifiFragment.this.getString(C0676R.string.components_passwordField_hideButton));
                    } else {
                        start = editPassword.getSelectionStart();
                        end = editPassword.getSelectionEnd();
                        editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        editPassword.setSelection(start, end);
                        showHideButton.setText(SelectCustomWifiFragment.this.getString(C0676R.string.components_passwordField_showButton));
                    }
                    return true;
                }
            });
            return rootView;
        }
    }

    public static class SelectWifiFragment extends Fragment {
        private TextView showHidePassword;
        private ArrayList<Wifi> wifiArrayList;
        private WifiListAdapter wifiListAdapter;

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(C0676R.layout.fragment_select_wifi_network, container, false);
            ((TextView) rootView.findViewById(C0676R.id.title_bar_textview)).setText(C0676R.string.installation_sacc_connectWifi_wifiSetup_wifiCredentials_title);
            ((TextView) rootView.findViewById(C0676R.id.title_template_textview)).setText(C0676R.string.installation_sacc_connectWifi_wifiSetup_wifiCredentials_chooseWifi_message);
            ((Button) rootView.findViewById(C0676R.id.proceed_button)).setText(C0676R.string.installation_sacc_connectWifi_wifiSetup_wifiCredentials_confirmButton);
            final EditText editPassword = (EditText) rootView.findViewById(C0676R.id.password);
            this.showHidePassword = (TextView) rootView.findViewById(C0676R.id.show_password);
            editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            this.showHidePassword.setText(C0676R.string.components_passwordField_hideButton);
            Spinner spinner = (Spinner) rootView.findViewById(C0676R.id.spinner);
            if (getArguments() != null) {
                ArrayList arrayList = (ArrayList) getArguments().getSerializable("wifiList");
                this.wifiArrayList = arrayList;
                if (arrayList != null) {
                    if (this.wifiArrayList.size() == 0) {
                        spinner.setVisibility(8);
                        ((TextView) rootView.findViewById(C0676R.id.error)).setVisibility(0);
                        rootView.findViewById(C0676R.id.proceed_button).setVisibility(8);
                    } else {
                        this.wifiListAdapter = new WifiListAdapter(TadoApplication.getTadoAppContext(), C0676R.layout.wifi_list_item, C0676R.id.wifi_list_item_text, this.wifiArrayList);
                        this.wifiListAdapter.setDropDownViewResource(C0676R.layout.wifi_list_item);
                        spinner.setAdapter(this.wifiListAdapter);
                        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                                if (((Wifi) SelectWifiFragment.this.wifiListAdapter.getItem(position)).getType().compareTo(Wifi.WIFI_SECURITY_OPEN) == 0) {
                                    editPassword.setVisibility(8);
                                    editPassword.setText("");
                                    return;
                                }
                                editPassword.setEnabled(true);
                                editPassword.setVisibility(0);
                            }

                            public void onNothingSelected(AdapterView<?> adapterView) {
                            }
                        });
                    }
                }
            }
            editPassword.setOnFocusChangeListener(new OnFocusChangeListener() {
                public void onFocusChange(View v, boolean hasFocus) {
                    String password = editPassword.getText().toString();
                    if (hasFocus && !password.isEmpty()) {
                        SelectWifiFragment.this.showHidePassword.setVisibility(0);
                    } else if (!hasFocus) {
                        SelectWifiFragment.this.showHidePassword.setVisibility(8);
                    }
                }
            });
            editPassword.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                public void afterTextChanged(Editable s) {
                    if (editPassword.getText().toString().isEmpty()) {
                        SelectWifiFragment.this.showHidePassword.setVisibility(8);
                    } else {
                        SelectWifiFragment.this.showHidePassword.setVisibility(0);
                    }
                }
            });
            this.showHidePassword.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() != 0) {
                        return false;
                    }
                    TextView showHideButton = (TextView) v;
                    int start;
                    int end;
                    if (showHideButton.getText().toString().compareTo(SelectWifiFragment.this.getString(C0676R.string.components_passwordField_showButton)) == 0) {
                        start = editPassword.getSelectionStart();
                        end = editPassword.getSelectionEnd();
                        editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        editPassword.setSelection(start, end);
                        showHideButton.setText(C0676R.string.components_passwordField_hideButton);
                    } else {
                        start = editPassword.getSelectionStart();
                        end = editPassword.getSelectionEnd();
                        editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        editPassword.setSelection(start, end);
                        showHideButton.setText(C0676R.string.components_passwordField_showButton);
                    }
                    return true;
                }
            });
            return rootView;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_select_wifi_network);
        if (savedInstanceState == null) {
            Fragment fragment = new SelectWifiFragment();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add((int) C0676R.id.container, fragment).commit();
        }
    }

    public void onProcessResponse(APICall call, Response response) {
        if (response instanceof PostWifiSetupResponse) {
            InstallationProcessController.startActivity((Activity) this, WaitForServerConnectionActivity.class, true);
        }
    }

    public void proceedClick(View view) {
        Fragment f = getSupportFragmentManager().findFragmentById(C0676R.id.container);
        Spinner spinner;
        EditText passwordEdit;
        TextView showHidePassword;
        String passwordString;
        boolean validInputs;
        WifiManager wifiManager;
        WifiInfo wifiInfo;
        if (f instanceof SelectWifiFragment) {
            spinner = (Spinner) findViewById(C0676R.id.spinner);
            if (spinner.getVisibility() == 0) {
                Wifi wifi = (Wifi) spinner.getSelectedItem();
                passwordEdit = (EditText) findViewById(C0676R.id.password);
                showHidePassword = (TextView) findViewById(C0676R.id.show_password);
                passwordString = passwordEdit.getText().toString();
                validInputs = true;
                if (passwordEdit.getVisibility() == 0 && passwordString.isEmpty()) {
                    validInputs = false;
                    passwordEdit.setError(getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_wifiCredentials_customWifi_errors_passwordFieldEmptyError));
                    showHidePassword.setVisibility(4);
                }
                if (validInputs) {
                    wifiManager = (WifiManager) getApplicationContext().getSystemService("wifi");
                    wifiInfo = wifiManager.getConnectionInfo();
                    if (wifiManager.isWifiEnabled() && Util.isCorrectSsid(wifiInfo.getSSID())) {
                        postWifiCredentials(wifi.getSsid(), passwordEdit.getText().toString(), wifi.getTypeInt());
                        return;
                    }
                    AlertDialogs.showWarning(getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_errors_title), getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_errors_notConnectedToTadoWifiError, new Object[]{UserConfig.getDeviceSsid()}), getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_errors_retryButton), this, null);
                }
            }
        } else if (f instanceof SelectCustomWifiFragment) {
            spinner = (Spinner) findViewById(C0676R.id.spinner);
            EditText ssidEdit = (EditText) findViewById(C0676R.id.ssid);
            String ssidString = ssidEdit.getText().toString();
            passwordEdit = (EditText) findViewById(C0676R.id.password);
            showHidePassword = (TextView) findViewById(C0676R.id.show_password);
            passwordString = passwordEdit.getText().toString();
            validInputs = true;
            if (ssidString.isEmpty()) {
                validInputs = false;
                ssidEdit.setError(getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_wifiCredentials_customWifi_errors_networkFieldEmptyError));
            }
            if (passwordEdit.getVisibility() == 0 && passwordString.isEmpty()) {
                validInputs = false;
                passwordEdit.setError(getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_wifiCredentials_customWifi_errors_passwordFieldEmptyError));
                showHidePassword.setVisibility(4);
            }
            String wifiString = (String) spinner.getSelectedItem();
            if ((wifiString.compareTo(Wifi.WIFI_SECURITY_WPA) == 0 || wifiString.compareTo(Wifi.WIFI_SECURITY_WPA2) == 0) && passwordString.length() < 8) {
                validInputs = false;
                passwordEdit.setError(getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_wifiCredentials_customWifi_errors_passwordFieldMinimumLengthError));
            }
            if (validInputs) {
                wifiManager = (WifiManager) getApplicationContext().getSystemService("wifi");
                wifiInfo = wifiManager.getConnectionInfo();
                if (wifiManager.isWifiEnabled() && Util.isCorrectSsid(wifiInfo.getSSID())) {
                    int securityType = spinner.getSelectedItemPosition();
                    postWifiCredentials(ssidEdit.getText().toString(), passwordEdit.getText().toString(), securityType);
                    return;
                }
                AlertDialogs.showWarning(getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_errors_title), getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_errors_notConnectedToTadoWifiError, new Object[]{UserConfig.getDeviceSsid()}), getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_errors_retryButton), this, null);
            }
        }
    }

    private void postWifiCredentials(String ssid, String password, int securityType) {
        LocalAPICallUtilities.executeRequestOnWiFi((ConnectivityManager) getSystemService("connectivity"), new PostWifiSetupRequest(ssid, password, securityType), this, this);
    }

    private void executeRequest(LocalAPICall request) {
        request.setAPICallListener(this);
        request.setmShowLoaderDialog(true);
        request.execute(new Void[0]);
    }

    public void troubleshoot(View view) {
        getSupportFragmentManager().beginTransaction().replace(C0676R.id.container, new SelectCustomWifiFragment()).addToBackStack(null).commit();
    }

    public void onCallFailed(APICall call, Response response) {
        AlertDialogs.showWarning(getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_errors_title), getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_errors_saccWifiConnectionError), getString(C0676R.string.installation_sacc_connectWifi_wifiSetup_errors_retryButton), this, null);
    }
}
