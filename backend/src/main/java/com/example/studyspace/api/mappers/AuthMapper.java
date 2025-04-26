package com.example.studyspace.api.mappers;

import com.example.studyspace.api.contracts.auth.AuthResponse;
import com.example.studyspace.api.contracts.auth.LoginRequest;
import com.example.studyspace.api.contracts.auth.RegisterRequest;
import com.example.studyspace.api.contracts.auth.UserResponse;
import com.example.studyspace.application.auth.dtos.UserDto;
import com.example.studyspace.domain.user.User;

public interface AuthMapper {

    static UserDto toUserDto(RegisterRequest request) {
        return UserDto.builder()
            .username(request.username())
            .email(request.email())
            .password(request.password())
            .fullName(request.fullName())
            .build();
    }

    static AuthResponse toAuthResponse(User user, String token) {
        return new AuthResponse(
            toUserResponse(user),
            token
        );
    }

    static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
            .id(user.getId().getValue())
            .username(user.getUsername())
            .email(user.getEmail())
            .fullName(user.getFullName())
            .build();
    }
}

