package com.tado.android.settings.mainsettings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v14.preference.PreferenceFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.preference.Preference;
import android.support.v7.preference.Preference.OnPreferenceClickListener;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceScreen;
import com.squareup.otto.Subscribe;
import com.tado.C0676R;
import com.tado.android.InfoActivity;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.app.TadoApplication;
import com.tado.android.controllers.ZoneController;
import com.tado.android.controllers.ZoneListLoadedEvent;
import com.tado.android.menu.DrawerItem;
import com.tado.android.menu.ZoneItem;
import com.tado.android.settings.ZonePreference;
import com.tado.android.settings.appsettings.AppSettingsActivity;
import com.tado.android.settings.homedetails.HomeDetailsPreferenceActivity;
import com.tado.android.settings.notifications.NotificationPreferenceActivity;
import com.tado.android.settings.users.PeoplePreferenceActivity;
import com.tado.android.settings.zonesettings.ZonePreferenceActivity;
import com.tado.android.utils.ResourceFactory;
import com.tado.android.utils.Snitcher;
import java.util.List;

public class SettingsPreferenceFragment extends PreferenceFragment {
    private static final String TAG = "SettingsPreferenceFragment";
    private PreferenceCategory zonesCategory;

    class C11211 implements OnPreferenceClickListener {
        C11211() {
        }

        public boolean onPreferenceClick(Preference preference) {
            SignOutDialog dialog = new SignOutDialog();
            if (SettingsPreferenceFragment.this.getActivity() != null) {
                dialog.show(((FragmentActivity) SettingsPreferenceFragment.this.getActivity()).getSupportFragmentManager(), "signout");
            }
            return true;
        }
    }

