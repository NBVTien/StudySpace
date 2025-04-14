package com.example.studyspace.application.common.interfaces;

/**
 * Generic interface for all use cases
 * @param <TRequest> The input request type
 * @param <TResponse> The output response type
 */
public interface UseCase<TRequest, TResponse> {
    
    /**
     * Executes the use case with the given request
     * @param request Input parameters
     * @return Response object (or Void for use cases without return values)
     */
    TResponse execute(TRequest request);
}