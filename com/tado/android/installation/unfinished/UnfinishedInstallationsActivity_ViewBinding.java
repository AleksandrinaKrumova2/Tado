package com.tado.android.installation.unfinished;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class UnfinishedInstallationsActivity_ViewBinding<T extends UnfinishedInstallationsActivity> implements Unbinder {
    protected T target;
    private View view2131297166;

    @UiThread
    public UnfinishedInstallationsActivity_ViewBinding(final T target, View source) {
        this.target = target;
        target.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0676R.id.unfinished_installation_recycler_view, "field 'recyclerView'", RecyclerView.class);
        View view = Utils.findRequiredView(source, C0676R.id.unfinished_installation_add_device, "method 'onAddDeviceClick'");
        this.view2131297166 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onAddDeviceClick(p0);
            }
        });
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.recyclerView = null;
        this.view2131297166.setOnClickListener(null);
        this.view2131297166 = null;
        this.target = null;
    }
}
