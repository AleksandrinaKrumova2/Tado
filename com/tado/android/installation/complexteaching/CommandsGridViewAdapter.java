package com.tado.android.installation.complexteaching;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.nhaarman.supertooltips.ToolTipView;
import com.tado.C0676R;
import java.util.List;

public class CommandsGridViewAdapter extends ArrayAdapter<String> {
    private boolean animationOn = false;
    private int currentStep;
    private List<String> items;

    public CommandsGridViewAdapter(Context context, int resource, List<String> items, int currentStep) {
        super(context, resource);
        this.items = items;
        this.currentStep = currentStep;
    }

    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.items.size();
    }

    public void addItems(List<String> items) {
        this.animationOn = true;
        if (this.items.size() == 2) {
            this.items.remove(1);
        }
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(C0676R.layout.commands_grid_item_layout, null);
        TextView textView = (TextView) convertView.findViewById(C0676R.id.commands_grid_item_text);
        textView.setText((CharSequence) this.items.get(position));
        if (position > 1 && this.animationOn) {
            ObjectAnimator objectAnimator = new ObjectAnimator();
            ObjectAnimator animator = ObjectAnimator.ofFloat(convertView, ToolTipView.ALPHA_COMPAT, new float[]{0.0f, 1.0f});
            animator.setDuration(700);
            animator.setInterpolator(new LinearInterpolator());
            animator.start();
        }
        if (position + 1 == this.items.size()) {
            this.animationOn = false;
        }
        if (position < this.currentStep) {
            textView.setBackgroundResource(C0676R.drawable.commands_grid_green_background);
        } else {
            textView.setBackgroundResource(C0676R.drawable.commands_grid_grey_background);
        }
        return convertView;
    }
}
