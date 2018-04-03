package com.tado.android.entities;

public class Cost {
    private long AWAY;
    private long HOME;
    private long MANUAL;
    private long NO_FREEZE;
    private long NO_INTERNET;
    private long SLEEP;
    private long UNDEFINED;
    private float amount;

    public long getSLEEP() {
        return this.SLEEP;
    }

    public void setSLEEP(long sLEEP) {
        this.SLEEP = sLEEP;
    }

    public long getHOME() {
        return this.HOME;
    }

    public void setHOME(long hOME) {
        this.HOME = hOME;
    }

    public long getAWAY() {
        return this.AWAY;
    }

    public void setAWAY(long aWAY) {
        this.AWAY = aWAY;
    }

    public long getMANUAL() {
        return this.MANUAL;
    }

    public void setMANUAL(long mANUAL) {
        this.MANUAL = mANUAL;
    }

    public long getNO_FREEZE() {
        return this.NO_FREEZE;
    }

    public void setNO_FREEZE(long nO_FREEZE) {
        this.NO_FREEZE = nO_FREEZE;
    }

    public long getUNDEFINED() {
        return this.UNDEFINED;
    }

    public void setUNDEFINED(long mUNDEFINED) {
        this.UNDEFINED = mUNDEFINED;
    }

    public long getNO_INTERNET() {
        return this.NO_INTERNET;
    }

    public void setNO_INTERNET(long nO_INTERNET) {
        this.NO_INTERNET = nO_INTERNET;
    }

    public float getAmount() {
        return this.amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
