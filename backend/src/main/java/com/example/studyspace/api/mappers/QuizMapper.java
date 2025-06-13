package com.example.studyspace.api.mappers;

import com.example.studyspace.api.contracts.quizzes.QuizResponse;
import com.example.studyspace.application.common.models.PaginatedResult;
import com.example.studyspace.application.quiz.dtos.QuizDto;
import com.example.studyspace.domain.quiz.Quiz;
import com.example.studyspace.api.contracts.quizzes.QuizRequest;
import com.example.studyspace.api.contracts.quizzes.QuizItemResponse;

import java.util.List;

/**
 * <code>QuizMapper</code> is a utility class that provides methods to map between different representations of quizzes.
 * It converts domain models to API response models and vice versa.
 *
 * @version 1.0
 */
public interface QuizMapper {

    /**
     * Converts a <code>Quiz</code> domain model to a <code>QuizResponse</code> API response model.
     *
     * @param quiz The <code>Quiz</code> domain model to convert.
     * @return A <code>QuizResponse</code> API response model.
     *
     */
    static QuizItemResponse quizItemResponse(Quiz quiz) {
        return QuizItemResponse.builder()
            .id(quiz.getId().getValue())
            .questionCount(quiz.getQuestions() != null ? quiz.getQuestions().size() : 0)
            .title(quiz.getTitle())
            .description(quiz.getDescription())
            .difficulty(quiz.getDifficulty())
            .estimatedTimeInMinutes(quiz.getEstimatedTimeInMinutes())
            .tags(quiz.getTags())
            .build();
    }

    static QuizResponse quizResponse(Quiz quiz) {
        return QuizResponse.builder()
                .id(quiz.getId().getValue())
                .questions(quiz.getQuestions())
                .title(quiz.getTitle())
                .description(quiz.getDescription())
                .difficulty(quiz.getDifficulty())
                .estimatedTimeInMinutes(quiz.getEstimatedTimeInMinutes())
                .tags(quiz.getTags())
                .build();
    }


    /**
     * Converts a list of <code>Quiz</code> domain models to a list of <code>QuizResponse</code> API response models.
     *
     * @param quizzes The list of <code>Quiz</code> domain models to convert.
     * @return A list of <code>QuizResponse</code> API response models.
     *
     */
    static List<QuizItemResponse> quizResponses(List<Quiz> quizzes) {
        return quizzes.stream()
            .map(QuizMapper::quizItemResponse)
            .toList();
    }

    static PaginatedResult<QuizItemResponse> paginatedQuizResponses(PaginatedResult<Quiz> quizzes) {
        return PaginatedResult.<QuizItemResponse>builder()
            .data(quizResponses(quizzes.getData()))
            .total(quizzes.getTotal())
            .totalPages(quizzes.getTotalPages())
            .page(quizzes.getPage())
            .pageSize(quizzes.getPageSize())
            .build();
    }

    /**
     * Converts a <code>QuizRequest</code> API request model to a <code>QuizDto</code> domain DTO.
     *
     * @param quizRequest The <code>QuizRequest</code> API request model to convert.
     * @return A <code>QuizDto</code> domain DTO.
     *
     */
    static QuizDto quizDto(QuizRequest quizRequest) {
        return QuizDto.builder()
            .questions(quizRequest.questions())
            .title(quizRequest.title())
            .description(quizRequest.description())
            .difficulty(quizRequest.difficulty())
            .estimatedTimeInMinutes(quizRequest.estimatedTimeInMinutes())
            .tags(quizRequest.tags())
            .build();
    }
}