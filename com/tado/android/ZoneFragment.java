package com.tado.android;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.tado.C0676R;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.control_panel.ControlPanelController;
import com.tado.android.controllers.ZoneController;
import com.tado.android.mvp.EndManualControlButtonInteraction;
import com.tado.android.mvp.model.TadoModeEnum;
import com.tado.android.mvp.presenters.WeatherPresenter;
import com.tado.android.mvp.presenters.ZonePresenter;
import com.tado.android.mvp.views.ZoneView;
import com.tado.android.onboarding.TutorialUtil;
import com.tado.android.onboarding.data.FeatureDataSource;
import com.tado.android.onboarding.data.FeatureDataSource.LoadVersionCallback;
import com.tado.android.premium.OpenWindowUpsellingActivity;
import com.tado.android.premium.SwitchAwayUpsellingActivity;
import com.tado.android.premium.SwitchHomeUpsellingActivity;
import com.tado.android.premium.UpsellingPresenter;
import com.tado.android.premium.data.PremiumUpsellingRepository;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.callback.presenters.GeneralErrorAlertPresenter;
import com.tado.android.rest.model.MobileDevice;
import com.tado.android.rest.model.Zone;
import com.tado.android.rest.model.ZoneSetting;
import com.tado.android.rest.model.ZoneState;
import com.tado.android.times.SmartScheduleActivity;
import com.tado.android.times.view.model.CoolingFanSpeedEnum;
import com.tado.android.times.view.model.CoolingSwingStateEnum;
import com.tado.android.user_radar.UserRadarLayout;
import com.tado.android.utils.ResourceFactory;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.Snitcher.Builder;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.ViewEnabler;
import com.tado.android.views.MorphingButton;
import com.tado.android.views.TadoStateTemperatureView;
import com.tado.android.views.loadingindicator.LoadingView;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class ZoneFragment extends Fragment implements ZoneView, EndManualControlButtonInteraction {
    private static final String TAG = "ZoneFragment";
    private static final String WEATHER_PRESENTER = "weatherPresenter";
    private static final String ZONE_ID = "position";
    @BindView(2131296581)
    ViewGroup dimView;
    @BindView(2131296587)
    ImageButton drawerButton;
    @BindView(2131296479)
    MorphingButton endManualControlButton;
    private String firstModeAttribute = "";
    @BindView(2131296674)
    Button ignoreOpenWindowDetectionButton;
    @BindView(2131296723)
    LoadingView loadingView;
    @BindView(2131296314)
    View mAdditionalInfoLayout;
    @BindView(2131296315)
    TextView mAdditionalInfoText;
    @BindView(2131296398)
    ImageView mCallForHeatIcon;
    @BindView(2131296509)
    View mFirstModeAttr;
    @BindView(2131296510)
    ImageView mFirstModeAttrIcon;
    @BindView(2131296511)
    ImageView mFirstModeAttrImageValue;
    @BindView(2131296512)
    TextView mFirstModeAttrName;
    @BindView(2131296665)
    ViewGroup mHumidityLayout;
    @BindView(2131296441)
    View mMainLayout;
    @BindView(2131296508)
    View mMainModeLayout;
    @BindView(2131296518)
    ImageView mModeIcon;
    @BindView(2131296519)
    TextView mModeName;
    @BindView(2131296831)
    LinearLayout mOverlayTerminationInfoLayout;
    @BindView(2131296832)
    TextView mOverlayTerminationInfoTextView;
    @BindView(2131296835)
    TextView mOverlayTimerTextView;
    @BindView(2131296920)
    ImageButton mReportButton;
    @BindView(2131296921)
    ImageView mReportButtonAnimation;
    @BindView(2131296937)
    ImageView mScheduleButtonAnimation;
    @BindView(2131296513)
    View mSecondModeAttr;
    @BindView(2131296514)
    ImageView mSecondModeAttrIcon;
    @BindView(2131296515)
    ImageView mSecondModeAttrImageValue;
    @BindView(2131296516)
    TextView mSecondModeAttrName;
    @BindView(2131296521)
    TextView mStateHumidity;
    @BindView(2131296522)
    ImageView mStateIcon;
    @BindView(2131297017)
    TadoStateTemperatureView mTadoStateTemperatureView;
    private float mTemperatureStep = 1.0f;
    @BindView(2131296308)
    ToolTipRelativeLayout mToolTipRelativeLayout;
    private UpsellingPresenter mUpsellingPresenter;
    private Runnable mUpsellingRunnable;
    @BindView(2131296525)
    UserRadarLayout mUserRadarLayout;
    @BindView(2131296526)
    TextView mWeatherInfo;
    private int mZoneID = -1;
    @BindView(2131297214)
    View mZoneModeNoConnectionLayout;
    @BindView(2131297213)
    TextView mZoneModeNoConnectionText;
    @BindView(2131296524)
    TextView mZoneName;
    private ZonePresenter mZonePresenter;
    @BindView(2131297224)
    ImageView mZoneStateHotWaterIcon;
    @BindView(2131297225)
    View mZoneStateHotWaterLayout;
    @BindView(2131297226)
    View mZoneStateNoConnectionLayout;
    @BindView(2131296392)
    ImageButton scheduleButton;
    @BindView(2131297004)
    Button switchHomePresenceButton;
    private Unbinder unbinder;
    @BindView(2131297172)
    Button unmaskOpenWindowButton;
    private WeatherPresenter weatherPresenter;

    class C06971 implements OnClickListener {
        C06971() {
        }

        public void onClick(View v) {
            ZoneFragment.this.onEndManualControlClick();
        }
    }

    class C06982 implements OnClickListener {
        C06982() {
        }

        public void onClick(View v) {
            if (ZoneFragment.this.getActivity() != null) {
                ContextCompat.startActivity(ZoneFragment.this.getActivity(), new Intent(ZoneFragment.this.getActivity(), SmartScheduleActivity.class), ActivityOptionsCompat.makeCustomAnimation(ZoneFragment.this.getActivity(), C0676R.anim.slide_in_left, C0676R.anim.slide_out_left).toBundle());
            }
        }
    }

    class C06993 implements OnClickListener {
        C06993() {
        }

        public void onClick(View v) {
            if (ZoneFragment.this.getActivity() != null) {
                PopupWindow popupWindow = new PopupWindow(-2, -2);
                View layout = LayoutInflater.from(ZoneFragment.this.getActivity()).inflate(C0676R.layout.call_for_heat_popup_layout, null);
                TextView callForHeatView = (TextView) layout.findViewById(C0676R.id.call_for_heat_text_view);
                popupWindow.setContentView(layout);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(0));
                if (ZoneFragment.this.mCallForHeatIcon.getTag() != null) {
                    callForHeatView.setText(((Integer) ZoneFragment.this.mCallForHeatIcon.getTag()).intValue());
                }
                layout.measure(-2, -2);
                int[] position = new int[2];
                ZoneFragment.this.mCallForHeatIcon.getLocationOnScreen(position);
                int centerX = position[0] + (ZoneFragment.this.mCallForHeatIcon.getWidth() / 2);
                if (ZoneFragment.this.isAdded()) {
                    popupWindow.showAtLocation(ZoneFragment.this.mMainLayout, 0, centerX - (layout.getMeasuredWidth() / 2), position[1] - layout.getMeasuredHeight());
                }
            }
        }
    }

    class C07004 implements OnClickListener {
        C07004() {
        }

        public void onClick(View v) {
            ZoneFragment.this.mZonePresenter.ignoreOpenWindowDetection();
        }
    }

    class C07035 implements OnClickListener {
        C07035() {
        }

        public void onClick(View v) {
            final Button button = (Button) v;
            final TadoModeEnum switchToMode = (TadoModeEnum) v.getTag();
            final ViewEnabler viewEnabler = new ViewEnabler(button);
            viewEnabler.disableViews();
            ZoneController.INSTANCE.setHomePresence(switchToMode, new TadoCallback<Void>() {

                class C07011 implements Runnable {
                    C07011() {
                    }

                    public void run() {
                        if (ZoneFragment.this.getActivity() != null) {
                            Intent intent;
                            if (switchToMode == TadoModeEnum.HOME) {
                                intent = new Intent(ZoneFragment.this.getActivity(), SwitchHomeUpsellingActivity.class);
                            } else {
                                intent = new Intent(ZoneFragment.this.getActivity(), SwitchAwayUpsellingActivity.class);
                            }
                            intent.addFlags(536870912);
                            ZoneFragment.this.startActivity(intent);
                        }
                    }
                }

                public void onResponse(Call<Void> call, Response<Void> response) {
                    super.onResponse(call, response);
                    if (response.isSuccessful()) {
                        button.setVisibility(8);
                        if (PremiumUpsellingRepository.INSTANCE.shouldShowGeofencingUpsellingScreen()) {
                            ZoneFragment.this.mUpsellingRunnable = new C07011();
                        }
                        ZoneFragment.this.mUpsellingPresenter.clearStates();
                        ZoneController.INSTANCE.callGetHomeState();
                        ZoneController.INSTANCE.callGetCurrentZoneState();
                    }
                    viewEnabler.enableViews();
                }

                public void onFailure(Call<Void> call, Throwable t) {
                    super.onFailure(call, t);
                    viewEnabler.enableViews();
                }
            });
        }
    }

    class C07066 implements OnClickListener {
        C07066() {
        }

        public void onClick(View v) {
            final Button button = (Button) v;
            final ViewEnabler viewEnabler = new ViewEnabler(button);
            viewEnabler.disableViews();
            ZoneController.INSTANCE.unmaskOpenWindow(new TadoCallback<Void>() {

                class C07041 implements Runnable {
                    C07041() {
                    }

                    public void run() {
                        if (ZoneFragment.this.getActivity() != null) {
                            Intent intent = new Intent(ZoneFragment.this.getActivity(), OpenWindowUpsellingActivity.class);
                            intent.addFlags(536870912);
                            ZoneFragment.this.startActivity(intent);
                        }
                    }
                }

                public void onResponse(Call<Void> call, Response<Void> response) {
                    super.onResponse(call, response);
                    if (response.isSuccessful()) {
                        button.setVisibility(8);
                        if (PremiumUpsellingRepository.INSTANCE.shouldShowOpenWindowUpsellingScreen()) {
                            ZoneFragment.this.mUpsellingRunnable = new C07041();
                        }
                        ZoneFragment.this.mUpsellingPresenter.clearStates();
                        ZoneController.INSTANCE.callGetCurrentZoneState();
                        ZoneController.INSTANCE.callGetHomeState();
                    }
                    viewEnabler.enableViews();
                }

                public void onFailure(Call<Void> call, Throwable t) {
                    super.onFailure(call, t);
                    viewEnabler.enableViews();
                }
            });
        }
    }

    class C07088 implements OnTouchListener {
        C07088() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }
    }

    public static ZoneFragment newInstance(int zoneId, WeatherPresenter presenter) {
        Snitcher.start().log(3, TAG, "zoneId=%d newInstance", Integer.valueOf(zoneId));
        ZoneFragment fragment = new ZoneFragment();
        Bundle args = new Bundle();
        args.putInt(ZONE_ID, zoneId);
        args.putParcelable(WEATHER_PRESENTER, presenter);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Snitcher.start().log(3, TAG, "%d zoneId=%d onCreate", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(this.mZoneID));
        if (getArguments() != null) {
            this.mZoneID = getArguments().getInt(ZONE_ID);
            this.weatherPresenter = (WeatherPresenter) getArguments().getParcelable(WEATHER_PRESENTER);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Snitcher.start().log(3, TAG, "%d zoneId=%d onCreateView", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(this.mZoneID));
        View view = inflater.inflate(C0676R.layout.fragment_zone, container, false);
        this.unbinder = ButterKnife.bind((Object) this, view);
        setDrawerBadgeVisibility(ZoneController.INSTANCE.getBatteryState());
        this.endManualControlButton.setOnClickListener(new C06971());
        this.scheduleButton.setOnClickListener(new C06982());
        this.mCallForHeatIcon.setOnClickListener(new C06993());
        this.ignoreOpenWindowDetectionButton.setOnClickListener(new C07004());
        this.switchHomePresenceButton.setOnClickListener(new C07035());
        this.unmaskOpenWindowButton.setOnClickListener(new C07066());
        return view;
    }

    public void showUpsellingScreen() {
        if (this.mUpsellingRunnable != null) {
            new Handler().postDelayed(this.mUpsellingRunnable, 2000);
            this.mUpsellingRunnable = null;
        }
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ZoneState zoneState = ZoneController.INSTANCE.getZoneState(this.mZoneID);
        if (zoneState != null && this.mZonePresenter != null) {
            this.mZonePresenter.getZoneState(zoneState);
        }
    }

    public void onEndManualControlClick() {
        ViewEnabler viewEnabler = new ViewEnabler(this.endManualControlButton);
        viewEnabler.disableViews();
        ZoneController.INSTANCE.callRemoveOverlay(new GeneralErrorAlertPresenter(getContext()), viewEnabler);
        ControlPanelController.INSTANCE.cleanZone(ZoneController.INSTANCE.getCurrentZoneId());
        AnalyticsHelper.trackEvent(getActivity(), Screen.MANUAL_CONTROL, "Stop", isCollapsed() ? "collapsed" : "expanded");
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Builder start = Snitcher.start();
        String str = TAG;
        String str2 = "%d zoneId=%d onActivityCreated savedInstanceState %s";
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(this.mZoneID);
        objArr[2] = savedInstanceState != null ? savedInstanceState.toString() : "empty";
        start.log(3, str, str2, objArr);
        this.mUserRadarLayout.setTooltipRelativeLayout(this.mToolTipRelativeLayout);
    }

    public void onResume() {
        boolean z = true;
        super.onResume();
        Builder start = Snitcher.start();
        String str = TAG;
        String str2 = "%d zoneId=%d onResume mZonePresenter %b";
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(this.mZoneID);
        if (this.mZonePresenter == null) {
            z = false;
        }
        objArr[2] = Boolean.valueOf(z);
        start.log(3, str, str2, objArr);
        if (this.mZonePresenter == null) {
            this.mZonePresenter = new ZonePresenter(this.weatherPresenter);
        }
        this.mZonePresenter.bindView((ZoneView) this);
        this.mZonePresenter.setZoneId(this.mZoneID);
        this.mZonePresenter.loadState();
        if (this.mUpsellingPresenter == null) {
            this.mUpsellingPresenter = new UpsellingPresenter();
        }
        this.mUpsellingPresenter.bindView((ZoneView) this);
        this.mUpsellingPresenter.setZoneId(this.mZoneID);
        updateReportButtonVisibility();
        updateScheduleButtonVisibility();
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (this.mUserRadarLayout != null) {
            this.mUserRadarLayout.invalidate();
        }
    }

    public void onPause() {
        Builder start = Snitcher.start();
        String str = TAG;
        String str2 = "%d zoneId=%d onPause endManualControlButton is %s";
        Object[] objArr = new Object[4];
        objArr[0] = Integer.valueOf(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(this.mZoneID);
        objArr[2] = Integer.valueOf(16842755);
        objArr[3] = this.endManualControlButton == null ? "NULL" : "not null";
        start.log(3, str, str2, objArr);
        if (this.endManualControlButton != null && this.endManualControlButton.isVisible()) {
            this.endManualControlButton.removeAnimations();
        }
        this.mZonePresenter.unbindView();
        this.mUpsellingPresenter.unbindView();
        super.onPause();
    }

    public void onDestroyView() {
        Builder start = Snitcher.start();
        String str = TAG;
        String str2 = "%d zoneId=%d onDestroy endManualControlButton is %s";
        Object[] objArr = new Object[4];
        objArr[0] = Integer.valueOf(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(this.mZoneID);
        objArr[2] = Integer.valueOf(16842755);
        objArr[3] = this.endManualControlButton == null ? "NULL" : "not null";
        start.log(3, str, str2, objArr);
        this.unbinder.unbind();
        super.onDestroyView();
    }

    public synchronized void setMainModeBackgroundColor(int color, boolean enabled) {
        if (enabled) {
            this.mMainModeLayout.setBackgroundResource(C0676R.drawable.manual_control_button_selectable_background);
            final GradientDrawable shape = (GradientDrawable) ((LayerDrawable) this.mMainModeLayout.getBackground()).findDrawableByLayerId(C0676R.id.manual_settings_background);
            if (this.mMainModeLayout.getTag() != null) {
                int lastColor = ((Integer) this.mMainModeLayout.getTag()).intValue();
                ValueAnimator valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(lastColor), Integer.valueOf(color)});
                valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        shape.setColor(((Integer) animation.getAnimatedValue()).intValue());
                    }
                });
                valueAnimator.start();
            } else {
                shape.setColor(color);
            }
            this.mMainModeLayout.setTag(Integer.valueOf(color));
        } else {
            this.mMainModeLayout.setBackgroundResource(C0676R.color.ignore_open_window_detection_manual_mode_background);
        }
    }

    public void transitionBackgroundColor(int to) {
        if (this.mMainLayout.getTag() != null) {
            int lastColor = ((Integer) this.mMainLayout.getTag()).intValue();
            ObjectAnimator objectAnimator = ObjectAnimator.ofObject(this.mMainLayout, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(lastColor), Integer.valueOf(to)});
            ObjectAnimator objectAnimator2 = ObjectAnimator.ofObject(this.loadingView, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(lastColor), Integer.valueOf(to)});
            AnimatorSet set = new AnimatorSet();
            set.playTogether(new Animator[]{objectAnimator, objectAnimator2});
            set.start();
        } else {
            this.mMainLayout.setBackgroundColor(to);
            this.loadingView.setBackgroundColor(to);
        }
        this.mMainLayout.setTag(Integer.valueOf(to));
    }

    public void updateCoolingModeLayout(ZoneState state) {
        this.mFirstModeAttr.setVisibility(8);
        this.mSecondModeAttr.setVisibility(8);
        this.mHumidityLayout.setVisibility(0);
        ZoneSetting setting = state.getSetting();
        if (setting.getPowerBoolean() && state.getSetting().getType().equalsIgnoreCase("AIR_CONDITIONING")) {
            setupModeAttribute(setting, this.mFirstModeAttr, this.mFirstModeAttrIcon, this.mFirstModeAttrName, this.mFirstModeAttrImageValue);
            setupModeAttribute(setting, this.mSecondModeAttr, this.mSecondModeAttrIcon, this.mSecondModeAttrName, this.mSecondModeAttrImageValue);
            resetFirstModeAttribute();
        }
    }

    public void setAdditionalInfoLayoutVisibility(boolean visible) {
        this.mAdditionalInfoLayout.setVisibility(visible ? 0 : 4);
    }

    public void setAdditionalInfoText(String earlyStartText) {
        this.mAdditionalInfoText.setText(earlyStartText);
    }

    public void startProgress() {
        if (this.loadingView != null) {
            this.loadingView.start();
        }
        Snitcher.start().log(TAG, "startProgress %d", Integer.valueOf(this.mZoneID));
    }

    public void endProgress() {
        if (this.loadingView != null) {
            this.loadingView.finish();
        }
        Snitcher.start().log(TAG, "endProgress %d", Integer.valueOf(this.mZoneID));
    }

    public void errorProgress() {
        if (this.loadingView != null) {
            this.loadingView.error();
        }
        Snitcher.start().log(TAG, "errorProgress %d", Integer.valueOf(this.mZoneID));
    }

    public void setIgnoreOpenWindowDetectionButtonVisibility(boolean visible) {
        this.ignoreOpenWindowDetectionButton.setVisibility(visible ? 0 : 8);
    }

    public void setSwitchHomePresenceButton(boolean visible, TadoModeEnum mode) {
        int drawableId;
        int textId;
        TadoModeEnum switchToMode;
        switch (mode) {
            case HOME:
                drawableId = C0676R.drawable.ic_away;
                textId = C0676R.string.premiumUpgrade_locationBasedControl_switchToAwayButton;
                switchToMode = TadoModeEnum.AWAY;
                break;
            case AWAY:
                drawableId = C0676R.drawable.ic_home;
                textId = C0676R.string.premiumUpgrade_locationBasedControl_switchToHomeButton;
                switchToMode = TadoModeEnum.HOME;
                break;
            default:
                switchToMode = TadoModeEnum.HOME;
                textId = 0;
                drawableId = 0;
                break;
        }
        if (visible) {
            this.switchHomePresenceButton.setCompoundDrawablesWithIntrinsicBounds(ResourceFactory.getTintedDrawable(getContext(), drawableId, C0676R.color.switchHomePresenceColor), null, null, null);
            this.switchHomePresenceButton.setTag(switchToMode);
            this.switchHomePresenceButton.setText(textId);
        }
        this.switchHomePresenceButton.setVisibility(visible ? 0 : 8);
    }

    public void setUnmaskOpenWindowButton(boolean visible) {
        if (visible) {
            Drawable drawable = getResources().getDrawable(C0676R.drawable.ic_owd).mutate();
            drawable.setColorFilter(new PorterDuffColorFilter(getResources().getColor(C0676R.color.switchHomePresenceColor), Mode.SRC_ATOP));
            this.unmaskOpenWindowButton.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        }
        this.unmaskOpenWindowButton.setVisibility(visible ? 0 : 8);
    }

    public void setIgnoreOpenWindowDetectionButtonBackgroundColor(int currentSettingsBackgroundColor) {
        GradientDrawable shape;
        Drawable backgroundDrawabe = this.ignoreOpenWindowDetectionButton.getBackground().mutate();
        if (backgroundDrawabe instanceof StateListDrawable) {
            StateListDrawable stateListDrawable = (StateListDrawable) this.ignoreOpenWindowDetectionButton.getBackground();
            Drawable drawableNormal = null;
            try {
                drawableNormal = (Drawable) StateListDrawable.class.getMethod("getStateDrawable", new Class[]{Integer.TYPE}).invoke(stateListDrawable, new Object[]{Integer.valueOf(1)});
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e2) {
                e2.printStackTrace();
            } catch (NoSuchMethodException e3) {
                e3.printStackTrace();
            }
            shape = (GradientDrawable) drawableNormal;
        } else {
            shape = (GradientDrawable) ((RippleDrawable) backgroundDrawabe).findDrawableByLayerId(C0676R.id.rounded_button_background);
        }
        if (shape != null) {
            shape.setColor(currentSettingsBackgroundColor);
        }
    }

    private void setupModeAttribute(ZoneSetting setting, View layout, ImageView iconView, TextView nameView, ImageView imageValue) {
        int iconId = 0;
        String value = "";
        if (setting.getTemperature() != null && !isAlreadyAddedAttribute(ResourceFactory.MODE_ATTRIBUTE_TEMPERATURE)) {
            iconId = ResourceFactory.getModeAttrDrawableIcon(ResourceFactory.MODE_ATTRIBUTE_TEMPERATURE);
            value = setting.getTemperature().getFormattedTemperatureValue(this.mTemperatureStep);
            setFirstModeAttribute(ResourceFactory.MODE_ATTRIBUTE_TEMPERATURE);
        } else if (setting.getFanSpeed() != null && !setting.getFanSpeed().equals("") && !isAlreadyAddedAttribute("fan")) {
            iconId = ResourceFactory.getModeAttrDrawableIcon("fan");
            value = setting.getFanSpeed();
            int fanImageValueId = getFanValueIcon(setting.getFanSpeed());
            if (fanImageValueId == -1) {
                imageValue.setVisibility(8);
                nameView.setVisibility(0);
            } else {
                imageValue.setImageResource(fanImageValueId);
                imageValue.setVisibility(0);
                nameView.setVisibility(8);
            }
            setFirstModeAttribute("fan");
        } else if (!(setting.getSwing() == null || setting.getSwing().equals("") || isAlreadyAddedAttribute(ResourceFactory.MODE_ATTRIBUTE_SWING))) {
            iconId = ResourceFactory.getModeAttrDrawableIcon(ResourceFactory.MODE_ATTRIBUTE_SWING);
            value = CoolingSwingStateEnum.getCoolingSwingString(CoolingSwingStateEnum.getBooleanValue(setting.getSwing()));
            setFirstModeAttribute(ResourceFactory.MODE_ATTRIBUTE_SWING);
        }
        if (iconId == 0 || value.equals("")) {
            layout.setVisibility(8);
            return;
        }
        iconView.setImageResource(iconId);
        nameView.setText(value);
        layout.setVisibility(0);
    }

    private int getFanValueIcon(String fanSpeed) {
        CoolingFanSpeedEnum fanSpeedEnum = CoolingFanSpeedEnum.getCoolingFanSpeed(fanSpeed);
        if (fanSpeedEnum == null) {
            return -1;
        }
        if (fanSpeedEnum == CoolingFanSpeedEnum.HIGH) {
            return C0676R.drawable.fanspeed_high;
        }
        if (fanSpeedEnum == CoolingFanSpeedEnum.MEDIUM) {
            return C0676R.drawable.fanspeed_mid;
        }
        if (fanSpeedEnum == CoolingFanSpeedEnum.LOW) {
            return C0676R.drawable.fanspeed_low;
        }
        return -1;
    }

    private void setFirstModeAttribute(String attr) {
        if (this.firstModeAttribute.equals("")) {
            this.firstModeAttribute = attr;
        }
    }

    private boolean isAlreadyAddedAttribute(String attr) {
        return this.firstModeAttribute.equals(attr);
    }

    private void resetFirstModeAttribute() {
        this.firstModeAttribute = "";
    }

    public View getDimView() {
        if (this.dimView == null) {
            return null;
        }
        this.dimView.setOnTouchListener(new C07088());
        return this.dimView;
    }

    public void setZoneName(String name) {
        Builder start = Snitcher.start();
        String str = TAG;
        String str2 = "%d zoneId=%d setZoneName name=%s endManualControlButton is %s";
        Object[] objArr = new Object[4];
        objArr[0] = Integer.valueOf(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(this.mZoneID);
        objArr[2] = name;
        objArr[3] = this.endManualControlButton == null ? "NULL" : "not null";
        start.log(3, str, str2, objArr);
        this.mZoneName.setText(name);
    }

    public void setWeatherTemperature(Integer temperature) {
        if (this.mWeatherInfo != null) {
            CharSequence string;
            TextView textView = this.mWeatherInfo;
            if (temperature != null) {
                string = getString(C0676R.string.weather_temperature, temperature);
            } else {
                string = getString(C0676R.string.empty_weather_temperature);
            }
            textView.setText(string);
        }
    }

    public void setWeatherIcon(int iconId) {
        if (this.mWeatherInfo != null) {
            this.mWeatherInfo.setCompoundDrawablesWithIntrinsicBounds(iconId, 0, 0, 0);
        }
    }

    public void setZoneBackgroundColor(int color) {
        transitionBackgroundColor(color);
    }

    public void setZoneSettingsBackgroundColorAndState(int color, boolean enabled) {
        setMainModeBackgroundColor(color, enabled);
        this.mMainModeLayout.setEnabled(enabled);
    }

    public void updateStateIconLayoutVisibility(boolean visibile) {
        this.mStateIcon.setVisibility(visibile ? 0 : 8);
    }

    public void setStateIcon(Drawable icon) {
        this.mStateIcon.setImageDrawable(icon);
    }

    public void updateStateNoConnectionLayoutVisibility(boolean visibile) {
        this.mZoneStateNoConnectionLayout.setVisibility(visibile ? 0 : 8);
    }

    public void updateHumidityLayoutVisibility(boolean visible) {
        int i;
        int i2 = 0;
        View childAt = this.mHumidityLayout.getChildAt(0);
        if (visible) {
            i = 0;
        } else {
            i = 8;
        }
        childAt.setVisibility(i);
        childAt = this.mHumidityLayout.getChildAt(1);
        if (visible) {
            i = 0;
        } else {
            i = 8;
        }
        childAt.setVisibility(i);
        ViewGroup viewGroup = this.mHumidityLayout;
        if (!visible) {
            i2 = 8;
        }
        viewGroup.setVisibility(i2);
    }

    public void setHumidityValue(String humidityValue) {
        this.mStateHumidity.setText(humidityValue);
    }

    public void updateCallForHeatIconVisibility(boolean visibile, Pair<Integer, Integer> callForHeatResourcesPair) {
        this.mCallForHeatIcon.setVisibility(visibile ? 0 : 4);
        this.mCallForHeatIcon.setImageResource(((Integer) callForHeatResourcesPair.first).intValue());
        this.mCallForHeatIcon.setTag(callForHeatResourcesPair.second);
    }

    public void setZoneStateHotWaterIcon(int iconId) {
        this.mZoneStateHotWaterIcon.setImageResource(iconId);
    }

    public void updateZoneStateHotWaterLayoutVisibility(boolean visibile) {
        this.mZoneStateHotWaterLayout.setVisibility(visibile ? 0 : 8);
    }

    public void updateZoneStateTemperatureLayoutVisibility(boolean visibile) {
        this.mTadoStateTemperatureView.setVisibility(visibile ? 0 : 8);
    }

    public void setTadoStateTemperatureValueAndPrecision(float temperatureValue, float precisionValue) {
        this.mTadoStateTemperatureView.setTemperatureAndPrecision(Float.valueOf(temperatureValue), Float.valueOf(precisionValue));
    }

    public void updateReportButtonVisibility() {
        boolean visible;
        int i = 0;
        Zone zone = ZoneController.INSTANCE.getZone(this.mZoneID);
        if (zone == null || !zone.shouldShowReport()) {
            visible = false;
        } else {
            visible = true;
        }
        ImageButton imageButton = this.mReportButton;
        if (!visible) {
            i = 4;
        }
        imageButton.setVisibility(i);
        if (visible) {
            final FeatureDataSource tutorial = TutorialUtil.getReportTutorial(zone.getType(), getActivity());
            if (tutorial != null) {
                tutorial.getVersion(new LoadVersionCallback() {
                    public void onVersionLoaded(int version) {
                        boolean newTutorialAvailable;
                        int i = 0;
                        if (UserConfig.getLastTutorialVersionShown(tutorial.getType(), 0) < version) {
                            newTutorialAvailable = true;
                        } else {
                            newTutorialAvailable = false;
                        }
                        ImageView imageView = ZoneFragment.this.mReportButtonAnimation;
                        if (!newTutorialAvailable) {
                            i = 4;
                        }
                        imageView.setVisibility(i);
                    }

                    public void onVersionNoFeaturesToShow() {
                    }

                    public void onLoadingError(String msg) {
                    }
                });
                return;
            } else {
                this.mReportButtonAnimation.setVisibility(4);
                return;
            }
        }
        this.mReportButtonAnimation.setVisibility(4);
    }

    public void updateScheduleButtonVisibility() {
        final FeatureDataSource tutorial = TutorialUtil.getScheduleTutorial(getActivity());
        tutorial.getVersion(new LoadVersionCallback() {
            public void onVersionLoaded(int version) {
                boolean newTutorialAvailable;
                int i = 0;
                if (UserConfig.getLastTutorialVersionShown(tutorial.getType(), 0) < version) {
                    newTutorialAvailable = true;
                } else {
                    newTutorialAvailable = false;
                }
                ImageView imageView = ZoneFragment.this.mScheduleButtonAnimation;
                if (!newTutorialAvailable) {
                    i = 4;
                }
                imageView.setVisibility(i);
            }

            public void onVersionNoFeaturesToShow() {
            }

            public void onLoadingError(String msg) {
            }
        });
    }

    public void updateCurrentSettingsMainModeVisibility(boolean visible) {
        int i;
        int i2 = 0;
        ImageView imageView = this.mModeIcon;
        if (visible) {
            i = 0;
        } else {
            i = 8;
        }
        imageView.setVisibility(i);
        TextView textView = this.mModeName;
        if (!visible) {
            i2 = 8;
        }
        textView.setVisibility(i2);
    }

    public void updateCurrentSettingsNoConnectionLayoutVisibility(boolean visible) {
        this.mZoneModeNoConnectionLayout.setVisibility(visible ? 0 : 8);
    }

    public void setNoConnectionText(CharSequence noConnectionText) {
        this.mZoneModeNoConnectionText.setText(noConnectionText);
        this.mZoneModeNoConnectionText.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void resetOverlayLayout() {
        this.mOverlayTimerTextView.setVisibility(8);
        this.mOverlayTerminationInfoLayout.setVisibility(4);
    }

    public void updateOverlayTimerLayoutVisibility(boolean visible) {
        this.mOverlayTimerTextView.setVisibility(visible ? 0 : 8);
    }

    public void updateOverlayTerminationInfoLayoutVisibility(boolean visible) {
        this.mOverlayTerminationInfoLayout.setVisibility(visible ? 0 : 8);
    }

    public void updateUserLayoutVisibility(boolean visible) {
        int i;
        int i2 = 0;
        UserRadarLayout userRadarLayout = this.mUserRadarLayout;
        if (visible) {
            i = 0;
        } else {
            i = 4;
        }
        userRadarLayout.setVisibility(i);
        ToolTipRelativeLayout toolTipRelativeLayout = this.mToolTipRelativeLayout;
        if (!visible) {
            i2 = 4;
        }
        toolTipRelativeLayout.setVisibility(i2);
    }

    public void setOverlayTerminationInfoTextValue(String value, boolean center) {
        if (value != null) {
            if (center) {
                this.mOverlayTerminationInfoTextView.setText(value);
                this.mOverlayTerminationInfoTextView.setVisibility(0);
                this.mAdditionalInfoText.setVisibility(8);
                return;
            }
            this.mAdditionalInfoText.setText(value);
            this.mAdditionalInfoText.setVisibility(0);
            this.mOverlayTerminationInfoTextView.setVisibility(8);
        }
    }

    public void setOverlayTerminationInfoIcon(Integer iconId) {
        this.mOverlayTerminationInfoTextView.setCompoundDrawablesWithIntrinsicBounds(null, iconId == null ? null : getResources().getDrawable(iconId.intValue()), null, null);
    }

    public void setOverlayTimerTextValue(String value) {
        this.mOverlayTimerTextView.setText(value);
    }

    public void dispatchTouchEventToUserRadar(MotionEvent event) {
        this.mUserRadarLayout.dispatchTouchEventToUserRadar(event);
    }

    public void updateMobileDevices(List<MobileDevice> mobileDevices) {
        this.mUserRadarLayout.updateMobileDevices(mobileDevices);
    }

    public void setCurrentSettingsMainModeIconId(int iconId) {
        this.mModeIcon.setImageResource(iconId);
    }

    public void setCurrentSettingsMainModeText(String value) {
        this.mModeName.setText(value);
    }

    public void onEndManualControlButtonSlideAnimation(boolean show) {
        Builder start = Snitcher.start();
        String str = TAG;
        String str2 = "%d zoneId=%d onEndManualControlButtonSlideAnimation show=%b endManualControlButton is %s";
        Object[] objArr = new Object[4];
        objArr[0] = Integer.valueOf(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(this.mZoneID);
        objArr[2] = Boolean.valueOf(show);
        objArr[3] = this.endManualControlButton == null ? "NULL" : "not null";
        start.log(3, str, str2, objArr);
        this.endManualControlButton.removeAnimations();
    }

    public void onAnimationStart(boolean show, boolean hasActiveOverlay, View referenceLayout, Interpolator slideInterpolator) {
        Builder start = Snitcher.start();
        String str = TAG;
        String str2 = "%d zoneId=%d onAnimationStart endManualControlButton is %s";
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(this.mZoneID);
        objArr[2] = this.endManualControlButton == null ? "NULL" : "not null";
        start.log(3, str, str2, objArr);
        if (show) {
            this.endManualControlButton.resetState(hasActiveOverlay);
        } else if (hasActiveOverlay) {
            this.endManualControlButton.detachView();
            this.endManualControlButton.slideDown(slideInterpolator, (float) referenceLayout.getHeight());
        } else {
            this.endManualControlButton.resetState(hasActiveOverlay);
        }
    }

    public void onAnimationEnd(boolean show, boolean hasActiveOverlay, View referenceLayout, Interpolator slideInterpolator) {
        Builder start = Snitcher.start();
        String str = TAG;
        String str2 = "%d zoneId=%d onAnimationEnd endManualControlButton is %s";
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(this.mZoneID);
        objArr[2] = this.endManualControlButton == null ? "NULL" : "not null";
        start.log(3, str, str2, objArr);
        if (show) {
            this.endManualControlButton.slideUp(slideInterpolator, hasActiveOverlay, (float) (-referenceLayout.getHeight()));
            if (hasActiveOverlay) {
                this.endManualControlButton.attachToView(referenceLayout);
                return;
            }
            return;
        }
        this.endManualControlButton.detachView();
        if (hasActiveOverlay) {
            this.endManualControlButton.collapseWithDelay();
        }
    }

    public void onAnimationCancel(boolean hasActiveOverlay) {
        Builder start = Snitcher.start();
        String str = TAG;
        String str2 = "%d zoneId=%d onAnimationCancel endManualControlButton is %s";
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(this.mZoneID);
        objArr[2] = this.endManualControlButton == null ? "NULL" : "not null";
        start.log(3, str, str2, objArr);
        this.endManualControlButton.resetState(hasActiveOverlay);
    }

    public void updateEndOverlayButtonStateForOfflineZone() {
        hideEndManualControlButton();
    }

    public void updateEndOverlayButtonStateWithActiveOverlay(boolean wasShownUncollapsed) {
        Snitcher.start().log(3, TAG, "%d zoneId=%d updateEndOverlayButtonStateWithActiveOverlay %b", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(this.mZoneID), Boolean.valueOf(wasShownUncollapsed));
        Builder start = Snitcher.start();
        String str = TAG;
        String str2 = "%d zoneId=%d endManualControlButton is %s";
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(this.mZoneID);
        objArr[2] = this.endManualControlButton == null ? "NULL" : "not null";
        start.log(3, str, str2, objArr);
        if (!this.endManualControlButton.isVisible()) {
            this.endManualControlButton.removeAnimations();
            if (wasShownUncollapsed) {
                this.endManualControlButton.collapse();
            } else {
                this.endManualControlButton.collapseWithDelay();
            }
        }
    }

    public void updateEndOverlayButtonStateWithoutActiveOverlay() {
        hideEndManualControlButton();
    }

    private void hideEndManualControlButton() {
        Builder start = Snitcher.start();
        String str = TAG;
        String str2 = "%d zoneId=%d hideEndManualControl endManualControlButton is %s";
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(this.mZoneID);
        objArr[2] = this.endManualControlButton == null ? "NULL" : "not null";
        start.log(3, str, str2, objArr);
        if (this.endManualControlButton.isVisible()) {
            this.endManualControlButton.fadeOut();
        } else {
            this.endManualControlButton.setVisibility(4);
        }
    }

    public void collapseEndManualControlButton() {
        Builder start = Snitcher.start();
        String str = TAG;
        String str2 = "%d zoneId=%d collapseEndManualControlButton endManualControlButton is %s";
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(this.mZoneID);
        objArr[2] = this.endManualControlButton == null ? "NULL" : "not null";
        start.log(3, str, str2, objArr);
        this.endManualControlButton.collapse();
    }

    public boolean isCollapsed() {
        Builder start = Snitcher.start();
        String str = TAG;
        String str2 = "%d zoneId=%d isCollapsed endManualControlButton is %s";
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(this.mZoneID);
        objArr[2] = this.endManualControlButton == null ? "NULL" : "not null";
        start.log(3, str, str2, objArr);
        return this.endManualControlButton.isCollapsed();
    }

    public int getZoneId() {
        return this.mZoneID;
    }

    public void setDrawerBadgeVisibility(boolean visible) {
        Context context = getContext();
        if (context != null && context.getResources() != null) {
            int i;
            LayerDrawable layerDrawable = (LayerDrawable) ContextCompat.getDrawable(context, C0676R.drawable.drawer_icon);
            Drawable drawable = layerDrawable.getDrawable(1);
            if (visible) {
                i = 255;
            } else {
                i = 0;
            }
            drawable.setAlpha(i);
            layerDrawable.getDrawable(1).setVisible(visible, false);
            if (this.drawerButton != null) {
                this.drawerButton.setImageDrawable(layerDrawable);
            }
        }
    }
}
