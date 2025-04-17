package com.example.studyspace.application.quiz.commands.createquestion;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseInput;
import com.example.studyspace.application.quiz.dtos.QuestionDto;
import com.example.studyspace.domain.quiz.entities.Question;
import lombok.Value;

/**
 * Command to create a new question.
 * This command is used to encapsulate the data needed to create a new question in the system.
 * It includes the quiz ID and the question data transfer object (DTO).
 *
 * @version 1.0
 */
@Value
public class CreateQuestionCommand implements UseCaseInput<Question> {
    String quizId;
    QuestionDto questionDto;
}
