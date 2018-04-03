package com.tado.android.demo.phone.presenter;

import com.google.gson.Gson;
import com.tado.C0676R;
import com.tado.android.demo.phone.model.AbstractPhoneNumberListItem;
import com.tado.android.demo.phone.model.PhoneNumberListItem;
import com.tado.android.demo.phone.view.PhoneListView;
import com.tado.android.mvp.common.BasePresenter;
import com.tado.android.utils.FileUtils;
import com.tado.android.utils.Util;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\t\u001a\u00020\n2\b\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0016J\n\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\nJ\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0002J>\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u00122\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u00122\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00190\u0018H\u0002J\b\u0010\u001a\u001a\u00020\nH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0002X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/tado/android/demo/phone/presenter/PhoneListPresenter;", "Lcom/tado/android/mvp/common/BasePresenter;", "Lcom/tado/android/demo/phone/view/PhoneListView;", "()V", "FLAGS", "", "Lcom/tado/android/demo/phone/presenter/Country;", "", "view", "bindView", "", "loadSupportedPhoneNumbers", "Lcom/tado/android/demo/phone/presenter/SupportPhoneNumbers;", "onPhoneNumberListItemClick", "item", "Lcom/tado/android/demo/phone/model/PhoneNumberListItem;", "onResumeDialog", "phoneItems", "", "Lcom/tado/android/demo/phone/model/AbstractPhoneNumberListItem;", "populatePhoneItems", "recommended", "others", "numbers", "Ljava/util/HashMap;", "", "unbindView", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: PhoneListPresenter.kt */
public final class PhoneListPresenter implements BasePresenter<PhoneListView> {
    private final Map<Country, Integer> FLAGS = MapsKt__MapsKt.hashMapOf(TuplesKt.to(Country.UK, Integer.valueOf(C0676R.drawable.ic_uk)), TuplesKt.to(Country.US, Integer.valueOf(C0676R.drawable.ic_us)), TuplesKt.to(Country.DE, Integer.valueOf(C0676R.drawable.ic_de)), TuplesKt.to(Country.IT, Integer.valueOf(C0676R.drawable.ic_it)), TuplesKt.to(Country.ES, Integer.valueOf(C0676R.drawable.ic_es)), TuplesKt.to(Country.FR, Integer.valueOf(C0676R.drawable.ic_fr)), TuplesKt.to(Country.CH, Integer.valueOf(C0676R.drawable.ic_ch)), TuplesKt.to(Country.NL, Integer.valueOf(C0676R.drawable.ic_nl)), TuplesKt.to(Country.AT, Integer.valueOf(C0676R.drawable.ic_at)), TuplesKt.to(Country.BE, Integer.valueOf(C0676R.drawable.ic_bl)), TuplesKt.to(Country.XX, Integer.valueOf(C0676R.drawable.ic_others)));
    private PhoneListView view;

