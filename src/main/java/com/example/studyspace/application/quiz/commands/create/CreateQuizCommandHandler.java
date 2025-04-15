package com.example.studyspace.application.quiz.commands.create;

import com.example.studyspace.application.common.interfaces.usecases.UseCase;
import com.example.studyspace.application.common.interfaces.repositories.QuizRepository;
import com.example.studyspace.application.quiz.dtos.QuestionDto;
import com.example.studyspace.application.quiz.dtos.QuizDto;
import com.example.studyspace.application.quiz.mappers.QuizFactory;
import com.example.studyspace.domain.quiz.Quiz;
import com.example.studyspace.domain.quiz.entities.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateQuizCommandHandler implements UseCase<CreateQuizCommand, Quiz> {
    private final QuizRepository quizRepository;

    public CreateQuizCommandHandler(QuizRepository quizRepository, QuizFactory quizFactory) {
        this.quizRepository = quizRepository;
    }
    
    @Override
    public Quiz execute(CreateQuizCommand request) {
        Quiz quiz = createFromDto(request.getQuizDto());
        quizRepository.save(quiz);
        return quiz;
    }

    private Quiz createFromDto(QuizDto dto) {
        List<Question> questions = createQuestionsFromDtos(dto.questions());

        return Quiz.create(
            questions,
            dto.title(),
            dto.description()
        );
    }

    private List<Question> createQuestionsFromDtos(List<QuestionDto> dtos) {
        return dtos.stream()
            .map(this::createQuestionFromDto)
            .toList();
    }

    private Question createQuestionFromDto(QuestionDto dto) {
        return Question.create(
            dto.question(),
            dto.options(),
            dto.correctAnswer()
        );
    }
}