package com.tado.android.menu;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.tado.C0676R;
import com.tado.android.adapters.ItemTouchInterfaceAdapter;
import com.tado.android.adapters.ZoneOnClickListener;
import com.tado.android.adapters.ZoneViewHolder;
import com.tado.android.app.TadoApplication;
import com.tado.android.controllers.RateAppUtil;
import com.tado.android.controllers.ZoneController;
import com.tado.android.demo.DemoUtils;
import com.tado.android.entities.InstallationInfo;
import com.tado.android.entities.ZoneOrderInput;
import com.tado.android.menu.DrawerItem.DrawerItemEnum;
import com.tado.android.rest.model.HomeInfo.LicenseEnum;
import com.tado.android.rest.model.HomeInfo.PartnerEnum;
import com.tado.android.utils.Constants;
import com.tado.android.utils.ResourceFactory;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class DrawerRecyclerViewAdapter extends Adapter<ViewHolder> implements ItemTouchInterfaceAdapter {
    private InstallationInfo installationInfo;
    private DrawerActionClickListener mDrawerActionOnClickListener;
    private OnClickListener mDrawerInstallationClickListener;
    private DrawerLogoClickListener mDrawerLogoClickListener;
    private List<DrawerItem> mItems = new ArrayList();
    private ZoneOnClickListener mZoneOnClickListener;
    private List<Pair<Integer, Integer>> moveOperations = new ArrayList();
    private boolean showHiddenActionItems = false;

    class C10331 implements Comparator<DrawerItem> {
        C10331() {
        }

        public int compare(DrawerItem o1, DrawerItem o2) {
            return o1.getItemType().ordinal() - o2.getItemType().ordinal();
        }
    }

    public DrawerRecyclerViewAdapter(List<DrawerItem> items, ZoneOnClickListener listener, DrawerActionClickListener drawerActionClickListener, DrawerLogoClickListener drawerLogoClickListener, OnClickListener drawerInstallationClickListener) {
        this.mZoneOnClickListener = listener;
        this.mDrawerActionOnClickListener = drawerActionClickListener;
        this.mDrawerLogoClickListener = drawerLogoClickListener;
        this.mDrawerInstallationClickListener = drawerInstallationClickListener;
        addItems(items);
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == DrawerItemEnum.ACTION_ITEM.ordinal()) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(C0676R.layout.drawer_action_layout, parent, false);
            itemView.setOnClickListener(this.mDrawerActionOnClickListener);
            return new DrawerActionViewHolder(itemView);
        } else if (viewType == DrawerItemEnum.LOGO_ITEM.ordinal()) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(C0676R.layout.drawer_logo_item, parent, false);
            itemView.setOnClickListener(this.mDrawerLogoClickListener);
            return new DrawerLogoViewHolder(itemView);
        } else if (viewType == DrawerItemEnum.ZONE_ITEM.ordinal()) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(C0676R.layout.cooling_drawer_room_list_item, parent, false);
            itemView.setOnClickListener(this.mZoneOnClickListener);
            return new ZoneViewHolder(itemView);
        } else if (viewType == DrawerItemEnum.PREMIUM_ITEM.ordinal()) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(C0676R.layout.drawer_premium_layout, parent, false);
            itemView.setOnClickListener(this.mDrawerActionOnClickListener);
            return new DrawerPremiumViewHolder(itemView);
        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(C0676R.layout.drawer_installation_list_item, parent, false);
            itemView.setOnClickListener(this.mDrawerInstallationClickListener);
            return new DrawerInstallationViewHolder(itemView);
        }
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        DrawerItem drawerItem = (DrawerItem) this.mItems.get(position);
        switch (drawerItem.getItemType()) {
            case ZONE_ITEM:
                ((ZoneViewHolder) holder).bind((ZoneItem) drawerItem);
                return;
            case INSTALLATION_ITEM:
                ((DrawerInstallationViewHolder) holder).bind((InstallationDrawerItem) drawerItem);
                return;
            case ACTION_ITEM:
                ((DrawerActionViewHolder) holder).bind((ActionItem) drawerItem);
                return;
            case LOGO_ITEM:
                ((DrawerLogoViewHolder) holder).bind((DrawerLogoItem) drawerItem);
                return;
            default:
                return;
        }
    }

    public int getItemCount() {
        return this.mItems == null ? 0 : this.mItems.size();
    }

    public int getItemViewType(int position) {
        return ((DrawerItem) this.mItems.get(position)).getItemType().ordinal();
    }

    public void addItems(List<DrawerItem> items) {
        clean(this.mItems);
        this.mItems = items;
        addInstallationInfoDrawerItem();
        this.mItems.addAll(Constants.DRAWER_ACTION_ITEMS);
        processActionItems(this.mItems);
        addLogoItem(this.mItems);
        sortItems();
        notifyDataSetChanged();
    }

    private void clean(List<DrawerItem> items) {
        Iterator<DrawerItem> itemIterator = items.iterator();
        while (itemIterator.hasNext()) {
            if (((DrawerItem) itemIterator.next()).getItemType() != DrawerItemEnum.ZONE_ITEM) {
                itemIterator.remove();
            }
        }
    }

    private void processActionItems(List<DrawerItem> items) {
        Iterator<DrawerItem> itemIterator = items.iterator();
        while (itemIterator.hasNext()) {
            DrawerItem item = (DrawerItem) itemIterator.next();
            if (item.getItemType() == DrawerItemEnum.PREMIUM_ITEM && UserConfig.getLicense() != LicenseEnum.NON_PREMIUM) {
                itemIterator.remove();
            }
            if (item.getItemType() == DrawerItemEnum.ACTION_ITEM) {
                if (((ActionItem) item).getActionName().equalsIgnoreCase(TadoApplication.getTadoAppContext().getString(C0676R.string.menu_energySavingsReportButton)) && !ZoneController.INSTANCE.isHeatingHome()) {
                    itemIterator.remove();
                } else if (((ActionItem) item).getActionName().equalsIgnoreCase(TadoApplication.getTadoAppContext().getString(C0676R.string.menu_appsBetaButton))) {
                    if (!isRequestForBetaAvailable() || DemoUtils.isInDemoMode()) {
                        itemIterator.remove();
                    }
                } else if (((ActionItem) item).getActionName().equalsIgnoreCase(TadoApplication.getTadoAppContext().getString(C0676R.string.menu_supportButton))) {
                    if (shouldRemoveItem(C0676R.bool.helpCenter)) {
                        itemIterator.remove();
                    }
                } else if (((ActionItem) item).getActionName().equalsIgnoreCase(TadoApplication.getTadoAppContext().getString(C0676R.string.menu_referralButton))) {
                    if (shouldRemoveItem(C0676R.bool.referralProgram) || DemoUtils.isInDemoMode()) {
                        itemIterator.remove();
                    }
                } else if (((ActionItem) item).getActionName().equalsIgnoreCase(TadoApplication.getTadoAppContext().getString(C0676R.string.menu_enjoyingTadoButton))) {
                    if (!new RateAppUtil().showRateAppItem()) {
                        itemIterator.remove();
                    }
                } else if (((ActionItem) item).getActionName().equalsIgnoreCase(TadoApplication.getTadoAppContext().getString(C0676R.string.menu_addDeviceButton))) {
                    if (shouldRemoveItem(C0676R.bool.addDevice)) {
                        itemIterator.remove();
                    }
                } else if (((ActionItem) item).getActionName().equalsIgnoreCase(TadoApplication.getTadoAppContext().getString(C0676R.string.menu_repairServicesButton))) {
                    if (!shouldShowRepairServices()) {
                        itemIterator.remove();
                    }
                } else if (((ActionItem) item).getActionName().equalsIgnoreCase("Debug Settings") && shouldRemoveItem(C0676R.bool.debugMenu)) {
                    itemIterator.remove();
                }
            }
        }
    }

    private boolean shouldShowRepairServices() {
        List<String> whiteListedCountries = Arrays.asList(new String[]{"GBR", "DEU"});
        List<PartnerEnum> whiteListedPartners = Arrays.asList(new PartnerEnum[]{PartnerEnum.NONE, PartnerEnum.HOMESERVE});
        if (!shouldRemoveItem(C0676R.bool.repairServices) && whiteListedCountries.contains(UserConfig.getHomeCountry()) && whiteListedPartners.contains(UserConfig.getPartner())) {
            return true;
        }
        return false;
    }

    private boolean shouldRemoveItem(int addDevice) {
        return !TadoApplication.getTadoAppContext().getResources().getBoolean(addDevice);
    }

    private boolean isRequestForBetaAvailable() {
        String language = Locale.getDefault().getLanguage();
        return !shouldRemoveItem(C0676R.bool.beta_program) && UserConfig.getPartner() == PartnerEnum.NONE && ("en".equalsIgnoreCase(language) || "de".equalsIgnoreCase(language));
    }

    private void addLogoItem(List<DrawerItem> items) {
        DrawerLogoItem logoItem = new DrawerLogoItem();
        Context context = TadoApplication.getTadoAppContext();
        String version = context.getText(C0676R.string.version) + " " + Util.getAppVersion(context);
        try {
            version = version + "\n(" + context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode + ")";
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        logoItem.setVersion(version);
        items.add(logoItem);
    }

    public void updateInstallationInfoDrawerItem(InstallationInfo installationInfo) {
        this.installationInfo = installationInfo;
        if (installationInfo != null && this.mItems != null) {
            for (DrawerItem item : this.mItems) {
                if (DrawerItemEnum.INSTALLATION_ITEM == item.getItemType()) {
                    ((InstallationDrawerItem) item).setTitle(TadoApplication.getTadoAppContext().getString(ResourceFactory.getInstallationDrawerTitle(installationInfo.isStartInstallation())));
                    ((InstallationDrawerItem) item).setStartNewInstallation(installationInfo.isStartInstallation());
                    break;
                }
            }
        }
        notifyDataSetChanged();
    }

    public void setInstallationInfo(InstallationInfo installationInfo) {
        this.installationInfo = installationInfo;
        addInstallationInfoDrawerItem();
        sortItems();
        notifyDataSetChanged();
    }

    private void addInstallationInfoDrawerItem() {
        if (this.installationInfo == null) {
            return;
        }
        if (this.installationInfo.hasUnfinishedInstallations() || this.installationInfo.isStartInstallation()) {
            this.mItems.add(new InstallationDrawerItem(TadoApplication.getTadoAppContext().getString(ResourceFactory.getInstallationDrawerTitle(this.installationInfo.isStartInstallation())), this.installationInfo.isStartInstallation()));
        }
    }

    private void sortItems() {
        Collections.sort(this.mItems, new C10331());
    }

    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition == toPosition) {
            return false;
        }
        this.moveOperations.add(0, new Pair(Integer.valueOf(fromPosition), Integer.valueOf(toPosition)));
        int i;
        if (fromPosition > toPosition) {
            for (i = fromPosition; i > toPosition; i--) {
                Collections.swap(this.mItems, i, i - 1);
            }
        } else {
            for (i = fromPosition; i < toPosition; i++) {
                Collections.swap(this.mItems, i, i + 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    public List<ZoneOrderInput> getItemOrder() {
        List<ZoneOrderInput> zoneOrder = new ArrayList(this.mItems.size());
        for (DrawerItem item : this.mItems) {
            if (item instanceof ZoneItem) {
                zoneOrder.add(new ZoneOrderInput(((ZoneItem) item).getZoneId()));
            }
        }
        return zoneOrder;
    }

    public void setEnabledActions(boolean enabledActions) {
        for (int i = 0; i < this.mItems.size(); i++) {
            if (((DrawerItem) this.mItems.get(i)).getItemType() == DrawerItemEnum.ACTION_ITEM) {
                ((ActionItem) this.mItems.get(i)).setEnabled(enabledActions);
                notifyItemChanged(i);
            }
        }
        if (enabledActions) {
            this.moveOperations.clear();
        }
    }

    public void undoChanges() {
        for (Pair<Integer, Integer> op : new ArrayList(this.moveOperations)) {
            onItemMove(((Integer) op.second).intValue(), ((Integer) op.first).intValue());
        }
        this.moveOperations.clear();
    }

    public boolean hasModifications() {
        return this.moveOperations.size() > 0;
    }

    public void setBadgeVisibility(int itemId, boolean visibility) {
        for (int i = 0; i < this.mItems.size(); i++) {
            DrawerItem item = (DrawerItem) this.mItems.get(i);
            if (item.getItemType() == DrawerItemEnum.ACTION_ITEM && ((ActionItem) item).getActionName().equals(TadoApplication.getTadoAppContext().getResources().getString(itemId)) && item.updateBadgeVisibility(visibility)) {
                notifyItemChanged(i);
            }
        }
    }
}
