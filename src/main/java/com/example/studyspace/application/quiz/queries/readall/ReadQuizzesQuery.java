package com.example.studyspace.application.quiz.queries.readall;

import com.example.studyspace.application.common.interfaces.usecases.UseCaseInput;
import com.example.studyspace.domain.quiz.Quiz;
import lombok.Value;

import java.util.List;

@Value
public class ReadQuizzesQuery implements UseCaseInput<List<Quiz>> {}
