'use client';

import { useRouter } from 'next/navigation';

import { NewQuizContainer } from '@/features/quiz/components/new-quiz-container';

const NewQuizPage = () => {
  const router = useRouter();

  const handleSuccess = () => {
    router.push('/overview');
  };

  return <NewQuizContainer onSuccess={handleSuccess} />;
};

export default NewQuizPage;
