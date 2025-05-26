import { useMutation, useQueryClient } from '@tanstack/react-query';

import { api } from '@/lib/api-client';
import { MutationConfig } from '@/lib/react-query';
import type { QuizRequest } from '@/types/api';

import { getQuizzesQueryOptions } from './get-quizzes';

export const createQuiz = async (quiz: QuizRequest) => {
  return api.post('/quizzes', quiz);
};

type UseCreateQuizOptions = {
  mutationConfig?: MutationConfig<typeof createQuiz>;
};

export const useCreateQuiz = ({
  mutationConfig,
}: UseCreateQuizOptions = {}) => {
  const queryClient = useQueryClient();

  const { onSuccess, ...restConfig } = mutationConfig || {};

  return useMutation({
    onSuccess: (...args) => {
      queryClient.invalidateQueries({
        queryKey: getQuizzesQueryOptions().queryKey,
      });
      onSuccess?.(...args);
    },
    ...restConfig,
    mutationFn: createQuiz,
  });
};
