import { Routes } from '@angular/router';
import {SolicitudesComponent} from '../components/solicitudes/solicitudes.component';
import {AgregarSolicitudComponent} from '../components/agregar-solicitud/agregar-solicitud.component';

export const SOLICITUDES_ROUTES: Routes = [
  {
    path: '',
    component: SolicitudesComponent
  },
  { path: 'agregar', component: AgregarSolicitudComponent },
  {
    path: 'editar/:id',
    component: AgregarSolicitudComponent
  }
];
