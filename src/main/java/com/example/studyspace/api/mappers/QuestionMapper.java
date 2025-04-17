package com.example.studyspace.api.mappers;

import com.example.studyspace.application.quiz.dtos.QuestionDto;
import com.example.studyspace.domain.quiz.entities.Question;
import com.example.studyspace.api.contracts.quizzes.QuestionRequest;
import com.example.studyspace.api.contracts.quizzes.QuestionResponse;

import java.util.List;

/**
 * <code>QuestionMapper</code> is a utility class that provides methods to convert between different representations of questions.
 * It converts domain entities to API response objects and API request objects to domain DTOs.
 *
 * @version 1.0
 */
public interface QuestionMapper {
    /**
     * Converts a <code>Question</code> entity to a <code>QuestionResponse</code> object.
     *
     * @param question The <code>Question</code> entity to convert.
     * @return A <code>QuestionResponse</code> object representing the question.
     *
     */
    static QuestionResponse questionResponse(Question question) {
        return QuestionResponse.builder()
            .id(question.getId().getValue())
            .question(question.getQuestion())
            .options(question.getOptions())
            .correctAnswer(question.getCorrectAnswer())
            .quizId(question.getQuizId().getValue())
            .build();
    }

    /**
     * Converts a list of <code>Question</code> entities to a list of <code>QuestionResponse</code> objects.
     *
     * @param questions The list of <code>Question</code> entities to convert.
     * @return A list of <code>QuestionResponse</code> objects representing the questions.
     *
     */
    static List<QuestionResponse> questionResponses(List<Question> questions) {
        return questions.stream()
            .map(QuestionMapper::questionResponse)
            .toList();
    }

    /**
     * Converts a <code>QuestionRequest</code> object to a <code>QuestionDto</code> object.
     *
     * @param questionRequest The <code>QuestionRequest</code> object to convert.
     * @return A <code>QuestionDto</code> object representing the question.
     *
     */
    static QuestionDto questionDto(QuestionRequest questionRequest) {
        return QuestionDto.builder()
            .question(questionRequest.question())
            .options(questionRequest.options())
            .correctAnswer(questionRequest.correctAnswer())
            .build();
    }
}