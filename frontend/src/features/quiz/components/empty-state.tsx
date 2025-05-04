import { Plus } from 'lucide-react';

import { Button } from '@/components/ui/button';
import { Link } from '@/components/ui/link';

type EmptyStateProps = {
  title: string;
  description: string;
  showCreateButton?: boolean;
};

const EmptyState = ({
  title,
  description,
  showCreateButton = false,
}: EmptyStateProps) => {
  return (
    <div className="rounded-xl bg-white p-12 text-center shadow-sm">
      <h3 className="text-xl font-medium text-gray-900">{title}</h3>
      <p className="mt-2 text-gray-600">{description}</p>
      {showCreateButton && (
        <Link href="/quiz/create" className="mt-6 inline-block">
          <Button variant="default" className="flex items-center gap-2">
            <Plus className="size-4" />
            Create Quiz
          </Button>
        </Link>
      )}
    </div>
  );
};

export default EmptyState;
