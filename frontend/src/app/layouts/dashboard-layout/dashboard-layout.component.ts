import {Component, inject} from '@angular/core';
import {Router, RouterLink, RouterLinkActive, RouterOutlet} from '@angular/router';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-dashboard-layout',
  imports: [
    RouterLink,
    RouterLinkActive,
    RouterOutlet,
    NgIf
  ],
  templateUrl: './dashboard-layout.component.html',
  styleUrl: './dashboard-layout.component.css'
})
export class DashboardLayoutComponent {
  router = inject(Router);
  usuario = JSON.parse(localStorage.getItem('usuario') || '{}');
  rol = this.usuario?.rol || '';

  mostrarSolicitudes = false;
  mostrarAsignaciones = false;
  mostrarSeguimiento = false;
  mostrarEstadisticas = false;
  mostrarPerfil = true;

  constructor() {
    this.configurarMenuPorRol();
  }

  configurarMenuPorRol() {
    switch (this.rol) {
      case 'USUARIO':
        this.mostrarSolicitudes = true;
        break;
      case 'COORDINADOR':
        this.mostrarAsignaciones = true;
        break;
      case 'COLABORADOR':
        this.mostrarSolicitudes = true;
        this.mostrarSeguimiento = true;
        break;
      case 'ADMIN':
        this.mostrarSolicitudes = true;
        this.mostrarAsignaciones = true;
        this.mostrarSeguimiento = true;
        this.mostrarEstadisticas = true;
        break;
    }
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/auth/login']);
  }
}
