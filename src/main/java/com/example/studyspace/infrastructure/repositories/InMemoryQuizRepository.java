package com.example.studyspace.infrastructure.repositories;

import com.example.studyspace.application.common.interfaces.repositories.QuizRepository;
import com.example.studyspace.domain.quiz.Quiz;
import com.example.studyspace.domain.quiz.entities.Question;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class InMemoryQuizRepository implements QuizRepository {
    private List<Quiz> quizzes = new ArrayList<>(List.of(
        Quiz.create(
            "Geography Quiz",
            "A quiz about world geography"
        ),
        Quiz.create(
            "Science Quiz",
            "A quiz about basic science"
        )
    ));

    @Override
    public List<Quiz> getAll() {
        return quizzes;
    }

    @Override
    public Quiz getById(UUID id) {
        Quiz quiz = quizzes.stream()
            .filter(q -> q.getId().getValue().equals(id))
            .findFirst()
            .orElse(null);
        return quiz;
    }

    @Override
    public void save(Quiz quiz) {
        quizzes.add(quiz);
    }

    @Override
    public void update(UUID id, Quiz quiz) {
        Quiz existingQuiz = getById(id);
        quizzes.remove(existingQuiz);
        quizzes.add(quiz);
    }

    @Override
    public void delete(UUID id) {
        Quiz quiz = getById(id);
        if (quiz != null) {
            quizzes.remove(quiz);
        }
    }
}
