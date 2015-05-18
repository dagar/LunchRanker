package ca.dagar.lunchranker.data;

import android.os.Looper;
import android.util.Log;

import ca.dagar.lunchranker.Consts;
import ca.dagar.lunchranker.domain.UserAccount;
import ca.dagar.lunchranker.service.WebHelper;
import ca.dagar.lunchranker.service.WebResult;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;

public class AccountDS {
    static {
        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);
    }


    public boolean register(UserAccount account) throws Exception {
        if (Looper.myLooper() == Looper.getMainLooper()) { throw new AssertionError("This code should not run on the main thread"); }

        String postString = "username=" + account.getUserName() + "&password=" + account.getPassword();
        WebHelper webHelper = new WebHelper();
        WebResult result = webHelper.executeHTTP(Consts.USERS_NEW, "POST", postString);
        boolean rc = (result.getHttpCode() == HttpURLConnection.HTTP_CREATED);
        if (!rc) {
            Log.e("AccountDS", result.getHttpBody());
        }
        return rc;
    }

    public boolean login(UserAccount account) throws Exception {

        if (Looper.myLooper() == Looper.getMainLooper()) { throw new AssertionError("This code should not run on the main thread"); }

        String postString = "username=" + account.getUserName() + "&password=" + account.getPassword();
        WebHelper webHelper = new WebHelper();
        WebResult result = webHelper.executeHTTP(Consts.USERS_LOGIN, "POST", postString);
        boolean rc = (result.getHttpCode() == HttpURLConnection.HTTP_OK);
        if (!rc) {
            Log.e("AccountDS", result.getHttpBody());
        }

        return rc;
    }
}