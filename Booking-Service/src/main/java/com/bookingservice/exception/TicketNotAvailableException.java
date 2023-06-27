package com.bookingservice.exception;

public class TicketNotAvailableException extends RuntimeException{

    public TicketNotAvailableException(String msg){

        super(msg);
    }
}
