package com.tado.android.times.view.interfaces;

public interface FragmentEventBusInterface {
    void doCallback();

    void register(FragmentListener fragmentListener);

    void unregister(FragmentListener fragmentListener);
}
