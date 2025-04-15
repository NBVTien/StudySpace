package com.example.studyspace.application.quiz.commands.update;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseInput;
import com.example.studyspace.application.quiz.dtos.QuizDto;
import com.example.studyspace.domain.quiz.Quiz;
import lombok.Value;

@Value
public class UpdateQuizCommand implements UseCaseInput<Quiz> {
    String quizId;
    QuizDto quizDto;
}