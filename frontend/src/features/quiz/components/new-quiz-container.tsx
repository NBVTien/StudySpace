import { useState } from 'react';
import { z } from 'zod';

import { TabsContent, Tabs } from '@/components/ui/tabs';
import type { Question, QuizRequest } from '@/types/api';

import { useCreateQuiz } from '../api/create-quiz';

import { QuestionForm, questionSchema } from './question-form';
import { QuizDetailsForm, quizDetailsSchema } from './quiz-details-form';
import { QuizNavigation } from './quiz-navigation';

export const NewQuizContainer = () => {
  const [quizData, setQuizData] = useState<Partial<QuizRequest>>({
    questions: [],
    tags: [],
  });
  const [activeTab, setActiveTab] = useState('quiz-detail');
  const createQuizMutation = useCreateQuiz();

  const handleQuizDetailsSubmit = (
    values: z.infer<typeof quizDetailsSchema>,
  ) => {
    const tags = values.tagsInput
      .split(',')
      .map((tag: string) => tag.trim())
      .filter(Boolean);

    setQuizData((prev) => ({
      ...prev,
      title: values.title,
      description: values.description,
      difficulty: values.difficulty,
      estimatedTimeInMinutes: values.estimatedTimeInMinutes,
      tags,
    }));

    if (quizData.questions && quizData.questions.length > 0) {
      setActiveTab('question-1');
    } else {
      handleAddQuestion();
    }
  };

  const handleQuestionSubmit = (
    values: z.infer<typeof questionSchema>,
    questionIndex: number,
  ) => {
    const question: Question = {
      question: values.question,
      options: values.options,
      correctAnswer: values.correctAnswer,
    };

    setQuizData((prev) => {
      const updatedQuestions = [...(prev.questions || [])];
      updatedQuestions[questionIndex] = question;
      return { ...prev, questions: updatedQuestions };
    });

    if (quizData.questions && questionIndex < quizData.questions.length - 1) {
      setActiveTab(`question-${questionIndex + 2}`);
    } else {
      setActiveTab('quiz-detail');
    }
  };

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

  const handleDeleteQuestion = (questionIndex: number) => {
    setQuizData((prev) => {
      const updatedQuestions = [...(prev.questions || [])];
      updatedQuestions.splice(questionIndex, 1);
      return { ...prev, questions: updatedQuestions };
    });

    if (activeTab === `question-${questionIndex + 1}`) {
      if (questionIndex > 0) {
        setActiveTab(`question-${questionIndex}`);
      } else {
        setActiveTab('quiz-detail');
      }
    } else if (parseInt(activeTab.split('-')[1]) > questionIndex + 1) {
      const currentQuestionNumber = parseInt(activeTab.split('-')[1]);
      setActiveTab(`question-${currentQuestionNumber - 1}`);
    }
  };

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
    console.log(quizData);
    createQuizMutation.mutate(quizData as QuizRequest);
  };

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
              onDelete={handleDeleteQuestion}
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
