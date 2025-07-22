import { Routes } from '@angular/router';
import {NotFoundComponent} from './shared/pages/not-found/not-found.component';
import {AppLayout} from './layouts/dashboard-layout/app.layout';

export const routes: Routes = [
  { path: '', redirectTo: 'auth/login', pathMatch: 'full' },
  {
    path: 'auth',
    loadChildren: () => import('./modules/auth/router/auth.routes').then(m => m.AUTH_ROUTES)
  },

  {
    path: 'dashboard',
    component: AppLayout,
    children: [
      {
        path: 'solicitudes',
        loadChildren: () =>
          import('./modules/solicitudes/router/solicitudes.router').then((m) => m.SOLICITUDES_ROUTES)
      },
      {
        path: 'asignaciones',
        loadChildren: () =>
          import('./modules/asignaciones/router/asignaciones.router').then((m) => m.ASIGNACIONES_ROUTES)
      },
      {
        path: 'seguimiento',
        loadChildren: () =>
          import('./modules/seguimiento/router/seguimiento.router').then((m) => m.SEGUIMIENTO_ROUTES)
      },
      {
        path: 'estadisticas',
        loadChildren: () =>
          import('./modules/estadisticas/router/estadisticas.router').then((m) => m.ESTADISTICAS_ROUTES)
      },
      {
        path: 'perfil',
        loadChildren: () =>
          import('./modules/perfil/router/perfil.router').then((m) => m.PERFIL_ROUTES)
      },
    ],
  },
  {
    path: '**',
    component: NotFoundComponent
  },
];
