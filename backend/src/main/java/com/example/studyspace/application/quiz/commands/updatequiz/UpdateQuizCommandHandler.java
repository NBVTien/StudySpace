package com.example.studyspace.application.quiz.commands.updatequiz;

import com.example.studyspace.application.common.exceptions.QuizNotFoundException;
import com.example.studyspace.application.common.interfaces.repositories.QuizRepository;
import com.example.studyspace.application.common.interfaces.usecases.UseCase;
import com.example.studyspace.domain.quiz.Quiz;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Command handler for updating an existing quiz.
 * This class is responsible for handling the update of an existing quiz in the system.
 * It uses the <code>QuizRepository</code> to update the quiz in the database.
 *
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UpdateQuizCommandHandler implements UseCase<UpdateQuizCommand, Quiz> {
    QuizRepository quizRepository;

    /**
     * Executes the command to update an existing quiz.
     *
     * @param command The command containing the details of the quiz to be updated.
     * @return The updated <code>Quiz</code> object.
     */
    @Override
    public Quiz execute(UpdateQuizCommand command) {
        var id = UUID.fromString(command.getQuizId());
        var quizDto = command.getQuizDto();

        Quiz quiz = quizRepository.getById(id);
        if (quiz == null) {
            throw new QuizNotFoundException();
        }

        quiz.update(
            quizDto.questions(),
            quizDto.title(),
            quizDto.description(),
            quizDto.difficulty(),
            quizDto.estimatedTimeInMinutes(),
            quizDto.tags()
        );
        quizRepository.update(id, quiz);
        return quiz;
    }
}