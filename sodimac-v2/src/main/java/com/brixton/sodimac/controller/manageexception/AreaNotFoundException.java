package com.brixton.sodimac.controller.manageexception;

public class AreaNotFoundException extends RuntimeException{

    public AreaNotFoundException(String message) {
        super(message);
    }

}
