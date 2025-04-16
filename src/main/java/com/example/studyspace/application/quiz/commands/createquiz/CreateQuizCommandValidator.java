package com.example.studyspace.application.quiz.commands.createquiz;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseValidator;
import org.springframework.stereotype.Component;

@Component
public class CreateQuizCommandValidator implements UseCaseValidator<CreateQuizCommand> {
    @Override
    public void validate(CreateQuizCommand command) {}
}
