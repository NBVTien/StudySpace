package com.example.studyspace.api.services;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        }

        if (token != null && JwtUtils.isTokenValid(token)) {
            String username = JwtUtils.getClaims(token).getSubject();
            String userId = JwtUtils.getUserId(token);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                username,
                null, // Verify credentials when calling isTokenValid()
                Collections.emptyList() // Roles can be added here if needed
            );

            // Attach userId as a detail to the authentication object
            ((UsernamePasswordAuthenticationToken) authentication).setDetails(userId);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}