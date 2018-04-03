package com.tado.android.adapters.demo;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class DemoBottomSheetRecyclerViewHolder_ViewBinding<T extends DemoBottomSheetRecyclerViewHolder> implements Unbinder {
    protected T target;

    @UiThread
    public DemoBottomSheetRecyclerViewHolder_ViewBinding(T target, View source) {
        this.target = target;
        target.demoItemName = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.demo_text, "field 'demoItemName'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.demoItemName = null;
        this.target = null;
    }
}
