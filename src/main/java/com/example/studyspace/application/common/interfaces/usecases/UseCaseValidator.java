package com.example.studyspace.application.common.interfaces.usecases;

/**
 * Interface for validating use case requests.
 * @param <TRequest> The input request type
 */
public interface UseCaseValidator<TRequest> {
    /**
     * Validates the given request.
     * @param request The request to validate
     * @throws IllegalArgumentException if validation fails
     */
    void validate(TRequest request);
}