    private final java.util.List<com.tado.android.demo.phone.model.AbstractPhoneNumberListItem> populatePhoneItems(java.util.List<? extends com.tado.android.demo.phone.presenter.Country> r1, java.util.List<? extends com.tado.android.demo.phone.presenter.Country> r2, java.util.HashMap<com.tado.android.demo.phone.presenter.Country, java.lang.String> r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.tado.android.demo.phone.presenter.PhoneListPresenter.populatePhoneItems(java.util.List, java.util.List, java.util.HashMap):java.util.List<com.tado.android.demo.phone.model.AbstractPhoneNumberListItem>
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
	at jadx.core.dex.nodes.MethodNode.addJump(MethodNode.java:370)
	at jadx.core.dex.nodes.MethodNode.initJumps(MethodNode.java:356)
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:106)
	... 5 more
*/
        /*
        r0 = this;
        r7 = 1;
        r8 = 0;
        r4 = new java.util.ArrayList;
        r4.<init>();
        r5 = r12;
        r5 = (java.util.Collection) r5;
        r5 = r5.isEmpty();
        if (r5 != 0) goto L_0x0079;
    L_0x0010:
        r5 = r7;
        if (r5 == 0) goto L_0x007d;
    L_0x0013:
        r5 = r13;
        r5 = (java.util.Collection) r5;
        r5 = r5.isEmpty();
        if (r5 != 0) goto L_0x007b;
    L_0x001c:
        r5 = r7;
        if (r5 == 0) goto L_0x007d;
    L_0x001f:
        r2 = r7;
        r5 = r12;
        r5 = (java.util.Collection) r5;
        r5 = r5.isEmpty();
        if (r5 != 0) goto L_0x007f;
    L_0x0029:
        r5 = r7;
        if (r5 == 0) goto L_0x0082;
    L_0x002c:
        if (r2 == 0) goto L_0x0039;
    L_0x002e:
        r5 = new com.tado.android.demo.phone.model.PhoneNumberListHeaderItem;
        r6 = 2131689825; // 0x7f0f0161 float:1.9008676E38 double:1.05319471E-314;
        r5.<init>(r6);
        r4.add(r5);
    L_0x0039:
        r0 = r12;
        r0 = (java.lang.Iterable) r0;
        r9 = r0.iterator();
        r5 = r9.hasNext();
        if (r5 == 0) goto L_0x0081;
    L_0x0046:
        r1 = r9.next();
        r3 = r1;
        r3 = (com.tado.android.demo.phone.presenter.Country) r3;
        r10 = new com.tado.android.demo.phone.model.PhoneNumberListItem;
        r5 = r14.get(r3);
        if (r5 != 0) goto L_0x0058;
        kotlin.jvm.internal.Intrinsics.throwNpe();
        r6 = "numbers[it]!!";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6);
        r5 = (java.lang.String) r5;
        r6 = r11.FLAGS;
        r6 = r6.get(r3);
        if (r6 != 0) goto L_0x006b;
        kotlin.jvm.internal.Intrinsics.throwNpe();
        r6 = (java.lang.Number) r6;
        r6 = r6.intValue();
        r10.<init>(r5, r6);
        r4.add(r10);
        goto L_0x0040;
    L_0x0079:
        r5 = r8;
        goto L_0x0011;
    L_0x007b:
        r5 = r8;
        goto L_0x001d;
    L_0x007d:
        r2 = r8;
        goto L_0x0020;
    L_0x007f:
        r5 = r8;
        goto L_0x002a;
    L_0x0082:
        r5 = r13;
        r5 = (java.util.Collection) r5;
        r5 = r5.isEmpty();
        if (r5 != 0) goto L_0x00da;
        if (r7 == 0) goto L_0x00dd;
        if (r2 == 0) goto L_0x009a;
        r5 = new com.tado.android.demo.phone.model.PhoneNumberListHeaderItem;
        r6 = 2131689824; // 0x7f0f0160 float:1.9008674E38 double:1.0531947096E-314;
        r5.<init>(r6);
        r4.add(r5);
        r0 = r13;
        r0 = (java.lang.Iterable) r0;
        r7 = r0.iterator();
        r5 = r7.hasNext();
        if (r5 == 0) goto L_0x00dc;
        r1 = r7.next();
        r3 = r1;
        r3 = (com.tado.android.demo.phone.presenter.Country) r3;
        r8 = new com.tado.android.demo.phone.model.PhoneNumberListItem;
        r5 = r14.get(r3);
        if (r5 != 0) goto L_0x00b9;
        kotlin.jvm.internal.Intrinsics.throwNpe();
        r6 = "numbers[it]!!";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6);
        r5 = (java.lang.String) r5;
        r6 = r11.FLAGS;
        r6 = r6.get(r3);
        if (r6 != 0) goto L_0x00cc;
        kotlin.jvm.internal.Intrinsics.throwNpe();
        r6 = (java.lang.Number) r6;
        r6 = r6.intValue();
        r8.<init>(r5, r6);
        r4.add(r8);
        goto L_0x00a1;
        r7 = r8;
        goto L_0x008b;
        r4 = (java.util.List) r4;
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.demo.phone.presenter.PhoneListPresenter.populatePhoneItems(java.util.List, java.util.List, java.util.HashMap):java.util.List<com.tado.android.demo.phone.model.AbstractPhoneNumberListItem>");
    }

    public void bindView(@Nullable PhoneListView view) {
        this.view = view;
    }

    public void unbindView() {
        this.view = (PhoneListView) null;
    }

    public final void onResumeDialog() {
        PhoneListView phoneListView = this.view;
        if (phoneListView != null) {
            phoneListView.preparePhoneList(phoneItems());
        }
    }

    public final void onPhoneNumberListItemClick(@NotNull PhoneNumberListItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        PhoneListView phoneListView = this.view;
        if (phoneListView != null) {
            phoneListView.dialNumber(item.getPhoneNumber());
        }
    }

    private final SupportPhoneNumbers loadSupportedPhoneNumbers() {
        try {
            PhoneListView phoneListView = this.view;
            String json = FileUtils.loadJSONFromAsset(phoneListView != null ? phoneListView.assets() : null, "support_phone_numbers.json");
            if (json != null) {
                String it = json;
                return (SupportPhoneNumbers) new Gson().fromJson(json, SupportPhoneNumbers.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private final List<AbstractPhoneNumberListItem> phoneItems() {
        SupportPhoneNumbers supportPhoneNumbers = loadSupportedPhoneNumbers();
        if (supportPhoneNumbers == null) {
            return new ArrayList();
        }
        SupportPhoneNumbers it = supportPhoneNumbers;
        String supportedLanguage = Util.getSupportedLanguage();
        Intrinsics.checkExpressionValueIsNotNull(supportedLanguage, "Util.getSupportedLanguage()");
        if (supportedLanguage == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        supportedLanguage = supportedLanguage.toUpperCase();
        Intrinsics.checkExpressionValueIsNotNull(supportedLanguage, "(this as java.lang.String).toUpperCase()");
        List list = (List) supportPhoneNumbers.getRecommendedByLanguage().get(Language.valueOf(supportedLanguage));
        List recommended = list != null ? list : new ArrayList();
        Collection destination$iv$iv = new ArrayList();
        for (Country element$iv$iv : supportPhoneNumbers.getDefaultOrder()) {
            if ((!recommended.contains((Country) element$iv$iv) ? 1 : null) != null) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List others = (List) destination$iv$iv;
        Intrinsics.checkExpressionValueIsNotNull(recommended, "recommended");
        return populatePhoneItems(recommended, others, supportPhoneNumbers.getNumbers());
    }
}
