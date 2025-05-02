package com.example.studyspace.application.quiz.queries.readquizzes;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseInput;
import com.example.studyspace.application.common.models.ListQuery;
import com.example.studyspace.application.common.models.PaginatedResult;
import com.example.studyspace.domain.quiz.Quiz;
import lombok.Value;

/**
 * Query for reading quizzes.
 * This class is used to encapsulate the input parameters required to read quizzes from the system.
 *
 * @version 1.0
 */
@Value
public class ReadQuizzesQuery implements UseCaseInput<PaginatedResult<Quiz>> {
    String ownerId;
    ListQuery request;
}
