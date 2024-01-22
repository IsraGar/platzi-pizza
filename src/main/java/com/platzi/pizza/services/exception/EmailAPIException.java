package com.platzi.pizza.services.exception;

public class EmailAPIException extends RuntimeException{
    public EmailAPIException(){
        super("Error sending email");
    }
}
