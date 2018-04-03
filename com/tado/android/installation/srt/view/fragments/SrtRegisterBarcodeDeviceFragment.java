package com.tado.android.installation.srt.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.tado.C0676R;
import com.tado.android.installation.barcode.BarcodeActivity;
import com.tado.android.installation.srt.common.SrtNavigationCallback;
import com.tado.android.installation.srt.common.SrtNavigationInterface;
import com.tado.android.installation.srt.view.fragments.SrtRegisterDeviceFragment.DeviceTypeEnum;

public class SrtRegisterBarcodeDeviceFragment extends Fragment implements SrtNavigationInterface {
    @BindView(2131296909)
    ImageView imageView;
    boolean isReplacement = false;
    private DeviceTypeEnum mDeviceTypeEnum;
    @BindView(2131296913)
    TextView messageView;
    @BindView(2131296912)
    ScrollView scrollView;
    @BindView(2131296916)
    TextView titleView;
    Unbinder unbinder;

    public SrtRegisterBarcodeDeviceFragment() {
        setRetainInstance(true);
    }

    public static SrtRegisterBarcodeDeviceFragment newGatewayInstance(boolean isReplacement) {
        return getSrtRegisterDeviceFragment(DeviceTypeEnum.GATEWAY, isReplacement);
    }

    public static SrtRegisterBarcodeDeviceFragment newValveInstance() {
        return getSrtRegisterDeviceFragment(DeviceTypeEnum.VALVE, false);
    }

    @NonNull
    private static SrtRegisterBarcodeDeviceFragment getSrtRegisterDeviceFragment(DeviceTypeEnum deviceTypeEnum, boolean isReplacement) {
        SrtRegisterBarcodeDeviceFragment fragment = new SrtRegisterBarcodeDeviceFragment();
        fragment.setArguments(getParams(deviceTypeEnum, isReplacement));
        return fragment;
    }

    private static Bundle getParams(@NonNull DeviceTypeEnum deviceTypeEnum, boolean isReplacement) {
        Bundle params = new Bundle();
        params.putSerializable(SrtHvacInstallationFragment.KEY_DEVICE_TYPE, deviceTypeEnum);
        params.putBoolean(SrtHvacInstallationFragment.KEY_IS_REPLACEMENT, isReplacement);
        return params;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0676R.layout.fragment_srt_register_barcode_device, container, false);
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
        ((SrtNavigationCallback) getActivity()).setNextButtonText(C0676R.string.installation_scanToRegister_confirm_registerButton);
        setRegisterButtonState(true);
    }

    private void setRegisterButtonState(boolean visible) {
        ((SrtNavigationCallback) getActivity()).setNextButtonState(visible);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    public void onNext() {
        Intent intent = new Intent(getContext(), BarcodeActivity.class);
        intent.putExtra(SrtHvacInstallationFragment.KEY_DEVICE_TYPE, this.mDeviceTypeEnum);
        intent.putExtra(SrtHvacInstallationFragment.KEY_IS_REPLACEMENT, this.isReplacement);
        startActivityForResult(intent, BarcodeActivity.REQ_BARCODE_SCAN);
    }

    public void onBack() {
        if (isAdded()) {
            ((SrtNavigationCallback) getActivity()).onBackStep();
        }
    }
}
