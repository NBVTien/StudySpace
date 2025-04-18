package com.example.studyspace.application.common.exceptions;

import com.example.studyspace.application.common.models.ErrorMessages;

/**
 * <code>QuestionNotFoundException</code> is a custom exception that is thrown when a requested question is not found.
 * It extends the <code>NotFoundException</code> class.
 *
 * @version 1.0
 */
public class QuestionNotFoundException extends NotFoundException {
    public QuestionNotFoundException() {
        super(ErrorMessages.QUESTION_NOT_FOUND.getMessage());
    }
}

