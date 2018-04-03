package com.tado.android.installation.srt.view.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.tado.C0676R;
import com.tado.android.installation.srt.common.HvacInstallationController;
import com.tado.android.installation.srt.common.HvacStepCallback;
import com.tado.android.installation.srt.common.SrtNavigationCallback;
import com.tado.android.installation.srt.common.SrtNavigationInterface;
import com.tado.android.installation.srt.view.HvacLayout;
import com.tado.android.rest.model.hvac.ContentTypeEnum;
import com.tado.android.rest.model.hvac.StateLookup.StateLookupEnum;
import com.tado.android.rest.model.hvac.Step;
import com.tado.android.rest.model.hvac.SubStep;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.utils.Snitcher;
import java.util.List;

public class SrtHvacInstallationFragment extends Fragment implements HvacStepCallback, SrtNavigationInterface {
    public static final int DELAY_MILLIS = 300;
    public static final String KEY_DEVICE_REPLACED = "keyDeviceReplaced";
    public static final String KEY_DEVICE_TYPE = "keyDeviceType";
    public static final String KEY_IS_REPLACEMENT = "replacement";
    private HvacInstallationController controller;
    Handler delayer = new Handler(Looper.getMainLooper());
    private GenericHardwareDevice device;
    @BindView(2131296666)
    HvacLayout mHvacLayout;
    private ProgressDialog mProgressDialog;
    private GenericHardwareDevice replacedDevice;
    private Unbinder unbinder;

    class C09791 implements Runnable {
        C09791() {
        }

        public void run() {
            try {
                SrtHvacInstallationFragment.this.mProgressDialog = new ProgressDialog(SrtHvacInstallationFragment.this.getActivity());
                SrtHvacInstallationFragment.this.mProgressDialog.setCancelable(false);
                SrtHvacInstallationFragment.this.mProgressDialog.show();
            } catch (Exception e) {
                Snitcher.start().toCrashlytics().toLogger().logException(e);
            }
        }
    }

    public SrtHvacInstallationFragment() {
        setRetainInstance(true);
    }

    public static SrtHvacInstallationFragment newInstance(GenericHardwareDevice device) {
        Bundle args = new Bundle();
        args.putSerializable(KEY_DEVICE_TYPE, device);
        SrtHvacInstallationFragment fragment = new SrtHvacInstallationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static Fragment newInstance(GenericHardwareDevice device, GenericHardwareDevice oldBridge) {
        Bundle args = new Bundle();
        args.putSerializable(KEY_DEVICE_TYPE, device);
        args.putSerializable(KEY_DEVICE_REPLACED, oldBridge);
        args.putBoolean(KEY_IS_REPLACEMENT, true);
        SrtHvacInstallationFragment fragment = new SrtHvacInstallationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0676R.layout.fragment_srt_installation, container, false);
        this.unbinder = ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.device = (GenericHardwareDevice) getArguments().getSerializable(KEY_DEVICE_TYPE);
        if (getArguments().containsKey(KEY_IS_REPLACEMENT)) {
            this.replacedDevice = (GenericHardwareDevice) getArguments().getSerializable(KEY_DEVICE_REPLACED);
        }
    }

    public void onResume() {
        super.onResume();
        ((SrtNavigationCallback) getActivity()).setNextButtonText(C0676R.string.installation_hvacDrivenInstallation_proceedButton);
        if (this.controller == null) {
            this.controller = new HvacInstallationController();
            showProgressDialog();
            this.controller.detectStatus(this.device, this, this.replacedDevice);
        }
    }

    public void onDestroyView() {
        this.unbinder.unbind();
        super.onDestroyView();
    }

    public void onHvacStep(Step step) {
        dismissProgressDialog();
        if (step != null && step.getSubSteps() != null && step.getSubSteps().get(0) != null && this.mHvacLayout != null) {
            if (step.getDescription() != null) {
                this.mHvacLayout.setTitle(step.getDescription());
            }
            this.mHvacLayout.setSubSteps((List) step.getSubSteps().get(0));
            for (SubStep subStep : (List) step.getSubSteps().get(0)) {
                if (ContentTypeEnum.DEVICE_PROPERTY_PANEL == subStep.getContentType() && isAdded()) {
                    ((SrtNavigationCallback) getActivity()).setNextButtonState(false);
                }
            }
        }
    }

    public void onFailure() {
        if (isAdded()) {
            dismissProgressDialog();
            ((SrtNavigationCallback) getActivity()).onServerCallFailure();
        }
    }

    public void onHvacBack() {
        if (isAdded()) {
            dismissProgressDialog();
            ((SrtNavigationCallback) getActivity()).onBackFinishHvacStep();
        }
    }

    public void onHvacNext() {
        if (isAdded()) {
            dismissProgressDialog();
            ((SrtNavigationCallback) getActivity()).onNextFinishHvacStep();
        }
    }

    public void onDevicePropertyPanelValueChanged(String text, StateLookupEnum type) {
        if (isAdded()) {
            this.mHvacLayout.updateDevicePropertyPanel(text, type);
            ((SrtNavigationCallback) getActivity()).setNextButtonState(type != StateLookupEnum.IN_PROGRESS);
        }
    }

    public void onNext() {
        if (this.controller != null) {
            showProgressDialog();
            this.controller.nextStep();
        }
    }

    public void onBack() {
        if (this.controller != null) {
            showProgressDialog();
            this.controller.previousStep();
        }
    }

    public void showProgressDialog() {
        if (this.mProgressDialog != null) {
            dismissProgressDialog();
        }
        this.delayer.postDelayed(new C09791(), 300);
    }

    public void dismissProgressDialog() {
        this.delayer.removeCallbacksAndMessages(null);
        if (this.mProgressDialog != null) {
            this.mProgressDialog.dismiss();
            this.mProgressDialog = null;
        }
    }
}
