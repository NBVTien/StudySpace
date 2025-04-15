package com.example.studyspace.application.quiz.commands.update;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseValidator;
import com.example.studyspace.application.common.models.ErrorMessages;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateQuizCommandValidator implements UseCaseValidator<UpdateQuizCommand> {
    @Override
    public void validate(UpdateQuizCommand command) {
        String id = command.getQuizId();
        try {
            UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_QUIZ_ID.getMessage());
        }
    }
}