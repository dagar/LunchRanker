package com.naxsoft.lunchinhell;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.naxsoft.lunchinhell.asyncTasks.AsyncTaskResult;
import com.naxsoft.lunchinhell.asyncTasks.LoginTask;
import com.naxsoft.lunchinhell.asyncTasks.RegisterTask;
import com.naxsoft.lunchinhell.domain.UserAccount;
import com.naxsoft.lunchinhell.service.WebHelper;

import com.naxsoft.lunchinhell.asyncTasks.BoolResult;

public class LoginActivity extends Activity
{
    public static final String PREF_USER_NAME = "PREF_USER_NAME";
    public static final String PREF_USER_PASSWORD = "PREF_USER_PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Context context = this;

        final SharedPreferences settings = getSharedPreferences("APP_PREFS", 0);


        Button login = (Button) findViewById(R.id.login);
        Button register = (Button) findViewById(R.id.register);

        final EditText userName = (EditText) findViewById(R.id.userName);
        final EditText password = (EditText) findViewById(R.id.password);
        final String android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        userName.setText(settings.getString(PREF_USER_NAME, ""));
//        password.setText(settings.getString(PREF_USER_PASSWORD, ""));
        password.setText(android_id);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!WebHelper.isOnline(v.getContext())) {
                    Toast.makeText(v.getContext(), "No Network Connectivity.", Toast.LENGTH_LONG).show();
                    return;
                }

                UserAccount account = new UserAccount(userName.getText().toString(), password.getText().toString());
                new LoginTask(new LoginCallback(LoginActivity.this)).execute(account);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!WebHelper.isOnline(v.getContext())) {
                    Toast.makeText(v.getContext(), "No Network Connectivity.", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText login = (EditText) findViewById(R.id.userName);
                EditText password = (EditText) findViewById(R.id.password);
                UserAccount account = new UserAccount(login.getText().toString(), password.getText().toString());
                new RegisterTask(new RegisterCallback(LoginActivity.this)).execute(account);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

