package com.example.studyspace.domain.common.models;

import lombok.Value;

import java.util.UUID;

@Value
public class EntityId {
    // Temporary setter for testing purposes
    UUID value;

    public EntityId(UUID uuid) {
        this.value = uuid;
    }

    public static EntityId generate() {
        return new EntityId(UUID.randomUUID());
    }

    public static EntityId create(String id) {
        return new EntityId(UUID.fromString(id));
    }

    public static EntityId create(UUID id) {
        return new EntityId(id);
    }
}
