import { Component } from '@angular/core';
import {ButtonModule} from 'primeng/button';
import {TableModule} from 'primeng/table';

@Component({
  selector: 'app-solicitudes',
  imports: [ButtonModule, TableModule],
  templateUrl: './solicitudes.component.html',
  styleUrl: './solicitudes.component.css'
})
export class SolicitudesComponent {
  solicitudes = [
    {
      id: 1,
      cliente: 'Empresa Alpha',
      tipo: 'Error de software',
      estado: 'Abierto',
      fecha: '2025-06-30'
    },
    {
      id: 2,
      cliente: 'Beta S.A.C.',
      tipo: 'Capacitación',
      estado: 'En proceso',
      fecha: '2025-06-28'
    }
  ];

  agregar() {
    // Redirigir a formulario de creación
    console.log('Agregar solicitud');
  }

  editar(solicitud: any) {
    console.log('Editar:', solicitud);
    // Redirigir a formulario de edición con solicitud.id
  }
}
