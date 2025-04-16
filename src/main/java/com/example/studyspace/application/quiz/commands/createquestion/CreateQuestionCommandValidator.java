package com.example.studyspace.application.quiz.commands.createquestion;

import com.example.studyspace.application.common.exceptions.InvalidQuizException;
import com.example.studyspace.application.common.interfaces.usecases.UseCaseValidator;
import com.example.studyspace.application.common.models.ErrorMessages;

public class CreateQuestionCommandValidator implements UseCaseValidator<CreateQuestionCommand> {

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
