package com.tado.android.menu;

import com.tado.android.menu.DrawerItem.DrawerItemEnum;

public class DrawerLogoItem extends DrawerItem {
    private String mVersion;

    public DrawerLogoItem() {
        setItemType(DrawerItemEnum.LOGO_ITEM);
    }

    public String getVersion() {
        return this.mVersion;
    }

    public void setVersion(String version) {
        this.mVersion = version;
    }
}
