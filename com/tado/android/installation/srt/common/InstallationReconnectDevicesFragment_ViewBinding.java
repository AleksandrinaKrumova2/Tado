package com.tado.android.installation.srt.common;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class InstallationReconnectDevicesFragment_ViewBinding<T extends InstallationReconnectDevicesFragment> implements Unbinder {
    protected T target;
    private View view2131296584;
    private View view2131296706;

    @UiThread
    public InstallationReconnectDevicesFragment_ViewBinding(final T target, View source) {
        this.target = target;
        View view = Utils.findRequiredView(source, C0676R.id.learn_more, "field 'learnMoreTextView' and method 'onLearnMore'");
        target.learnMoreTextView = (TextView) Utils.castView(view, C0676R.id.learn_more, "field 'learnMoreTextView'", TextView.class);
        this.view2131296706 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onLearnMore();
            }
        });
        target.deviceConnectionStateList = (RecyclerView) Utils.findRequiredViewAsType(source, C0676R.id.device_connection_state_list, "field 'deviceConnectionStateList'", RecyclerView.class);
        target.reconnectStateTextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.reconnect_state_view, "field 'reconnectStateTextView'", TextView.class);
        target.reconnectStateImageView = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.device_reconnection_result, "field 'reconnectStateImageView'", ImageView.class);
        target.reconnectStateProgress = (ProgressBar) Utils.findRequiredViewAsType(source, C0676R.id.device_reconnection_progress, "field 'reconnectStateProgress'", ProgressBar.class);
        target.timerTextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.timer, "field 'timerTextView'", TextView.class);
        view = Utils.findRequiredView(source, C0676R.id.done, "field 'doneButton' and method 'onDone'");
        target.doneButton = (Button) Utils.castView(view, C0676R.id.done, "field 'doneButton'", Button.class);
        this.view2131296584 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onDone();
            }
        });
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.learnMoreTextView = null;
        target.deviceConnectionStateList = null;
        target.reconnectStateTextView = null;
        target.reconnectStateImageView = null;
        target.reconnectStateProgress = null;
        target.timerTextView = null;
        target.doneButton = null;
        this.view2131296706.setOnClickListener(null);
        this.view2131296706 = null;
        this.view2131296584.setOnClickListener(null);
        this.view2131296584 = null;
        this.target = null;
    }
}
