package com.tado.android.premium;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.anko.AnkoAsyncContext;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "Lorg/jetbrains/anko/AnkoAsyncContext;", "Lcom/tado/android/premium/PremiumCarouselActivity;", "invoke"}, k = 3, mv = {1, 1, 9})
/* compiled from: PremiumCarouselActivity.kt */
final class PremiumCarouselActivity$onCreate$1 extends Lambda implements Function1<AnkoAsyncContext<PremiumCarouselActivity>, Unit> {
    final /* synthetic */ PremiumCarouselActivity this$0;

    PremiumCarouselActivity$onCreate$1(PremiumCarouselActivity premiumCarouselActivity) {
        this.this$0 = premiumCarouselActivity;
        super(1);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void invoke(@org.jetbrains.annotations.NotNull org.jetbrains.anko.AnkoAsyncContext<com.tado.android.premium.PremiumCarouselActivity> r24) {
        /*
        r23 = this;
        r3 = "$receiver";
        r0 = r24;
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r3);
        r0 = r23;
        r3 = r0.this$0;	 Catch:{ Exception -> 0x016b }
        r3 = r3.getAssets();	 Catch:{ Exception -> 0x016b }
        r5 = "premium_prices.json";
        r17 = com.tado.android.utils.FileUtils.loadJSONFromAsset(r3, r5);	 Catch:{ Exception -> 0x016b }
        if (r17 == 0) goto L_0x0160;
    L_0x001a:
        r16 = r17;
        r3 = new com.tado.android.premium.PremiumCarouselActivity$onCreate$1$1$type$1;	 Catch:{ Exception -> 0x016b }
        r3.<init>();	 Catch:{ Exception -> 0x016b }
        r22 = r3.getType();	 Catch:{ Exception -> 0x016b }
        r3 = new com.google.gson.Gson;	 Catch:{ Exception -> 0x016b }
        r3.<init>();	 Catch:{ Exception -> 0x016b }
        r0 = r16;
        r1 = r22;
        r18 = r3.fromJson(r0, r1);	 Catch:{ Exception -> 0x016b }
        r3 = "Gson().fromJson(it, type)";
        r0 = r18;
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r3);	 Catch:{ Exception -> 0x016b }
        r18 = (java.util.HashMap) r18;	 Catch:{ Exception -> 0x016b }
        r15 = com.tado.android.utils.UserConfig.getHomeCountry();	 Catch:{ Exception -> 0x016b }
        r3 = "UserConfig.getHomeCountry()";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r15, r3);	 Catch:{ Exception -> 0x016b }
        r11 = com.tado.android.premium.PremiumCarouselActivity.Country.f397default;	 Catch:{ Exception -> 0x016b }
        r11 = com.tado.android.premium.PremiumCarouselActivity.Country.valueOf(r15);	 Catch:{ IllegalArgumentException -> 0x0170 }
    L_0x004d:
        r0 = r23;
        r5 = r0.this$0;	 Catch:{ Exception -> 0x016b }
        r0 = r18;
        r3 = r0.get(r11);	 Catch:{ Exception -> 0x016b }
        r3 = (com.tado.android.premium.PremiumCarouselActivity.PriceTag) r3;	 Catch:{ Exception -> 0x016b }
        r5.setPriceTag(r3);	 Catch:{ Exception -> 0x016b }
        r0 = r23;
        r3 = r0.this$0;	 Catch:{ Exception -> 0x016b }
        r3 = r3.getPriceTag();	 Catch:{ Exception -> 0x016b }
        if (r3 == 0) goto L_0x0161;
    L_0x0066:
        r14 = r3.getFormattedReducedPrice();	 Catch:{ Exception -> 0x016b }
        if (r14 == 0) goto L_0x0161;
    L_0x006c:
        r0 = r23;
        r3 = r0.this$0;	 Catch:{ Exception -> 0x016b }
        r5 = 2131690698; // 0x7f0f04ca float:1.9010447E38 double:1.0531951414E-314;
        r21 = r3.getString(r5);	 Catch:{ Exception -> 0x016b }
        r0 = r23;
        r3 = r0.this$0;	 Catch:{ Exception -> 0x016b }
        r5 = 2131690699; // 0x7f0f04cb float:1.901045E38 double:1.053195142E-314;
        r4 = r3.getString(r5);	 Catch:{ Exception -> 0x016b }
        r0 = r23;
        r3 = r0.this$0;	 Catch:{ Exception -> 0x016b }
        r3 = r3.getPriceTag();	 Catch:{ Exception -> 0x016b }
        if (r3 == 0) goto L_0x0166;
    L_0x008c:
        r20 = r3.getFormattedRegularPrice();	 Catch:{ Exception -> 0x016b }
        if (r20 == 0) goto L_0x0166;
    L_0x0092:
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x016b }
        r3.<init>();	 Catch:{ Exception -> 0x016b }
        r5 = "";
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x016b }
        r3 = r3.append(r14);	 Catch:{ Exception -> 0x016b }
        r5 = 32;
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x016b }
        r0 = r21;
        r3 = r3.append(r0);	 Catch:{ Exception -> 0x016b }
        r5 = " \n";
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x016b }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x016b }
        r5 = 32;
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x016b }
        r0 = r20;
        r3 = r3.append(r0);	 Catch:{ Exception -> 0x016b }
        r13 = r3.toString();	 Catch:{ Exception -> 0x016b }
        r19 = new android.text.SpannableString;	 Catch:{ Exception -> 0x016b }
        r0 = r13;
        r0 = (java.lang.CharSequence) r0;	 Catch:{ Exception -> 0x016b }
        r3 = r0;
        r0 = r19;
        r0.<init>(r3);	 Catch:{ Exception -> 0x016b }
        r3 = new android.text.style.ForegroundColorSpan;	 Catch:{ Exception -> 0x016b }
        r0 = r23;
        r5 = r0.this$0;	 Catch:{ Exception -> 0x016b }
        r5 = r5.getApplicationContext();	 Catch:{ Exception -> 0x016b }
        r6 = 2131099881; // 0x7f0600e9 float:1.7812128E38 double:1.052903239E-314;
        r5 = android.support.v4.content.ContextCompat.getColor(r5, r6);	 Catch:{ Exception -> 0x016b }
        r3.<init>(r5);	 Catch:{ Exception -> 0x016b }
        r5 = 0;
        r6 = r14.length();	 Catch:{ Exception -> 0x016b }
        r7 = 33;
        r0 = r19;
        r0.setSpan(r3, r5, r6, r7);	 Catch:{ Exception -> 0x016b }
        r9 = new android.text.style.AbsoluteSizeSpan;	 Catch:{ Exception -> 0x016b }
        r3 = 13;
        r3 = (float) r3;	 Catch:{ Exception -> 0x016b }
        r0 = r23;
        r5 = r0.this$0;	 Catch:{ Exception -> 0x016b }
        r5 = r5.getResources();	 Catch:{ Exception -> 0x016b }
        r6 = "resources";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6);	 Catch:{ Exception -> 0x016b }
        r5 = r5.getDisplayMetrics();	 Catch:{ Exception -> 0x016b }
        r5 = r5.density;	 Catch:{ Exception -> 0x016b }
        r3 = r3 * r5;
        r3 = (int) r3;	 Catch:{ Exception -> 0x016b }
        r9.<init>(r3);	 Catch:{ Exception -> 0x016b }
        r0 = r13;
        r0 = (java.lang.CharSequence) r0;	 Catch:{ Exception -> 0x016b }
        r3 = r0;
        r5 = "secondLineNormal";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5);	 Catch:{ Exception -> 0x016b }
        r5 = 0;
        r6 = 0;
        r7 = 6;
        r8 = 0;
        r3 = kotlin.text.StringsKt__StringsKt.indexOf$default(r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x016b }
        r5 = r13.length();	 Catch:{ Exception -> 0x016b }
        r6 = 33;
        r0 = r19;
        r0.setSpan(r9, r3, r5, r6);	 Catch:{ Exception -> 0x016b }
        r3 = new android.text.style.StrikethroughSpan;	 Catch:{ Exception -> 0x016b }
        r3.<init>();	 Catch:{ Exception -> 0x016b }
        r0 = r13;
        r0 = (java.lang.CharSequence) r0;	 Catch:{ Exception -> 0x016b }
        r5 = r0;
        r7 = 0;
        r8 = 0;
        r9 = 6;
        r10 = 0;
        r6 = r20;
        r5 = kotlin.text.StringsKt__StringsKt.indexOf$default(r5, r6, r7, r8, r9, r10);	 Catch:{ Exception -> 0x016b }
        r6 = r13.length();	 Catch:{ Exception -> 0x016b }
        r7 = 33;
        r0 = r19;
        r0.setSpan(r3, r5, r6, r7);	 Catch:{ Exception -> 0x016b }
        r3 = new com.tado.android.premium.PremiumCarouselActivity$onCreate$1$$special$$inlined$let$lambda$1;	 Catch:{ Exception -> 0x016b }
        r0 = r19;
        r1 = r23;
        r2 = r24;
        r3.<init>(r0, r1, r2);	 Catch:{ Exception -> 0x016b }
        r3 = (kotlin.jvm.functions.Function1) r3;	 Catch:{ Exception -> 0x016b }
        r0 = r24;
        org.jetbrains.anko.AsyncKt.uiThread(r0, r3);	 Catch:{ Exception -> 0x016b }
    L_0x0160:
        return;
    L_0x0161:
        r14 = "";
        goto L_0x006c;
    L_0x0166:
        r20 = "";
        goto L_0x0092;
    L_0x016b:
        r12 = move-exception;
        r12.printStackTrace();
        goto L_0x0160;
    L_0x0170:
        r3 = move-exception;
        goto L_0x004d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.premium.PremiumCarouselActivity$onCreate$1.invoke(org.jetbrains.anko.AnkoAsyncContext):void");
    }
}
