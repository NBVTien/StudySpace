package com.example.studyspace.application.auth.commands.register;

import com.example.studyspace.application.common.interfaces.repositories.UserRepository;
import com.example.studyspace.application.common.interfaces.usecases.UseCase;
import com.example.studyspace.domain.user.AppUser;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class RegisterCommandHandler implements UseCase<RegisterCommand, AppUser> {

    PasswordEncoder passwordEncoder;
    UserRepository userRepository;

    @Override
    public AppUser execute(RegisterCommand registerCommand) {
        AppUser user = AppUser.create(
            registerCommand.getUserDto().username(),
            registerCommand.getUserDto().email(),
            passwordEncoder.encode(registerCommand.getUserDto().password()),
            registerCommand.getUserDto().fullName()
        );

        userRepository.save(user);
        return user;
    }
}
