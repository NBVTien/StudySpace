package com.example.studyspace.api.controllers;

import com.example.studyspace.api.contracts.auth.LoginRequest;
import com.example.studyspace.api.services.JwtUtils;
import com.example.studyspace.application.auth.commands.register.RegisterCommand;
import com.example.studyspace.application.auth.dtos.UserDto;
import com.example.studyspace.application.common.services.UseCaseMediator;
import com.example.studyspace.domain.user.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class AuthController {

    AuthenticationManager authenticationManager;
    UseCaseMediator mediator;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDto request) {
        var command = new RegisterCommand(request);
        User user = mediator.execute(command);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.username(), request.password());

        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = JwtUtils.generateToken((org.springframework.security.core.userdetails.User) authentication.getPrincipal());
        return ResponseEntity.ok(jwtToken);
    }

}
