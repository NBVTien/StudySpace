package com.example.studyspace.application.services;

import com.example.studyspace.application.interfaces.ReadQuizService;
import com.example.studyspace.domain.quiz.Quiz;
import org.springframework.stereotype.Service;

@Service
public class ReadQuizServiceImpl implements ReadQuizService {
    @Override
    public Quiz execute(String quizId) {
        return null;
    }
}
