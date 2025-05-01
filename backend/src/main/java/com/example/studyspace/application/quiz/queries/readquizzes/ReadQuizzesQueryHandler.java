package com.example.studyspace.application.quiz.queries.readquizzes;

import com.example.studyspace.application.common.interfaces.repositories.QuizRepository;
import com.example.studyspace.application.common.interfaces.usecases.UseCase;
import com.example.studyspace.domain.quiz.Quiz;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Query handler for reading quizzes.
 * This class is responsible for handling the ReadQuizzesQuery and returning a list of quizzes.
 *
 * @version 1.0
 */
@Service
public class ReadQuizzesQueryHandler implements UseCase<ReadQuizzesQuery, List<Quiz>> {

    private final QuizRepository quizRepository;

    public ReadQuizzesQueryHandler(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    /**
     * Handles the ReadQuizzesQuery and returns a list of quizzes.
     *
     * @param query The query containing the parameters for reading quizzes.
     * @return A list of quizzes.
     */
    @Override
    public List<Quiz> execute(ReadQuizzesQuery query) {
        return quizRepository.getAllByOwnerId(UUID.fromString(query.getOwnerId()));
    }
}
