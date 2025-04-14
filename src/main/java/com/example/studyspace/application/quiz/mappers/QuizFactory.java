package com.example.studyspace.application.quiz.mappers;

import com.example.studyspace.application.quiz.dtos.QuizDto;
import com.example.studyspace.application.quiz.dtos.QuestionDto;
import com.example.studyspace.domain.quiz.Quiz;
import com.example.studyspace.domain.quiz.entities.Question;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuizFactory {
    public Quiz createFromDto(QuizDto dto) {
        List<Question> questions = createQuestionsFromDtos(dto.questions());
        
        return Quiz.create(
            questions,
            dto.title(),
            dto.description()
        );
    }
    
    public List<Question> createQuestionsFromDtos(List<QuestionDto> dtos) {
        return dtos.stream()
            .map(this::createQuestionFromDto)
            .toList();
    }
    
    private Question createQuestionFromDto(QuestionDto dto) {
        return Question.create(
            dto.question(),
            dto.options(),
            dto.correctAnswer()
        );
    }
}