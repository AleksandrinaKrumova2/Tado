package com.tado.android.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tado.C0676R;
import com.tado.android.rest.model.Temperature;
import com.tado.android.utils.Constants;
import com.tado.android.utils.Snitcher;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class TadoTemperaturePickerView extends LinearLayout {
    private static final float DECREASING_FACTOR = 1.5f;
    private static final int MIN_DELAY = 20;
    private static final int START_DELAY = 400;
    private float currentTemperature = 0.0f;
    final Handler handler = new Handler(Looper.getMainLooper());
    @BindView(2131297022)
    ImageButton mTemperatureDownButton;
    @BindView(2131297024)
    TextView mTemperatureTextView;
    @BindView(2131297025)
    ImageButton mTemperatureUpButton;
    private Timer mTimerTouchAndHold;
    private float maxValue;
    private float minValue;
    private OnTemperatureValueChangedListener onTemperatureValueChangedListener;
    private boolean pressAndHoldEnabled = true;
    private float step;
    private int stepInMillis = START_DELAY;
    final Runnable temperatureDownRunnable = new C12814();
    final Runnable temperatureUpRunnable = new C12803();
    private int totalMillis = 0;

    public interface OnTemperatureValueChangedListener {
        void onTemperatureValueChanged(float f);
    }

    class C12781 implements OnClickListener {
        C12781() {
        }

        public void onClick(View view) {
            TadoTemperaturePickerView.this.updateTemperatureBy(TadoTemperaturePickerView.this.step);
        }
    }

    class C12792 implements OnClickListener {
        C12792() {
        }

        public void onClick(View view) {
            TadoTemperaturePickerView.this.updateTemperatureBy(-TadoTemperaturePickerView.this.step);
        }
    }

    class C12803 implements Runnable {
        C12803() {
        }

        public void run() {
            TadoTemperaturePickerView.this.temperatureRunnableMethod(TadoTemperaturePickerView.this.step);
        }
    }

    class C12814 implements Runnable {
        C12814() {
        }

        public void run() {
            TadoTemperaturePickerView.this.temperatureRunnableMethod(-TadoTemperaturePickerView.this.step);
        }
    }

    private class OnTemperatureTouchListener implements OnTouchListener {
        Runnable mRunnable;

        public OnTemperatureTouchListener(Runnable runnable) {
            this.mRunnable = runnable;
        }

        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getActionMasked();
            Snitcher.start().log(4, "motion event %s", event);
            switch (action) {
                case 0:
                    TadoTemperaturePickerView.this.cancelAndPurgeTimer();
                    TadoTemperaturePickerView.this.mTimerTouchAndHold = new Timer();
                    TadoTemperaturePickerView.this.setupTimer(TadoTemperaturePickerView.this.mTimerTouchAndHold, this.mRunnable, 20);
                    break;
                case 1:
                    TadoTemperaturePickerView.this.cancelAndPurgeTimer();
                    break;
            }
            return false;
        }
    }

    public TadoTemperaturePickerView(Context context) {
        super(context);
        init(null);
    }

    public TadoTemperaturePickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TadoTemperaturePickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public TadoTemperaturePickerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attributeSet) {
        ButterKnife.bind(LayoutInflater.from(getContext()).inflate(C0676R.layout.tado_temperature_picker_view, this), (View) this);
        if (attributeSet != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(attributeSet, C0676R.styleable.TadoTemperaturePickerView, 0, 0);
            try {
                this.minValue = a.getFloat(2, Constants.MAX_OFFSET_CELSIUS);
                this.maxValue = a.getFloat(1, 25.0f);
                this.step = a.getFloat(5, 1.0f);
                this.pressAndHoldEnabled = a.getBoolean(4, true);
            } finally {
                a.recycle();
            }
        }
    }

    private void initListeners(Runnable temperatureUpRunnable, Runnable temperatureDownRunnable) {
        if (this.pressAndHoldEnabled) {
            this.mTemperatureUpButton.setOnTouchListener(new OnTemperatureTouchListener(temperatureUpRunnable));
            this.mTemperatureDownButton.setOnTouchListener(new OnTemperatureTouchListener(temperatureDownRunnable));
        }
        this.mTemperatureUpButton.setOnClickListener(new C12781());
        this.mTemperatureDownButton.setOnClickListener(new C12792());
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        initListeners(this.temperatureUpRunnable, this.temperatureDownRunnable);
    }

    private void temperatureRunnableMethod(final float tempStep) {
        int i = this.totalMillis + 20;
        this.totalMillis = i;
        if (i >= this.stepInMillis) {
            this.stepInMillis = Math.max((int) (((float) this.stepInMillis) / DECREASING_FACTOR), 20);
            this.handler.post(new Runnable() {
                public void run() {
                    TadoTemperaturePickerView.this.updateTemperatureBy(tempStep);
                }
            });
            this.totalMillis = 0;
        }
    }

    private void updateTemperatureBy(float value) {
        float newValue = this.currentTemperature + value;
        if (newValue >= this.minValue && newValue <= this.maxValue) {
            this.currentTemperature = newValue;
        } else if (newValue < this.minValue) {
            this.currentTemperature = this.minValue;
        } else if (newValue > this.maxValue) {
            this.currentTemperature = this.maxValue;
        }
        updateTemperatureValueView();
        updateTemperatureButtonsState(this.currentTemperature, this.minValue, this.maxValue);
        if (this.onTemperatureValueChangedListener != null) {
            this.onTemperatureValueChangedListener.onTemperatureValueChanged(this.currentTemperature);
        }
    }

    private void updateTemperatureValueView() {
        this.mTemperatureTextView.setText(Temperature.fromValue(this.currentTemperature).getFormattedTemperatureValue(this.step));
        updateTemperatureButtonsState(this.currentTemperature, this.minValue, this.maxValue);
    }

    private void updateTemperatureButtonsState(float value, float min, float max) {
        boolean z = true;
        if (value <= min && this.mTemperatureDownButton != null) {
            setTemperatureButtonEnabled(this.mTemperatureDownButton, false);
        } else if (value >= max && this.mTemperatureUpButton != null) {
            setTemperatureButtonEnabled(this.mTemperatureUpButton, false);
        } else if (this.mTemperatureDownButton != null && this.mTemperatureUpButton != null) {
            if (!this.mTemperatureDownButton.isEnabled() || !this.mTemperatureUpButton.isEnabled()) {
                boolean z2;
                ImageView imageView = this.mTemperatureUpButton;
                if (isEnabled()) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                setTemperatureButtonEnabled(imageView, z2);
                ImageView imageView2 = this.mTemperatureDownButton;
                if (!isEnabled()) {
                    z = false;
                }
                setTemperatureButtonEnabled(imageView2, z);
            }
        }
    }

    private void setTemperatureButtonEnabled(ImageView view, boolean enabled) {
        cancelAndPurgeTimer();
        view.setEnabled(enabled);
        if (enabled) {
            view.clearColorFilter();
        } else {
            view.setColorFilter(ContextCompat.getColor(getContext(), C0676R.color.control_panel_disabled), Mode.SRC_ATOP);
        }
    }

    private void setTemperatureTextViewEnabled(boolean enabled) {
        if (enabled) {
            this.mTemperatureTextView.getCompoundDrawables()[0].mutate().clearColorFilter();
            this.mTemperatureTextView.setTextColor(ContextCompat.getColor(getContext(), C0676R.color.control_panel_dark));
            return;
        }
        this.mTemperatureTextView.setTextColor(ContextCompat.getColor(getContext(), C0676R.color.control_panel_disabled));
        this.mTemperatureTextView.getCompoundDrawables()[0].mutate().setColorFilter(ContextCompat.getColor(getContext(), C0676R.color.control_panel_disabled), Mode.SRC_ATOP);
    }

    public boolean isPressAndHoldEnabled() {
        return this.pressAndHoldEnabled;
    }

    public void setPressAndHoldEnabled(boolean pressAndHoldEnabled) {
        this.pressAndHoldEnabled = pressAndHoldEnabled;
        if (pressAndHoldEnabled) {
            this.mTemperatureUpButton.setOnTouchListener(new OnTemperatureTouchListener(this.temperatureUpRunnable));
            this.mTemperatureDownButton.setOnTouchListener(new OnTemperatureTouchListener(this.temperatureDownRunnable));
            return;
        }
        this.mTemperatureUpButton.setOnTouchListener(null);
        this.mTemperatureDownButton.setOnTouchListener(null);
    }

    private void cancelAndPurgeTimer() {
        this.totalMillis = 0;
        this.stepInMillis = START_DELAY;
        if (this.mTimerTouchAndHold != null) {
            this.mTimerTouchAndHold.cancel();
            this.mTimerTouchAndHold.purge();
            this.mTimerTouchAndHold = null;
        }
    }

    private void setupTimer(Timer timer, final Runnable runnable, long updateInterval) {
        if (timer != null && runnable != null) {
            timer.schedule(new TimerTask() {
                public void run() {
                    runnable.run();
                }
            }, 400, updateInterval);
        }
    }

    private String formatTemperatureOutput(float temperature, float step) {
        return String.format(Locale.US, ((int) ((step - ((float) ((int) step))) * Constants.MAX_OFFSET_CELSIUS)) == 0 ? "%.0f˚" : "%.1f˚", new Object[]{Float.valueOf(temperature)});
    }

    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("superstate", super.onSaveInstanceState());
        bundle.putFloat("precision", this.step);
        bundle.putFloat("currentvalue", this.currentTemperature);
        bundle.putFloat("maxtemp", this.maxValue);
        bundle.putFloat("mintemp", this.minValue);
        return bundle;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.step = bundle.getFloat("precision");
            this.currentTemperature = bundle.getFloat("currentvalue");
            this.maxValue = bundle.getFloat("maxtemp");
            this.minValue = bundle.getFloat("mintemp");
            state = bundle.getParcelable("superstate");
        }
        super.onRestoreInstanceState(state);
    }

    public void setOnTemperatureValueChangedListener(OnTemperatureValueChangedListener listener) {
        this.onTemperatureValueChangedListener = listener;
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setTemperatureButtonEnabled(this.mTemperatureUpButton, enabled);
        setTemperatureButtonEnabled(this.mTemperatureDownButton, enabled);
        setTemperatureTextViewEnabled(enabled);
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public void setStep(float step) {
        this.step = step;
    }

    public void setCurrentTemperature(float currentTemperature) {
        this.currentTemperature = currentTemperature;
        updateTemperatureValueView();
    }

    public float getCurrentTemperature() {
        return this.currentTemperature;
    }
}
