package com.tado.android.views;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tado.C0676R;
import com.tado.android.control_panel.CoolingControlHelper;
import com.tado.android.times.view.model.ModeEnum;

public class TadoAcModeButtonView extends LinearLayout {
    @BindView(2131297008)
    FloatingActionButton mButton;
    @BindView(2131297010)
    TextView mNameView;
    private ModeEnum mode;

    public TadoAcModeButtonView(Context context) {
        super(context);
        initView();
    }

    public TadoAcModeButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TadoAcModeButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public TadoAcModeButtonView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        ButterKnife.bind(LayoutInflater.from(getContext()).inflate(C0676R.layout.tado_ac_mode_button_view, this), (View) this);
    }

    private void prepareButtonView() {
        this.mButton.setImageDrawable(ContextCompat.getDrawable(getContext(), getModeDrawableIcon()));
        this.mNameView.setText(getModeText());
    }

    @StringRes
    public int getModeText() {
        if (ModeEnum.COOL == this.mode) {
            return C0676R.string.components_acModeSelector_coolModeLabel;
        }
        if (ModeEnum.DRY == this.mode) {
            return C0676R.string.components_acModeSelector_dryModeLabel;
        }
        if (ModeEnum.FAN == this.mode) {
            return C0676R.string.components_acModeSelector_fanModeLabel;
        }
        if (ModeEnum.AUTO == this.mode) {
            return C0676R.string.components_acModeSelector_autoModeLabel;
        }
        return C0676R.string.components_acModeSelector_heatModeLabel;
    }

    @DrawableRes
    private int getModeDrawableIcon() {
        return CoolingControlHelper.getModeDrawableIcon(this.mode);
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.mNameView.setEnabled(enabled);
        setButtonEnabled(enabled);
    }

    private void setButtonEnabled(boolean enabled) {
        this.mButton.setEnabled(enabled);
        if (enabled) {
            this.mButton.clearColorFilter();
        } else {
            this.mButton.setColorFilter(ContextCompat.getColor(getContext(), C0676R.color.control_panel_disabled), Mode.SRC_ATOP);
        }
    }

    public void setMode(@NonNull ModeEnum mode) {
        this.mode = mode;
        prepareButtonView();
    }

    public void setAcModeButtonOnClickListener(@NonNull OnClickListener listener) {
        this.mButton.setOnClickListener(listener);
    }
}
