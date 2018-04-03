package com.tado.android.installation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tado.C0676R;
import com.tado.android.entities.ServerError;
import com.tado.android.installation.unfinished.UnfinishedInstallationDetailsActivity;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.installation.AcInstallation.StateEnum;
import retrofit2.Call;

public class ACInstallationBaseActivity extends InstallationBaseActivity {
    @Nullable
    @BindView(2131296405)
    protected ImageView centerImage;
    @Nullable
    @BindView(2131296406)
    protected ImageView centerImageOverlay;
    private ProgressDialog mProgress;
    @Nullable
    @BindView(2131296870)
    protected Button proceedButton;
    @Nullable
    @BindView(2131297075)
    protected TextView textView;
    @Nullable
    @BindView(2131297146)
    protected TextView titleBarTextview;
    @Nullable
    @BindView(2131297148)
    protected TextView titleTemplateTextview;

    protected void onResume() {
        super.onResume();
        try {
            setSupportActionBar((Toolbar) findViewById(C0676R.id.toolbar));
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        } catch (Exception e) {
        }
    }

    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind((Activity) this);
        if (this.proceedButton != null) {
            this.proceedButton.setText(C0676R.string.next);
        }
        overridePendingTransition(C0676R.anim.slide_in_left, C0676R.anim.slide_out_left);
    }

    public void proceedClick(View view) {
        view.setEnabled(false);
    }

    public void handleServerError(@Nullable ServerError serverError, @NonNull Activity activity, Call<?> call, int responseCode, @NonNull TadoCallback<?> listener) {
        if (serverError != null) {
            InstallationProcessController.handleError(activity, serverError, (Call) call, responseCode);
        } else {
            InstallationProcessController.showConnectionErrorRetrofit(activity, call, listener);
        }
    }

    public void showLoadingUI() {
        if (this.proceedButton != null) {
            this.proceedButton.setEnabled(false);
        }
        if (this.mProgress == null) {
            this.mProgress = new ProgressDialog(this);
            this.mProgress.setCancelable(false);
            this.mProgress.setMessage(getResources().getString(C0676R.string.loading_title));
            this.mProgress.setCanceledOnTouchOutside(false);
        }
        this.mProgress.show();
    }

    public void dismissLoadingUI() {
        if (this.mProgress != null && this.mProgress.isShowing()) {
            this.mProgress.dismiss();
        }
        if (this.proceedButton != null) {
            this.proceedButton.setEnabled(true);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        StateEnum state = InstallationProcessController.getInstallationProcessController().getAcInstallationState();
        if (state.ordinal() > StateEnum.REGISTER_WR.ordinal() && state.ordinal() < StateEnum.COMPLETED.ordinal()) {
            menu.add(0, 0, 0, C0676R.string.installation_menu_supendInstallationButton).setIcon(C0676R.drawable.ic_more_vert_white_24dp).setShowAsAction(2);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            onBackPressed();
        } else {
            startActivity(new Intent(this, UnfinishedInstallationDetailsActivity.class));
        }
        return true;
    }

    public void onBackPressed() {
        if (isTaskRoot()) {
            InstallationProcessController.getInstallationProcessController().detectStatus(this);
        } else {
            super.onBackPressed();
        }
    }
}
