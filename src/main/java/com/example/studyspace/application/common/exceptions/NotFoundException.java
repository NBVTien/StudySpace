package com.example.studyspace.application.common.exceptions;

/**
 * <code>NotFoundException</code> is a custom exception that is thrown when a requested resource is not found.
 * It extends the <code>RuntimeException</code> class.
 *
 * @version 1.0
 */
public abstract class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
