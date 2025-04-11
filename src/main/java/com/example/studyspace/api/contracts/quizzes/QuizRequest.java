package com.example.studyspace.api.contracts.quizzes;

import java.util.List;

public record QuizRequest (
    List<QuestionRequest> questions,
    String title,
    String description
) {}
