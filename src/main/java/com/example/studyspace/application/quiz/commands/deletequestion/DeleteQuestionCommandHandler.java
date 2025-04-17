package com.example.studyspace.application.quiz.commands.deletequestion;

import com.example.studyspace.application.common.exceptions.QuizNotFoundException;
import com.example.studyspace.application.common.interfaces.repositories.QuestionRepository;
import com.example.studyspace.application.common.interfaces.repositories.QuizRepository;
import com.example.studyspace.application.common.interfaces.usecases.UseCase;
import com.example.studyspace.domain.quiz.Quiz;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Command handler for deleting a question.
 * This class is responsible for handling the deletion of a question in the system.
 * It uses the <code>QuizRepository</code> and <code>QuestionRepository</code> to perform the necessary operations.
 *
 * @version 1.0
 */
@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DeleteQuestionCommandHandler implements UseCase<DeleteQuestionCommand, Void> {
    QuizRepository quizRepository;
    QuestionRepository questionRepository;

    /**
     * Executes the command to delete a question.
     *
     * @param command The command containing the details of the question to be deleted.
     * @return void
     * @throws QuizNotFoundException if the quiz or question is not found.
     */
    @Override
    public Void execute(DeleteQuestionCommand command) {
        var questionId = UUID.fromString(command.getQuestionId());
        var quizId = UUID.fromString(command.getQuizId());

        Quiz quiz = quizRepository.getById(quizId);
        if (quiz == null) {
            throw new QuizNotFoundException();
        }

        var question = questionRepository.getById(questionId);
        if (question == null) {
            throw new QuizNotFoundException();
        }
        if (!(question.getQuizId().getValue() == quizId)) {
            throw new QuizNotFoundException();
        }

        quiz.removeQuestion(question.getId());
        quizRepository.update(quizId, quiz);
        questionRepository.delete(questionId);
        return null;
    }
}
