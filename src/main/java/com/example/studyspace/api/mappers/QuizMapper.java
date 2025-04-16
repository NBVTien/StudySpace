package com.example.studyspace.api.mappers;

import com.example.studyspace.application.quiz.dtos.QuizDto;
import com.example.studyspace.domain.common.models.EntityId;
import com.example.studyspace.domain.quiz.Quiz;
import com.example.studyspace.api.contracts.quizzes.QuizRequest;
import com.example.studyspace.api.contracts.quizzes.QuizResponse;

import java.util.List;

public interface QuizMapper {

    static QuizResponse quizResponse(Quiz quiz) {
        return QuizResponse.builder()
            .id(quiz.getId().getValue())
            .questionIds(quiz.getQuestionIds().stream()
                .map(EntityId::getValue)
                .toList())
            .title(quiz.getTitle())
            .description(quiz.getDescription())
            .build();
    }

    static List<QuizResponse> quizResponses(List<Quiz> quizzes) {
        return quizzes.stream()
            .map(QuizMapper::quizResponse)
            .toList();
    }

    static QuizDto quizDto(QuizRequest quizRequest) {
        return new QuizDto(
            quizRequest.title(),
            quizRequest.description()
        );
    }
}