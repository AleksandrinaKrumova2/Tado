package com.tado.android.installation.srt.view.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.tado.C0676R;
import com.tado.android.rest.model.hvac.ZoneWithImplicitControl;

public class AddZoneDialogFragment extends AppCompatDialogFragment {
    private static final String KEY_SERIAL_NUMBER = "keySerialNumber";
    private AddZoneDialogListener addZoneDialogListener;
    Button confirmButton;
    private String serialNumber;
    private Unbinder unbinder;
    @BindView(2131296567)
    EditText zoneNameView;

    class C09681 implements OnClickListener {
        C09681() {
        }

        public void onClick(DialogInterface dialog, int which) {
            if (AddZoneDialogFragment.this.zoneNameView.getText().length() != 0) {
                ZoneWithImplicitControl newZone = new ZoneWithImplicitControl(AddZoneDialogFragment.this.zoneNameView.getText().toString(), AddZoneDialogFragment.this.serialNumber);
                if (AddZoneDialogFragment.this.addZoneDialogListener != null) {
                    AddZoneDialogFragment.this.addZoneDialogListener.onSave(newZone);
                    AddZoneDialogFragment.this.dismiss();
                }
            }
        }
    }

    class C09692 implements OnClickListener {
        C09692() {
        }

        public void onClick(DialogInterface dialog, int which) {
            if (AddZoneDialogFragment.this.addZoneDialogListener != null) {
                AddZoneDialogFragment.this.addZoneDialogListener.onCancel();
            }
            AddZoneDialogFragment.this.dismiss();
        }
    }

    class C09703 implements TextWatcher {
        C09703() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {
            AddZoneDialogFragment.this.updateConfirmButtonState(s.length());
        }
    }

    public interface AddZoneDialogListener {
        void onCancel();

        void onSave(ZoneWithImplicitControl zoneWithImplicitControl);
    }

    public static AddZoneDialogFragment newInstance(String serialNumber) {
        AddZoneDialogFragment fragment = new AddZoneDialogFragment();
        Bundle args = new Bundle();
        args.putString(KEY_SERIAL_NUMBER, serialNumber);
        fragment.setArguments(args);
        fragment.setStyle(0, C0676R.style.AddZoneDialog);
        return fragment;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Builder alertDialogBuilder = new Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(C0676R.layout.dialog_assign_new_zone, null);
        this.unbinder = ButterKnife.bind((Object) this, view);
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setTitle((int) C0676R.string.installation_assignZone_newZoneName_title);
        alertDialogBuilder.setPositiveButton((int) C0676R.string.installation_assignZone_newZoneName_saveButton, new C09681());
        alertDialogBuilder.setNegativeButton((int) C0676R.string.installation_assignZone_newZoneName_cancelButton, new C09692());
        AlertDialog dialog = alertDialogBuilder.create();
        this.confirmButton = dialog.getButton(-1);
        return dialog;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(KEY_SERIAL_NUMBER)) {
            this.serialNumber = getArguments().getString(KEY_SERIAL_NUMBER);
        }
    }

    public void onResume() {
        super.onResume();
        initListeners();
        this.zoneNameView.requestFocus();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    private void initListeners() {
        updateConfirmButtonState(this.zoneNameView.getText().length());
        this.zoneNameView.addTextChangedListener(getTextWatcher());
    }

    @NonNull
    private TextWatcher getTextWatcher() {
        return new C09703();
    }

    private void updateConfirmButtonState(int length) {
        try {
            this.confirmButton.setEnabled(length != 0);
        } catch (Exception e) {
        }
    }

    public void setCallback(AddZoneDialogListener addZoneDialogListener) {
        this.addZoneDialogListener = addZoneDialogListener;
    }
}
