package com.example.studyspace.application.quiz.commands.updatequestion;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseInput;
import com.example.studyspace.application.quiz.dtos.QuestionDto;
import com.example.studyspace.domain.quiz.entities.Question;
import lombok.Value;

/**
 * Command to update a question in a quiz.
 * This command contains the quiz ID, question ID, and the updated question data.
 */
@Value
public class UpdateQuestionCommand implements UseCaseInput<Question> {
    String quizId;
    String questionId;
    QuestionDto questionDto;
}
