package com.example.studyspace.api.contracts.auth;

public record AuthResponse (
    UserResponse user,
    String token
) {}