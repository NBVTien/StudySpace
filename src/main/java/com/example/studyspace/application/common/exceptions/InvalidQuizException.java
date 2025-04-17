package com.example.studyspace.application.common.exceptions;

/*
 * <code>InvalidQuizException</code> is a custom exception that is thrown when a quiz is invalid.
 * It extends the <code>RuntimeException</code> class.
 *
 * @version 1.0
 */
public class InvalidQuizException extends RuntimeException {
    public InvalidQuizException(String message) {
        super(message);
    }
}
