package com.naxsoft.lunchinhell.service;

/**
 * Created by Iouri on 25/04/2015.
 */
public class WebResult {

    private int mCode;
    private String mBody;

    public WebResult(int mCode, String mBody) {
        this.mCode = mCode;
        this.mBody = mBody;
    }

    public int getHttpCode() {
        return mCode;
    }

    public String getHttpBody() {
        return mBody;
    }
}
