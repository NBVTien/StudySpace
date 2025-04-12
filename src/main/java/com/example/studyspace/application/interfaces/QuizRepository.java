package com.example.studyspace.application.interfaces;

import com.example.studyspace.domain.quiz.Quiz;

import java.util.List;
import java.util.UUID;

public interface QuizRepository {
    List<Quiz> getAll();
    Quiz getById(UUID id);
    void save(Quiz quiz);
    void update(UUID id, Quiz quiz);
    void delete(UUID id);
}
