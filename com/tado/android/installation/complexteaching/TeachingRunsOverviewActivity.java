package com.tado.android.installation.complexteaching;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.tado.C0676R;
import com.tado.android.adapters.TeachingRunAdapter;
import com.tado.android.app.TadoApplication;
import com.tado.android.entities.TeachingRun;
import com.tado.android.entities.TeachingRunSummary;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.installation.teaching.SetACToSettingActivity;
import java.util.ArrayList;
import java.util.Collections;

public class TeachingRunsOverviewActivity extends ACInstallationBaseActivity {
    ArrayList<TeachingRun> list;

    protected void onCreate(Bundle savedInstanceState) {
        boolean hasTemperatures;
        boolean hasFanSpeed;
        boolean hasSwing;
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_teaching_runs_overview);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_title);
        ComplexTeachingController state = ComplexTeachingController.getComplexTeachingController();
        TeachingRun[] teachingRuns = state.getCurrentTeachingMode().getRuns();
        this.list = new ArrayList();
        Collections.addAll(this.list, teachingRuns);
        int highlightIndex = state.getCurrentRunIndex();
        TeachingRunSummary teachingRunSummary = teachingRuns[highlightIndex].getSummary();
        ListView listView = (ListView) findViewById(C0676R.id.listView);
        if (teachingRunSummary.getTemperatures() != null) {
            hasTemperatures = true;
        } else {
            hasTemperatures = false;
        }
        if (teachingRunSummary.getFanSpeed() != null) {
            hasFanSpeed = true;
        } else {
            hasFanSpeed = false;
        }
        if (teachingRunSummary.getSwing() != null) {
            hasSwing = true;
        } else {
            hasSwing = false;
        }
        this.titleTemplateTextview.setText(getTitleText(hasTemperatures, hasFanSpeed, hasSwing, teachingRunSummary));
        this.proceedButton.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_runOverview_confirmButton);
        listView.setAdapter(new TeachingRunAdapter(TadoApplication.getTadoAppContext(), C0676R.layout.teaching_run_item, C0676R.id.temperature_range_textview, this.list, highlightIndex));
    }

    private String getTitleText(boolean hasTemperatures, boolean hasFanSpeed, boolean hasSwing, TeachingRunSummary teachingRunSummary) {
        if (hasFanSpeed && hasSwing && hasTemperatures) {
            r1 = new Object[3];
            r1[1] = getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_runOverview_fanSpeedParameter, new Object[]{teachingRunSummary.getFanSpeed()});
            r1[2] = getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_runOverview_swingParameter, new Object[]{teachingRunSummary.getSwing()});
            return getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_runOverview_threeParameterTitle, r1);
        } else if (hasSwing || hasFanSpeed) {
            return getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_runOverview_twoParameterTitle, new Object[]{getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_runOverview_allTemperaturesParameter), getSecondParameterText(hasFanSpeed, teachingRunSummary.getFanSpeed(), teachingRunSummary.getSwing())});
        } else {
            return getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_runOverview_oneParameterTitle, new Object[]{getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_runOverview_allTemperaturesParameter)});
        }
    }

    private String getSecondParameterText(boolean hasFanSpeed, String fanSpeed, String swing) {
        if (hasFanSpeed) {
            return getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_runOverview_fanSpeedParameter, new Object[]{fanSpeed});
        }
        return getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_runOverview_swingParameter, new Object[]{swing});
    }

    public void proceedClick(View view) {
        InstallationProcessController.startActivity((Activity) this, SetACToSettingActivity.class, false);
    }
}
