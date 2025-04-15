package com.example.studyspace.application.quiz.commands.update;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseValidator;
import com.example.studyspace.application.common.models.ErrorMessages;
import com.example.studyspace.application.quiz.dtos.QuestionDto;
import com.example.studyspace.application.quiz.dtos.QuizDto;
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

        QuizDto quizDto = command.getQuizDto();
        quizDto.questions().forEach(this::validateQuestion);
    }

    private void validateQuestion(QuestionDto questionDto) {
        if (questionDto.options().size() < 2) {
            throw new IllegalArgumentException(ErrorMessages.QUESTION_MINIMUM_OPTIONS.getMessage());
        }
        if (!questionDto.options().contains(questionDto.correctAnswer())) {
            throw new IllegalArgumentException(ErrorMessages.QUESTION_NOT_CONTAINING_CORRECT_ANSWER.getMessage());
        }
    }
}