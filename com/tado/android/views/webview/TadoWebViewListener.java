package com.tado.android.views.webview;

import android.content.Context;
import android.webkit.WebView;
import com.tado.C0676R;
import com.tado.android.dialogs.AlertDialogs;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\bH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016J\u000e\u0010\f\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006J\u0012\u0010\r\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/tado/android/views/webview/TadoWebViewListener;", "", "()V", "errorAlertShown", "", "webView", "Lcom/tado/android/views/webview/TadoWebView;", "invalidateWebView", "", "onFailure", "onLeaveWebView", "onSuccess", "setTadoWebView", "showLoadingError", "view", "Landroid/webkit/WebView;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: TadoWebViewListener.kt */
public class TadoWebViewListener {
    private boolean errorAlertShown;
    private TadoWebView webView;

    public final void setTadoWebView(@NotNull TadoWebView webView) {
        Intrinsics.checkParameterIsNotNull(webView, "webView");
        this.webView = webView;
    }

    public void onLeaveWebView() {
    }

    public void onSuccess() {
    }

    public void onFailure() {
        invalidateWebView();
        showLoadingError(this.webView);
    }

    private final void showLoadingError(WebView view) {
        Context context = null;
        synchronized (this) {
            if (this.errorAlertShown) {
                return;
            }
            String string;
            Context context2;
            String string2;
            Context context3;
            String string3;
            Context context4;
            String string4;
            Unit unit;
            if (view != null) {
                Context context5 = view.getContext();
                if (context5 != null) {
                    string = context5.getString(C0676R.string.errors_noInternetConnection_title);
                    if (view != null) {
                        context2 = view.getContext();
                        if (context2 != null) {
                            string2 = context2.getString(C0676R.string.errors_noInternetConnection_message);
                            if (view != null) {
                                context3 = view.getContext();
                                if (context3 != null) {
                                    string3 = context3.getString(C0676R.string.errors_noInternetConnection_confirmButton);
                                    if (view != null) {
                                        context4 = view.getContext();
                                        if (context4 != null) {
                                            string4 = context4.getString(C0676R.string.errors_noInternetConnection_cancelButton);
                                            if (view != null) {
                                                context = view.getContext();
                                            }
                                            AlertDialogs.showCancelRetryAlert(string, string2, string3, string4, context, new C1301xfdf03813(this, view));
                                            this.errorAlertShown = true;
                                            unit = Unit.INSTANCE;
                                        }
                                    }
                                    string4 = null;
                                    if (view != null) {
                                        context = view.getContext();
                                    }
                                    AlertDialogs.showCancelRetryAlert(string, string2, string3, string4, context, new C1301xfdf03813(this, view));
                                    this.errorAlertShown = true;
                                    unit = Unit.INSTANCE;
                                }
                            }
                            string3 = null;
                            if (view != null) {
                                context4 = view.getContext();
                                if (context4 != null) {
                                    string4 = context4.getString(C0676R.string.errors_noInternetConnection_cancelButton);
                                    if (view != null) {
                                        context = view.getContext();
                                    }
                                    AlertDialogs.showCancelRetryAlert(string, string2, string3, string4, context, new C1301xfdf03813(this, view));
                                    this.errorAlertShown = true;
                                    unit = Unit.INSTANCE;
                                }
                            }
                            string4 = null;
                            if (view != null) {
                                context = view.getContext();
                            }
                            AlertDialogs.showCancelRetryAlert(string, string2, string3, string4, context, new C1301xfdf03813(this, view));
                            this.errorAlertShown = true;
                            unit = Unit.INSTANCE;
                        }
                    }
                    string2 = null;
                    if (view != null) {
                        context3 = view.getContext();
                        if (context3 != null) {
                            string3 = context3.getString(C0676R.string.errors_noInternetConnection_confirmButton);
                            if (view != null) {
                                context4 = view.getContext();
                                if (context4 != null) {
                                    string4 = context4.getString(C0676R.string.errors_noInternetConnection_cancelButton);
                                    if (view != null) {
                                        context = view.getContext();
                                    }
                                    AlertDialogs.showCancelRetryAlert(string, string2, string3, string4, context, new C1301xfdf03813(this, view));
                                    this.errorAlertShown = true;
                                    unit = Unit.INSTANCE;
                                }
                            }
                            string4 = null;
                            if (view != null) {
                                context = view.getContext();
                            }
                            AlertDialogs.showCancelRetryAlert(string, string2, string3, string4, context, new C1301xfdf03813(this, view));
                            this.errorAlertShown = true;
                            unit = Unit.INSTANCE;
                        }
                    }
                    string3 = null;
                    if (view != null) {
                        context4 = view.getContext();
                        if (context4 != null) {
                            string4 = context4.getString(C0676R.string.errors_noInternetConnection_cancelButton);
                            if (view != null) {
                                context = view.getContext();
                            }
                            AlertDialogs.showCancelRetryAlert(string, string2, string3, string4, context, new C1301xfdf03813(this, view));
                            this.errorAlertShown = true;
                            unit = Unit.INSTANCE;
                        }
                    }
                    string4 = null;
                    if (view != null) {
                        context = view.getContext();
                    }
                    AlertDialogs.showCancelRetryAlert(string, string2, string3, string4, context, new C1301xfdf03813(this, view));
                    this.errorAlertShown = true;
                    unit = Unit.INSTANCE;
                }
            }
            string = null;
            if (view != null) {
                context2 = view.getContext();
                if (context2 != null) {
                    string2 = context2.getString(C0676R.string.errors_noInternetConnection_message);
                    if (view != null) {
                        context3 = view.getContext();
                        if (context3 != null) {
                            string3 = context3.getString(C0676R.string.errors_noInternetConnection_confirmButton);
                            if (view != null) {
                                context4 = view.getContext();
                                if (context4 != null) {
                                    string4 = context4.getString(C0676R.string.errors_noInternetConnection_cancelButton);
                                    if (view != null) {
                                        context = view.getContext();
                                    }
                                    AlertDialogs.showCancelRetryAlert(string, string2, string3, string4, context, new C1301xfdf03813(this, view));
                                    this.errorAlertShown = true;
                                    unit = Unit.INSTANCE;
                                }
                            }
                            string4 = null;
                            if (view != null) {
                                context = view.getContext();
                            }
                            AlertDialogs.showCancelRetryAlert(string, string2, string3, string4, context, new C1301xfdf03813(this, view));
                            this.errorAlertShown = true;
                            unit = Unit.INSTANCE;
                        }
                    }
                    string3 = null;
                    if (view != null) {
                        context4 = view.getContext();
                        if (context4 != null) {
                            string4 = context4.getString(C0676R.string.errors_noInternetConnection_cancelButton);
                            if (view != null) {
                                context = view.getContext();
                            }
                            AlertDialogs.showCancelRetryAlert(string, string2, string3, string4, context, new C1301xfdf03813(this, view));
                            this.errorAlertShown = true;
                            unit = Unit.INSTANCE;
                        }
                    }
                    string4 = null;
                    if (view != null) {
                        context = view.getContext();
                    }
                    AlertDialogs.showCancelRetryAlert(string, string2, string3, string4, context, new C1301xfdf03813(this, view));
                    this.errorAlertShown = true;
                    unit = Unit.INSTANCE;
                }
            }
            string2 = null;
            if (view != null) {
                context3 = view.getContext();
                if (context3 != null) {
                    string3 = context3.getString(C0676R.string.errors_noInternetConnection_confirmButton);
                    if (view != null) {
                        context4 = view.getContext();
                        if (context4 != null) {
                            string4 = context4.getString(C0676R.string.errors_noInternetConnection_cancelButton);
                            if (view != null) {
                                context = view.getContext();
                            }
                            AlertDialogs.showCancelRetryAlert(string, string2, string3, string4, context, new C1301xfdf03813(this, view));
                            this.errorAlertShown = true;
                            unit = Unit.INSTANCE;
                        }
                    }
                    string4 = null;
                    if (view != null) {
                        context = view.getContext();
                    }
                    AlertDialogs.showCancelRetryAlert(string, string2, string3, string4, context, new C1301xfdf03813(this, view));
                    this.errorAlertShown = true;
                    unit = Unit.INSTANCE;
                }
            }
            string3 = null;
            if (view != null) {
                context4 = view.getContext();
                if (context4 != null) {
                    string4 = context4.getString(C0676R.string.errors_noInternetConnection_cancelButton);
                    if (view != null) {
                        context = view.getContext();
                    }
                    AlertDialogs.showCancelRetryAlert(string, string2, string3, string4, context, new C1301xfdf03813(this, view));
                    this.errorAlertShown = true;
                    unit = Unit.INSTANCE;
                }
            }
            string4 = null;
            if (view != null) {
                context = view.getContext();
            }
            AlertDialogs.showCancelRetryAlert(string, string2, string3, string4, context, new C1301xfdf03813(this, view));
            this.errorAlertShown = true;
            unit = Unit.INSTANCE;
        }
    }

    private final void invalidateWebView() {
        if (this.webView != null) {
            TadoWebView tadoWebView = this.webView;
            if (tadoWebView != null) {
                tadoWebView.reset();
            }
        }
    }
}
