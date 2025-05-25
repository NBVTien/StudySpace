'use client';

import * as React from 'react';
import { Controller, UseFormRegisterReturn } from 'react-hook-form';

import {
  Select,
  SelectTrigger,
  SelectValue,
  SelectContent,
  SelectItem,
} from '@/components/ui/select';
import { cn } from '@/utils/cn';

import { FieldWrapper, FieldWrapperPassThroughProps } from './field-wrapper';

type Option = {
  label: React.ReactNode;
  value: string | number | string[];
};

type FormSelectFieldProps = FieldWrapperPassThroughProps & {
  options: Option[];
  className?: string;
  defaultValue?: string;
  registration: Partial<UseFormRegisterReturn>;
};

export const FormSelect = (props: FormSelectFieldProps) => {
  const { label, options, error, className, defaultValue, registration } =
    props;

  const name = registration.name || '';

  return (
    <FieldWrapper label={label} error={error}>
      <Controller
        name={name}
        defaultValue={defaultValue}
        render={({ field }) => (
          <Select defaultValue={field.value} onValueChange={field.onChange}>
            <SelectTrigger className={cn(className)}>
              <SelectValue placeholder={label} />
            </SelectTrigger>
            <SelectContent>
              {options.map(({ label, value }) => (
                <SelectItem key={value?.toString()} value={value.toString()}>
                  {label}
                </SelectItem>
              ))}
            </SelectContent>
          </Select>
        )}
      />
    </FieldWrapper>
  );
};
