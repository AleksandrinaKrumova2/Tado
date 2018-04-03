package com.tado.android.rest.model;

public class ZoneWithState {
    private Zone mZone;
    private ZoneState mZoneState;

    public ZoneWithState(Zone zone, ZoneState zoneState) {
        this.mZone = zone;
        this.mZoneState = zoneState;
    }

    public Zone getZone() {
        return this.mZone;
    }

    public void setZone(Zone zone) {
        this.mZone = zone;
    }

    public ZoneState getZoneState() {
        return this.mZoneState;
    }

    public void setZoneState(ZoneState zoneState) {
        this.mZoneState = zoneState;
    }
}
