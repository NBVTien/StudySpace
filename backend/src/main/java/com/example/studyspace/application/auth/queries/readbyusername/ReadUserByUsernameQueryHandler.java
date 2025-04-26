package com.example.studyspace.application.auth.queries.readbyusername;

import com.example.studyspace.application.common.interfaces.repositories.UserRepository;
import com.example.studyspace.application.common.interfaces.usecases.UseCase;
import com.example.studyspace.domain.user.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ReadUserByUsernameQueryHandler implements UseCase<ReadUserByUsernameQuery, User> {
    UserRepository userRepository;

    @Override
    public User execute(ReadUserByUsernameQuery readUserByUsernameQuery) {
        var username = readUserByUsernameQuery.getUsername();
        User user = userRepository.getByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found"); // TODO: Create a custom exception
        }
        return user;
    }
}
