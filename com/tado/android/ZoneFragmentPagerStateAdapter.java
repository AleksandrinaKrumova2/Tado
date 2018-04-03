package com.tado.android;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import com.tado.android.mvp.presenters.WeatherPresenter;
import com.tado.android.rest.model.Zone;
import com.tado.android.utils.Snitcher;
import java.util.List;

public class ZoneFragmentPagerStateAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = "ZoneFragmentPagerStateAdapter";
    List<Zone> items;
    private WeatherPresenter weatherPresenter;

    public ZoneFragmentPagerStateAdapter(FragmentManager fm, List<Zone> zoneList, WeatherPresenter presenter, ViewPager mZoneFragmentPager) {
        super(fm);
        this.items = zoneList;
        this.weatherPresenter = presenter;
    }

    public ZoneFragment getItem(int position) {
        Snitcher.start().log(3, TAG, "getItem %d", Integer.valueOf(position));
        return ZoneFragment.newInstance(((Zone) this.items.get(position)).getId(), this.weatherPresenter);
    }

    public int getCount() {
        return this.items.size();
    }

    public int getPositionForZoneId(int zoneId) {
        int position = 0;
        for (Zone zone : this.items) {
            if (zone.getId() == zoneId) {
                return position;
            }
            position++;
        }
        Snitcher.start().toCrashlytics().log(6, TAG, "zoneId " + zoneId + " is not in the zone items of the zone fragment pager adapter", new Object[0]);
        return 0;
    }

    public void addItems(List<Zone> zoneItems) {
        this.items = zoneItems;
        notifyDataSetChanged();
    }

    public int getItemPosition(Object object) {
        return -2;
    }
}
