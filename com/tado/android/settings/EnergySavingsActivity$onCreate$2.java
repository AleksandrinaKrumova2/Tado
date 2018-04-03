package com.tado.android.settings;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J \u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0006H\u0016¨\u0006\r"}, d2 = {"com/tado/android/settings/EnergySavingsActivity$onCreate$2", "Landroid/support/v4/view/ViewPager$OnPageChangeListener;", "(Lcom/tado/android/settings/EnergySavingsActivity;)V", "onPageScrollStateChanged", "", "state", "", "onPageScrolled", "position", "positionOffset", "", "positionOffsetPixels", "onPageSelected", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: EnergySavingsActivity.kt */
public final class EnergySavingsActivity$onCreate$2 implements OnPageChangeListener {
    final /* synthetic */ EnergySavingsActivity this$0;

    EnergySavingsActivity$onCreate$2(EnergySavingsActivity $outer) {
        this.this$0 = $outer;
    }

    public void onPageScrollStateChanged(int state) {
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    public void onPageSelected(int position) {
        this.this$0.esrPresenter.showReportForPosition(position);
    }
}
