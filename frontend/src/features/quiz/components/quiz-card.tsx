import { BookOpen, Clock } from 'lucide-react';

import { Button } from '@/components/ui/button';
import { Link } from '@/components/ui/link';
import { Quiz } from '@/types/api';

const QuizCard = ({
  quiz,
  isOwner = false,
}: {
  quiz: Quiz;
  isOwner?: boolean;
}) => {
  const difficultyColor = {
    Easy: 'bg-green-100 text-green-800',
    Medium: 'bg-yellow-100 text-yellow-800',
    Hard: 'bg-red-100 text-red-800',
  }[quiz.difficulty];

  return (
    <div className="group relative rounded-xl bg-white p-6 shadow-md transition-all hover:shadow-lg">
      {/* {quiz.completed && (
        <div className="absolute -right-2 -top-2 rounded-full bg-green-500 p-1.5 text-white">
          <Star className="size-4" />
        </div>
      )} */}
      <div className="flex justify-between">
        <h3 className="text-xl font-semibold text-gray-900">{quiz.title}</h3>
        <span
          className={`rounded-full px-3 py-1 text-xs font-medium ${difficultyColor}`}
        >
          {quiz.difficulty}
        </span>
      </div>
      <p className="mt-2 line-clamp-2 text-sm text-gray-600">
        {quiz.description}
      </p>
      <div className="mt-4 flex flex-wrap gap-2">
        {quiz.tags.map((tag) => (
          <span
            key={tag}
            className="rounded-full bg-blue-50 px-2.5 py-0.5 text-xs font-medium text-blue-700"
          >
            {tag}
          </span>
        ))}
      </div>
      <div className="mt-4 flex items-center justify-between text-sm text-gray-500">
        <div className="flex items-center gap-4">
          <div className="flex items-center gap-1">
            <BookOpen className="size-4" />
            <span>{quiz.questionCount} questions</span>
          </div>
          <div className="flex items-center gap-1">
            <Clock className="size-4" />
            <span>{quiz.estimatedTimeInMinutes} mins</span>
          </div>
        </div>
      </div>
      <div className="mt-4 border-t border-gray-100 pt-4">
        <div className="flex items-center justify-between">
          <div className="flex gap-2">
            {isOwner ? (
              <>
                <Link href={`/quiz/${quiz.id}/edit`}>
                  <Button variant="outline" size="sm">
                    Edit
                  </Button>
                </Link>
                <Link href={`/quiz/${quiz.id}/results`}>
                  <Button variant="outline" size="sm">
                    Results
                  </Button>
                </Link>
              </>
            ) : (
              <Link href={`/quiz/${quiz.id}`}>
                <Button variant="default" size="sm">
                  Start Quiz
                </Button>
              </Link>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default QuizCard;
