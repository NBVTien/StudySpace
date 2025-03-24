package com.example.studyspace.web.controllers;

import com.example.studyspace.application.interfaces.ReadQuizService;
import com.example.studyspace.domain.quiz.Quiz;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizController {
    private final ReadQuizService readQuizService;

    public QuizController(ReadQuizService readQuizService) {
        this.readQuizService = readQuizService;
    }

    @GetMapping("/quiz/{id}")
    public ResponseEntity<Quiz> readQuiz(@PathVariable String id) {
        return ResponseEntity.ok(readQuizService.execute(id));
    }
}
