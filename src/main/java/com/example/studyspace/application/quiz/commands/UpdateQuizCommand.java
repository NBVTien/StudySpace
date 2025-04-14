package com.example.studyspace.application.quiz.commands;

import com.example.studyspace.application.quiz.dtos.QuizDto;

public record UpdateQuizCommand(
    String quizId,
    QuizDto quizDto
) {}
