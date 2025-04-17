package com.example.studyspace.application.quiz.queries.readquiz;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseInput;
import com.example.studyspace.domain.quiz.Quiz;
import lombok.Value;

/**
 * Query for reading a quiz.
 * This class is used to encapsulate the input parameters required to read a quiz from the system.
 *
 * @version 1.0
 */
@Value
public class ReadQuizQuery implements UseCaseInput<Quiz> {
    String quizId;
}