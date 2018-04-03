package com.tado.android.report;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.squareup.otto.Subscribe;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.report.model.ChartToolbarButtonStateChangeEvent;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.DayReport;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import retrofit2.Call;
import retrofit2.Response;

public class TadoReportScreenSlidePageFragment extends Fragment {
    private static final String POSITION = "position";
    private static final String SELECTED_DATE = "selected_date";
    private Call<DayReport> dayReportCall;
    private FrameLayout parentView;
    private int position;
    private ProgressBar progressBar;
    private boolean requestedData = false;
    private DateTime selectedDate;
    private TadoReportView tadoReport;

    class C10601 extends TadoCallback<DayReport> {

        class C10581 implements OnClickListener {
            C10581() {
            }

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }

        C10601() {
        }

        public void onResponse(final Call<DayReport> call, Response<DayReport> response) {
            super.onResponse(call, response);
            if (TadoReportScreenSlidePageFragment.this.isAdded()) {
                TadoReportScreenSlidePageFragment.this.progressBar.setVisibility(8);
                if (response.isSuccessful()) {
                    TadoReportScreenSlidePageFragment.this.initReports((DayReport) response.body());
                } else if (response.code() == 503) {
                    TadoReportScreenSlidePageFragment.this.requestedData = false;
                    new Builder(TadoReportScreenSlidePageFragment.this.getActivity()).setMessage(C0676R.string.report_errors_reportCurrentlyNotAvailable_message).setPositiveButton(C0676R.string.report_errors_reportCurrentlyNotAvailable_retryButton, new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            C10601.this.retry(call);
                            dialog.dismiss();
                        }
                    }).setNegativeButton(C0676R.string.report_errors_reportCurrentlyNotAvailable_closeButton, new C10581()).show();
                }
            }
        }

        public void onFailure(Call<DayReport> call, Throwable t) {
            super.onFailure(call, t);
            TadoReportScreenSlidePageFragment.this.requestedData = false;
            if (TadoReportScreenSlidePageFragment.this.isAdded()) {
                TadoReportScreenSlidePageFragment.this.progressBar.setVisibility(8);
                if (!Util.isNetworkAvailable()) {
                    AlertDialogs.showSimpleWarning("", TadoReportScreenSlidePageFragment.this.getResources().getString(C0676R.string.login_login_noconnection), "OK", TadoReportScreenSlidePageFragment.this.getActivity(), null);
                }
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup rootView = (ViewGroup) inflater.inflate(C0676R.layout.fragment_tado_report_screen_slide, container, false);
        Bundle bundle = getArguments();
        this.progressBar = (ProgressBar) rootView.findViewById(C0676R.id.report_progressBar);
        if (savedInstanceState == null) {
            this.selectedDate = (DateTime) bundle.getSerializable(SELECTED_DATE);
            this.position = bundle.getInt(POSITION);
        } else {
            this.selectedDate = (DateTime) savedInstanceState.getSerializable(SELECTED_DATE);
            this.position = savedInstanceState.getInt(POSITION);
        }
        this.parentView = (FrameLayout) rootView.findViewById(C0676R.id.content);
        TadoApplication.getBus().register(this);
        return rootView;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SELECTED_DATE, this.selectedDate);
        outState.putInt(POSITION, this.position);
    }

    public void onResume() {
        super.onResume();
        if (!(getActivity() == null || this.requestedData)) {
            requestNewData(this.selectedDate);
        }
        this.parentView.invalidate();
    }

    public void onDestroyView() {
        TadoApplication.getBus().unregister(this);
        super.onDestroyView();
    }

    public void onPause() {
        super.onPause();
        if (this.dayReportCall != null) {
            this.dayReportCall.cancel();
        }
    }

    public static Fragment newInstance(Context context, DateTime selectedDate, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(SELECTED_DATE, selectedDate);
        bundle.putInt(POSITION, position);
        return Fragment.instantiate(context, TadoReportScreenSlidePageFragment.class.getName(), bundle);
    }

    private void requestNewData(DateTime selectedDate) {
        this.requestedData = true;
        this.selectedDate = selectedDate;
        DateTimeFormatter dformat = DateTimeFormat.forPattern("yyyy-MM-dd'");
        this.progressBar.setVisibility(0);
        this.dayReportCall = RestServiceGenerator.getTadoRestService().getDayReport(UserConfig.getHomeId(), UserConfig.getCurrentZone().intValue(), selectedDate.toDateTimeISO().toString(dformat), RestServiceGenerator.getCredentialsMap());
        this.dayReportCall.enqueue(new C10601());
    }

    private void initReports(DayReport dayReport) {
        TadoReportViewFactory factory = new TadoReportViewFactory();
        if (getActivity() != null) {
            this.tadoReport = factory.createTadoReportView(getActivity(), dayReport, this.selectedDate);
            this.parentView.addView(this.tadoReport, -1, -1);
        }
    }

    @Subscribe
    public void onChartToolbarButtonStateChange(ChartToolbarButtonStateChangeEvent event) {
        if (this.tadoReport != null) {
            this.tadoReport.onToolbarButtonClick(event.getButtonTypeEnum(), event.isEnabled());
        }
    }
}
