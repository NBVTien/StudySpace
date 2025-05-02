package com.example.studyspace.infrastructure.repositories;

import com.example.studyspace.application.common.interfaces.repositories.QuizRepository;
import com.example.studyspace.application.common.interfaces.repositories.UserRepository;
import com.example.studyspace.application.common.models.PaginatedResult;
import com.example.studyspace.domain.quiz.Quiz;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class InMemoryQuizRepository implements QuizRepository {
    private final List<Quiz> quizzes = new ArrayList<>();

    public InMemoryQuizRepository(UserRepository userRepository) {

        quizzes.add(Quiz.create(
            new ArrayList<>(),
            "Math Quiz",
            "A quiz about basic math",
            "Easy",
            10,
            List.of("math", "easy"),
            userRepository.getByUsername("admin").getId()
        ));
        quizzes.add(Quiz.create(
            new ArrayList<>(),
            "Science Quiz",
            "A quiz about basic science",
            "Medium",
            15,
            List.of("science", "medium"),
            userRepository.getByUsername("admin").getId()
        ));
        quizzes.add(Quiz.create(
            new ArrayList<>(),
            "History Quiz",
            "A quiz about basic history",
            "Hard",
            20,
            List.of("history", "hard"),
            userRepository.getByUsername("user").getId()
        ));
    }

    @Override
    public PaginatedResult<Quiz> getAllByOwnerId(UUID ownerId, int page, int pageSize) {
        List<Quiz> filteredQuizzes = quizzes.stream()
            .filter(quiz -> quiz.getOwnerId().getValue().equals(ownerId))
            .toList();

        int total = filteredQuizzes.size();
        int totalPages = (int) Math.ceil((double) total / pageSize);
        int fromIndex = Math.min((page - 1) * pageSize, total);
        int toIndex = Math.min(fromIndex + pageSize, total);

        List<Quiz> paginatedQuizzes = filteredQuizzes.subList(fromIndex, toIndex);

        return PaginatedResult.<Quiz>builder()
            .data(paginatedQuizzes)
            .total(total)
            .totalPages(totalPages)
            .page(page)
            .pageSize(pageSize)
            .build();
    }

    @Override
    public Quiz getById(UUID id) {
        return quizzes.stream()
            .filter(q -> q.getId().getValue().equals(id))
            .findFirst()
            .orElse(null);
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
