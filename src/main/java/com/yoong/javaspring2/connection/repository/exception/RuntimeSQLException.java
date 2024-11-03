package com.yoong.javaspring2.connection.repository.exception;

public class RuntimeSQLException extends RuntimeException {

    public RuntimeSQLException() {
    }

    public RuntimeSQLException(String message) {
        super(message);
    }

    public RuntimeSQLException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimeSQLException(Throwable cause) {
        super(cause);
    }
}
