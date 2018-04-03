package com.tado.android.repairServices;

import com.tado.android.views.webview.TadoWebViewListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"com/tado/android/repairServices/RepairServicesWebViewActivity$initWebView$1", "Lcom/tado/android/views/webview/TadoWebViewListener;", "(Lcom/tado/android/repairServices/RepairServicesWebViewActivity;)V", "onLeaveWebView", "", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: RepairServicesWebViewActivity.kt */
public final class RepairServicesWebViewActivity$initWebView$1 extends TadoWebViewListener {
    final /* synthetic */ RepairServicesWebViewActivity this$0;

    RepairServicesWebViewActivity$initWebView$1(RepairServicesWebViewActivity $outer) {
        this.this$0 = $outer;
    }

    public void onLeaveWebView() {
        this.this$0.onBackPressed();
    }
}
