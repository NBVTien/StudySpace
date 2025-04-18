package com.example.studyspace.application.quiz.commands.createquestion;

import com.example.studyspace.application.common.exceptions.InvalidQuizException;
import com.example.studyspace.application.common.interfaces.usecases.UseCaseValidator;
import com.example.studyspace.application.common.models.ErrorMessages;
import org.springframework.stereotype.Component;

/**
 * Validator for the CreateQuestionCommand.
 * This class is responsible for validating the CreateQuestionCommand before it is executed.
 * It checks if the question has at least two options and if the correct answer is one of the options.
 *
 * @version 1.0
 */
@Component
public class CreateQuestionCommandValidator implements UseCaseValidator<CreateQuestionCommand> {

    /**
     * Validates the CreateQuestionCommand.
     * This method checks if the question has at least two options and if the correct answer is one of the options.
     *
     * @param command The command to be validated.
     */
    @Override
    public void validate(CreateQuestionCommand command) {
        if (command.getQuestionDto().options().size() < 2) {
            throw new InvalidQuizException(ErrorMessages.QUESTION_MINIMUM_OPTIONS.getMessage());
        }

        if (!command.getQuestionDto().options().contains(command.getQuestionDto().correctAnswer())) {
            throw new InvalidQuizException(ErrorMessages.QUESTION_NOT_CONTAINING_CORRECT_ANSWER.getMessage());
        }
    }
}
