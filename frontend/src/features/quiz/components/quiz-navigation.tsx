import { Home, PlusCircle, Send } from 'lucide-react';

import { Button } from '@/components/ui/button';
import { TabsList, TabsTrigger } from '@/components/ui/tabs';
import type { Question } from '@/types/api';

type QuizNavigationProps = {
  questions: Question[];
  onAddQuestion: () => void;
  onSubmitQuiz: () => void;
  canSubmitQuiz: boolean;
};

export const QuizNavigation = ({
  questions,
  onAddQuestion,
  onSubmitQuiz,
  canSubmitQuiz,
}: QuizNavigationProps) => {
  return (
    <TabsList className="fixed inset-x-0 bottom-0 z-50 flex h-14 w-full items-center justify-start border-t bg-background shadow-lg">
      <TabsTrigger
        value="quiz-detail"
        className="flex justify-center gap-2 py-3"
      >
        <Home size={18} />
      </TabsTrigger>

      {questions?.map((_, index) => (
        <TabsTrigger
          key={`tab-question-${index + 1}`}
          value={`question-${index + 1}`}
          className="flex justify-center gap-2 py-3"
        >
          <span>{index + 1}</span>
        </TabsTrigger>
      ))}

      <div className="ml-auto flex items-center">
        {/* Add question button */}
        <Button
          variant="ghost"
          onClick={onAddQuestion}
          className="flex items-center px-3"
          icon={<PlusCircle size={18} />}
        >
          Add Question
        </Button>

        {/* Submit quiz button */}
        <Button
          variant="default"
          onClick={onSubmitQuiz}
          disabled={!canSubmitQuiz}
          className="flex flex-row items-center px-3"
          icon={<Send size={18} />}
        >
          Submit Quiz
        </Button>
      </div>
    </TabsList>
  );
};
