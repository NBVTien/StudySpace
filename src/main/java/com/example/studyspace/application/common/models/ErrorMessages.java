package com.example.studyspace.application.common.models;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    QUIZ_NOT_FOUND("Quiz not found!");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }
}
