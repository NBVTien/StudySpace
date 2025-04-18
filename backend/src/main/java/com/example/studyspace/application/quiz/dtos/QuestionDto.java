package com.example.studyspace.application.quiz.dtos;

import lombok.Builder;

import java.util.List;

/**
 * Data Transfer Object (DTO) for a question.
 * This class is used to transfer question data between different layers of the application.
 *
 * @param question The text of the question.
 * @param options  The list of possible answers for the question.
 * @param correctAnswer The correct answer for the question.
 */
@Builder
public record QuestionDto(
    String question,
    List<String> options,
    String correctAnswer
) {}
