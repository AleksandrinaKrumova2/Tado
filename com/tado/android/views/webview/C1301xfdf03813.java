package com.tado.android.views.webview;

import android.webkit.WebView;
import com.tado.android.dialogs.AlertChoiceDialogListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\u0006"}, d2 = {"com/tado/android/views/webview/TadoWebViewListener$showLoadingError$1$1", "Lcom/tado/android/dialogs/AlertChoiceDialogListener;", "(Lcom/tado/android/views/webview/TadoWebViewListener$showLoadingError$1;)V", "OnCancelClicked", "", "OnOKClicked", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: TadoWebViewListener.kt */
public final class C1301xfdf03813 implements AlertChoiceDialogListener {
    final /* synthetic */ WebView $view$inlined;
    final /* synthetic */ TadoWebViewListener this$0;

    C1301xfdf03813(TadoWebViewListener tadoWebViewListener, WebView webView) {
        this.this$0 = tadoWebViewListener;
        this.$view$inlined = webView;
    }

    public void OnOKClicked() {
        this.this$0.errorAlertShown = false;
        TadoWebView access$getWebView$p = this.this$0.webView;
        if (access$getWebView$p != null) {
            access$getWebView$p.reload();
        }
    }

    public void OnCancelClicked() {
        this.this$0.errorAlertShown = false;
        this.this$0.onLeaveWebView();
    }
}
