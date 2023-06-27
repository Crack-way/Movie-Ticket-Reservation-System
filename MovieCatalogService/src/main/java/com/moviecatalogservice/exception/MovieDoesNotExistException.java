package com.moviecatalogservice.exception;

public class MovieDoesNotExistException extends RuntimeException{

    public MovieDoesNotExistException(String msg){
        super(msg);
    }
}
