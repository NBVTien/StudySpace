package com.example.studyspace.application.quiz.commands.create;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseValidator;
import com.example.studyspace.application.common.models.ErrorMessages;
import com.example.studyspace.application.quiz.dtos.QuestionDto;
import com.example.studyspace.application.quiz.dtos.QuizDto;
import org.springframework.stereotype.Component;

@Component
public class CreateQuizCommandValidator implements UseCaseValidator<CreateQuizCommand> {
    @Override
    public void validate(CreateQuizCommand command) {}
}
