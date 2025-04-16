package com.example.studyspace.application.quiz.queries.readquestion;

import com.example.studyspace.application.common.exceptions.QuestionNotFoundException;
import com.example.studyspace.application.common.interfaces.repositories.QuestionRepository;
import com.example.studyspace.application.common.interfaces.usecases.UseCase;
import com.example.studyspace.domain.quiz.entities.Question;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReadQuestionQueryHandler implements UseCase<ReadQuestionQuery, Question> {
    private final QuestionRepository questionRepository;

    public ReadQuestionQueryHandler(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

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
