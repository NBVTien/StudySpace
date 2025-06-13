import NextLink from 'next/link';
import { useSearchParams } from 'next/navigation';
import { useState } from 'react';

import { Button } from '@/components/ui/button';
import { Form, Input } from '@/components/ui/form';
import { Error } from '@/components/ui/form/error';
import { paths } from '@/config/paths';
import { useLogin, loginInputSchema } from '@/lib/auth';

type LoginFormProps = {
  onSuccess: () => void;
};

export const LoginForm = ({ onSuccess }: LoginFormProps) => {
  const [loginError, setLoginError] = useState<string | null>(null);

  const login = useLogin({
    onSuccess,
    onError: (error) => {
      setLoginError(
        error.message ||
          'Failed to login. Please check your credentials and try again.',
      );
    },
  });

  const searchParams = useSearchParams();
  const redirectTo = searchParams?.get('redirectTo');
  return (
    <div>
      <Form
        onSubmit={(values) => {
          setLoginError(null);
          login.mutate(values);
        }}
        schema={loginInputSchema}
      >
        {({ register, formState }) => (
          <>
            <Input
              type="username"
              label="Username"
              error={formState.errors['username']}
              registration={register('username')}
            />
            <Input
              type="password"
              label="Password"
              error={formState.errors['password']}
              registration={register('password')}
            />

            {loginError && (
              <div className="mt-4">
                <Error errorMessage={loginError} />
              </div>
            )}

            <div className="mt-4">
              <Button
                isLoading={login.isPending}
                type="submit"
                className="w-full"
              >
                Log in
              </Button>
            </div>
          </>
        )}
      </Form>
      <div className="mt-2 flex items-center justify-end">
        <div className="text-sm">
          <NextLink
            href={paths.auth.register.getHref(redirectTo)}
            className="font-medium text-blue-600 hover:text-blue-500"
          >
            Register
          </NextLink>
        </div>
      </div>
    </div>
  );
};
