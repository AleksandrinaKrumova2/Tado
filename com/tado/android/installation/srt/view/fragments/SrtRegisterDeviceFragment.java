package com.tado.android.installation.srt.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.tado.C0676R;
import com.tado.android.entities.ServerError;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.installation.InstallationProcessController.UiCallback;
import com.tado.android.installation.srt.common.SrtNavigationCallback;
import com.tado.android.installation.srt.common.SrtNavigationInterface;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.callback.presenters.GeneralErrorAlertPresenter;
import com.tado.android.rest.model.DeviceInput;
import com.tado.android.rest.model.hvac.BridgeReplacementInstallation;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.rest.model.installation.Installation;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.Constants;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import com.tado.android.utils.ValidationUtils;
import retrofit2.Call;
import retrofit2.Response;

public class SrtRegisterDeviceFragment extends Fragment implements SrtNavigationInterface {
    @NonNull
    private OnFocusChangeListener authCodeFocusChangeListener = new C09856();
    @NonNull
    private TextWatcher authCodeTextWatcher = new C09834();
    @BindView(2131296907)
    TextView authCodeView;
    @BindView(2131296908)
    TextView errorView;
    @BindView(2131296909)
    ImageView imageView;
    private boolean isReplacement;
    private DeviceTypeEnum mDeviceTypeEnum;
    @BindView(2131296913)
    TextView messageView;
    @BindView(2131296912)
    ScrollView scrollView;
    @NonNull
    private OnFocusChangeListener serialNumberFocusChangeListener = new C09845();
    @NonNull
    private TextWatcher serialNumberTextWatcher = new C09823();
    @BindView(2131296914)
    TextView serialNumberView;
    @BindView(2131296916)
    TextView titleView;
    Unbinder unbinder;

    class C09801 implements UiCallback {
        C09801() {
        }

        public void onRequestFinished(Response response, ServerError serverError) {
            if (response.isSuccessful()) {
                ((SrtNavigationCallback) SrtRegisterDeviceFragment.this.getActivity()).onReplacementCreated((BridgeReplacementInstallation) response.body());
            }
            if (serverError == null) {
                return;
            }
            if (response.code() == 422) {
                String code = serverError.getCode();
                Object obj = -1;
                switch (code.hashCode()) {
                    case 402034827:
                        if (code.equals(Constants.SERVER_ERROR_ALREADY_REGISTERED)) {
                            obj = null;
                            break;
                        }
                        break;
                    case 1102768017:
                        if (code.equals("hwDevice.alreadyRegistered")) {
                            obj = 1;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case null:
                        SrtRegisterDeviceFragment.this.setErrorMessage(SrtRegisterDeviceFragment.this.getString(C0676R.string.installation_errors_deviceRegisteredToAnotherHome_message));
                        return;
                    case 1:
                        SrtRegisterDeviceFragment.this.setErrorMessage(SrtRegisterDeviceFragment.this.getString(C0676R.string.installation_errors_deviceRegisteredToYourHome_message));
                        return;
                    default:
                        SrtRegisterDeviceFragment.this.setErrorMessage(serverError.getTitle());
                        return;
                }
            } else if (response.code() == 412) {
                if (serverError.getCode().equalsIgnoreCase("home.bridgeReplacementInProgress")) {
                    for (Installation installation : InstallationProcessController.getInstallationProcessController().getInstallationInfo().getUnfinishedInstallations()) {
                        if (installation instanceof BridgeReplacementInstallation) {
                            ((SrtNavigationCallback) SrtRegisterDeviceFragment.this.getActivity()).onReplacementCreated((BridgeReplacementInstallation) installation);
                            break;
                        }
                    }
                }
                SrtRegisterDeviceFragment.this.setErrorMessage(serverError.getTitle());
            } else {
                SrtRegisterDeviceFragment.this.setErrorMessage(serverError.getTitle());
            }
        }

        public void onRequestFailed() {
            if (SrtRegisterDeviceFragment.this.isAdded()) {
                ((SrtNavigationCallback) SrtRegisterDeviceFragment.this.getActivity()).onServerCallFailure();
            }
        }
    }

    class C09823 implements TextWatcher {
        C09823() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {
            ValidationUtils.validateSerialNumber(s.toString(), false, SrtRegisterDeviceFragment.this.getResources(), SrtRegisterDeviceFragment.this.mDeviceTypeEnum);
            if (s.length() <= 0) {
                return;
            }
            if (ValidationUtils.isValidForRegistration(s.toString(), SrtRegisterDeviceFragment.this.authCodeView.getText().toString(), SrtRegisterDeviceFragment.this.mDeviceTypeEnum)) {
                SrtRegisterDeviceFragment.this.checkDeviceValidForRegistration();
            } else {
                SrtRegisterDeviceFragment.this.setRegisterButtonState(false);
            }
        }
    }

