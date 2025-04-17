package com.example.studyspace.api.controllers;

import com.example.studyspace.api.contracts.quizzes.QuestionRequest;
import com.example.studyspace.api.contracts.quizzes.QuestionResponse;
import com.example.studyspace.api.contracts.quizzes.QuizRequest;
import com.example.studyspace.api.mappers.QuestionMapper;
import com.example.studyspace.application.common.services.UseCaseMediator;
import com.example.studyspace.application.quiz.commands.createquestion.CreateQuestionCommand;
import com.example.studyspace.application.quiz.commands.createquiz.CreateQuizCommand;
import com.example.studyspace.application.quiz.commands.deletequestion.DeleteQuestionCommand;
import com.example.studyspace.application.quiz.commands.deletequiz.DeleteQuizCommand;
import com.example.studyspace.application.quiz.commands.updatequestion.UpdateQuestionCommand;
import com.example.studyspace.application.quiz.commands.updatequiz.UpdateQuizCommand;
import com.example.studyspace.application.quiz.queries.readquestion.ReadQuestionQuery;
import com.example.studyspace.application.quiz.queries.readquestions.ReadQuestionsQuery;
import com.example.studyspace.application.quiz.queries.readquiz.ReadQuizQuery;
import com.example.studyspace.application.quiz.queries.readquizzes.ReadQuizzesQuery;
import com.example.studyspace.domain.quiz.Quiz;
import com.example.studyspace.api.contracts.quizzes.QuizResponse;
import com.example.studyspace.api.mappers.QuizMapper;
import com.example.studyspace.domain.quiz.entities.Question;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <code>QuizController</code> is responsible for handling HTTP requests related to quizzes and their questions.
 * It provides endpoints for creating, reading, updating, and deleting quizzes and questions.
 *
 * @version 1.0
 */
@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class QuizController {
    /**
     * <code>UseCaseMediator</code> is used to execute commands and queries.
     */
    UseCaseMediator mediator;

    /**
     * Retrieves all quizzes.
     *
     * @return A <code>ResponseEntity</code> containing a list of <code>QuizResponse</code> objects.
     *
     */
    @GetMapping()
    public ResponseEntity<List<QuizResponse>> getAllQuizzes() {
        var query = new ReadQuizzesQuery();
        List<Quiz> quizzes = mediator.execute(query);
        return ResponseEntity.ok(QuizMapper.quizResponses(quizzes));
    }

    /**
     * Retrieves a quiz by its ID.
     *
     * @param id The unique identifier of the quiz.
     * @return A <code>ResponseEntity</code> containing the <code>QuizResponse</code> object.
     *
     */
    @GetMapping("/{id}")
    public ResponseEntity<QuizResponse> getQuizById(@PathVariable String id) {
        var query = new ReadQuizQuery(id);
        Quiz quiz = mediator.execute(query);
        return ResponseEntity.ok(QuizMapper.quizResponse(quiz));
    }

    /**
     * Creates a new quiz.
     *
     * @param request The QuizRequest object containing the details of the quiz to be created.
     * @return A <code>ResponseEntity</code> containing the created <code>QuizResponse</code> object.
     *
     */
    @PostMapping()
    public ResponseEntity<QuizResponse> createQuiz(@RequestBody QuizRequest request) {
        var quizDto = QuizMapper.quizDto(request);
        var command = new CreateQuizCommand(quizDto);
        var response = QuizMapper.quizResponse(mediator.execute(command));
        return ResponseEntity.status(201).body(response);
    }

    /**
     * Updates an existing quiz.
     *
     * @param id      The unique identifier of the quiz to be updated.
     * @param request The QuizRequest object containing the updated details of the quiz.
     * @return A <code>ResponseEntity</code> containing the updated <code>QuestionResponse</code> object.
     *
     */
    @PutMapping("/{id}")
    public ResponseEntity<QuizResponse> updateQuiz(@PathVariable String id, @RequestBody QuizRequest request) {
        var quizDto = QuizMapper.quizDto(request);
        var command = new UpdateQuizCommand(id, quizDto);
        var response = QuizMapper.quizResponse(mediator.execute(command));
        return ResponseEntity.ok(response);
    }

    /**
     * Deletes a quiz by its ID.
     *
     * @param id The unique identifier of the quiz to be deleted.
     * @return A <code>ResponseEntity</code> with no content.
     *
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable String id) {
        var command = new DeleteQuizCommand(id);
        mediator.execute(command);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves all questions for a specific quiz.
     *
     * @param id The unique identifier of the quiz.
     * @return A <code>ResponseEntity</code> containing a list of <code>QuestionResponse</code> objects.
     *
     */
    @GetMapping("{id}/questions")
    public ResponseEntity<List<QuestionResponse>> getQuestions(@PathVariable String id) {
        var query = new ReadQuestionsQuery(id);
        List<Question> questions = mediator.execute(query);
        return ResponseEntity.ok(QuestionMapper.questionResponses(questions));
    }

    /**
     * Retrieves a specific question by its ID within a quiz.
     *
     * @param quizId      The unique identifier of the quiz.
     * @param questionId  The unique identifier of the question.
     * @return A <code>ResponseEntity</code> containing the <code>QuestionResponse</code> object.
     *
     */
    @GetMapping("{quizId}/questions/{questionId}")
    public ResponseEntity<QuestionResponse> getQuestionById(
        @PathVariable String quizId,
        @PathVariable String questionId) {
        var query = new ReadQuestionQuery(quizId, questionId);
        Question question = mediator.execute(query);
        return ResponseEntity.ok(QuestionMapper.questionResponse(question));
    }

    /**
     * Creates a new question within a specific quiz.
     *
     * @param id      The unique identifier of the quiz.
     * @param request The <code>QuestionRequest</code> object containing the details of the question to be created.
     * @return A <code>ResponseEntity</code> containing the created QuestionResponse object.
     *
     */
    @PostMapping("{id}/questions")
    public ResponseEntity<QuestionResponse> createQuestion(@PathVariable String id, @RequestBody QuestionRequest request) {
        var questionDto = QuestionMapper.questionDto(request);
        var command = new CreateQuestionCommand(id, questionDto);
        var response = QuestionMapper.questionResponse(mediator.execute(command));
        return ResponseEntity.status(201).body(response);
    }

    /**
     * Updates an existing question within a specific quiz.
     *
     * @param quizId      The unique identifier of the quiz.
     * @param questionId  The unique identifier of the question to be updated.
     * @param request     The QuestionRequest object containing the updated details of the question.
     * @return A <code>ResponseEntity</code> containing the updated QuestionResponse object.
     *
     */
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

    /**
     * Deletes a specific question within a quiz.
     *
     * @param quizId      The unique identifier of the quiz.
     * @param questionId  The unique identifier of the question to be deleted.
     * @return A <code>ResponseEntity</code> with no content.
     *
     */
    @DeleteMapping("{quizId}/questions/{questionId}")
    public ResponseEntity<Void> deleteQuestion(
        @PathVariable String quizId,
        @PathVariable String questionId) {
        var command = new DeleteQuestionCommand(quizId, questionId);
        mediator.execute(command);
        return ResponseEntity.noContent().build();
    }
}
