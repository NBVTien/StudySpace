package com.example.studyspace.application.auth.dtos;

import lombok.Builder;

@Builder
public record UserDto(
    String username,
    String email,
    String password,
    String fullName
) {}
