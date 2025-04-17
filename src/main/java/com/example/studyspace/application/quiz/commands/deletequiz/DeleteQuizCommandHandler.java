package com.example.studyspace.application.quiz.commands.deletequiz;

import com.example.studyspace.application.common.exceptions.QuizNotFoundException;
import com.example.studyspace.application.common.interfaces.repositories.QuizRepository;
import com.example.studyspace.application.common.interfaces.usecases.UseCase;
import com.example.studyspace.domain.quiz.Quiz;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Command handler for deleting a quiz.
 * This class is responsible for handling the deletion of a quiz in the system.
 * It uses the <code>QuizRepository</code> to delete the quiz from the database.
 *
 * @version 1.0
 */
@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DeleteQuizCommandHandler implements UseCase<DeleteQuizCommand, Void> {
    QuizRepository quizRepository;

    /**
     * Executes the command to delete a quiz.
     *
     * @param command The command containing the details of the quiz to be deleted.
     * @return void
     */
    @Override
    public Void execute(DeleteQuizCommand command) {
        var id = UUID.fromString(command.getQuizId());
        Quiz existingQuiz = quizRepository.getById(id);
        if (existingQuiz == null) {
            throw new QuizNotFoundException();
        }
        quizRepository.delete(id);
        return null;
    }
}
