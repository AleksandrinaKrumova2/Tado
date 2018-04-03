package com.tado.android.installation;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.tado.C0676R;
import com.tado.android.SplashScreenActivity;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.controllers.ZoneController;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.Invitation;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.settings.model.PresenceDetectionSettings;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import retrofit2.Call;
import retrofit2.Response;

public class InvitationReviewActivity extends AppCompatActivity {
    public static final String KEY_INVITATION = "keyInvitation";
    @BindView(2131296386)
    Button buttonJoinHome;
    @BindView(2131296387)
    Button buttonProceed;
    @BindView(2131296388)
    Button buttonRejectInvitation;
    @BindView(2131296394)
    Button buttonSignOut;
    private Invitation invitation;
    @BindView(2131297079)
    TextView textviewInvitationBody;
    Unbinder unbinder;

    class C08231 implements OnClickListener {
        C08231() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            InvitationReviewActivity.this.startActivity(new Intent(InvitationReviewActivity.this, SplashScreenActivity.class));
            InvitationReviewActivity.this.finish();
        }
    }

    class C08242 extends TadoCallback<Void> {
        C08242() {
        }

        public void onResponse(Call<Void> call, Response<Void> response) {
            super.onResponse(call, response);
            if (response.isSuccessful()) {
                ZoneController.INSTANCE.clear();
                InstallationProcessController.getInstallationProcessController().detectStatus(InvitationReviewActivity.this, false);
            }
        }

        public void onFailure(Call<Void> call, Throwable t) {
            super.onFailure(call, t);
            InstallationProcessController.getInstallationProcessController().detectStatus(InvitationReviewActivity.this, false);
        }
    }

    class C08253 extends TadoCallback<Void> {
        C08253() {
        }

        public void onResponse(Call<Void> call, Response<Void> response) {
            super.onResponse(call, response);
            InstallationProcessController.getInstallationProcessController().detectStatus(InvitationReviewActivity.this, false);
        }

        public void onFailure(Call<Void> call, Throwable t) {
            super.onFailure(call, t);
            InstallationProcessController.getInstallationProcessController().detectStatus(InvitationReviewActivity.this, false);
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_invitation_review);
        this.unbinder = ButterKnife.bind((Activity) this);
        if (getIntent().hasExtra("keyInvitation")) {
            this.invitation = (Invitation) getIntent().getExtras().getParcelable("keyInvitation");
            if (this.invitation != null && this.invitation.isValid()) {
                this.textviewInvitationBody.setText(getBaseText(this.invitation));
                if (!isSignedIn()) {
                    proceedToCreateAccountScreen();
                    return;
                } else if (UserConfig.getHomeId() == -1) {
                    showAcceptOnly();
                    return;
                } else if (UserConfig.getHomeId() == this.invitation.getHome().getId()) {
                    showSameHome();
                    return;
                } else {
                    showAcceptReject();
                    return;
                }
            } else if (isSignedIn()) {
                showInvalid();
                return;
            } else {
                proceedToCreateAccountScreen();
                return;
            }
        }
        Snitcher.start().toCrashlytics().logException(new IllegalArgumentException("No invitation found in the intent extras from InvitationReviewActivity"));
        finish();
    }

    private boolean isSignedIn() {
        return (UserConfig.getNickname() == null && (UserConfig.getUsername().isEmpty() || UserConfig.getPassword().isEmpty())) ? false : true;
    }

    private CharSequence getBaseText(Invitation invitation) {
        return Util.getText(this, C0676R.string.account_invitation_description, invitation.getInviter().getName(), invitation.getHome().getName(), invitation.getHome().getAddress().getAddressLine1());
    }

    public SpannableStringBuilder getInvitationMessage(CharSequence base, CharSequence message) {
        SpannableStringBuilder sameHome = new SpannableStringBuilder();
        sameHome.append(base);
        sameHome.append(" ");
        sameHome.append(message);
        return sameHome;
    }

    private void showAcceptOnly() {
        this.textviewInvitationBody.setText(getInvitationMessage(getBaseText(this.invitation), getText(C0676R.string.account_invitation_reactionNoHome_description)));
        this.buttonRejectInvitation.setVisibility(8);
        this.buttonJoinHome.setVisibility(0);
        this.buttonJoinHome.setText(C0676R.string.account_invitation_reactionNoHome_acceptButton);
        this.buttonSignOut.setVisibility(8);
        this.buttonProceed.setVisibility(8);
    }

    private void showSameHome() {
        this.textviewInvitationBody.setText(getInvitationMessage(getBaseText(this.invitation), Util.getText(this, C0676R.string.account_invitation_reactionSameHome_description, UserConfig.getUsername())));
        this.buttonJoinHome.setVisibility(8);
        this.buttonRejectInvitation.setVisibility(8);
        this.buttonSignOut.setVisibility(0);
        this.buttonSignOut.setText(C0676R.string.account_invitation_reactionSameHome_signOutButton);
        this.buttonProceed.setVisibility(0);
        this.buttonProceed.setText(C0676R.string.account_invitation_reactionSameHome_continueButton);
    }

    private void showAcceptReject() {
        this.textviewInvitationBody.setText(getInvitationMessage(getBaseText(this.invitation), Util.getText(this, C0676R.string.account_invitation_reactionDifferentHome_description, UserConfig.getHomeName())));
        this.buttonJoinHome.setVisibility(0);
        this.buttonJoinHome.setText(C0676R.string.account_invitation_reactionDifferentHome_acceptButton);
        this.buttonRejectInvitation.setVisibility(0);
        this.buttonRejectInvitation.setText(C0676R.string.account_invitation_reactionDifferentHome_rejectButton);
        this.buttonSignOut.setVisibility(8);
        this.buttonProceed.setVisibility(8);
    }

    private void showInvalid() {
        new Builder(this).setTitle(getString(C0676R.string.account_invitation_invalid_title)).setMessage(getText(C0676R.string.account_invitation_invalid_description)).setPositiveButton(getString(C0676R.string.account_invitation_invalid_okButton), new C08231()).setCancelable(false).create().show();
    }

    @OnClick({2131296386, 2131296388, 2131296394, 2131296387})
    public void onClick(View view) {
        disableButtons();
        switch (view.getId()) {
            case C0676R.id.button_join_home:
                joinHome();
                return;
            case C0676R.id.button_proceed_home:
                proceed();
                return;
            case C0676R.id.button_reject_invitation:
                rejectInvitation();
                return;
            case C0676R.id.button_sign_out:
                signOut();
                return;
            default:
                return;
        }
    }

    private void disableButtons() {
        this.buttonJoinHome.setEnabled(false);
        this.buttonProceed.setEnabled(false);
        this.buttonRejectInvitation.setEnabled(false);
        this.buttonSignOut.setEnabled(false);
    }

    private void joinHome() {
        RestServiceGenerator.getTadoRestService().acceptInvitation(this.invitation.getToken(), RestServiceGenerator.getCredentialsMap()).enqueue(new C08242());
    }

    private void rejectInvitation() {
        RestServiceGenerator.getTadoRestService().rejectInvitation(this.invitation.getToken(), RestServiceGenerator.getCredentialsMap()).enqueue(new C08253());
    }

    private void proceed() {
        InstallationProcessController.getInstallationProcessController().detectStatus(this, true);
    }

    private void signOut() {
        PresenceDetectionSettings.updatePresenceDetectionSetting(false, null);
        Util.clearUserData();
        proceedToCreateAccountScreen();
        AnalyticsHelper.trackEvent(getApplication(), Screen.SETTINGS, "SignOut");
    }

    private void proceedToCreateAccountScreen() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        intent.putExtra("keyInvitation", this.invitation);
        intent.setFlags(1409318912);
        overridePendingTransition(17432576, 17432577);
        startActivity(intent);
        finish();
    }

    protected void onDestroy() {
        this.unbinder.unbind();
        super.onDestroy();
    }
}
