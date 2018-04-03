package com.tado.android.installation;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tado.BuildConfig;
import com.tado.C0676R;

public class InstallEkOverviewActivity extends AppCompatActivity {

    public static class PlaceholderFragment extends Fragment {
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(C0676R.layout.fragment_install_heating_overview, container, false);
            ((TextView) rootView.findViewById(C0676R.id.title_bar_textview)).setText(getString(C0676R.string.installation_productSelection_extensionKit_title));
            ((TextView) rootView.findViewById(C0676R.id.title_template_textview)).setText(getDescriptionStringId());
            return rootView;
        }

        @StringRes
        private int getDescriptionStringId() {
            return BuildConfig.FLAVOR.equalsIgnoreCase("hager") ? C0676R.string.installation_productSelection_extensionKit_hagerMessage : C0676R.string.installation_productSelection_extensionKit_message;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_install_heating_overview);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add((int) C0676R.id.container, new PlaceholderFragment()).commit();
        }
    }
}
