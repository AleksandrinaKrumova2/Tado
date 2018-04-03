package com.tado.android.premium;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.tado.C0676R;
import com.tado.android.PromoPageFragment;
import com.tado.android.rest.callback.presenters.ErrorAlertWithBackOnCancelPresenter;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.rest.service.TadoApiService;
import com.tado.android.settings.homedetails.data.HomeRepository;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import me.relex.circleindicator.CircleIndicator;
import org.jetbrains.anko.AsyncKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0005\u0013\u0014\u0015\u0016\u0017B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/tado/android/premium/PremiumCarouselActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "accountName", "", "getAccountName", "()Ljava/lang/String;", "setAccountName", "(Ljava/lang/String;)V", "priceTag", "Lcom/tado/android/premium/PremiumCarouselActivity$PriceTag;", "getPriceTag", "()Lcom/tado/android/premium/PremiumCarouselActivity$PriceTag;", "setPriceTag", "(Lcom/tado/android/premium/PremiumCarouselActivity$PriceTag;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "Country", "Page", "PremiumAdapter", "PremiumPage", "PriceTag", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: PremiumCarouselActivity.kt */
public final class PremiumCarouselActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    @Nullable
    private String accountName;
    @Nullable
    private PriceTag priceTag;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/tado/android/premium/PremiumCarouselActivity$Country;", "", "(Ljava/lang/String;I)V", "GBR", "DEU", "default", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: PremiumCarouselActivity.kt */
    public enum Country {
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u0013\u001a\u00020\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J&\u0010\u0016\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u001c"}, d2 = {"Lcom/tado/android/premium/PremiumCarouselActivity$Page;", "Landroid/support/v4/app/Fragment;", "()V", "cover", "", "getCover", "()Z", "setCover", "(Z)V", "page", "Lcom/tado/android/premium/PremiumCarouselActivity$PremiumPage;", "getPage", "()Lcom/tado/android/premium/PremiumCarouselActivity$PremiumPage;", "setPage", "(Lcom/tado/android/premium/PremiumCarouselActivity$PremiumPage;)V", "bindView", "", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "Companion", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: PremiumCarouselActivity.kt */
    public static final class Page extends Fragment {
        public static final Companion Companion = new Companion();
        private HashMap _$_findViewCache;
        private boolean cover = true;
        @Nullable
        private PremiumPage page;

        @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/tado/android/premium/PremiumCarouselActivity$Page$Companion;", "", "()V", "getPageCover", "Lcom/tado/android/premium/PremiumCarouselActivity$Page;", "getPageInstance", "premiumPage", "Lcom/tado/android/premium/PremiumCarouselActivity$PremiumPage;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
        /* compiled from: PremiumCarouselActivity.kt */
        public static final class Companion {
            private Companion() {
            }

            @JvmStatic
            @NotNull
            public final Page getPageCover() {
                Page page = new Page();
                Bundle arguments = new Bundle(1);
                arguments.putBoolean("cover", true);
                page.setArguments(arguments);
                return page;
            }

            @JvmStatic
            @NotNull
            public final Page getPageInstance(@NotNull PremiumPage premiumPage) {
                Intrinsics.checkParameterIsNotNull(premiumPage, "premiumPage");
                Page page = new Page();
                Bundle arguments = new Bundle(2);
                arguments.putBoolean("cover", false);
                arguments.putParcelable(PromoPageFragment.ARG_PAGE, premiumPage);
                page.setArguments(arguments);
                return page;
            }
        }

        @JvmStatic
        @NotNull
        public static final Page getPageCover() {
            return Companion.getPageCover();
        }

        @JvmStatic
        @NotNull
        public static final Page getPageInstance(@NotNull PremiumPage premiumPage) {
            return Companion.getPageInstance(premiumPage);
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
            view = getView();
            if (view == null) {
                return null;
            }
            view = view.findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), view);
            return view;
        }

        public /* synthetic */ void onDestroyView() {
            super.onDestroyView();
            _$_clearFindViewByIdCache();
        }

        public final boolean getCover() {
            return this.cover;
        }

        public final void setCover(boolean <set-?>) {
            this.cover = <set-?>;
        }

        @Nullable
        public final PremiumPage getPage() {
            return this.page;
        }

        public final void setPage(@Nullable PremiumPage <set-?>) {
            this.page = <set-?>;
        }

        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Bundle arguments = getArguments();
            Object obj = arguments != null ? arguments.get("cover") : null;
            if (obj == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
            }
            this.cover = ((Boolean) obj).booleanValue();
            if (!this.cover) {
                arguments = getArguments();
                obj = arguments != null ? arguments.get(PromoPageFragment.ARG_PAGE) : null;
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.tado.android.premium.PremiumCarouselActivity.PremiumPage");
                }
                this.page = (PremiumPage) obj;
            }
        }

        @Nullable
        public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Intrinsics.checkParameterIsNotNull(inflater, "inflater");
            if (this.cover) {
                return inflater.inflate(C0676R.layout.premium_carousel_cover, container, false);
            }
            View view = inflater.inflate(C0676R.layout.premium_carousel_page_template, container, false);
            Intrinsics.checkExpressionValueIsNotNull(view, Promotion.ACTION_VIEW);
            PremiumPage premiumPage = this.page;
            if (premiumPage == null) {
                Intrinsics.throwNpe();
            }
            bindView(view, premiumPage);
            return view;
        }

        private final void bindView(View view, PremiumPage page) {
            if (!this.cover) {
                ((TextView) view.findViewById(C0676R.id.title)).setText(page.getTitleRes());
                ((ImageView) view.findViewById(C0676R.id.feature_image)).setImageResource(page.getImageRes());
                ((TextView) view.findViewById(C0676R.id.description)).setText(page.getDescriptionRes());
            }
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\u0006H\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/tado/android/premium/PremiumCarouselActivity$PremiumAdapter;", "Landroid/support/v4/app/FragmentPagerAdapter;", "fragmentManager", "Landroid/support/v4/app/FragmentManager;", "(Landroid/support/v4/app/FragmentManager;)V", "NUM_ITEMS", "", "premiumPageList", "", "Lcom/tado/android/premium/PremiumCarouselActivity$PremiumPage;", "getPremiumPageList", "()Ljava/util/List;", "getCount", "getItem", "Landroid/support/v4/app/Fragment;", "position", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: PremiumCarouselActivity.kt */
    public static final class PremiumAdapter extends FragmentPagerAdapter {
        private final int NUM_ITEMS = (this.premiumPageList.size() + 1);
        @NotNull
        private final List<PremiumPage> premiumPageList = CollectionsKt__CollectionsKt.listOf((Object[]) new PremiumPage[]{PremiumPage.Companion.getGeolocationPage(), PremiumPage.Companion.getWeatherAdaptationPage(), PremiumPage.Companion.getOpenWindowDetectionPage(), PremiumPage.Companion.getClimateReportPage()});

        public PremiumAdapter(@NotNull FragmentManager fragmentManager) {
            Intrinsics.checkParameterIsNotNull(fragmentManager, "fragmentManager");
            super(fragmentManager);
        }

        @NotNull
        public final List<PremiumPage> getPremiumPageList() {
            return this.premiumPageList;
        }

        @NotNull
        public Fragment getItem(int position) {
            return position == 0 ? Page.Companion.getPageCover() : Page.Companion.getPageInstance((PremiumPage) this.premiumPageList.get(position - 1));
        }

        public int getCount() {
            return this.NUM_ITEMS;
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u001d\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0002\u0010\tJ\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006HÆ\u0001J\b\u0010\u0012\u001a\u00020\u0006H\u0016J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u0006H\u0016R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000b¨\u0006\u001e"}, d2 = {"Lcom/tado/android/premium/PremiumCarouselActivity$PremiumPage;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "titleRes", "", "imageRes", "descriptionRes", "(III)V", "getDescriptionRes", "()I", "getImageRes", "getTitleRes", "component1", "component2", "component3", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "flags", "Companion", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: PremiumCarouselActivity.kt */
    public static final class PremiumPage implements Parcelable {
        @NotNull
        @JvmField
        public static final Creator<PremiumPage> CREATOR = new PremiumCarouselActivity$PremiumPage$Companion$CREATOR$1();
        public static final Companion Companion = new Companion();
        private final int descriptionRes;
        private final int imageRes;
        private final int titleRes;

        @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0006\u001a\u00020\u0005J\u0006\u0010\u0007\u001a\u00020\u0005J\u0006\u0010\b\u001a\u00020\u0005J\u0006\u0010\t\u001a\u00020\u0005R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/tado/android/premium/PremiumCarouselActivity$PremiumPage$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/tado/android/premium/PremiumCarouselActivity$PremiumPage;", "getClimateReportPage", "getGeolocationPage", "getOpenWindowDetectionPage", "getWeatherAdaptationPage", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
        /* compiled from: PremiumCarouselActivity.kt */
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final PremiumPage getGeolocationPage() {
                return new PremiumPage(C0676R.string.premiumUpgrade_featureCarousel_locationBasedControl_title, C0676R.drawable.premium_geofencing, C0676R.string.premiumUpgrade_featureCarousel_locationBasedControl_description);
            }

            @NotNull
            public final PremiumPage getWeatherAdaptationPage() {
                return new PremiumPage(C0676R.string.premiumUpgrade_featureCarousel_weatherAdapation_title, C0676R.drawable.premium_weather_adaptation, C0676R.string.premiumUpgrade_featureCarousel_weatherAdapation_description);
            }

            @NotNull
            public final PremiumPage getOpenWindowDetectionPage() {
                return new PremiumPage(C0676R.string.premiumUpgrade_featureCarousel_openWindowDetection_title, C0676R.drawable.premium_openwindowdetection, C0676R.string.premiumUpgrade_featureCarousel_openWindowDetection_description);
            }

            @NotNull
            public final PremiumPage getClimateReportPage() {
                return new PremiumPage(C0676R.string.premiumUpgrade_featureCarousel_climateReport_title, C0676R.drawable.premium_climate_report, C0676R.string.premiumUpgrade_featureCarousel_climateReport_description);
            }
        }

        @NotNull
        public static /* bridge */ /* synthetic */ PremiumPage copy$default(PremiumPage premiumPage, int i, int i2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = premiumPage.titleRes;
            }
            if ((i4 & 2) != 0) {
                i2 = premiumPage.imageRes;
            }
            if ((i4 & 4) != 0) {
                i3 = premiumPage.descriptionRes;
            }
            return premiumPage.copy(i, i2, i3);
        }

        public final int component1() {
            return this.titleRes;
        }

        public final int component2() {
            return this.imageRes;
        }

        public final int component3() {
            return this.descriptionRes;
        }

        @NotNull
        public final PremiumPage copy(int titleRes, int imageRes, int descriptionRes) {
            return new PremiumPage(titleRes, imageRes, descriptionRes);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                if (!(obj instanceof PremiumPage)) {
                    return false;
                }
                PremiumPage premiumPage = (PremiumPage) obj;
                if (!(this.titleRes == premiumPage.titleRes)) {
                    return false;
                }
                if (!(this.imageRes == premiumPage.imageRes)) {
                    return false;
                }
                if (!(this.descriptionRes == premiumPage.descriptionRes)) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            return (((this.titleRes * 31) + this.imageRes) * 31) + this.descriptionRes;
        }

        public String toString() {
            return "PremiumPage(titleRes=" + this.titleRes + ", imageRes=" + this.imageRes + ", descriptionRes=" + this.descriptionRes + ")";
        }

        public PremiumPage(int titleRes, int imageRes, int descriptionRes) {
            this.titleRes = titleRes;
            this.imageRes = imageRes;
            this.descriptionRes = descriptionRes;
        }

        public final int getDescriptionRes() {
            return this.descriptionRes;
        }

        public final int getImageRes() {
            return this.imageRes;
        }

        public final int getTitleRes() {
            return this.titleRes;
        }

        private PremiumPage(Parcel parcel) {
            this(parcel.readInt(), parcel.readInt(), parcel.readInt());
        }

        public void writeToParcel(@NotNull Parcel parcel, int flags) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            parcel.writeInt(this.titleRes);
            parcel.writeInt(this.imageRes);
            parcel.writeInt(this.descriptionRes);
        }

        public int describeContents() {
            return 0;
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\t\u0010\t\u001a\u00020\u0003HÂ\u0003J\t\u0010\n\u001a\u00020\u0003HÂ\u0003J\t\u0010\u000b\u001a\u00020\u0006HÂ\u0003J\t\u0010\f\u001a\u00020\u0006HÂ\u0003J1\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\u0010\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0003H\u0002J\u0006\u0010\u0014\u001a\u00020\u0006J\u0006\u0010\u0015\u001a\u00020\u0006J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0006HÖ\u0001R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/tado/android/premium/PremiumCarouselActivity$PriceTag;", "Ljava/io/Serializable;", "reducedPrice", "", "regularPrice", "currencyCode", "", "currencySymbol", "(FFLjava/lang/String;Ljava/lang/String;)V", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "getFormattedPrice", "price", "getFormattedReducedPrice", "getFormattedRegularPrice", "hashCode", "", "toString", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: PremiumCarouselActivity.kt */
    public static final class PriceTag implements Serializable {
        private final String currencyCode;
        private final String currencySymbol;
        private final float reducedPrice;
        private final float regularPrice;

        private final float component1() {
            return this.reducedPrice;
        }

        private final float component2() {
            return this.regularPrice;
        }

        private final String component3() {
            return this.currencyCode;
        }

        private final String component4() {
            return this.currencySymbol;
        }

        @NotNull
        public static /* bridge */ /* synthetic */ PriceTag copy$default(PriceTag priceTag, float f, float f2, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                f = priceTag.reducedPrice;
            }
            if ((i & 2) != 0) {
                f2 = priceTag.regularPrice;
            }
            if ((i & 4) != 0) {
                str = priceTag.currencyCode;
            }
            if ((i & 8) != 0) {
                str2 = priceTag.currencySymbol;
            }
            return priceTag.copy(f, f2, str, str2);
        }

        @NotNull
        public final PriceTag copy(float reducedPrice, float regularPrice, @NotNull String currencyCode, @NotNull String currencySymbol) {
            Intrinsics.checkParameterIsNotNull(currencyCode, "currencyCode");
            Intrinsics.checkParameterIsNotNull(currencySymbol, "currencySymbol");
            return new PriceTag(reducedPrice, regularPrice, currencyCode, currencySymbol);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r3) {
            /*
            r2 = this;
            if (r2 == r3) goto L_0x0030;
        L_0x0002:
            r0 = r3 instanceof com.tado.android.premium.PremiumCarouselActivity.PriceTag;
            if (r0 == 0) goto L_0x0032;
        L_0x0006:
            r3 = (com.tado.android.premium.PremiumCarouselActivity.PriceTag) r3;
            r0 = r2.reducedPrice;
            r1 = r3.reducedPrice;
            r0 = java.lang.Float.compare(r0, r1);
            if (r0 != 0) goto L_0x0032;
        L_0x0012:
            r0 = r2.regularPrice;
            r1 = r3.regularPrice;
            r0 = java.lang.Float.compare(r0, r1);
            if (r0 != 0) goto L_0x0032;
        L_0x001c:
            r0 = r2.currencyCode;
            r1 = r3.currencyCode;
            r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
            if (r0 == 0) goto L_0x0032;
        L_0x0026:
            r0 = r2.currencySymbol;
            r1 = r3.currencySymbol;
            r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
            if (r0 == 0) goto L_0x0032;
        L_0x0030:
            r0 = 1;
        L_0x0031:
            return r0;
        L_0x0032:
            r0 = 0;
            goto L_0x0031;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tado.android.premium.PremiumCarouselActivity.PriceTag.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            int i = 0;
            int floatToIntBits = ((Float.floatToIntBits(this.reducedPrice) * 31) + Float.floatToIntBits(this.regularPrice)) * 31;
            String str = this.currencyCode;
            int hashCode = ((str != null ? str.hashCode() : 0) + floatToIntBits) * 31;
            String str2 = this.currencySymbol;
            if (str2 != null) {
                i = str2.hashCode();
            }
            return hashCode + i;
        }

        public String toString() {
            return "PriceTag(reducedPrice=" + this.reducedPrice + ", regularPrice=" + this.regularPrice + ", currencyCode=" + this.currencyCode + ", currencySymbol=" + this.currencySymbol + ")";
        }

        public PriceTag(float reducedPrice, float regularPrice, @NotNull String currencyCode, @NotNull String currencySymbol) {
            Intrinsics.checkParameterIsNotNull(currencyCode, "currencyCode");
            Intrinsics.checkParameterIsNotNull(currencySymbol, "currencySymbol");
            this.reducedPrice = reducedPrice;
            this.regularPrice = regularPrice;
            this.currencyCode = currencyCode;
            this.currencySymbol = currencySymbol;
        }

        @NotNull
        public final String getFormattedReducedPrice() {
            return getFormattedPrice(this.reducedPrice);
        }

        @NotNull
        public final String getFormattedRegularPrice() {
            return getFormattedPrice(this.regularPrice);
        }

        private final String getFormattedPrice(float price) {
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            Intrinsics.checkExpressionValueIsNotNull(formatter, "formatter");
            formatter.setCurrency(Currency.getInstance(this.currencyCode));
            String format = formatter.format(Float.valueOf(price));
            Intrinsics.checkExpressionValueIsNotNull(format, "formatter.format(price)");
            return format;
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

    @Nullable
    public final String getAccountName() {
        return this.accountName;
    }

    public final void setAccountName(@Nullable String <set-?>) {
        this.accountName = <set-?>;
    }

    @Nullable
    public final PriceTag getPriceTag() {
        return this.priceTag;
    }

    public final void setPriceTag(@Nullable PriceTag <set-?>) {
        this.priceTag = <set-?>;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.premium_carousel_activity);
        ViewPager viewPager = (ViewPager) _$_findCachedViewById(C0676R.id.viewPager);
        Intrinsics.checkExpressionValueIsNotNull(viewPager, "viewPager");
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "supportFragmentManager");
        viewPager.setAdapter(new PremiumAdapter(supportFragmentManager));
        ((CircleIndicator) _$_findCachedViewById(C0676R.id.viewPagerIndicator)).setViewPager((ViewPager) _$_findCachedViewById(C0676R.id.viewPager));
        AsyncKt.doAsync$default(this, null, new PremiumCarouselActivity$onCreate$1(this), 1, null);
        Button button = (Button) _$_findCachedViewById(C0676R.id.premium_button);
        Intrinsics.checkExpressionValueIsNotNull(button, "premium_button");
        button.setEnabled(false);
        TadoApiService tadoRestService = RestServiceGenerator.getTadoRestService();
        Intrinsics.checkExpressionValueIsNotNull(tadoRestService, "RestServiceGenerator.getTadoRestService()");
        new HomeRepository(tadoRestService).requestHomeDetails(new PremiumCarouselActivity$onCreate$2(this, new ErrorAlertWithBackOnCancelPresenter(this), new PremiumCarouselActivity$onCreate$3(this)));
    }
}
