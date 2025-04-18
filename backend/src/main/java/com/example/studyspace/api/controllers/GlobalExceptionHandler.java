package com.example.studyspace.api.controllers;

import com.example.studyspace.application.common.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the application.
 * This class handles exceptions thrown by controllers and returns appropriate HTTP responses.
 *
 * @version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles NotFoundException and returns a 404 Not Found response.
     *
     * @param e The NotFoundException that was thrown.
     * @return A ProblemDetail object with the status and detail message.
     *
     */
    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    /**
     * Handles generic exceptions and returns a 500 Internal Server Error response.
     *
     * @param e The Exception that was thrown.
     * @return A ProblemDetail object with the status and detail message.
     *
     */
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
