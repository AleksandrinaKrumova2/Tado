package com.tado.android.times.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.controllers.CapabilitiesController;
import com.tado.android.rest.model.Temperature;
import com.tado.android.rest.model.installation.CoolingZoneSetting;
import com.tado.android.rest.model.installation.GenericZoneSetting;
import com.tado.android.rest.model.installation.GenericZoneSetting.TypeEnum;
import com.tado.android.rest.model.installation.ModeEnum;
import com.tado.android.utils.ColorFactory;
import com.tado.android.utils.ColorFactory.ColorPair;
import com.tado.android.utils.ResourceFactory;
import com.tado.android.utils.Snitcher;
import com.tado.android.views.LocalizedTimeTextView;
import java.lang.reflect.Method;

public class BlockView extends LinearLayout {
    public static final int GROW_FACTOR = 75;
    public static final int MIN_TAPPABLE_AREA = 48;
    public static float PIXELS_PER_MINUTE;
    private ImageView addImageView;
    private boolean addingMode = false;
    private RelativeLayout blockView;
    public ColorPair color;
    private boolean editingMode;
    private int endTime = -1;
    private OnGlobalLayoutListener globalLayoutListener = new C12181();
    private boolean hasPreheatingEnabled;
    private boolean hasPresenceEnabled;
    public boolean isExpanded = false;
    private int lbsIconWidth = 0;
    private float modeIconWidth = 0.0f;
    private ImageView modeImageView;
    private int plusIconWidth = 0;
    private ImageView presenceImageView;
    private int startTime = -1;
    private Temperature temperature;
    private TextView temperatureTextView;
    private float temperatureTextWidth = 0.0f;
    private LocalizedTimeTextView timeTextView;
    private float timeTextWidth = 0.0f;
    private GenericZoneSetting zoneSetting;

    class C12181 implements OnGlobalLayoutListener {
        C12181() {
        }

        public void onGlobalLayout() {
            BlockView.this.adjustVisibilities(BlockView.this.blockView.getWidth());
        }
    }

