package com.example.studyspace.application.services;

import com.example.studyspace.application.interfaces.QuizRepository;
import com.example.studyspace.domain.quiz.Quiz;

import java.util.List;
import java.util.UUID;

public class QuizService {
    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<Quiz> getAll() {
        return quizRepository.getAll();
    }

    public Quiz getById(UUID id) {
        return quizRepository.getById(id);
    }

    public void save(Quiz quiz) {
        quizRepository.save(quiz);
    }

    public void update(Quiz quiz) {
        quizRepository.update(quiz);
    }

    public void delete(String id) {
        quizRepository.delete(id);
    }
}
