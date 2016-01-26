package com.lftechnology.batch7crud.exception;

import java.util.Map;

@SuppressWarnings("serial")
public class ValidationExceptions extends Exception {
    Map<String, String> errorMessage;

    public ValidationExceptions() {
        super("Validation Error");
    }

    public Map<String, String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Map<String, String> error) {
        this.errorMessage = error;
    }
}
