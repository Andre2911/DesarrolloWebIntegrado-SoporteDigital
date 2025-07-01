import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PerfilService {

  private baseUrl = `${environment.apiUrl}/perfil`;

  constructor(private http: HttpClient) {}

  getPerfilActual() {
    return this.http.get<any>(`${this.baseUrl}/me`);
  }

  actualizarDatos(data: any) {
    return this.http.put(`${this.baseUrl}/me`, data);
  }

  cambiarContrasenia(data: { actual: string; nueva: string }) {
    return this.http.put(`${this.baseUrl}/me/password`, data);
  }
}
