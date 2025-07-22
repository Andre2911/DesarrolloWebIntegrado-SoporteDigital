import {Component, inject, OnInit, ViewChild} from '@angular/core';
import {Button, ButtonDirective} from 'primeng/button';
import {DatePipe} from '@angular/common';
import {PrimeTemplate} from 'primeng/api';
import {Table, TableModule} from 'primeng/table';
import {Toolbar} from 'primeng/toolbar';
import {SolicitudesService} from '../../../../service/solicitudes.service';
import {Router} from '@angular/router';



interface Column {
  field: string;
  header: string;
  customExportHeader?: string;
}


@Component({
  selector: 'app-seguimiento',
  imports: [
    Button,
    ButtonDirective,
    DatePipe,
    PrimeTemplate,
    TableModule,
    Toolbar
  ],
  templateUrl: './seguimiento.component.html',
  styleUrl: './seguimiento.component.css'
})
export class SeguimientoComponent implements OnInit{
  solicitudes: any[] = [];

  @ViewChild('dt') dt!: Table;

  private solicitudesService = inject(SolicitudesService);
  private router = inject(Router);
  cols!: Column[];

  constructor() {
    this.obtenerSolicitudes();
  }

  ngOnInit() {
    this.cols = [
      { field: 'id', header: 'ID' },
      { field: 'tipo', header: 'Tipo' },
      { field: 'estado', header: 'Estado' },
      { field: 'fechaRegistro', header: 'Fecha' },
      { field: 'acciones', header: 'Acciones' }
    ];
  }



  obtenerSolicitudes() {
    this.solicitudes = [
      {
        id: 1,
        cliente: 'empresaX.com',
        tipo: 'Error de software',
        aplicativo: 'Sistema de ventas',
        estado: 'Pendiente',
        fechaRegistro: '2024-07-20T10:15:00',
        asignado: false
      },
      {
        id: 2,
        cliente: 'empresaY.com',
        tipo: 'Capacitación',
        aplicativo: 'Módulo contable',
        estado: 'Pendiente',
        fechaRegistro: '2024-07-19T14:30:00',
        asignado: true
      },
      {
        id: 3,
        cliente: 'empresaZ.com',
        tipo: 'Requerimiento',
        aplicativo: 'App móvil',
        estado: 'En atención',
        fechaRegistro: '2024-07-18T09:00:00',
        asignado: true
      }
    ];
  }



  agregar() {
    this.router.navigate(['/dashboard/solicitudes/agregar']);
  }


  editar(solicitud: any) {
    this.router.navigate(['/dashboard/seguimiento/solicitud']);
  }

  exportCSV() {
    this.dt.exportCSV();
  }
}
