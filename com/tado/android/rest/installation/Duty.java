package com.tado.android.rest.model.installation;

public enum Duty {
    ZONE_UI(8),
    CIRCUIT_DRIVER(2),
    ZONE_DRIVER(0),
    ZONE_LEADER(0);
    
    private final int value;

    private Duty(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
