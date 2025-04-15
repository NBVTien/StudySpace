package com.example.studyspace.application.quiz.commands.update;

import com.example.studyspace.application.common.exceptions.QuizNotFoundException;
import com.example.studyspace.application.common.interfaces.repositories.QuizRepository;
import com.example.studyspace.application.common.interfaces.usecases.UseCase;
import com.example.studyspace.application.quiz.dtos.QuestionDto;
import com.example.studyspace.application.quiz.dtos.QuizDto;
import com.example.studyspace.application.quiz.mappers.QuizFactory;
import com.example.studyspace.domain.quiz.Quiz;
import com.example.studyspace.domain.quiz.entities.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UpdateQuizCommandHandler implements UseCase<UpdateQuizCommand, Quiz> {
    private final QuizRepository quizRepository;
    private final QuizFactory quizFactory;

    public UpdateQuizCommandHandler(QuizRepository quizRepository, QuizFactory quizFactory) {
        this.quizRepository = quizRepository;
        this.quizFactory = quizFactory;
    }

    @Override
    public Quiz execute(UpdateQuizCommand command) {
        var id = UUID.fromString(command.getQuizId());
        var quizDto = command.getQuizDto();

        Quiz existingQuiz = quizRepository.getById(id);
        if (existingQuiz == null) {
            throw new QuizNotFoundException();
        }

        updateQuiz(existingQuiz, quizDto);
        quizRepository.update(id, existingQuiz);
        return existingQuiz;
    }

    private Quiz updateQuiz(Quiz quiz, QuizDto quizDto) {
        quiz.update(
            quizFactory.createQuestionsFromDtos(quizDto.questions()),
            quizDto.title(),
            quizDto.description()
        );
        return quiz;
    }

    private List<Question> updateQuestions(List<Question> questions, List<QuestionDto> questionDtos) {
        List<Question> updatedQuestions = questions.stream()
            .map(question -> updateQuestion(question, questionDtos.get(questions.indexOf(question))))
            .toList();
        return updatedQuestions;
    }

    private Question updateQuestion(Question question, QuestionDto questionDto) {
        question.update(
            questionDto.question(),
            questionDto.options(),
            questionDto.correctAnswer()
        );
        return question;
    }
}