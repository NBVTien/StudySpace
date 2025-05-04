import { useQuizzes } from '@/features/quiz/api/get-quizzes';
import QuizCard from '@/features/quiz/components/quiz-card';

import EmptyState from './empty-state';

const QuizzesList = ({ searchTerm }: { searchTerm: string }) => {
  const quizzesQuery = useQuizzes();

  if (quizzesQuery.isLoading) {
    return <div>Loading...</div>;
  }

  const quizzes = quizzesQuery.data?.data;

  if (!quizzes || quizzes.length === 0) {
    return (
      <EmptyState
        title="No quizzes yet"
        description="Get started by creating your first quiz"
        showCreateButton
      />
    );
  }

  const filteredQuizzes = quizzes.filter(
    (quiz) =>
      quiz.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
      quiz.description.toLowerCase().includes(searchTerm.toLowerCase()) ||
      quiz.tags.some((tag) =>
        tag.toLowerCase().includes(searchTerm.toLowerCase()),
      ),
  );

  return filteredQuizzes.length === 0 ? (
    <EmptyState
      title="No quizzes found"
      description={
        searchTerm
          ? 'Try adjusting your search criteria'
          : 'Start by creating your first quiz'
      }
      showCreateButton={!searchTerm}
    />
  ) : (
    <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
      {filteredQuizzes.map((quiz) => (
        <QuizCard key={quiz.id} quiz={quiz} isOwner={true} />
      ))}
    </div>
  );
};

export default QuizzesList;
