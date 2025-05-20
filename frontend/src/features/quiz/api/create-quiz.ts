import { api } from '@/lib/api-client';
import type { QuizRequest } from '@/types/api';

export const createQuiz = async (quiz: QuizRequest) => {
  return api.post('/api/quizzes', { data: quiz });
};
