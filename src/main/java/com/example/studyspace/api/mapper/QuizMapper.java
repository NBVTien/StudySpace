package com.example.studyspace.api.mapper;

import com.example.studyspace.application.quiz.dtos.QuizDto;
import com.example.studyspace.domain.quiz.Quiz;
import com.example.studyspace.api.contracts.quizzes.QuizRequest;
import com.example.studyspace.api.contracts.quizzes.QuizResponse;

import java.util.List;

public interface QuizMapper {
    static Quiz quiz(QuizRequest quizRequest) {
        return Quiz.create(
            QuestionMapper.questions(quizRequest.questions()),
            quizRequest.title(),
            quizRequest.description()
        );
    }

    static QuizResponse quizResponse(Quiz quiz) {
        return QuizResponse.builder()
            .id(quiz.getId().getValue())
            .questions(QuestionMapper.questionResponses(quiz.getQuestions()))
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
            QuestionMapper.questionDtos(quizRequest.questions()),
            quizRequest.title(),
            quizRequest.description()
        );
    }
}
