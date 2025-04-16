package com.example.studyspace.application.quiz.commands.deletequiz;

import com.example.studyspace.application.common.exceptions.InvalidQuizException;
import com.example.studyspace.application.common.interfaces.usecases.UseCaseValidator;
import com.example.studyspace.application.common.models.ErrorMessages;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteQuizCommandValidator implements UseCaseValidator<DeleteQuizCommand> {
    @Override
    public void validate(DeleteQuizCommand command) {
        String id = command.getQuizId();
        try {
            UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidQuizException(ErrorMessages.INVALID_QUIZ_ID.getMessage());
        }
    }
}
