package com.example.studyspace.application.quiz.usecases;

import com.example.studyspace.application.common.exceptions.QuizNotFoundException;
import com.example.studyspace.application.common.interfaces.QuizRepository;
import com.example.studyspace.application.common.interfaces.UseCase;
import com.example.studyspace.application.quiz.commands.DeleteQuizCommand;
import com.example.studyspace.domain.quiz.Quiz;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteQuizCommandHandler implements UseCase<DeleteQuizCommand, Void> {
    private final QuizRepository quizRepository;

    public DeleteQuizCommandHandler(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public Void execute(DeleteQuizCommand deleteQuizCommand) {
        var id = deleteQuizCommand.quizId();

        // Validate
        UUID uuid;
        uuid = UUID.fromString(id);

        // Execute
        Quiz existingQuiz = quizRepository.getById(uuid);
        if (existingQuiz == null) {
            throw new QuizNotFoundException();
        }
        quizRepository.delete(uuid);

        return null;
    }
}
