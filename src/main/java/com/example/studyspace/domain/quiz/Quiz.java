package com.example.studyspace.domain.quiz;

import com.example.studyspace.domain.common.models.Aggregate;
import com.example.studyspace.domain.common.models.AggregateId;
import com.example.studyspace.domain.quiz.entities.Question;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Quiz extends Aggregate {
    private List<Question> questions;
    private String title;
    private String description;

    protected Quiz(AggregateId id,
                   List<Question> questions,
                   String title,
                   String description,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.questions = questions;
        this.title = title;
        this.description = description;
    }

    public static Quiz create(List<Question> questions,
                              String title,
                              String description) {
        return new Quiz(AggregateId.generate(),
                questions,
                title,
                description,
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    public void update(List<Question> questions,
                       String title,
                       String description) {
        this.questions = questions;
        this.title = title;
        this.description = description;
        super.update();
    }
}
