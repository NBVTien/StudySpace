package com.example.studyspace.api.controllers;

import com.example.studyspace.api.contracts.quizzes.QuizRequest;
import com.example.studyspace.application.common.models.ListQuery;
import com.example.studyspace.application.common.models.PaginatedResult;
import com.example.studyspace.application.common.services.UseCaseMediator;
import com.example.studyspace.application.quiz.commands.createquiz.CreateQuizCommand;
import com.example.studyspace.application.quiz.commands.deletequiz.DeleteQuizCommand;
import com.example.studyspace.application.quiz.commands.updatequiz.UpdateQuizCommand;
import com.example.studyspace.application.quiz.queries.readquiz.ReadQuizQuery;
import com.example.studyspace.application.quiz.queries.readquizzes.ReadQuizzesQuery;
import com.example.studyspace.domain.quiz.Quiz;
import com.example.studyspace.api.contracts.quizzes.QuizResponse;
import com.example.studyspace.api.mappers.QuizMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * <code>QuizController</code> is responsible for handling HTTP requests related to quizzes and their questions.
 * It provides endpoints for creating, reading, updating, and deleting quizzes and questions.
 *
 * @version 1.0
 */
@RestController
@RequestMapping("/quizzes")
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
    public ResponseEntity<PaginatedResult<QuizResponse>> getAllQuizzes(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "3") int pageSize
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getDetails();
        var listQuery = ListQuery.builder()
            .page(page)
            .pageSize(pageSize)
            .build();

        var query = new ReadQuizzesQuery(userId, listQuery);
        PaginatedResult<Quiz> quizzes = mediator.execute(query);
        return ResponseEntity.ok(QuizMapper.paginatedQuizResponses(quizzes));
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getDetails();

        var command = new CreateQuizCommand(quizDto, userId);
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
}
