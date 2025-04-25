package com.example.studyspace.api.services;

import com.example.studyspace.application.auth.queries.readbyusername.ReadUserByUsernameQuery;
import com.example.studyspace.application.common.services.UseCaseMediator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ApplicationUserDetailsService implements UserDetailsService {

    UseCaseMediator mediator;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var query = new ReadUserByUsernameQuery(username);
        var user = mediator.execute(query);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return User
            .withUsername(user.getUsername())
            .password(user.getPassword())
            .build();
    }
}
