package com.example.studyspace.application.quiz.queries.readquiz;

import com.example.studyspace.application.common.exceptions.QuizNotFoundException;
import com.example.studyspace.application.common.interfaces.repositories.QuizRepository;
import com.example.studyspace.application.common.interfaces.usecases.UseCase;
import com.example.studyspace.domain.quiz.Quiz;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Query handler for reading a quiz.
 * This class is responsible for handling the retrieval of a quiz from the system.
 * It uses the <code>QuizRepository</code> to fetch the quiz from the database.
 *
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ReadQuizQueryHandler implements UseCase<ReadQuizQuery, Quiz> {
    QuizRepository quizRepository;

    /**
     * Executes the query to read a quiz.
     *
     * @param query The query containing the details of the quiz to be read.
     * @return The <code>Quiz</code> object.
     */
    @Override
    public Quiz execute(ReadQuizQuery query) {
        var id = UUID.fromString(query.getQuizId());

        Quiz quiz = quizRepository.getById(id);
        if (quiz == null) {
            throw new QuizNotFoundException();
        }
        return quiz;
    }
}
