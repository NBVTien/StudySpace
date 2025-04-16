package com.example.studyspace.application.quiz.commands.deletequestion;

import com.example.studyspace.application.common.exceptions.QuizNotFoundException;
import com.example.studyspace.application.common.interfaces.repositories.QuestionRepository;
import com.example.studyspace.application.common.interfaces.repositories.QuizRepository;
import com.example.studyspace.application.common.interfaces.usecases.UseCase;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteQuestionCommandHandler implements UseCase<DeleteQuestionCommand, Void> {
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    public DeleteQuestionCommandHandler(QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Void execute(DeleteQuestionCommand command) {
        var questionId = UUID.fromString(command.getQuestionId());
        var quizId = UUID.fromString(command.getQuizId());

        if (quizRepository.getById(quizId) == null) {
            throw new QuizNotFoundException();
        }

        var question = questionRepository.getById(questionId);
        if (question == null) {
            throw new QuizNotFoundException();
        }
        if (!(question.getQuizId().getValue() == quizId)) {
            throw new QuizNotFoundException();
        }

        questionRepository.delete(questionId);
        return null;
    }
}
