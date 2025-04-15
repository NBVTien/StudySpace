package com.example.studyspace.domain.quiz;

import com.example.studyspace.domain.common.models.Entity;
import com.example.studyspace.domain.common.models.EntityId;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
public class Quiz extends Entity {
    private ArrayList<EntityId> questionIds;
    private String title;
    private String description;

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

    public static Quiz create(String title,
                              String description) {
        return new Quiz(EntityId.generate(),
                title,
                description,
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    public void update(String title,
                       String description) {
        this.title = title;
        this.description = description;
        super.update();
    }

    public void addQuestion(EntityId questionId) {
        this.questionIds.add(questionId);
    }

    public void removeQuestion(EntityId questionId) {
        this.questionIds.remove(questionId);
    }
}
