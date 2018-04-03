package com.tado.android.entities;

public class Costs {
    private Cost normal = null;
    private Cost tado = null;

    public Cost getTado() {
        return this.tado;
    }

    public void setTado(Cost tado) {
        this.tado = tado;
    }

    public Cost getNormal() {
        return this.normal;
    }

    public void setNormal(Cost normal) {
        this.normal = normal;
    }

    public boolean isAlmostEqualTo(Costs costs) {
        if (Math.abs(this.tado.getAWAY() - costs.getTado().getAWAY()) <= 600000 && Math.abs(this.tado.getHOME() - costs.getTado().getHOME()) <= 600000 && Math.abs(this.tado.getSLEEP() - costs.getTado().getSLEEP()) <= 600000 && Math.abs(this.tado.getMANUAL() - costs.getTado().getMANUAL()) <= 600000 && Math.abs(this.tado.getNO_FREEZE() - costs.getTado().getNO_FREEZE()) <= 600000 && Math.abs(this.tado.getUNDEFINED() - costs.getTado().getUNDEFINED()) <= 600000 && Math.abs(this.tado.getNO_INTERNET() - costs.getTado().getNO_INTERNET()) <= 600000 && this.tado.getAmount() == costs.getTado().getAmount() && Math.abs(this.normal.getAWAY() - costs.getNormal().getAWAY()) <= 600000 && Math.abs(this.normal.getHOME() - costs.getNormal().getHOME()) <= 600000 && Math.abs(this.normal.getSLEEP() - costs.getNormal().getSLEEP()) <= 600000 && Math.abs(this.normal.getMANUAL() - costs.getNormal().getMANUAL()) <= 600000 && Math.abs(this.normal.getNO_FREEZE() - costs.getNormal().getNO_FREEZE()) <= 600000 && Math.abs(this.normal.getUNDEFINED() - costs.getNormal().getUNDEFINED()) <= 600000 && Math.abs(this.normal.getNO_INTERNET() - costs.getNormal().getNO_INTERNET()) <= 600000 && this.normal.getAmount() == costs.getNormal().getAmount()) {
            return true;
        }
        return false;
    }
}
