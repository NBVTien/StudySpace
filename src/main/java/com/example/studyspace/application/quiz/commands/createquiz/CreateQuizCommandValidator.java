package com.example.studyspace.application.quiz.commands.createquiz;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseValidator;
import org.springframework.stereotype.Component;

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
    public void validate(CreateQuizCommand command) {}
}
