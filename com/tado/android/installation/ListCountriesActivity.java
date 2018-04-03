package com.tado.android.installation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.adapters.StringListAdapter;
import com.tado.android.utils.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class ListCountriesActivity extends AppCompatActivity {
    private ArrayList<String> countryList;
    private ArrayList<String> countryListFiltered;
    private ListView listView;

    public static class CustomListFragment extends ListFragment {
        private ListCountriesActivity listCountriesActivity;

        public void onAttach(Activity activity) {
            if (activity instanceof ListCountriesActivity) {
                this.listCountriesActivity = (ListCountriesActivity) activity;
            }
            super.onAttach(activity);
        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(C0676R.layout.fragement_select_manufacturer, container, false);
            ((TextView) rootView.findViewById(C0676R.id.title_bar_textview)).setText(getString(C0676R.string.createHome_homeAddress_chooseCountry_title));
            ((TextView) rootView.findViewById(16908292)).setText(getString(C0676R.string.createHome_homeAddress_chooseCountry_errors_searchCountryFieldNoutFoundErrorLabel));
            Intent intent = this.listCountriesActivity.getIntent();
            if (intent.getExtras() != null) {
                ArrayList<String> countryArrayList = (ArrayList) intent.getExtras().getSerializable("countries");
                if (countryArrayList != null) {
                    Collections.sort(countryArrayList);
                    final StringListAdapter stringListAdapter = new StringListAdapter(this.listCountriesActivity, C0676R.layout.wifi_list_item, C0676R.id.wifi_list_item_text, countryArrayList);
                    setListAdapter(stringListAdapter);
                    final EditText editsearch = (EditText) rootView.findViewById(C0676R.id.inputSearch);
                    editsearch.addTextChangedListener(new TextWatcher() {
                        public void afterTextChanged(Editable arg0) {
                            stringListAdapter.filter(editsearch.getText().toString().toLowerCase(Locale.getDefault()));
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
            Util.hideKeyboard(this.listCountriesActivity, v);
            this.listCountriesActivity.countrySelected((String) l.getAdapter().getItem(position));
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_list_manufacturer);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add((int) C0676R.id.container, new CustomListFragment()).commit();
        }
    }

    public void countrySelected(String country) {
        Intent intent = new Intent();
        intent.putExtra(CreateHomeContactDetailsActivity.INTENT_COUNTRY, country);
        setResult(-1, intent);
        finish();
    }
}
