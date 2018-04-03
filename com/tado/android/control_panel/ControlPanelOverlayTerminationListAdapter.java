package com.tado.android.control_panel;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import com.tado.C0676R;
import com.tado.android.rest.model.OverlayTerminationCondition;
import java.util.List;

public class ControlPanelOverlayTerminationListAdapter extends ArrayAdapter<OverlayTerminationListItem> {
    List<OverlayTerminationListItem> items;
    private CheckedTextView timerView;

    public ControlPanelOverlayTerminationListAdapter(Context context, int resource, List<OverlayTerminationListItem> items) {
        super(context, resource);
        this.items = items;
    }

    public int getCount() {
        return this.items.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(C0676R.layout.overlay_termination_list_item_layout, null, false);
        OverlayTerminationListItem item = (OverlayTerminationListItem) this.items.get(position);
        CheckedTextView checkedTextView = (CheckedTextView) view.findViewById(C0676R.id.overlay_termination_list_item);
        if (item.getType().equalsIgnoreCase(OverlayTerminationCondition.TIMER)) {
            this.timerView = checkedTextView;
        }
        Drawable drawable = getContext().getResources().getDrawable(item.getResourceId()).mutate();
        if (!item.isEnabled()) {
            int disabledStateColor = getContext().getResources().getColor(C0676R.color.disabled_text_color);
            PorterDuffColorFilter disabledStateColorFilter = new PorterDuffColorFilter(disabledStateColor, Mode.SRC_ATOP);
            checkedTextView.setTextColor(disabledStateColor);
            if (VERSION.SDK_INT >= 16) {
                checkedTextView.getCheckMarkDrawable().setColorFilter(disabledStateColorFilter);
            }
            drawable.setColorFilter(disabledStateColorFilter);
        }
        checkedTextView.setText(item.getName());
        checkedTextView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        return view;
    }

    public void setTimerValue(String timerValue) {
        if (this.timerView != null) {
            this.timerView.setText(timerValue);
        }
    }
}
