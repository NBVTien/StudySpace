package com.example.studyspace.application.quiz.queries.readquestion;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseInput;
import com.example.studyspace.domain.quiz.entities.Question;
import lombok.Value;

@Value
public class ReadQuestionQuery implements UseCaseInput<Question> {
    String quizId;
    String questionId;
}
