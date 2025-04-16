package com.example.studyspace.application.common.models;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    INVALID_QUIZ_ID("Quiz ID have to be UUID."),
    QUIZ_NOT_FOUND("Quiz not found."),
    QUESTION_NOT_FOUND("Question not found."),
    QUESTION_NOT_CONTAINING_CORRECT_ANSWER("Correct answer must be one of the options."),
    QUESTION_MINIMUM_OPTIONS("Question must have at least 2 options.");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }
}
