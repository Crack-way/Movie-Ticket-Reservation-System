package com.example.apigateway.exception;

public class TokenInvalidException extends RuntimeException{

    public TokenInvalidException(String msg){

        super(msg);
    }
}
