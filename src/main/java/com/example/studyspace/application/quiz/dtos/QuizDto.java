package com.example.studyspace.application.quiz.dtos;

import lombok.Builder;

/**
 * Data Transfer Object (DTO) for a quiz.
 * This class is used to transfer quiz data between different layers of the application.
 *
 * @param title       The title of the quiz.
 * @param description A brief description of the quiz.
 */
@Builder
public record QuizDto(
    String title,
    String description
) {}
