package com.example.studyspace.api.contracts.auth;

public record LoginRequest(
    String username,
    String password
) {}