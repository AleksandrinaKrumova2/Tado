package com.tado.android.app;

public enum MyGlobalProperties {
    INSTANCE;
    
    public boolean isNetworkDialogDisplayed;

    public void setNetworkDialogDisplayed(boolean displayed) {
        this.isNetworkDialogDisplayed = displayed;
    }

    public boolean getIsNetworkDialogDisplayed() {
        return this.isNetworkDialogDisplayed;
    }
}
