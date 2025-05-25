import { Plus, X } from 'lucide-react';
import React, { useState } from 'react';
import { FieldError } from 'react-hook-form';
import { z } from 'zod';

import { Button } from '@/components/ui/button';
import { Form, AutosizeTextarea, FormSelect } from '@/components/ui/form';
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
    const newOptions = [...questionOptions];
    newOptions.splice(index, 1);
    setQuestionOptions(newOptions);
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

              <div className="mb-2 mt-4 flex flex-col">
                <div className="flex flex-row items-center justify-between">
                  <Label>Options</Label>
                  <Button
                    type="button"
                    variant="outline"
                    size="default"
                    className="mt-2 flex flex-row items-center gap-1"
                    onClick={addOption}
                    icon={<Plus size={16} />}
                  >
                    Add Option
                  </Button>
                </div>
                <Error
                  errorMessage={
                    getFieldError(formState.errors.options)?.message
                  }
                />

                {questionOptions.length === 0 ? (
                  <div className="mt-4 rounded-md bg-gray-50 p-4 text-center text-sm text-gray-500">
                    No options added yet. Click the &quot;Add Option&quot;
                    button to add answers for this question.
                  </div>
                ) : (
                  questionOptions.map((_, index: number) => {
                    return (
                      <div key={index} className="mt-2 flex w-full items-start">
                        <Button
                          type="button"
                          variant="ghost"
                          className="ml-2 p-2"
                          onClick={() => removeOption(index)}
                        >
                          <X size={18} />
                        </Button>
                        <AutosizeTextarea
                          error={getFieldError(
                            Array.isArray(formState.errors.options)
                              ? formState.errors.options[index]
                              : undefined,
                          )}
                          minHeight={25}
                          registration={register(`options.${index}`)}
                          defaultValue={question.options[index]}
                          placeholder={`Option #${index + 1}`}
                          onChange={(e) => {
                            handleOptionChange(index, e.target.value);
                          }}
                        />
                      </div>
                    );
                  })
                )}
              </div>

              <FormSelect
                label="Correct answer"
                error={getFieldError(formState.errors.correctAnswer)}
                registration={register('correctAnswer')}
                defaultValue={question.correctAnswer}
                options={questionOptions
                  .filter((opt) => opt.trim() !== '')
                  .map((option) => ({ label: option, value: option }))}
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
