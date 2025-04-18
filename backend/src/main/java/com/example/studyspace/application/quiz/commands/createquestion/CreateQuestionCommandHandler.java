package com.example.studyspace.application.quiz.commands.createquestion;

import com.example.studyspace.application.common.interfaces.repositories.QuestionRepository;
import com.example.studyspace.application.common.interfaces.repositories.QuizRepository;
import com.example.studyspace.application.common.interfaces.usecases.UseCase;
import com.example.studyspace.domain.common.models.EntityId;
import com.example.studyspace.domain.quiz.Quiz;
import com.example.studyspace.domain.quiz.entities.Question;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Command handler for creating a new question.
 * This class is responsible for handling the creation of a new question in the system.
 * It uses the <code>QuestionRepository</code> to save the new question to the database.
 *
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class CreateQuestionCommandHandler implements UseCase<CreateQuestionCommand, Question> {
    /**
     * The <code>QuestionRepository</code> is used to interact with the database for question-related operations.
     */
    QuestionRepository questionRepository;
    /**
     * The <code>QuizRepository</code> is used to interact with the database for quiz-related operations.
     */
    QuizRepository quizRepository;

    /**
     * Executes the command to create a new question.
     *
     * @param command The command containing the details of the question to be created.
     * @return The created <code>Question</code> object.
     */
    @Override
    public Question execute(CreateQuestionCommand command) {
        Question question = Question.create(
            command.getQuestionDto().question(),
            command.getQuestionDto().options(),
            command.getQuestionDto().correctAnswer(),
            EntityId.create(command.getQuizId())
        );

        Quiz quiz = quizRepository.getById(UUID.fromString(command.getQuizId()));
        if (quiz == null) {
            throw new IllegalArgumentException("Quiz not found");
        }

        quiz.addQuestion(question.getId());
        questionRepository.save(question);
        quizRepository.update(quiz.getId().getValue(), quiz);

        return question;
    }
}
