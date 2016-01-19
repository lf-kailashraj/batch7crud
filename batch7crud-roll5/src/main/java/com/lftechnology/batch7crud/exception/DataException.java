/**
 * 
 */
package com.lftechnology.batch7crud.exception;

/**
 * @author leapfrog
 *
 */
public class DataException extends Exception {

    public DataException() {
        super("Sql error");
    }
    
    public DataException(String message){
        super(message);
    }

}
