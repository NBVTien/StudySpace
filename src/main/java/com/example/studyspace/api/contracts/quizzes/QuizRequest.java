package com.example.studyspace.api.contracts.quizzes;

/**
 * A record representing a request to create a new quiz.
 *
 * @param title       The title of the quiz.
 * @param description A brief description of the quiz.
 *
 * @version 1.0
 */
public record QuizRequest (
    String title,
    String description
) {}
