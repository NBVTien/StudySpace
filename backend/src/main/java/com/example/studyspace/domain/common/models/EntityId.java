package com.example.studyspace.domain.common.models;

import lombok.Value;

import java.util.UUID;

/**
 * Represents a unique identifier for an entity in the system.
 * This class is used to encapsulate the UUID and provide methods for generating and creating IDs.
 *
 * @version 1.0
 */
@Value
public class EntityId {
    /**
     * The UUID value representing the unique identifier.
     */
    UUID value;

    /**
     * Private constructor to create an EntityId from a UUID.
     *
     * @param uuid The UUID value to be used as the unique identifier.
     */
    public EntityId(UUID uuid) {
        this.value = uuid;
    }

    /**
     * Generates a new unique identifier using a random UUID.
     *
     * @return A new EntityId instance with a randomly generated UUID.
     */
    public static EntityId generate() {
        return new EntityId(UUID.randomUUID());
    }

    /**
     * Creates an EntityId from a string representation of a UUID.
     *
     * @param id The string representation of the UUID.
     * @return A new EntityId instance with the specified UUID.
     */
    public static EntityId create(String id) {
        return new EntityId(UUID.fromString(id));
    }

    /**
     * Creates an EntityId from a UUID.
     *
     * @param id The UUID value to be used as the unique identifier.
     * @return A new EntityId instance with the specified UUID.
     */
    public static EntityId create(UUID id) {
        return new EntityId(id);
    }
}
