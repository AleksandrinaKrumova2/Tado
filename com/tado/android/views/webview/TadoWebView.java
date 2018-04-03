package com.tado.android.views.webview;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.tado.C0676R;
import com.tado.android.utils.Snitcher;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ&\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00012\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0002J\u0006\u0010\u0016\u001a\u00020\u0010J\u0014\u0010\u0017\u001a\u00020\u00102\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007J\u0012\u0010\u0018\u001a\u00020\u00102\b\u0010\u0019\u001a\u0004\u0018\u00010\fH\u0016J\u0012\u0010\u001a\u001a\u00020\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0002J\b\u0010\u001b\u001a\u00020\u0010H\u0016J\u0006\u0010\u001c\u001a\u00020\u0010J\u0006\u0010\u001d\u001a\u00020\u0010R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/tado/android/views/webview/TadoWebView;", "Landroid/webkit/WebView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "loadedUrl", "", "mProgressDialog", "Landroid/app/ProgressDialog;", "checkError", "", "view", "request", "Landroid/webkit/WebResourceRequest;", "listener", "Lcom/tado/android/views/webview/TadoWebViewListener;", "dismissProgressDialog", "initWebView", "loadUrl", "url", "onError", "reload", "reset", "showProgressDialog", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: TadoWebView.kt */
public final class TadoWebView extends WebView {
    private HashMap _$_findViewCache;
    private String loadedUrl;
    private ProgressDialog mProgressDialog;

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        view = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), view);
        return view;
    }

    public TadoWebView(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super(context);
    }

    public TadoWebView(@NotNull Context context, @NotNull AttributeSet attrs) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
        super(context, attrs);
    }

    public TadoWebView(@NotNull Context context, @NotNull AttributeSet attrs, int defStyleAttr) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public static /* bridge */ /* synthetic */ void initWebView$default(TadoWebView tadoWebView, TadoWebViewListener tadoWebViewListener, int i, Object obj) {
        tadoWebView.initWebView((i & 1) != 0 ? (TadoWebViewListener) null : tadoWebViewListener);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public final void initWebView(@Nullable TadoWebViewListener listener) {
        if (listener != null) {
            TadoWebViewListener it = listener;
            listener.setTadoWebView(this);
        }
        WebSettings settings = getSettings();
        Intrinsics.checkExpressionValueIsNotNull(settings, "settings");
        settings.setJavaScriptEnabled(true);
        getSettings().setSupportMultipleWindows(true);
        setWebChromeClient(new TadoWebView$initWebView$2());
        setWebViewClient(new TadoWebView$initWebView$3(this, listener));
    }

    public final void reset() {
        super.loadUrl("about:blank");
        invalidate();
    }

    public void loadUrl(@Nullable String url) {
        this.loadedUrl = url;
        Snitcher.start().log(3, "webView", url);
        super.loadUrl(url);
    }

    public void reload() {
        loadUrl(this.loadedUrl);
    }

    private final void checkError(WebView view, WebResourceRequest request, TadoWebViewListener listener) {
        if (request != null) {
            if (!Intrinsics.areEqual(Uri.parse(view != null ? view.getUrl() : null), request.getUrl())) {
                return;
            }
        }
        onError(listener);
    }

    private final void onError(TadoWebViewListener listener) {
        if (listener != null) {
            listener.onFailure();
        }
    }

    public final void showProgressDialog() {
        if (this.mProgressDialog != null) {
            dismissProgressDialog();
        }
        this.mProgressDialog = new ProgressDialog(getContext());
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog != null) {
            progressDialog.setMessage(getContext().getString(C0676R.string.components_loadingIndicator_initialLoadingLabel));
        }
        ProgressDialog progressDialog2 = this.mProgressDialog;
        if (progressDialog2 != null) {
            progressDialog2.setCancelable(false);
        }
        progressDialog2 = this.mProgressDialog;
        if (progressDialog2 != null) {
            progressDialog2.show();
        }
    }

    public final void dismissProgressDialog() {
        if (this.mProgressDialog != null) {
            ProgressDialog progressDialog = this.mProgressDialog;
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            this.mProgressDialog = (ProgressDialog) null;
        }
    }
}
