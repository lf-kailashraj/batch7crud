package com.lftechnology.batch7crud.exception;

/**
 * Created by grishma on 1/18/16.
 */
public class DataException extends Exception {
    public DataException(){
        super("Sql ma error cha hai!");
    }

    public DataException(String message){
        super(message);
    }
}
