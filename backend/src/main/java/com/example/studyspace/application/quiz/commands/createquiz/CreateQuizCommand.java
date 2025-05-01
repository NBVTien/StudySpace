package com.example.studyspace.application.quiz.commands.createquiz;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseInput;
import com.example.studyspace.application.quiz.dtos.QuizDto;
import com.example.studyspace.domain.quiz.Quiz;
import lombok.Value;

/**
 * Command for creating a new quiz.
 * This class contains the data required to create a new quiz.
 *
 * @version 1.0
 */
@Value
public class CreateQuizCommand implements UseCaseInput<Quiz> {
    QuizDto quizDto;
    String ownerId;
}
