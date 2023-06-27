package com.bookingservice.exception;

public class TicketAlreadyBookedWithThisPaymentException extends RuntimeException{

    public TicketAlreadyBookedWithThisPaymentException(String msg){

        super(msg);
    }
}
