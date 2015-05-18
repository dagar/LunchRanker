package ca.dagar.lunchranker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ca.dagar.lunchranker.asyncTasks.LoginTask;
import ca.dagar.lunchranker.asyncTasks.RegisterTask;
import ca.dagar.lunchranker.domain.UserAccount;
import ca.dagar.lunchranker.service.WebHelper;

public class LoginActivity extends Activity
{
    public static final String PREF_USER_NAME = "PREF_USER_NAME";
    public static final String PREF_USER_PASSWORD = "PREF_USER_PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ca.dagar.lunchranker.R.layout.activity_login);

        final Context context = this;

        final SharedPreferences settings = getSharedPreferences("APP_PREFS", 0);


        Button login = (Button) findViewById(ca.dagar.lunchranker.R.id.login);
        Button register = (Button) findViewById(ca.dagar.lunchranker.R.id.register);

        final EditText userName = (EditText) findViewById(ca.dagar.lunchranker.R.id.userName);
        final EditText password = (EditText) findViewById(ca.dagar.lunchranker.R.id.password);
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

                EditText login = (EditText) findViewById(ca.dagar.lunchranker.R.id.userName);
                EditText password = (EditText) findViewById(ca.dagar.lunchranker.R.id.password);
                UserAccount account = new UserAccount(login.getText().toString(), password.getText().toString());
                new RegisterTask(new RegisterCallback(LoginActivity.this)).execute(account);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(ca.dagar.lunchranker.R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == ca.dagar.lunchranker.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

