package com.example.studyspace.application.quiz.queries.readquestions;

import com.example.studyspace.application.common.interfaces.repositories.QuestionRepository;
import com.example.studyspace.application.common.interfaces.usecases.UseCase;
import com.example.studyspace.domain.quiz.entities.Question;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Query handler for reading questions.
 * This class is responsible for handling the retrieval of questions from the system.
 * It uses the <code>QuestionRepository</code> to fetch questions from the database.
 *
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ReadQuestionsQueryHandler implements UseCase<ReadQuestionsQuery, List<Question>> {
    QuestionRepository questionRepository;

    /**
     * Executes the query to read questions.
     *
     * @param query The query containing the details of the questions to be read.
     * @return A list of <code>Question</code> objects.
     */
    @Override
    public List<Question> execute(ReadQuestionsQuery query) {
        return questionRepository.getAllByQuizId(UUID.fromString(query.getQuizId()));
    }
}
