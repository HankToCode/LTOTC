package com.lt.basics.http;

/**
 * Created by HankGreen on 2017/5/11.
 */

public class ResponseThrowable extends Exception {
    public int code;
    public String message;

    public ResponseThrowable(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }
}
