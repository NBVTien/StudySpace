package com.example.studyspace.application.quiz.commands.deletequestion;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseInput;
import lombok.Value;

/**
 * Command for deleting a question.
 * This class contains the necessary information to delete a question from the system.
 *
 * @version 1.0
 */
@Value
public class DeleteQuestionCommand implements UseCaseInput<Void> {
    String questionId;
    String quizId;
}
