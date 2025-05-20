'use client';

import { Search, Plus } from 'lucide-react';
import { useState } from 'react';

import { Button } from '@/components/ui/button';
import { Link } from '@/components/ui/link';
import QuizCard from '@/features/quiz/components/quiz-card';
import QuizzesList from '@/features/quiz/components/quizzes-list';
import { Quiz } from '@/types/api';

// Mock quiz data - Available quizzes
const AVAILABLE_QUIZZES: Quiz[] = [
  {
    id: '3',
    title: 'Web Development Basics',
    description:
      'Learn about HTML, CSS, and JavaScript fundamentals with this interactive quiz.',
    questionCount: 12,
    difficulty: 'Easy',
    estimatedTimeInMinutes: 15,
    tags: ['Web', 'Frontend', 'HTML', 'CSS'],
  },
  {
    id: '4',
    title: 'Database Concepts',
    description:
      'Test your knowledge of SQL, NoSQL, and database design principles.',
    questionCount: 18,
    difficulty: 'Medium',
    estimatedTimeInMinutes: 25,
    tags: ['SQL', 'Database', 'Design'],
  },
  {
    id: '5',
    title: 'Machine Learning Fundamentals',
    description:
      'Explore basic concepts in ML including regression, classification, and neural networks.',
    questionCount: 25,
    difficulty: 'Hard',
    estimatedTimeInMinutes: 40,
    tags: ['ML', 'AI', 'Data Science'],
  },
];

const OverviewPage = () => {
  const [searchTerm, setSearchTerm] = useState('');

  const filteredAvailableQuizzes = AVAILABLE_QUIZZES.filter(
    (quiz) =>
      quiz.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
      quiz.description.toLowerCase().includes(searchTerm.toLowerCase()) ||
      quiz.tags.some((tag) =>
        tag.toLowerCase().includes(searchTerm.toLowerCase()),
      ),
  );

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-white to-purple-50 pb-12">
      <div className="container mx-auto px-4 py-12">
        <div className="mb-10">
          <h1 className="text-4xl font-bold tracking-tight text-gray-900">
            StudySpace
          </h1>
          <p className="mt-3 text-lg text-gray-600">
            Create and take quizzes to enhance your learning journey
          </p>
        </div>

        <div className="mb-8 flex items-center gap-4">
          <div className="relative flex-1">
            <div className="pointer-events-none absolute inset-y-0 left-0 flex items-center pl-3">
              <Search className="size-5 text-gray-400" />
            </div>
            <input
              type="text"
              className="block w-full rounded-md border-gray-300 bg-white py-2 pl-10 pr-3 shadow-sm placeholder:text-gray-400 focus:border-blue-500 focus:outline-none focus:ring-1 focus:ring-blue-500"
              placeholder="Search quizzes by title, description, or tags..."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
          </div>
          <Link href="/quizzes/new">
            <Button
              variant="default"
              size="lg"
              className="flex flex-row items-center gap-2 bg-gradient-to-r from-blue-600 to-indigo-600 hover:from-blue-700 hover:to-indigo-700"
            >
              <Plus className="size-5" />
            </Button>
          </Link>
        </div>

        <div className="mb-12">
          <div className="mb-6">
            <h2 className="text-2xl font-semibold text-gray-900">
              Your Quizzes
            </h2>
            <p className="text-sm text-gray-600">
              Quizzes you&#39;ve created and manage
            </p>
          </div>
          <QuizzesList searchTerm={searchTerm} />
        </div>

        <div>
          <div className="mb-6">
            <h2 className="text-2xl font-semibold text-gray-900">
              Available Quizzes
            </h2>
            <p className="text-sm text-gray-600">
              Quizzes shared by other members of the community
            </p>
          </div>
          {filteredAvailableQuizzes.length === 0 ? (
            <div className="rounded-xl bg-white p-12 text-center shadow-sm">
              <h3 className="text-xl font-medium text-gray-900">
                No quizzes found
              </h3>
              <p className="mt-2 text-gray-600">
                Try adjusting your search criteria
              </p>
            </div>
          ) : (
            <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
              {filteredAvailableQuizzes.map((quiz) => (
                <QuizCard key={quiz.id} quiz={quiz} />
              ))}
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default OverviewPage;
