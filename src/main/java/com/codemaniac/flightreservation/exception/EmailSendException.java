package com.codemaniac.flightreservation.exception;

public class EmailSendException extends RuntimeException{
    public EmailSendException(String msg, Throwable t){
        super(msg,t);
    }
}
