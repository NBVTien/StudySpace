package com.example.studyspace.domain.common.models;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Abstract class representing a base entity in the system.
 * This class provides common properties and methods for all entities.
 *
 * @version 1.0
 */
@Getter
public abstract class Entity {
    private final EntityId id;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected Entity(EntityId id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Updates the <code>updatedAt</code> timestamp to the current time.
     */
    public void update() {
        this.updatedAt = LocalDateTime.now();
    }
}
