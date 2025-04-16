package com.example.studyspace.application.quiz.commands.updatequestion;

import com.example.studyspace.application.common.interfaces.repositories.QuestionRepository;
import com.example.studyspace.application.common.interfaces.repositories.QuizRepository;
import com.example.studyspace.application.common.interfaces.usecases.UseCase;
import com.example.studyspace.domain.common.models.EntityId;
import com.example.studyspace.domain.quiz.entities.Question;

import java.util.UUID;

public class UpdateQuestionCommandHandler implements UseCase<UpdateQuestionCommand, Question> {
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    public UpdateQuestionCommandHandler(QuestionRepository questionRepository, QuizRepository quizRepository) {
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
    }

    @Override
    public Question execute(UpdateQuestionCommand command) {
        var quiz = quizRepository.getById(UUID.fromString(command.getQuizId()));
        var question = questionRepository.getById(UUID.fromString(command.getQuestionId()));

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
