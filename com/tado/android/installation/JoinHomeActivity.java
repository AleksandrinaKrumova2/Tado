package com.tado.android.installation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.tado.C0676R;

public class JoinHomeActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_join_home);
        ((TextView) findViewById(C0676R.id.title_bar_textview)).setText(getString(C0676R.string.createHome_joinHome_title));
    }
}
