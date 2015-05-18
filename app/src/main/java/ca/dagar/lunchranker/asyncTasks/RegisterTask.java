package ca.dagar.lunchranker.asyncTasks;

import android.os.AsyncTask;

import ca.dagar.lunchranker.RegisterCallback;
import ca.dagar.lunchranker.data.AccountDS;
import ca.dagar.lunchranker.domain.UserAccount;

public class RegisterTask extends AsyncTask<UserAccount, Integer, AsyncTaskResult<BoolResult>> {
    private RegisterCallback registerCallback;
    private UserAccount userAccount;

    public RegisterTask(RegisterCallback registerCallback) {
        this.registerCallback = registerCallback;
    }


    @Override
    public AsyncTaskResult<BoolResult> doInBackground(UserAccount... params) {
        userAccount = params[0];
        AccountDS accountDS = new AccountDS();
        try {
            boolean registerResult = accountDS.register(userAccount);
            return new AsyncTaskResult<BoolResult>(new BoolResult(registerResult));
        } catch (Exception e) {
            return new AsyncTaskResult(e);
        }
    }

    @Override
    protected void onPostExecute(AsyncTaskResult<BoolResult> boolResultAsyncTaskResult) {
        registerCallback.execute(userAccount, boolResultAsyncTaskResult);
    }
}
