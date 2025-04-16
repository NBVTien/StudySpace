package com.example.studyspace.application.common.exceptions;

import com.example.studyspace.application.common.models.ErrorMessages;

public class QuestionNotFoundException extends NotFoundException {
    public QuestionNotFoundException() {
        super(ErrorMessages.QUESTION_NOT_FOUND.getMessage());
    }
}

