package com.example.studyspace.api.contracts.quizzes;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

/**
 * Represents a response containing details about a quiz.
 *
 * @param id          The unique identifier of the quiz.
 * @param questionIds  A list of unique identifiers for the questions in the quiz.
 * @param title       The title of the quiz.
 * @param description A brief description of the quiz.
 *
 * @version 1.0
 */
@Builder
public record QuizResponse (
    UUID id,
    List<UUID> questionIds,
    String title,
    String description
) {}
