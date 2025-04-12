package com.example.studyspace.infrastructure.repositories;

import com.example.studyspace.application.interfaces.QuizRepository;
import com.example.studyspace.domain.quiz.Quiz;
import com.example.studyspace.domain.quiz.entities.Question;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class InMemoryQuizRepository implements QuizRepository {
    private final List<Quiz> quizzes = List.of(
        Quiz.create(
            List.of(
                Question.create(
                    "What is the capital of France?",
                    List.of("Berlin", "Madrid", "Paris", "Rome"),
                    "Paris"
                ),
                Question.create(
                    "What is the largest planet in our solar system?",
                    List.of("Earth", "Jupiter", "Mars", "Saturn"),
                    "Jupiter"
                ),
                Question.create(
                    "What is the smallest country in the world?",
                    List.of("Vatican City", "Monaco", "Nauru", "Malta"),
                    "Vatican City"
                )
            ),
            "Geography Quiz",
            "A quiz about world geography"
        ),
        Quiz.create(
            List.of(
                Question.create(
                    "What is the chemical symbol for gold?",
                    List.of("Au", "Ag", "Pb", "Fe"),
                    "Au"
                ),
                Question.create(
                    "What is the speed of light?",
                    List.of("300,000 km/s", "150,000 km/s", "1,000,000 km/s", "3,000 km/s"),
                    "300,000 km/s"
                ),
                Question.create(
                    "What is the most abundant gas in the Earth's atmosphere?",
                    List.of("Oxygen", "Nitrogen", "Carbon Dioxide", "Hydrogen"),
                    "Nitrogen"
                )
            ),
            "Science Quiz",
            "A quiz about basic science"
        )
    );

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
        // Not implemented in this in-memory repository
    }

    @Override
    public void delete(UUID id) {
        Quiz quiz = getById(id);
        if (quiz != null) {
            quizzes.remove(quiz);
        }
    }
}
