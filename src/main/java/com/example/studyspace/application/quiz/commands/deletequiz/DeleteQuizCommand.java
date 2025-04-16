package com.example.studyspace.application.quiz.commands.deletequiz;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseInput;
import lombok.Value;

@Value
public class DeleteQuizCommand implements UseCaseInput<Void> {
    String quizId;
}
