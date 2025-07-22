import {Component, inject, OnInit, ViewChild} from '@angular/core';
import {ButtonModule} from 'primeng/button';
import {Table, TableModule} from 'primeng/table';
import {SolicitudesService} from '../../../../service/solicitudes.service';
import {Router} from '@angular/router';
import {DatePipe} from '@angular/common';
import {Toolbar} from 'primeng/toolbar';


interface Column {
  field: string;
  header: string;
  customExportHeader?: string;
}

@Component({
  selector: 'app-solicitudes',
  imports: [ButtonModule, TableModule, DatePipe, Toolbar],
  templateUrl: './solicitudes.component.html',
  styleUrl: './solicitudes.component.css'
})
export class SolicitudesComponent implements OnInit{
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

  exportCSV() {
    this.dt.exportCSV();
  }

}
