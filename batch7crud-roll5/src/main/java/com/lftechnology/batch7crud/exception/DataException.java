package com.lftechnology.batch7crud.exception;

import com.lftechnology.batch7crud.constants.NormalConstants;

@SuppressWarnings("serial")
public class DataException extends Exception {

    public DataException() {
        super(NormalConstants.SQL_ERROR_MESSAGE);
    }

    public DataException(String message) {
        super(message);
    }

}
