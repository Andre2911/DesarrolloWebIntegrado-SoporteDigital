import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SeguimientoService {

  private baseUrl = `${environment.apiUrl}/seguimiento`;

  constructor(private http: HttpClient) {}

  getAllBySolicitud(solicitudId: number) {
    return this.http.get<any[]>(`${this.baseUrl}/solicitud/${solicitudId}`);
  }

  getById(id: number) {
    return this.http.get<any>(`${this.baseUrl}/${id}`);
  }

  registrarActividad(data: any) {
    return this.http.post(this.baseUrl, data);
  }

  actualizarActividad(id: number, data: any) {
    return this.http.put(`${this.baseUrl}/${id}`, data);
  }

  eliminarActividad(id: number) {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

  actividadesPorColaborador(colaboradorId: number) {
    return this.http.get<any[]>(`${this.baseUrl}/colaborador/${colaboradorId}`);
  }
}
