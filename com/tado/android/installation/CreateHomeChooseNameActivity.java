package com.tado.android.installation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.tado.C0676R;

public class CreateHomeChooseNameActivity extends AppCompatActivity {
    private EditText homeNameInput;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_create_home_choose_name);
        ((TextView) findViewById(C0676R.id.title_bar_textview)).setText(getString(C0676R.string.createHome_title));
        ((TextView) findViewById(C0676R.id.title_template_textview)).setText(getString(C0676R.string.createHome_homeName_message));
        ((ImageView) findViewById(C0676R.id.center_image)).setImageResource(C0676R.drawable.house);
        ((Button) findViewById(C0676R.id.proceed_button)).setText(getString(C0676R.string.createHome_homeName_confirmButton));
        this.homeNameInput = (EditText) findViewById(C0676R.id.input);
    }

    public void proceedClick(View view) {
        String homeName = this.homeNameInput.getText().toString();
        if (homeName.isEmpty()) {
            this.homeNameInput.setError(getString(C0676R.string.createHome_homeName_errors_homeNameFieldEmptyError));
            return;
        }
        Intent intent = new Intent(this, CreateHomeContactDetailsActivity.class);
        intent.putExtras(getIntent());
        intent.putExtra(CreateHomeContactDetailsActivity.INTENT_HOME_NAME, homeName);
        InstallationProcessController.startActivity((Activity) this, intent, false);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0676R.menu.menu_create_home_welcome, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == C0676R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
