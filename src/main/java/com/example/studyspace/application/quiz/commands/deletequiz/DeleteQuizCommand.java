package com.example.studyspace.application.quiz.commands.deletequiz;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseInput;
import lombok.Value;

/**
 * Command for deleting a quiz.
 * This class contains the necessary information to delete a quiz from the system.
 *
 * @version 1.0
 */
@Value
public class DeleteQuizCommand implements UseCaseInput<Void> {
    String quizId;
}
