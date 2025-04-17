package com.example.studyspace.application.common.interfaces.repositories;

import com.example.studyspace.domain.quiz.Quiz;

import java.util.List;
import java.util.UUID;

/**
 * <code>QuizRepository</code> is an interface that defines the contract for a repository that manages
 * <code>Quiz</code> entities. It provides methods to access and manipulate quizzes in the data store.
 *
 * @version 1.0
 */
public interface QuizRepository {
    /**
     * Retrieves all quizzes from the data store.
     *
     * @return A list of <code>Quiz</code> objects.
     *
     */
    List<Quiz> getAll();

    /**
     * Retrieves a quiz by its unique identifier.
     *
     * @param id The unique identifier of the quiz.
     * @return The <code>Quiz</code> object associated with the specified ID.
     *
     */
    Quiz getById(UUID id);

    /**
     * Saves a new quiz to the data store.
     *
     * @param quiz The <code>Quiz</code> object to be saved.
     *
     */
    void save(Quiz quiz);

    /**
     * Updates an existing quiz in the data store.
     *
     * @param id The unique identifier of the quiz to be updated.
     * @param quiz The <code>Quiz</code> object with updated values.
     *
     */
    void update(UUID id, Quiz quiz);

    /**
     * Deletes a quiz from the data store.
     *
     * @param id The unique identifier of the quiz to be deleted.
     *
     */
    void delete(UUID id);
}
