package com.tado.android.views;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.tado.C0676R;
import com.tado.android.utils.TimeUtils;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Locale;

public class TimerView extends LinearLayout {
    private static final int DIGITS = 4;
    private static final int HUNDRED_HOURS_IN_SECONDS = 360000;
    @BindView(2131296480)
    ImageView deleteButton;
    private Deque<Integer> digits;
    @BindView(2131296482)
    View keyEightView;
    @BindView(2131296483)
    View keyFiveView;
    @BindView(2131296484)
    View keyFourView;
    @BindView(2131296485)
    View keyNineView;
    @BindView(2131296486)
    View keyOneView;
    @BindView(2131296487)
    View keySevenView;
    @BindView(2131296488)
    View keySixView;
    @BindView(2131296489)
    View keyThreeView;
    @BindView(2131296490)
    View keyTwoView;
    @BindView(2131296491)
    View keyZeroView;
    private OnTimerValueChanged listener;
    private OnClickListener onNumpadClick = new C12891();
    private int seconds = 0;
    @BindView(2131297134)
    TimeTextView timeTextView;
    private Unbinder unbinder;

    class C12891 implements OnClickListener {
        C12891() {
        }

        public void onClick(View v) {
            TimerView.this.onKeyClick(Integer.parseInt((String) v.getTag()));
        }
    }

    class C12902 implements OnClickListener {
        C12902() {
        }

        public void onClick(View v) {
            TimerView.this.onDeleteClick();
        }
    }

    public TimerView(Context context) {
        super(context);
    }

    public TimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TimerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public TimerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(C0676R.layout.view_timer, this, true);
        setOrientation(1);
        setGravity(80);
        if (!isInEditMode()) {
            this.unbinder = ButterKnife.bind((View) this);
            this.deleteButton.getDrawable().mutate().setColorFilter(ContextCompat.getColor(context, C0676R.color.app_bar_title_temperatures_and_times), Mode.SRC_ATOP);
            this.digits = new ArrayDeque(4);
            initDefaultTimerValue();
            updateTimerView();
            initListeners();
        }
    }

    private void initDefaultTimerValue() {
        int i = 0;
        char[] toCharArray = String.format(Locale.US, "%02d%02d", new Object[]{Integer.valueOf(TimeUtils.getHours(this.seconds, false)), Integer.valueOf(TimeUtils.getMinutesRemainder(this.seconds))}).toCharArray();
        int length = toCharArray.length;
        while (i < length) {
            push(Integer.parseInt(Character.toString(toCharArray[i])));
            i++;
        }
        updateTimerView();
    }

    private void push(int digit) {
        if (this.digits.size() == 4) {
            this.digits.pollFirst();
        }
        this.digits.addLast(Integer.valueOf(digit));
    }

    private void onKeyClick(int key) {
        push(key);
        updateTimerView();
    }

    private void updateTimerView() {
        String value = String.format(Locale.US, "%04d", new Object[]{Integer.valueOf(getNumericFormat())});
        this.seconds = convertToSeconds(number);
        this.timeTextView.setText(value);
        if (this.listener != null) {
            this.listener.onTimerValueChanged(this.seconds);
        }
    }

    private int getNumericFormat() {
        Iterator<Integer> iterator = this.digits.iterator();
        int[] list = new int[4];
        int number = 0;
        for (int i = 0; i < list.length; i++) {
            list[i] = ((Integer) iterator.next()).intValue();
            number += (int) (((double) list[i]) * Math.pow(10.0d, (double) ((list.length - 1) - i)));
        }
        return number;
    }

    private int convertToSeconds(int number) {
        return (((number / 100) * 60) * 60) + ((number % 100) * 60);
    }

    private void onDeleteClick() {
        this.digits.pollLast();
        this.digits.addFirst(Integer.valueOf(0));
        updateTimerView();
    }

    public void resetTimer() {
        for (int i = 0; i < 4; i++) {
            push(0);
        }
        updateTimerView();
    }

    public int getCurrentSeconds() {
        return this.seconds;
    }

    public void setStartingSeconds(int seconds) {
        this.seconds = seconds;
        if (seconds >= HUNDRED_HOURS_IN_SECONDS) {
            this.seconds = 359999;
        }
        initDefaultTimerValue();
    }

    public void setListener(OnTimerValueChanged listener) {
        this.listener = listener;
    }

    public void removeListener() {
        this.listener = null;
    }

    private void initListeners() {
        this.keyZeroView.setOnClickListener(this.onNumpadClick);
        this.keyOneView.setOnClickListener(this.onNumpadClick);
        this.keyTwoView.setOnClickListener(this.onNumpadClick);
        this.keyThreeView.setOnClickListener(this.onNumpadClick);
        this.keyFourView.setOnClickListener(this.onNumpadClick);
        this.keyFiveView.setOnClickListener(this.onNumpadClick);
        this.keySixView.setOnClickListener(this.onNumpadClick);
        this.keySevenView.setOnClickListener(this.onNumpadClick);
        this.keyEightView.setOnClickListener(this.onNumpadClick);
        this.keyNineView.setOnClickListener(this.onNumpadClick);
        this.deleteButton.setOnClickListener(new C12902());
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.unbinder.unbind();
    }
}
