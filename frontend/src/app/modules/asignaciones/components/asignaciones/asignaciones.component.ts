import {Component, inject} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, Validators} from '@angular/forms';
import {DropdownModule} from 'primeng/dropdown';
import {Dialog} from 'primeng/dialog';
import {DialogService, DynamicDialogRef} from 'primeng/dynamicdialog';
import {AsignacionesService} from '../../../../service/asignaciones.service';
import {TableModule} from 'primeng/table';
import {ButtonDirective} from 'primeng/button';
import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';

@Component({
  selector: 'app-asignaciones',
  imports: [
    FormsModule,
    DropdownModule,
    Dialog,
    TableModule,
    ButtonDirective,
    DatePipe
  ],
  templateUrl: './asignaciones.component.html',
  styleUrl: './asignaciones.component.css'
})
export class AsignacionesComponent {
  solicitudes = [
    {
      id: 1,
      tipo: 'Error de software',
      estado: 'Pendiente',
      fechaRegistro: new Date('2025-06-30T09:00:00'),
      responsable: null
    },
    {
      id: 2,
      tipo: 'Requerimiento',
      estado: 'Pendiente',
      fechaRegistro: new Date('2025-06-29T14:30:00'),
      responsable: 'Ana Torres'
    }
  ];

  responsables = [
    { id: 1, nombre: 'Ana Torres' },
    { id: 2, nombre: 'Carlos Mendoza' },
    { id: 3, nombre: 'Luis Vega' }
  ];
  responsableSeleccionado: any = null;
  solicitudSeleccionada: any = null;
  modalVisible = false;

  private asignacionesService = inject(AsignacionesService);
/*  private usuarioService = inject(UsuarioService);*/
  private router = inject(Router);

  ngOnInit(): void {
    this.cargarSolicitudes();
    this.cargarResponsables();
  }

  cargarSolicitudes(): void {
    this.asignacionesService.getAll().subscribe({
      next: (res:any) => {
        this.solicitudes = res.data.map((solicitud: any) => ({
          ...solicitud,
          tipo: solicitud.tipo.s_nombre_tipo,
          id: solicitud.n_id_solicitud,
          fechaRegistro: new Date(solicitud.f_fecha_registro),
        }));
      },
      error: (err) => console.error('Error al cargar solicitudes:', err)
    });
  }

  cargarResponsables(): void {
/*    this.usuarioService.getResponsables().subscribe({
      next: (res) => this.responsables = res.data,
      error: (err) => console.error('Error al cargar responsables:', err)
    });*/
  }

  abrirAsignacion(solicitud: any): void {
    this.solicitudSeleccionada = solicitud;
    this.modalVisible = true;
  }

  asignarResponsable(): void {
    if (!this.solicitudSeleccionada || !this.responsableSeleccionado) return;

/*    this.asignacionesService.asignarResponsable({
      solicitudId: this.solicitudSeleccionada.id,
      responsableId: this.responsableSeleccionado.id
    }).subscribe({
      next: () => {
        this.modalVisible = false;
        this.cargarSolicitudes();
      },
      error: (err) => console.error('Error al asignar responsable:', err)
    });*/
  }
}
