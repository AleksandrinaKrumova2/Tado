package com.tado.android.views.webview;

import android.content.Context;
import android.os.Message;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebView.HitTestResult;
import kotlin.Metadata;
import org.jetbrains.anko.IntentsKt;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J,\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u000b"}, d2 = {"com/tado/android/views/webview/TadoWebView$initWebView$2", "Landroid/webkit/WebChromeClient;", "()V", "onCreateWindow", "", "view", "Landroid/webkit/WebView;", "isDialog", "isUserGesture", "resultMsg", "Landroid/os/Message;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: TadoWebView.kt */
public final class TadoWebView$initWebView$2 extends WebChromeClient {
    TadoWebView$initWebView$2() {
    }

    public boolean onCreateWindow(@Nullable WebView view, boolean isDialog, boolean isUserGesture, @Nullable Message resultMsg) {
        HitTestResult result;
        String url = null;
        if (view != null) {
            result = view.getHitTestResult();
        } else {
            result = null;
        }
        if (result != null) {
            url = result.getExtra();
        }
        if (url == null) {
            return false;
        }
        Context context = view.getContext();
        if (context == null) {
            return true;
        }
        IntentsKt.browse(context, url, true);
        return true;
    }
}
