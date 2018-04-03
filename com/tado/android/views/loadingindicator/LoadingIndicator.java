package com.tado.android.views.loadingindicator;

public interface LoadingIndicator {
    void endProgress();

    void errorProgress();

    void startProgress();
}
