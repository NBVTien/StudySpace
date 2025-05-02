package com.example.studyspace.domain.quiz.valueobjects;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.util.List;

/**
 * Represents a question in a quiz as a Value Object.
 * This class contains the question text, options, correct answer, and the quiz it belongs to.
 *
 * @version 1.0
 */
@Getter
@Value
@Builder
public class Question {
    /**
     * The question text.
     */
    String question;
    /**
     * The list of options for the question.
     */
    List<String> options;
    /**
     * The correct answer for the question.
     */
    String correctAnswer;
}
