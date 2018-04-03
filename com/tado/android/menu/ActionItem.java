package com.tado.android.menu;

import android.content.Context;
import com.tado.android.menu.DrawerItem.DrawerItemEnum;

public class ActionItem extends DrawerItem {
    private Action action;
    private String actionName;
    private boolean enabled;

    public interface Action {
        void performAction(Context context);
    }

    public ActionItem(String actionName, Action action) {
        this(actionName, action, false);
    }

    public ActionItem(String actionName, Action action, boolean showBadge) {
        this.enabled = true;
        this.actionName = actionName;
        setItemType(DrawerItemEnum.ACTION_ITEM);
        this.hasBadge = showBadge;
        setAction(action);
    }

    public String getActionName() {
        return this.actionName;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void performAction(Context context) {
        if (this.action != null) {
            this.action.performAction(context);
        }
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
