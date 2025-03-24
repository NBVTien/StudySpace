package com.example.studyspace.domain.common.models;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class Aggregate {
    private final AggregateId id;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected Aggregate(AggregateId id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void update() {
        this.updatedAt = LocalDateTime.now();
    }
}
