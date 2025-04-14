package com.example.studyspace.application.quiz.usecases;

import com.example.studyspace.application.common.interfaces.QuizRepository;
import com.example.studyspace.application.common.interfaces.UseCase;
import com.example.studyspace.application.quiz.queries.ReadQuizzesQuery;
import com.example.studyspace.domain.quiz.Quiz;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadQuizzesQueryHandler implements UseCase<ReadQuizzesQuery, List<Quiz>> {

    private final QuizRepository quizRepository;

    public ReadQuizzesQueryHandler(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public List<Quiz> execute(ReadQuizzesQuery readQuizQuery) {
        return quizRepository.getAll();
    }
}
