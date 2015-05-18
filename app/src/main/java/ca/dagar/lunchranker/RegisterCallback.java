package ca.dagar.lunchranker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import ca.dagar.lunchranker.asyncTasks.AsyncTaskResult;
import ca.dagar.lunchranker.asyncTasks.BoolResult;
import ca.dagar.lunchranker.domain.UserAccount;

/**
 * Created by Iouri on 15/05/2015.
 */
public class RegisterCallback {
    private Context applicationContext;

    public RegisterCallback(Context applicationContext) {

        this.applicationContext = applicationContext;
    }

    public void execute(UserAccount userAccount, AsyncTaskResult<BoolResult> result) {
        if (null == result.getError() && result.getResult().isSuccess()) {
            final SharedPreferences settings = applicationContext.getSharedPreferences("APP_PREFS", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString(LoginActivity.PREF_USER_NAME, userAccount.getUserName());
            editor.putString(LoginActivity.PREF_USER_PASSWORD, userAccount.getPassword());
            editor.apply();

            new AlertDialog.Builder(applicationContext).setTitle("Authentication succeeded")
                    .setMessage("Now you can login")
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    // TODO
                                    dialog.dismiss();
                                }
                            })
                    .create()
                    .show();
        } else {
            new AlertDialog.Builder(applicationContext).setTitle("Registration failed")
                    .setMessage("Try different username")
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    // TODO
                                    dialog.dismiss();
                                }
                            })
                    .create()
                    .show();
        }
    }
}
