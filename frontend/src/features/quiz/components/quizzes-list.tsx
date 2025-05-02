import { useQuizzes } from '@/features/quiz/api/get-quizzes';
import QuizCard from '@/features/quiz/components/quiz-card';

const QuizzesList = ({ searchTerm }: { searchTerm: string }) => {
  const quizzesQuery = useQuizzes();

  if (quizzesQuery.isLoading) {
    return <div>Loading...</div>;
  }

  const quizzes = quizzesQuery.data;

  if (!quizzes) {
    return null;
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
    <div className="rounded-xl bg-white p-12 text-center shadow-sm">
      <h3 className="text-xl font-medium text-gray-900">No quizzes found</h3>
      <p className="mt-2 text-gray-600">
        {searchTerm
          ? 'Try adjusting your search criteria'
          : 'Start by creating your first quiz'}
      </p>
    </div>
  ) : (
    <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
      {filteredQuizzes.map((quiz) => (
        <QuizCard key={quiz.id} quiz={quiz} isOwner={true} />
      ))}
    </div>
  );
};

export default QuizzesList;
