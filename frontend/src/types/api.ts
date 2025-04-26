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
