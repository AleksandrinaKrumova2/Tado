package com.tado.android.onboarding;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.tado.android.onboarding.data.TutorialDataSourceRepository.TutorialDataSourceRepositoryEnum;
import com.tado.android.onboarding.data.model.FeatureOrderItem;
import com.tado.android.onboarding.data.model.FeaturesOrder;

public class FeaturesCarouselPagerAdapter extends FragmentPagerAdapter {
    private FeaturesOrder order;
    private TutorialDataSourceRepositoryEnum tutorialDataSourceEnum;

    public FeaturesCarouselPagerAdapter(FragmentManager fragmentManager, TutorialDataSourceRepositoryEnum tutorialDataSourceEnum) {
        super(fragmentManager);
        this.tutorialDataSourceEnum = tutorialDataSourceEnum;
    }

    public Fragment getItem(int position) {
        return OnboardingPageFragment.getInstance(((FeatureOrderItem) this.order.getFeatureOrder().get(position)).getKey(), this.tutorialDataSourceEnum);
    }

    public int getCount() {
        return this.order.getFeatureOrder().size();
    }

    public void setOrder(FeaturesOrder order) {
        this.order = order;
        notifyDataSetChanged();
    }
}
