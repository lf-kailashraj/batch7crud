package com.lftechnology.batch7crud.exception;

/**
 * Created by kiran on 1/18/16.
 */
public class DataException extends Exception {

    public DataException(){
        super("exception while getting data from database");
    }

    public DataException(String message){
        super(message);
    }

}
