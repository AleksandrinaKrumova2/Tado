package com.tado.android.app;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.crashlytics.android.Crashlytics;
import com.tado.C0676R;
import com.tado.android.MainZoneActivity;
import com.tado.android.adapters.ZoneItemTouchHelperCallback;
import com.tado.android.adapters.ZoneOnClickListener;
import com.tado.android.controllers.LoggerController;
import com.tado.android.controllers.ZoneController;
import com.tado.android.controllers.ZoneListLoadedEvent;
import com.tado.android.dialogs.AlertChoiceDialogListener;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.entities.InstallationInfo;
import com.tado.android.entities.ZoneOrderInput;
import com.tado.android.menu.ActionItem;
import com.tado.android.menu.DrawerActionClickListener;
import com.tado.android.menu.DrawerItem;
import com.tado.android.menu.DrawerLogoClickListener;
import com.tado.android.menu.DrawerRecyclerViewAdapter;
import com.tado.android.menu.InstallationDrawerItem;
import com.tado.android.menu.ZoneItem;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.Constants;
import com.tado.android.utils.ResourceFactory;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;
import com.tado.android.views.OnDragListener;
import com.tado.android.views.loadingindicator.LoadingView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class NavigationDrawerFragment extends Fragment implements OnDragListener {
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";
    private static final String TAG = "NavigationDrawrFragment";
    private DrawerRecyclerViewAdapter adapter;
    private Call<Void> call;
    private boolean inDraggingOperation = false;
    private boolean inServerCall = false;
    private InstallationInfo installationInfo;
    private NavigationDrawerCallbacks mCallbacks;
    private int mCurrentSelectedPosition = 0;
    private List<DrawerItem> mDrawerItemsList;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private View mFragmentContainerView;
    private boolean mFromSavedInstanceState;
    private int mUnlockTaps = 0;
    private RecyclerView mZonesRecyclerView;
    private LoadingView progressBar;
    private boolean reorderingMode = false;
    private ZoneItem temporarySelectedZone = null;

    class C07221 implements ZoneOnClickListener {
        C07221() {
        }

        public void onClick(View v) {
            int position = NavigationDrawerFragment.this.mZonesRecyclerView.getChildAdapterPosition(v);
            if (position == -1) {
                Snitcher.start().toCrashlytics().log(6, NavigationDrawerFragment.TAG, "Trying to access a position that does not exist %s", v.toString());
                return;
            }
            ZoneItem item = (ZoneItem) NavigationDrawerFragment.this.mDrawerItemsList.get(position);
            if (NavigationDrawerFragment.this.inDraggingOperation || NavigationDrawerFragment.this.inServerCall) {
                NavigationDrawerFragment.this.temporarySelectedZone = item;
            } else {
                NavigationDrawerFragment.this.selectZone(item);
            }
        }
    }

    class C07232 implements DrawerActionClickListener {
        C07232() {
        }

        public void onClick(View v) {
            int position = NavigationDrawerFragment.this.mZonesRecyclerView.getChildAdapterPosition(v);
            if (position == -1) {
                Snitcher.start().toCrashlytics().log(6, NavigationDrawerFragment.TAG, "Trying to access a position that does not exist %s", v.toString());
                return;
            }
            ActionItem item = (ActionItem) NavigationDrawerFragment.this.mDrawerItemsList.get(position);
            if (item.isEnabled()) {
                item.performAction(NavigationDrawerFragment.this.getActivity());
                NavigationDrawerFragment.this.closeDrawer();
            }
        }
    }

    class C07243 implements DrawerLogoClickListener {
        C07243() {
        }

        public void onClick(View v) {
            NavigationDrawerFragment.this.mUnlockTaps = NavigationDrawerFragment.this.mUnlockTaps + 1;
            if (NavigationDrawerFragment.this.mUnlockTaps == 5 && NavigationDrawerFragment.this.getResources().getBoolean(C0676R.bool.logger)) {
                LoggerController.INSTANCE.showSendLogsDialog(NavigationDrawerFragment.this.getActivity(), NavigationDrawerFragment.this.mDrawerLayout, false);
                NavigationDrawerFragment.this.mUnlockTaps = 0;
            }
        }
    }

    class C07254 implements OnClickListener {
        C07254() {
        }

        public void onClick(View v) {
            NavigationDrawerFragment.this.mCallbacks.onInstallation();
            NavigationDrawerFragment.this.closeDrawer();
        }
    }

    class C07276 implements Runnable {
        C07276() {
        }

        public void run() {
            NavigationDrawerFragment.this.mDrawerToggle.syncState();
        }
    }

    class C07298 implements AlertChoiceDialogListener {
        C07298() {
        }

        public void OnOKClicked() {
            NavigationDrawerFragment.this.callUpdateZoneOrder(NavigationDrawerFragment.this.adapter.getItemOrder());
        }

        public void OnCancelClicked() {
            NavigationDrawerFragment.this.undoChanges();
            NavigationDrawerFragment.this.enableUI();
            NavigationDrawerFragment.this.inServerCall = false;
        }
    }

    public interface NavigationDrawerCallbacks {
        void onInstallation();

        void onSelectZoneId(int i);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            this.mFromSavedInstanceState = true;
        }
        selectItem(this.mCurrentSelectedPosition);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.mDrawerItemsList == null) {
            this.mDrawerItemsList = new ArrayList();
            if (this.installationInfo != null && (this.installationInfo.isStartInstallation() || this.installationInfo.hasUnfinishedInstallations())) {
                this.mDrawerItemsList.add(new InstallationDrawerItem(getString(ResourceFactory.getInstallationDrawerTitle(this.installationInfo.isStartInstallation())), this.installationInfo.isStartInstallation()));
            }
            this.mDrawerItemsList.addAll(Constants.DRAWER_ACTION_ITEMS);
        }
        this.mZonesRecyclerView = (RecyclerView) inflater.inflate(C0676R.layout.fragment_navigation_drawer, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(1);
        this.mZonesRecyclerView.setLayoutManager(linearLayoutManager);
        this.adapter = new DrawerRecyclerViewAdapter(this.mDrawerItemsList, new C07221(), new C07232(), new C07243(), new C07254());
        this.mZonesRecyclerView.setAdapter(this.adapter);
        new ItemTouchHelper(new ZoneItemTouchHelperCallback((DrawerRecyclerViewAdapter) this.mZonesRecyclerView.getAdapter(), this)).attachToRecyclerView(this.mZonesRecyclerView);
        this.mZonesRecyclerView.setBackgroundColor(-1);
        return this.mZonesRecyclerView;
    }

    public boolean isDrawerOpen() {
        return this.mDrawerLayout != null && this.mDrawerLayout.isDrawerOpen(this.mFragmentContainerView);
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        this.mFragmentContainerView = getActivity().findViewById(fragmentId);
        this.mDrawerLayout = drawerLayout;
        this.mDrawerToggle = new ActionBarDrawerToggle(getActivity(), this.mDrawerLayout, C0676R.string.navigation_drawer_open, C0676R.string.navigation_drawer_close) {
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (NavigationDrawerFragment.this.isAdded()) {
                    ((MainZoneActivity) NavigationDrawerFragment.this.getActivity()).onDrawerClosed();
                    NavigationDrawerFragment.this.getActivity().supportInvalidateOptionsMenu();
                }
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (NavigationDrawerFragment.this.isAdded()) {
                    ((MainZoneActivity) NavigationDrawerFragment.this.getActivity()).onDrawerOpened();
                    NavigationDrawerFragment.this.getActivity().supportInvalidateOptionsMenu();
                }
            }

            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (NavigationDrawerFragment.this.isAdded()) {
                    ((MainZoneActivity) NavigationDrawerFragment.this.getActivity()).onDrawerSlide();
                }
            }
        };
        this.mDrawerLayout.post(new C07276());
        this.mDrawerLayout.addDrawerListener(this.mDrawerToggle);
        this.progressBar = (LoadingView) getActivity().findViewById(C0676R.id.zones_progress_indicator);
    }

    private void selectItem(int position) {
        this.mCurrentSelectedPosition = position;
        if (this.mDrawerLayout != null) {
            this.mDrawerLayout.closeDrawer(this.mFragmentContainerView);
        }
    }

    private void selectZone(ZoneItem item) {
        this.mCallbacks.onSelectZoneId(item.getZoneId());
        ZoneController.INSTANCE.selectZone(item.getZoneId());
        closeDrawer();
    }

    public void onDetach() {
        super.onDetach();
        this.mCallbacks = null;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, this.mCurrentSelectedPosition);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (this.mDrawerLayout != null && isDrawerOpen()) {
            inflater.inflate(C0676R.menu.global, menu);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (this.mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void registerCallback(NavigationDrawerCallbacks listener) {
        if (listener != null) {
            this.mCallbacks = listener;
        } else {
            Crashlytics.log("Presenter must implement NavigationDrawerCallbacks.");
            throw new ClassCastException("Presenter must implement NavigationDrawerCallbacks.");
        }
    }

    public void updateInstallationInfo(InstallationInfo installationInfo) {
        if (this.mZonesRecyclerView != null && this.mZonesRecyclerView.getAdapter() != null) {
            ((DrawerRecyclerViewAdapter) this.mZonesRecyclerView.getAdapter()).updateInstallationInfoDrawerItem(installationInfo);
        }
    }

    public void setInstallationInfo(InstallationInfo installationInfo) {
        this.installationInfo = installationInfo;
        if (this.mZonesRecyclerView != null && this.mZonesRecyclerView.getAdapter() != null) {
            ((DrawerRecyclerViewAdapter) this.mZonesRecyclerView.getAdapter()).setInstallationInfo(installationInfo);
        }
    }

    public void onDragStarted() {
        this.inDraggingOperation = true;
        disableUI();
    }

    public void onDragFinished() {
        this.inDraggingOperation = false;
        if (this.adapter.hasModifications()) {
            if (!(this.call == null || this.call.isCanceled())) {
                this.call.cancel();
            }
            callUpdateZoneOrder(this.adapter.getItemOrder());
            return;
        }
        enableUI();
    }

    private void onCallFinished(boolean successful) {
        if (successful) {
            if (isAdded()) {
                this.progressBar.finish();
                if (!this.inDraggingOperation) {
                    enableUI();
                    unlockDrawer();
                    TadoApplication.getBus().post(new ZoneListLoadedEvent());
                    if (this.temporarySelectedZone != null) {
                        selectZone(this.temporarySelectedZone);
                        this.temporarySelectedZone = null;
                    }
                }
            }
        } else if (!isAdded()) {
            undoChanges();
            enableUI();
        } else if (!this.inDraggingOperation) {
            updateZones(ZoneController.INSTANCE.getZoneItems());
            this.progressBar.error();
            showRetryDialog();
        }
        this.inServerCall = false;
    }

    private void callUpdateZoneOrder(final List<ZoneOrderInput> zoneOrderInput) {
        this.progressBar.start();
        this.inServerCall = true;
        this.call = RestServiceGenerator.getTadoRestService().putZoneOrder(UserConfig.getHomeId(), zoneOrderInput, RestServiceGenerator.getCredentialsMap());
        this.call.enqueue(new TadoCallback<Void>() {
            public void onResponse(Call<Void> call, Response<Void> response) {
                super.onResponse(call, response);
                if (response.isSuccessful() && !call.isCanceled()) {
                    ZoneController.INSTANCE.updateZoneListOrder(zoneOrderInput);
                }
                NavigationDrawerFragment.this.onCallFinished(response.isSuccessful());
            }

            public void onFailure(Call<Void> call, Throwable t) {
                super.onFailure(call, t);
                if (!call.isCanceled()) {
                    NavigationDrawerFragment.this.onCallFinished(false);
                }
            }
        });
    }

    private void disableUI() {
        this.reorderingMode = true;
        this.adapter.setEnabledActions(false);
        lockDrawerOpen();
    }

    private void enableUI() {
        this.reorderingMode = false;
        this.adapter.setEnabledActions(true);
        unlockDrawer();
    }

    private void showRetryDialog() {
        AlertDialogs.showCancelRetryAlert(getString(C0676R.string.errors_sendingFailed_title), getString(C0676R.string.errors_sendingFailed_message), getString(C0676R.string.errors_sendingFailed_retryButton), getString(C0676R.string.errors_sendingFailed_cancelButton), getActivity(), new C07298());
    }

    private void undoChanges() {
        this.adapter.undoChanges();
    }

    public void openDrawer() {
        this.mDrawerLayout.openDrawer(this.mFragmentContainerView);
    }

    public void closeDrawer() {
        if (this.mDrawerLayout.getDrawerLockMode(3) != 2) {
            this.mDrawerLayout.closeDrawer(this.mFragmentContainerView);
        }
    }

    public void closeDrawerWithoutAnimation() {
        this.mDrawerLayout.closeDrawer(this.mFragmentContainerView, false);
    }

    public void updateZones(List<DrawerItem> zones) {
        if (!this.reorderingMode) {
            this.mDrawerItemsList = zones;
            if (this.mZonesRecyclerView != null && this.mZonesRecyclerView.getAdapter() != null) {
                ((DrawerRecyclerViewAdapter) this.mZonesRecyclerView.getAdapter()).addItems(this.mDrawerItemsList);
            }
        }
    }

    public void lockDrawerOpen() {
        this.mDrawerLayout.setDrawerLockMode(2);
    }

    public void lockDrawerClosed() {
        this.mDrawerLayout.setDrawerLockMode(1);
    }

    public void unlockDrawer() {
        this.mDrawerLayout.setDrawerLockMode(0);
    }

    public void setBadgeVisibility(boolean visibility) {
        ((DrawerRecyclerViewAdapter) this.mZonesRecyclerView.getAdapter()).setBadgeVisibility(C0676R.string.menu_settingsButton, visibility);
    }
}
