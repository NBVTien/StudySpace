package com.example.studyspace.application.quiz.commands.updatequestion;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseInput;
import com.example.studyspace.application.quiz.dtos.QuestionDto;
import com.example.studyspace.domain.quiz.entities.Question;
import lombok.Value;

@Value
public class UpdateQuestionCommand implements UseCaseInput<Question> {
    String quizId;
    String questionId;
    QuestionDto questionDto;
}
