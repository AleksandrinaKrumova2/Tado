package com.tado.android.login;

import android.app.Activity;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tado.android.debug.DebugAccount;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.mvp.common.BasePresenter;
import com.tado.android.mvp.common.BaseView;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.Invitation;
import com.tado.android.rest.model.User;
import com.tado.android.rest.model.UserHome;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.Constants;
import com.tado.android.utils.FileUtils;
import com.tado.android.utils.GeolocationLogger;
import com.tado.android.utils.Server;
import com.tado.android.utils.SimpleLogEntry;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Response;

public class LoginPresenter implements BasePresenter<LoginView> {
    List<DebugAccount> accounts = new ArrayList();
    private String connectAddress;
    private Invitation invitation;
    private LoginView view;

    public interface LoginView extends BaseView {
        void dismissProgressDialog();

        void prefillEmail(String str);

        void showDialogIncorrectCredentials();

        void showDialogNoInternet();

        void showInvitationNote(String str, String str2);

        void showInvitationScreen(Invitation invitation);

        void showLogSelection();

        void showProgressDialog();

        void showServerSelection();
    }

    class C10311 extends TypeToken<List<DebugAccount>> {
        C10311() {
        }
    }

    LoginPresenter(@NonNull LoginView view) {
        bindView(view);
        setConnectAddress(Server.getProduction());
    }

    public void setConnectAddress(String connectAddress) {
        RestServiceGenerator.destroyClient();
        this.connectAddress = connectAddress;
        UserConfig.setServerAddress(this.connectAddress);
    }

    public String getConnectAddress() {
        return this.connectAddress;
    }

    public List<DebugAccount> getDebugAccounts(AssetManager assetManager) {
        try {
            String json = FileUtils.loadJSONFromAsset(assetManager, "accounts.json");
            if (json != null) {
                this.accounts = (List) new Gson().fromJson(json, new C10311().getType());
            }
            return this.accounts;
        } catch (Exception e) {
            return new ArrayList();
        }
    }

    public void signIn(final String username, final String password, final Activity callingActivity) {
        SimpleLogEntry simple1 = new SimpleLogEntry();
        String str = "username = %s password = %s server = %s";
        Object[] objArr = new Object[3];
        objArr[0] = username;
        objArr[1] = password.isEmpty() ? "empty" : "******";
        objArr[2] = UserConfig.getServerAddress();
        simple1.setMessage(String.format(str, objArr));
        GeolocationLogger.logToFile(simple1);
        this.view.showProgressDialog();
        Map<String, String> credentials = new HashMap(2);
        credentials.put(Constants.KEY_EXTRA_USERNAME, username);
        credentials.put(Constants.KEY_EXTRA_PASSWORD, password);
        RestServiceGenerator.getTadoRestService().getMe(credentials).enqueue(new TadoCallback<User>() {
            public void onResponse(Call<User> call, Response<User> response) {
                super.onResponse(call, response);
                if (LoginPresenter.this.view != null) {
                    LoginPresenter.this.view.dismissProgressDialog();
                }
                if (response.isSuccessful()) {
                    User user = (User) response.body();
                    UserConfig.setUsername(username);
                    UserConfig.setPassword(password);
                    if (!user.getHomes().isEmpty()) {
                        UserHome userHome = (UserHome) user.getHomes().get(0);
                        UserConfig.setHomeId(userHome.getId());
                        UserConfig.setHomeName(userHome.getName());
                    }
                    if (LoginPresenter.this.invitation == null || !LoginPresenter.this.invitation.isValid()) {
                        InstallationProcessController.getInstallationProcessController().detectStatus(callingActivity);
                    } else {
                        LoginPresenter.this.view.showInvitationScreen(LoginPresenter.this.invitation);
                    }
                } else if (Util.isNetworkAvailable()) {
                    if (response.code() == 401) {
                        if (LoginPresenter.this.view != null) {
                            LoginPresenter.this.view.showDialogIncorrectCredentials();
                        }
                    } else if (response.code() == 410) {
                        super.onResponse(call, response);
                    } else {
                        InstallationProcessController.showConnectionErrorRetrofit(callingActivity, call, this);
                    }
                } else if (LoginPresenter.this.view != null) {
                    LoginPresenter.this.view.showDialogNoInternet();
                }
            }

            public void onFailure(Call<User> call, Throwable t) {
                super.onFailure(call, t);
                if (LoginPresenter.this.view != null) {
                    LoginPresenter.this.view.dismissProgressDialog();
                    if (!Util.isNetworkAvailable()) {
                        LoginPresenter.this.view.showDialogNoInternet();
                    }
                }
            }
        });
    }

    public void bindView(LoginView view) {
        this.view = view;
    }

    public void unbindView() {
        this.view = null;
    }

    public void handleInvitation(Invitation invitation) {
        this.invitation = invitation;
        if (invitation != null && invitation.getHome() != null && invitation.getHome().getAddress() != null) {
            this.view.showInvitationNote(invitation.getHome().getName(), invitation.getHome().getAddress().getAddressLine1());
            this.view.prefillEmail(invitation.getEmail());
        }
    }
}
