package com.example.studyspace.application.quiz.queries.readquiz;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseValidator;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Validator for the <code>ReadQuizQuery</code>.
 * This class is responsible for validating the input parameters of the <code>ReadQuizQuery</code>.
 *
 * @version 1.0
 */
@Component
public class ReadQuizQueryValidator implements UseCaseValidator<ReadQuizQuery> {
    /**
     * Validates the <code>ReadQuizQuery</code>.
     * This method checks if the <code>quizId</code> is in a valid format.
     *
     * @param query The <code>ReadQuizQuery</code> to be validated.
     * @throws IllegalArgumentException if the <code>quizId</code> is not in a valid format.
     */
    @Override
    public void validate(ReadQuizQuery query) {
        try {
            UUID.fromString(query.getQuizId());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Quiz ID format.", e);
        }
    }
}