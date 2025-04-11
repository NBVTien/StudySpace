package com.example.studyspace.api.controllers;

import com.example.studyspace.application.services.QuizService;
import com.example.studyspace.domain.common.models.EntityId;
import com.example.studyspace.domain.quiz.Quiz;
import com.example.studyspace.api.contracts.quizzes.QuizResponse;
import com.example.studyspace.api.mapper.QuizMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/quizzes")
    public List<QuizResponse> getAllQuizzes() {
        return QuizMapper.quizResponses(quizService.getAll());
    }

    @GetMapping("/quizzes/{id}")
    public Quiz getQuizById(@PathVariable UUID id) {
        return quizService.getById(id);
    }

    @PostMapping("/quizzes")
    public void createQuiz(Quiz quiz) {
        quizService.save(quiz);
    }

    @PostMapping("/quizzes/{id}")
    public void updateQuiz(@PathVariable UUID id, Quiz quiz) {
        quiz.setId(EntityId.create(id));
        quizService.update(quiz);
    }

    @PostMapping("/quizzes/{id}/delete")
    public void deleteQuiz(@PathVariable String id) {
        quizService.delete(id);
    }
}
