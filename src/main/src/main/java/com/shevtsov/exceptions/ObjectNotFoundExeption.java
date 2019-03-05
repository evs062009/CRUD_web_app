package com.shevtsov.exceptions;

public class ObjectNotFoundExeption extends RuntimeException {
    public ObjectNotFoundExeption(long id) {
        super("Object with ID " + id + " not found!!!");
    }
}
