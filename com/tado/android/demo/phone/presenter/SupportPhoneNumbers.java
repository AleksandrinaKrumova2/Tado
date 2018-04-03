package com.tado.android.demo.phone.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001BA\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\b0\u0006\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n0\u0006¢\u0006\u0002\u0010\u000bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR#\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/tado/android/demo/phone/presenter/SupportPhoneNumbers;", "", "defaultOrder", "Ljava/util/ArrayList;", "Lcom/tado/android/demo/phone/presenter/Country;", "recommendedByLanguage", "Ljava/util/HashMap;", "Lcom/tado/android/demo/phone/presenter/Language;", "", "numbers", "", "(Ljava/util/ArrayList;Ljava/util/HashMap;Ljava/util/HashMap;)V", "getDefaultOrder", "()Ljava/util/ArrayList;", "getNumbers", "()Ljava/util/HashMap;", "getRecommendedByLanguage", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: PhoneListPresenter.kt */
public final class SupportPhoneNumbers {
    @NotNull
    private final ArrayList<Country> defaultOrder;
    @NotNull
    private final HashMap<Country, String> numbers;
    @NotNull
    private final HashMap<Language, List<Country>> recommendedByLanguage;

    public SupportPhoneNumbers(@NotNull ArrayList<Country> defaultOrder, @NotNull HashMap<Language, List<Country>> recommendedByLanguage, @NotNull HashMap<Country, String> numbers) {
        Intrinsics.checkParameterIsNotNull(defaultOrder, "defaultOrder");
        Intrinsics.checkParameterIsNotNull(recommendedByLanguage, "recommendedByLanguage");
        Intrinsics.checkParameterIsNotNull(numbers, "numbers");
        this.defaultOrder = defaultOrder;
        this.recommendedByLanguage = recommendedByLanguage;
        this.numbers = numbers;
    }

    @NotNull
    public final ArrayList<Country> getDefaultOrder() {
        return this.defaultOrder;
    }

    @NotNull
    public final HashMap<Country, String> getNumbers() {
        return this.numbers;
    }

    @NotNull
    public final HashMap<Language, List<Country>> getRecommendedByLanguage() {
        return this.recommendedByLanguage;
    }
}
