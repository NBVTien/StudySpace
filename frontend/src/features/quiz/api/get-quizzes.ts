import { queryOptions, useQuery } from '@tanstack/react-query';

import { api } from '@/lib/api-client';
import { Quizzes } from '@/types/api';

export const getQuizzes = async (): Promise<Quizzes> => {
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
