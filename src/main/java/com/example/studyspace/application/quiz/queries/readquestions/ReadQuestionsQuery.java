package com.example.studyspace.application.quiz.queries.readquestions;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseInput;
import com.example.studyspace.domain.quiz.entities.Question;
import lombok.Value;

import java.util.List;

@Value
public class ReadQuestionsQuery implements UseCaseInput<List<Question>> {
    String quizId;
}
