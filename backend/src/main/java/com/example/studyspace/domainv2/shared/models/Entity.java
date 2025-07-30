package com.example.studyspace.domainv2.shared.models;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Abstract class representing a base entity in the system.
 * This class provides common properties and methods for all entities.
 *
 * @param <TId> the type of the entity identifier
 *
 * @version 1.0
 */
@Getter
public abstract class Entity<TId> {
    private final TId id;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected Entity(TId id, LocalDateTime createdAt, LocalDateTime updatedAt) {
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
