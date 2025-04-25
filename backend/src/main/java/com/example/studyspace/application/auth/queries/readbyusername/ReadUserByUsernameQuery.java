package com.example.studyspace.application.auth.queries.readbyusername;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseInput;
import com.example.studyspace.domain.user.AppUser;
import lombok.Value;

@Value
public class ReadUserByUsernameQuery implements UseCaseInput<AppUser> {
    String username;
}
