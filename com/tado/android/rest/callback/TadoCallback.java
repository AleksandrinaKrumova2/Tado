package com.tado.android.rest.callback;

import android.content.Context;
import android.content.Intent;
import com.google.gson.GsonBuilder;
import com.tado.android.app.TadoApplication;
import com.tado.android.controllers.NavigationController;
import com.tado.android.entities.ServerError;
import com.tado.android.login.LoginActivity;
import com.tado.android.rest.callback.presenters.PresenterDelegate;
import com.tado.android.rest.model.ResponseError;
import com.tado.android.settings.interfaces.GenericCallbackListener;
import com.tado.android.utils.GeolocationLogger;
import com.tado.android.utils.RestErrorLogEntry;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import java.lang.ref.WeakReference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class TadoCallback<T> implements Callback<T> {
    protected boolean handled;
    private GenericCallbackListener<T> mListener;
    private WeakReference<PresenterDelegate> mPresenterDelegate;
    private RetryListener mRetryListener;
    protected ServerError serverError;

    public TadoCallback() {
        this(null, null);
    }

    public TadoCallback(GenericCallbackListener<T> listener, PresenterDelegate presenterDelegate) {
        this.serverError = null;
        this.handled = false;
        this.mRetryListener = null;
        this.mListener = listener;
        this.mPresenterDelegate = new WeakReference(presenterDelegate);
    }

    public TadoCallback(PresenterDelegate presenterDelegate) {
        this(null, presenterDelegate);
    }

    public void onResponse(Call<T> call, Response<T> response) {
        if (!response.isSuccessful()) {
            try {
                String url = response.raw().request().url().url().toString().replaceAll("password=([^&]+)", "password=*******");
                ResponseError responseError = (ResponseError) new GsonBuilder().create().fromJson(response.errorBody().string(), ResponseError.class);
                if (responseError == null || responseError.errors == null || responseError.errors.length <= 0) {
                    Snitcher.start().toCrashlytics().toLogger().log(6, "TadoCallback", url + " " + response.code(), new Object[0]);
                    addRestLog(response, this.serverError);
                    if (response.code() == 401) {
                        Snitcher.start().toCrashlytics().toLogger().log(5, "TadoCallback", "401 Logout from TadoCallback", new Object[0]);
                        logoutUser();
                    } else if (response.code() != 410) {
                        Snitcher.start().toCrashlytics().toLogger().log(5, "TadoCallback", "410 force update screen from TadoCallback", new Object[0]);
                        NavigationController.showForceUpdateScreen(TadoApplication.getTadoAppContext());
                    } else if (response.code() != 500 && this.serverError != null && this.serverError.getCode().equalsIgnoreCase("notSupportedInDemoMode") && this.mPresenterDelegate != null && this.mPresenterDelegate.get() != null) {
                        this.handled = true;
                        ((PresenterDelegate) this.mPresenterDelegate.get()).onNotSupportedInDemoMode();
                    } else if (response.code() >= 500) {
                        Snitcher.start().toCrashlytics().toLogger().log(6, "TadoCallback", "%d Server error", Integer.valueOf(response.code()));
                    }
                    if (this.mListener != null) {
                        this.mListener.onFailure();
                    }
                }
                this.serverError = responseError.errors[0];
                Snitcher.start().toCrashlytics().toLogger().log(6, "TadoCallback", "%s: %s", this.serverError.getCode(), this.serverError.getTitle());
                addRestLog(response, this.serverError);
                if (response.code() == 401) {
                    Snitcher.start().toCrashlytics().toLogger().log(5, "TadoCallback", "401 Logout from TadoCallback", new Object[0]);
                    logoutUser();
                } else if (response.code() != 410) {
                    if (response.code() != 500) {
                    }
                    if (response.code() >= 500) {
                        Snitcher.start().toCrashlytics().toLogger().log(6, "TadoCallback", "%d Server error", Integer.valueOf(response.code()));
                    }
                } else {
                    Snitcher.start().toCrashlytics().toLogger().log(5, "TadoCallback", "410 force update screen from TadoCallback", new Object[0]);
                    NavigationController.showForceUpdateScreen(TadoApplication.getTadoAppContext());
                }
                if (this.mListener != null) {
                    this.mListener.onFailure();
                }
            } catch (Exception e) {
                Snitcher.start().toCrashlytics().toLogger().logException("TadoCallback", e);
            }
        } else if (this.mListener != null) {
            this.mListener.onSuccess(response.body());
        }
    }

    public void onFailure(Call<T> call, Throwable t) {
        Snitcher.start().toCrashlytics().toLogger().log(6, "TadoCallback", t.getStackTrace().toString() + " " + t.getLocalizedMessage(), new Object[0]);
        RestErrorLogEntry logEntry = new RestErrorLogEntry();
        logEntry.setFailure(RestErrorLogEntry.FAILURE);
        logEntry.setUrl(call.request().url().toString());
        logEntry.setTimestamp(System.currentTimeMillis() + "");
        GeolocationLogger.logToFile(logEntry);
        if (this.mListener != null) {
            this.mListener.onFailure();
        }
    }

    public void setRetryListener(RetryListener retryListener) {
        this.mRetryListener = retryListener;
    }

    public void retry(Call<T> call) {
        this.handled = false;
        if (this.mRetryListener != null) {
            this.mRetryListener.retry();
        } else {
            call.clone().enqueue(this);
        }
    }

    private void addRestLog(Response<T> response, ServerError error) {
        RestErrorLogEntry logEntry = new RestErrorLogEntry();
        logEntry.setFailure(RestErrorLogEntry.HAS_RESPONSE);
        logEntry.setTimestamp(System.currentTimeMillis() + "");
        logEntry.setResponseCode(response.code());
        logEntry.setResponseMessage(response.message() + " " + response.raw().message());
        logEntry.setError(error);
        logEntry.setUrl(response.raw().request().url().toString());
        try {
            logEntry.setBody(response.raw().body().string());
        } catch (Exception e) {
            logEntry.setBody("no body - exception");
        }
        GeolocationLogger.logToFile(logEntry);
    }

    private void logoutUser() {
        if (!UserConfig.getUsername().isEmpty()) {
            TadoApplication.locationManager.stopTracking();
            Util.clearUserData();
            startActivity(LoginActivity.class);
        }
    }

    private void startActivity(Class clazz) {
        Context context = TadoApplication.getTadoAppContext();
        Intent intent = new Intent(context, clazz);
        intent.setFlags(268468224);
        context.startActivity(intent);
    }
}
