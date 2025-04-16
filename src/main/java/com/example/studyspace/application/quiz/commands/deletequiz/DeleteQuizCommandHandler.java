package com.example.studyspace.application.quiz.commands.deletequiz;

import com.example.studyspace.application.common.exceptions.QuizNotFoundException;
import com.example.studyspace.application.common.interfaces.repositories.QuizRepository;
import com.example.studyspace.application.common.interfaces.usecases.UseCase;
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
        var id = UUID.fromString(deleteQuizCommand.getQuizId());
        Quiz existingQuiz = quizRepository.getById(id);
        if (existingQuiz == null) {
            throw new QuizNotFoundException();
        }
        quizRepository.delete(id);
        return null;
    }
}
