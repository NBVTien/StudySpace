package com.example.studyspace.application.quiz.dtos;

import lombok.Builder;

import java.util.List;

@Builder
public record QuestionDto(
    String question,
    List<String> options,
    String correctAnswer
) {}
