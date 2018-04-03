package com.tado.android.repairServices;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import com.tado.C0676R;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.rest.model.HomeLocation;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0005H\u0002J\u0018\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u0012\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0010H\u0014J\u000e\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001bR*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/tado/android/repairServices/RepairServicesActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "buttonsForFeaturesMap", "Ljava/util/HashMap;", "", "Lcom/tado/android/repairServices/RepairServicesButton;", "Lkotlin/collections/HashMap;", "addTadoImageButton", "Lcom/tado/android/repairServices/TadoImageButton;", "repairServicesButton", "url", "getURLForHome", "location", "Lcom/tado/android/rest/model/HomeLocation;", "initButtons", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onResume", "setupActionBar", "title", "", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: RepairServicesActivity.kt */
public final class RepairServicesActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private final HashMap<String, RepairServicesButton> buttonsForFeaturesMap = MapsKt__MapsKt.hashMapOf(TuplesKt.to("bookARepair", new RepairServicesButton(C0676R.string.repairServices_bookRepairButton, C0676R.drawable.tado_button_selectable_book_repair, "bookRepair")), TuplesKt.to("bookABoiler", new RepairServicesButton(C0676R.string.repairServices_bookMaintenanceButton, C0676R.drawable.tado_button_selectable_book_service, "bookService")), TuplesKt.to("getABoilerQuote", new RepairServicesButton(C0676R.string.repairServices_getQuoteButton, C0676R.drawable.tado_button_selectable_boiler_quote, "getBoilerQuote")), TuplesKt.to("getABoilerCover", new RepairServicesButton(C0676R.string.repairServices_getBoilerCoverButton, C0676R.drawable.tado_button_selectable_boiler_cover, "getBoilerCover")));

    private final void initButtons() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.tado.android.repairServices.RepairServicesActivity.initButtons():void
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r0 = this;
        r9 = 2131296715; // 0x7f0901cb float:1.8211355E38 double:1.053000488E-314;
        r5 = r12.findViewById(r9);
        r5 = (android.widget.LinearLayout) r5;
        r2 = com.tado.android.utils.UserConfig.getHomeCountry();
        r3 = com.tado.android.utils.UserConfig.getHomeLocation();
        if (r2 == 0) goto L_0x007a;
    L_0x0013:
        r4 = r2;
        r9 = r12;
        r9 = (android.app.Activity) r9;
        r9 = com.tado.android.repairServices.RepairServicesLoaderKt.loadRepairServices(r9);
        if (r9 == 0) goto L_0x0072;
    L_0x001d:
        r10 = com.tado.android.repairServices.Country.valueOf(r2);
        r9 = r9.get(r10);
        r9 = (java.util.List) r9;
        r7 = r9;
        if (r7 == 0) goto L_0x0079;
    L_0x002a:
        r0 = r7;
        r0 = (java.lang.Iterable) r0;
        r10 = r0.iterator();
        r9 = r10.hasNext();
        if (r9 == 0) goto L_0x0079;
        r1 = r10.next();
        r4 = r1;
        r4 = (com.tado.android.repairServices.RepairService) r4;
        r9 = r12.buttonsForFeaturesMap;
        r11 = r4.getName();
        r6 = r9.get(r11);
        r6 = (com.tado.android.repairServices.RepairServicesButton) r6;
        if (r3 == 0) goto L_0x0074;
        r9 = com.tado.android.utils.UserConfig.getPartner();
        r11 = com.tado.android.rest.model.HomeInfo.PartnerEnum.HOMESERVE;
        r9 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r11);
        r9 = r9 ^ 1;
        if (r9 == 0) goto L_0x0074;
        r9 = r4.getUrl();
        r8 = r12.getURLForHome(r9, r3);
        if (r6 == 0) goto L_0x006f;
        r4 = r6;
        r9 = r12.addTadoImageButton(r6, r8);
        r9 = (android.view.View) r9;
        r5.addView(r9);
        goto L_0x0031;
    L_0x0072:
        r7 = 0;
        goto L_0x0028;
        r8 = r4.getUrl();
        goto L_0x0062;
    L_0x007a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.repairServices.RepairServicesActivity.initButtons():void");
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
        setContentView((int) C0676R.layout.activity_repair_services);
        CharSequence text = getText(C0676R.string.repairServices_title);
        Intrinsics.checkExpressionValueIsNotNull(text, "getText(R.string.repairServices_title)");
        setupActionBar(text);
        initButtons();
    }

    protected void onResume() {
        super.onResume();
        AnalyticsHelper.trackPageView((Activity) this, Screen.REPAIR_SERVICES);
    }

    private final String getURLForHome(String url, HomeLocation location) {
        return "" + url + "?latitude=" + location.geolocation.latitude + "&longitude=" + location.geolocation.longitude;
    }

    private final TadoImageButton addTadoImageButton(RepairServicesButton repairServicesButton, String url) {
        Drawable backgroundDrawable;
        String title = getString(repairServicesButton.getTitleResId());
        if (VERSION.SDK_INT >= 21) {
            backgroundDrawable = getResources().getDrawable(repairServicesButton.getBackgroundResId(), null);
        } else {
            backgroundDrawable = getResources().getDrawable(repairServicesButton.getBackgroundResId());
        }
        Intrinsics.checkExpressionValueIsNotNull(title, "title");
        Intrinsics.checkExpressionValueIsNotNull(backgroundDrawable, "backgroundDrawable");
        TadoImageButton button = new TadoImageButton(title, backgroundDrawable, new RepairServicesActivity$addTadoImageButton$button$1(this, url, repairServicesButton), this, null, 0, 48, null);
        LayoutParams $receiver = new LayoutParams(-2, -2);
        $receiver.bottomMargin = getResources().getDimensionPixelSize(C0676R.dimen.tado_image_button_spacing);
        button.setLayoutParams($receiver);
        return button;
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
