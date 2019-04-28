package com.gmail.nsalahub.servises.exeptions;

public class LoginException extends Exception {

    public LoginException() {
    }

    public LoginException(String s) {
        super(s);
    }

    public LoginException(Throwable throwable) {
        super(throwable);
    }
}
