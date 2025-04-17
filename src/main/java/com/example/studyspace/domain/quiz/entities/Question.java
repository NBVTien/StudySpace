package com.example.studyspace.domain.quiz.entities;

import com.example.studyspace.domain.common.models.Entity;
import com.example.studyspace.domain.common.models.EntityId;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a question in a quiz.
 * This class contains the question text, options, correct answer, and the quiz it belongs to.
 * It extends the <code>Entity</code> class to inherit common entity properties.
 *
 * @version 1.0
 */
@Getter
public class Question extends Entity {
    /**
     * The question text.
     */
    private String question;
    /**
     * The list of options for the question.
     */
    private List<String> options;
    /**
     * The correct answer for the question.
     */
    private String correctAnswer;
    /**
     * The ID of the quiz this question belongs to.
     */
    private EntityId quizId;

    /**
     * Constructor for creating a new Question object.
     *
     * @param id            The unique identifier for the question.
     * @param question      The question text.
     * @param options       The list of options for the question.
     * @param correctAnswer The correct answer for the question.
     * @param quizId        The ID of the quiz this question belongs to.
     * @param createdAt     The creation timestamp of the question.
     * @param updatedAt     The last updated timestamp of the question.
     */
    protected Question(
        EntityId id,
        String question,
        List<String> options,
        String correctAnswer,
        EntityId quizId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.quizId = quizId;
    }

    /**
     * Factory method to create a new Question object.
     *
     * @param question      The question text.
     * @param options       The list of options for the question.
     * @param correctAnswer The correct answer for the question.
     * @param quizId        The ID of the quiz this question belongs to.
     * @return A new Question object.
     */
    public static Question create(
        String question,
        List<String> options,
        String correctAnswer,
        EntityId quizId) {
        return new Question(EntityId.generate(),
                question,
                options,
                correctAnswer,
                quizId,
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    /**
     * Updates the question with new values.
     *
     * @param question      The new question text.
     * @param options       The new list of options for the question.
     * @param correctAnswer The new correct answer for the question.
     * @param quizId        The new ID of the quiz this question belongs to.
     */
    public void update(String question,
                       List<String> options,
                       String correctAnswer,
                       EntityId quizId) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.quizId = quizId;
        super.update();
    }
}
