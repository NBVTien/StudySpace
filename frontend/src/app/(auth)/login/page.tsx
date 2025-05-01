'use client';

import { useSearchParams, useRouter } from 'next/navigation';

import { paths } from '@/config/paths';
import { LoginForm } from '@/features/auth/components/login-form';

const LoginPage = () => {
  const router = useRouter();
  const searchParams = useSearchParams();
  const redirectTo = searchParams?.get('redirectTo');

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
        <LoginForm
          onSuccess={() => {
            router.replace(
              `${redirectTo ? `${decodeURIComponent(redirectTo)}` : paths.overview.getHref()}`,
            );
          }}
        />
      </div>
    </div>
  );
};

export default LoginPage;
