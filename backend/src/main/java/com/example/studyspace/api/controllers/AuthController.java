package com.example.studyspace.api.controllers;

import com.example.studyspace.application.auth.commands.register.RegisterCommand;
import com.example.studyspace.application.auth.dtos.UserDto;
import com.example.studyspace.application.common.services.UseCaseMediator;
import com.example.studyspace.domain.user.AppUser;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class AuthController {

    UseCaseMediator mediator;

    @PostMapping("/register")
    public ResponseEntity<AppUser> register(@RequestBody UserDto request) {
        var command = new RegisterCommand(request);
        AppUser user = mediator.execute(command);
        return ResponseEntity.ok(user);
    }
}
