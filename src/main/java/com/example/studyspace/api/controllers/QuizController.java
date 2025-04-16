package com.example.studyspace.api.controllers;

import com.example.studyspace.api.contracts.quizzes.QuestionRequest;
import com.example.studyspace.api.contracts.quizzes.QuestionResponse;
import com.example.studyspace.api.contracts.quizzes.QuizRequest;
import com.example.studyspace.api.mappers.QuestionMapper;
import com.example.studyspace.application.common.services.UseCaseMediator;
import com.example.studyspace.application.quiz.commands.createquestion.CreateQuestionCommand;
import com.example.studyspace.application.quiz.commands.createquiz.CreateQuizCommand;
import com.example.studyspace.application.quiz.commands.deletequiz.DeleteQuizCommand;
import com.example.studyspace.application.quiz.commands.updatequestion.UpdateQuestionCommand;
import com.example.studyspace.application.quiz.commands.updatequiz.UpdateQuizCommand;
import com.example.studyspace.application.quiz.queries.readquestion.ReadQuestionQuery;
import com.example.studyspace.application.quiz.queries.readquestions.ReadQuestionsQuery;
import com.example.studyspace.application.quiz.queries.readquizbyid.ReadQuizQuery;
import com.example.studyspace.application.quiz.queries.readquizzes.ReadQuizzesQuery;
import com.example.studyspace.domain.quiz.Quiz;
import com.example.studyspace.api.contracts.quizzes.QuizResponse;
import com.example.studyspace.api.mappers.QuizMapper;
import com.example.studyspace.domain.quiz.entities.Question;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    private final UseCaseMediator mediator;

    public QuizController(UseCaseMediator mediator) {
        this.mediator = mediator;
    }

    @GetMapping()
    public ResponseEntity<List<QuizResponse>> getAllQuizzes() {
        var query = new ReadQuizzesQuery();
        List<Quiz> quizzes = mediator.execute(query);
        return ResponseEntity.ok(QuizMapper.quizResponses(quizzes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizResponse> getQuizById(@PathVariable String id) {
        var query = new ReadQuizQuery(id);
        Quiz quiz = mediator.execute(query);
        return ResponseEntity.ok(QuizMapper.quizResponse(quiz));
    }

    @PostMapping()
    public ResponseEntity<QuizResponse> createQuiz(@RequestBody QuizRequest request) {
        var quizDto = QuizMapper.quizDto(request);
        var command = new CreateQuizCommand(quizDto);
        var response = QuizMapper.quizResponse(mediator.execute(command));
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizResponse> updateQuiz(@PathVariable String id, @RequestBody QuizRequest request) {
        var quizDto = QuizMapper.quizDto(request);
        var command = new UpdateQuizCommand(id, quizDto);
        var response = QuizMapper.quizResponse(mediator.execute(command));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable String id) {
        var command = new DeleteQuizCommand(id);
        mediator.execute(command);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}/questions")
    public ResponseEntity<List<QuestionResponse>> getQuestions(@PathVariable String id) {
        var query = new ReadQuestionsQuery(id);
        List<Question> questions = mediator.execute(query);
        return ResponseEntity.ok(QuestionMapper.questionResponses(questions));
    }

    @GetMapping("{quizId}/questions/{questionId}")
    public ResponseEntity<QuestionResponse> getQuestionById(
        @PathVariable String quizId,
        @PathVariable String questionId) {
        var query = new ReadQuestionQuery(quizId, questionId);
        Question question = mediator.execute(query);
        return ResponseEntity.ok(QuestionMapper.questionResponse(question));
    }

    @PostMapping("{id}/questions")
    public ResponseEntity<QuestionResponse> createQuestion(@PathVariable String id, @RequestBody QuestionRequest request) {
        var questionDto = QuestionMapper.questionDto(request);
        var command = new CreateQuestionCommand(id, questionDto);
        var response = QuestionMapper.questionResponse(mediator.execute(command));
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("{quizId}/questions/{questionId}")
    public ResponseEntity<QuestionResponse> updateQuestion(
        @PathVariable String quizId,
        @PathVariable String questionId,
        @RequestBody QuestionRequest request) {
        var questionDto = QuestionMapper.questionDto(request);
        var command = new UpdateQuestionCommand(quizId, questionId, questionDto);
        var response = QuestionMapper.questionResponse(mediator.execute(command));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{quizId}/questions/{questionId}")
    public ResponseEntity<Void> deleteQuestion(
        @PathVariable String quizId,
        @PathVariable String questionId) {
        return null;
    }
}
