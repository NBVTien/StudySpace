package com.example.studyspace.api.contracts.quizzes;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

/**
 * A record representing a response containing quiz details.
 * This record is used to transfer quiz data between different layers of the application.
 *
 * @param id                 The unique identifier of the quiz.
 * @param questionCount      The number of questions in the quiz.
 * @param title              The title of the quiz.
 * @param description        A brief description of the quiz.
 * @param difficulty         The difficulty level of the quiz.
 * @param estimatedTimeInMinutes The estimated time to complete the quiz in minutes.
 * @param tags               A list of tags associated with the quiz.
 *
 * @version 1.0
 */
@Builder
public record QuizItemResponse(
    UUID id,
    int questionCount,
    String title,
    String description,
    String difficulty,
    int estimatedTimeInMinutes,
    List<String> tags
) {}
