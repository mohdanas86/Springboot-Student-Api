package com.anasalam.restapi.RestApis;

public class ValidationException extends RuntimeException{
    public ValidationException(String message){
        super(message);
    }

   public ValidationException(String message, Throwable cause){
        super(message, cause);
   }
}
