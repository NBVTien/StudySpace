package com.example.studyspace.application.quiz.commands.create;

import com.example.studyspace.application.common.interfaces.usecases.UseCase;
import com.example.studyspace.application.common.interfaces.repositories.QuizRepository;
import com.example.studyspace.domain.quiz.Quiz;
import org.springframework.stereotype.Service;

@Service
public class CreateQuizCommandHandler implements UseCase<CreateQuizCommand, Quiz> {
    private final QuizRepository quizRepository;

    public CreateQuizCommandHandler(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }
    
    @Override
    public Quiz execute(CreateQuizCommand command) {
        Quiz quiz = Quiz.create(
            command.getQuizDto().title(),
            command.getQuizDto().description()
        );
        quizRepository.save(quiz);
        return quiz;
    }
}