package com.tado.android.views;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tado.C0676R;
import com.tado.android.controllers.CapabilitiesController;
import com.tado.android.rest.model.installation.FanSpeedEnum;
import com.tado.android.times.view.model.ModeEnum;

public class TadoFanSpeedPickerView extends LinearLayout {
    private FanSpeedEnum currentFanSpeed;
    private ModeEnum currentMode;
    @BindView(2131297011)
    ImageButton fanDownButton;
    @BindView(2131297012)
    ImageView fanSpeedIcon;
    @BindView(2131297014)
    ImageView fanSpeedIconValueImage;
    @BindView(2131297015)
    TextView fanSpeedValueText;
    @BindView(2131297013)
    ImageButton fanUpButton;
    private OnFanSpeedValueChangedListener onFanSpeedValueChangedListener;

    class C12751 implements OnClickListener {
        C12751() {
        }

        public void onClick(View v) {
            TadoFanSpeedPickerView.this.updateFanSpeed(1);
        }
    }

    class C12762 implements OnClickListener {
        C12762() {
        }

        public void onClick(View v) {
            TadoFanSpeedPickerView.this.updateFanSpeed(-1);
        }
    }

    public interface OnFanSpeedValueChangedListener {
        void onFanSpeedValueChanged(FanSpeedEnum fanSpeedEnum);
    }

    public TadoFanSpeedPickerView(Context context) {
        super(context);
        initView();
    }

    public TadoFanSpeedPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TadoFanSpeedPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public TadoFanSpeedPickerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        ButterKnife.bind(LayoutInflater.from(getContext()).inflate(C0676R.layout.tado_fan_speed_picker_view, this), (View) this);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.fanSpeedIcon.setColorFilter(ContextCompat.getColor(getContext(), C0676R.color.control_panel_dark), Mode.SRC_ATOP);
        this.fanUpButton.setOnClickListener(new C12751());
        this.fanDownButton.setOnClickListener(new C12762());
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setComponentsEnabled(enabled);
    }

    private void setComponentsEnabled(boolean enabled) {
        this.fanDownButton.setEnabled(enabled);
        this.fanUpButton.setEnabled(enabled);
        this.fanSpeedValueText.setEnabled(enabled);
        if (enabled) {
            this.fanDownButton.clearColorFilter();
            this.fanUpButton.clearColorFilter();
            this.fanSpeedIconValueImage.clearColorFilter();
            this.fanSpeedIcon.setColorFilter(ContextCompat.getColor(getContext(), C0676R.color.control_panel_dark), Mode.SRC_ATOP);
            this.fanSpeedValueText.setTextColor(ContextCompat.getColor(getContext(), C0676R.color.control_panel_dark));
            return;
        }
        int disabledColor = ContextCompat.getColor(getContext(), C0676R.color.control_panel_disabled);
        this.fanDownButton.setColorFilter(disabledColor, Mode.SRC_ATOP);
        this.fanUpButton.setColorFilter(disabledColor, Mode.SRC_ATOP);
        this.fanSpeedIconValueImage.setColorFilter(disabledColor, Mode.SRC_ATOP);
        this.fanSpeedIcon.setColorFilter(disabledColor, Mode.SRC_ATOP);
        this.fanSpeedValueText.setTextColor(disabledColor);
    }

    private void updateFanSpeed(int value) {
        String[] fanSpeedArray = CapabilitiesController.INSTANCE.getFanSpeedValues(this.currentMode);
        int currentFanSpeedIndex = -1;
        if (fanSpeedArray != null && fanSpeedArray.length > 1) {
            for (int i = 0; i < fanSpeedArray.length; i++) {
                if (FanSpeedEnum.valueOf(fanSpeedArray[i]) == this.currentFanSpeed) {
                    currentFanSpeedIndex = i;
                    break;
                }
            }
            this.currentFanSpeed = FanSpeedEnum.valueOf(fanSpeedArray[((fanSpeedArray.length + currentFanSpeedIndex) + value) % fanSpeedArray.length]);
            updateFanSpeedValueViews(this.currentFanSpeed);
            if (this.onFanSpeedValueChangedListener != null) {
                this.onFanSpeedValueChangedListener.onFanSpeedValueChanged(this.currentFanSpeed);
            }
        }
    }

    private void updateFanSpeedValueViews(FanSpeedEnum fanSpeedValue) {
        int id;
        if (FanSpeedEnum.AUTO == fanSpeedValue) {
            id = -1;
        } else if (FanSpeedEnum.LOW == fanSpeedValue) {
            id = C0676R.drawable.control_panel_fan_speed_low;
        } else if (FanSpeedEnum.MIDDLE == fanSpeedValue) {
            id = C0676R.drawable.control_panel_fan_speed_mid;
        } else {
            id = C0676R.drawable.control_panel_fan_speed_high;
        }
        if (id == -1) {
            this.fanSpeedIconValueImage.setVisibility(8);
            this.fanSpeedValueText.setVisibility(0);
            return;
        }
        this.fanSpeedIconValueImage.setVisibility(0);
        this.fanSpeedIconValueImage.setImageDrawable(ContextCompat.getDrawable(getContext(), id));
        this.fanSpeedValueText.setVisibility(8);
    }

    public void setCurrentMode(ModeEnum currentMode) {
        this.currentMode = currentMode;
        updateFanSpeedValueViews(this.currentFanSpeed);
    }

    public void setCurrentFanSpeed(FanSpeedEnum currentFanSpeed) {
        this.currentFanSpeed = currentFanSpeed;
        updateFanSpeedValueViews(currentFanSpeed);
    }

    public void setOnFanSpeedValueChangedListener(OnFanSpeedValueChangedListener onFanSpeedValueChangedListener) {
        this.onFanSpeedValueChangedListener = onFanSpeedValueChangedListener;
    }
}
