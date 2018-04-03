package com.tado.android.settings.users;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.tado.C0676R;
import com.tado.android.alert_dialogs.CustomDialog;
import com.tado.android.app.TadoApplication;
import com.tado.android.login.LoginActivity;
import com.tado.android.rest.callback.RetryCallback;
import com.tado.android.rest.callback.presenters.SendingErrorAlertPresenter;
import com.tado.android.rest.model.Invitation;
import com.tado.android.rest.model.MobileDevice;
import com.tado.android.rest.model.User;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import retrofit2.Call;
import retrofit2.Response;

public class PeopleDetailsPreferenceActivityFragment extends Fragment {
    public static final String KEY_MOBILE_DEVICE_ID = "keyMobileDeviceId";
    public static final int MOBILE_DEVICE_CODE = 52;
    @BindView(2131297178)
    TextView description;
    @BindView(2131297179)
    TextView email;
    Invitation invitation;
    @BindView(2131297180)
    RecyclerView mobileDevicesRecyclerView;
    @BindView(2131297181)
    View mobileDevicesSeparatorLine;
    @BindView(2131297182)
    TextView mobileDevicesTitle;
    @BindView(2131297183)
    TextView name;
    @BindView(2131297184)
    Button resendButton;
    @BindView(2131297185)
    Button revokeButton;
    Unbinder unbinder;
    User user;
    int usersCount;

    class C11438 implements OnClickListener {
        C11438() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }

    public static PeopleDetailsPreferenceActivityFragment newInstance(User user, int usersCount) {
        PeopleDetailsPreferenceActivityFragment fragment = new PeopleDetailsPreferenceActivityFragment();
        Bundle args = new Bundle();
        args.putParcelable(PeopleDetailsPreferenceActivity.KEY_USER, user);
        args.putInt(PeopleDetailsPreferenceActivity.KEY_USERS_COUNT, usersCount);
        fragment.setArguments(args);
        return fragment;
    }

