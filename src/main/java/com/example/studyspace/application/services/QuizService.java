package com.example.studyspace.application.services;

import com.example.studyspace.api.contracts.quizzes.QuizRequest;
import com.example.studyspace.api.mapper.QuizMapper;
import com.example.studyspace.application.common.exceptions.QuizNotFoundException;
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

    public Quiz getById(String id) {
        // Validate
        UUID uuid;
        uuid = UUID.fromString(id);

        // Execute
        Quiz quiz = quizRepository.getById(uuid);
        if (quiz == null) {
            throw new QuizNotFoundException();
        }
        return quiz;
    }

    public void save(Quiz quiz) {
        quizRepository.save(quiz);
    }

    public void update(String id, Quiz quiz) {
        // Validate
        UUID uuid;
        uuid = UUID.fromString(id);

        // Execute
        Quiz existingQuiz = quizRepository.getById(uuid);
        if (existingQuiz == null) {
            throw new QuizNotFoundException();
        }
        quizRepository.update(uuid, quiz);
    }

    public void delete(String id) {
        // Validate
        UUID uuid;
        uuid = UUID.fromString(id);

        // Execute
        Quiz existingQuiz = quizRepository.getById(uuid);
        if (existingQuiz == null) {
            throw new QuizNotFoundException();
        }
        quizRepository.delete(uuid);
    }
}
