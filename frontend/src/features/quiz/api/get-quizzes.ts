import { queryOptions, useQuery } from '@tanstack/react-query';

import { api } from '@/lib/api-client';
import { Quiz } from '@/types/api';

export const getQuizzes = async (): Promise<Quiz[]> => {
  return api.get('/quizzes');
};

const quizzesQueryKey = ['quizzes'];

export const getQuizzesQueryOptions = () => {
  return queryOptions({
    queryKey: quizzesQueryKey,
    queryFn: getQuizzes,
  });
};

export const useQuizzes = () => useQuery(getQuizzesQueryOptions());
