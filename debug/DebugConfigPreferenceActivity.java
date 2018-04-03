package com.tado.android.debug;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class DebugConfigPreferenceActivity extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(16908290, new DebugConfigPreferenceFragment(), "root").commit();
    }
}
