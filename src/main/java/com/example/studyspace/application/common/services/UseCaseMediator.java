package com.example.studyspace.application.common.services;

import com.example.studyspace.application.common.interfaces.usecases.UseCase;
import com.example.studyspace.application.common.interfaces.usecases.UseCaseInput;
import com.example.studyspace.application.common.interfaces.usecases.UseCaseValidator;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

@Component
public class UseCaseMediator {
    private final Map<Class<?>, UseCase<?, ?>> useCases = new HashMap<>();
    private final Map<Class<?>, UseCaseValidator<?>> validators = new HashMap<>();

    public UseCaseMediator(ApplicationContext applicationContext) {
        applicationContext.getBeansOfType(UseCase.class).values().forEach(useCase -> {
            Class<?> requestType = extractRequestType(useCase);
            useCases.put(requestType, useCase);
        });

        applicationContext.getBeansOfType(UseCaseValidator.class).values().forEach(validator -> {
            Class<?> requestType = extractRequestType(validator);
            validators.put(requestType, validator);
        });
    }

    @SuppressWarnings("unchecked")
    public <TRequest extends UseCaseInput<TResponse>, TResponse> TResponse execute(TRequest request) {
        UseCaseValidator<TRequest> validator = (UseCaseValidator<TRequest>) validators.get(request.getClass());
        if (validator != null) {
            validator.validate(request);
        }

        UseCase<TRequest, TResponse> useCase = (UseCase<TRequest, TResponse>) useCases.get(request.getClass());
        if (useCase == null) {
            throw new IllegalArgumentException("No use case registered for request type: " + request.getClass().getName());
        }

        return useCase.execute(request);
    }

    private Class<?> extractRequestType(Object object) {
        ParameterizedType type = (ParameterizedType) object.getClass()
                .getGenericInterfaces()[0];
        return (Class<?>) type.getActualTypeArguments()[0];
    }
}