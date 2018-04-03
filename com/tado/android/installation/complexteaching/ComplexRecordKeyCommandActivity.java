package com.tado.android.installation.complexteaching;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ScrollView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.nhaarman.supertooltips.ToolTipView;
import com.tado.C0676R;
import com.tado.android.alert_dialogs.CustomDialog;
import com.tado.android.entities.InProgessRecordingState;
import com.tado.android.entities.Key;
import com.tado.android.entities.TeachingRun;
import com.tado.android.entities.TeachingStep;
import com.tado.android.entities.TemperatureRange;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.installation.teaching.SetACToSettingActivity;
import com.tado.android.requests.GetCommandSetRecordingModeRequest;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.installation.ModeEnum;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.Util;
import com.tado.android.views.TemperatureGridView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import retrofit2.Call;
import retrofit2.Response;

public class ComplexRecordKeyCommandActivity extends ACInstallationBaseActivity {
    private int[] animationDelays = new int[]{0, 0, 100, 350};
    private int[] animationDurations = new int[]{300, 300, 550, 300};
    HashMap<String, Integer> commandsTextResourcesMap = new C08982();
    boolean errorDialogShown = false;
    Handler handler = null;
    boolean initTickLocation = false;
    private boolean initializedData = false;
    @BindView(2131296421)
    ImageView mCommandView;
    Map<String, Integer> mCommandsImageResourceMap = new C08971();
    @BindView(2131296658)
    TemperatureGridView mGridView;
    @BindView(2131296881)
    View mProgressLayout;
    @BindView(2131296917)
    View mRemoteView;
    @BindView(2131296918)
    View mRepeatButton;
    TeachingRun mTeachingRun;
    @BindView(2131297100)
    View mTickView;
    int[] mTickViewLocation = new int[2];
    private AtomicBoolean pollRequestOpen = new AtomicBoolean(false);

    class C08971 extends HashMap<String, Integer> {
        C08971() {
            put("POWER_OFF", Integer.valueOf(C0676R.drawable.button_power));
            put("POWER_ON", Integer.valueOf(C0676R.drawable.button_power));
            put(Key.TEMP_UP, Integer.valueOf(C0676R.drawable.arrow_up));
            put(Key.TEMP_DOWN, Integer.valueOf(C0676R.drawable.arrow_down));
        }
    }

    class C08982 extends HashMap<String, Integer> {
        C08982() {
            put("POWER_OFF", Integer.valueOf(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_recording_pressPowerLabel));
            put("POWER_ON", Integer.valueOf(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_recording_pressPowerAgainLabel));
            put(Key.TEMP_UP, Integer.valueOf(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_recording_pressTempUpLabel));
            put(Key.TEMP_DOWN, Integer.valueOf(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_recording_pressTempDownLabel));
        }
    }

    class C08993 extends TadoCallback<Void> {
        C08993() {
        }

        public void onResponse(Call<Void> call, Response<Void> response) {
            super.onResponse(call, response);
            if (response.isSuccessful()) {
                ComplexRecordKeyCommandActivity.this.callGetCommandSetRecordingStep(100);
            } else if (this.serverError != null) {
                InstallationProcessController.handleError(ComplexRecordKeyCommandActivity.this, this.serverError, (Call) call, response.code());
            } else {
                InstallationProcessController.showConnectionErrorRetrofit(ComplexRecordKeyCommandActivity.this, call, this);
            }
        }

        public void onFailure(Call<Void> call, Throwable t) {
            super.onFailure(call, t);
            ComplexRecordKeyCommandActivity.this.handleCallFailure();
        }
    }

    class C09004 extends TadoCallback<Void> {
        C09004() {
        }

        public void onResponse(Call<Void> call, Response<Void> response) {
            super.onResponse(call, response);
            if (response.isSuccessful()) {
                ComplexRecordKeyCommandActivity.this.callGetCommandSetRecordingStep(100);
            } else if (this.serverError != null) {
                InstallationProcessController.handleError(ComplexRecordKeyCommandActivity.this, this.serverError, (Call) call, response.code());
            } else {
                InstallationProcessController.showConnectionErrorRetrofit(ComplexRecordKeyCommandActivity.this, call, this);
            }
        }

