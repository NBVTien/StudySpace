import { useState } from 'react';
import { z } from 'zod';

import { TabsContent, Tabs } from '@/components/ui/tabs';
import type { Question, QuizRequest } from '@/types/api';

import { QuestionForm, questionSchema } from './question-form';
import { QuizDetailsForm, quizDetailsSchema } from './quiz-details-form';
import { QuizNavigation } from './quiz-navigation';

export const NewQuizContainer = () => {
  // State for the entire quiz
  const [quizData, setQuizData] = useState<Partial<QuizRequest>>({
    questions: [],
    tags: [],
  });

  // Current active tab
  const [activeTab, setActiveTab] = useState('quiz-detail');

  // Handle quiz details form submission
  const handleQuizDetailsSubmit = (
    values: z.infer<typeof quizDetailsSchema>,
  ) => {
    // Convert tagsInput string to tags array
    const tags = values.tagsInput
      .split(',')
      .map((tag: string) => tag.trim())
      .filter(Boolean);

    // Update quiz data with form values, transforming tags appropriately
    setQuizData((prev) => ({
      ...prev,
      title: values.title,
      description: values.description,
      difficulty: values.difficulty,
      estimatedTimeInMinutes: values.estimatedTimeInMinutes,
      tags,
    }));

    // Navigate to the first question or add a new question if none exists
    if (quizData.questions && quizData.questions.length > 0) {
      setActiveTab('question-1');
    } else {
      handleAddQuestion();
    }
  };

  // Handle question form submission
  const handleQuestionSubmit = (
    values: z.infer<typeof questionSchema>,
    questionIndex: number,
  ) => {
    const question: Question = {
      question: values.question,
      options: values.options
        .split('\n')
        .filter((line) => line.trim() !== '')
        .map((line) => line.trim()),
      correctAnswer: values.correctAnswer,
    };

    setQuizData((prev) => {
      const updatedQuestions = [...(prev.questions || [])];
      updatedQuestions[questionIndex] = question;
      return { ...prev, questions: updatedQuestions };
    });

    // Navigate to the next question if it exists, or back to quiz details
    if (quizData.questions && questionIndex < quizData.questions.length - 1) {
      setActiveTab(`question-${questionIndex + 2}`);
    } else {
      setActiveTab('quiz-detail');
    }
  };

  // Add a new question
  const handleAddQuestion = () => {
    setQuizData((prev) => ({
      ...prev,
      questions: [
        ...(prev.questions || []),
        { question: '', options: [], correctAnswer: '' },
      ],
    }));

    const newQuestionIndex = quizData.questions ? quizData.questions.length : 0;
    setActiveTab(`question-${newQuestionIndex + 1}`);
  };

  // Submit the complete quiz
  const handleSubmitQuiz = () => {
    if (
      !quizData.title ||
      !quizData.description ||
      !quizData.difficulty ||
      !quizData.estimatedTimeInMinutes ||
      !quizData.tags?.length ||
      !quizData.questions?.length
    ) {
      alert('Please complete all required fields before submitting the quiz');
      return;
    }

    // Here you would make an API call to submit the quiz
    console.log('Submitting quiz:', quizData);
    // For now we'll just log the data
    alert('Quiz submitted successfully!');
  };

  // Can submit quiz if all required data is present
  const canSubmitQuiz = Boolean(
    quizData.title &&
      quizData.description &&
      quizData.difficulty &&
      quizData.estimatedTimeInMinutes &&
      quizData.tags?.length &&
      quizData.questions?.length &&
      quizData.questions.every(
        (q) => q.question && q.options.length && q.correctAnswer,
      ),
  );

  return (
    <div className="flex min-h-screen flex-col bg-gradient-to-br from-blue-50 via-white to-purple-50">
      <Tabs value={activeTab} onValueChange={setActiveTab} className="flex-1">
        <TabsContent
          value="quiz-detail"
          className="h-full overflow-hidden p-4 pb-20"
        >
          <QuizDetailsForm
            quizData={quizData}
            onSubmit={handleQuizDetailsSubmit}
          />
        </TabsContent>

        {quizData.questions?.map((question, index) => (
          <TabsContent
            key={`question-${index + 1}`}
            value={`question-${index + 1}`}
            className="h-full overflow-hidden p-4 pb-20"
          >
            <QuestionForm
              question={question}
              questionIndex={index}
              onSubmit={handleQuestionSubmit}
            />
          </TabsContent>
        ))}

        <QuizNavigation
          questions={quizData.questions || []}
          onAddQuestion={handleAddQuestion}
          onSubmitQuiz={handleSubmitQuiz}
          canSubmitQuiz={canSubmitQuiz}
        />
      </Tabs>
    </div>
  );
};
