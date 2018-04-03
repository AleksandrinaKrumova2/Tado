package com.tado.android.settings.users;

import android.app.AlertDialog;
import android.content.Intent;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.text.Editable;
import android.text.TextWatcher;
import com.tado.C0676R;
import com.tado.android.rest.callback.RetryCallback;
import com.tado.android.rest.callback.presenters.GeneralErrorSnackbarPresenter;
import com.tado.android.rest.callback.presenters.SendingErrorAlertPresenter;
import com.tado.android.rest.model.Invitation;
import com.tado.android.rest.model.User;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.settings.InviteUserEditTextPreference;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import retrofit2.Call;
import retrofit2.Response;

public class PeoplePreferenceFragment extends PreferenceFragment {
    private Set<String> invitedUsersSet = new HashSet();
    private Set<String> registeredUsersSet = new HashSet();

    public static PeoplePreferenceFragment newInstance() {
        return new PeoplePreferenceFragment();
    }

    public void onResume() {
        super.onResume();
        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(getActivity());
        createInviteUserPreference(screen);
        createInviteUserPreferenceInfo(screen);
        getUsers(screen);
        setPreferenceScreen(screen);
    }

    private void createInviteUserPreference(final PreferenceScreen screen) {
        final InviteUserEditTextPreference editTextPreference = new InviteUserEditTextPreference(getActivity());
        editTextPreference.setIcon(C0676R.drawable.ic_plus);
        editTextPreference.setTitle(C0676R.string.settings_users_inviteButton);
        editTextPreference.setDialogTitle(C0676R.string.settings_users_invitations_title);
        editTextPreference.setPositiveButtonText(C0676R.string.settings_users_invitations_inviteButtonLabel);
        editTextPreference.setPersistent(false);
        editTextPreference.getEditText().setHint(C0676R.string.settings_users_invitations_emailPlaceholder);
        editTextPreference.getEditText().addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                String email = s.toString();
                if (editTextPreference.getDialog() != null) {
                    ((AlertDialog) editTextPreference.getDialog()).getButton(-1).setEnabled(false);
                }
                if (!Util.isValidEmail(email) && s.length() != 0) {
                    editTextPreference.getEditText().setError(PeoplePreferenceFragment.this.getString(C0676R.string.settings_users_invitations_errors_emailFieldInvalidErrorLabel));
                } else if (PeoplePreferenceFragment.this.registeredUsersSet.contains(email)) {
                    editTextPreference.getEditText().setError(PeoplePreferenceFragment.this.getString(C0676R.string.settings_users_invitations_errors_userAlreadyInHome));
                } else if (PeoplePreferenceFragment.this.invitedUsersSet.contains(email)) {
                    editTextPreference.getEditText().setError(PeoplePreferenceFragment.this.getString(C0676R.string.settings_users_invitations_errors_userAlreadyInvited));
                } else if (!email.isEmpty()) {
                    editTextPreference.getEditText().setError(null);
                    if (editTextPreference.getDialog() != null) {
                        ((AlertDialog) editTextPreference.getDialog()).getButton(-1).setEnabled(true);
                    }
                }
            }
        });
        editTextPreference.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                RestServiceGenerator.getTadoRestService().inviteUser(UserConfig.getHomeId(), new Invitation((String) newValue), RestServiceGenerator.getCredentialsMap()).enqueue(new RetryCallback<Invitation>(new SendingErrorAlertPresenter(PeoplePreferenceFragment.this.getActivity())) {
                    public void onResponse(Call<Invitation> call, Response<Invitation> response) {
                        if (PeoplePreferenceFragment.this.isAdded() && response.isSuccessful()) {
                            editTextPreference.setDefaultValue("");
                            screen.addPreference(PeoplePreferenceFragment.this.createInvitationUserPreference((Invitation) response.body()));
                            PeoplePreferenceFragment.this.invitedUsersSet.add(((Invitation) response.body()).getEmail());
                        }
                        super.onResponse(call, response);
                    }
                });
                return true;
            }
        });
        screen.addPreference(editTextPreference);
    }

    private void createInviteUserPreferenceInfo(PreferenceScreen screen) {
        Preference preference = new Preference(getActivity());
        preference.setSummary(C0676R.string.settings_users_descriptionLabel);
        preference.setPersistent(false);
        preference.setSelectable(false);
        screen.addPreference(preference);
    }

    private void getUsers(final PreferenceScreen screen) {
        RestServiceGenerator.getTadoRestService().listUsers(UserConfig.getHomeId(), RestServiceGenerator.getCredentialsMap()).enqueue(new RetryCallback<List<User>>(new GeneralErrorSnackbarPresenter(getView())) {
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && PeoplePreferenceFragment.this.isAdded()) {
                    PeoplePreferenceFragment.this.processUsers((List) response.body(), screen);
                    PeoplePreferenceFragment.this.getInvitations(screen);
                }
                super.onResponse(call, response);
            }
        });
    }

    private void processUsers(List<User> users, PreferenceScreen screen) {
        this.registeredUsersSet.clear();
        for (User user : users) {
            screen.addPreference(createUserPreference(user, users.size()));
            this.registeredUsersSet.add(user.getEmail());
        }
    }

    private Preference createUserPreference(User user, int usersCount) {
        Preference userPreference = new Preference(getActivity());
        userPreference.setLayoutResource(C0676R.layout.preference_user);
        userPreference.setTitle(user.getName());
        userPreference.setSummary(user.getEmail());
        userPreference.setIcon(C0676R.drawable.ic_user);
        userPreference.setPersistent(false);
        userPreference.setKey(user.getName());
        Intent intent = new Intent(getActivity(), PeopleDetailsPreferenceActivity.class);
        intent.putExtra(PeopleDetailsPreferenceActivity.KEY_USER, user);
        intent.putExtra(PeopleDetailsPreferenceActivity.KEY_USERS_COUNT, usersCount);
        userPreference.setIntent(intent);
        return userPreference;
    }

    private void getInvitations(final PreferenceScreen screen) {
        RestServiceGenerator.getTadoRestService().listInvitations(UserConfig.getHomeId(), RestServiceGenerator.getCredentialsMap()).enqueue(new RetryCallback<List<Invitation>>(new GeneralErrorSnackbarPresenter(getView())) {
            public void onResponse(Call<List<Invitation>> call, Response<List<Invitation>> response) {
                if (response.isSuccessful() && PeoplePreferenceFragment.this.isAdded()) {
                    PeoplePreferenceFragment.this.processInvitations((List) response.body(), screen);
                }
                super.onResponse(call, response);
            }
        });
    }

    private void processInvitations(List<Invitation> invitations, PreferenceScreen screen) {
        this.invitedUsersSet.clear();
        for (Invitation invitation : invitations) {
            screen.addPreference(createInvitationUserPreference(invitation));
            this.invitedUsersSet.add(invitation.getEmail());
        }
    }

    private Preference createInvitationUserPreference(Invitation invitation) {
        Preference invitationPreference = new Preference(getActivity());
        invitationPreference.setLayoutResource(C0676R.layout.preference_user);
        invitationPreference.setTitle(invitation.getEmail());
        invitationPreference.setSummary(C0676R.string.settings_users_invitations_sentLabel);
        invitationPreference.setIcon(C0676R.drawable.ic_user);
        invitationPreference.getIcon().mutate().setAlpha(77);
        invitationPreference.setPersistent(false);
        Intent intent = new Intent(getActivity(), PeopleDetailsPreferenceActivity.class);
        intent.putExtra("keyInvitation", invitation);
        invitationPreference.setIntent(intent);
        return invitationPreference;
    }
}
