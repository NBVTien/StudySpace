'use client';

import * as z from 'zod';

import { Button } from '@/components/ui/button';
import { Form, Input } from '@/components/ui/form';
import { Link } from '@/components/ui/link';

const schema = z.object({
  email: z.string().min(1, 'Required').email('Invalid email'),
  password: z.string().min(1, 'Required'),
});

const LoginPage = () => {
  return (
    <div className="flex min-h-screen items-center justify-center bg-gradient-to-br from-blue-50 via-white to-purple-50">
      <div className="w-full max-w-md space-y-8 rounded-xl bg-white/80 p-8 shadow-lg backdrop-blur-sm">
        <div className="text-center">
          <h1 className="text-3xl font-bold tracking-tight text-gray-900">
            Welcome back
          </h1>
          <p className="mt-2 text-sm text-gray-600">
            Please sign in to your account
          </p>
        </div>

        <Form
          onSubmit={async (values) => {
            // TODO: Implement login functionality
            console.log(values);
          }}
          schema={schema}
        >
          {({ register, formState }) => (
            <>
              <div className="space-y-4">
                <Input
                  label="Email Address"
                  type="email"
                  registration={register('email')}
                  error={formState.errors['email']}
                />

                <Input
                  label="Password"
                  type="password"
                  registration={register('password')}
                  error={formState.errors['password']}
                />
              </div>

              <div className="mt-4 flex items-center justify-between">
                <div className="text-sm">
                  <Link
                    href="/register"
                    className="text-blue-600 hover:text-blue-500"
                  >
                    Don&apos;t have an account?
                  </Link>
                </div>

                <Link
                  href="/forgot-password"
                  className="text-sm text-blue-600 hover:text-blue-500"
                >
                  Forgot password?
                </Link>
              </div>

              <Button type="submit" className="mt-6 w-full">
                Sign in
              </Button>
            </>
          )}
        </Form>
      </div>
    </div>
  );
};

export default LoginPage;
