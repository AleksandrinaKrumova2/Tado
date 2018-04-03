package com.tado.android.adapters.demo;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

class DemoBottomSheetRecyclerViewHolder extends ViewHolder {
    @BindView(2131296550)
    TextView demoItemName;

    public DemoBottomSheetRecyclerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind((Object) this, itemView);
    }
}