    public static PeopleDetailsPreferenceActivityFragment newInstance(Invitation invitation) {
        PeopleDetailsPreferenceActivityFragment fragment = new PeopleDetailsPreferenceActivityFragment();
        Bundle args = new Bundle();
        args.putParcelable("keyInvitation", invitation);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0676R.layout.fragment_people_details_preference, container, false);
        this.unbinder = ButterKnife.bind((Object) this, view);
        if (this.user != null) {
            initUserDetailsViews(this.user, this.usersCount);
        } else if (this.invitation != null) {
            initUserInvitationViews(this.invitation);
        }
        return view;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(PeopleDetailsPreferenceActivity.KEY_USER)) {
            this.user = (User) getArguments().getParcelable(PeopleDetailsPreferenceActivity.KEY_USER);
            this.usersCount = getArguments().getInt(PeopleDetailsPreferenceActivity.KEY_USERS_COUNT);
        } else if (getArguments() != null && getArguments().containsKey("keyInvitation")) {
            this.invitation = (Invitation) getArguments().getParcelable("keyInvitation");
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    private void setButtonsState(boolean enabled) {
        this.revokeButton.setEnabled(enabled);
        this.resendButton.setEnabled(enabled);
    }

    private void initUserInvitationViews(final Invitation invitation) {
        DateTime firstSentDateTime = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z").withZoneUTC().parseDateTime(invitation.getFirstSent()).withZone(DateTimeZone.getDefault());
        hideMobileDeviceLayout();
        this.resendButton.setVisibility(0);
        this.revokeButton.setText(C0676R.string.settings_users_invitations_revokeButtonLabel);
        this.name.setText(C0676R.string.settings_users_invitations_sentLabel);
        this.email.setText(invitation.getEmail());
        this.description.setText(String.format(getString(C0676R.string.settings_users_invitations_descriptionLabel), new Object[]{invitation.getEmail(), firstSentDateTime.toString(DateTimeFormat.mediumDate()), firstSentDateTime.toString(DateTimeFormat.shortTime())}));
        this.revokeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PeopleDetailsPreferenceActivityFragment.this.setButtonsState(false);
                RestServiceGenerator.getTadoRestService().cancelInvitation(UserConfig.getHomeId(), invitation.getToken(), RestServiceGenerator.getCredentialsMap()).enqueue(new RetryCallback<Void>(new SendingErrorAlertPresenter(PeopleDetailsPreferenceActivityFragment.this.getActivity())) {
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (PeopleDetailsPreferenceActivityFragment.this.isAdded()) {
                            if (response.isSuccessful()) {
                                PeopleDetailsPreferenceActivityFragment.this.getActivity().finish();
                            }
                            PeopleDetailsPreferenceActivityFragment.this.setButtonsState(true);
                        }
                        super.onResponse(call, response);
                    }

                    public void onFailure(Call<Void> call, Throwable t) {
                        if (PeopleDetailsPreferenceActivityFragment.this.isAdded()) {
                            PeopleDetailsPreferenceActivityFragment.this.setButtonsState(true);
                        }
                        super.onFailure(call, t);
                    }
                });
            }
        });
        this.resendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PeopleDetailsPreferenceActivityFragment.this.setButtonsState(false);
                RestServiceGenerator.getTadoRestService().resendInvitation(UserConfig.getHomeId(), invitation.getToken(), RestServiceGenerator.getCredentialsMap()).enqueue(new RetryCallback<Void>(new SendingErrorAlertPresenter(PeopleDetailsPreferenceActivityFragment.this.getActivity())) {
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (PeopleDetailsPreferenceActivityFragment.this.isAdded()) {
                            PeopleDetailsPreferenceActivityFragment.this.setButtonsState(true);
                        }
                        super.onResponse(call, response);
                    }

                    public void onFailure(Call<Void> call, Throwable t) {
                        if (PeopleDetailsPreferenceActivityFragment.this.isAdded()) {
                            PeopleDetailsPreferenceActivityFragment.this.setButtonsState(true);
                        }
                        super.onFailure(call, t);
                    }
                });
            }
        });
    }

    private void hideMobileDeviceLayout() {
        this.mobileDevicesRecyclerView.setVisibility(8);
        this.mobileDevicesSeparatorLine.setVisibility(8);
        this.mobileDevicesTitle.setVisibility(8);
    }

    private void initUserDetailsViews(final User user, int usersCount) {
        int i = 8;
        this.resendButton.setVisibility(8);
        this.revokeButton.setText(C0676R.string.settings_users_existingUser_revokeAccess_buttonLabel);
        this.name.setText(user.getName());
        this.email.setText(user.getEmail());
        this.description.setText(String.format(getString(C0676R.string.settings_users_existingUser_descriptionLabel), new Object[]{getNameOrEmail(user)}));
        this.revokeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PeopleDetailsPreferenceActivityFragment.this.revokeButton.setEnabled(false);
                PeopleDetailsPreferenceActivityFragment.this.showRevokingAccessDialog(PeopleDetailsPreferenceActivityFragment.this.isRevokingOwnUser(user));
            }
        });
        if (user.getMobileDevices().size() == 0) {
            hideMobileDeviceLayout();
        } else {
            prepareMobileDeviceListLayout(user);
        }
        Button button = this.revokeButton;
        if (usersCount != 1) {
            i = 0;
        }
        button.setVisibility(i);
    }

    private void prepareMobileDeviceListLayout(final User user) {
        this.mobileDevicesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.mobileDevicesRecyclerView.setAdapter(new MobileDevicesRecyclerViewAdapter(user.getMobileDevices(), new View.OnClickListener() {
            public void onClick(View v) {
                int position = PeopleDetailsPreferenceActivityFragment.this.mobileDevicesRecyclerView.getChildAdapterPosition(v);
                if (position == -1) {
                    Snitcher.start().toCrashlytics().log(6, PeopleDetailsPreferenceActivityFragment.class.getCanonicalName(), "Trying to access a position that does not exist %s", v.toString());
                    return;
                }
                Intent intent = new Intent(PeopleDetailsPreferenceActivityFragment.this.getActivity(), MobileDeviceDetailsPreferencesActivity.class);
                intent.putExtra(MobileDeviceDetailsPreferencesActivity.KEY_MOBILE_DEVICE, (Parcelable) user.getMobileDevices().get(position));
                intent.putExtra(MobileDeviceDetailsPreferencesActivity.KEY_NAME_OF_USER, user.getName());
                PeopleDetailsPreferenceActivityFragment.this.startActivityForResult(intent, 52);
            }
        }));
    }

    private void showRevokingAccessDialog(final boolean ownRevoke) {
        final CustomDialog dialog = new CustomDialog(getActivity());
        dialog.setTitle(getString(ownRevoke ? C0676R.string.settings_users_existingUser_revokeOwnAccess_title : C0676R.string.settings_users_existingUser_revokeUserAccess_title));
        dialog.setBodyText1(ownRevoke ? getString(C0676R.string.settings_users_existingUser_revokeOwnAccess_message) : getString(C0676R.string.settings_users_existingUser_revokeUserAccess_message, new Object[]{getNameOrEmail(this.user)}));
        dialog.setButton1Text(getString(ownRevoke ? C0676R.string.settings_users_existingUser_revokeOwnAccess_confirmButton : C0676R.string.settings_users_existingUser_revokeUserAccess_confirmButton));
        dialog.setCancelButtonText(getString(ownRevoke ? C0676R.string.settings_users_existingUser_revokeOwnAccess_cancelButton : C0676R.string.settings_users_existingUser_revokeUserAccess_cancelButton));
        dialog.setCancelButtonVisible(true);
        dialog.setButton1Listener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
                if (ownRevoke) {
                    PeopleDetailsPreferenceActivityFragment.this.revokeOwnAccess();
                } else {
                    PeopleDetailsPreferenceActivityFragment.this.revokeUserAccess();
                }
            }
        });
        dialog.setCancelButtonListener(new View.OnClickListener() {
            public void onClick(View v) {
                PeopleDetailsPreferenceActivityFragment.this.setButtonsState(true);
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }

    private void revokeUserAccess() {
        callRevokeUserAccess(false);
    }

    private void revokeOwnAccess() {
        callRevokeUserAccess(true);
    }

    private void callRevokeUserAccess(final boolean ownUser) {
        RestServiceGenerator.getTadoRestService().revokeAccess(UserConfig.getHomeId(), this.user.getUsername(), RestServiceGenerator.getCredentialsMap()).enqueue(new RetryCallback<Void>(new SendingErrorAlertPresenter(getActivity())) {
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (PeopleDetailsPreferenceActivityFragment.this.isAdded()) {
                    if (response.isSuccessful()) {
                        if (ownUser) {
                            TadoApplication.locationManager.stopTracking();
                            Util.clearUserData();
                            Intent intent = new Intent(PeopleDetailsPreferenceActivityFragment.this.getActivity(), LoginActivity.class);
                            intent.setFlags(268468224);
                            PeopleDetailsPreferenceActivityFragment.this.startActivity(intent);
                        } else {
                            PeopleDetailsPreferenceActivityFragment.this.getActivity().finish();
                        }
                    } else if (response.code() != 422 || this.serverError == null || this.serverError.getCode() == null || !this.serverError.getCode().equalsIgnoreCase("lastUserCannotBeRemoved")) {
                        PeopleDetailsPreferenceActivityFragment.this.revokeButton.setEnabled(true);
                    } else {
                        PeopleDetailsPreferenceActivityFragment.this.revokeButton.setVisibility(8);
                        PeopleDetailsPreferenceActivityFragment.this.showLastUserErrorDialog();
                        this.handled = true;
                    }
                    super.onResponse(call, response);
                }
            }

            public void onFailure(Call<Void> call, Throwable t) {
                if (PeopleDetailsPreferenceActivityFragment.this.isAdded()) {
                    PeopleDetailsPreferenceActivityFragment.this.revokeButton.setEnabled(true);
                }
                super.onFailure(call, t);
            }
        });
    }

    private void showLastUserErrorDialog() {
        new Builder(getActivity()).setTitle((int) C0676R.string.settings_users_existingUser_revokeAccess_errors_lastUserCannotBeRevoked_title).setMessage((int) C0676R.string.settings_users_existingUser_revokeAccess_errors_lastUserCannotBeRevoked_message).setPositiveButton((int) C0676R.string.settings_users_existingUser_revokeAccess_errors_lastUserCannotBeRevoked_confirmButton, new C11438()).setCancelable(false).create().show();
    }

    private boolean isRevokingOwnUser(User user) {
        for (MobileDevice mobileDevice : user.getMobileDevices()) {
            if (mobileDevice.getId() == UserConfig.getMobileDeviceId()) {
                return true;
            }
        }
        return false;
    }

    private String getNameOrEmail(User user) {
        return user.getName() != null ? user.getName() : user.getEmail();
    }

    private void removeMobileDevice(int deviceId) {
        if (this.mobileDevicesRecyclerView != null) {
            ((MobileDevicesRecyclerViewAdapter) this.mobileDevicesRecyclerView.getAdapter()).removeMobileDevice(deviceId);
        }
    }

    private void updateMobileDevice(MobileDevice mobileDevice) {
        if (mobileDevice != null && this.mobileDevicesRecyclerView != null) {
            ((MobileDevicesRecyclerViewAdapter) this.mobileDevicesRecyclerView.getAdapter()).updateMobileDeviceGeotracking(mobileDevice.getId(), mobileDevice.getSettings().isGeoTrackingEnabled());
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != -1 || requestCode != 52) {
            return;
        }
        if (data.hasExtra(KEY_MOBILE_DEVICE_ID)) {
            removeMobileDevice(data.getIntExtra(KEY_MOBILE_DEVICE_ID, -1));
        } else if (data.hasExtra(MobileDeviceDetailsPreferencesActivity.KEY_MOBILE_DEVICE)) {
            updateMobileDevice((MobileDevice) data.getParcelableExtra(MobileDeviceDetailsPreferencesActivity.KEY_MOBILE_DEVICE));
        }
    }
}
