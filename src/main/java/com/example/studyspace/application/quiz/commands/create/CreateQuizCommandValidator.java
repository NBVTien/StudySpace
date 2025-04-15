package com.example.studyspace.application.quiz.commands.create;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseValidator;
import com.example.studyspace.application.common.models.ErrorMessages;
import com.example.studyspace.application.quiz.dtos.QuestionDto;
import com.example.studyspace.application.quiz.dtos.QuizDto;
import org.springframework.stereotype.Component;

@Component
public class CreateQuizCommandValidator implements UseCaseValidator<CreateQuizCommand> {
    @Override
    public void validate(CreateQuizCommand command) {
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
