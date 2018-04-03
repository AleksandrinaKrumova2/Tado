package com.tado.android.times;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.controllers.CapabilitiesController;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.Block;
import com.tado.android.rest.model.Timetable;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.rest.service.TadoApiService;
import com.tado.android.schedule.model.ScheduleDayEnum;
import com.tado.android.schedule.model.ScheduleTypeEnum;
import com.tado.android.settings.interfaces.GenericCallbackListener;
import com.tado.android.settings.model.ScheduleSettings;
import com.tado.android.times.view.BlockView;
import com.tado.android.times.view.CommunicationView;
import com.tado.android.times.view.TimeBlocksView2;
import com.tado.android.times.view.TimeBlocksView2.OnBlockClickListener;
import com.tado.android.times.view.TimeBlocksView2.OnBlockSplitListener;
import com.tado.android.times.view.TimeBlocksView2.OnBlockZoomed;
import com.tado.android.times.view.TimeBlocksView2.OnEditModeListener;
import com.tado.android.times.view.interfaces.FragmentBackButtonHandler;
import com.tado.android.times.view.interfaces.FragmentListener;
import com.tado.android.utils.UserConfig;
import com.tado.android.views.progressbar.ProgressBarComponent;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class TimesFragment2 extends Fragment implements FragmentListener, FragmentBackButtonHandler {
    private View blockerScreen;
    private CommunicationView communicationView;
    private LinearLayout container;
    OnMenuItemClickListener daysMenuListener = new C12168();
    private FloatingActionButton fab;
    private boolean isAddingMode = false;
    private boolean isZommedIn = false;
    private OnBlockClickListener onBlockClickListener = new OnBlockClickListener() {
        public void onBlockClick(String blockId, ScheduleDayEnum day) {
            Intent intent = new Intent(TimesFragment2.this.getContext(), EditTimesDayActivity.class);
            intent.putExtra(EditTimesDayActivity.EXTRA_SELECTED_DAY, day);
            intent.putExtra(EditTimesDayActivity.EXTRA_BLOCK_ID, blockId);
            intent.putExtra("mode", EditTimesDayActivity.EXTRA_EDIT_BLOCK);
            TimesFragment2.this.startActivity(intent);
        }
    };
    private OnBlockSplitListener onBlockSplitListener = new OnBlockSplitListener() {
        public void onBlockSplit(BlockView blockView, Block newDataBlock, ScheduleDayEnum day, List<Block> blockList) {
            TimesFragment2.this.showProgress();
            TimesFragment2.this.setAddingMode(false);
            blockList.add(newDataBlock);
            ScheduleSettings.uploadBlocks(day, blockList, TimesFragment2.this.scheduleBlocksCallback, TimesFragment2.this.getActivity());
        }
    };
    private OnBlockZoomed onBlockZoomed = new OnBlockZoomed() {
        public void onDayZoomedIn() {
            TimesFragment2.this.isZommedIn = true;
        }

        public void onDayZoomedOut() {
            TimesFragment2.this.isZommedIn = false;
        }
    };
    private OnEditModeListener onEditModeListener = new OnEditModeListener() {
        public void onEditModeStart(ScheduleDayEnum day) {
            TimesFragment2.this.fadeOutView(TimesFragment2.this.fab);
            TimesFragment2.this.fadeOutView(TimesFragment2.this.findDayName(day));
            TimesFragment2.this.communicationView.setDraggingText();
        }

        public void onEditModeEnd(ScheduleDayEnum day, List<Block> blockList, boolean hasChanges, boolean isDeletion) {
            TimesFragment2.this.fadeInView(TimesFragment2.this.fab);
            TimesFragment2.this.fadeInView(TimesFragment2.this.findDayName(day));
            TimesFragment2.this.communicationView.setReleaseText();
            if (isDeletion) {
                TimesFragment2.this.showDeleteConfirmation(day, blockList);
            } else if (hasChanges) {
                TimesFragment2.this.showProgress();
                ScheduleSettings.uploadBlocks(day, blockList, TimesFragment2.this.scheduleBlocksCallback, TimesFragment2.this.getActivity());
            }
        }
    };
    private ProgressBarComponent progressBar;
    private GenericCallbackListener<List<Block>> scheduleBlocksCallback = new C12179();
    private TextView scheduleDaysTextView;
    OnClickListener showDaysPopup = new C12157();

    class C12091 implements OnClickListener {
        C12091() {
        }

        public void onClick(View v) {
            TimesFragment2.this.setAddingMode(true);
        }
    }

    class C12113 extends TadoCallback<Timetable> {
        C12113() {
        }

        public void onResponse(Call<Timetable> call, Response<Timetable> response) {
            super.onResponse(call, response);
            if (response.isSuccessful()) {
                ScheduleSettings.setActiveScheduleId(((Timetable) response.body()).getId());
            }
        }
    }

    class C12124 extends ArrayList<ScheduleDayEnum> {
        C12124() {
            add(ScheduleDayEnum.MONDAY_TO_SUNDAY);
        }
    }

    class C12135 extends ArrayList<ScheduleDayEnum> {
        C12135() {
            add(ScheduleDayEnum.MONDAY_TO_FRIDAY);
            add(ScheduleDayEnum.SATURDAY);
            add(ScheduleDayEnum.SUNDAY);
        }
    }

    class C12146 extends ArrayList<ScheduleDayEnum> {
        C12146() {
            add(ScheduleDayEnum.MONDAY);
            add(ScheduleDayEnum.TUESDAY);
            add(ScheduleDayEnum.WEDNESDAY);
            add(ScheduleDayEnum.THURSDAY);
            add(ScheduleDayEnum.FRIDAY);
            add(ScheduleDayEnum.SATURDAY);
            add(ScheduleDayEnum.SUNDAY);
        }
    }

    class C12157 implements OnClickListener {
        C12157() {
        }

        public void onClick(View v) {
            PopupMenu popup = new PopupMenu(TimesFragment2.this.getContext(), v);
            popup.setOnMenuItemClickListener(TimesFragment2.this.daysMenuListener);
            popup.getMenuInflater().inflate(C0676R.menu.schedule_day_display, popup.getMenu());
            popup.show();
        }
    }

    class C12168 implements OnMenuItemClickListener {
        C12168() {
        }

        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case C0676R.id.times_one_day:
                    TimesFragment2.this.callUpdateScheduleType(ScheduleTypeEnum.ONEDAY);
                    AnalyticsHelper.trackEvent(TimesFragment2.this.getActivity(), Screen.TIMES_OVERVIEW, "DayType", "One");
                    return true;
                case C0676R.id.times_seven_day:
                    TimesFragment2.this.callUpdateScheduleType(ScheduleTypeEnum.SEVENDAY);
                    AnalyticsHelper.trackEvent(TimesFragment2.this.getActivity(), Screen.TIMES_OVERVIEW, "DayType", "Seven");
                    return true;
                case C0676R.id.times_three_day:
                    TimesFragment2.this.callUpdateScheduleType(ScheduleTypeEnum.THREEDAY);
                    AnalyticsHelper.trackEvent(TimesFragment2.this.getActivity(), Screen.TIMES_OVERVIEW, "DayType", "Three");
                    return true;
                default:
                    return false;
            }
        }
    }

    class C12179 implements GenericCallbackListener<List<Block>> {
        C12179() {
        }

        public void onSuccess(List<Block> list) {
            TimesFragment2.this.hideProgress(true);
            TimesFragment2.this.setAddingMode(false);
            TimesFragment2.this.reload();
        }

        public void onFailure() {
            TimesFragment2.this.hideProgress(false);
            TimesFragment2.this.setAddingMode(false);
            TimesFragment2.this.reload();
        }
    }

    public static TimesFragment2 newInstance() {
        return new TimesFragment2();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(C0676R.layout.times_fragment2, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (((SmartScheduleActivity) getActivity()).bus != null) {
            ((SmartScheduleActivity) getActivity()).bus.register(this);
        }
        this.container = (LinearLayout) view.findViewById(C0676R.id.week_container);
        this.fab = (FloatingActionButton) view.findViewById(C0676R.id.fab);
        this.fab.setOnClickListener(new C12091());
        this.scheduleDaysTextView = (TextView) view.findViewById(C0676R.id.schedule_days_textview);
        ((ViewGroup) this.scheduleDaysTextView.getParent()).setOnClickListener(this.showDaysPopup);
        this.progressBar = (ProgressBarComponent) view.findViewById(C0676R.id.progressBar);
        this.blockerScreen = view.findViewById(C0676R.id.blocker);
        this.communicationView = (CommunicationView) view.findViewById(C0676R.id.communication_area);
        loadData();
        configureCommArea();
    }

    public void onPause() {
        super.onPause();
        if (((SmartScheduleActivity) getActivity()).bus != null) {
            ((SmartScheduleActivity) getActivity()).bus.unregister(this);
        }
    }

    private void configureCommArea() {
        if (CapabilitiesController.INSTANCE.isCoolingZone()) {
            this.communicationView.configureAsCoolingHomeView();
        } else {
            this.communicationView.configureAsHeatingHomeView();
        }
    }

    private void setAddingMode(boolean enableAddingMode) {
        this.isAddingMode = enableAddingMode;
        if (this.isAddingMode) {
            fadeOutView(this.fab);
        } else {
            fadeInView(this.fab);
        }
        for (int i = 0; i < this.container.getChildCount(); i++) {
            TimeBlocksView2 day = (TimeBlocksView2) findViewByType(TimeBlocksView2.class, (ViewGroup) this.container.getChildAt(i));
            if (day != null) {
                if (!day.isAddModeEnabled() || this.isAddingMode) {
                    day.setAddMode(true);
                } else {
                    day.setAddMode(false);
                }
            }
        }
    }

    private View findViewByType(Class<? extends View> type, ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);
            if (view.getClass() == type) {
                return view;
            }
            if (view instanceof ViewGroup) {
                return findViewByType(type, (ViewGroup) view);
            }
        }
        return null;
    }

    private void fadeOutView(final View view) {
        if (view != null) {
            ViewCompat.animate(view).scaleX(0.0f).scaleY(0.0f).alpha(0.0f).setStartDelay(0).setDuration(300).withEndAction(new Runnable() {
                public void run() {
                    view.setVisibility(4);
                }
            }).start();
        }
    }

    private void fadeInView(View view) {
        if (view != null) {
            view.setVisibility(0);
            ViewCompat.animate(view).scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setStartDelay(0).setDuration(300).start();
        }
    }

    private void callUpdateScheduleType(ScheduleTypeEnum type) {
        showProgress();
        ScheduleSettings.setActiveScheduleId(type.getId());
        ScheduleSettings.setSelectedScheduleType(type);
        TadoApiService tadoApiService = RestServiceGenerator.getTadoRestService();
        Timetable timetable = new Timetable();
        timetable.setType(null);
        timetable.setId(ScheduleSettings.getActiveScheduleId());
        tadoApiService.setActiveTimetable(UserConfig.getHomeId(), UserConfig.getCurrentZone().intValue(), RestServiceGenerator.getCredentialsMap(), timetable).enqueue(new C12113());
        callGetScheduleBlocks();
    }

    public void reload() {
        this.isZommedIn = false;
        initModel(ScheduleSettings.getActiveScheduleId());
    }

    public void onResume() {
        super.onResume();
        initModel(ScheduleSettings.getActiveScheduleId());
        this.isZommedIn = false;
        this.isAddingMode = false;
        fadeInView(this.fab);
    }

    private void initModel(int type) {
        if (getContext() != null) {
            List<ScheduleDayEnum> list;
            if (type == ScheduleTypeEnum.ONEDAY.getId()) {
                list = new C12124();
                this.scheduleDaysTextView.setText(C0676R.string.smartSchedule_home_oneDayScheduleLabel);
            } else if (type == ScheduleTypeEnum.THREEDAY.getId()) {
                list = new C12135();
                this.scheduleDaysTextView.setText(C0676R.string.smartSchedule_home_threeDayScheduleLabel);
            } else {
                list = new C12146();
                this.scheduleDaysTextView.setText(C0676R.string.smartSchedule_home_sevenDayScheduleLabel);
            }
            this.container.removeAllViews();
            for (ScheduleDayEnum day : list) {
                ViewGroup row = (ViewGroup) LayoutInflater.from(getContext()).inflate(C0676R.layout.day_times_row, this.container, false);
                TextView dayName = (TextView) row.findViewById(C0676R.id.day_name);
                dayName.setText(day.getLocalizedDay());
                dayName.setTag(day);
                TimeBlocksView2 blocksView = (TimeBlocksView2) row.findViewById(C0676R.id.day_blocks);
                blocksView.setOnBlockClickListener(this.onBlockClickListener);
                blocksView.setOnBlockSplitListener(this.onBlockSplitListener);
                blocksView.setOnBlockZoomedListener(this.onBlockZoomed);
                blocksView.setOnEditModeListener(this.onEditModeListener);
                blocksView.setBlockList(ScheduleSettings.getBlocks(day), day);
                this.container.addView(row);
            }
        }
    }

    public void showProgress() {
        this.progressBar.showView();
        this.blockerScreen.setVisibility(0);
    }

    public void hideProgress(boolean animation) {
        if (animation) {
            this.progressBar.hideViewWithAnimation();
        } else {
            this.progressBar.hideView();
        }
        this.blockerScreen.setVisibility(8);
    }

    public void zoomOut() {
        for (int i = 0; i < this.container.getChildCount(); i++) {
            TimeBlocksView2 day = (TimeBlocksView2) findViewByType(TimeBlocksView2.class, (ViewGroup) this.container.getChildAt(i));
            if (day != null && day.isZoomedIn()) {
                day.zoomOut();
            }
        }
    }

    private void loadData() {
        showProgress();
        RestServiceGenerator.getTadoRestService().getActiveTimetable(UserConfig.getHomeId(), UserConfig.getCurrentZone().intValue(), RestServiceGenerator.getCredentialsMap()).enqueue(new TadoCallback<Timetable>() {
            public void onResponse(Call<Timetable> call, Response<Timetable> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    ScheduleSettings.setActiveScheduleId(((Timetable) response.body()).getId());
                    TimesFragment2.this.callGetScheduleBlocks();
                    return;
                }
                TimesFragment2.this.hideProgress(true);
            }

            public void onFailure(Call<Timetable> call, Throwable t) {
                super.onFailure(call, t);
                TimesFragment2.this.hideProgress(false);
            }
        });
    }

    public void callGetScheduleBlocks() {
        RestServiceGenerator.getTadoRestService().getTimetableBlocks(UserConfig.getHomeId(), UserConfig.getCurrentZone().intValue(), ScheduleSettings.getActiveScheduleId(), RestServiceGenerator.getCredentialsMap()).enqueue(new TadoCallback<List<Block>>() {
            public void onResponse(Call<List<Block>> call, Response<List<Block>> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    ScheduleSettings.saveBlocks(UserConfig.getCurrentZone().intValue(), (List) response.body());
                    TimesFragment2.this.reload();
                }
                TimesFragment2.this.hideProgress(true);
            }

            public void onFailure(Call<List<Block>> call, Throwable t) {
                super.onFailure(call, t);
                TimesFragment2.this.hideProgress(false);
            }
        });
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        new MenuInflater(getContext()).inflate(C0676R.menu.block_view_edit_context_menu, menu);
    }

    public boolean handleBackButton() {
        boolean handled = false;
        if (this.isAddingMode) {
            setAddingMode(false);
            fadeInView(this.fab);
            handled = true;
        }
        if (!this.isZommedIn) {
            return handled;
        }
        zoomOut();
        return true;
    }

    public View findDayName(ScheduleDayEnum day) {
        for (int i = 0; i < this.container.getChildCount(); i++) {
            View dayName = this.container.getChildAt(i).findViewById(C0676R.id.day_name);
            if (dayName.getTag() == day) {
                return dayName;
            }
        }
        return null;
    }

    public void showDeleteConfirmation(final ScheduleDayEnum day, final List<Block> blockList) {
        if (isAdded()) {
            new Builder(getContext()).setMessage(getString(C0676R.string.smartSchedule_notifications_deleteTimeBlockMessage)).setPositiveButton(getString(C0676R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    TimesFragment2.this.showProgress();
                    ScheduleSettings.uploadBlocks(day, blockList, TimesFragment2.this.scheduleBlocksCallback, TimesFragment2.this.getActivity());
                }
            }).setNegativeButton(getString(C0676R.string.cancel), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    TimesFragment2.this.reload();
                }
            }).show();
        }
    }
}
