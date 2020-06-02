package com.epam.lab.exception;

public class RemoveException extends RuntimeException {

    public RemoveException() {
        super();
    }

    public RemoveException(String message) {
        super(message);
    }

    public RemoveException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoveException(Throwable cause) {
        super(cause);
    }
}
