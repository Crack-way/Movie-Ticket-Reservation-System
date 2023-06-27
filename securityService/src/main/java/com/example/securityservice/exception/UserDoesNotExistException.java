package com.example.securityservice.exception;


public class UserDoesNotExistException extends RuntimeException{

    public UserDoesNotExistException(String str){

        super(str);
    }


}
