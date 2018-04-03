package com.tado.android.installation.acsetup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.AcDriver;
import com.tado.android.rest.model.installation.AcCommandSet;
import com.tado.android.rest.model.installation.AvailableCommandSets;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.settings.cooling.AcCommandSetArrayAdapter;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestConfirmedCommandSetListActivity extends ACInstallationBaseActivity {
    @BindView(2131296426)
    ListView listView;
    private int mPosition = -1;

    class C08521 extends TadoCallback<AvailableCommandSets> {
        C08521() {
        }

        public void onResponse(Call<AvailableCommandSets> call, Response<AvailableCommandSets> response) {
            super.onResponse(call, response);
            TestConfirmedCommandSetListActivity.this.dismissLoadingUI();
            if (response.isSuccessful()) {
                TestConfirmedCommandSetListActivity.this.processAvailableCommandSets((AvailableCommandSets) response.body());
                return;
            }
            TestConfirmedCommandSetListActivity.this.handleServerError(this.serverError, TestConfirmedCommandSetListActivity.this, call, response.code(), this);
        }

        public void onFailure(Call<AvailableCommandSets> call, Throwable t) {
            super.onFailure(call, t);
            InstallationProcessController.showConnectionErrorRetrofit(TestConfirmedCommandSetListActivity.this, call, this);
            TestConfirmedCommandSetListActivity.this.dismissLoadingUI();
        }
    }

    class C08543 implements Callback<AcCommandSet> {
        C08543() {
        }

        public void onResponse(Call<AcCommandSet> call, Response<AcCommandSet> response) {
            InstallationProcessController.getInstallationProcessController().setSelectedAcCommandSet((AcCommandSet) response.body());
            TestConfirmedCommandSetListActivity.this.finish();
        }

        public void onFailure(Call<AcCommandSet> call, Throwable t) {
            Snitcher.start().log(6, "e", "e", t);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_test_confrimed_command_set_list);
        ButterKnife.bind((Activity) this);
        this.titleBarTextview.setText(getString(C0676R.string.installation_sacc_setupAC_selectCommandSet_testingCommandSets_commandSetList_title));
        callGetCommandSetsApi();
    }

    private void callGetCommandSetsApi() {
        showLoadingUI();
        RestServiceGenerator.getTadoInstallationRestService().listAvailableCommandSets(UserConfig.getHomeId(), Integer.valueOf(InstallationProcessController.getInstallationProcessController().getInstallationId())).enqueue(new C08521());
    }

    private void processAvailableCommandSets(AvailableCommandSets availableCommandSets) {
        if (availableCommandSets.getAvailableCommandSets() != null && availableCommandSets.getAvailableCommandSets().size() != 0) {
            if (InstallationProcessController.getInstallationProcessController().getSelectedAcCommandSet() != null) {
                int code = InstallationProcessController.getInstallationProcessController().getSelectedAcCommandSet().getCode().intValue();
                for (int i = 0; i < availableCommandSets.getAvailableCommandSets().size(); i++) {
                    if (code == ((AcCommandSet) availableCommandSets.getAvailableCommandSets().get(i)).getCode().intValue()) {
                        this.mPosition = i;
                    }
                }
            }
            List<AcDriver> listConfirmedCommandSets = new ArrayList(availableCommandSets.getAvailableCommandSets().size());
            for (AcCommandSet acCommandSet : availableCommandSets.getAvailableCommandSets()) {
                AcDriver driver = new AcDriver();
                driver.setCommandSet(acCommandSet);
                listConfirmedCommandSets.add(driver);
            }
            final AcCommandSetArrayAdapter adapter = new AcCommandSetArrayAdapter(this, C0676R.layout.ac_command_set_list_item, listConfirmedCommandSets, this.mPosition);
            this.listView.setAdapter(adapter);
            this.listView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    adapter.setSelected(position);
                    TestConfirmedCommandSetListActivity.this.mPosition = position;
                    TestConfirmedCommandSetListActivity.this.getUserAcCapabilitiesForCommandSet(adapter.getItem(position).getCommandSet());
                }
            });
        }
    }

    private void getUserAcCapabilitiesForCommandSet(AcCommandSet selectedCommandSet) {
        if (selectedCommandSet != null) {
            RestServiceGenerator.getTadoInstallationRestService().getAcCommandSet(selectedCommandSet.getCommandType().toString(), selectedCommandSet.getCode().intValue()).enqueue(new C08543());
        }
    }
}
