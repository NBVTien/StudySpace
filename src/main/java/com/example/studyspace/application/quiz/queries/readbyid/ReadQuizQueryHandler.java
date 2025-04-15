package com.example.studyspace.application.quiz.queries.readbyid;

import com.example.studyspace.application.common.exceptions.QuizNotFoundException;
import com.example.studyspace.application.common.interfaces.repositories.QuizRepository;
import com.example.studyspace.application.common.interfaces.usecases.UseCase;
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
        var id = readQuizQuery.getQuizId();

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
