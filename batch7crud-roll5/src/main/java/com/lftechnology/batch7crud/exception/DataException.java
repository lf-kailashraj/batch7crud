package com.lftechnology.batch7crud.exception;

@SuppressWarnings("serial")
public class DataException extends Exception {

    public DataException() {
        super("Sql error");
    }

    public DataException(String message) {
        super(message);
    }

}
