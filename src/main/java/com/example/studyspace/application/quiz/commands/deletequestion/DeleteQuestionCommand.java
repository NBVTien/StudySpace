package com.example.studyspace.application.quiz.commands.deletequestion;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseInput;
import lombok.Value;

@Value
public class DeleteQuestionCommand implements UseCaseInput<Void> {
    String questionId;
    String quizId;
}
