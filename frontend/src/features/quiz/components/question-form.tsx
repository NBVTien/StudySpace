import { Plus, X } from 'lucide-react';
import React, { useState } from 'react';
import { FieldError } from 'react-hook-form';
import { z } from 'zod';

import { Button } from '@/components/ui/button';
import { Form, AutosizeTextarea } from '@/components/ui/form';
import { Error } from '@/components/ui/form/error';
import { Label } from '@/components/ui/form/label';
import type { Question } from '@/types/api';

export const questionSchema = z
  .object({
    question: z.string().min(1, 'Question is required.'),
    options: z
      .array(z.string().min(1, 'Option is required.'))
      .min(1, 'At least one option is needed. Try again.'),
    correctAnswer: z.string().min(1, 'Correct answer is required.'),
  })
  .refine((data) => data.options.includes(data.correctAnswer), {
    message: 'Correct answer must be one of the options.',
    path: ['correctAnswer'],
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
  const [questionOptions, setQuestionOptions] = useState<string[]>(
    question.options.length > 0 ? question.options : [],
  );

  const handleOptionChange = (index: number, value: string) => {
    const newOptions = [...questionOptions];
    newOptions[index] = value;
    setQuestionOptions(newOptions);
  };

  const addOption = () => {
    setQuestionOptions([...questionOptions, '']);
  };

  const removeOption = (index: number) => {
    if (questionOptions.length > 1) {
      const newOptions = [...questionOptions];
      newOptions.splice(index, 1);
      setQuestionOptions(newOptions);
    }
  };

  const handleFormSubmit = (values: any) => {
    const filteredOptions = questionOptions.filter((opt) => opt.trim() !== '');

    const modifiedValues = {
      ...values,
      options: filteredOptions,
    };

    onSubmit(modifiedValues, questionIndex);
  };

  return (
    <div className="mx-auto max-w-2xl">
      <div className="rounded-xl bg-white/80 p-6 shadow-sm backdrop-blur-sm">
        <div className="mb-6">
          <h1 className="text-3xl font-bold tracking-tight text-gray-900">
            Question #{questionIndex + 1}
          </h1>
        </div>

        <Form
          onSubmit={handleFormSubmit}
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

              <div className="mb-2 mt-4">
                <div className="flex flex-row items-center justify-between">
                  <Label>Options</Label>
                  <Button
                    type="button"
                    variant="outline"
                    className="mt-2 flex flex-row items-center gap-1"
                    onClick={addOption}
                  >
                    <Plus size={16} />
                    <span>Add Option</span>
                  </Button>
                </div>
                <Error
                  errorMessage={
                    getFieldError(formState.errors.options)?.message
                  }
                />

                {questionOptions.map((_, index: number) => {
                  return (
                    <div key={index} className="mt-2 flex items-center">
                      <Button
                        type="button"
                        variant="ghost"
                        className="ml-2 p-2"
                        onClick={() => removeOption(index)}
                        disabled={questionOptions.length <= 1}
                      >
                        <X size={18} />
                      </Button>
                      <AutosizeTextarea
                        error={getFieldError(
                          Array.isArray(formState.errors.options)
                            ? formState.errors.options[index]
                            : undefined,
                        )}
                        registration={register(`options.${index}`)}
                        defaultValue={question.options[index]}
                        placeholder={`Option #${index + 1}`}
                        onChange={(e) => {
                          handleOptionChange(index, e.target.value);
                        }}
                      />
                    </div>
                  );
                })}
              </div>

              <div className="mt-4">
                <Label>Correct Answer</Label>
                <Error
                  errorMessage={
                    getFieldError(formState.errors.correctAnswer)?.message
                  }
                />

                <select
                  className="block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
                  {...register('correctAnswer')}
                  defaultValue={question.correctAnswer}
                >
                  <option value="" disabled>
                    Select correct answer
                  </option>
                  {questionOptions
                    .filter((opt) => opt.trim() !== '')
                    .map((option, index) => (
                      <option key={index} value={option}>
                        {option}
                      </option>
                    ))}
                </select>
              </div>

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
