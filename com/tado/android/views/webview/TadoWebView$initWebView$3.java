package com.tado.android.views.webview;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.analytics.ecommerce.Promotion;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000Q\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J&\u0010\t\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J&\u0010\f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0017J.\u0010\f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\bH\u0017J&\u0010\u0015\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0018\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eH\u0017J\u0018\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\u001d"}, d2 = {"com/tado/android/views/webview/TadoWebView$initWebView$3", "Landroid/webkit/WebViewClient;", "(Lcom/tado/android/views/webview/TadoWebView;Lcom/tado/android/views/webview/TadoWebViewListener;)V", "onPageFinished", "", "view", "Landroid/webkit/WebView;", "url", "", "onPageStarted", "favicon", "Landroid/graphics/Bitmap;", "onReceivedError", "request", "Landroid/webkit/WebResourceRequest;", "error", "Landroid/webkit/WebResourceError;", "errorCode", "", "description", "failingUrl", "onReceivedHttpError", "errorResponse", "Landroid/webkit/WebResourceResponse;", "shouldOverrideUrl", "", "uri", "Landroid/net/Uri;", "shouldOverrideUrlLoading", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: TadoWebView.kt */
public final class TadoWebView$initWebView$3 extends WebViewClient {
    final /* synthetic */ TadoWebViewListener $listener;
    final /* synthetic */ TadoWebView this$0;

    TadoWebView$initWebView$3(TadoWebView $outer, TadoWebViewListener $captured_local_variable$1) {
        this.this$0 = $outer;
        this.$listener = $captured_local_variable$1;
    }

    public void onReceivedError(@Nullable WebView view, int errorCode, @Nullable String description, @Nullable String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        this.this$0.checkError(view, null, this.$listener);
    }

    @TargetApi(23)
    public void onReceivedError(@Nullable WebView view, @Nullable WebResourceRequest request, @Nullable WebResourceError error) {
        super.onReceivedError(view, request, error);
        this.this$0.checkError(view, request, this.$listener);
    }

    public void onReceivedHttpError(@Nullable WebView view, @Nullable WebResourceRequest request, @Nullable WebResourceResponse errorResponse) {
        super.onReceivedHttpError(view, request, errorResponse);
        this.this$0.checkError(view, request, this.$listener);
    }

    public void onPageStarted(@Nullable WebView view, @Nullable String url, @Nullable Bitmap favicon) {
        this.this$0.showProgressDialog();
        super.onPageStarted(view, url, favicon);
    }

    public void onPageFinished(@Nullable WebView view, @Nullable String url) {
        this.this$0.dismissProgressDialog();
        super.onPageFinished(view, url);
    }

    public boolean shouldOverrideUrlLoading(@NotNull WebView view, @NotNull String url) {
        Intrinsics.checkParameterIsNotNull(view, Promotion.ACTION_VIEW);
        Intrinsics.checkParameterIsNotNull(url, "url");
        Uri parse = Uri.parse(url);
        Intrinsics.checkExpressionValueIsNotNull(parse, "Uri.parse(url)");
        return shouldOverrideUrl(view, parse);
    }

    @TargetApi(24)
    public boolean shouldOverrideUrlLoading(@NotNull WebView view, @NotNull WebResourceRequest request) {
        Intrinsics.checkParameterIsNotNull(view, Promotion.ACTION_VIEW);
        Intrinsics.checkParameterIsNotNull(request, "request");
        Uri url = request.getUrl();
        Intrinsics.checkExpressionValueIsNotNull(url, "request.url");
        return shouldOverrideUrl(view, url);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean shouldOverrideUrl(android.webkit.WebView r5, android.net.Uri r6) {
        /*
        r4 = this;
        r0 = r6.getScheme();
        if (r0 != 0) goto L_0x0017;
    L_0x0006:
        r0 = r5.getContext();
        r1 = new android.content.Intent;
        r2 = "android.intent.action.VIEW";
        r1.<init>(r2, r6);
        r0.startActivity(r1);
        r0 = 1;
    L_0x0016:
        return r0;
    L_0x0017:
        r1 = r0.hashCode();
        switch(r1) {
            case 3213448: goto L_0x001f;
            case 3552184: goto L_0x0034;
            case 99617003: goto L_0x002a;
            default: goto L_0x001e;
        };
    L_0x001e:
        goto L_0x0006;
    L_0x001f:
        r1 = "http";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0006;
    L_0x0028:
        r0 = 0;
        goto L_0x0016;
    L_0x002a:
        r1 = "https";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0006;
    L_0x0033:
        goto L_0x0028;
    L_0x0034:
        r1 = "tado";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0006;
    L_0x003d:
        r0 = new com.tado.android.app.DeepLinkingManager;
        r0.<init>();
        r1 = new android.content.Intent;
        r2 = "android.intent.action.VIEW";
        r1.<init>(r2, r6);
        r2 = r5.getContext();
        r3 = "view.context";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3);
        r0 = r0.parseIntent(r1, r2);
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.views.webview.TadoWebView$initWebView$3.shouldOverrideUrl(android.webkit.WebView, android.net.Uri):boolean");
    }
}
