package com.example.studyspace.application.quiz.commands.updatequestion;

import com.example.studyspace.application.common.interfaces.repositories.QuestionRepository;
import com.example.studyspace.application.common.interfaces.repositories.QuizRepository;
import com.example.studyspace.application.common.interfaces.usecases.UseCase;
import com.example.studyspace.domain.common.models.EntityId;
import com.example.studyspace.domain.quiz.entities.Question;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Command handler for updating an existing question.
 * This class is responsible for handling the update of an existing question in the system.
 * It uses the <code>QuestionRepository</code> to update the question in the database.
 *
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UpdateQuestionCommandHandler implements UseCase<UpdateQuestionCommand, Question> {
    QuestionRepository questionRepository;
    QuizRepository quizRepository;

    /**
     * Executes the command to update an existing question.
     *
     * @param command The command containing the details of the question to be updated.
     * @return The updated <code>Question</code> object.
     */
    @Override
    public Question execute(UpdateQuestionCommand command) {
        var quiz = quizRepository.getById(UUID.fromString(command.getQuizId()));
        var question = questionRepository.getById(UUID.fromString(command.getQuestionId()));

        if (!quiz.getId().equals(question.getQuizId())) {
            throw new IllegalArgumentException("Question does not belong to the specified quiz");
        }

        question.update(
            command.getQuestionDto().question(),
            command.getQuestionDto().options(),
            command.getQuestionDto().correctAnswer(),
            EntityId.create(command.getQuizId())
        );
        questionRepository.update(question.getId().getValue(), question);
        return question;
    }
}
