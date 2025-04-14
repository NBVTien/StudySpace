package com.example.studyspace.application.quiz.usecases;

import com.example.studyspace.application.common.interfaces.UseCase;
import com.example.studyspace.application.common.interfaces.QuizRepository;
import com.example.studyspace.application.quiz.commands.CreateQuizCommand;
import com.example.studyspace.application.quiz.dtos.QuizDto;
import com.example.studyspace.application.quiz.mappers.QuizFactory;
import com.example.studyspace.domain.quiz.Quiz;
import org.springframework.stereotype.Service;

@Service
public class CreateQuizCommandHandler implements UseCase<CreateQuizCommand, Quiz> {
    private final QuizRepository quizRepository;
    private final QuizFactory quizFactory;

    public CreateQuizCommandHandler(QuizRepository quizRepository, QuizFactory quizFactory) {
        this.quizRepository = quizRepository;
        this.quizFactory = quizFactory;
    }

    @Override
    public Quiz execute(CreateQuizCommand request) {
        validateRequest(request.quizDto());

        Quiz quiz = quizFactory.createFromDto(request.quizDto());
        quizRepository.save(quiz);

        return quiz;
    }

    private void validateRequest(QuizDto request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        // Add more validation as needed
    }
}