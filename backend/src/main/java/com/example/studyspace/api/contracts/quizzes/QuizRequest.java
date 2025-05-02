package com.example.studyspace.api.contracts.quizzes;

import com.example.studyspace.domain.quiz.valueobjects.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * A record representing a request to create a new quiz.
 *
 * @param title       The title of the quiz.
 * @param description A brief description of the quiz.
 * @param difficulty  The difficulty level of the quiz.
 * @param estimatedTimeInMinutes The estimated time to complete the quiz in minutes.
 * @param tags        A list of tags associated with the quiz.
 *
 * @version 1.0
 */
public record QuizRequest (
    ArrayList<Question> questions,
    String title,
    String description,
    String difficulty,
    int estimatedTimeInMinutes,
    List<String> tags
) {}
