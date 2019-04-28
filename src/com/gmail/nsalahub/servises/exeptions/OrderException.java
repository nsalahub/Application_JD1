package com.gmail.nsalahub.servises.exeptions;

public class OrderException extends Exception {

    public OrderException() {
    }

    public OrderException(String s) {
        super(s);
    }

    public OrderException(Throwable throwable) {
        super(throwable);
    }
}
