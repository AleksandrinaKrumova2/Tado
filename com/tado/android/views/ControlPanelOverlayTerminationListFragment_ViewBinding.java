package com.tado.android.views;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class ControlPanelOverlayTerminationListFragment_ViewBinding<T extends ControlPanelOverlayTerminationListFragment> implements Unbinder {
    protected T target;

    @UiThread
    public ControlPanelOverlayTerminationListFragment_ViewBinding(T target, View source) {
        this.target = target;
        target.mListView = (ListView) Utils.findRequiredViewAsType(source, C0676R.id.overlay_termination_list_view, "field 'mListView'", ListView.class);
        target.mBackButton = (ImageButton) Utils.findRequiredViewAsType(source, C0676R.id.list_overlay_back_button, "field 'mBackButton'", ImageButton.class);
        target.mButtonsView = Utils.findRequiredView(source, C0676R.id.list_overlay_buttons, "field 'mButtonsView'");
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mListView = null;
        target.mBackButton = null;
        target.mButtonsView = null;
        this.target = null;
    }
}
