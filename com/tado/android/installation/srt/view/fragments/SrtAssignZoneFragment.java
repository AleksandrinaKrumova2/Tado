package com.tado.android.installation.srt.view.fragments;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.tado.C0676R;
import com.tado.android.dialogs.AlertChoiceDialogListener;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.installation.srt.common.SrtNavigationCallback;
import com.tado.android.installation.srt.common.SrtNavigationInterface;
import com.tado.android.installation.srt.view.fragments.AddZoneDialogFragment.AddZoneDialogListener;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.callback.presenters.GeneralErrorAlertPresenter;
import com.tado.android.rest.model.Zone;
import com.tado.android.rest.model.hvac.SrtDevice;
import com.tado.android.rest.model.hvac.ZoneWithImplicitControl;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.settings.zonesettings.SrtAssignCallback;
import com.tado.android.utils.ResourceFactory;
import com.tado.android.utils.UserConfig;
import java.io.Serializable;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class SrtAssignZoneFragment extends Fragment implements SrtNavigationInterface {
    private static final String KEY_DEVICE = "keyDevice";
    private static final String KEY_ZONES = "keyZones";
    @BindView(2131296327)
    Button acceptButton;
    AddZoneDialogListener addZoneDialogListener = new C09754();
    @BindView(2131296328)
    Button createNewZoneButton;
    private GenericHardwareDevice device;
    private SrtAssignCallback srtAssignCallback = null;
    private SrtNavigationCallback srtNavigationCallback = null;
    Unbinder unbinder;
    private List<Zone> zones;
    @BindView(2131296330)
    RadioGroup zonesRadioGroup;

    class C09711 implements OnClickListener {
        C09711() {
        }

        public void onClick(View v) {
            SrtAssignZoneFragment.this.onNext();
        }
    }

    class C09722 implements OnClickListener {
        C09722() {
        }

        public void onClick(View v) {
            if (SrtAssignZoneFragment.this.isAdded()) {
                if (SrtAssignZoneFragment.this.srtNavigationCallback != null) {
                    SrtAssignZoneFragment.this.srtNavigationCallback.setNextButtonState(false);
                }
                if (SrtAssignZoneFragment.this.srtAssignCallback != null) {
                    SrtAssignZoneFragment.this.acceptButton.setEnabled(false);
                }
                AddZoneDialogFragment addZoneDialogFragment = AddZoneDialogFragment.newInstance(SrtAssignZoneFragment.this.device.getShortSerialNo());
                addZoneDialogFragment.setCallback(SrtAssignZoneFragment.this.addZoneDialogListener);
                addZoneDialogFragment.show(SrtAssignZoneFragment.this.getActivity().getSupportFragmentManager(), "addZoneFragment");
            }
        }
    }

    class C09754 implements AddZoneDialogListener {
        C09754() {
        }

        public void onSave(ZoneWithImplicitControl newZone) {
            RadioButton radioButton = new RadioButton(SrtAssignZoneFragment.this.getActivity());
            radioButton.setId(Integer.MAX_VALUE);
            radioButton.setText(newZone.getName());
            SrtAssignZoneFragment.this.zonesRadioGroup.addView(radioButton);
            radioButton.setChecked(true);
            SrtAssignZoneFragment.this.setViewsEnabledState(false);
            SrtAssignZoneFragment.this.addNewZone(newZone, false, radioButton);
        }

        public void onCancel() {
            if (SrtAssignZoneFragment.this.srtNavigationCallback != null) {
                SrtAssignZoneFragment.this.srtNavigationCallback.setNextButtonState(true);
            }
            if (SrtAssignZoneFragment.this.srtAssignCallback != null) {
                SrtAssignZoneFragment.this.acceptButton.setEnabled(true);
            }
        }
    }

    class C09786 implements DialogInterface.OnClickListener {
        C09786() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }

    public SrtAssignZoneFragment() {
        setRetainInstance(true);
    }

    public static SrtAssignZoneFragment newInstance(List<Zone> zones, GenericHardwareDevice device) {
        SrtAssignZoneFragment fragment = new SrtAssignZoneFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_ZONES, (Serializable) zones);
        args.putSerializable(KEY_DEVICE, device);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.zones = (List) getArguments().getSerializable(KEY_ZONES);
            this.device = (GenericHardwareDevice) getArguments().getSerializable(KEY_DEVICE);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0676R.layout.fragment_srt_assign_zone, container, false);
        this.unbinder = ButterKnife.bind((Object) this, view);
        this.createNewZoneButton.setCompoundDrawablesWithIntrinsicBounds(ResourceFactory.getTintedDrawable(getActivity(), C0676R.drawable.ic_add_white_24dp, C0676R.color.ac_home), null, null, null);
        this.acceptButton.setOnClickListener(new C09711());
        if (this.srtAssignCallback != null) {
            this.acceptButton.setVisibility(0);
        }
        view.setBackgroundColor(-1);
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof SrtNavigationCallback) {
            this.srtNavigationCallback = (SrtNavigationCallback) getActivity();
        } else if (getActivity() instanceof SrtAssignCallback) {
            this.srtAssignCallback = (SrtAssignCallback) getActivity();
        }
    }

    public void onDetach() {
        super.onDetach();
        this.srtNavigationCallback = null;
        this.srtAssignCallback = null;
    }

    public void onResume() {
        super.onResume();
        initZoneList();
        this.createNewZoneButton.setOnClickListener(new C09722());
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    private void initZoneList() {
        this.zonesRadioGroup.removeAllViews();
        int checkedZoneId = -1;
        for (Zone zone : this.zones) {
            if (zone.isHeatingZone()) {
                RadioButton radioButton = new RadioButton(getActivity());
                radioButton.setId(zone.getId());
                radioButton.setText(zone.getName());
                this.zonesRadioGroup.addView(radioButton);
                if (checkedZoneId == -1) {
                    checkedZoneId = zone.getId();
                }
                for (GenericHardwareDevice device : zone.getDevices()) {
                    if (device.getShortSerialNo().equalsIgnoreCase(this.device.getShortSerialNo())) {
                        checkedZoneId = zone.getId();
                    }
                }
                radioButton.setChecked(zone.getId() == checkedZoneId);
            }
        }
    }

    public void onNext() {
        registerDevice(false);
    }

    private void registerDevice(final boolean force) {
        SrtDevice srtDevice = new SrtDevice();
        srtDevice.setSerialNo(this.device.getShortSerialNo());
        RestServiceGenerator.getTadoRestService().assignDeviceToZone(UserConfig.getHomeId(), this.zonesRadioGroup.getCheckedRadioButtonId(), srtDevice, force, RestServiceGenerator.getCredentialsMap()).enqueue(new TadoCallback<GenericHardwareDevice>(new GeneralErrorAlertPresenter(getContext())) {

            class C09731 implements AlertChoiceDialogListener {
                C09731() {
                }

                public void OnOKClicked() {
                    SrtAssignZoneFragment.this.registerDevice(true);
                }

                public void OnCancelClicked() {
                }
            }

            public void onResponse(Call<GenericHardwareDevice> call, Response<GenericHardwareDevice> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    if (SrtAssignZoneFragment.this.isAdded()) {
                        if (SrtAssignZoneFragment.this.srtNavigationCallback != null) {
                            SrtAssignZoneFragment.this.srtNavigationCallback.onAssignedZone(SrtAssignZoneFragment.this.zonesRadioGroup.getCheckedRadioButtonId());
                        }
                        if (SrtAssignZoneFragment.this.srtAssignCallback != null) {
                            SrtAssignZoneFragment.this.srtAssignCallback.onAssignedZone(new Zone(Integer.valueOf(SrtAssignZoneFragment.this.zonesRadioGroup.getCheckedRadioButtonId()), null, null), force);
                        }
                    }
                } else if (response.code() == 412 && SrtAssignZoneFragment.this.isAdded()) {
                    SrtAssignZoneFragment.this.showConfirmationDialog(new C09731());
                }
            }

            public void onFailure(Call<GenericHardwareDevice> call, Throwable t) {
                super.onFailure(call, t);
                if (SrtAssignZoneFragment.this.isAdded()) {
                    if (SrtAssignZoneFragment.this.srtNavigationCallback != null) {
                        SrtAssignZoneFragment.this.srtNavigationCallback.onServerCallFailure();
                    }
                    if (SrtAssignZoneFragment.this.srtAssignCallback != null) {
                        SrtAssignZoneFragment.this.srtAssignCallback.onServerCallFailure();
                    }
                }
            }
        });
    }

    private void showConfirmationDialog(AlertChoiceDialogListener listener) {
        AlertDialogs.showChoiceAlert(getString(C0676R.string.settings_zoneSettings_devices_assignDevice_moveLastDevice_title), getString(C0676R.string.settings_zoneSettings_devices_assignDevice_moveLastDevice_message), getString(C0676R.string.settings_zoneSettings_devices_assignDevice_moveLastDevice_confirmButton), getString(C0676R.string.settings_zoneSettings_devices_assignDevice_moveLastDevice_cancelButton), getActivity(), listener);
    }

    private void addNewZone(ZoneWithImplicitControl newZone, boolean force, RadioButton radioButton) {
        final boolean z = force;
        final ZoneWithImplicitControl zoneWithImplicitControl = newZone;
        final RadioButton radioButton2 = radioButton;
        RestServiceGenerator.getTadoRestService().addNewZone(UserConfig.getHomeId(), newZone, force, RestServiceGenerator.getCredentialsMap()).enqueue(new TadoCallback<Zone>(new GeneralErrorAlertPresenter(getContext())) {

            class C09761 implements AlertChoiceDialogListener {
                C09761() {
                }

                public void OnOKClicked() {
                    SrtAssignZoneFragment.this.addNewZone(zoneWithImplicitControl, true, radioButton2);
                }

                public void OnCancelClicked() {
                }
            }

            public void onResponse(Call<Zone> call, Response<Zone> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    if (SrtAssignZoneFragment.this.isAdded()) {
                        Zone zone = (Zone) response.body();
                        InstallationProcessController.getInstallationProcessController().addZone(zone);
                        if (SrtAssignZoneFragment.this.srtNavigationCallback != null) {
                            SrtAssignZoneFragment.this.srtNavigationCallback.onAssignedZone(zone.getId());
                        } else if (SrtAssignZoneFragment.this.srtAssignCallback != null) {
                            SrtAssignZoneFragment.this.srtAssignCallback.onAssignedZone(zone, z);
                        }
                    }
                } else if (response.code() == 412) {
                    SrtAssignZoneFragment.this.showConfirmationDialog(new C09761());
                } else if (response.code() != 422 || this.serverError == null || this.serverError.getCode() == null || !this.serverError.getCode().equalsIgnoreCase("heatingCircuitUnknown")) {
                    if (SrtAssignZoneFragment.this.isAdded()) {
                        SrtAssignZoneFragment.this.setViewsEnabledState(true);
                        SrtAssignZoneFragment.this.zonesRadioGroup.removeView(radioButton2);
                    }
                } else if (SrtAssignZoneFragment.this.isAdded()) {
                    SrtAssignZoneFragment.this.showErrorDialog();
                    SrtAssignZoneFragment.this.setViewsEnabledState(true);
                }
            }

            public void onFailure(Call<Zone> call, Throwable t) {
                super.onFailure(call, t);
                if (SrtAssignZoneFragment.this.isAdded()) {
                    SrtAssignZoneFragment.this.setViewsEnabledState(true);
                    SrtAssignZoneFragment.this.zonesRadioGroup.removeView(radioButton2);
                    if (SrtAssignZoneFragment.this.srtNavigationCallback != null) {
                        SrtAssignZoneFragment.this.srtNavigationCallback.onServerCallFailure();
                    }
                    if (SrtAssignZoneFragment.this.srtAssignCallback != null) {
                        SrtAssignZoneFragment.this.srtAssignCallback.onServerCallFailure();
                    }
                }
            }
        });
    }

    private void changeRadioGroupEnabledState(RadioGroup group, boolean enabled) {
        for (int i = 0; i < group.getChildCount(); i++) {
            group.getChildAt(i).setEnabled(enabled);
        }
    }

    public void setViewsEnabledState(boolean enabled) {
        changeRadioGroupEnabledState(this.zonesRadioGroup, enabled);
        this.createNewZoneButton.setEnabled(enabled);
        this.acceptButton.setEnabled(enabled);
        if (this.srtAssignCallback != null) {
            this.srtAssignCallback.allowBackNavigation(enabled);
        }
    }

    public void onBack() {
        if (isAdded() && this.srtNavigationCallback != null) {
            this.srtNavigationCallback.onBackStep();
        }
    }

    private void showErrorDialog() {
        Builder builder = new Builder(getActivity());
        builder.setTitle(C0676R.string.settings_zoneSettings_devices_assignDevice_errors_cannotCreateZoneForTechnicalReasons_title).setMessage(getText(C0676R.string.settings_zoneSettings_devices_assignDevice_errors_cannotCreateZoneForTechnicalReasons_message)).setPositiveButton(C0676R.string.settings_zoneSettings_devices_assignDevice_errors_cannotCreateZoneForTechnicalReasons_confirmButton, new C09786()).setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
        TextView messageView = (TextView) dialog.findViewById(16908299);
        if (messageView != null) {
            messageView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}
