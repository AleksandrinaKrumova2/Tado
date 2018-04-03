package com.tado.android.utils;

import android.view.View;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

public class ViewEnabler {
    private List<WeakReference<View>> viewList = new LinkedList();

    public ViewEnabler(View view) {
        addView(view);
    }

    public void addView(View view) {
        this.viewList.add(new WeakReference(view));
    }

    private void enableViews(boolean enable) {
        for (WeakReference<View> reference : this.viewList) {
            View view = (View) reference.get();
            if (view != null) {
                view.setEnabled(enable);
            }
        }
    }

    public void enableViews() {
        enableViews(true);
    }

    public void disableViews() {
        enableViews(false);
    }
}
