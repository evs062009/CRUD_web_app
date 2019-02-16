package com.shevtsov.exceptions;

public class BusinessException extends Exception {
    /*
     * exception for all our manual cases
     */
    public BusinessException(String msg) {
        super(msg);
    }
}
