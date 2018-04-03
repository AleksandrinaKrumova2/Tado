package com.tado.android.installation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.User;
import com.tado.android.rest.service.RestServiceGenerator;
import retrofit2.Call;
import retrofit2.Response;

public class CreateHomeWelcomeActivity extends ACInstallationBaseActivity {

    class C08051 extends TadoCallback<User> {
        C08051() {
        }

        public void onResponse(Call<User> call, Response<User> response) {
            super.onResponse(call, response);
            CreateHomeWelcomeActivity.this.dismissLoadingUI();
            if (response.isSuccessful()) {
                User user = (User) response.body();
                CreateHomeWelcomeActivity.this.getIntent().putExtra(CreateHomeContactDetailsActivity.INTENT_NAME, user.getName());
                CreateHomeWelcomeActivity.this.getIntent().putExtra("email", user.getEmail());
                CreateHomeWelcomeActivity.this.titleTemplateTextview.setText(CreateHomeWelcomeActivity.this.getString(C0676R.string.createHome_introduction_title, new Object[]{user.getName()}));
                return;
            }
            CreateHomeWelcomeActivity.this.handleServerError(this.serverError, CreateHomeWelcomeActivity.this, call, response.code(), this);
        }

        public void onFailure(Call<User> call, Throwable t) {
            super.onFailure(call, t);
            CreateHomeWelcomeActivity.this.dismissLoadingUI();
            InstallationProcessController.showConnectionErrorRetrofit(CreateHomeWelcomeActivity.this, call, this);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_create_home_welcome);
        this.titleBarTextview.setText(C0676R.string.createHome_title);
        this.textView.setText(C0676R.string.createHome_introduction_instructionsLabel);
        this.centerImage.setImageResource(C0676R.drawable.house);
        this.proceedButton.setText(C0676R.string.createHome_introduction_createHomeButton);
        String name = "";
        if (getIntent().getExtras() == null || !getIntent().getExtras().containsKey(CreateHomeContactDetailsActivity.INTENT_NAME)) {
            sendUserRequest();
        } else {
            name = getIntent().getExtras().getString(CreateHomeContactDetailsActivity.INTENT_NAME);
        }
        this.titleTemplateTextview.setText(getString(C0676R.string.createHome_introduction_title, new Object[]{name}));
    }

    private void sendUserRequest() {
        showLoadingUI();
        RestServiceGenerator.getTadoRestService().getMe(RestServiceGenerator.getCredentialsMap()).enqueue(new C08051());
    }

    public void troubleshoot(View view) {
        InstallationProcessController.startActivity((Activity) this, JoinHomeActivity.class, false);
    }

    public void proceedClick(View view) {
        Intent intent = new Intent(this, CreateHomeEnterAddressActivity.class);
        intent.putExtras(getIntent());
        InstallationProcessController.startActivity((Activity) this, intent, false);
    }
}
