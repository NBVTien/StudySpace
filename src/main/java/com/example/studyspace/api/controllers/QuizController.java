package com.example.studyspace.api.controllers;

import com.example.studyspace.application.services.QuizService;
import com.example.studyspace.domain.quiz.Quiz;
import com.example.studyspace.api.contracts.quizzes.QuizResponse;
import com.example.studyspace.api.mapper.QuizMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Quiz> getQuizById(@PathVariable String id) {
        var quiz = quizService.getById(id);
        return ResponseEntity.ok(quiz);
    }

    @PostMapping("/quizzes")
    public void createQuiz(Quiz quiz) {
        quizService.save(quiz);
    }

    @PutMapping("/quizzes/{id}")
    public void updateQuiz(@PathVariable String id, Quiz quiz) {
        quizService.update(id, quiz);
    }

    @DeleteMapping("/quizzes/{id}")
    public void deleteQuiz(@PathVariable String id) {
        quizService.delete(id);
    }
}
