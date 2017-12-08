package org.wahlzeit.model;

public class ConversionException extends Exception {

    public ConversionException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
