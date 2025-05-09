package com.example.studyspace.api.controllers;

import com.example.studyspace.api.contracts.auth.AuthResponse;
import com.example.studyspace.api.contracts.auth.LoginRequest;
import com.example.studyspace.api.contracts.auth.RegisterRequest;
import com.example.studyspace.api.mappers.AuthMapper;
import com.example.studyspace.api.services.JwtUtils;
import com.example.studyspace.application.auth.commands.register.RegisterCommand;
import com.example.studyspace.application.auth.queries.readbyusername.ReadUserByUsernameQuery;
import com.example.studyspace.application.common.services.UseCaseMediator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class AuthController {

    AuthenticationManager authenticationManager;
    UseCaseMediator mediator;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        var userDto = AuthMapper.toUserDto(request);
        var command = new RegisterCommand(userDto);
        var applicationUser = mediator.execute(command);

        User user = new User(
            applicationUser.getUsername(),
            applicationUser.getPassword(),
            new ArrayList<>()
        );
        String jwtToken = JwtUtils.generateToken(user, applicationUser.getId().getValue().toString());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", "authorization=" + jwtToken + "; Path=/; HttpOnly");

        var response = AuthMapper.toAuthResponse(applicationUser, jwtToken);
        return ResponseEntity.ok().headers(headers).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.username(), request.password());

        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        var user = ((User) authentication.getPrincipal());
        var query = new ReadUserByUsernameQuery(user.getUsername());
        var applicationUser = mediator.execute(query);

        String jwtToken = JwtUtils.generateToken(user, applicationUser.getId().getValue().toString());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", "authorization=" + jwtToken + "; Path=/; HttpOnly");

        var response = AuthMapper.toAuthResponse(applicationUser, jwtToken);
        return ResponseEntity.ok().headers(headers).body(response);
    }

    @GetMapping("/me")
    public ResponseEntity<AuthResponse> me() {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var query = new ReadUserByUsernameQuery(user.getUsername());
        var applicationUser = mediator.execute(query);

        String jwtToken = JwtUtils.generateToken(user, applicationUser.getId().getValue().toString());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", "authorization=" + jwtToken + "; Path=/; HttpOnly");

        var response = AuthMapper.toAuthResponse(applicationUser, jwtToken);
        return ResponseEntity.ok().headers(headers).body(response);
    }
}
