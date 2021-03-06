package com.wdullaer.materialdatetimepicker.date;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.View.MeasureSpec;
import android.view.accessibility.AccessibilityEvent;
import com.wdullaer.materialdatetimepicker.R;
import com.wdullaer.materialdatetimepicker.date.MonthAdapter.CalendarDay;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public abstract class MonthView extends View {
    protected static int DAY_HIGHLIGHT_CIRCLE_MARGIN = 0;
    protected static int DAY_HIGHLIGHT_CIRCLE_SIZE = 0;
    protected static int DAY_SELECTED_CIRCLE_SIZE = 0;
    protected static int DAY_SEPARATOR_WIDTH = 1;
    protected static final int DEFAULT_FOCUS_MONTH = -1;
    protected static int DEFAULT_HEIGHT = 32;
    protected static final int DEFAULT_NUM_DAYS = 7;
    protected static final int DEFAULT_NUM_ROWS = 6;
    protected static final int DEFAULT_SELECTED_DAY = -1;
    protected static final int DEFAULT_SHOW_WK_NUM = 0;
    protected static final int DEFAULT_WEEK_START = 1;
    protected static final int MAX_NUM_ROWS = 6;
    protected static int MINI_DAY_NUMBER_TEXT_SIZE = 0;
    protected static int MIN_HEIGHT = 10;
    protected static int MONTH_DAY_LABEL_TEXT_SIZE = 0;
    protected static int MONTH_HEADER_SIZE = 0;
    protected static int MONTH_LABEL_TEXT_SIZE = 0;
    private static final int SELECTED_CIRCLE_ALPHA = 255;
    protected static float mScale = 0.0f;
    private final Calendar mCalendar;
    protected DatePickerController mController;
    protected final Calendar mDayLabelCalendar;
    private int mDayOfWeekStart;
    private String mDayOfWeekTypeface;
    protected int mDayTextColor;
    protected int mDisabledDayTextColor;
    protected int mEdgePadding;
    protected int mFirstJulianDay;
    protected int mFirstMonth;
    protected boolean mHasToday;
    protected int mHighlightedDayTextColor;
    protected int mLastMonth;
    private boolean mLockAccessibilityDelegate;
    protected int mMonth;
    protected Paint mMonthDayLabelPaint;
    protected int mMonthDayTextColor;
    protected Paint mMonthNumPaint;
    protected int mMonthTitleColor;
    protected Paint mMonthTitlePaint;
    private String mMonthTitleTypeface;
    protected int mNumCells;
    protected int mNumDays;
    protected int mNumRows;
    protected OnDayClickListener mOnDayClickListener;
    protected int mRowHeight;
    protected Paint mSelectedCirclePaint;
    protected int mSelectedDay;
    protected int mSelectedDayTextColor;
    protected int mSelectedLeft;
    protected int mSelectedRight;
    private final StringBuilder mStringBuilder;
    protected int mToday;
    protected int mTodayNumberColor;
    private final MonthViewTouchHelper mTouchHelper;
    protected int mWeekStart;
    protected int mWidth;
    protected int mYear;
    private SimpleDateFormat weekDayLabelFormatter;

    public interface OnDayClickListener {
        void onDayClick(MonthView monthView, CalendarDay calendarDay);
    }

    protected class MonthViewTouchHelper extends ExploreByTouchHelper {
        private static final String DATE_FORMAT = "dd MMMM yyyy";
        private final Calendar mTempCalendar = Calendar.getInstance(MonthView.this.mController.getTimeZone());
        private final Rect mTempRect = new Rect();

        public MonthViewTouchHelper(View host) {
            super(host);
        }

        public void setFocusedVirtualView(int virtualViewId) {
            getAccessibilityNodeProvider(MonthView.this).performAction(virtualViewId, 64, null);
        }

        public void clearFocusedVirtualView() {
            int focusedVirtualView = getFocusedVirtualView();
            if (focusedVirtualView != Integer.MIN_VALUE) {
                getAccessibilityNodeProvider(MonthView.this).performAction(focusedVirtualView, 128, null);
            }
        }

        protected int getVirtualViewAt(float x, float y) {
            int day = MonthView.this.getDayFromLocation(x, y);
            return day >= 0 ? day : Integer.MIN_VALUE;
        }

        protected void getVisibleVirtualViews(List<Integer> virtualViewIds) {
            for (int day = 1; day <= MonthView.this.mNumCells; day++) {
                virtualViewIds.add(Integer.valueOf(day));
            }
        }

        protected void onPopulateEventForVirtualView(int virtualViewId, AccessibilityEvent event) {
            event.setContentDescription(getItemDescription(virtualViewId));
        }

        protected void onPopulateNodeForVirtualView(int virtualViewId, AccessibilityNodeInfoCompat node) {
            getItemBounds(virtualViewId, this.mTempRect);
            node.setContentDescription(getItemDescription(virtualViewId));
            node.setBoundsInParent(this.mTempRect);
            node.addAction(16);
            if (virtualViewId == MonthView.this.mSelectedDay) {
                node.setSelected(true);
            }
        }

        protected boolean onPerformActionForVirtualView(int virtualViewId, int action, Bundle arguments) {
            switch (action) {
                case 16:
                    MonthView.this.onDayClick(virtualViewId);
                    return true;
                default:
                    return false;
            }
        }

        protected void getItemBounds(int day, Rect rect) {
            int offsetX = MonthView.this.mEdgePadding;
            int offsetY = MonthView.this.getMonthHeaderSize();
            int cellHeight = MonthView.this.mRowHeight;
            int cellWidth = (MonthView.this.mWidth - (MonthView.this.mEdgePadding * 2)) / MonthView.this.mNumDays;
            int index = (day - 1) + MonthView.this.findDayOffset();
            int x = offsetX + ((index % MonthView.this.mNumDays) * cellWidth);
            int y = offsetY + ((index / MonthView.this.mNumDays) * cellHeight);
            rect.set(x, y, x + cellWidth, y + cellHeight);
        }

        protected CharSequence getItemDescription(int day) {
            this.mTempCalendar.set(MonthView.this.mYear, MonthView.this.mMonth, day);
            CharSequence date = DateFormat.format(DATE_FORMAT, this.mTempCalendar.getTimeInMillis());
            if (day != MonthView.this.mSelectedDay) {
                return date;
            }
            return MonthView.this.getContext().getString(R.string.mdtp_item_is_selected, new Object[]{date});
        }
    }

    public abstract void drawMonthDay(Canvas canvas, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9);

    public MonthView(Context context) {
        this(context, null, null);
    }

    public MonthView(Context context, AttributeSet attr, DatePickerController controller) {
        boolean darkTheme = false;
        super(context, attr);
        this.mEdgePadding = 0;
        this.mFirstJulianDay = -1;
        this.mFirstMonth = -1;
        this.mLastMonth = -1;
        this.mRowHeight = DEFAULT_HEIGHT;
        this.mHasToday = false;
        this.mSelectedDay = -1;
        this.mToday = -1;
        this.mWeekStart = 1;
        this.mNumDays = 7;
        this.mNumCells = this.mNumDays;
        this.mSelectedLeft = -1;
        this.mSelectedRight = -1;
        this.mNumRows = 6;
        this.mDayOfWeekStart = 0;
        this.mController = controller;
        Resources res = context.getResources();
        this.mDayLabelCalendar = Calendar.getInstance(this.mController.getTimeZone());
        this.mCalendar = Calendar.getInstance(this.mController.getTimeZone());
        this.mDayOfWeekTypeface = res.getString(R.string.mdtp_day_of_week_label_typeface);
        this.mMonthTitleTypeface = res.getString(R.string.mdtp_sans_serif);
        if (this.mController != null && this.mController.isThemeDark()) {
            darkTheme = true;
        }
        if (darkTheme) {
            this.mDayTextColor = ContextCompat.getColor(context, R.color.mdtp_date_picker_text_normal_dark_theme);
            this.mMonthDayTextColor = ContextCompat.getColor(context, R.color.mdtp_date_picker_month_day_dark_theme);
            this.mDisabledDayTextColor = ContextCompat.getColor(context, R.color.mdtp_date_picker_text_disabled_dark_theme);
            this.mHighlightedDayTextColor = ContextCompat.getColor(context, R.color.mdtp_date_picker_text_highlighted_dark_theme);
        } else {
            this.mDayTextColor = ContextCompat.getColor(context, R.color.mdtp_date_picker_text_normal);
            this.mMonthDayTextColor = ContextCompat.getColor(context, R.color.mdtp_date_picker_month_day);
            this.mDisabledDayTextColor = ContextCompat.getColor(context, R.color.mdtp_date_picker_text_disabled);
            this.mHighlightedDayTextColor = ContextCompat.getColor(context, R.color.mdtp_date_picker_text_highlighted);
        }
        this.mSelectedDayTextColor = ContextCompat.getColor(context, R.color.mdtp_white);
        this.mTodayNumberColor = this.mController.getAccentColor();
        this.mMonthTitleColor = ContextCompat.getColor(context, R.color.mdtp_white);
        this.mStringBuilder = new StringBuilder(50);
        MINI_DAY_NUMBER_TEXT_SIZE = res.getDimensionPixelSize(R.dimen.mdtp_day_number_size);
        MONTH_LABEL_TEXT_SIZE = res.getDimensionPixelSize(R.dimen.mdtp_month_label_size);
        MONTH_DAY_LABEL_TEXT_SIZE = res.getDimensionPixelSize(R.dimen.mdtp_month_day_label_text_size);
        MONTH_HEADER_SIZE = res.getDimensionPixelOffset(R.dimen.mdtp_month_list_item_header_height);
        DAY_SELECTED_CIRCLE_SIZE = res.getDimensionPixelSize(R.dimen.mdtp_day_number_select_circle_radius);
        DAY_HIGHLIGHT_CIRCLE_SIZE = res.getDimensionPixelSize(R.dimen.mdtp_day_highlight_circle_radius);
        DAY_HIGHLIGHT_CIRCLE_MARGIN = res.getDimensionPixelSize(R.dimen.mdtp_day_highlight_circle_margin);
        this.mRowHeight = (res.getDimensionPixelOffset(R.dimen.mdtp_date_picker_view_animator_height) - getMonthHeaderSize()) / 6;
        this.mTouchHelper = getMonthViewTouchHelper();
        ViewCompat.setAccessibilityDelegate(this, this.mTouchHelper);
        ViewCompat.setImportantForAccessibility(this, 1);
        this.mLockAccessibilityDelegate = true;
        initView();
    }

    public void setDatePickerController(DatePickerController controller) {
        this.mController = controller;
    }

    protected MonthViewTouchHelper getMonthViewTouchHelper() {
        return new MonthViewTouchHelper(this);
    }

    public void setAccessibilityDelegate(AccessibilityDelegate delegate) {
        if (!this.mLockAccessibilityDelegate) {
            super.setAccessibilityDelegate(delegate);
        }
    }

    public void setOnDayClickListener(OnDayClickListener listener) {
        this.mOnDayClickListener = listener;
    }

    public boolean dispatchHoverEvent(@NonNull MotionEvent event) {
        if (this.mTouchHelper.dispatchHoverEvent(event)) {
            return true;
        }
        return super.dispatchHoverEvent(event);
    }

    public boolean onTouchEvent(@NonNull MotionEvent event) {
        switch (event.getAction()) {
            case 1:
                int day = getDayFromLocation(event.getX(), event.getY());
                if (day >= 0) {
                    onDayClick(day);
                    break;
                }
                break;
        }
        return true;
    }

    protected void initView() {
        this.mMonthTitlePaint = new Paint();
        this.mMonthTitlePaint.setFakeBoldText(true);
        this.mMonthTitlePaint.setAntiAlias(true);
        this.mMonthTitlePaint.setTextSize((float) MONTH_LABEL_TEXT_SIZE);
        this.mMonthTitlePaint.setTypeface(Typeface.create(this.mMonthTitleTypeface, 1));
        this.mMonthTitlePaint.setColor(this.mDayTextColor);
        this.mMonthTitlePaint.setTextAlign(Align.CENTER);
        this.mMonthTitlePaint.setStyle(Style.FILL);
        this.mSelectedCirclePaint = new Paint();
        this.mSelectedCirclePaint.setFakeBoldText(true);
        this.mSelectedCirclePaint.setAntiAlias(true);
        this.mSelectedCirclePaint.setColor(this.mTodayNumberColor);
        this.mSelectedCirclePaint.setTextAlign(Align.CENTER);
        this.mSelectedCirclePaint.setStyle(Style.FILL);
        this.mSelectedCirclePaint.setAlpha(255);
        this.mMonthDayLabelPaint = new Paint();
        this.mMonthDayLabelPaint.setAntiAlias(true);
        this.mMonthDayLabelPaint.setTextSize((float) MONTH_DAY_LABEL_TEXT_SIZE);
        this.mMonthDayLabelPaint.setColor(this.mMonthDayTextColor);
        this.mMonthTitlePaint.setTypeface(Typeface.create(this.mDayOfWeekTypeface, 1));
        this.mMonthDayLabelPaint.setStyle(Style.FILL);
        this.mMonthDayLabelPaint.setTextAlign(Align.CENTER);
        this.mMonthDayLabelPaint.setFakeBoldText(true);
        this.mMonthNumPaint = new Paint();
        this.mMonthNumPaint.setAntiAlias(true);
        this.mMonthNumPaint.setTextSize((float) MINI_DAY_NUMBER_TEXT_SIZE);
        this.mMonthNumPaint.setStyle(Style.FILL);
        this.mMonthNumPaint.setTextAlign(Align.CENTER);
        this.mMonthNumPaint.setFakeBoldText(false);
    }

    protected void onDraw(Canvas canvas) {
        drawMonthTitle(canvas);
        drawMonthDayLabels(canvas);
        drawMonthNums(canvas);
    }

    public void setMonthParams(int selectedDay, int year, int month, int weekStart) {
        if (month == -1 && year == -1) {
            throw new InvalidParameterException("You must specify month and year for this view");
        }
        this.mSelectedDay = selectedDay;
        this.mMonth = month;
        this.mYear = year;
        Calendar today = Calendar.getInstance(this.mController.getTimeZone());
        this.mHasToday = false;
        this.mToday = -1;
        this.mCalendar.set(2, this.mMonth);
        this.mCalendar.set(1, this.mYear);
        this.mCalendar.set(5, 1);
        this.mDayOfWeekStart = this.mCalendar.get(7);
        if (weekStart != -1) {
            this.mWeekStart = weekStart;
        } else {
            this.mWeekStart = this.mCalendar.getFirstDayOfWeek();
        }
        this.mNumCells = this.mCalendar.getActualMaximum(5);
        for (int i = 0; i < this.mNumCells; i++) {
            int day = i + 1;
            if (sameDay(day, today)) {
                this.mHasToday = true;
                this.mToday = day;
            }
        }
        this.mNumRows = calculateNumRows();
        this.mTouchHelper.invalidateRoot();
    }

    public void setSelectedDay(int day) {
        this.mSelectedDay = day;
    }

    private int calculateNumRows() {
        int offset = findDayOffset();
        return ((this.mNumCells + offset) % this.mNumDays > 0 ? 1 : 0) + ((this.mNumCells + offset) / this.mNumDays);
    }

    private boolean sameDay(int day, Calendar today) {
        if (this.mYear == today.get(1) && this.mMonth == today.get(2) && day == today.get(5)) {
            return true;
        }
        return false;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), ((this.mRowHeight * this.mNumRows) + getMonthHeaderSize()) + 5);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.mWidth = w;
        this.mTouchHelper.invalidateRoot();
    }

    public int getMonth() {
        return this.mMonth;
    }

    public int getYear() {
        return this.mYear;
    }

    protected int getMonthHeaderSize() {
        return MONTH_HEADER_SIZE;
    }

    @NonNull
    private String getMonthAndYearString() {
        Locale locale = Locale.getDefault();
        String pattern = "MMMM yyyy";
        if (VERSION.SDK_INT < 18) {
            pattern = getContext().getResources().getString(R.string.mdtp_date_v1_monthyear);
        } else {
            pattern = DateFormat.getBestDateTimePattern(locale, pattern);
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, locale);
        formatter.setTimeZone(this.mController.getTimeZone());
        formatter.applyLocalizedPattern(pattern);
        this.mStringBuilder.setLength(0);
        return formatter.format(this.mCalendar.getTime());
    }

    protected void drawMonthTitle(Canvas canvas) {
        canvas.drawText(getMonthAndYearString(), (float) ((this.mWidth + (this.mEdgePadding * 2)) / 2), (float) ((getMonthHeaderSize() - MONTH_DAY_LABEL_TEXT_SIZE) / 2), this.mMonthTitlePaint);
    }

    protected void drawMonthDayLabels(Canvas canvas) {
        int y = getMonthHeaderSize() - (MONTH_DAY_LABEL_TEXT_SIZE / 2);
        int dayWidthHalf = (this.mWidth - (this.mEdgePadding * 2)) / (this.mNumDays * 2);
        for (int i = 0; i < this.mNumDays; i++) {
            int x = (((i * 2) + 1) * dayWidthHalf) + this.mEdgePadding;
            this.mDayLabelCalendar.set(7, (this.mWeekStart + i) % this.mNumDays);
            canvas.drawText(getWeekDayLabel(this.mDayLabelCalendar), (float) x, (float) y, this.mMonthDayLabelPaint);
        }
    }

    protected void drawMonthNums(Canvas canvas) {
        int y = (((this.mRowHeight + MINI_DAY_NUMBER_TEXT_SIZE) / 2) - DAY_SEPARATOR_WIDTH) + getMonthHeaderSize();
        float dayWidthHalf = ((float) (this.mWidth - (this.mEdgePadding * 2))) / (((float) this.mNumDays) * 2.0f);
        int j = findDayOffset();
        for (int dayNumber = 1; dayNumber <= this.mNumCells; dayNumber++) {
            int x = (int) ((((float) ((j * 2) + 1)) * dayWidthHalf) + ((float) this.mEdgePadding));
            int startY = y - (((this.mRowHeight + MINI_DAY_NUMBER_TEXT_SIZE) / 2) - DAY_SEPARATOR_WIDTH);
            Canvas canvas2 = canvas;
            drawMonthDay(canvas2, this.mYear, this.mMonth, dayNumber, x, y, (int) (((float) x) - dayWidthHalf), (int) (((float) x) + dayWidthHalf), startY, startY + this.mRowHeight);
            j++;
            if (j == this.mNumDays) {
                j = 0;
                y += this.mRowHeight;
            }
        }
    }

    protected int findDayOffset() {
        return (this.mDayOfWeekStart < this.mWeekStart ? this.mDayOfWeekStart + this.mNumDays : this.mDayOfWeekStart) - this.mWeekStart;
    }

    public int getDayFromLocation(float x, float y) {
        int day = getInternalDayFromLocation(x, y);
        if (day < 1 || day > this.mNumCells) {
            return -1;
        }
        return day;
    }

    protected int getInternalDayFromLocation(float x, float y) {
        int dayStart = this.mEdgePadding;
        if (x < ((float) dayStart) || x > ((float) (this.mWidth - this.mEdgePadding))) {
            return -1;
        }
        return ((((int) (((x - ((float) dayStart)) * ((float) this.mNumDays)) / ((float) ((this.mWidth - dayStart) - this.mEdgePadding)))) - findDayOffset()) + 1) + (this.mNumDays * (((int) (y - ((float) getMonthHeaderSize()))) / this.mRowHeight));
    }

    private void onDayClick(int day) {
        if (!this.mController.isOutOfRange(this.mYear, this.mMonth, day)) {
            if (this.mOnDayClickListener != null) {
                this.mOnDayClickListener.onDayClick(this, new CalendarDay(this.mYear, this.mMonth, day));
            }
            this.mTouchHelper.sendEventForVirtualView(day, 1);
        }
    }

    protected boolean isHighlighted(int year, int month, int day) {
        return this.mController.isHighlighted(year, month, day);
    }

    private String getWeekDayLabel(Calendar day) {
        Locale locale = Locale.getDefault();
        if (VERSION.SDK_INT < 18) {
            int len;
            String dayName = new SimpleDateFormat("E", locale).format(day.getTime());
            String dayLabel = dayName.toUpperCase(locale).substring(0, 1);
            if (locale.equals(Locale.CHINA) || locale.equals(Locale.CHINESE) || locale.equals(Locale.SIMPLIFIED_CHINESE) || locale.equals(Locale.TRADITIONAL_CHINESE)) {
                len = dayName.length();
                dayLabel = dayName.substring(len - 1, len);
            }
            if (locale.getLanguage().equals("he") || locale.getLanguage().equals("iw")) {
                if (this.mDayLabelCalendar.get(7) != 7) {
                    len = dayName.length();
                    dayLabel = dayName.substring(len - 2, len - 1);
                } else {
                    dayLabel = dayName.toUpperCase(locale).substring(0, 1);
                }
            }
            if (locale.getLanguage().equals("ca")) {
                dayLabel = dayName.toLowerCase().substring(0, 2);
            }
            if (locale.getLanguage().equals("es") && day.get(7) == 4) {
                return "X";
            }
            return dayLabel;
        }
        if (this.weekDayLabelFormatter == null) {
            this.weekDayLabelFormatter = new SimpleDateFormat("EEEEE", locale);
        }
        return this.weekDayLabelFormatter.format(day.getTime());
    }

    public CalendarDay getAccessibilityFocus() {
        int day = this.mTouchHelper.getFocusedVirtualView();
        if (day >= 0) {
            return new CalendarDay(this.mYear, this.mMonth, day);
        }
        return null;
    }

    public void clearAccessibilityFocus() {
        this.mTouchHelper.clearFocusedVirtualView();
    }

    public boolean restoreAccessibilityFocus(CalendarDay day) {
        if (day.year != this.mYear || day.month != this.mMonth || day.day > this.mNumCells) {
            return false;
        }
        this.mTouchHelper.setFocusedVirtualView(day.day);
        return true;
    }
}
