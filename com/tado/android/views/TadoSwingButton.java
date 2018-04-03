package com.tado.android.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.tado.C0676R;
import com.tado.android.rest.model.installation.SwingEnum;
import com.wdullaer.materialdatetimepicker.Utils;

public class TadoSwingButton extends AppCompatButton {
    private OnSwingStateChanged onSwingStateChangedListener;
    private SwingEnum swingState;

    class C12771 implements OnClickListener {
        C12771() {
        }

        public void onClick(View view) {
            TadoSwingButton.this.swingState = TadoSwingButton.this.isSwingOn(TadoSwingButton.this.swingState) ? SwingEnum.OFF : SwingEnum.ON;
            TadoSwingButton.this.updateViewState(TadoSwingButton.this.swingState);
            if (TadoSwingButton.this.onSwingStateChangedListener != null) {
                TadoSwingButton.this.onSwingStateChangedListener.onSwingStateChanged(TadoSwingButton.this.swingState);
            }
        }
    }

    public interface OnSwingStateChanged {
        void onSwingStateChanged(SwingEnum swingEnum);
    }

    public TadoSwingButton(Context context) {
        super(context);
        init();
    }

    public TadoSwingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TadoSwingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(new int[]{16843534});
        setBackgroundDrawable(typedArray.getDrawable(0));
        typedArray.recycle();
        Drawable drawable = DrawableCompat.wrap(ContextCompat.getDrawable(getContext(), C0676R.drawable.control_panel_swing)).mutate();
        DrawableCompat.setTintList(drawable, ContextCompat.getColorStateList(getContext(), C0676R.color.swing_icon_color));
        setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        setCompoundDrawablePadding(Utils.dpToPx(8.0f, getResources()));
        setOnClickListener(new C12771());
    }

    private void updateViewState(SwingEnum swingState) {
        setTextColor(ContextCompat.getColorStateList(getContext(), C0676R.color.manual_control_start_button_color));
        setSelected(isSwingOn(swingState));
        setText(isSwingOn(swingState) ? C0676R.string.components_swingSelector_onLabel : C0676R.string.components_swingSelector_offLabel);
    }

    private boolean isSwingOn(SwingEnum swingState) {
        return SwingEnum.ON == swingState;
    }

    public void setSwingState(SwingEnum swingState) {
        this.swingState = swingState;
        updateViewState(swingState);
    }

    public void setOnSwingStateChangedListener(OnSwingStateChanged onSwingStateChangedListener) {
        this.onSwingStateChangedListener = onSwingStateChangedListener;
    }
}
