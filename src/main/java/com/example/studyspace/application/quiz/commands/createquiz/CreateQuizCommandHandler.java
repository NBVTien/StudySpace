package com.example.studyspace.application.quiz.commands.createquiz;

import com.example.studyspace.application.common.interfaces.usecases.UseCase;
import com.example.studyspace.application.common.interfaces.repositories.QuizRepository;
import com.example.studyspace.domain.quiz.Quiz;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

/**
 * Command handler for creating a new quiz.
 * This class is responsible for handling the creation of a new quiz in the system.
 * It uses the <code>QuizRepository</code> to save the new quiz to the database.
 *
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class CreateQuizCommandHandler implements UseCase<CreateQuizCommand, Quiz> {
    QuizRepository quizRepository;

    /**
     * Executes the command to create a new quiz.
     *
     * @param command The command containing the details of the quiz to be created.
     * @return The created <code>Quiz</code> object.
     */
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