import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EstadisticasService {

  private baseUrl = `${environment.apiUrl}/estadisticas`;

  constructor(private http: HttpClient) {}

  atencionesPorTipo() {
    return this.http.get<any[]>(`${this.baseUrl}/tipos`);
  }

  atencionesPorEstado() {
    return this.http.get<any[]>(`${this.baseUrl}/estados`);
  }

  atencionesPorFecha() {
    return this.http.get<any[]>(`${this.baseUrl}/fechas`);
  }

  colaboradoresConMasCasos() {
    return this.http.get<any[]>(`${this.baseUrl}/top-colaboradores`);
  }

  resumenGeneral() {
    return this.http.get<any>(`${this.baseUrl}/resumen`);
  }
}
