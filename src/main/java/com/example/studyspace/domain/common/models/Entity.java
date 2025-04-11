package com.example.studyspace.domain.common.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public abstract class Entity {
    @Setter
    private EntityId id;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected Entity(EntityId id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void update() {
        this.updatedAt = LocalDateTime.now();
    }
}
