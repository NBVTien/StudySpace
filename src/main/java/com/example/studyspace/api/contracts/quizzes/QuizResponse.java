package com.example.studyspace.api.contracts.quizzes;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record QuizResponse (
    UUID id,
    List<UUID> questionIds,
    String title,
    String description
) {}
