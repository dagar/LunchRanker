package ca.dagar.lunchranker.asyncTasks;

import android.os.AsyncTask;

import ca.dagar.lunchranker.LoginCallback;
import ca.dagar.lunchranker.data.AccountDS;
import ca.dagar.lunchranker.domain.UserAccount;

/**
 * Created by Iouri on 15/05/2015.
 */
public class LoginTask extends AsyncTask<UserAccount, Integer, AsyncTaskResult<BoolResult>> {
    private LoginCallback loginCallback;
    private UserAccount userAccount;
    public LoginTask(LoginCallback loginCallback) {
        this.loginCallback = loginCallback;
    }

    @Override
    public AsyncTaskResult<BoolResult> doInBackground(UserAccount... params) {
        userAccount = params[0];
        AccountDS accountDS = new AccountDS();
        try {
            boolean result = accountDS.login(userAccount);
            BoolResult loginTaskResult = new BoolResult(result);
            return new AsyncTaskResult<BoolResult>(loginTaskResult);
        } catch (Exception e) {
            return new AsyncTaskResult(e);
        }
    }

    @Override
    protected void onPostExecute(AsyncTaskResult<BoolResult> boolResultAsyncTaskResult) {
        loginCallback.execute(boolResultAsyncTaskResult);
    }
}
