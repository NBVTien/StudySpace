package com.example.studyspace.application.common.services;

import com.example.studyspace.application.common.interfaces.usecases.UseCase;
import com.example.studyspace.application.common.interfaces.usecases.UseCaseInput;
import com.example.studyspace.application.common.interfaces.usecases.UseCaseValidator;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

/**
 * <code>UseCaseMediator</code> is responsible for mediating between use cases and their validators.
 * It allows for the execution of use cases with validation.
 *
 * @version 1.0
 */
@Component
public class UseCaseMediator {
    // A map to hold use cases, where the key is the request type and the value is the use case instance.
    private final Map<Class<?>, UseCase<?, ?>> useCases = new HashMap<>();
    // A map to hold use case validators, where the key is the request type and the value is the validator instance.
    private final Map<Class<?>, UseCaseValidator<?>> validators = new HashMap<>();

    /**
     * Constructor that initializes the mediator with use cases and validators from the application context.
     *
     * @param applicationContext The Spring application context.
     */
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

    /**
     * Executes a use case with the provided request.
     *
     * @param request The request object containing the input data for the use case.
     * @param <TRequest> The type of the request object.
     * @param <TResponse> The type of the response object.
     * @return The response object containing the result of the use case execution.
     */
    @SuppressWarnings("unchecked")
    public <TRequest extends UseCaseInput<TResponse>, TResponse> TResponse execute(TRequest request) {
        UseCaseValidator<TRequest> validator = (UseCaseValidator<TRequest>) validators.get(request.getClass());
        if (validator != null) { validator.validate(request); }

        UseCase<TRequest, TResponse> useCase = (UseCase<TRequest, TResponse>) useCases.get(request.getClass());
        if (useCase == null) {
            throw new IllegalArgumentException("No use case registered for request type: " + request.getClass().getName());
        }

        return useCase.execute(request);
    }

    /**
     * Extracts the request type from the given object.
     *
     * @param object The object from which to extract the request type.
     * @return The class representing the request type.
     */
    private Class<?> extractRequestType(Object object) {
        ParameterizedType type = (ParameterizedType) object.getClass()
                .getGenericInterfaces()[0];
        return (Class<?>) type.getActualTypeArguments()[0];
    }
}