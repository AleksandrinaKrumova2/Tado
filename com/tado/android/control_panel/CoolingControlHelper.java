package com.tado.android.control_panel;

import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.times.view.model.ModeEnum;
import java.util.Map;
import java.util.Map.Entry;

public class CoolingControlHelper {
    private CoolingControlHelper() {
    }

    public static int getModeDrawableIcon(ModeEnum mode) {
        if (ModeEnum.COOL == mode) {
            return C0676R.drawable.button_mode_cool_state_icon_drawable;
        }
        if (ModeEnum.DRY == mode) {
            return C0676R.drawable.button_mode_dry_state_icon_drawable;
        }
        if (ModeEnum.FAN == mode) {
            return C0676R.drawable.button_mode_fan_state_icon_drawable;
        }
        if (ModeEnum.AUTO == mode) {
            return C0676R.drawable.button_mode_auto_state_icon_drawable;
        }
        return C0676R.drawable.button_mode_heat_state_icon_drawable;
    }

    public static int getModeDrawableIconV2(com.tado.android.rest.model.installation.ModeEnum mode) {
        if (com.tado.android.rest.model.installation.ModeEnum.COOL == mode) {
            return C0676R.drawable.button_mode_cool_state_icon_drawable;
        }
        if (com.tado.android.rest.model.installation.ModeEnum.DRY == mode) {
            return C0676R.drawable.button_mode_dry_state_icon_drawable;
        }
        if (com.tado.android.rest.model.installation.ModeEnum.FAN == mode) {
            return C0676R.drawable.button_mode_fan_state_icon_drawable;
        }
        if (com.tado.android.rest.model.installation.ModeEnum.AUTO == mode) {
            return C0676R.drawable.button_mode_auto_state_icon_drawable;
        }
        return C0676R.drawable.button_mode_heat_state_icon_drawable;
    }

    public static String getModeText(ModeEnum mode) {
        if (ModeEnum.COOL == mode) {
            return TadoApplication.getTadoAppContext().getResources().getString(C0676R.string.components_acModeSelector_coolModeLabel);
        }
        if (ModeEnum.DRY == mode) {
            return TadoApplication.getTadoAppContext().getResources().getString(C0676R.string.components_acModeSelector_dryModeLabel);
        }
        if (ModeEnum.FAN == mode) {
            return TadoApplication.getTadoAppContext().getResources().getString(C0676R.string.components_acModeSelector_fanModeLabel);
        }
        if (ModeEnum.AUTO == mode) {
            return TadoApplication.getTadoAppContext().getResources().getString(C0676R.string.components_acModeSelector_autoModeLabel);
        }
        return TadoApplication.getTadoAppContext().getResources().getString(C0676R.string.components_acModeSelector_heatModeLabel);
    }

    public static String getModeTextV2(com.tado.android.rest.model.installation.ModeEnum mode) {
        if (com.tado.android.rest.model.installation.ModeEnum.COOL == mode) {
            return TadoApplication.getTadoAppContext().getResources().getString(C0676R.string.components_acModeSelector_coolModeLabel);
        }
        if (com.tado.android.rest.model.installation.ModeEnum.DRY == mode) {
            return TadoApplication.getTadoAppContext().getResources().getString(C0676R.string.components_acModeSelector_dryModeLabel);
        }
        if (com.tado.android.rest.model.installation.ModeEnum.FAN == mode) {
            return TadoApplication.getTadoAppContext().getResources().getString(C0676R.string.components_acModeSelector_fanModeLabel);
        }
        if (com.tado.android.rest.model.installation.ModeEnum.AUTO == mode) {
            return TadoApplication.getTadoAppContext().getResources().getString(C0676R.string.components_acModeSelector_autoModeLabel);
        }
        return TadoApplication.getTadoAppContext().getResources().getString(C0676R.string.components_acModeSelector_heatModeLabel);
    }

    public static int getColor(boolean selected) {
        if (selected) {
            return ContextCompat.getColor(TadoApplication.getTadoAppContext(), C0676R.color.white);
        }
        return ContextCompat.getColor(TadoApplication.getTadoAppContext(), C0676R.color.round_mode_button_selected);
    }

    @Deprecated
    public static int getKeyFromValue(Map<Integer, ModeEnum> buttonMap, ModeEnum mode) {
        for (Entry<Integer, ModeEnum> e : buttonMap.entrySet()) {
            if (mode == e.getValue()) {
                return ((Integer) e.getKey()).intValue();
            }
        }
        return 0;
    }

    public static int getKeyFromValue(SparseArray<com.tado.android.rest.model.installation.ModeEnum> buttonMap, com.tado.android.rest.model.installation.ModeEnum mode) {
        for (int i = 0; i < buttonMap.size(); i++) {
            int key = buttonMap.keyAt(i);
            if (buttonMap.get(key) == mode) {
                return key;
            }
        }
        return 0;
    }

    public static void initButtonLayoutSelectedState(FloatingActionButton button, boolean selected) {
        if (!button.isEnabled()) {
            selected = false;
        }
        button.setSelected(selected);
    }

    public static void initModeButtonLayout(View buttonLayout, FloatingActionButton button, TextView text, ModeEnum mode) {
        button.setImageDrawable(TadoApplication.getTadoAppContext().getResources().getDrawable(getModeDrawableIcon(mode)));
        text.setText(getModeText(mode));
        buttonLayout.setVisibility(0);
    }

    public static void initModeButtonLayoutV2(View buttonLayout, FloatingActionButton button, TextView text, com.tado.android.rest.model.installation.ModeEnum mode) {
        button.setImageDrawable(TadoApplication.getTadoAppContext().getResources().getDrawable(getModeDrawableIconV2(mode)));
        text.setText(getModeTextV2(mode));
        buttonLayout.setVisibility(0);
    }

    public static void enableButton(FloatingActionButton button, boolean enable) {
        button.setEnabled(enable);
        if (enable) {
            button.clearColorFilter();
        } else {
            button.setColorFilter(ContextCompat.getColor(button.getContext(), C0676R.color.control_panel_disabled), Mode.SRC_ATOP);
        }
    }

    public static void enableButton(Button button, boolean enable) {
        button.setEnabled(enable);
        Drawable[] drawables = button.getCompoundDrawables();
        if (button.getBackground() == null) {
            return;
        }
        if (enable) {
            button.getBackground().clearColorFilter();
            button.setTextColor(ContextCompat.getColor(button.getContext(), C0676R.color.control_panel_dark));
            if (drawables[0] != null) {
                drawables[0].clearColorFilter();
                return;
            }
            return;
        }
        button.getBackground().setColorFilter(ContextCompat.getColor(button.getContext(), C0676R.color.cooling_control_panel_disabled), Mode.SRC_ATOP);
        if (drawables[0] != null) {
            drawables[0].setColorFilter(ContextCompat.getColor(button.getContext(), C0676R.color.control_panel_disabled), Mode.SRC_ATOP);
        }
        button.setTextColor(ContextCompat.getColor(button.getContext(), C0676R.color.control_panel_disabled));
    }
}
