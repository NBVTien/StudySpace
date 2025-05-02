package com.example.studyspace.application.quiz.dtos;

import com.example.studyspace.domain.quiz.valueobjects.Question;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object (DTO) for a quiz.
 * This class is used to transfer quiz data between different layers of the application.
 *
 * @param title       The title of the quiz.
 * @param description A brief description of the quiz.
 * @param difficulty  The difficulty level of the quiz (e.g., Easy, Medium, Hard).
 * @param estimatedTimeInMinutes The estimated time to complete the quiz in minutes.
 * @param tags        A list of tags associated with the quiz.
 *
 * @version 1.0
 *
 */
@Builder
public record QuizDto(
    String title,
    String description,
    String difficulty,
    int estimatedTimeInMinutes,
    List<String> tags,
    ArrayList<Question> questions
) {}
