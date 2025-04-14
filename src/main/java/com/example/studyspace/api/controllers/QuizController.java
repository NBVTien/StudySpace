package com.example.studyspace.api.controllers;

import com.example.studyspace.api.contracts.quizzes.QuizRequest;
import com.example.studyspace.application.common.services.UseCaseMediator;
import com.example.studyspace.application.quiz.commands.CreateQuizCommand;
import com.example.studyspace.application.quiz.commands.DeleteQuizCommand;
import com.example.studyspace.application.quiz.commands.UpdateQuizCommand;
import com.example.studyspace.application.quiz.queries.ReadQuizQuery;
import com.example.studyspace.application.quiz.queries.ReadQuizzesQuery;
import com.example.studyspace.domain.quiz.Quiz;
import com.example.studyspace.api.contracts.quizzes.QuizResponse;
import com.example.studyspace.api.mapper.QuizMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuizController {
    private final UseCaseMediator mediator;

    public QuizController(UseCaseMediator mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/quizzes")
    public ResponseEntity<List<QuizResponse>> getAllQuizzes() {
        var query = new ReadQuizzesQuery();
        List<Quiz> quizzes = mediator.execute(query);
        return ResponseEntity.ok(QuizMapper.quizResponses(quizzes));
    }

    @GetMapping("/quizzes/{id}")
    public ResponseEntity<QuizResponse> getQuizById(@PathVariable String id) {
        var query = new ReadQuizQuery(id);
        Quiz quiz = mediator.execute(query);
        return ResponseEntity.ok(QuizMapper.quizResponse(quiz));
    }

    @PostMapping("/quizzes")
    public ResponseEntity<QuizResponse> createQuiz(@RequestBody QuizRequest request) {
        var quizDto = QuizMapper.quizDto(request);
        var command = new CreateQuizCommand(quizDto);
        var response = QuizMapper.quizResponse(mediator.execute(command));
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/quizzes/{id}")
    public ResponseEntity<QuizResponse> updateQuiz(@PathVariable String id, @RequestBody QuizRequest request) {
        var quizDto = QuizMapper.quizDto(request);
        var command = new UpdateQuizCommand(id, quizDto);
        var response = QuizMapper.quizResponse(mediator.execute(command));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/quizzes/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable String id) {
        var command = new DeleteQuizCommand(id);
        mediator.execute(command);
        return ResponseEntity.noContent().build();
    }
}
