package com.tado.android.repairServices;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.views.webview.TadoWebView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010¨\u0006\u0012"}, d2 = {"Lcom/tado/android/repairServices/RepairServicesWebViewActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "initWebView", "", "webView", "Lcom/tado/android/views/webview/TadoWebView;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "setupActionBar", "title", "", "Companion", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: RepairServicesWebViewActivity.kt */
public final class RepairServicesWebViewActivity extends AppCompatActivity {
    public static final Companion Companion = new Companion();
    private static final String SERVICE_URL = SERVICE_URL;
    private HashMap _$_findViewCache;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lcom/tado/android/repairServices/RepairServicesWebViewActivity$Companion;", "", "()V", "SERVICE_URL", "", "getSERVICE_URL", "()Ljava/lang/String;", "newIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "url", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: RepairServicesWebViewActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        private final String getSERVICE_URL() {
            return RepairServicesWebViewActivity.SERVICE_URL;
        }

        @NotNull
        public final Intent newIntent(@NotNull Context context, @NotNull String url) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(url, "url");
            Intent intent = new Intent(context, RepairServicesWebViewActivity.class);
            intent.putExtra(getSERVICE_URL(), url);
            return intent;
        }
    }

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

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_repair_services_web_view);
        CharSequence text = getText(C0676R.string.repairServices_title);
        Intrinsics.checkExpressionValueIsNotNull(text, "getText(R.string.repairServices_title)");
        setupActionBar(text);
        TadoWebView tadoWebView = (TadoWebView) _$_findCachedViewById(C0676R.id.webView);
        Intrinsics.checkExpressionValueIsNotNull(tadoWebView, "webView");
        initWebView(tadoWebView);
        ((TadoWebView) _$_findCachedViewById(C0676R.id.webView)).loadUrl(getIntent().getStringExtra(Companion.getSERVICE_URL()));
    }

    private final void initWebView(TadoWebView webView) {
        webView.initWebView(new RepairServicesWebViewActivity$initWebView$1(this));
    }

    public final void setupActionBar(@NotNull CharSequence title) {
        Intrinsics.checkParameterIsNotNull(title, "title");
        ActionBar $receiver = getSupportActionBar();
        if ($receiver != null) {
            Intrinsics.checkExpressionValueIsNotNull($receiver, "this");
            $receiver.setTitle(title);
            $receiver.setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
