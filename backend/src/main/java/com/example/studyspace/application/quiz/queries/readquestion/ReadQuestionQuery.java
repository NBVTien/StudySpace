package com.example.studyspace.application.quiz.queries.readquestion;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseInput;
import com.example.studyspace.domain.quiz.entities.Question;
import lombok.Value;

/**
 * Query for reading a question.
 * This class is used to encapsulate the input parameters required to read a question from the system.
 *
 * @version 1.0
 */
@Value
public class ReadQuestionQuery implements UseCaseInput<Question> {
    String quizId;
    String questionId;
}
