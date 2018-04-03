package com.tado.android.utils;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class TadoBus extends Bus {
    public TadoBus(ThreadEnforcer enforcer) {
        super(enforcer);
    }

    public void register(Object object) {
        try {
            super.register(object);
        } catch (IllegalArgumentException e) {
            if (!e.getMessage().contains("Object already registered")) {
                throw e;
            }
        }
    }

    public void unregister(Object object) {
        try {
            super.unregister(object);
        } catch (IllegalArgumentException e) {
            if (!e.getMessage().contains("Missing event handler")) {
                throw e;
            }
        }
    }
}
