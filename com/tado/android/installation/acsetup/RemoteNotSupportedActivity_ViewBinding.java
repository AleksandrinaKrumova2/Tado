package com.tado.android.installation.acsetup;

import android.support.annotation.UiThread;
import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.installation.unfinished.UnfinishedInstallationDetailsActivity_ViewBinding;

public class RemoteNotSupportedActivity_ViewBinding<T extends RemoteNotSupportedActivity> extends UnfinishedInstallationDetailsActivity_ViewBinding<T> {
    private View view2131296706;

    @UiThread
    public RemoteNotSupportedActivity_ViewBinding(final T target, View source) {
        super(target, source);
        View view = Utils.findRequiredView(source, C0676R.id.learn_more, "method 'onLearnMore'");
        this.view2131296706 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onLearnMore();
            }
        });
    }

    public void unbind() {
        super.unbind();
        this.view2131296706.setOnClickListener(null);
        this.view2131296706 = null;
    }
}
