package com.tado.android.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.tado.android.PromoPageFragment;

public class PromoPagerAdapter extends FragmentPagerAdapter {
    private int promoPageCount = 3;

    public PromoPagerAdapter(FragmentManager fm, int promoPageCount) {
        super(fm);
        this.promoPageCount = promoPageCount;
    }

    public Fragment getItem(int position) {
        return PromoPageFragment.create(position % this.promoPageCount);
    }

    public int getCount() {
        return Integer.MAX_VALUE;
    }
}
