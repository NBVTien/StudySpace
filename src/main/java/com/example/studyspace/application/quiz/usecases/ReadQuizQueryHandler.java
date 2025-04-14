package com.example.studyspace.application.quiz.usecases;

import com.example.studyspace.application.common.exceptions.QuizNotFoundException;
import com.example.studyspace.application.common.interfaces.QuizRepository;
import com.example.studyspace.application.common.interfaces.UseCase;
import com.example.studyspace.application.quiz.queries.ReadQuizQuery;
import com.example.studyspace.domain.quiz.Quiz;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReadQuizQueryHandler implements UseCase<ReadQuizQuery, Quiz> {
    private final QuizRepository quizRepository;

    public ReadQuizQueryHandler(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public Quiz execute(ReadQuizQuery readQuizQuery) {
        var id = readQuizQuery.quizId();

        // Validate
        UUID uuid;
        uuid = UUID.fromString(id);

        // Execute
        Quiz quiz = quizRepository.getById(uuid);
        if (quiz == null) {
            throw new QuizNotFoundException();
        }
        return quiz;
    }
}
