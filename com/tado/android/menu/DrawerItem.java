package com.tado.android.menu;

public class DrawerItem {
    protected boolean hasBadge = false;
    private DrawerItemEnum mItemType;

    public enum DrawerItemEnum {
        ZONE_ITEM,
        INSTALLATION_ITEM,
        PREMIUM_ITEM,
        ACTION_ITEM,
        LOGO_ITEM
    }

    public DrawerItemEnum getItemType() {
        return this.mItemType;
    }

    public void setItemType(DrawerItemEnum itemType) {
        this.mItemType = itemType;
    }

    public boolean updateBadgeVisibility(boolean showBadge) {
        if (this.hasBadge == showBadge) {
            return false;
        }
        this.hasBadge = showBadge;
        return true;
    }

    public boolean hasBadge() {
        return this.hasBadge;
    }
}
