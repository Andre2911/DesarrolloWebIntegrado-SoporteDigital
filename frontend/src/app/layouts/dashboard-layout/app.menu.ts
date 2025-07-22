import {Component, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { AppMenuitem } from './app.menuitem';

@Component({
    selector: 'app-menu',
    standalone: true,
  imports: [CommonModule, AppMenuitem, RouterModule, AppMenuitem],
    template: `<ul class="layout-menu">
        <ng-container *ngFor="let item of model; let i = index">
            <li app-menuitem *ngIf="!item.separator" [item]="item" [index]="i" [root]="true"></li>
            <li *ngIf="item.separator" class="menu-separator"></li>
        </ng-container>
    </ul> `
})
export class AppMenu implements OnInit{
    model: MenuItem[] = [];

  mostrarSolicitudes = false;
  mostrarAsignaciones = false;
  mostrarSeguimiento = false;
  mostrarEstadisticas = false;

  ngOnInit() {
    const rol = this.obtenerRol(); // 'ADMIN', 'COORDINADOR', etc.
    console.log('Rol del usuario:', rol);
    this.configurarMenuPorRol(rol);
    this.armarSidebar();
  }

  obtenerRol(): string {
    // ejemplo: token JWT decodificado, o servicio auth
    const userJson = localStorage.getItem('usuario');
    return userJson ? JSON.parse(userJson).rol || '' : '';
  }

  configurarMenuPorRol(rol: string) {
    this.mostrarSolicitudes = false;
    this.mostrarAsignaciones = false;
    this.mostrarSeguimiento = false;
    this.mostrarEstadisticas = false;

    switch (rol.toUpperCase()) {
      case 'USUARIO':
        this.mostrarSolicitudes = true;
        break;
      case 'COLABORADOR':
        this.mostrarSeguimiento = true;
        break;
      case 'COORDINADOR':
        this.mostrarSolicitudes = true;
        this.mostrarAsignaciones = true;
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

  armarSidebar() {
    this.model = [
      {
        label: 'Menú',
        items: [
          {
            label: 'Solicitudes',
            icon: 'pi pi-file',
            routerLink: ['/dashboard/solicitudes'],
            visible: this.mostrarSolicitudes
          },
          {
            label: 'Asignaciones',
            icon: 'pi pi-user-plus',
            routerLink: ['/dashboard/asignaciones'],
            visible: this.mostrarAsignaciones
          },
          {
            label: 'Seguimiento',
            icon: 'pi pi-calendar',
            routerLink: ['/dashboard/seguimiento'],
            visible: this.mostrarSeguimiento
          },
          {
            label: 'Estadísticas',
            icon: 'pi pi-chart-bar',
            routerLink: ['/dashboard/estadisticas'],
            visible: this.mostrarEstadisticas
          },
          {
            label: 'Perfil',
            icon: 'pi pi-user',
            routerLink: ['/dashboard/perfil'],
            visible: true
          },
          {
            label: 'Cerrar sesión',
            icon: 'pi pi-sign-out',
            command: () => this.logout(),
            visible: true
          }
        ]
      }
    ];
  }

  logout() {
    localStorage.clear();
    window.location.href = '/auth/login';
  }
}
