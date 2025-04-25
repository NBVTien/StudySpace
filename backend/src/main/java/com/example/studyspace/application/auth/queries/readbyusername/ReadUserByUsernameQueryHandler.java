package com.example.studyspace.application.auth.queries.readbyusername;

import com.example.studyspace.application.common.interfaces.repositories.UserRepository;
import com.example.studyspace.application.common.interfaces.usecases.UseCase;
import com.example.studyspace.domain.user.AppUser;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ReadUserByUsernameQueryHandler implements UseCase<ReadUserByUsernameQuery, AppUser> {
    UserRepository userRepository;

    @Override
    public AppUser execute(ReadUserByUsernameQuery readUserByUsernameQuery) {
        var username = readUserByUsernameQuery.getUsername();
        AppUser user = userRepository.getByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found"); // TODO: Create a custom exception
        }
        return user;
    }
}
