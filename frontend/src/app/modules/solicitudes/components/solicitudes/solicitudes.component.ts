import {Component, inject} from '@angular/core';
import {ButtonModule} from 'primeng/button';
import {TableModule} from 'primeng/table';
import {SolicitudesService} from '../../../../service/solicitudes.service';
import {Router} from '@angular/router';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-solicitudes',
  imports: [ButtonModule, TableModule, DatePipe],
  templateUrl: './solicitudes.component.html',
  styleUrl: './solicitudes.component.css'
})
export class SolicitudesComponent {
  solicitudes: any[] = [];

  private solicitudesService = inject(SolicitudesService);
  private router = inject(Router);

  constructor() {
    this.obtenerSolicitudes();
  }

  obtenerSolicitudes() {
    this.solicitudesService.getAll().subscribe({
      next: (data: any) => this.solicitudes = data.data,
      error: (err) => console.error('Error al obtener solicitudes:', err)
    });
  }


  agregar() {
    this.router.navigate(['/dashboard/solicitudes/agregar']);
  }


  editar(solicitud: any) {
    this.router.navigate(['/dashboard/solicitudes/editar', solicitud.id]);
  }
}
