package com.example.studyspace.application.common.exceptions;

import com.example.studyspace.application.common.models.ErrorMessages;

/**
 * <code>QuizNotFoundException</code> is a custom exception that is thrown when a requested quiz is not found.
 * It extends the <code>NotFoundException</code> class.
 *
 * @version 1.0
 */
public class QuizNotFoundException extends RuntimeException {
    public QuizNotFoundException() {
        super(ErrorMessages.QUIZ_NOT_FOUND.getMessage());
    }
}
