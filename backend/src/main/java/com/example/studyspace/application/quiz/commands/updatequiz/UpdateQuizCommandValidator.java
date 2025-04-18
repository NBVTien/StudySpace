package com.example.studyspace.application.quiz.commands.updatequiz;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseValidator;
import com.example.studyspace.application.common.models.ErrorMessages;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Validator for the <code>UpdateQuizCommand</code>.
 * This class is responsible for validating the input parameters of the UpdateQuizCommand.
 * It checks if the quiz ID is a valid UUID format.
 *
 * @version 1.0
 */
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