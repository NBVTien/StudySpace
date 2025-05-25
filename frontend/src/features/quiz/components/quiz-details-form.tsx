import { FieldError } from 'react-hook-form';
import { z } from 'zod';

import { Button } from '@/components/ui/button';
import { Form, Input, FormSelect, Textarea } from '@/components/ui/form';
import type { QuizRequest } from '@/types/api';

export const quizDetailsSchema = z.object({
  title: z.string().min(1, 'Title is required'),
  description: z.string().min(1, 'Description is required'),
  difficulty: z.enum(['Easy', 'Medium', 'Hard']),
  estimatedTimeInMinutes: z.coerce.number().min(1, 'Time estimate is required'),
  tagsInput: z.string(),
});

type QuizDetailsFormProps = {
  quizData: Partial<QuizRequest>;
  onSubmit: (values: z.infer<typeof quizDetailsSchema>) => void;
};

const getFieldError = (error: any): FieldError | undefined => {
  return error as FieldError | undefined;
};

export const QuizDetailsForm = ({
  quizData,
  onSubmit,
}: QuizDetailsFormProps) => {
  return (
    <div className="mx-auto max-w-2xl">
      <div className="rounded-xl bg-white/80 p-6 shadow-sm backdrop-blur-sm">
        <div className="mb-6">
          <h1 className="text-3xl font-bold tracking-tight text-gray-900">
            Create New Quiz
          </h1>
          <p className="mt-2 text-sm text-gray-600">
            Fill in the details to create your new quiz.
          </p>
        </div>
        <Form
          onSubmit={onSubmit}
          schema={quizDetailsSchema}
          id="quiz-details-form"
        >
          {({ register, formState }) => (
            <>
              <Input
                label="Title"
                error={getFieldError(formState.errors.title)}
                registration={register('title')}
                defaultValue={quizData.title}
              />
              <Textarea
                label="Description"
                error={getFieldError(formState.errors.description)}
                registration={register('description')}
                defaultValue={quizData.description}
              />
              <div className="grid grid-cols-2 gap-3">
                <FormSelect
                  label="Difficulty"
                  error={getFieldError(formState.errors.difficulty)}
                  registration={register('difficulty')}
                  defaultValue={quizData.difficulty}
                  options={[
                    { label: 'Easy', value: 'Easy' },
                    { label: 'Medium', value: 'Medium' },
                    { label: 'Hard', value: 'Hard' },
                  ]}
                />
                <Input
                  type="number"
                  label="Estimated Time (minutes)"
                  error={getFieldError(formState.errors.estimatedTimeInMinutes)}
                  registration={register('estimatedTimeInMinutes')}
                  defaultValue={quizData.estimatedTimeInMinutes?.toString()}
                />
              </div>
              <Input
                label="Tags (comma separated)"
                error={getFieldError(formState.errors.tagsInput)}
                registration={register('tagsInput')}
                defaultValue={quizData.tags?.join(', ')}
                placeholder="e.g. javascript, programming, web development"
              />

              <div className="mt-6 flex justify-between">
                <Button type="submit" className="w-full">
                  {quizData.questions && quizData.questions.length > 0
                    ? 'Save Details'
                    : 'Continue to Add Questions'}
                </Button>
              </div>
            </>
          )}
        </Form>
      </div>
    </div>
  );
};
