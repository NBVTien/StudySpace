package com.example.studyspace.api.contracts.quizzes;

import java.util.List;

public record QuestionRequest (
    String question,
    List<String> options,
    String correctAnswer
) {}
