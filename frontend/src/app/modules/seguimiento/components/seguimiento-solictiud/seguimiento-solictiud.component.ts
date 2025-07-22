import { Component } from '@angular/core';
import {Timeline} from 'primeng/timeline';
import {DividerModule} from 'primeng/divider';
import {DatePipe, NgIf} from '@angular/common';
import {ButtonModule} from 'primeng/button';
import {FormsModule} from '@angular/forms';
import {Textarea} from 'primeng/textarea';
import {InputText} from 'primeng/inputtext';

@Component({
  selector: 'app-seguimiento-solictiud',
  imports: [
    Timeline,
    DividerModule,
    ButtonModule,
    DatePipe,
    NgIf,
    FormsModule,
    Textarea,
    InputText
  ],
  templateUrl: './seguimiento-solictiud.component.html',
  styleUrl: './seguimiento-solictiud.component.css'
})
export class SeguimientoSolictiudComponent {
  solicitud = {
    id: 1,
    tipo: 'Error de software',
    estado: 'En atención',
    cliente: 'Tech Solutions',
    coordinador: 'Ana Ramos'
  };

  usuarioActual = 'Luis Torres'; // simulado

  actividades = [
    {
      descripcion: 'Revisión del código fuente',
      horas: 2,
      fecha: new Date('2025-07-19T10:30:00'),
      colaborador: 'Luis Torres'
    },
    {
      descripcion: 'Pruebas de validación',
      horas: 1,
      fecha: new Date('2025-07-20T09:00:00'),
      colaborador: 'Ana Ramos'
    }
  ];

  nuevaActividad = {
    descripcion: '',
    horas: 1
  };

  get esCoordinador(): boolean {
    const rol = localStorage.getItem('usuario') ? JSON.parse(localStorage.getItem('usuario') || '{}').rol : '';
    console.log("Rol del usuario actual:", rol);
    return rol === 'COORDINADOR' || rol === 'ADMIN';
  }

  registrarActividad() {
    const { descripcion, horas } = this.nuevaActividad;

    if (descripcion?.trim() && horas > 0) {
      this.actividades.unshift({
        descripcion,
        horas,
        fecha: new Date(),
        colaborador: this.usuarioActual
      });

      this.nuevaActividad = {
        descripcion: '',
        horas: 1
      };
    }
  }


  finalizarSolicitud() {
    this.solicitud.estado = 'Finalizada';
  }

  ngOnInit() {}
}
