package com.example.studyspace.application.common.interfaces.usecases;

/**
 * Generic interface for all use cases
 * @param <TRequest> The input request type, must extend UseCaseInput
 * @param <TResponse> The output response type
 */
public interface UseCase<TRequest extends UseCaseInput<TResponse>, TResponse> {
    
    /**
     * Executes the use case with the given request
     * @param request Input parameters
     * @return Response object (or Void for use cases without return values)
     */
    TResponse execute(TRequest request);
}