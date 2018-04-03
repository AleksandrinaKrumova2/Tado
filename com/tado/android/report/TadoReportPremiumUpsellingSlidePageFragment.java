package com.tado.android.report;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.rest.model.DayReport;
import org.joda.time.DateTime;
import retrofit2.Call;

public class TadoReportPremiumUpsellingSlidePageFragment extends Fragment {
    private static final String POSITION = "position";
    private static final String SELECTED_DATE = "selected_date";
    private Call<DayReport> dayReportCall;
    private FrameLayout parentView;
    private int position;
    private boolean requestedData = false;
    private DateTime selectedDate;
    private TadoReportView tadoReport;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup rootView = (ViewGroup) inflater.inflate(C0676R.layout.fragment_tado_report_premium_upselling_screen_slide, container, false);
        Bundle bundle = getArguments();
        if (savedInstanceState == null) {
            this.selectedDate = (DateTime) bundle.getSerializable(SELECTED_DATE);
            this.position = bundle.getInt(POSITION);
        } else {
            this.selectedDate = (DateTime) savedInstanceState.getSerializable(SELECTED_DATE);
            this.position = savedInstanceState.getInt(POSITION);
        }
        this.parentView = (FrameLayout) rootView.findViewById(C0676R.id.content);
        initReport();
        return rootView;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SELECTED_DATE, this.selectedDate);
        outState.putInt(POSITION, this.position);
    }

    public void onResume() {
        super.onResume();
    }

    public void onDestroyView() {
        TadoApplication.getBus().unregister(this);
        super.onDestroyView();
    }

    public void onPause() {
        super.onPause();
    }

    public static Fragment newInstance(Context context, DateTime selectedDate, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(SELECTED_DATE, selectedDate);
        bundle.putInt(POSITION, position);
        return Fragment.instantiate(context, TadoReportPremiumUpsellingSlidePageFragment.class.getName(), bundle);
    }

    private void initReport() {
        DayReport dayReport = new DayReport();
        TadoReportPremiumUpsellingViewFactory factory = new TadoReportPremiumUpsellingViewFactory();
        if (getActivity() != null) {
            this.tadoReport = factory.createTadoReportView(getActivity(), dayReport, this.selectedDate);
            this.parentView.addView(this.tadoReport, -1, -1);
        }
    }
}
