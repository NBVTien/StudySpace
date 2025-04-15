package com.example.studyspace.application.quiz.queries.readbyid;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseValidator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ReadQuizQueryValidator implements UseCaseValidator<ReadQuizQuery> {
    @Override
    public void validate(ReadQuizQuery query) {
        try {
            UUID.fromString(query.getQuizId());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Quiz ID format.", e);
        }
    }
}