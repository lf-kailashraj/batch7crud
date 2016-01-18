package com.lftechnology.batch7crud.exception;

/**
 * @Author binodnme
 * Created on 1/18/16
 */
public class DataException extends Exception {

    public DataException(){
        super("data exception");
    }

    public DataException(String message){
        super(message);
    }
}
