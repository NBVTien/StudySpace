package com.example.studyspace.application.quiz.queries.readquestions;

import com.example.studyspace.application.common.interfaces.repositories.QuestionRepository;
import com.example.studyspace.application.common.interfaces.usecases.UseCase;
import com.example.studyspace.domain.quiz.entities.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReadQuestionsQueryHandler implements UseCase<ReadQuestionsQuery, List<Question>> {
    private final QuestionRepository questionRepository;

    public ReadQuestionsQueryHandler(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> execute(ReadQuestionsQuery query) {
        return questionRepository.getAllByQuizId(UUID.fromString(query.getQuizId()));
    }
}
