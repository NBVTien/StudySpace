package com.example.studyspace.api.contracts.auth;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserResponse(
    UUID id,
    String username,
    String email,
    String fullName
) {}
