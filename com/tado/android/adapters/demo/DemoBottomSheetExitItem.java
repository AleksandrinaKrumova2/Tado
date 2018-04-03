package com.tado.android.adapters.demo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import com.tado.android.PromoActivity;
import com.tado.android.utils.Util;

public class DemoBottomSheetExitItem extends DemoBottomSheetItem {
    public DemoBottomSheetExitItem(String name, @DrawableRes int iconRes, AppCompatActivity activity) {
        super(name, iconRes, activity, "exitDemo");
    }

    public void onClick() {
        if (this.activity.get() != null) {
            trackOnClick();
            Util.clearUserData();
            Intent intent = new Intent((Context) this.activity.get(), PromoActivity.class);
            intent.setFlags(1409318912);
            ((AppCompatActivity) this.activity.get()).startActivity(intent);
        }
    }

    public boolean shouldCloseParentOnClick() {
        return true;
    }
}
