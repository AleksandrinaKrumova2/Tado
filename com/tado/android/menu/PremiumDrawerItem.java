package com.tado.android.menu;

import com.tado.android.menu.ActionItem.Action;
import com.tado.android.menu.DrawerItem.DrawerItemEnum;

public class PremiumDrawerItem extends ActionItem {
    public PremiumDrawerItem(String actionName, Action action) {
        super(actionName, action);
    }

    public DrawerItemEnum getItemType() {
        return DrawerItemEnum.PREMIUM_ITEM;
    }
}
