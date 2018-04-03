package com.tado.android.times;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tado.C0676R;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.controllers.CapabilitiesController;
import com.tado.android.rest.model.Block;
import com.tado.android.rest.model.Temperature;
import com.tado.android.rest.model.ZoneSetting;
import com.tado.android.rest.model.installation.GenericZoneSetting;
import com.tado.android.rest.model.installation.GenericZoneSetting.TypeEnum;
import com.tado.android.schedule.model.ScheduleDayEnum;
import com.tado.android.schedule.model.ScheduleTypeEnum;
import com.tado.android.settings.interfaces.GenericCallbackListener;
import com.tado.android.settings.model.ScheduleSettings;
import com.tado.android.settings.model.TemperatureSettings;
import com.tado.android.utils.ColorFactory;
import com.tado.android.utils.Constants;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.TimeUtils;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import com.tado.android.views.SettingModelAdapter;
import com.tado.android.views.TadoZoneSettingsView;
import com.tado.android.views.TadoZoneSettingsView.OnZoneSettingChangedListener;
import com.tado.android.views.progressbar.ProgressBarComponent;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog.OnTimeSetListener;
import java.util.Iterator;
import java.util.List;
import org.joda.time.Period;

public class EditTimesDayActivity extends AppCompatActivity {
    public static final String EXTRA_BLOCK_ID = "blockId";
    public static final String EXTRA_EDIT_BLOCK = "editBlock";
    public static final String EXTRA_OPERATION = "mode";
    public static final String EXTRA_SELECTED_DAY = "selectedDay";
    private static final float OFF_TEMPERATURE_VALUE = 0.0f;
    private static final String TAG = "EditTimesDayActivity";
    @BindView(2131296619)
    View endTimeView;
    private boolean isInEditOperation;
    @BindView(2131296319)
    ImageView mAdvancedSettingsArrowImage;
    @BindView(2131296320)
    View mAdvancedSettingsLayout;
    @BindView(2131296613)
    TextView mAlwaysActiveInfo;
    @BindView(2131296608)
    SwitchCompat mAlwaysActiveSwitchView;
    private List<Block> mBlockList;
    private List<Block> mBlockOriginalList;
    private ScheduleDayEnum mDay;
    @BindView(2131296610)
    Button mDelete;
    private Block mEditBlock;
    @BindView(2131296611)
    TextView mEndBlockLabel;
    @BindView(2131296612)
    TextView mEndBlockTime;
    private boolean mExpanded = false;
    private String mId;
    @BindView(2131296880)
    ProgressBarComponent mProgressBar;
    private boolean mSavingInProgress = false;
    @BindView(2131296614)
    TextView mStartBlockLabel;
    @BindView(2131296615)
    TextView mStartBlockTime;
    @BindView(2131296607)
    TadoZoneSettingsView mTadoZoneSettingsView;
    private Toolbar mToolbar;
    @BindView(2131296990)
    View startTimeView;

    class C11861 implements OnClickListener {
        C11861() {
        }

        public void onClick(View v) {
            EditTimesDayActivity.this.onCloseClick();
        }
    }