    class C12192 implements AnimatorUpdateListener {
        C12192() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            LayoutParams params = (LayoutParams) BlockView.this.getLayoutParams();
            params.width = ((Integer) animation.getAnimatedValue()).intValue();
            BlockView.this.setLayoutParams(params);
        }
    }

    class C12203 implements AnimatorListener {
        C12203() {
        }

        public void onAnimationStart(Animator animation) {
            BlockView.this.isExpanded = true;
        }

        public void onAnimationEnd(Animator animation) {
        }

        public void onAnimationCancel(Animator animation) {
            BlockView.this.isExpanded = false;
        }

        public void onAnimationRepeat(Animator animation) {
        }
    }

    class C12214 implements AnimatorUpdateListener {
        C12214() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            LayoutParams params = (LayoutParams) BlockView.this.getLayoutParams();
            params.width = ((Integer) animation.getAnimatedValue()).intValue();
            BlockView.this.setLayoutParams(params);
        }
    }

    class C12225 implements AnimatorListener {
        C12225() {
        }

        public void onAnimationStart(Animator animation) {
            BlockView.this.isExpanded = false;
        }

        public void onAnimationEnd(Animator animation) {
        }

        public void onAnimationCancel(Animator animation) {
            BlockView.this.isExpanded = true;
        }

        public void onAnimationRepeat(Animator animation) {
        }
    }

    public BlockView(Context context) {
        super(context);
        initViews(context);
        setDefaultData();
    }

    public BlockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public BlockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    public BlockView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews(context);
    }

    public BlockView(Context context, int startTime, int endTime, boolean hasPreheatingEnabled, boolean hasPresenceEnabled, GenericZoneSetting setting) {
        super(context);
        initViews(context);
        initData(startTime, endTime, hasPreheatingEnabled, hasPresenceEnabled, setting);
    }

    public static BlockView copy(BlockView view) {
        return new BlockView(view.getContext(), view.getStartTime(), view.getEndTime(), view.hasPreheatingEnabled, view.hasPresenceEnabled, view.getZoneSetting());
    }

    private void initData(int startTime, int endTime, boolean hasPreheatingEnabled, boolean hasPresenceEnabled, GenericZoneSetting setting) {
        setZoneSetting(setting);
        setStartTime(startTime);
        setEndTime(endTime);
        setHasPreheatingEnabled(hasPreheatingEnabled);
        setHasPresenceEnabled(hasPresenceEnabled);
        setTemperature(setting.getTemperature());
        setId(generateId());
    }

    protected void initViews(Context context) {
        BitmapDrawable plusIconDrawable;
        BitmapDrawable lbsIconDrawable;
        View view = LayoutInflater.from(context).inflate(C0676R.layout.block_view, this);
        this.temperatureTextView = (TextView) view.findViewById(C0676R.id.temperature_textview);
        this.modeImageView = (ImageView) view.findViewById(C0676R.id.mode_icon);
        this.presenceImageView = (ImageView) view.findViewById(C0676R.id.presence_icon);
        this.timeTextView = (LocalizedTimeTextView) findViewById(C0676R.id.start_time_textview);
        this.addImageView = (ImageView) findViewById(C0676R.id.add_block);
        this.blockView = (RelativeLayout) findViewById(C0676R.id.block_bg);
        getViewTreeObserver().addOnGlobalLayoutListener(this.globalLayoutListener);
        if (VERSION.SDK_INT >= 22) {
            plusIconDrawable = (BitmapDrawable) getResources().getDrawable(C0676R.drawable.ic_add_white_24dp, getContext().getTheme());
            lbsIconDrawable = (BitmapDrawable) getResources().getDrawable(C0676R.drawable.location_based_controll_off, getContext().getTheme());
        } else {
            plusIconDrawable = (BitmapDrawable) getResources().getDrawable(C0676R.drawable.ic_add_white_24dp);
            lbsIconDrawable = (BitmapDrawable) getResources().getDrawable(C0676R.drawable.location_based_controll_off);
        }
        if (plusIconDrawable != null && lbsIconDrawable != null) {
            this.plusIconWidth = plusIconDrawable.getBitmap().getWidth();
            this.lbsIconWidth = lbsIconDrawable.getBitmap().getWidth();
        }
    }

    private void setDefaultData() {
        setZoneSetting(new GenericZoneSetting(TypeEnum.HEATING, true, Temperature.fromCelsius(21.0f)));
        setStartTime(0);
        setEndTime(1440);
        setHasPreheatingEnabled(true);
        setHasPresenceEnabled(false);
        setId(generateId());
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
        if (!isOff()) {
            if (temperature != null) {
                this.temperatureTextView.setText(getFormattedTemperature());
            }
            if (isAC()) {
                this.modeImageView.setImageDrawable(DrawableCompat.wrap(ContextCompat.getDrawable(getContext(), ResourceFactory.getModeDrawableIcon(((CoolingZoneSetting) this.zoneSetting).getMode(), !isOff()))));
            } else if (isHotWaterRelay()) {
                this.temperatureTextView.setText(C0676R.string.components_hotWaterSettingDisplay_onLabel);
            }
        } else if (isHotWaterRelay()) {
            this.temperatureTextView.setText(C0676R.string.components_hotWaterSettingDisplay_offLabel);
        } else if (isAC()) {
            this.temperatureTextView.setText(C0676R.string.components_acSettingDisplay_offLabel);
        } else {
            this.temperatureTextView.setText(C0676R.string.components_heatingSettingDisplay_offLabel);
        }
        this.temperatureTextWidth = this.temperatureTextView.getPaint().measureText(this.temperatureTextView.getText().toString());
        updateColors();
    }

    public int getStartTime() {
        return this.startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
        if (this.endTime > -1) {
            setWidth(calculateWidth());
        }
        this.timeTextView.setLocalisedTime(startTime);
        this.timeTextWidth = this.timeTextView.getPaint().measureText(this.timeTextView.getText().toString());
    }

    public int getEndTime() {
        return this.endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
        if (this.startTime > -1) {
            setWidth(calculateWidth());
        }
    }

    public void setHasPresenceEnabled(boolean hasPresenceEnabled) {
        this.hasPresenceEnabled = hasPresenceEnabled;
    }

    public void setHasPreheatingEnabled(boolean hasPreheatingEnabled) {
        this.hasPreheatingEnabled = hasPreheatingEnabled;
    }

    public GenericZoneSetting getZoneSetting() {
        return this.zoneSetting;
    }

    public void setZoneSetting(GenericZoneSetting zoneSetting) {
        this.zoneSetting = zoneSetting;
        if (isAC()) {
            this.modeImageView.setImageResource(ResourceFactory.getModeDrawableIcon(((CoolingZoneSetting) zoneSetting).getMode(), !isOff()));
            this.modeIconWidth = (float) ((BitmapDrawable) ContextCompat.getDrawable(getContext(), C0676R.drawable.location_based_controll_off)).getBitmap().getWidth();
        }
    }

    public void setWidth(int width) {
        setLayoutParams(new LayoutParams(width, -1));
    }

    private int calculateWidth() {
        return Math.round((this.isExpanded ? 75.0f * getResources().getDisplayMetrics().density : 0.0f) + (((float) getDuration()) * PIXELS_PER_MINUTE));
    }

    private String getFormattedTemperature() {
        return this.temperature.getFormattedTemperatureValue(CapabilitiesController.INSTANCE.getZoneTypeTemperatureStep(this.zoneSetting.getType()));
    }

    private void updateColors() {
        float f = 0.0f;
        Context context;
        switch (this.zoneSetting.getType()) {
            case HEATING:
                context = getContext();
                if (!isOff()) {
                    f = this.temperature.getCelsius();
                }
                this.color = ColorFactory.getColorIndexForTemperature(context, f);
                break;
            case HOT_WATER:
                if (!isHotWaterRelay()) {
                    context = getContext();
                    if (!isOff()) {
                        f = this.temperature.getCelsius();
                    }
                    this.color = ColorFactory.getColorIndexForTemperatureHotWater(context, f);
                    break;
                }
                this.color = ColorFactory.getColorForHotWaterRelay(getContext(), isOff());
                break;
            case AIR_CONDITIONING:
                this.color = ColorFactory.getColorForAc(getContext(), ((CoolingZoneSetting) this.zoneSetting).getMode(), isOff());
                break;
        }
        if (this.color != null) {
            this.temperatureTextView.setTextColor(getColorStateList());
            this.addImageView.setColorFilter(this.color.darkColor);
            this.presenceImageView.setImageDrawable(processIcon(C0676R.drawable.location_based_controll_off));
            try {
                Drawable bg = this.blockView.getBackground();
                if (bg instanceof GradientDrawable) {
                    processStateNormal((GradientDrawable) bg);
                } else if (bg instanceof StateListDrawable) {
                    StateListDrawable stateListDrawable = (StateListDrawable) bg;
                    Method getStateDrawable = StateListDrawable.class.getMethod("getStateDrawable", new Class[]{Integer.TYPE});
                    processStateNormal((GradientDrawable) ((Drawable) getStateDrawable.invoke(stateListDrawable, new Object[]{Integer.valueOf(2)})));
                    processStatePressed((GradientDrawable) ((Drawable) getStateDrawable.invoke(stateListDrawable, new Object[]{Integer.valueOf(1)})));
                    processStatePressed((GradientDrawable) ((Drawable) getStateDrawable.invoke(stateListDrawable, new Object[]{Integer.valueOf(0)})));
                }
            } catch (Exception e) {
                Snitcher.start().toCrashlytics().log("BlockView", "Crash because using reflection to get state drawable", new Object[0]);
            }
        }
        if (isAC()) {
            boolean z;
            ImageView imageView = this.modeImageView;
            ModeEnum mode = ((CoolingZoneSetting) this.zoneSetting).getMode();
            if (isOff()) {
                z = false;
            } else {
                z = true;
            }
            imageView.setImageDrawable(processIcon(ResourceFactory.getModeDrawableIcon(mode, z)));
        }
    }

    private void processStateNormal(GradientDrawable bg) {
        bg.setColor(this.color.lightColor);
    }

    private void processStatePressed(GradientDrawable bg) {
        ((GradientDrawable) bg.mutate()).setColor(this.color.darkColor);
    }

    private ColorStateList getColorStateList() {
        states = new int[3][];
        states[0] = new int[]{16842919};
        states[1] = new int[]{16842913};
        states[2] = new int[0];
        return new ColorStateList(states, new int[]{-1, -1, this.color.darkColor});
    }

    private Drawable processIcon(@DrawableRes int drawableRes) {
        Drawable drawable = DrawableCompat.wrap(ContextCompat.getDrawable(getContext(), drawableRes)).mutate();
        DrawableCompat.setTintList(drawable, getColorStateList());
        return drawable;
    }

    private void adjustVisibilities(int w) {
        int i;
        int i2 = 0;
        this.temperatureTextView.setVisibility(shouldTemperatureBeVisible(w) ? 0 : 8);
        ImageView imageView = this.modeImageView;
        if (shouldModeIconBeVisible(w)) {
            i = 0;
        } else {
            i = 8;
        }
        imageView.setVisibility(i);
        imageView = this.presenceImageView;
        if (shouldPresenceDisabledIconBeVisible(w)) {
            i = 0;
        } else {
            i = 8;
        }
        imageView.setVisibility(i);
        this.timeTextView.setVisibility(shouldTimeBeVisible() ? 0 : 4);
        ImageView imageView2 = this.addImageView;
        if (!shouldPlusIconBeVisible()) {
            i2 = 8;
        }
        imageView2.setVisibility(i2);
        updateColors();
    }

    private boolean shouldPlusIconBeVisible() {
        return this.addingMode && this.plusIconWidth < getWidth() && canSplit();
    }

    private boolean shouldTimeBeVisible() {
        return this.timeTextWidth < ((float) getWidth()) && !this.editingMode;
    }

    private boolean shouldTemperatureBeVisible(int w) {
        return (this.addingMode || hideTemperature() || ((float) (this.blockView.getPaddingLeft() + this.blockView.getPaddingRight())) + this.temperatureTextWidth >= ((float) w)) ? false : true;
    }

    private boolean shouldModeIconBeVisible(int w) {
        return !this.addingMode && hideTemperature() && ((float) (this.blockView.getPaddingLeft() + this.blockView.getPaddingRight())) + this.modeIconWidth < ((float) w);
    }

    private boolean shouldPresenceDisabledIconBeVisible(int w) {
        return (this.addingMode || this.hasPresenceEnabled || ((float) ((this.temperatureTextView.getRight() + this.lbsIconWidth) + this.blockView.getPaddingRight())) >= ((float) w)) ? false : true;
    }

    private boolean shouldPreheatingBeVisible() {
        return this.hasPreheatingEnabled;
    }

    public boolean canSplit() {
        return this.endTime - this.startTime >= 30;
    }

    public int getDuration() {
        return this.endTime - this.startTime;
    }

    public boolean shouldExpand() {
        return ((float) getWidth()) < getResources().getDisplayMetrics().density * 48.0f || !shouldTimeBeVisible();
    }

    public Animator getExpandAnimator() {
        int width = ((LayoutParams) getLayoutParams()).width;
        ValueAnimator animator = ValueAnimator.ofInt(new int[]{width, Math.round(75.0f * getResources().getDisplayMetrics().density) + width});
        animator.addUpdateListener(new C12192());
        animator.addListener(new C12203());
        return animator;
    }

    public Animator getCollapseAnimator() {
        int width = ((LayoutParams) getLayoutParams()).width;
        ValueAnimator animator = ValueAnimator.ofInt(new int[]{width, width - Math.round(75.0f * getResources().getDisplayMetrics().density)});
        animator.addUpdateListener(new C12214());
        animator.addListener(new C12225());
        return animator;
    }

    public void setAddMode(boolean enabled) {
        this.addingMode = enabled;
        if (this.addingMode) {
            if (shouldPlusIconBeVisible()) {
                this.addImageView.setVisibility(0);
            }
            this.temperatureTextView.setVisibility(8);
            this.presenceImageView.setVisibility(8);
            this.modeImageView.setVisibility(8);
            updateColors();
            return;
        }
        this.addImageView.setVisibility(8);
        adjustVisibilities(this.blockView.getWidth());
    }

    public boolean isAC() {
        return this.zoneSetting.getType() == TypeEnum.AIR_CONDITIONING;
    }

    public boolean isHotWaterRelay() {
        return this.zoneSetting.getType() == TypeEnum.HOT_WATER && this.zoneSetting.getTemperature() == null;
    }

    public boolean hideTemperature() {
        if (!isAC()) {
            return false;
        }
        ModeEnum mode = ((CoolingZoneSetting) this.zoneSetting).getMode();
        if (mode == ModeEnum.AUTO || mode == ModeEnum.DRY || mode == ModeEnum.FAN) {
            return true;
        }
        return false;
    }

    private boolean isOff() {
        return !this.zoneSetting.getPowerBoolean();
    }

    private int generateId() {
        if (VERSION.SDK_INT >= 17) {
            return generateViewId();
        }
        return this.startTime;
    }

    public boolean onTouchEvent(MotionEvent event) {
        Snitcher.start().log("BlockView", "onTouchEvent ID %s \nTOUCH %s \nediting %b", this, event, Boolean.valueOf(this.editingMode));
        int eventAction = event.getActionMasked();
        boolean handled = super.onTouchEvent(event);
        if ((eventAction == 1 || eventAction == 3) && shouldExpand()) {
            return false;
        }
        return handled;
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        Snitcher.start().log("BlockView", "onInterceptTouchEvent ID %s \n intercept touch %s", this, event);
        return super.onInterceptTouchEvent(event);
    }

    public void setEditingMode(boolean editingMode) {
        Snitcher.start().log("BlockView", "setEditingMode %b", Boolean.valueOf(editingMode));
        this.editingMode = editingMode;
        setSelected(editingMode);
        invalidate();
    }

    public String toString() {
        return "BlockView " + getId() + "\ntemp: " + this.temperature + "\nstart time: " + this.startTime + "\nend time: " + this.endTime + "\nsetting: " + this.zoneSetting.toString();
    }
}
