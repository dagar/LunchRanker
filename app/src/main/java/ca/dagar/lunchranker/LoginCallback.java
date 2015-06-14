package ca.dagar.lunchranker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import ca.dagar.lunchranker.asyncTasks.AsyncTaskResult;
import ca.dagar.lunchranker.asyncTasks.BoolResult;

/**
 * Created by Iouri on 15/05/2015.
 */
public class LoginCallback {
    private Context applicationContext;

    public LoginCallback(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void execute(AsyncTaskResult<BoolResult> taskResult) {
        if (null == taskResult.getError() && taskResult.getResult().isSuccess()) {
            applicationContext.startActivity(new Intent(applicationContext, VotingActivity.class));
        } else {
            new AlertDialog.Builder(applicationContext).setTitle("Authentication failed")
                    .setMessage("Are you registered on online?")
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
