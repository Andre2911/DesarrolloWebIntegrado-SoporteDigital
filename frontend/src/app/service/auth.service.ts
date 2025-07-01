import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import {environment} from '../../environments/environment';


@Injectable({ providedIn: 'root' })
export class AuthService {

  private baseUrl = `${environment.apiUrl}/auth`;

  constructor(private http: HttpClient, private router: Router) {}

  login(correo: string, contrasena: string) {
    return this.http.post<{ token: string }>(`${this.baseUrl}/login`, {
      correo,
      contrasena
    });
  }

  guardarToken(token: string) {
    localStorage.setItem('token', token);
  }

  obtenerToken(): string | null {
    return localStorage.getItem('token');
  }

  estaAutenticado(): boolean {
    return !!this.obtenerToken();
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/auth/login']);
  }

  obtenerUsuarioLocalStorage(): any {
    const raw = localStorage.getItem('usuario');
    return raw ? JSON.parse(raw) : null;
  }
}
