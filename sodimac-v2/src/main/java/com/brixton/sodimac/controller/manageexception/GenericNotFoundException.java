package com.brixton.sodimac.controller.manageexception;

public class GenericNotFoundException extends RuntimeException{
    public GenericNotFoundException(String message){
        super(message);
    }
}
