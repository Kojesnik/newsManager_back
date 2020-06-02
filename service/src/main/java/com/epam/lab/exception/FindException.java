package com.epam.lab.exception;

public class FindException extends RuntimeException {

    public FindException() {
        super();
    }

    public FindException(String message) {
        super(message);
    }

    public FindException(String message, Throwable cause) {
        super(message, cause);
    }

    public FindException(Throwable cause) {
        super(cause);
    }
}