        public void onFailure(Call<Void> call, Throwable t) {
            super.onFailure(call, t);
            ComplexRecordKeyCommandActivity.this.handleCallFailure();
        }
    }

    class C09015 implements OnClickListener {
        C09015() {
        }

        public void onClick(View v) {
            ComplexRecordKeyCommandActivity.this.stopPolling();
            ComplexTeachingController state = ComplexTeachingController.getComplexTeachingController();
            state.setCurrentStepIndex(state.determineCurrentStep(state.getCurrentRunIndex()));
            InstallationProcessController.startActivity(ComplexRecordKeyCommandActivity.this, SetACToSettingActivity.class, true);
        }
    }

    class C09026 implements OnClickListener {
        C09026() {
        }

        public void onClick(View v) {
            ComplexRecordKeyCommandActivity.this.restart();
            ComplexRecordKeyCommandActivity.this.errorDialogShown = false;
        }
    }

    class C09037 implements AnimatorListener {
        C09037() {
        }

        public void onAnimationStart(Animator animation) {
        }

        public void onAnimationEnd(Animator animation) {
            int stepIndex = ComplexTeachingController.getComplexTeachingController().getCurrentStepIndex();
            ComplexRecordKeyCommandActivity.this.setCommandImageResource(ComplexRecordKeyCommandActivity.this.mTeachingRun, stepIndex);
            ComplexRecordKeyCommandActivity.this.setTextCommand(ComplexRecordKeyCommandActivity.this.mTeachingRun, stepIndex);
        }

        public void onAnimationCancel(Animator animation) {
        }

        public void onAnimationRepeat(Animator animation) {
        }
    }

    class C09048 implements AnimatorListener {
        C09048() {
        }

        public void onAnimationStart(Animator animation) {
        }

        public void onAnimationEnd(Animator animation) {
            ComplexRecordKeyCommandActivity.this.getWindow().clearFlags(16);
            if (ComplexTeachingController.getComplexTeachingController().getCurrentStepIndex() + 1 <= ComplexRecordKeyCommandActivity.this.mGridView.getAdapter().getCount()) {
                ComplexRecordKeyCommandActivity.this.callGetCommandSetRecordingStep(300);
            }
        }

        public void onAnimationCancel(Animator animation) {
        }

