package com.tado.android.premium;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.utils.Util;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lcom/tado/android/premium/PremiumConfirmationActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: PremiumConfirmationActivity.kt */
public final class PremiumConfirmationActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;

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
        setContentView((int) C0676R.layout.premium_confirmation_activity);
        TextView textView = (TextView) _$_findCachedViewById(C0676R.id.confirmation_text);
        Intrinsics.checkExpressionValueIsNotNull(textView, "confirmation_text");
        textView.setText(Util.getText(this, C0676R.string.premiumUpgrade_subscriptionFlow_welcomeToPremium_welcomeMessage, getIntent().getStringExtra("account")));
        ((AppCompatButton) _$_findCachedViewById(C0676R.id.premium_payment_done)).setOnClickListener(new PremiumConfirmationActivity$onCreate$1(this));
    }
}
