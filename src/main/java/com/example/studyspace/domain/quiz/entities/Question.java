package com.example.studyspace.domain.quiz.entities;

import com.example.studyspace.domain.common.models.Aggregate;
import com.example.studyspace.domain.common.models.AggregateId;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Question extends Aggregate {
    private String question;
    private List<String> options;
    private String correctAnswer;

    protected Question(AggregateId id,
                       String question,
                       List<String> options,
                       String correctAnswer,
                       LocalDateTime createdAt,
                       LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public static Question create(String question,
                                  List<String> options,
                                  String correctAnswer) {
        if (options.size() < 2) {
            throw new IllegalArgumentException("Question must have at least 2 options");
        }

        if (!options.contains(correctAnswer)) {
            throw new IllegalArgumentException("Correct answer must be one of the options");
        }

        return new Question(AggregateId.generate(),
                question,
                options,
                correctAnswer,
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    public void update(String question,
                       List<String> options,
                       String correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        super.update();
    }
}