    class C09834 implements TextWatcher {
        C09834() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {
            if (s.length() > 0) {
                ValidationUtils.validateAuthCode(s.toString(), false, SrtRegisterDeviceFragment.this.getResources());
                if (ValidationUtils.isValidForRegistration(SrtRegisterDeviceFragment.this.serialNumberView.getText().toString(), s.toString(), SrtRegisterDeviceFragment.this.mDeviceTypeEnum)) {
                    SrtRegisterDeviceFragment.this.checkDeviceValidForRegistration();
                } else {
                    SrtRegisterDeviceFragment.this.setRegisterButtonState(false);
                }
            }
        }
    }

    class C09845 implements OnFocusChangeListener {
        C09845() {
        }

        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                if (ValidationUtils.isValidForRegistration(((TextView) v).getText().toString(), SrtRegisterDeviceFragment.this.authCodeView.getText().toString(), SrtRegisterDeviceFragment.this.mDeviceTypeEnum)) {
                    SrtRegisterDeviceFragment.this.checkDeviceValidForRegistration();
                }
            } else if (((TextView) v).getText().length() > 0) {
                ValidationUtils.validateSerialNumber(((TextView) v).getText().toString(), true, SrtRegisterDeviceFragment.this.getResources(), SrtRegisterDeviceFragment.this.mDeviceTypeEnum);
            }
        }
    }

    class C09856 implements OnFocusChangeListener {
        C09856() {
        }

        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                if (ValidationUtils.isValidForRegistration(SrtRegisterDeviceFragment.this.serialNumberView.getText().toString(), ((TextView) v).getText().toString(), SrtRegisterDeviceFragment.this.mDeviceTypeEnum)) {
                    SrtRegisterDeviceFragment.this.checkDeviceValidForRegistration();
                }
            } else if (((TextView) v).getText().length() > 0) {
                ValidationUtils.validateAuthCode(((TextView) v).getText().toString(), true, SrtRegisterDeviceFragment.this.getResources());
            }
        }
    }

    public enum DeviceTypeEnum {
        VALVE,
        GATEWAY
    }

    public SrtRegisterDeviceFragment() {
        setRetainInstance(true);
    }

    public static SrtRegisterDeviceFragment newGatewayInstance(boolean isReplacement) {
        return getSrtRegisterDeviceFragment(DeviceTypeEnum.GATEWAY, isReplacement);
    }

    public static SrtRegisterDeviceFragment newValveInstance() {
        return getSrtRegisterDeviceFragment(DeviceTypeEnum.VALVE, false);
    }

    @NonNull
    private static SrtRegisterDeviceFragment getSrtRegisterDeviceFragment(DeviceTypeEnum deviceTypeEnum, boolean isReplacement) {
        SrtRegisterDeviceFragment fragment = new SrtRegisterDeviceFragment();
        fragment.setArguments(getParams(deviceTypeEnum, isReplacement));
        return fragment;
    }

    private static Bundle getParams(@NonNull DeviceTypeEnum deviceTypeEnum, boolean isReplacement) {
        Bundle params = new Bundle();
        params.putSerializable(SrtHvacInstallationFragment.KEY_DEVICE_TYPE, deviceTypeEnum);
        params.putSerializable(SrtHvacInstallationFragment.KEY_IS_REPLACEMENT, Boolean.valueOf(isReplacement));
        return params;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0676R.layout.fragment_srt_register_device, container, false);
        this.unbinder = ButterKnife.bind((Object) this, view);
        initViews(this.mDeviceTypeEnum);
        return view;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(SrtHvacInstallationFragment.KEY_DEVICE_TYPE)) {
            this.mDeviceTypeEnum = (DeviceTypeEnum) getArguments().getSerializable(SrtHvacInstallationFragment.KEY_DEVICE_TYPE);
            this.isReplacement = getArguments().getBoolean(SrtHvacInstallationFragment.KEY_IS_REPLACEMENT, false);
        }
    }

    private void initViews(DeviceTypeEnum deviceTypeEnum) {
        TextView textView = this.titleView;
        int i = DeviceTypeEnum.GATEWAY == deviceTypeEnum ? this.isReplacement ? C0676R.string.installation_replaceBridge_title : C0676R.string.installation_registerBridge_title : C0676R.string.installation_registerValve_title;
        textView.setText(i);
        textView = this.messageView;
        i = DeviceTypeEnum.GATEWAY == deviceTypeEnum ? this.isReplacement ? C0676R.string.installation_replaceBridge_message : C0676R.string.installation_registerBridge_message : C0676R.string.installation_registerValve_message;
        textView.setText(i);
        this.messageView.setMovementMethod(LinkMovementMethod.getInstance());
        this.imageView.setImageResource(DeviceTypeEnum.GATEWAY == deviceTypeEnum ? C0676R.drawable.srt_register_bridge : C0676R.drawable.srt_register_valve);
    }

    public void onResume() {
        super.onResume();
        ((SrtNavigationCallback) getActivity()).setNextButtonText(C0676R.string.installation_hvacDrivenInstallation_proceedButton);
        setRegisterButtonState(false);
        initListeners();
    }

    private void setRegisterButtonState(boolean visible) {
        ((SrtNavigationCallback) getActivity()).setNextButtonState(visible);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    public void onNext() {
        ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.errorView.getWindowToken(), 0);
        if (this.isReplacement) {
            callReplaceBridge(getDeviceInput());
        } else {
            callRegisterDevice(getDeviceInput());
        }
    }

    private void callReplaceBridge(DeviceInput bridge) {
        InstallationProcessController.getInstallationProcessController().callReplaceBridge(bridge, new C09801());
    }

    private void callRegisterDevice(DeviceInput device) {
        RestServiceGenerator.getTadoRestService().registerDevice(UserConfig.getHomeId(), device, RestServiceGenerator.getCredentialsMap()).enqueue(new TadoCallback<GenericHardwareDevice>(new GeneralErrorAlertPresenter(getContext())) {
            public void onResponse(Call<GenericHardwareDevice> call, Response<GenericHardwareDevice> response) {
                super.onResponse(call, response);
                if (!SrtRegisterDeviceFragment.this.isAdded()) {
                    return;
                }
                if (response.isSuccessful()) {
                    ((SrtNavigationCallback) SrtRegisterDeviceFragment.this.getActivity()).onRegisteredDevice((GenericHardwareDevice) response.body());
                } else if (this.serverError == null) {
                    SrtRegisterDeviceFragment.this.setErrorMessage(SrtRegisterDeviceFragment.this.getString(C0676R.string.installation_generic_error_title));
                } else if (response.code() == 422 && DeviceTypeEnum.VALVE == SrtRegisterDeviceFragment.this.mDeviceTypeEnum) {
                    String code = this.serverError.getCode();
                    Object obj = -1;
                    switch (code.hashCode()) {
                        case 402034827:
                            if (code.equals(Constants.SERVER_ERROR_ALREADY_REGISTERED)) {
                                obj = null;
                                break;
                            }
                            break;
                        case 1102768017:
                            if (code.equals("hwDevice.alreadyRegistered")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            SrtRegisterDeviceFragment.this.setErrorMessage(SrtRegisterDeviceFragment.this.getString(C0676R.string.installation_errors_deviceRegisteredToAnotherHome_message));
                            return;
                        case 1:
                            SrtRegisterDeviceFragment.this.setErrorMessage(SrtRegisterDeviceFragment.this.getString(C0676R.string.installation_errors_deviceRegisteredToYourHome_message));
                            return;
                        default:
                            SrtRegisterDeviceFragment.this.setErrorMessage(SrtRegisterDeviceFragment.this.getString(C0676R.string.installation_errors_wrongRegistrationInfo_message));
                            return;
                    }
                } else {
                    SrtRegisterDeviceFragment.this.setErrorMessage(this.serverError.getTitle());
                }
            }

            public void onFailure(Call<GenericHardwareDevice> call, Throwable t) {
                super.onFailure(call, t);
                if (SrtRegisterDeviceFragment.this.isAdded()) {
                    ((SrtNavigationCallback) SrtRegisterDeviceFragment.this.getActivity()).onServerCallFailure();
                }
            }
        });
    }

    @NonNull
    private DeviceInput getDeviceInput() {
        return new DeviceInput(this.serialNumberView.getText().toString(), this.authCodeView.getText().toString());
    }

    public void onBack() {
        if (isAdded()) {
            Util.hideKeyboard(getActivity(), getView());
            ((SrtNavigationCallback) getActivity()).onBackStep();
        }
    }

    private void initListeners() {
        this.serialNumberView.addTextChangedListener(this.serialNumberTextWatcher);
        this.serialNumberView.setOnFocusChangeListener(this.serialNumberFocusChangeListener);
        this.authCodeView.addTextChangedListener(this.authCodeTextWatcher);
        this.authCodeView.setOnFocusChangeListener(this.authCodeFocusChangeListener);
    }

    private void checkDeviceValidForRegistration() {
        RestServiceGenerator.getTadoRestService().searchDevice(getDeviceInput(), RestServiceGenerator.getCredentialsMap()).enqueue(new TadoCallback<GenericHardwareDevice>(new GeneralErrorAlertPresenter(getContext())) {
            public void onResponse(Call<GenericHardwareDevice> call, Response<GenericHardwareDevice> response) {
                super.onResponse(call, response);
                SrtRegisterDeviceFragment.this.setRegisterButtonState(response.isSuccessful());
                SrtRegisterDeviceFragment.this.setErrorMessage(response.isSuccessful() ? "" : SrtRegisterDeviceFragment.this.getString(C0676R.string.installation_errors_wrongRegistrationInfo_message));
            }

            public void onFailure(Call<GenericHardwareDevice> call, Throwable t) {
                super.onFailure(call, t);
                if (SrtRegisterDeviceFragment.this.isAdded()) {
                    ((SrtNavigationCallback) SrtRegisterDeviceFragment.this.getActivity()).onServerCallFailure();
                }
            }
        });
    }

    private void setErrorMessage(String message) {
        if (this.errorView != null) {
            this.errorView.setText(message);
            this.scrollView.smoothScrollTo(0, this.errorView.getBottom());
        }
    }
}
