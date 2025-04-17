package com.example.studyspace.application.quiz.commands.deletequiz;

import com.example.studyspace.application.common.exceptions.InvalidQuizException;
import com.example.studyspace.application.common.interfaces.usecases.UseCaseValidator;
import com.example.studyspace.application.common.models.ErrorMessages;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Validator for the DeleteQuizCommand.
 * This class is responsible for validating the input of the DeleteQuizCommand.
 * It checks if the quiz ID is a valid UUID.
 *
 * @version 1.0
 */
public class DeleteQuizCommandValidator implements UseCaseValidator<DeleteQuizCommand> {

    /**
     * Validates the DeleteQuizCommand.
     * This method checks if the quiz ID is a valid UUID.
     *
     * @param command The command to be validated.
     * @throws InvalidQuizException if the quiz ID is not a valid UUID.
     */
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
