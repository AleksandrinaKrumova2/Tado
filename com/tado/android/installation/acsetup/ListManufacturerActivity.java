package com.tado.android.installation.acsetup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.adapters.ManufacturerListAdapter;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.rest.model.installation.Manufacturer;
import com.tado.android.utils.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class ListManufacturerActivity extends ACInstallationBaseActivity {

    public static class CustomListFragment extends ListFragment {
        private ListManufacturerActivity listManufacturerActivity;

        public void onAttach(Activity activity) {
            if (activity instanceof ListManufacturerActivity) {
                this.listManufacturerActivity = (ListManufacturerActivity) activity;
            }
            super.onAttach(activity);
        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(C0676R.layout.fragement_select_manufacturer, container, false);
            ((TextView) rootView.findViewById(C0676R.id.title_bar_textview)).setText(getString(C0676R.string.installation_sacc_acSpecs_brandSelection_brandSearch_title));
            Intent intent = this.listManufacturerActivity.getIntent();
            if (intent.getExtras() != null) {
                ArrayList<Manufacturer> manufacturerArrayList = (ArrayList) intent.getExtras().getSerializable("manufacturersList");
                if (manufacturerArrayList != null) {
                    Collections.sort(manufacturerArrayList, new ManufacturerComparator(true));
                    final ManufacturerListAdapter manufacturerListAdapter = new ManufacturerListAdapter(this.listManufacturerActivity, C0676R.layout.wifi_list_item, C0676R.id.wifi_list_item_text, manufacturerArrayList);
                    setListAdapter(manufacturerListAdapter);
                    final EditText editsearch = (EditText) rootView.findViewById(C0676R.id.inputSearch);
                    editsearch.addTextChangedListener(new TextWatcher() {
                        public void afterTextChanged(Editable arg0) {
                            manufacturerListAdapter.filter(editsearch.getText().toString().toLowerCase(Locale.getDefault()));
                        }

                        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                        }

                        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                        }
                    });
                }
            }
            return rootView;
        }

        public void onListItemClick(ListView l, View v, int position, long id) {
            super.onListItemClick(l, v, position, id);
            Util.hideKeyboard(this.listManufacturerActivity, v);
            this.listManufacturerActivity.manufacturerSelected((Manufacturer) l.getAdapter().getItem(position));
        }
    }

    static class ManufacturerComparator implements Comparator<Manufacturer> {
        boolean mAscending = true;

        public ManufacturerComparator(boolean ascending) {
            this.mAscending = ascending;
        }

        public int compare(Manufacturer lhs, Manufacturer rhs) {
            int result = lhs.getName().compareTo(rhs.getName());
            return this.mAscending ? result : result * -1;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_list_manufacturer);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add((int) C0676R.id.container, new CustomListFragment()).commit();
        }
    }

    public void manufacturerSelected(Manufacturer manufacturer) {
        Intent intent = new Intent();
        intent.putExtra("manufacturerId", manufacturer.getId());
        intent.putExtra("manufacturerName", manufacturer.getName());
        setResult(-1, intent);
        finish();
    }
}
