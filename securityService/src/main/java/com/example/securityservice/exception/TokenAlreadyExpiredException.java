package com.example.securityservice.exception;

public class TokenAlreadyExpiredException extends RuntimeException{

    public TokenAlreadyExpiredException(String msg){
        super(msg);
    }
}
