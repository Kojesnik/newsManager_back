package com.epam.lab.exception;

public class NotExistsException extends RuntimeException {

    public NotExistsException() {
        super();
    }

    public NotExistsException(String message) {
        super(message);
    }

    public NotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotExistsException(Throwable cause) {
        super(cause);
    }
}
