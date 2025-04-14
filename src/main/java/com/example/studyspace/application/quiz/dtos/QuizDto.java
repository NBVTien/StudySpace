package com.example.studyspace.application.quiz.dtos;

import java.util.List;

public record QuizDto(
    List<QuestionDto> questions,
    String title,
    String description
) {}
