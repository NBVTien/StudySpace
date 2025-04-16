package com.example.studyspace.api.mappers;

import com.example.studyspace.application.quiz.dtos.QuestionDto;
import com.example.studyspace.domain.quiz.entities.Question;
import com.example.studyspace.api.contracts.quizzes.QuestionRequest;
import com.example.studyspace.api.contracts.quizzes.QuestionResponse;

import java.util.List;

public interface QuestionMapper {
    static QuestionResponse questionResponse(Question question) {
        return QuestionResponse.builder()
            .id(question.getId().getValue())
            .question(question.getQuestion())
            .options(question.getOptions())
            .correctAnswer(question.getCorrectAnswer())
            .quizId(question.getQuizId().getValue())
            .build();
    }

    static List<QuestionResponse> questionResponses(List<Question> questions) {
        return questions.stream()
            .map(QuestionMapper::questionResponse)
            .toList();
    }

    static QuestionDto questionDto(QuestionRequest questionRequest) {
        return new QuestionDto(
            questionRequest.question(),
            questionRequest.options(),
            questionRequest.correctAnswer()
        );
    }
}