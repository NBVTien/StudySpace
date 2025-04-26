package com.example.studyspace.application.auth.queries.readbyusername;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseInput;
import com.example.studyspace.domain.user.User;
import lombok.Value;

@Value
public class ReadUserByUsernameQuery implements UseCaseInput<User> {
    String username;
}
