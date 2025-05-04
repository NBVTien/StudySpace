export type User = {
  id: string;
  username: string;
  email: string;
  fullName: string;
};

export type AuthResponse = {
  user: User;
  token: string;
};

export type Quiz = {
  id: string;
  title: string;
  description: string;
  questionCount: number;
  difficulty: 'Easy' | 'Medium' | 'Hard';
  estimatedTimeInMinutes: number;
  tags: string[];
};

export type Meta = {
  total: number;
  totalPages: number;
  page: number;
  pageSize: number;
};

export type Quizzes = Meta & {
  data: Quiz[];
};
