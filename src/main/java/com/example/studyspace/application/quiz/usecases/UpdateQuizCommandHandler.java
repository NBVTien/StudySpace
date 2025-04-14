package com.example.studyspace.application.quiz.usecases;

import com.example.studyspace.application.common.exceptions.QuizNotFoundException;
import com.example.studyspace.application.common.interfaces.QuizRepository;
import com.example.studyspace.application.common.interfaces.UseCase;
import com.example.studyspace.application.quiz.commands.UpdateQuizCommand;
import com.example.studyspace.application.quiz.mappers.QuizFactory;
import com.example.studyspace.domain.quiz.Quiz;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateQuizCommandHandler implements UseCase<UpdateQuizCommand, Quiz> {
    private final QuizRepository quizRepository;
    private final QuizFactory quizFactory;

    public UpdateQuizCommandHandler(QuizRepository quizRepository, QuizFactory quizFactory) {
        this.quizRepository = quizRepository;
        this.quizFactory = quizFactory;
    }

    @Override
    public Quiz execute(UpdateQuizCommand command) {
        var id = command.quizId();
        var quizDto = command.quizDto();

        // Validate
        UUID uuid;
        uuid = UUID.fromString(id);

        // Execute
        Quiz existingQuiz = quizRepository.getById(uuid);
        if (existingQuiz == null) {
            throw new QuizNotFoundException();
        }

        existingQuiz.update(
            // TODO: Questions should not be created again, but updated
            quizFactory.createQuestionsFromDtos(quizDto.questions()),
            quizDto.title(),
            quizDto.description());

        quizRepository.update(uuid, existingQuiz);
        return existingQuiz;
    }
}