package com.example.apigateway.exception;

public class DoesNotContainAuthorizationException extends RuntimeException{


    public DoesNotContainAuthorizationException(String str){

        super(str);
    }
}
