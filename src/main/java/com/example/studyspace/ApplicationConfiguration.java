package com.example.studyspace;

import com.example.studyspace.application.interfaces.QuizRepository;
import com.example.studyspace.application.services.QuizService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    QuizService quizService(QuizRepository quizRepository) {
        return new QuizService(quizRepository);
    }
}