    class C11872 implements OnCheckedChangeListener {
        C11872() {
        }

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            EditTimesDayActivity.this.mEditBlock.setGeolocationOverride(!isChecked);
            EditTimesDayActivity.this.mBlockList = TimesBlockUtils.updateModel(EditTimesDayActivity.this.mBlockList, EditTimesDayActivity.this.mBlockOriginalList, EditTimesDayActivity.this.mEditBlock, EditTimesDayActivity.this.mId);
            EditTimesDayActivity.this.updateWithBlockList();
            EditTimesDayActivity.this.updateGeolocationOverrideInfoView();
        }
    }

    class C11883 implements OnZoneSettingChangedListener {
        C11883() {
        }

        public void onZoneSettingChanged(ZoneSetting zoneSetting) {
            SettingModelAdapter.copyToGenericZoneSetting(EditTimesDayActivity.this.mEditBlock.getSetting(), zoneSetting);
            EditTimesDayActivity.this.changeActionBarColorWithAnimation(EditTimesDayActivity.this.getColor(EditTimesDayActivity.this.mEditBlock.getSetting()), EditTimesDayActivity.this.getColor(EditTimesDayActivity.this.mEditBlock.getSetting()));
        }
    }

    class C11905 implements DialogInterface.OnClickListener {
        C11905() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }

    class C11916 implements DialogInterface.OnClickListener {
        C11916() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            EditTimesDayActivity.this.finish();
        }
    }

    class C11927 implements GenericCallbackListener<List<Block>> {
        C11927() {
        }

        public void onSuccess(List<Block> list) {
            EditTimesDayActivity.this.mProgressBar.hideView();
            EditTimesDayActivity.this.finish();
        }

        public void onFailure() {
            EditTimesDayActivity.this.mProgressBar.hideView();
            EditTimesDayActivity.this.mSavingInProgress = false;
            EditTimesDayActivity.this.invalidateOptionsMenu();
        }
    }

    class C11938 implements DialogInterface.OnClickListener {
        C11938() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }

    class C11949 implements DialogInterface.OnClickListener {
        C11949() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            EditTimesDayActivity.this.deleteBlock();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_edit_times_day);
        ButterKnife.bind((Activity) this);
        Bundle extras = getIntent().getExtras();
        if (getIntent().hasExtra(EXTRA_SELECTED_DAY)) {
            this.mDay = (ScheduleDayEnum) extras.get(EXTRA_SELECTED_DAY);
            this.mId = extras.getString(EXTRA_BLOCK_ID);
            String operation = extras.getString("mode");
            if (operation != null) {
                this.isInEditOperation = operation.equalsIgnoreCase(EXTRA_EDIT_BLOCK);
            }
        }
        setupActionBar();
    }

    public void setupActionBar() {
        this.mToolbar = (Toolbar) findViewById(C0676R.id.toolbar);
        this.mToolbar.setTitle((int) C0676R.string.smartSchedule_home_editTimeBlock_title);
        this.mToolbar.setNavigationIcon((int) C0676R.drawable.ic_close_white_24dp);
        setSupportActionBar(this.mToolbar);
        this.mToolbar.setNavigationOnClickListener(new C11861());
    }

    protected void onResume() {
        super.onResume();
        initData();
        updateViews();
        if (this.isInEditOperation) {
            AnalyticsHelper.trackPageView(getApplication(), Screen.EDIT_SCHEDULE);
        } else {
            AnalyticsHelper.trackPageView(getApplication(), Screen.ADD_SCHEDULE);
        }
    }

    public void changeActionBarColorWithAnimation(int color) {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
    }

    private void changeActionBarColorWithAnimation(int oldVal, int darkColor) {
        TransitionDrawable t = new TransitionDrawable(new Drawable[]{new ColorDrawable(oldVal), new ColorDrawable(darkColor)});
        getSupportActionBar().setBackgroundDrawable(t);
        t.startTransition(300);
    }

    private void initData() {
        boolean z = true;
        this.mBlockOriginalList = ScheduleSettings.getBlocks(this.mDay);
        this.mBlockList = ScheduleSettings.getBlocks(this.mDay);
        this.mEditBlock = getEditBlock(this.mBlockList);
        this.mEditBlock.setInEditMode(true);
        SwitchCompat switchCompat = this.mAlwaysActiveSwitchView;
        if (this.mEditBlock.isGeolocationOverride()) {
            z = false;
        }
        switchCompat.setChecked(z);
        this.mAlwaysActiveSwitchView.setOnCheckedChangeListener(new C11872());
        changeActionBarColorWithAnimation(getColor(this.mEditBlock.getSetting()));
        updateGeolocationOverrideInfoView();
        this.mTadoZoneSettingsView.initViewModel(CapabilitiesController.INSTANCE.getZoneSettingViewConfiguration(), SettingModelAdapter.getZoneSetting(this.mEditBlock.getSetting()));
        this.mTadoZoneSettingsView.setOnZoneSettingChangedListener(new C11883());
    }

    private void updateGeolocationOverrideInfoView() {
        if (this.mEditBlock.isGeolocationOverride()) {
            this.mAlwaysActiveInfo.setText(C0676R.string.smartSchedule_home_editTimeBlock_advancedSettings_lbcOffLabel);
        } else {
            this.mAlwaysActiveInfo.setText(getResources().getString(C0676R.string.smartSchedule_home_editTimeBlock_advancedSettings_lbcOnLabel));
        }
    }

    private void updateViews() {
        if (getListSize(this.mBlockList) == 1) {
            this.mDelete.setEnabled(false);
        }
        if (this.isInEditOperation) {
            this.mDelete.setVisibility(0);
        } else {
            this.mDelete.setVisibility(4);
        }
        int endSeconds = this.mEditBlock.getEndSeconds();
        if (this.mEditBlock.getEndSeconds() == 0) {
        }
        if (this.mEditBlock.getStart() != null) {
            this.mStartBlockTime.setText(TimeUtils.formatTimeToAmPm(TimeUtils.getHours(this.mEditBlock.getStartSeconds()), TimeUtils.getMinutesRemainder(this.mEditBlock.getStartSeconds()), DateFormat.is24HourFormat(this)));
        } else {
            this.mStartBlockTime.setText("?");
        }
        if (this.mEditBlock.getEnd() != null) {
            this.mEndBlockTime.setText(TimeUtils.formatTimeToAmPm(TimeUtils.getHours(this.mEditBlock.getEndSeconds()), TimeUtils.getMinutesRemainder(this.mEditBlock.getEndSeconds()), DateFormat.is24HourFormat(this)));
        } else {
            this.mEndBlockTime.setText("?");
        }
        if (this.mEditBlock.getStartSeconds() == 0 && this.isInEditOperation) {
            this.mStartBlockTime.setEnabled(false);
            this.mStartBlockLabel.setEnabled(false);
            this.startTimeView.setClickable(false);
            this.startTimeView.setEnabled(false);
        }
        if (this.mEditBlock.getEndSeconds() == 86400 && this.isInEditOperation) {
            this.mEndBlockTime.setEnabled(false);
            this.mEndBlockLabel.setEnabled(false);
            this.endTimeView.setClickable(false);
            this.endTimeView.setEnabled(false);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0676R.menu.menu_action_save, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0676R.id.action_save) {
            return super.onOptionsItemSelected(item);
        }
        saveChanges();
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(C0676R.id.action_save).setVisible(!this.mSavingInProgress);
        return super.onPrepareOptionsMenu(menu);
    }

    public void onBackPressed() {
        onCloseClick();
    }

    private Block getEditBlock(List<Block> blocks) {
        for (Block b : blocks) {
            if (b.getId().equals(this.mId)) {
                return b;
            }
        }
        return getNewBlock(this.mId);
    }

    private void showTimePickerDialog(int seconds, final boolean isStartTime) {
        TimePickerDialog tpd = TimePickerDialog.newInstance(new OnTimeSetListener() {
            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                int minutes = EditTimesDayActivity.this.getValidMinuteEntry(hourOfDay, minute, isStartTime);
                if (isStartTime) {
                    EditTimesDayActivity.this.setStartTime(minutes / 60, minutes % 60);
                } else {
                    EditTimesDayActivity.this.setEndTime(minutes / 60, minutes % 60);
                }
                EditTimesDayActivity.this.mBlockList = TimesBlockUtils.updateModel(EditTimesDayActivity.this.mBlockList, EditTimesDayActivity.this.mBlockOriginalList, EditTimesDayActivity.this.mEditBlock, EditTimesDayActivity.this.mId);
                EditTimesDayActivity.this.updateWithBlockList();
            }
        }, TimeUtils.getHours(seconds), TimeUtils.getMinutesRemainder(seconds), DateFormat.is24HourFormat(this));
        tpd.setTimeInterval(1, 5, 60);
        Period period;
        if (isStartTime) {
            tpd.setTitle(getString(C0676R.string.smartSchedule_home_editTimeBlock_startField));
            period = new Period((long) (this.mEditBlock.getEndSeconds() * 1000));
            if (this.mEditBlock.getEndSeconds() == 0 || this.mEditBlock.getEndSeconds() == -1) {
                period = new Period(86400000);
            }
            period = period.minusMinutes(15).normalizedStandard();
            tpd.setMaxTime(period.getHours(), period.getMinutes(), 0);
        } else {
            tpd.setTitle(getString(C0676R.string.smartSchedule_home_editTimeBlock_endField));
            period = new Period((long) (this.mEditBlock.getStartSeconds() * 1000));
            if (this.mEditBlock.getStartSeconds() == 0 || this.mEditBlock.getStartSeconds() == -1) {
                period = new Period(0);
            }
            period.plusMinutes(15).normalizedStandard();
        }
        tpd.show(getFragmentManager(), "Timepickerdialog");
    }

    private void updateWithBlockList() {
        this.mEditBlock = getEditBlock(this.mBlockList);
        Util.deepTimeBlockCopy(this.mBlockOriginalList, this.mBlockList);
        updateViews();
    }

    private void onCloseClick() {
        if (hasChanges()) {
            new Builder(this).setMessage(getString(C0676R.string.smartSchedule_notifications_discardChangesMessage)).setPositiveButton(getString(C0676R.string.ok), new C11916()).setNegativeButton(getString(C0676R.string.cancel), new C11905()).show();
        } else {
            finish();
        }
    }

    private boolean hasChanges() {
        Block originalBlock = null;
        for (Block block : this.mBlockOriginalList) {
            if (block.getId().equals(this.mEditBlock.getId())) {
                originalBlock = block;
                break;
            }
        }
        return (originalBlock != null && originalBlock.getSetting().equals(this.mEditBlock.getSetting()) && originalBlock.getEndSeconds() == this.mEditBlock.getEndSeconds() && originalBlock.getStartSeconds() == this.mEditBlock.getStartSeconds() && originalBlock.isGeolocationOverride() == this.mEditBlock.isGeolocationOverride()) ? false : true;
    }

    private void saveChanges() {
        if (this.mEditBlock.getEnd() == null && this.mEditBlock.getStart() == null) {
            finish();
            return;
        }
        this.mEditBlock.setGeolocationOverride(!this.mAlwaysActiveSwitchView.isChecked());
        cleanAndSort(this.mBlockList);
        uploadBlocks();
    }

    private void uploadBlocks() {
        this.mProgressBar.showView();
        this.mSavingInProgress = true;
        invalidateOptionsMenu();
        ScheduleSettings.uploadBlocks(this.mDay, this.mBlockList, new C11927(), this);
    }

    public void onStartClick(View view) {
        if (this.mEditBlock.getStart() != null) {
            showTimePickerDialog(this.mEditBlock.getStartSeconds(), true);
        } else {
            showTimePickerDialog(0, true);
        }
    }

    public void onEndClick(View view) {
        if (this.mEditBlock.getEnd() != null) {
            showTimePickerDialog(this.mEditBlock.getEndSeconds(), false);
        } else {
            showTimePickerDialog(Constants.TIMES_BLOCK_MIN_LENGTH_IN_SECONDS, false);
        }
    }

    private void cleanAndSort(List<Block> list) {
        Iterator<Block> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (((Block) iterator.next()).isDeleteCandidate()) {
                iterator.remove();
            }
        }
        Util.sortBlockList(list);
    }

    private void deleteBlock() {
        Snitcher.start().log(3, "", "edit " + this.mEditBlock.getId() + " " + this.mEditBlock.getStartSeconds() + " " + this.mEditBlock.getEndSeconds(), new Object[0]);
        Iterator iterator = this.mBlockList.iterator();
        while (iterator.hasNext()) {
            Block block = (Block) iterator.next();
            if (!block.isDeleteCandidate()) {
                if (block.getEndSeconds() != this.mEditBlock.getStartSeconds() || block.getEndSeconds() == 0) {
                    if (!block.getId().equals(this.mEditBlock.getId())) {
                        if (block.getStartSeconds() == this.mEditBlock.getEndSeconds() && block.getStartSeconds() != 0) {
                            block.setStartSeconds(this.mEditBlock.getStartSeconds());
                            block.setInEditMode(true);
                            this.mId = block.getId();
                            break;
                        }
                    }
                    iterator.remove();
                    if (!this.mId.equals(this.mEditBlock.getId())) {
                        break;
                    }
                } else {
                    block.setEndSeconds(this.mEditBlock.getEndSeconds());
                    block.setInEditMode(true);
                    this.mId = block.getId();
                }
            }
        }
        this.mEditBlock = getEditBlock(this.mBlockList);
        Util.deepTimeBlockCopy(this.mBlockOriginalList, this.mBlockList);
        uploadBlocks();
    }

    private int getListSize(List<Block> list) {
        int size = 0;
        for (Block b : list) {
            if (!b.isDeleteCandidate()) {
                size++;
            }
        }
        return size;
    }

    public void onDeleteClick(View view) {
        if (getListSize(this.mBlockList) != 1) {
            new Builder(this).setMessage(getString(C0676R.string.smartSchedule_notifications_deleteTimeBlockMessage)).setPositiveButton(getString(C0676R.string.ok), new C11949()).setNegativeButton(getString(C0676R.string.cancel), new C11938()).show();
        }
    }

    private int getStartTimeFromNextBlock(int newBlockStartTime) {
        for (Block block : this.mBlockList) {
            if (!block.isDeleteCandidate() && block.getStart() != null && block.getStartSeconds() >= newBlockStartTime) {
                if (block.getStartSeconds() - newBlockStartTime < Constants.TIMES_BLOCK_MIN_LENGTH_IN_SECONDS) {
                    block.setStartSeconds(newBlockStartTime + Constants.TIMES_BLOCK_MIN_LENGTH_IN_SECONDS);
                }
                return block.getStartSeconds();
            }
        }
        return 0;
    }

    private int getEndTimeFromPreviousBlock(int newBlockEndTime) {
        int i = this.mBlockList.size() - 1;
        while (i >= 0) {
            Block block = (Block) this.mBlockList.get(i);
            if (block.isDeleteCandidate() || block.getEnd() == null || block.getEndSeconds() == 0 || block.getEndSeconds() > newBlockEndTime) {
                i--;
            } else {
                if (newBlockEndTime - block.getEndSeconds() < Constants.TIMES_BLOCK_MIN_LENGTH_IN_SECONDS) {
                    block.setEndSeconds(newBlockEndTime - 900);
                }
                return block.getEndSeconds();
            }
        }
        return 0;
    }

    private Block getNewBlock(String id) {
        if (this.mEditBlock != null) {
            return this.mEditBlock;
        }
        Util.sortTemperatureList(TemperatureSettings.getTemperatures());
        Block newBlock = new Block();
        newBlock.setId(id);
        newBlock.setSetting(new GenericZoneSetting(TypeEnum.HEATING, true, Temperature.fromCelsius(0.0f)));
        newBlock.setGeolocationOverride(false);
        if (ScheduleSettings.getActiveScheduleId() >= 0 && ScheduleSettings.getActiveScheduleId() <= ScheduleTypeEnum.values().length) {
            newBlock.setWeekdaysType(ScheduleTypeEnum.values()[ScheduleSettings.getActiveScheduleId()].getType());
        }
        newBlock.setDayType(this.mDay.getDay());
        return newBlock;
    }

    private void addBlock() {
        if (!(this.mEditBlock.getEndSeconds() == 0 || this.mEditBlock.getStartSeconds() == 0)) {
            for (Block block : this.mBlockList) {
                if (this.mEditBlock.getEndSeconds() > block.getStartSeconds() && this.mEditBlock.getEndSeconds() < block.getEndSeconds()) {
                    block.setStartSeconds(this.mEditBlock.getEndSeconds());
                } else if (this.mEditBlock.getStartSeconds() >= block.getStartSeconds() && this.mEditBlock.getEndSeconds() < block.getEndSeconds()) {
                    block.setEndSeconds(this.mEditBlock.getStartSeconds());
                }
            }
        }
        this.mBlockList.add(this.mEditBlock);
        Util.sortBlockList(this.mBlockList);
        Util.deepTimeBlockCopy(this.mBlockOriginalList, this.mBlockList);
    }

    private void setStartTime(int hourOfDay, int minute) {
        Snitcher.start().log(3, TAG, String.format("setStartTime %d:%d", new Object[]{Integer.valueOf(hourOfDay), Integer.valueOf(minute)}), new Object[0]);
        this.mEditBlock.setStartSeconds(TimeUtils.getSeconds(hourOfDay, minute));
        if (this.mEditBlock.getEnd() == null) {
            this.mEditBlock.setEndSeconds(getStartTimeFromNextBlock(this.mEditBlock.getStartSeconds()));
            addBlock();
        }
        invalidateOptionsMenu();
    }

    private void setEndTime(int hourOfDay, int minute) {
        Snitcher.start().log(3, TAG, String.format("setEndTime %d:%d", new Object[]{Integer.valueOf(hourOfDay), Integer.valueOf(minute)}), new Object[0]);
        this.mEditBlock.setEndSeconds(TimeUtils.getSeconds(hourOfDay, minute));
        if (this.mEditBlock.getStart() == null) {
            this.mEditBlock.setStartSeconds(getEndTimeFromPreviousBlock(this.mEditBlock.getEndSeconds()));
            addBlock();
        }
        invalidateOptionsMenu();
    }

    private int getValidMinuteEntry(int hourOfDay, int minutes, boolean isStartTime) {
        int totalMinutes = TimeUtils.getMinutes(hourOfDay, minutes);
        if (isStartTime) {
            if (totalMinutes < 15) {
                return TimeUtils.getMinutes(0, 0);
            }
            return totalMinutes;
        } else if (totalMinutes > TimeUtils.getMinutes(24, 0) - 15 || totalMinutes == 0) {
            return TimeUtils.getMinutes(24, 0);
        } else {
            if (TimeUtils.getSeconds(hourOfDay, minutes) > this.mEditBlock.getStartSeconds()) {
                return totalMinutes;
            }
            int newSeconds = this.mEditBlock.getStartSeconds() + Constants.TIMES_BLOCK_MIN_LENGTH_IN_SECONDS;
            return TimeUtils.getMinutes(TimeUtils.getHours(newSeconds), TimeUtils.getMinutesRemainder(newSeconds));
        }
    }

    public void temperatureLayoutOnClick(View view) {
        Animation animation;
        int i;
        boolean z = false;
        if (this.mExpanded) {
            animation = AnimationUtils.loadAnimation(this, C0676R.anim.expand_show);
        } else {
            animation = AnimationUtils.loadAnimation(this, C0676R.anim.expand_hide);
        }
        animation.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                int i;
                EditTimesDayActivity.this.mAdvancedSettingsLayout.requestLayout();
                View view = EditTimesDayActivity.this.mAdvancedSettingsLayout;
                if (EditTimesDayActivity.this.mExpanded) {
                    i = 8;
                } else {
                    i = 0;
                }
                view.setVisibility(i);
                EditTimesDayActivity.this.mSavingInProgress = false;
                EditTimesDayActivity.this.invalidateOptionsMenu();
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        Animation arrowAnimation = AnimationUtils.loadAnimation(this, C0676R.anim.arrow_rotate_down);
        if (!this.mExpanded) {
            arrowAnimation = AnimationUtils.loadAnimation(this, C0676R.anim.arrow_rotate_up);
        }
        arrowAnimation.setFillAfter(true);
        View view2 = this.mAdvancedSettingsLayout;
        if (this.mExpanded) {
            i = 8;
        } else {
            i = 0;
        }
        view2.setVisibility(i);
        this.mAdvancedSettingsArrowImage.startAnimation(arrowAnimation);
        if (!this.mExpanded) {
            z = true;
        }
        this.mExpanded = z;
    }

    private int getColor(GenericZoneSetting setting) {
        if (setting.getPowerBoolean() && setting.getTemperature() != null) {
            float temperature = setting.getTemperature().getTemperatureValue();
        }
        return ColorFactory.getBackgroundColorPair(setting, UserConfig.getCurrentZone().intValue()).darkColor;
    }
}
