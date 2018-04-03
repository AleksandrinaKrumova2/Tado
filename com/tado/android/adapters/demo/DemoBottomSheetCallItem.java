package com.tado.android.adapters.demo;

import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import com.tado.android.demo.phone.view.PhoneListFragment;

public class DemoBottomSheetCallItem extends DemoBottomSheetItem {
    public DemoBottomSheetCallItem(String name, @DrawableRes int iconRes, AppCompatActivity activity) {
        super(name, iconRes, activity, "callUs");
    }

    public void onClick() {
        if (this.activity.get() != null) {
            trackOnClick();
            new PhoneListFragment().show(((AppCompatActivity) this.activity.get()).getSupportFragmentManager(), "phoneList");
        }
    }

    public boolean shouldCloseParentOnClick() {
        return true;
    }
}
