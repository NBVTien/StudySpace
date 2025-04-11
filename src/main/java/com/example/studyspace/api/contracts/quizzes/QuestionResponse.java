package com.example.studyspace.api.contracts.quizzes;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record QuestionResponse(
    UUID id,
    String question,
    List<String> options,
    String correctAnswer
) {}
