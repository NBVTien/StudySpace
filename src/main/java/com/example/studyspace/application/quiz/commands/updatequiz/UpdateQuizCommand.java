package com.example.studyspace.application.quiz.commands.updatequiz;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseInput;
import com.example.studyspace.application.quiz.dtos.QuizDto;
import com.example.studyspace.domain.quiz.Quiz;
import lombok.Value;

/**
 * Command for updating an existing quiz.
 * This class contains the necessary information to update a quiz, including its ID and the new details.
 *
 * @version 1.0
 */
@Value
public class UpdateQuizCommand implements UseCaseInput<Quiz> {
    String quizId;
    QuizDto quizDto;
}