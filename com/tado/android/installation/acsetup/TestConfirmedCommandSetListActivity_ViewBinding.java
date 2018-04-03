package com.tado.android.installation.acsetup;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity_ViewBinding;

public class TestConfirmedCommandSetListActivity_ViewBinding<T extends TestConfirmedCommandSetListActivity> extends ACInstallationBaseActivity_ViewBinding<T> {
    @UiThread
    public TestConfirmedCommandSetListActivity_ViewBinding(T target, View source) {
        super(target, source);
        target.listView = (ListView) Utils.findRequiredViewAsType(source, C0676R.id.command_set_list_view, "field 'listView'", ListView.class);
    }

    public void unbind() {
        TestConfirmedCommandSetListActivity target = (TestConfirmedCommandSetListActivity) this.target;
        super.unbind();
        target.listView = null;
    }
}
