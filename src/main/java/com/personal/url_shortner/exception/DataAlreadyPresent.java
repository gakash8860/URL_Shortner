package com.personal.url_shortner.exception;



public class DataAlreadyPresent extends RuntimeException{

    public DataAlreadyPresent(String msg){
        super(msg);
    }

}
