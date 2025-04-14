package com.example.studyspace.application.common.services;

import com.example.studyspace.application.common.interfaces.UseCase;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

@Component
public class UseCaseMediator {
    private final Map<Class<?>, UseCase<?, ?>> useCases = new HashMap<>();

    public UseCaseMediator(ApplicationContext applicationContext) {
        // Find all UseCase implementations in the Spring context
        applicationContext.getBeansOfType(UseCase.class).values().forEach(useCase -> {
            // Get the request type from the generic parameter
            Class<?> requestType = extractRequestType(useCase);
            useCases.put(requestType, useCase);
        });
    }

    @SuppressWarnings("unchecked")
    public <TRequest, TResponse> TResponse execute(TRequest request) {
        UseCase<TRequest, TResponse> useCase = (UseCase<TRequest, TResponse>) useCases.get(request.getClass());
        
        if (useCase == null) {
            throw new IllegalArgumentException("No use case registered for request type: " + request.getClass().getName());
        }
        
        return useCase.execute(request);
    }
    
    private Class<?> extractRequestType(UseCase<?, ?> useCase) {
        // Get the actual type arguments of the UseCase interface implementation
        ParameterizedType type = (ParameterizedType) useCase.getClass()
                .getGenericInterfaces()[0];
        return (Class<?>) type.getActualTypeArguments()[0];
    }
}