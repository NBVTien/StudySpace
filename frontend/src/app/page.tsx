import { BookOpen, Medal, Users } from 'lucide-react';

import { Button } from '@/components/ui/button';
import { Link } from '@/components/ui/link';

const HomePage = () => {
  return (
    <div className="relative min-h-screen overflow-hidden bg-gradient-to-br from-blue-50 via-white to-purple-50">
      <div className="absolute inset-0">
        <div className="absolute -left-4 top-20 size-72 animate-blob rounded-full bg-purple-100 opacity-70 mix-blend-multiply blur-xl"></div>
        <div className="animation-delay-2000 absolute -right-4 top-40 size-72 animate-blob rounded-full bg-yellow-100 opacity-70 mix-blend-multiply blur-xl"></div>
        <div className="animation-delay-4000 absolute -bottom-8 left-20 size-72 animate-blob rounded-full bg-pink-100 opacity-70 mix-blend-multiply blur-xl"></div>
      </div>

      {/* Main content */}
      <div className="relative h-full">
        <div className="mx-auto max-w-7xl px-6 py-24 sm:py-32 lg:px-8">
          <div className="text-center">
            <h1 className="text-5xl font-bold tracking-tight text-gray-900 sm:text-7xl">
              <span className="block bg-gradient-to-r from-blue-600 via-purple-600 to-indigo-600 bg-clip-text text-transparent">
                StudySpace
              </span>
            </h1>
            <p className="mt-6 text-xl leading-8 text-gray-600 sm:text-2xl">
              Your collaborative learning platform where knowledge meets
              community
            </p>
            <div className="mt-10 flex items-center justify-center gap-6">
              <Link href="/register">
                <Button
                  variant="default"
                  size="lg"
                  className="animate-bounce-subtle"
                >
                  Get Started
                </Button>
              </Link>
              <Link href="/login">
                <Button
                  variant="outline"
                  size="lg"
                  className="hover:bg-gray-50"
                >
                  Login
                </Button>
              </Link>
            </div>

            {/* Feature highlights */}
            <div className="mt-20 grid grid-cols-1 gap-8 sm:grid-cols-2 lg:grid-cols-3">
              <div className="rounded-xl bg-white/50 p-6 backdrop-blur-sm">
                <div className="text-blue-600">
                  <Users className="mx-auto size-8" />
                </div>
                <h3 className="mt-4 text-lg font-semibold">
                  Collaborative Learning
                </h3>
                <p className="mt-2 text-gray-600">
                  Connect with peers and learn together
                </p>
              </div>
              <div className="rounded-xl bg-white/50 p-6 backdrop-blur-sm">
                <div className="text-purple-600">
                  <BookOpen className="mx-auto size-8" />
                </div>
                <h3 className="mt-4 text-lg font-semibold">Rich Resources</h3>
                <p className="mt-2 text-gray-600">
                  Access comprehensive study materials and guides
                </p>
              </div>
              <div className="rounded-xl bg-white/50 p-6 backdrop-blur-sm sm:col-span-2 lg:col-span-1">
                <div className="text-indigo-600">
                  <Medal className="mx-auto size-8" />
                </div>
                <h3 className="mt-4 text-lg font-semibold">Track Progress</h3>
                <p className="mt-2 text-gray-600">
                  Monitor your learning journey with detailed analytics
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default HomePage;
