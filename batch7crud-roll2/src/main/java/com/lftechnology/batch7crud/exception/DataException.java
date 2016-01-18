package com.lftechnology.batch7crud.exception;

/**
 * Created by romit on 1/18/16.
 */
public class DataException extends Exception {
    public DataException() {
        super("Data Lini Bela Ko Exception");
    }

    public DataException(String message) {
        super(message);
    }
}
