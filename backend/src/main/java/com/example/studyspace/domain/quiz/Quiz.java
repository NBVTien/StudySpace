package com.example.studyspace.domain.quiz;

import com.example.studyspace.domain.common.models.Entity;
import com.example.studyspace.domain.common.models.EntityId;
import com.example.studyspace.domain.quiz.valueobjects.Question;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private final ArrayList<Question> questions;
    /**
     * The title of the quiz.
     */
    private String title;
    /**
     * The description of the quiz.
     */
    private String description;
    /**
     * The difficulty level of the quiz.
     */
    private String difficulty;
    /**
     * The estimated time to complete the quiz in minutes.
     */
    private int estimatedTimeInMinutes;
    /**
     * The tags associated with the quiz.
     */
    private List<String> tags;
    /**
     * The ID of the owner of the quiz.
     */
    private final EntityId ownerId;

    /**
     * Constructor for creating a new Quiz object.
     *
     * @param id          The unique identifier for the quiz.
     * @param title       The title of the quiz.
     * @param description The description of the quiz.
     * @param createdAt   The creation timestamp of the quiz.
     * @param updatedAt   The last updated timestamp of the quiz.
     */
    protected Quiz(
        EntityId id,
        ArrayList<Question> questions,
        String title,
        String description,
        String difficulty,
        int estimatedTimeInMinutes,
        List<String> tags,
        EntityId ownerId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        super(id, createdAt, updatedAt);
        this.questions = questions;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.tags = tags;
        this.ownerId = ownerId;
    }

    /**
     * Factory method to create a new Quiz object.
     *
     * @param title       The title of the quiz.
     * @param description The description of the quiz.
     * @return A new Quiz object.
     */
    public static Quiz create(
        ArrayList<Question> questions,
        String title,
        String description,
        String difficulty,
        int estimatedTimeInMinutes,
        List<String> tags,
        EntityId ownerId
    ) {
        return new Quiz(EntityId.generate(),
                questions,
                title,
                description,
                difficulty,
                estimatedTimeInMinutes,
                tags,
                ownerId,
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    /**
     * This method updates the title and description of the quiz and sets the updated timestamp to the current time.
     *
     * @param title       The new title of the quiz.
     * @param description The new description of the quiz.
     */
    public void update(
        ArrayList<Question> questions,
        String title,
        String description,
        String difficulty,
        int estimatedTimeInMinutes,
        List<String> tags
    ) {
        this.questions.clear();
        this.questions.addAll(questions);
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.tags = tags;
        super.update();
    }
}
