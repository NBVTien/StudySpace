package com.example.studyspace.domainv2.content.aggregates;

import com.example.studyspace.domainv2.content.valueobjects.ContentId;

import java.time.LocalDateTime;

public class Quiz extends Content {
    public Quiz(ContentId id, LocalDateTime createdAt, LocalDateTime updatedAt, String title, int version) {
        super(id, createdAt, updatedAt, title, version);
    }

    public static Quiz create(ContentId id, String title) {
        return new Quiz(
            id,
            LocalDateTime.now(),
            LocalDateTime.now(),
            title,
            1
        );
    }
}