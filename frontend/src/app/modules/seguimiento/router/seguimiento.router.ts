import { Routes } from '@angular/router';
import { SeguimientoComponent } from '../components/seguimiento/seguimiento.component';
import {SeguimientoSolictiudComponent} from '../components/seguimiento-solictiud/seguimiento-solictiud.component';

export const SEGUIMIENTO_ROUTES: Routes = [
  {
    path: '',
    component: SeguimientoComponent
  },
  {
    path: "solicitud",
    component: SeguimientoSolictiudComponent
  }
];
