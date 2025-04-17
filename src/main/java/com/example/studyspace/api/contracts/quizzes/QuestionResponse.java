package com.example.studyspace.api.contracts.quizzes;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

/**
 * Represents a response containing details about a quiz question.
 *
 * @param id            The unique identifier of the question.
 * @param question      The text of the question.
 * @param options       A list of possible answers to the question.
 * @param correctAnswer The correct answer to the question.
 * @param quizId        The unique identifier of the quiz to which this question belongs.
 *
 * @version 1.0
 */
@Builder
public record QuestionResponse(
    UUID id,
    String question,
    List<String> options,
    String correctAnswer,
    UUID quizId
) {}
