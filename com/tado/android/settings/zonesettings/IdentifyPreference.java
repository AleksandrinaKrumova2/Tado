package com.tado.android.settings.zonesettings;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import com.tado.C0676R;

public class IdentifyPreference extends Preference {
    private boolean identifiable;
    private Button identifyButton;
    private OnIdentifyClickListener identifyClickListener;
    private boolean selected;
    private RadioButton selectedRadioButton;
    private String serialNumber;

    class C11581 implements OnClickListener {
        C11581() {
        }

        public void onClick(View v) {
            if (IdentifyPreference.this.identifyClickListener != null) {
                IdentifyPreference.this.identifyClickListener.onIdentifyClickListener(IdentifyPreference.this.serialNumber);
            }
        }
    }

    public interface OnIdentifyClickListener {
        void onIdentifyClickListener(String str);
    }

    public IdentifyPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public IdentifyPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public IdentifyPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IdentifyPreference(Context context) {
        super(context);
        init();
    }

    private void init() {
        setWidgetLayoutResource(C0676R.layout.button_identify);
    }

    protected void onBindView(View view) {
        super.onBindView(view);
        this.selectedRadioButton = (RadioButton) view.findViewById(C0676R.id.device_selected);
        this.identifyButton = (Button) view.findViewById(C0676R.id.identifyButton);
        if (this.identifyButton != null) {
            this.identifyButton.setVisibility(this.identifiable ? 0 : 4);
            this.identifyButton.setClickable(this.identifiable);
            this.identifyButton.setOnClickListener(new C11581());
        }
        if (this.selectedRadioButton != null) {
            this.selectedRadioButton.setChecked(this.selected);
            this.selectedRadioButton.setClickable(false);
        }
    }

    public void setIdentifyClickListener(OnIdentifyClickListener identifyClickListener) {
        this.identifyClickListener = identifyClickListener;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if (this.selectedRadioButton != null) {
            this.selectedRadioButton.setSelected(selected);
        }
    }

    public void setIdentifiable(boolean isIdentifiable) {
        this.identifiable = isIdentifiable;
    }

    protected void onPrepareForRemoval() {
        super.onPrepareForRemoval();
        this.identifyClickListener = null;
    }
}
