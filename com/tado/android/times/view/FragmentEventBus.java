package com.tado.android.times.view;

import com.tado.android.times.view.interfaces.FragmentEventBusInterface;
import com.tado.android.times.view.interfaces.FragmentListener;
import java.util.ArrayList;
import java.util.List;

public final class FragmentEventBus implements FragmentEventBusInterface {
    List<FragmentListener> listeners = new ArrayList();

    public void register(FragmentListener listener) {
        this.listeners.add(listener);
    }

    public void unregister(FragmentListener listener) {
        if (this.listeners.contains(listener)) {
            this.listeners.remove(listener);
        }
    }

    public void doCallback() {
        for (FragmentListener l : this.listeners) {
            l.reload();
        }
    }
}
