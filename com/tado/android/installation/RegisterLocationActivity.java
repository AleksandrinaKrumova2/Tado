package com.tado.android.installation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.tado.C0676R;

public class RegisterLocationActivity extends AppCompatActivity {

    public static class PlaceholderFragment extends Fragment {
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(C0676R.layout.fragment_register_location, container, false);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_register_location);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add((int) C0676R.id.container, new PlaceholderFragment()).commit();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0676R.menu.menu_register_location, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == C0676R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
