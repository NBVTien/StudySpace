package com.example.studyspace.application.auth.commands.register;

import com.example.studyspace.application.auth.dtos.UserDto;
import com.example.studyspace.application.common.interfaces.usecases.UseCaseInput;
import com.example.studyspace.domain.user.AppUser;
import lombok.Value;

@Value
public class RegisterCommand implements UseCaseInput<AppUser> {
    UserDto userDto;
}
