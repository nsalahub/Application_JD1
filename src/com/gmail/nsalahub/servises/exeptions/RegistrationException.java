package com.gmail.nsalahub.servises.exeptions;

public class RegistrationException extends Exception {

    public RegistrationException() {
    }

    public RegistrationException(String s) {
        super(s);
    }

    public RegistrationException(Throwable throwable) {
        super(throwable);
    }
}
