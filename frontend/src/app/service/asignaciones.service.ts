import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AsignacionesService {

  private baseUrl = `${environment.apiUrl}/api/asignaciones`;

  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<any[]>(this.baseUrl);
  }

  getById(id: number) {
    return this.http.get<any>(`${this.baseUrl}/${id}`);
  }

  create(data: any) {
    return this.http.post(this.baseUrl, data);
  }

  update(id: number, data: any) {
    return this.http.put(`${this.baseUrl}/${id}`, data);
  }

  delete(id: number) {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

  asignarColaboradores(solicitudId: number, data: any) {
    return this.http.post(`${this.baseUrl}/${solicitudId}/colaboradores`, data);
  }

  obtenerColaboradoresAsignados(solicitudId: number) {
    return this.http.get<any[]>(`${this.baseUrl}/${solicitudId}/colaboradores`);
  }
}
