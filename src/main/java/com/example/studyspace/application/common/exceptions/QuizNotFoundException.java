package com.example.studyspace.application.common.exceptions;

import com.example.studyspace.application.common.models.ErrorMessages;

public class QuizNotFoundException extends NotFoundException {
    public QuizNotFoundException() {
        super(ErrorMessages.QUIZ_NOT_FOUND.getMessage());
    }
}
