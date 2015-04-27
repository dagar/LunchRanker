package com.naxsoft.lunchinhell.data;

import android.accounts.Account;
import android.os.AsyncTask;
import android.os.Debug;
import android.util.Log;

import com.naxsoft.lunchinhell.Consts;
import com.naxsoft.lunchinhell.domain.UserAccount;

import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * Created by Iouri on 25/04/2015.
 */
public class AccountDS {
    static {
        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);
    }


    public boolean register(UserAccount account) {
        AsyncTask<UserAccount, Void, Boolean> asyncTask = new AsyncTask<UserAccount, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(UserAccount... params) {
                Boolean rc = false;
                try {
                    String postString = "username=" + params[0].getUserName() + "&password=" + params[0].getPassword();
                    byte[] postStringBytes = postString.getBytes(Charset.forName("UTF-8"));

                    HttpURLConnection connection = (HttpURLConnection) new URL(Consts.USERS_NEW).openConnection();
                    connection.setDoOutput(true); // POST
                    connection.setChunkedStreamingMode(0);
                    connection.setRequestProperty( "Content-Length", Integer.toString( postStringBytes.length ));

                    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                    out.write(postString);
                    out.flush();
//                    Scanner scanner = new Scanner(connection.getInputStream());
//                    while(scanner.hasNext()) {
//                        Log.i("NET", scanner.next());
//                    }
//                    scanner.close();
//                    scanner = new Scanner(connection.getErrorStream());
//                    while(scanner.hasNext()) {
//                        Log.e("NET", scanner.next());
//                    }
//                    scanner.close();

                    rc = HttpURLConnection.HTTP_CREATED == connection.getResponseCode();
                    connection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return rc;
            }
        };
        Boolean rc = false;
        try {
            rc = asyncTask.execute(account).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rc;
    }

    public boolean login(UserAccount account) {

        AsyncTask<UserAccount, Void, Boolean> asyncTask = new AsyncTask<UserAccount, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(UserAccount... params) {
                Boolean rc = false;
                try {
                    String postString = "username=" + params[0].getUserName() + "&password=" + params[0].getPassword();
                    byte[] postStringBytes = postString.getBytes(Charset.forName("UTF-8"));

                    HttpURLConnection connection = (HttpURLConnection) new URL(Consts.USERS_LOGIN).openConnection();
                    connection.setDoOutput(true); // POST
                    connection.setChunkedStreamingMode(0);
                    connection.setRequestProperty( "Content-Length", Integer.toString( postStringBytes.length ));

                    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                    out.write(postString);
                    out.flush();
//                    Scanner scanner = new Scanner(connection.getInputStream());
//                    while(scanner.hasNext()) {
//                        Log.i("NET", scanner.next());
//                    }
//                    scanner.close();
//                    scanner = new Scanner(connection.getErrorStream());
//                    while(scanner.hasNext()) {
//                        Log.e("NET", scanner.next());
//                    }
//                    scanner.close();

                    rc = HttpURLConnection.HTTP_OK == connection.getResponseCode();
                    connection.disconnect();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return rc;
            }
        };
        Boolean rc = false;
        try {
            rc = asyncTask.execute(account).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rc;
    }
}
