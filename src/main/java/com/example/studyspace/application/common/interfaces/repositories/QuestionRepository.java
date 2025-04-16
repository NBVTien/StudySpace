package com.example.studyspace.application.common.interfaces.repositories;

import com.example.studyspace.domain.quiz.entities.Question;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository {
    List<Question> getAll();
    List<Question> getAllByQuizId(UUID quizId);
    Question getById(UUID id);
    void save(Question question);
    void update(UUID id, Question question);
    void delete(UUID id);
}