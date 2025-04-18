package com.example.studyspace.infrastructure.repositories;

import com.example.studyspace.application.common.interfaces.repositories.QuestionRepository;
import com.example.studyspace.domain.quiz.entities.Question;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class InMemoryQuestionRepository implements QuestionRepository {
    private List<Question> questions = new ArrayList<>();

    @Override
    public List<Question> getAll() {
        return List.of();
    }

    @Override
    public List<Question> getAllByQuizId(UUID quizId) {
        return questions.stream()
                .filter(question -> question.getQuizId().getValue().equals(quizId))
                .toList();
    }

    @Override
    public Question getById(UUID id) {
        return questions.stream()
                .filter(question -> question.getId().getValue().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Question question) {
        questions.add(question);
    }

    @Override
    public void update(UUID id, Question question) {

    }

    @Override
    public void delete(UUID id) {

    }
}
