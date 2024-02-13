package com.amir.customerapi.exception;

public class CustomerDoesNotExistException extends RuntimeException{
    public CustomerDoesNotExistException(String message){
        super(message);
    }
}
