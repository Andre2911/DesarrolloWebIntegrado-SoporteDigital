import {Component, inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, Validators} from '@angular/forms';
import {DropdownModule} from 'primeng/dropdown';
import {Dialog} from 'primeng/dialog';
import {DialogService, DynamicDialogRef} from 'primeng/dynamicdialog';
import {AsignacionesService} from '../../../../service/asignaciones.service';
import {TableModule} from 'primeng/table';
import {Button, ButtonDirective} from 'primeng/button';
import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {MultiSelect} from 'primeng/multiselect';

@Component({
  selector: 'app-asignaciones',
  imports: [
    FormsModule,
    DropdownModule,
    Dialog,
    TableModule,
    ButtonDirective,
    DatePipe,
    MultiSelect,
    Button
  ],
  templateUrl: './asignaciones.component.html',
  styleUrl: './asignaciones.component.css'
})
export class AsignacionesComponent implements OnInit{
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

  usuarios = [
    { id: 1, nombre: 'Ana Ramos', area: '123 Soporte Digital' },
    { id: 2, nombre: 'Luis Torres', area: '123 Soporte Digital' },
    { id: 3, nombre: 'Gabriel Díaz', area: '123 Soporte Digital' },
    { id: 4, nombre: 'Pedro Mendoza', area: 'Ventas' } // inválido
  ];

  usuariosAsignados: any[] = [];
  coordinador: any = null;


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
  }

  abrirAsignacion(solicitud: any): void {
    this.solicitudSeleccionada = solicitud;
    this.modalVisible = true;
  }

  puedeAsignar(): boolean {
    return this.usuariosAsignados.length > 0 &&
      this.coordinador &&
      this.usuariosAsignados.includes(this.coordinador)
  }

  asignar() {
    if (this.puedeAsignar()) {
      // Actualiza la solicitud directamente
      this.solicitudSeleccionada.responsable = this.coordinador.nombre;
      this.solicitudSeleccionada.estado = 'En atención';

      // Cierra el diálogo
      this.modalVisible = false;

      // Limpia selección (opcional)
      this.usuariosAsignados = [];
      this.coordinador = null;
    }
  }

  cerrar() {
    this.modalVisible = false;
  }
  asignarResponsable(): void {
    if (!this.solicitudSeleccionada || !this.responsableSeleccionado) return;
  }
}
