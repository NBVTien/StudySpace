package com.example.studyspace.domain.quiz;

import com.example.studyspace.domain.common.models.Entity;
import com.example.studyspace.domain.common.models.EntityId;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents a quiz in the system.
 * This class contains the title, description, and a list of question IDs associated with the quiz.
 * It extends the <code>Entity</code> class to inherit common entity properties.
 *
 * @version 1.0
 */
@Getter
public class Quiz extends Entity {
    /**
     * The list of question IDs associated with the quiz.
     */
    private final ArrayList<EntityId> questionIds;
    /**
     * The title of the quiz.
     */
    private String title;
    /**
     * The description of the quiz.
     */
    private String description;

    /**
     * Constructor for creating a new Quiz object.
     *
     * @param id          The unique identifier for the quiz.
     * @param title       The title of the quiz.
     * @param description The description of the quiz.
     * @param createdAt   The creation timestamp of the quiz.
     * @param updatedAt   The last updated timestamp of the quiz.
     */
    protected Quiz(EntityId id,
                   String title,
                   String description,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.questionIds = new ArrayList<>();
        this.title = title;
        this.description = description;
    }

    /**
     * Factory method to create a new Quiz object.
     *
     * @param title       The title of the quiz.
     * @param description The description of the quiz.
     * @return A new Quiz object.
     */
    public static Quiz create(String title,
                              String description) {
        return new Quiz(EntityId.generate(),
                title,
                description,
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    /**
     * This method updates the title and description of the quiz and sets the updated timestamp to the current time.
     *
     * @param title       The new title of the quiz.
     * @param description The new description of the quiz.
     */
    public void update(String title,
                       String description) {
        this.title = title;
        this.description = description;
        super.update();
    }

    /**
     * This method adds a question ID to the quiz.
     *
     * @param questionId The ID of the question to be added.
     */
    public void addQuestion(EntityId questionId) {
        this.questionIds.add(questionId);
    }

    /**
     * This method removes a question ID from the quiz.
     *
     * @param questionId The ID of the question to be removed.
     */
    public void removeQuestion(EntityId questionId) {
        this.questionIds.remove(questionId);
    }
}
