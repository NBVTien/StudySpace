package com.example.studyspace.api.contracts.quizzes;

import java.util.List;

/**
 * Represents a request to create or update a question in a quiz.
 *
 * @param question      The text of the question.
 * @param options       A list of possible answers for the question.
 * @param correctAnswer The correct answer from the list of options.
 *
 * @version 1.0
 */
public record QuestionRequest (
    String question,
    List<String> options,
    String correctAnswer
) {}
