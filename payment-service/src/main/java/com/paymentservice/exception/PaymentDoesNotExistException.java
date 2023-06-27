package com.paymentservice.exception;

public class PaymentDoesNotExistException extends RuntimeException{

    public PaymentDoesNotExistException(String str){

        super(str);
    }
        }
