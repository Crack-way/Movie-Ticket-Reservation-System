package com.example.securityservice.exception;

public class TokenDoesntExistException extends RuntimeException{

    public TokenDoesntExistException(String msg){
        super(msg);
    }
}
