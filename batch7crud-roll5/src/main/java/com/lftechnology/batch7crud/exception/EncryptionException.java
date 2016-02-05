package com.lftechnology.batch7crud.exception;

import com.lftechnology.batch7crud.constants.NormalConstants;

public class EncryptionException extends Exception {
    private static final long serialVersionUID = 1L;

    public EncryptionException() {
        super(NormalConstants.SQL_ERROR_MESSAGE);
    }

    public EncryptionException(String message) {
        super(message);
    }

}
