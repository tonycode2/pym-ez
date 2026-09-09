package com.pymez.backend.pymez.Exceptions;


public class ItemsException extends RuntimeException{
    /**
     * This is an exception that must be used for everyting related to items
     */
    public ItemsException(){}

    public ItemsException(String message){
        super(message);
    }

    public ItemsException(Throwable cause){
        super(cause);
    }
    
    public ItemsException(String message, Throwable cause){
        super(message, cause);
    }
}
