package com.example.studyspace.application.quiz.dtos;

import java.util.List;

public record QuestionDto(
    String question,
    List<String> options,
    String correctAnswer
) {}
