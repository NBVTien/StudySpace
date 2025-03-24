package com.example.studyspace.domain.common.models;

import lombok.Value;

import java.util.UUID;

@Value
public class AggregateId {
    UUID id;

    public AggregateId(UUID uuid) {
        this.id = uuid;
    }

    public static AggregateId generate() {
        return new AggregateId(UUID.randomUUID());
    }

    public static AggregateId create(String id) {
        return new AggregateId(UUID.fromString(id));
    }
}
