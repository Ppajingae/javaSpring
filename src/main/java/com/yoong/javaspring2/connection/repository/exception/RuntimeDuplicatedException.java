package com.yoong.javaspring2.connection.repository.exception;

public class RuntimeDuplicatedException extends RuntimeSQLException {

    public RuntimeDuplicatedException() {
    }

    public RuntimeDuplicatedException(String message) {
        super(message);
    }

    public RuntimeDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimeDuplicatedException(Throwable cause) {
        super(cause);
    }
}
