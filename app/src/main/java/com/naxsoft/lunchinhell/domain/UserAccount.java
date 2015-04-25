package com.naxsoft.lunchinhell.domain;

/**
 * Created by Iouri on 25/04/2015.
 */
public class UserAccount {
    private String userName;
    private String password;

    public UserAccount(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
