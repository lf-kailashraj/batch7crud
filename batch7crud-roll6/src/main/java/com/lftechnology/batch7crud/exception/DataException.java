package com.lftechnology.batch7crud.exception;

public class DataException extends Exception {

    private static final long serialVersionUID = 1L;

    public DataException() {
        super("Data access acception");
    }

    public DataException(String message) {
        super(message);
    }

}
