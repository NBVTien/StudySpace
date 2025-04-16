package com.example.studyspace.application.quiz.commands.createquestion;

import com.example.studyspace.application.common.interfaces.repositories.QuestionRepository;
import com.example.studyspace.application.common.interfaces.usecases.UseCase;
import com.example.studyspace.domain.common.models.EntityId;
import com.example.studyspace.domain.quiz.entities.Question;
import org.springframework.stereotype.Service;

@Service
public class CreateQuestionCommandHandler implements UseCase<CreateQuestionCommand, Question> {
    private final QuestionRepository questionRepository;

    public CreateQuestionCommandHandler(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question execute(CreateQuestionCommand command) {
        Question question = Question.create(
            command.getQuestionDto().question(),
            command.getQuestionDto().options(),
            command.getQuestionDto().correctAnswer(),
            EntityId.create(command.getQuizId())
        );

        questionRepository.save(question);
        return question;
    }
}
