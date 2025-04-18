package com.example.studyspace.application.quiz.queries.readquestions;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseInput;
import com.example.studyspace.domain.quiz.entities.Question;
import lombok.Value;

import java.util.List;

/**
 * Query for reading questions.
 * This class is used to encapsulate the input parameters required to read questions from the system.
 *
 * @version 1.0
 */
@Value
public class ReadQuestionsQuery implements UseCaseInput<List<Question>> {
    String quizId;
}
