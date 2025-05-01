package com.example.studyspace.application.quiz.commands.createquiz;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseValidator;
import com.example.studyspace.application.common.models.ErrorMessages;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Validator for the <code>CreateQuizCommand</code>.
 * This class is responsible for validating the <code>CreateQuizCommand</code> before it is executed.
 * It implements the <code>UseCaseValidator</code> interface.
 *
 * @version 1.0
 */
@Component
public class CreateQuizCommandValidator implements UseCaseValidator<CreateQuizCommand> {
    /**
     * Validates the <code>CreateQuizCommand</code>.
     *
     * @param command The command to be validated.
     */
    @Override
    public void validate(CreateQuizCommand command) {
        String id = command.getOwnerId();
        try {
            UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_QUIZ_ID.getMessage());
        }
    }
}
