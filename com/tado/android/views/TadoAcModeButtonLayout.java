package com.tado.android.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tado.C0676R;
import com.tado.android.times.view.model.ModeEnum;
import java.util.List;

public class TadoAcModeButtonLayout extends LinearLayout {
    private SparseArray<ModeEnum> buttonMap;
    private ModeEnum currentMode;
    @BindView(2131296638)
    TadoAcModeButtonView mFifthButtonLayout;
    @BindView(2131296643)
    TadoAcModeButtonView mFirstButtonLayout;
    @BindView(2131296649)
    TadoAcModeButtonView mFourthButtonLayout;
    @BindView(2131296957)
    TadoAcModeButtonView mSecondButtonLayout;
    @BindView(2131297096)
    TadoAcModeButtonView mThirdButtonLayout;
    private OnAcModeChangedListener onAcModeChangedListener;

    public interface OnAcModeChangedListener {
        void onAcModeChangedListener(ModeEnum modeEnum);
    }

    public TadoAcModeButtonLayout(Context context) {
        super(context);
        init();
    }

    public TadoAcModeButtonLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TadoAcModeButtonLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TadoAcModeButtonLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        ButterKnife.bind(LayoutInflater.from(getContext()).inflate(C0676R.layout.tado_ac_mode_button_layout, this), (View) this);
        this.buttonMap = new SparseArray();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        initButtonListeners();
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.mFirstButtonLayout.setEnabled(enabled);
        this.mSecondButtonLayout.setEnabled(enabled);
        this.mThirdButtonLayout.setEnabled(enabled);
        this.mFourthButtonLayout.setEnabled(enabled);
        this.mFifthButtonLayout.setEnabled(enabled);
    }

    private void initButtonsLayoutSelectedState(int selected) {
        boolean z;
        boolean z2 = true;
        this.mFirstButtonLayout.setSelected(isSelected(this.mFirstButtonLayout, selected == 1));
        TadoAcModeButtonView tadoAcModeButtonView = this.mSecondButtonLayout;
        TadoAcModeButtonView tadoAcModeButtonView2 = this.mSecondButtonLayout;
        if (selected == 2) {
            z = true;
        } else {
            z = false;
        }
        tadoAcModeButtonView.setSelected(isSelected(tadoAcModeButtonView2, z));
        tadoAcModeButtonView = this.mThirdButtonLayout;
        tadoAcModeButtonView2 = this.mThirdButtonLayout;
        if (selected == 3) {
            z = true;
        } else {
            z = false;
        }
        tadoAcModeButtonView.setSelected(isSelected(tadoAcModeButtonView2, z));
        tadoAcModeButtonView = this.mFourthButtonLayout;
        tadoAcModeButtonView2 = this.mFourthButtonLayout;
        if (selected == 4) {
            z = true;
        } else {
            z = false;
        }
        tadoAcModeButtonView.setSelected(isSelected(tadoAcModeButtonView2, z));
        TadoAcModeButtonView tadoAcModeButtonView3 = this.mFifthButtonLayout;
        tadoAcModeButtonView = this.mFifthButtonLayout;
        if (selected != 5) {
            z2 = false;
        }
        tadoAcModeButtonView3.setSelected(isSelected(tadoAcModeButtonView, z2));
    }

    private boolean isSelected(TadoAcModeButtonView buttonLayoutView, boolean selected) {
        return selected && buttonLayoutView.isEnabled();
    }

    public void setSupportedModes(List<ModeEnum> modeCapabilities) {
        int index = 1;
        for (ModeEnum mode : modeCapabilities) {
            int index2 = index + 1;
            this.buttonMap.put(index, mode);
            index = index2;
        }
        if (this.buttonMap.get(1) != null) {
            initTadoAcButtonLayoutView(this.mFirstButtonLayout, (ModeEnum) this.buttonMap.get(1));
        }
        if (this.buttonMap.get(2) != null) {
            initTadoAcButtonLayoutView(this.mSecondButtonLayout, (ModeEnum) this.buttonMap.get(2));
        }
        if (this.buttonMap.get(3) != null) {
            initTadoAcButtonLayoutView(this.mThirdButtonLayout, (ModeEnum) this.buttonMap.get(3));
        }
        if (this.buttonMap.get(4) != null) {
            initTadoAcButtonLayoutView(this.mFourthButtonLayout, (ModeEnum) this.buttonMap.get(4));
        }
        if (this.buttonMap.get(5) != null) {
            initTadoAcButtonLayoutView(this.mFifthButtonLayout, (ModeEnum) this.buttonMap.get(5));
        }
    }

    private void initTadoAcButtonLayoutView(TadoAcModeButtonView buttonLayoutView, ModeEnum mode) {
        buttonLayoutView.setMode(mode);
        buttonLayoutView.setVisibility(0);
    }

    private void initButtonListeners() {
        initTadoAcButtonLayoutViewListeners(this.mFirstButtonLayout, getAcModeButtonListener(1));
        initTadoAcButtonLayoutViewListeners(this.mSecondButtonLayout, getAcModeButtonListener(2));
        initTadoAcButtonLayoutViewListeners(this.mThirdButtonLayout, getAcModeButtonListener(3));
        initTadoAcButtonLayoutViewListeners(this.mFourthButtonLayout, getAcModeButtonListener(4));
        initTadoAcButtonLayoutViewListeners(this.mFifthButtonLayout, getAcModeButtonListener(5));
    }

    private void initTadoAcButtonLayoutViewListeners(TadoAcModeButtonView buttonLayoutView, OnClickListener listener) {
        buttonLayoutView.setAcModeButtonOnClickListener(listener);
    }

    @NonNull
    private OnClickListener getAcModeButtonListener(final int button) {
        return new OnClickListener() {
            public void onClick(View v) {
                TadoAcModeButtonLayout.this.buttonListener(button);
            }
        };
    }

    private int getKeyFromValue(SparseArray<ModeEnum> buttonMap, ModeEnum mode) {
        for (int i = 0; i < buttonMap.size(); i++) {
            int key = buttonMap.keyAt(i);
            if (buttonMap.get(key) == mode) {
                return key;
            }
        }
        return 0;
    }

    private void buttonListener(int id) {
        this.currentMode = (ModeEnum) this.buttonMap.get(id);
        initButtonsLayoutSelectedState(id);
        if (this.onAcModeChangedListener != null) {
            this.onAcModeChangedListener.onAcModeChangedListener(this.currentMode);
        }
    }

    public void setCurrentMode(ModeEnum currentMode) {
        this.currentMode = currentMode;
        initButtonListeners();
        initButtonsLayoutSelectedState(getKeyFromValue(this.buttonMap, currentMode));
    }

    public void setOnAcModeChangedListener(OnAcModeChangedListener onAcModeChangedListener) {
        this.onAcModeChangedListener = onAcModeChangedListener;
    }
}
