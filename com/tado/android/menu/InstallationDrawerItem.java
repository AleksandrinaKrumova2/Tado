package com.tado.android.menu;

import com.tado.android.menu.DrawerItem.DrawerItemEnum;

public class InstallationDrawerItem extends DrawerItem {
    boolean startNewInstallation = false;
    String title;

    public InstallationDrawerItem(String title, boolean startNewInstallation) {
        setItemType(DrawerItemEnum.INSTALLATION_ITEM);
        this.title = title;
        this.startNewInstallation = startNewInstallation;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isStartNewInstallation() {
        return this.startNewInstallation;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartNewInstallation(boolean startNewInstallation) {
        this.startNewInstallation = startNewInstallation;
    }
}
