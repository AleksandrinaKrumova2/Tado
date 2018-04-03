package com.tado.android.menu;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.utils.ResourceFactory;

public class DrawerActionViewHolder extends ViewHolder {
    private TextView actionTextView;

    DrawerActionViewHolder(View itemView) {
        super(itemView);
        this.actionTextView = (TextView) itemView.findViewById(C0676R.id.drawer_action_text_view);
    }

    public void bind(ActionItem item) {
        this.actionTextView.setText(item.getActionName());
        this.actionTextView.setEnabled(item.isEnabled());
        Drawable[] drawables = this.actionTextView.getCompoundDrawables();
        drawables[0] = ResourceFactory.getActionIconDrawable(this.actionTextView.getContext(), item.getActionName());
        if (item.hasBadge()) {
            drawables[2] = ContextCompat.getDrawable(this.actionTextView.getContext(), C0676R.drawable.badge_dot);
        } else {
            drawables[2] = null;
        }
        this.actionTextView.setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], drawables[3]);
    }
}
