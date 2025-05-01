package com.example.studyspace.application.quiz.queries.readquizzes;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseInput;
import com.example.studyspace.domain.quiz.Quiz;
import lombok.Value;

import java.util.List;

/**
 * Query for reading quizzes.
 * This class is used to encapsulate the input parameters required to read quizzes from the system.
 *
 * @version 1.0
 */
@Value
public class ReadQuizzesQuery implements UseCaseInput<List<Quiz>> {
    String ownerId;
}
