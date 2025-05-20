import { FieldError } from 'react-hook-form';
import { z } from 'zod';

import { Button } from '@/components/ui/button';
import { Form, Input, AutosizeTextarea } from '@/components/ui/form';
import type { Question } from '@/types/api';

export const questionSchema = z.object({
  question: z.string().min(1, 'Question is required'),
  options: z.string().min(1, 'Options are required'),
  correctAnswer: z.string().min(1, 'Correct answer is required'),
});

type QuestionFormProps = {
  question: Question;
  questionIndex: number;
  onSubmit: (
    values: z.infer<typeof questionSchema>,
    questionIndex: number,
  ) => void;
};

const getFieldError = (error: any): FieldError | undefined => {
  return error as FieldError | undefined;
};

export const QuestionForm = ({
  question,
  questionIndex,
  onSubmit,
}: QuestionFormProps) => {
  return (
    <div className="mx-auto max-w-2xl">
      <div className="mb-6">
        <h1 className="text-3xl font-bold tracking-tight text-gray-900">
          Question {questionIndex + 1}
        </h1>
      </div>

      <div className="rounded-xl bg-white/80 p-6 shadow-sm backdrop-blur-sm">
        <Form
          onSubmit={(values) => onSubmit(values, questionIndex)}
          schema={questionSchema}
          id={`question-${questionIndex + 1}-form`}
        >
          {({ register, formState }) => (
            <>
              <AutosizeTextarea
                label="Question"
                error={getFieldError(formState.errors.question)}
                registration={register('question')}
                defaultValue={question.question}
                placeholder="Enter your question here"
              />
              <AutosizeTextarea
                label="Options (One per line)"
                error={getFieldError(formState.errors.options)}
                registration={register('options')}
                defaultValue={question.options.join('\n')}
                placeholder="Option 1&#10;Option 2&#10;Option 3&#10;Option 4"
              />
              <Input
                label="Correct Answer"
                error={getFieldError(formState.errors.correctAnswer)}
                registration={register('correctAnswer')}
                defaultValue={question.correctAnswer}
                placeholder="Must match exactly one of the options above"
              />

              <div className="mt-6">
                <Button type="submit" className="w-full">
                  Save Question
                </Button>
              </div>
            </>
          )}
        </Form>
      </div>
    </div>
  );
};
