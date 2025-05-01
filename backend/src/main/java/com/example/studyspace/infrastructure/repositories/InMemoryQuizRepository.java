package com.example.studyspace.infrastructure.repositories;

import com.example.studyspace.application.common.interfaces.repositories.QuizRepository;
import com.example.studyspace.application.common.interfaces.repositories.UserRepository;
import com.example.studyspace.domain.quiz.Quiz;
import com.example.studyspace.domain.quiz.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class InMemoryQuizRepository implements QuizRepository {
    private final UserRepository userRepository;
    private final List<Quiz> quizzes = new ArrayList<>();

    public InMemoryQuizRepository(UserRepository userRepository) {
        this.userRepository = userRepository;

        quizzes.add(Quiz.create(
            "Math Quiz",
            "A quiz about basic math",
            "Easy",
            10,
            List.of("math", "easy"),
            this.userRepository.getByUsername("admin").getId()
        ));
        quizzes.add(Quiz.create(
            "Science Quiz",
            "A quiz about basic science",
            "Medium",
            15,
            List.of("science", "medium"),
            this.userRepository.getByUsername("admin").getId()
        ));
        quizzes.add(Quiz.create(
            "History Quiz",
            "A quiz about basic history",
            "Hard",
            20,
            List.of("history", "hard"),
            this.userRepository.getByUsername("user").getId()
        ));
    }

    @Override
    public List<Quiz> getAll() {
        return quizzes;
    }

    @Override
    public List<Quiz> getAllByOwnerId(UUID ownerId) {
        List<Quiz> ownedQuizzes = new ArrayList<>();
        for (Quiz quiz : quizzes) {
            if (quiz.getOwnerId().getValue().equals(ownerId)) {
                ownedQuizzes.add(quiz);
            }
        }
        return ownedQuizzes;
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
