package com.example.studyspace.api.contracts.auth;

public record RegisterRequest(
    String username,
    String email,
    String password,
    String fullName
) {}
