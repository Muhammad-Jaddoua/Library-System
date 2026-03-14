import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export interface AuthResponse {
  token: string;
  username: string;
  role: string;
}

@Injectable({ providedIn: 'root' })

export class AuthService {
  private apiUrl = '/api/auth';  // proxied to gateway:8080

  constructor(private http: HttpClient) {}

  register(username: string, password: string) {
    return this.http.post<AuthResponse>(`${this.apiUrl}/register`, { username, password });
  }

  login(username: string, password: string) {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, { username, password });
  }

  setToken(token: string) {
    localStorage.setItem('jwt_token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('jwt_token');
  }

  logout() {
    localStorage.removeItem('jwt_token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }
}
