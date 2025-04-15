package com.example.studyspace.application.quiz.queries.readbyid;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseInput;
import com.example.studyspace.domain.quiz.Quiz;
import lombok.Value;

@Value
public class ReadQuizQuery implements UseCaseInput<Quiz> {
    String quizId;
}