    public static SettingsPreferenceFragment newInstance() {
        return new SettingsPreferenceFragment();
    }

    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        createPreferences();
    }

    private void createPreferences() {
        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(getActivity());
        String name = "";
        if (ZoneController.INSTANCE.getZoneList().size() != 0) {
            name = getString(C0676R.string.settings_zonesSectionTitle);
        }
        this.zonesCategory = createZonesCategory(name);
        screen.addPreference(this.zonesCategory);
        createZonesPreferences(this.zonesCategory);
        populateHome(screen);
        populateApp(screen);
        screen.addPreference(createSignOutButton());
        setPreferenceScreen(screen);
    }

    public void onResume() {
        super.onResume();
        TadoApplication.getBus().register(this);
        ZoneController.INSTANCE.callGetZoneList();
        AnalyticsHelper.trackPageView(getActivity(), Screen.SETTINGS);
    }

    public void onPause() {
        TadoApplication.getBus().unregister(this);
        super.onPause();
    }

    private PreferenceCategory createZonesCategory(String title) {
        PreferenceCategory zoneCategory = new PreferenceCategory(getActivity());
        zoneCategory.setKey("preference_zones_category");
        zoneCategory.setTitle((CharSequence) title);
        return zoneCategory;
    }

    private void populateHome(PreferenceScreen screen) {
        PreferenceCategory emptyCategory = new PreferenceCategory(getActivity());
        emptyCategory.setTitle((int) C0676R.string.settings_homeSectionTitle);
        screen.addPreference(emptyCategory);
        screen.addPreference(createLBCPreference());
        screen.addPreference(createHomeDetailsPreference());
        screen.addPreference(createPeoplePreference());
    }

    private void populateApp(PreferenceScreen screen) {
        if (shouldAddInfo()) {
            PreferenceCategory appCategory = new PreferenceCategory(getActivity());
            appCategory.setTitle((int) C0676R.string.settings_appSectionTitle);
            screen.addPreference(appCategory);
            screen.addPreference(createNotificationPreference());
            screen.addPreference(createInfoPreference());
        }
    }

    private boolean shouldAddInfo() {
        return getResources().getBoolean(C0676R.bool.info);
    }

    private Preference createInfoPreference() {
        Preference infoPreference = new Preference(getActivity());
        infoPreference.setIntent(new Intent(getActivity(), InfoActivity.class));
        infoPreference.setTitle((int) C0676R.string.settings_infoButton);
        infoPreference.setPersistent(false);
        infoPreference.setIcon(ContextCompat.getDrawable(getActivity(), C0676R.drawable.info));
        return infoPreference;
    }

    private Preference createNotificationPreference() {
        Preference notificationPreferences = new Preference(getActivity());
        notificationPreferences.setIntent(new Intent(getActivity(), NotificationPreferenceActivity.class));
        notificationPreferences.setTitle((int) C0676R.string.settings_notificationSettingsButton);
        notificationPreferences.setPersistent(false);
        notificationPreferences.setIcon(ContextCompat.getDrawable(getActivity(), C0676R.drawable.ic_notification));
        return notificationPreferences;
    }

    private void createZonesPreferences(PreferenceCategory parent) {
        parent.removeAll();
        parent.setTitle(getString(C0676R.string.settings_zonesSectionTitle));
        List<DrawerItem> zoneItems = ZoneController.INSTANCE.getZoneItems();
        for (int i = 0; i < zoneItems.size(); i++) {
            ZoneItem item = (ZoneItem) zoneItems.get(i);
            ZonePreference zonePreference = new ZonePreference(getActivity());
            zonePreference.setKey(item.getZoneType().name() + "." + item.getZoneId());
            zonePreference.setPersistent(false);
            zonePreference.setIcon(ResourceFactory.getTintedVectorSupportDrawable(getActivity(), item.getZoneImageResource(), C0676R.color.app_bar_settings_background));
            zonePreference.setTitle(item.getZoneName());
            zonePreference.setHasBadge(item.hasBadge());
            Snitcher.start().log(3, TAG, "Add zone %s", item.getZoneName());
            Intent intent = new Intent(getActivity(), ZonePreferenceActivity.class);
            intent.putExtra(ZonePreferenceActivity.KEY_ZONE_ID, item.getZoneId());
            intent.setFlags(536870912);
            zonePreference.setIntent(intent);
            parent.addPreference(zonePreference);
        }
        if (zoneItems.isEmpty() && getPreferenceScreen() != null) {
            getPreferenceScreen().removePreference(parent);
        }
    }

    private Preference createHomeDetailsPreference() {
        Preference appPreference = new Preference(getActivity());
        appPreference.setIntent(new Intent(getActivity(), HomeDetailsPreferenceActivity.class));
        appPreference.setTitle((int) C0676R.string.settings_homeDetailsButton);
        appPreference.setPersistent(false);
        appPreference.setIcon(ResourceFactory.getTintedDrawable(getActivity(), C0676R.drawable.ic_homedetails, C0676R.color.app_bar_settings_background));
        return appPreference;
    }

    private Preference createLBCPreference() {
        Preference appPreference = new Preference(getActivity());
        appPreference.setIntent(new Intent(getActivity(), AppSettingsActivity.class));
        appPreference.setTitle((int) C0676R.string.settings_locationBasedControl_title);
        appPreference.setPersistent(false);
        appPreference.setIcon(ResourceFactory.getTintedDrawable(getActivity(), C0676R.drawable.ic_location_based_control, C0676R.color.app_bar_settings_background));
        return appPreference;
    }

    @NonNull
    private Preference createSignOutButton() {
        Preference signOut = new Preference(getActivity());
        signOut.setPersistent(false);
        signOut.setTitle((int) C0676R.string.settings_signOutButton);
        signOut.setLayoutResource(C0676R.layout.sign_out_layout);
        signOut.setOnPreferenceClickListener(new C11211());
        return signOut;
    }

    private Preference createPeoplePreference() {
        Preference peoplePreference = new Preference(getActivity());
        peoplePreference.setIntent(new Intent(getActivity(), PeoplePreferenceActivity.class));
        peoplePreference.setTitle((int) C0676R.string.settings_peopleButton);
        peoplePreference.setPersistent(false);
        peoplePreference.setIcon(ResourceFactory.getTintedDrawable(getActivity(), C0676R.drawable.ic_people, C0676R.color.app_bar_settings_background));
        return peoplePreference;
    }

    @Subscribe
    public void getZoneListLoadedEvent(ZoneListLoadedEvent event) {
        createZonesPreferences(this.zonesCategory);
    }
}
