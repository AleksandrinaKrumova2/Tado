package com.tado.android.installation.acsetup;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import com.tado.C0676R;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.dialogs.AlertWarningDialogListener;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.installation.AcInstallation;
import com.tado.android.rest.model.installation.AcSetupPhase;
import com.tado.android.rest.model.installation.AcSetupPhase.PhaseEnum;
import com.tado.android.rest.model.installation.CandidateRating;
import com.tado.android.rest.model.installation.CandidateRating.RatingEnum;
import com.tado.android.rest.model.installation.InstallationStateTransitionResult;
import com.tado.android.rest.model.installation.OnOffCandidate;
import com.tado.android.rest.model.installation.OnOffCandidateList;
import com.tado.android.rest.model.installation.TestOnOff;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.Constants;
import com.tado.android.utils.UserConfig;
import java.util.Collections;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class TestingCommandSetsActivity extends ACInstallationBaseActivity {
    private boolean acWasReacting = false;
    AnimationDrawable animationDrawable;
    @BindView(2131296326)
    ImageView animationView;
    private List<OnOffCandidate> commandSetEntries;
    private int currentIndex;
    private boolean finishState = false;
    private Handler handler;
    private boolean loadingState = true;
    @BindView(2131296679)
    ImageView noImageView;
    @BindView(2131296425)
    ImageView part1ImageView;
    @BindView(2131296424)
    TextView part1TextView;
    @BindView(2131296880)
    ProgressBar progressBar;
    @BindView(2131297165)
    TextView redoTextView;
    @BindView(2131296680)
    ImageView yesImageView;

    class C08571 extends TadoCallback<OnOffCandidateList> {

        class C08561 implements AlertWarningDialogListener {
            C08561() {
            }

            public void OnOKClicked() {
                TestingCommandSetsActivity.this.restartACSetupPhase();
            }
        }

        C08571() {
        }

        public void onResponse(Call<OnOffCandidateList> call, Response<OnOffCandidateList> response) {
            super.onResponse(call, response);
            TestingCommandSetsActivity.this.dismissLoadingUI();
            if (!response.isSuccessful() || ((OnOffCandidateList) response.body()).getCandidates() == null || ((OnOffCandidateList) response.body()).getCandidates().isEmpty()) {
                AlertDialogs.showSimpleWarning(TestingCommandSetsActivity.this.getString(C0676R.string.installation_sacc_setupAC_onOffReduction_existingCommandSets_errors_noCommandSets_title), TestingCommandSetsActivity.this.getString(C0676R.string.installation_sacc_setupAC_onOffReduction_existingCommandSets_errors_noCommandSets_message), TestingCommandSetsActivity.this.getString(C0676R.string.installation_sacc_setupAC_onOffReduction_existingCommandSets_errors_noCommandSets_confirmButton), TestingCommandSetsActivity.this, new C08561());
                return;
            }
            TestingCommandSetsActivity.this.commandSetEntries = ((OnOffCandidateList) response.body()).getCandidates();
            TestingCommandSetsActivity.this.processCandidateList();
        }

        public void onFailure(Call<OnOffCandidateList> call, Throwable t) {
            super.onFailure(call, t);
            TestingCommandSetsActivity.this.dismissLoadingUI();
            InstallationProcessController.showConnectionErrorRetrofit(TestingCommandSetsActivity.this, call, this);
        }
    }

    class C08582 extends TadoCallback<InstallationStateTransitionResult> {
        C08582() {
        }

        public void onResponse(Call<InstallationStateTransitionResult> call, Response<InstallationStateTransitionResult> response) {
            super.onResponse(call, response);
            TestingCommandSetsActivity.this.dismissLoadingUI();
            if (response.isSuccessful()) {
                InstallationProcessController.getInstallationProcessController().goToScreenForInstallationProcessStatus(TestingCommandSetsActivity.this, ((InstallationStateTransitionResult) response.body()).getInstallation());
                return;
            }
            TestingCommandSetsActivity.this.handleServerError(this.serverError, TestingCommandSetsActivity.this, call, response.code(), this);
        }

        public void onFailure(Call<InstallationStateTransitionResult> call, Throwable t) {
            super.onFailure(call, t);
            TestingCommandSetsActivity.this.dismissLoadingUI();
            InstallationProcessController.showConnectionErrorRetrofit(TestingCommandSetsActivity.this, call, this);
        }
    }

    class C08644 extends TadoCallback<Void> {
        C08644() {
        }

        public void onResponse(final Call<Void> call, Response<Void> response) {
            super.onResponse(call, response);
            if (!response.isSuccessful()) {
                String code = this.serverError.getCode();
                Object obj = -1;
                switch (code.hashCode()) {
                    case -2095386907:
                        if (code.equals(Constants.SERVER_ERROR_IR_COMMAND_SEND_EXCEPTION)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case -791718753:
                        if (code.equals(Constants.SERVER_ERROR_HW_DEVICE_NOT_REACHABLE_EXCEPTION)) {
                            obj = null;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case null:
                    case 1:
                        AlertDialogs.showWarning(TestingCommandSetsActivity.this.getString(C0676R.string.installation_error_alert_title), TestingCommandSetsActivity.this.getString(C0676R.string.could_not_connect_to_server), TestingCommandSetsActivity.this.getString(C0676R.string.alert_retry), TestingCommandSetsActivity.this, new AlertWarningDialogListener() {
                            public void OnOKClicked() {
                                C08644.this.retry(call);
                            }
                        });
                        return;
                    default:
                        TestingCommandSetsActivity.this.handleServerError(this.serverError, TestingCommandSetsActivity.this, call, response.code(), this);
                        return;
                }
            } else if (TestingCommandSetsActivity.this.currentIndex > 0) {
                TestingCommandSetsActivity.this.currentIndex = TestingCommandSetsActivity.this.currentIndex - 1;
                TestingCommandSetsActivity.this.resetCommandSetRating(TestingCommandSetsActivity.this.currentIndex);
            }
        }

        public void onFailure(Call<Void> call, Throwable t) {
            super.onFailure(call, t);
            InstallationProcessController.showConnectionErrorRetrofit(TestingCommandSetsActivity.this, call, this);
        }
    }

    class C08665 extends TadoCallback<OnOffCandidateList> {
        C08665() {
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onResponse(final retrofit2.Call<com.tado.android.rest.model.installation.OnOffCandidateList> r7, retrofit2.Response<com.tado.android.rest.model.installation.OnOffCandidateList> r8) {
            /*
            r6 = this;
            r0 = 0;
            super.onResponse(r7, r8);
            r1 = r8.isSuccessful();
            if (r1 == 0) goto L_0x0030;
        L_0x000a:
            r1 = com.tado.android.installation.acsetup.TestingCommandSetsActivity.this;
            r1.acWasReacting = r0;
            r1 = com.tado.android.installation.acsetup.TestingCommandSetsActivity.this;
            r1.showLoadingScreen(r0);
            r1 = com.tado.android.installation.acsetup.TestingCommandSetsActivity.this;
            r1 = r1.redoTextView;
            r1.setVisibility(r0);
            r1 = com.tado.android.installation.acsetup.TestingCommandSetsActivity.this;
            r0 = r8.body();
            r0 = (com.tado.android.rest.model.installation.OnOffCandidateList) r0;
            r0 = r0.getCandidates();
            r1.commandSetEntries = r0;
            r0 = com.tado.android.installation.acsetup.TestingCommandSetsActivity.this;
            r0.processCandidateList();
        L_0x002f:
            return;
        L_0x0030:
            r1 = r6.serverError;
            r2 = r1.getCode();
            r1 = -1;
            r3 = r2.hashCode();
            switch(r3) {
                case -2095386907: goto L_0x005c;
                case -791718753: goto L_0x0052;
                default: goto L_0x003e;
            };
        L_0x003e:
            r0 = r1;
        L_0x003f:
            switch(r0) {
                case 0: goto L_0x0067;
                case 1: goto L_0x0067;
                default: goto L_0x0042;
            };
        L_0x0042:
            r0 = com.tado.android.installation.acsetup.TestingCommandSetsActivity.this;
            r1 = r6.serverError;
            r2 = com.tado.android.installation.acsetup.TestingCommandSetsActivity.this;
            r4 = r8.code();
            r3 = r7;
            r5 = r6;
            r0.handleServerError(r1, r2, r3, r4, r5);
            goto L_0x002f;
        L_0x0052:
            r3 = "hwDeviceNotReachable";
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x003e;
        L_0x005b:
            goto L_0x003f;
        L_0x005c:
            r0 = "irCommandSendException";
            r0 = r2.equals(r0);
            if (r0 == 0) goto L_0x003e;
        L_0x0065:
            r0 = 1;
            goto L_0x003f;
        L_0x0067:
            r0 = com.tado.android.installation.acsetup.TestingCommandSetsActivity.this;
            r1 = 2131690004; // 0x7f0f0214 float:1.900904E38 double:1.0531947986E-314;
            r0 = r0.getString(r1);
            r1 = com.tado.android.installation.acsetup.TestingCommandSetsActivity.this;
            r2 = 2131689714; // 0x7f0f00f2 float:1.9008451E38 double:1.0531946553E-314;
            r1 = r1.getString(r2);
            r2 = com.tado.android.installation.acsetup.TestingCommandSetsActivity.this;
            r3 = 2131689555; // 0x7f0f0053 float:1.9008129E38 double:1.0531945767E-314;
            r2 = r2.getString(r3);
            r3 = com.tado.android.installation.acsetup.TestingCommandSetsActivity.this;
            r4 = new com.tado.android.installation.acsetup.TestingCommandSetsActivity$5$1;
            r4.<init>(r7);
            com.tado.android.dialogs.AlertDialogs.showWarning(r0, r1, r2, r3, r4);
            goto L_0x002f;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tado.android.installation.acsetup.TestingCommandSetsActivity.5.onResponse(retrofit2.Call, retrofit2.Response):void");
        }

        public void onFailure(Call<OnOffCandidateList> call, Throwable t) {
            super.onFailure(call, t);
            InstallationProcessController.showConnectionErrorRetrofit(TestingCommandSetsActivity.this, call, this);
        }
    }

    class C08686 extends TadoCallback<OnOffCandidateList> {
        C08686() {
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onResponse(final retrofit2.Call<com.tado.android.rest.model.installation.OnOffCandidateList> r7, retrofit2.Response<com.tado.android.rest.model.installation.OnOffCandidateList> r8) {
            /*
            r6 = this;
            r0 = 0;
            super.onResponse(r7, r8);
            r1 = r8.isSuccessful();
            if (r1 == 0) goto L_0x0024;
        L_0x000a:
            r1 = com.tado.android.installation.acsetup.TestingCommandSetsActivity.this;
            r1.showLoadingScreen(r0);
            r1 = com.tado.android.installation.acsetup.TestingCommandSetsActivity.this;
            r0 = r8.body();
            r0 = (com.tado.android.rest.model.installation.OnOffCandidateList) r0;
            r0 = r0.getCandidates();
            r1.commandSetEntries = r0;
            r0 = com.tado.android.installation.acsetup.TestingCommandSetsActivity.this;
            r0.processCandidateList();
        L_0x0023:
            return;
        L_0x0024:
            r1 = r6.serverError;
            r2 = r1.getCode();
            r1 = -1;
            r3 = r2.hashCode();
            switch(r3) {
                case -2095386907: goto L_0x0050;
                case -791718753: goto L_0x0046;
                default: goto L_0x0032;
            };
        L_0x0032:
            r0 = r1;
        L_0x0033:
            switch(r0) {
                case 0: goto L_0x005b;
                case 1: goto L_0x005b;
                default: goto L_0x0036;
            };
        L_0x0036:
            r0 = com.tado.android.installation.acsetup.TestingCommandSetsActivity.this;
            r1 = r6.serverError;
            r2 = com.tado.android.installation.acsetup.TestingCommandSetsActivity.this;
            r4 = r8.code();
            r3 = r7;
            r5 = r6;
            r0.handleServerError(r1, r2, r3, r4, r5);
            goto L_0x0023;
        L_0x0046:
            r3 = "hwDeviceNotReachable";
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0032;
        L_0x004f:
            goto L_0x0033;
        L_0x0050:
            r0 = "irCommandSendException";
            r0 = r2.equals(r0);
            if (r0 == 0) goto L_0x0032;
        L_0x0059:
            r0 = 1;
            goto L_0x0033;
        L_0x005b:
            r0 = com.tado.android.installation.acsetup.TestingCommandSetsActivity.this;
            r1 = 2131690004; // 0x7f0f0214 float:1.900904E38 double:1.0531947986E-314;
            r0 = r0.getString(r1);
            r1 = com.tado.android.installation.acsetup.TestingCommandSetsActivity.this;
            r2 = 2131689714; // 0x7f0f00f2 float:1.9008451E38 double:1.0531946553E-314;
            r1 = r1.getString(r2);
            r2 = com.tado.android.installation.acsetup.TestingCommandSetsActivity.this;
            r3 = 2131689555; // 0x7f0f0053 float:1.9008129E38 double:1.0531945767E-314;
            r2 = r2.getString(r3);
            r3 = com.tado.android.installation.acsetup.TestingCommandSetsActivity.this;
            r4 = new com.tado.android.installation.acsetup.TestingCommandSetsActivity$6$1;
            r4.<init>(r7);
            com.tado.android.dialogs.AlertDialogs.showWarning(r0, r1, r2, r3, r4);
            goto L_0x0023;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tado.android.installation.acsetup.TestingCommandSetsActivity.6.onResponse(retrofit2.Call, retrofit2.Response):void");
        }

        public void onFailure(Call<OnOffCandidateList> call, Throwable t) {
            super.onFailure(call, t);
            InstallationProcessController.showConnectionErrorRetrofit(TestingCommandSetsActivity.this, call, this);
        }
    }

    class C08697 extends TadoCallback<InstallationStateTransitionResult> {
        C08697() {
        }

        public void onResponse(Call<InstallationStateTransitionResult> call, Response<InstallationStateTransitionResult> response) {
            super.onResponse(call, response);
            if (response.isSuccessful()) {
                TestingCommandSetsActivity.this.showLoadingScreen(false);
                AcInstallation installation = ((InstallationStateTransitionResult) response.body()).getInstallation();
                if (installation == null || installation.getState() == null) {
                    InstallationProcessController.getInstallationProcessController().detectStatus(TestingCommandSetsActivity.this);
                    return;
                } else {
                    InstallationProcessController.getInstallationProcessController().goToScreenForInstallationProcessStatus(TestingCommandSetsActivity.this, installation);
                    return;
                }
            }
            TestingCommandSetsActivity.this.handleServerError(this.serverError, TestingCommandSetsActivity.this, call, response.code(), this);
        }

        public void onFailure(Call<InstallationStateTransitionResult> call, Throwable t) {
            super.onFailure(call, t);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_testing_command_sets);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_setupAC_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_setupAC_onOffReduction_acTinder_message);
        this.textView.setText(C0676R.string.installation_sacc_setupAC_onOffReduction_acTinder_description);
        this.part1ImageView.setVisibility(4);
        this.redoTextView.setVisibility(4);
        this.proceedButton.setText(C0676R.string.installation_sacc_setupAC_onOffReduction_acTinder_confirmButton);
        this.proceedButton.setVisibility(4);
        this.progressBar.setVisibility(4);
        this.animationView = (ImageView) findViewById(C0676R.id.animation);
        this.animationView.setImageResource(C0676R.drawable.animation_tinder);
        getCandidateCommandSetList();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (this.animationDrawable == null) {
            this.animationDrawable = (AnimationDrawable) this.animationView.getDrawable();
        }
        if (!this.loadingState && !this.finishState) {
            this.animationDrawable.start();
        } else if (this.finishState) {
            this.animationDrawable.stop();
            this.animationDrawable.selectDrawable(0);
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.handler != null) {
            this.handler.removeCallbacksAndMessages(null);
        }
    }

    private OnOffCandidate getCurrentEntry() {
        Collections.sort(this.commandSetEntries);
        for (OnOffCandidate cse : this.commandSetEntries) {
            if (cse.getConfidence() == null) {
                return cse;
            }
        }
        return null;
    }

    private void processCandidateList() {
        OnOffCandidate cse = getCurrentEntry();
        if (cse != null) {
            this.currentIndex = cse.getIndex().intValue();
            postOnOffCommand(this.currentIndex, String.valueOf(this.currentIndex + 1));
            return;
        }
        this.currentIndex = this.commandSetEntries.size();
        finishPhase(true);
    }

    private void updateTexts() {
        if (this.currentIndex == 0) {
            this.redoTextView.setVisibility(4);
        } else {
            this.redoTextView.setVisibility(0);
        }
        this.part1TextView.setText(getString(C0676R.string.installation_sacc_setupAC_onOffReduction_acTinder_signalLabel, new Object[]{Integer.valueOf(Math.min(this.commandSetEntries.size(), this.currentIndex + 1)), Integer.valueOf(this.commandSetEntries.size())}));
    }

    private void showAnimation(boolean show) {
        if (this.animationDrawable != null && !show) {
            this.animationDrawable.stop();
            this.animationDrawable.selectDrawable(0);
        } else if (this.animationDrawable != null && show && !this.finishState) {
            this.animationDrawable.start();
        }
    }

    private void showLoadingScreen(boolean loading) {
        boolean z;
        boolean z2 = true;
        this.loadingState = loading;
        this.noImageView.setEnabled(!loading);
        ImageView imageView = this.yesImageView;
        if (loading) {
            z = false;
        } else {
            z = true;
        }
        imageView.setEnabled(z);
        if (loading) {
            showAnimation(false);
            this.progressBar.setVisibility(0);
        } else {
            this.progressBar.setVisibility(4);
        }
        TextView textView = this.redoTextView;
        if (loading) {
            z2 = false;
        }
        textView.setEnabled(z2);
    }

    private void finishPhase(boolean finish) {
        this.finishState = finish;
        updateTexts();
        if (finish) {
            if (this.animationDrawable != null) {
                this.animationDrawable.stop();
                this.animationDrawable.selectDrawable(0);
            }
            findViewById(C0676R.id.command_set_buttons).setVisibility(4);
            this.part1ImageView.setVisibility(0);
            this.proceedButton.setVisibility(0);
            return;
        }
        findViewById(C0676R.id.command_set_buttons).setVisibility(0);
        this.part1ImageView.setVisibility(4);
        this.proceedButton.setVisibility(4);
    }

    public void redo(View view) {
        animateImageViewToRight(this.animationView);
        finishPhase(false);
        stopCommandSetRating(Math.min(this.currentIndex, this.commandSetEntries.size() - 1));
    }

    public void acReacts(View view) {
        animateImageViewToLeft(this.animationView);
        this.acWasReacting = true;
        postCommandSetRating(this.currentIndex, RatingEnum.CONFIRM);
    }

    public void acDoesNotReact(View view) {
        animateImageViewToLeft(this.animationView);
        this.acWasReacting = false;
        postCommandSetRating(this.currentIndex, RatingEnum.REJECT);
    }

    private void getCandidateCommandSetList() {
        showLoadingUI();
        RestServiceGenerator.getTadoInstallationRestService().listOnOffCandidates(UserConfig.getHomeId(), Integer.valueOf(InstallationProcessController.getInstallationProcessController().getInstallationId())).enqueue(new C08571());
    }

    private void restartACSetupPhase() {
        showLoadingUI();
        RestServiceGenerator.getTadoInstallationRestService().restartAcSetupPhase(UserConfig.getHomeId(), Integer.valueOf(InstallationProcessController.getInstallationProcessController().getInstallationId()), new AcSetupPhase(PhaseEnum.AC_SPECS)).enqueue(new C08582());
    }

    private void postOnOffCommand(final int index, final String text) {
        updateTexts();
        showLoadingScreen(true);
        long waitUntilPost = 0;
        if (this.acWasReacting) {
            waitUntilPost = 2000;
        }
        if (this.handler == null) {
            this.handler = new Handler();
        }
        this.handler.postDelayed(new Runnable() {

            class C08611 extends TadoCallback<Void> {

                class C08591 implements Runnable {
                    C08591() {
                    }

                    public void run() {
                        TestingCommandSetsActivity.this.showLoadingScreen(false);
                    }
                }

                C08611() {
                }

                public void onResponse(final Call<Void> call, Response<Void> response) {
                    super.onResponse(call, response);
                    if (response.isSuccessful()) {
                        if (TestingCommandSetsActivity.this.handler == null) {
                            TestingCommandSetsActivity.this.handler = new Handler();
                        }
                        TestingCommandSetsActivity.this.showAnimation(true);
                        TestingCommandSetsActivity.this.handler.postDelayed(new C08591(), Constants.WAIT_FOR_COMMANDSET_TEST);
                        return;
                    }
                    String code = this.serverError.getCode();
                    boolean z = true;
                    switch (code.hashCode()) {
                        case -2095386907:
                            if (code.equals(Constants.SERVER_ERROR_IR_COMMAND_SEND_EXCEPTION)) {
                                z = true;
                                break;
                            }
                            break;
                        case -791718753:
                            if (code.equals(Constants.SERVER_ERROR_HW_DEVICE_NOT_REACHABLE_EXCEPTION)) {
                                z = false;
                                break;
                            }
                            break;
                    }
                    switch (z) {
                        case false:
                        case true:
                            AlertDialogs.showWarning(TestingCommandSetsActivity.this.getString(C0676R.string.installation_error_alert_title), TestingCommandSetsActivity.this.getString(C0676R.string.could_not_connect_to_server), TestingCommandSetsActivity.this.getString(C0676R.string.alert_retry), TestingCommandSetsActivity.this, new AlertWarningDialogListener() {
                                public void OnOKClicked() {
                                    C08611.this.retry(call);
                                }
                            });
                            return;
                        default:
                            TestingCommandSetsActivity.this.handleServerError(this.serverError, TestingCommandSetsActivity.this, call, response.code(), this);
                            return;
                    }
                }

                public void onFailure(Call<Void> call, Throwable t) {
                    super.onFailure(call, t);
                    InstallationProcessController.showConnectionErrorRetrofit(TestingCommandSetsActivity.this, call, this);
                }
            }

            public void run() {
                RestServiceGenerator.getTadoInstallationRestService().startOnOffCandidateTest(UserConfig.getHomeId(), Integer.valueOf(InstallationProcessController.getInstallationProcessController().getInstallationId()), Long.valueOf((long) index), new TestOnOff(text)).enqueue(new C08611());
            }
        }, waitUntilPost);
    }

    private void stopCommandSetRating(int index) {
        showLoadingScreen(true);
        RestServiceGenerator.getTadoInstallationRestService().stopOnOffCandidateTest(UserConfig.getHomeId(), Integer.valueOf(InstallationProcessController.getInstallationProcessController().getInstallationId()), Long.valueOf((long) index)).enqueue(new C08644());
    }

    private void postCommandSetRating(int index, RatingEnum rating) {
        showLoadingScreen(true);
        RestServiceGenerator.getTadoInstallationRestService().rateCandidate(UserConfig.getHomeId(), Integer.valueOf(InstallationProcessController.getInstallationProcessController().getInstallationId()), Long.valueOf((long) index), new CandidateRating(rating)).enqueue(new C08665());
    }

    private void resetCommandSetRating(int index) {
        showLoadingScreen(true);
        RestServiceGenerator.getTadoInstallationRestService().rateCandidate(UserConfig.getHomeId(), Integer.valueOf(InstallationProcessController.getInstallationProcessController().getInstallationId()), Long.valueOf((long) index), new CandidateRating(RatingEnum.RESET)).enqueue(new C08686());
    }

    public void proceedClick(View view) {
        RestServiceGenerator.getTadoInstallationRestService().confirmOnOffReduction(UserConfig.getHomeId(), Integer.valueOf(InstallationProcessController.getInstallationProcessController().getInstallationId()), new Object()).enqueue(new C08697());
    }

    private void animateImageViewToLeft(View view) {
        float x = view.getX();
        ObjectAnimator objectAnimator = new ObjectAnimator();
        ObjectAnimator animatorCenterToLeft = ObjectAnimator.ofFloat(view, "x", new float[]{view.getX(), (float) (0 - view.getWidth())});
        objectAnimator = new ObjectAnimator();
        final ObjectAnimator animatorToCenterFromRight = ObjectAnimator.ofFloat(view, "x", new float[]{(float) (getScreenWidth() + view.getWidth()), x});
        animatorToCenterFromRight.setDuration(750);
        animatorToCenterFromRight.setInterpolator(new BounceInterpolator());
        animatorCenterToLeft.setDuration(300);
        animatorCenterToLeft.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorCenterToLeft.addListener(new AnimatorListener() {
            public void onAnimationStart(Animator animation) {
            }

            public void onAnimationEnd(Animator animation) {
                animatorToCenterFromRight.start();
            }

            public void onAnimationCancel(Animator animation) {
            }

            public void onAnimationRepeat(Animator animation) {
            }
        });
        animatorCenterToLeft.start();
    }

    private void animateImageViewToRight(View view) {
        float x = view.getX();
        ObjectAnimator objectAnimator = new ObjectAnimator();
        ObjectAnimator animatorCenterToRight = ObjectAnimator.ofFloat(view, "x", new float[]{view.getX(), (float) (getScreenWidth() + view.getWidth())});
        objectAnimator = new ObjectAnimator();
        final ObjectAnimator animatorToCenterFromLeft = ObjectAnimator.ofFloat(view, "x", new float[]{(float) (0 - view.getWidth()), x});
        animatorToCenterFromLeft.setDuration(750);
        animatorToCenterFromLeft.setInterpolator(new BounceInterpolator());
        animatorCenterToRight.setDuration(300);
        animatorCenterToRight.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorCenterToRight.addListener(new AnimatorListener() {
            public void onAnimationStart(Animator animation) {
            }

            public void onAnimationEnd(Animator animation) {
                animatorToCenterFromLeft.start();
            }

            public void onAnimationCancel(Animator animation) {
            }

            public void onAnimationRepeat(Animator animation) {
            }
        });
        animatorCenterToRight.start();
    }

    private int getScreenWidth() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }
}
