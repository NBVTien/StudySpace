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
