package com.example.studyspace.domainv2.shared.models;

import java.time.LocalDateTime;

/**
 * Represents a base class for aggregate roots in the domain model.
 * Aggregate roots are entities that serve as the entry point for a cluster of related entities and value objects.
 *
 * @version 1.0
 */
public abstract class AggregateRoot<TId> extends Entity<TId> {
    protected AggregateRoot(TId id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }
}