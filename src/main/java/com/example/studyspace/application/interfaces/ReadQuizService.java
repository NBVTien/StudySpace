package com.example.studyspace.application.interfaces;

import com.example.studyspace.domain.quiz.Quiz;

public interface ReadQuizService {
    public Quiz execute(String quizId);
}