        public void onAnimationRepeat(Animator animation) {
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_complex_record_key_command);
        ButterKnife.bind((Activity) this);
        getWindow().addFlags(128);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_title);
        int stepIndex = ComplexTeachingController.getComplexTeachingController().getCurrentStepIndex();
        this.mTeachingRun = ComplexTeachingController.getComplexTeachingController().getCurrentTeachingMode().getRuns()[ComplexTeachingController.getComplexTeachingController().getCurrentRunIndex()];
        this.initializedData = false;
        this.mGridView.setAdapter(new CommandsGridViewAdapter(this, C0676R.layout.commands_grid_item_layout, getRunData(this.mTeachingRun, stepIndex), stepIndex));
        setCommandImageResource(this.mTeachingRun, stepIndex);
        setTextCommand(this.mTeachingRun, stepIndex);
    }

    protected void onResume() {
        super.onResume();
        handleTeachingRunState();
    }

    protected void onPause() {
        super.onPause();
        if (this.handler != null) {
            this.handler.removeCallbacksAndMessages(null);
        }
    }

    private void callCheckAcSettingsForMode() {
        GetCommandSetRecordingModeRequest request = new GetCommandSetRecordingModeRequest(ComplexTeachingController.getComplexTeachingController().getRecordingId(), ComplexTeachingController.getComplexTeachingController().getCurrentTeachingMode().getMode());
    }

    private void callResume() {
        hideProceedButton();
        hideRepeatButton();
        int stepIndex = ComplexTeachingController.getComplexTeachingController().getCurrentStepIndex();
        if (stepIndex < this.mGridView.getAdapter().getCount()) {
            ((CommandsGridViewAdapter) this.mGridView.getAdapter()).setCurrentStep(stepIndex);
            RestServiceGenerator.getTadoInstallationRestService().resumeAcSettingCommandSetRecordingRun(Long.valueOf((long) ComplexTeachingController.getComplexTeachingController().getRecordingId()), ModeEnum.valueOf(ComplexTeachingController.getComplexTeachingController().getCurrentTeachingMode().getMode()), ComplexTeachingController.getComplexTeachingController().getCurrentRunIndex(), stepIndex, new InProgessRecordingState()).enqueue(new C08993());
        }
    }

    private void callStart() {
        hideProceedButton();
        hideRepeatButton();
        ((CommandsGridViewAdapter) this.mGridView.getAdapter()).setCurrentStep(0);
        RestServiceGenerator.getTadoInstallationRestService().startAcSettingCommandSetRecordingRun(Long.valueOf((long) ComplexTeachingController.getComplexTeachingController().getRecordingId()), ModeEnum.valueOf(ComplexTeachingController.getComplexTeachingController().getCurrentTeachingMode().getMode()), Integer.valueOf(ComplexTeachingController.getComplexTeachingController().getCurrentRunIndex()), new InProgessRecordingState()).enqueue(new C09004());
    }

    private void handleTeachingRunState() {
        String recordingState = this.mTeachingRun.getRecordingState();
        boolean z = true;
        switch (recordingState.hashCode()) {
            case -1391247659:
                if (recordingState.equals("NOT_STARTED")) {
                    z = true;
                    break;
                }
                break;
            case -604548089:
                if (recordingState.equals("IN_PROGRESS")) {
                    z = true;
                    break;
                }
                break;
            case 108966002:
                if (recordingState.equals("FINISHED")) {
                    z = true;
                    break;
                }
                break;
            case 2066319421:
                if (recordingState.equals("FAILED")) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                hideProceedButton();
                hideRepeatButton();
                callResume();
                return;
            case true:
                hideProceedButton();
                hideRepeatButton();
                callGetCommandSetRecordingStep(100);
                return;
            case true:
                showProceedButton();
                showRepeatButton();
                this.pollRequestOpen.set(false);
                return;
            case true:
                callStart();
                hideProceedButton();
                hideRepeatButton();
                return;
            default:
                return;
        }
    }

    public void showErrorDialog() {
        if (!this.errorDialogShown) {
            this.mTeachingRun.setRecordingState("FAILED");
            CustomDialog.showComplexTeachingErrorDialog(getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_recording_noSignalDetected_title), getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_recording_noSignalDetected_message), getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_recording_noSignalDetected_retryCommandButton), getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_recording_noSignalDetected_retryRunButton), new C09015(), new C09026(), this);
            this.errorDialogShown = true;
        }
    }

    private void hideProceedButton() {
        this.proceedButton.setVisibility(4);
    }

    private void showProceedButton() {
        this.proceedButton.setVisibility(0);
        this.proceedButton.setText(getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_recording_confirmButton));
    }

    private void hideRepeatButton() {
        this.mRepeatButton.setVisibility(4);
    }

    private void showRepeatButton() {
        this.mRepeatButton.setVisibility(0);
    }

    private void hideImages() {
        this.mTickView.setVisibility(0);
        this.mCommandView.setVisibility(4);
        this.mRemoteView.setVisibility(0);
        this.mRemoteView.setAlpha(1.0f);
        this.titleTemplateTextview.setAlpha(1.0f);
        this.titleTemplateTextview.setText(getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_recording_recordingCompletedLabel));
    }

    public void showTeachingStepProgress() {
        int stepIndex = ComplexTeachingController.getComplexTeachingController().getCurrentStepIndex();
        if (stepIndex < this.mTeachingRun.getSteps().length) {
            if (!this.initTickLocation) {
                this.initTickLocation = true;
                this.mTickView.getLocationOnScreen(this.mTickViewLocation);
            }
            this.mTickView.bringToFront();
            startAnimations(this.mTickView, this.mGridView.getChildAt(stepIndex).findViewById(C0676R.id.commands_grid_item_text));
        }
    }

    private void restart() {
        stopPolling();
        this.mTeachingRun.setRecordingState("FAILED");
        ComplexTeachingController.getComplexTeachingController().setCurrentStepIndex(0);
        InstallationProcessController.startActivity((Activity) this, SetACToSettingActivity.class, true);
    }

    public void repeat(View view) {
        restart();
    }

    public void proceedClick(View view) {
        InstallationProcessController.startActivity((Activity) this, PointToACActivity.class, true);
    }

    private void setCommandImageResource(TeachingRun teachingRun, int stepIndex) {
        if (stepIndex < teachingRun.getSteps().length) {
            String command = teachingRun.getSteps()[stepIndex].getKeyToPressToRecordThisCommand();
            if (this.mCommandsImageResourceMap.containsKey(command)) {
                this.mCommandView.setImageResource(((Integer) this.mCommandsImageResourceMap.get(command)).intValue());
            }
        }
    }

    private List<String> getRunData(TeachingRun teachingRun, int currentStepIndex) {
        List<String> data = new ArrayList();
        String command = "";
        int i;
        if (currentStepIndex < 2) {
            this.initializedData = true;
            int length = Math.min(2, teachingRun.getSteps().length);
            for (i = 0; i < length; i++) {
                command = teachingRun.getSteps()[i].getKeyToPressToRecordThisCommand();
                if ("POWER_OFF".equalsIgnoreCase(command)) {
                    data.add("OFF");
                } else if ("POWER_ON".equalsIgnoreCase(command)) {
                    data.add("ON");
                }
            }
        } else {
            int temperature = 0;
            TemperatureRange temperatureRange = teachingRun.getSummary().getTemperatures();
            if (temperatureRange != null) {
                int min = temperatureRange.getMin();
                int max = temperatureRange.getMax();
                for (TeachingStep keyToPressToRecordThisCommand : teachingRun.getSteps()) {
                    command = keyToPressToRecordThisCommand.getKeyToPressToRecordThisCommand();
                    if ("POWER_OFF".equalsIgnoreCase(command)) {
                        if (!this.initializedData) {
                            data.add("OFF");
                        }
                    } else if (!"POWER_ON".equalsIgnoreCase(command)) {
                        int temperature2;
                        if (Key.TEMP_UP.equalsIgnoreCase(command)) {
                            if (temperature == 0) {
                                temperature = min;
                                temperature2 = temperature + 1;
                                data.add(temperature + "°");
                                temperature = temperature2;
                            }
                            temperature2 = temperature + 1;
                            data.add(temperature + "°");
                            temperature = temperature2;
                        } else if (Key.TEMP_DOWN.equalsIgnoreCase(command)) {
                            if (temperature == 0) {
                                temperature = max;
                                temperature2 = temperature - 1;
                                data.add(temperature + "°");
                                temperature = temperature2;
                            }
                            temperature2 = temperature - 1;
                            data.add(temperature + "°");
                            temperature = temperature2;
                        }
                    }
                }
            }
        }
        return data;
    }

    private void setTextCommand(TeachingRun teachingRun, int stepIndex) {
        if (stepIndex < teachingRun.getSteps().length) {
            String command = teachingRun.getSteps()[stepIndex].getKeyToPressToRecordThisCommand();
            if (this.commandsTextResourcesMap.containsKey(command)) {
                this.titleTemplateTextview.setText(getString(((Integer) this.commandsTextResourcesMap.get(command)).intValue()));
            }
        }
    }

    private ObjectAnimator getFadeAnimation(View view, float from, float to) {
        ObjectAnimator objectAnimator = new ObjectAnimator();
        return ObjectAnimator.ofFloat(view, ToolTipView.ALPHA_COMPAT, new float[]{from, to});
    }

    private void startAnimations(View startView, View endView) {
        if (endView != null) {
            Rect rect = new Rect();
            getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            int satusBarHeight = rect.top;
            int startHeight = startView.getHeight();
            int endHeight = endView.getHeight();
            int startWidth = startView.getWidth();
            int endWidth = endView.getWidth();
            ScrollView scrollView = (ScrollView) findViewById(C0676R.id.scrollview);
            int[] endViewLocation = new int[2];
            endView.getLocationOnScreen(endViewLocation);
            final float sx = (float) this.mTickViewLocation[0];
            final float sy = (float) this.mTickViewLocation[1];
            float ex = (float) endViewLocation[0];
            float ey = (float) ((endViewLocation[1] - satusBarHeight) + scrollView.getScrollY());
            startView.setPivotX(0.0f);
            startView.setPivotY(0.0f);
            ObjectAnimator fadeOutRemote = getFadeAnimation(this.mRemoteView, 1.0f, 0.0f);
            ObjectAnimator fadeOutCommand = getFadeAnimation(this.mCommandView, 1.0f, 0.0f);
            ObjectAnimator fadeOutCommandTitle = getFadeAnimation(this.titleTemplateTextview, 1.0f, 0.0f);
            ObjectAnimator fadeInTick = getFadeAnimation(this.mTickView, 0.0f, 1.0f);
            AnimatorSet commandSet = new AnimatorSet();
            commandSet.setDuration((long) this.animationDelays[0]);
            commandSet.setStartDelay((long) this.animationDelays[0]);
            commandSet.setInterpolator(new AccelerateDecelerateInterpolator());
            commandSet.playTogether(new Animator[]{fadeOutCommand, fadeOutCommandTitle, fadeOutRemote, fadeInTick});
            commandSet.addListener(new C09037());
            ObjectAnimator fadeInRemote = getFadeAnimation(this.mRemoteView, 0.0f, 1.0f);
            ObjectAnimator fadeInCommand = getFadeAnimation(this.mCommandView, 0.0f, 1.0f);
            ObjectAnimator fadeInCommandTitle = getFadeAnimation(this.titleTemplateTextview, 0.0f, 1.0f);
            final AnimatorSet fadeInControllerSet = new AnimatorSet();
            fadeInControllerSet.setDuration((long) this.animationDelays[1]);
            fadeInControllerSet.setStartDelay((long) this.animationDelays[1]);
            fadeInControllerSet.setInterpolator(new DecelerateInterpolator());
            fadeInControllerSet.playTogether(new Animator[]{fadeInRemote, fadeInCommand, fadeInCommandTitle});
            fadeInControllerSet.addListener(new C09048());
            ObjectAnimator objectAnimator = new ObjectAnimator();
            View view = startView;
            ObjectAnimator translateX = ObjectAnimator.ofFloat(view, "x", new float[]{sx, ex});
            objectAnimator = new ObjectAnimator();
            view = startView;
            ObjectAnimator translateY = ObjectAnimator.ofFloat(view, "y", new float[]{sy, ey - ((View) startView.getParent()).getY()});
            objectAnimator = new ObjectAnimator();
            ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(startView, ToolTipView.SCALE_X_COMPAT, new float[]{1.0f, ((float) endHeight) / ((float) startHeight)});
            objectAnimator = new ObjectAnimator();
            ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(startView, ToolTipView.SCALE_Y_COMPAT, new float[]{1.0f, ((float) endWidth) / ((float) startWidth)});
            final ObjectAnimator fadeOut = getFadeAnimation(startView, 1.0f, 0.0f);
            AnimatorSet set = new AnimatorSet();
            set.setStartDelay((long) this.animationDelays[2]);
            set.setDuration((long) this.animationDurations[2]);
            set.setInterpolator(new AccelerateDecelerateInterpolator());
            set.playTogether(new Animator[]{translateX, translateY, scaleDownX, scaleDownY});
            final View view2 = endView;
            final View view3 = startView;
            set.addListener(new AnimatorListener() {

                class C09051 implements AnimatorListener {
                    C09051() {
                    }

                    public void onAnimationStart(Animator animation) {
                    }

                    public void onAnimationEnd(Animator animation) {
                        view3.setScaleX(1.0f);
                        view3.setScaleY(1.0f);
                        view3.setAlpha(1.0f);
                        view3.setX(sx);
                        view3.setY(sy - ((View) view3.getParent()).getY());
                        view3.setVisibility(4);
                        int stepIndex = ComplexTeachingController.getComplexTeachingController().getCurrentStepIndex();
                        if (stepIndex == 2) {
                            if (ComplexRecordKeyCommandActivity.this.mTeachingRun.getSteps().length > 2) {
                                ((CommandsGridViewAdapter) ComplexRecordKeyCommandActivity.this.mGridView.getAdapter()).addItems(ComplexRecordKeyCommandActivity.this.getRunData(ComplexRecordKeyCommandActivity.this.mTeachingRun, stepIndex));
                            }
                            ((CommandsGridViewAdapter) ComplexRecordKeyCommandActivity.this.mGridView.getAdapter()).setCurrentStep(stepIndex);
                        }
                        if (stepIndex + 1 > ComplexRecordKeyCommandActivity.this.mGridView.getAdapter().getCount()) {
                            ComplexRecordKeyCommandActivity.this.getWindow().clearFlags(16);
                            ((CommandsGridViewAdapter) ComplexRecordKeyCommandActivity.this.mGridView.getAdapter()).setCurrentStep(stepIndex);
                            ComplexRecordKeyCommandActivity.this.showProceedButton();
                            ComplexRecordKeyCommandActivity.this.showRepeatButton();
                            ComplexRecordKeyCommandActivity.this.hideImages();
                            return;
                        }
                        fadeInControllerSet.start();
                    }

                    public void onAnimationCancel(Animator animation) {
                    }

                    public void onAnimationRepeat(Animator animation) {
                    }
                }

                public void onAnimationStart(Animator animation) {
                }

                public void onAnimationEnd(Animator animation) {
                    view2.setBackgroundResource(C0676R.drawable.commands_grid_green_background);
                    fadeOut.setDuration((long) ComplexRecordKeyCommandActivity.this.animationDurations[3]);
                    fadeOut.setStartDelay((long) ComplexRecordKeyCommandActivity.this.animationDelays[3]);
                    fadeOut.setInterpolator(new LinearInterpolator());
                    fadeOut.addListener(new C09051());
                    fadeOut.start();
                }

                public void onAnimationCancel(Animator animation) {
                }

                public void onAnimationRepeat(Animator animation) {
                }
            });
            final AnimatorSet animatorSet = set;
            commandSet.addListener(new AnimatorListener() {
                public void onAnimationStart(Animator animation) {
                    ComplexRecordKeyCommandActivity.this.mTickView.setVisibility(0);
                    ComplexRecordKeyCommandActivity.this.getWindow().setFlags(16, 16);
                }

                public void onAnimationEnd(Animator animation) {
                    animatorSet.start();
                }

                public void onAnimationCancel(Animator animation) {
                }

                public void onAnimationRepeat(Animator animation) {
                }
            });
            commandSet.start();
        }
    }

    private void stopPolling() {
        if (this.handler != null) {
            this.handler.removeCallbacksAndMessages(null);
            this.pollRequestOpen.set(false);
        }
    }

    private void callGetCommandSetRecordingStep(int initialDelay) {
        this.handler = new Handler();
        this.handler.postDelayed(new Runnable() {
            public void run() {
                ComplexRecordKeyCommandActivity.this.pollStatus();
                ComplexRecordKeyCommandActivity.this.handler.postDelayed(this, 1000);
            }
        }, (long) initialDelay);
    }

    private void pollStatus() {
        int stepIndex = ComplexTeachingController.getComplexTeachingController().getCurrentStepIndex();
        if (Util.isNetworkAvailable() && this.pollRequestOpen.compareAndSet(false, true)) {
            RestServiceGenerator.getTadoInstallationRestService().showAcSettingCommandSetRecordingStep(Long.valueOf((long) ComplexTeachingController.getComplexTeachingController().getRecordingId()), ModeEnum.valueOf(ComplexTeachingController.getComplexTeachingController().getCurrentTeachingMode().getMode()), ComplexTeachingController.getComplexTeachingController().getCurrentRunIndex(), stepIndex).enqueue(new TadoCallback<TeachingStep>() {
                public void onResponse(Call<TeachingStep> call, Response<TeachingStep> response) {
                    super.onResponse(call, response);
                    ComplexRecordKeyCommandActivity.this.pollRequestOpen.set(false);
                    if (response.isSuccessful()) {
                        String stepRecordingState = ((TeachingStep) response.body()).getRecordingState();
                        ComplexTeachingController state = ComplexTeachingController.getComplexTeachingController();
                        int stepIndex = state.getCurrentStepIndex();
                        state.getCurrentTeachingMode().getRuns()[state.getCurrentRunIndex()].getSteps()[stepIndex].setRecordingState(stepRecordingState);
                        boolean z = true;
                        switch (stepRecordingState.hashCode()) {
                            case -604548089:
                                if (stepRecordingState.equals("IN_PROGRESS")) {
                                    z = true;
                                    break;
                                }
                                break;
                            case 108966002:
                                if (stepRecordingState.equals("FINISHED")) {
                                    z = false;
                                    break;
                                }
                                break;
                            case 2066319421:
                                if (stepRecordingState.equals("FAILED")) {
                                    z = true;
                                    break;
                                }
                                break;
                        }
                        switch (z) {
                            case false:
                                ComplexRecordKeyCommandActivity.this.stopPolling();
                                ComplexRecordKeyCommandActivity.this.mProgressLayout.setVisibility(4);
                                ComplexRecordKeyCommandActivity.this.showTeachingStepProgress();
                                if (stepIndex + 1 <= ComplexRecordKeyCommandActivity.this.mGridView.getAdapter().getCount()) {
                                    ComplexTeachingController.getComplexTeachingController().setCurrentStepIndex(stepIndex + 1);
                                    return;
                                }
                                ComplexRecordKeyCommandActivity.this.mTeachingRun.setRecordingState("FINISHED");
                                return;
                            case true:
                                ComplexRecordKeyCommandActivity.this.mProgressLayout.setVisibility(0);
                                ComplexRecordKeyCommandActivity.this.mTeachingRun.setRecordingState("IN_PROGRESS");
                                return;
                            case true:
                                ComplexRecordKeyCommandActivity.this.mProgressLayout.setVisibility(4);
                                ComplexRecordKeyCommandActivity.this.mTeachingRun.setRecordingState("FAILED");
                                ComplexRecordKeyCommandActivity.this.showErrorDialog();
                                return;
                            default:
                                return;
                        }
                    } else if (this.serverError != null) {
                        InstallationProcessController.handleError(ComplexRecordKeyCommandActivity.this, this.serverError, (Call) call, response.code());
                    } else {
                        InstallationProcessController.showConnectionErrorRetrofit(ComplexRecordKeyCommandActivity.this, call, this);
                    }
                }

                public void onFailure(Call<TeachingStep> call, Throwable t) {
                    super.onFailure(call, t);
                    ComplexRecordKeyCommandActivity.this.handleCallFailure();
                }
            });
        }
    }

    private void handleCallFailure() {
        this.mProgressLayout.setVisibility(4);
        showErrorDialog();
    }
}
