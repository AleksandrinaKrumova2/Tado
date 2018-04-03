package com.tado.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.tado.android.menu.DrawerRecyclerViewAdapter;
import com.tado.android.views.OnDragListener;

public class ZoneItemTouchHelperCallback extends Callback {
    private DrawerRecyclerViewAdapter adapter;
    private OnDragListener dragListener;

    public ZoneItemTouchHelperCallback(DrawerRecyclerViewAdapter adapter, OnDragListener onDragListener) {
        this.adapter = adapter;
        this.dragListener = onDragListener;
    }

    public boolean isLongPressDragEnabled() {
        return true;
    }

    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    public int getMovementFlags(RecyclerView recyclerView, ViewHolder viewHolder) {
        if (viewHolder instanceof ZoneViewHolder) {
            return Callback.makeMovementFlags(3, 48);
        }
        return 0;
    }

    public boolean onMove(RecyclerView recyclerView, ViewHolder viewHolder, ViewHolder target) {
        if ((viewHolder instanceof ZoneViewHolder) && (target instanceof ZoneViewHolder)) {
            return this.adapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }
        return false;
    }

    public void onSwiped(ViewHolder viewHolder, int direction) {
    }

    public void onSelectedChanged(ViewHolder viewHolder, int actionState) {
        if (!(viewHolder == null || actionState == 0)) {
            viewHolder.itemView.animate().setDuration(150).alpha(0.8f).scaleX(1.1f).scaleY(1.1f);
        }
        if (this.dragListener != null) {
            this.dragListener.onDragStarted();
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    public void clearView(RecyclerView recyclerView, ViewHolder viewHolder) {
        viewHolder.itemView.animate().setDuration(150).alpha(1.0f).scaleX(1.0f).scaleY(1.0f);
        if (this.dragListener != null) {
            this.dragListener.onDragFinished();
        }
        super.clearView(recyclerView, viewHolder);
    }
}
