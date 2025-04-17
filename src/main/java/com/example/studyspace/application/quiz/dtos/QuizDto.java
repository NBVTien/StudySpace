package com.example.studyspace.application.quiz.dtos;

import lombok.Builder;

@Builder
public record QuizDto(
    String title,
    String description
) {}
