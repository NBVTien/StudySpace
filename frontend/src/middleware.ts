import { NextRequest, NextResponse } from 'next/server';

export async function middleware(req: NextRequest) {
  const path = req.nextUrl.pathname;
  const accessToken = req.cookies.get('authorization')?.value;

  if (accessToken && path.startsWith('/api')) {
    const headers = new Headers(req.headers);
    headers.set('Authorization', `Bearer ${accessToken}`);
    return NextResponse.next({
      request: { headers },
    });
  }
  return NextResponse.next();
}

export const config = {
  matcher: '/api/:path*',
};
