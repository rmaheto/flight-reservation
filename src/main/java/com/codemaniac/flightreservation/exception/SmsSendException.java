package com.codemaniac.flightreservation.exception;

public class SmsSendException extends RuntimeException{
    public SmsSendException(String msg, Throwable t){
        super(msg,t);
    }
}
