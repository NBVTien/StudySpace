package com.example.studyspace.application.common.interfaces.repositories;

import com.example.studyspace.domain.quiz.entities.Question;

import java.util.List;
import java.util.UUID;

/**
 * <code>QuestionRepository</code> is an interface that defines the contract for a repository that manages
 * <code>Question</code> entities. It provides methods to access and manipulate questions in the data store.
 *
 * @version 1.0
 */
public interface QuestionRepository {
    /**
     * Retrieves all questions from the data store.
     *
     * @return A list of <code>Question</code> objects.
     *
     */
    @SuppressWarnings("unused")
    List<Question> getAll();

    /**
     * Retrieves all questions associated with a specific quiz ID.
     *
     * @param quizId The unique identifier of the quiz.
     * @return A list of <code>Question</code> objects associated with the specified quiz ID.
     *
     */
    List<Question> getAllByQuizId(UUID quizId);

    /**
     * Retrieves a question by its unique identifier.
     *
     * @param id The unique identifier of the question.
     * @return The <code>Question</code> object associated with the specified ID.
     *
     */
    Question getById(UUID id);

    /**
     * Saves a new question to the data store.
     *
     * @param question The <code>Question</code> object to be saved.
     *
     */
    void save(Question question);

    /**
     * Updates an existing question in the data store.
     *
     * @param id The unique identifier of the question to be updated.
     * @param question The <code>Question</code> object with updated values.
     *
     */
    void update(UUID id, Question question);

    /**
     * Deletes a question from the data store.
     *
     * @param id The unique identifier of the question to be deleted.
     *
     */
    void delete(UUID id);
}