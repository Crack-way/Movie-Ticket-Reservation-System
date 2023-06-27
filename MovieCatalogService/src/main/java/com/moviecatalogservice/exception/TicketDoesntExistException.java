package com.moviecatalogservice.exception;

public class TicketDoesntExistException extends RuntimeException{

    public TicketDoesntExistException(String str){
        super(str);
    }
}
