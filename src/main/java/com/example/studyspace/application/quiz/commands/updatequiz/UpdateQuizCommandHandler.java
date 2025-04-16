package com.example.studyspace.application.quiz.commands.updatequiz;

import com.example.studyspace.application.common.exceptions.QuizNotFoundException;
import com.example.studyspace.application.common.interfaces.repositories.QuizRepository;
import com.example.studyspace.application.common.interfaces.usecases.UseCase;
import com.example.studyspace.domain.quiz.Quiz;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateQuizCommandHandler implements UseCase<UpdateQuizCommand, Quiz> {
    private final QuizRepository quizRepository;

    public UpdateQuizCommandHandler(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public Quiz execute(UpdateQuizCommand command) {
        var id = UUID.fromString(command.getQuizId());
        var quizDto = command.getQuizDto();

        Quiz quiz = quizRepository.getById(id);
        if (quiz == null) {
            throw new QuizNotFoundException();
        }

        quiz.update(
            quizDto.title(),
            quizDto.description()
        );
        quizRepository.update(id, quiz);
        return quiz;
    }
}