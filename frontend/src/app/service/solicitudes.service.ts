import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Injectable({ providedIn: 'root' })
export class SolicitudesService {

  private baseUrl = `${environment.apiUrl}/api/solicitudes`;

  constructor(private http: HttpClient, private router: Router) {
  }


  getAll() {
    return this.http.get<any[]>(this.baseUrl + '/mis-solicitudes');
  }

  getById(id: number) {
    return this.http.get<any>(`${this.baseUrl}/${id}`);
  }

  create(data: any) {
    return this.http.post(this.baseUrl + '/crear', data);
  }

  update(id: number, data: any) {
    return this.http.put(`${this.baseUrl}/${id}`, data);
  }

  delete(id: number) {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }


  getTiposSolicitud() {
    return this.http.get<any[]>(`${this.baseUrl}/getTiposSolicitud`);
  }

  getAplicativos() {
    return this.http.get<any[]>(`${this.baseUrl}/getAplicativos`);
  }
}
