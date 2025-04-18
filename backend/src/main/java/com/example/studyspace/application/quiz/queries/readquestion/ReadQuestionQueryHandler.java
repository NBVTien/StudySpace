package com.example.studyspace.application.quiz.queries.readquestion;

import com.example.studyspace.application.common.exceptions.QuestionNotFoundException;
import com.example.studyspace.application.common.interfaces.repositories.QuestionRepository;
import com.example.studyspace.application.common.interfaces.usecases.UseCase;
import com.example.studyspace.domain.quiz.entities.Question;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Query handler for reading a question.
 * This class is responsible for handling the retrieval of a question from the system.
 * It uses the <code>QuestionRepository</code> to fetch the question from the database.
 *
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ReadQuestionQueryHandler implements UseCase<ReadQuestionQuery, Question> {
    QuestionRepository questionRepository;

    /**
     * Executes the query to read a question.
     *
     * @param query The query containing the details of the question to be read.
     * @return The <code>Question</code> object if found, otherwise throws <code>QuestionNotFoundException</code>.
     */
    @Override
    public Question execute(ReadQuestionQuery query) {
        var id = UUID.fromString(query.getQuestionId());

        Question question = questionRepository.getById(id);
        if (question == null) {
            throw new QuestionNotFoundException();
        }
        if (!question.getQuizId().getValue().toString().equals(query.getQuizId())) {
            throw new QuestionNotFoundException();
        }
        return question;
    }
}
