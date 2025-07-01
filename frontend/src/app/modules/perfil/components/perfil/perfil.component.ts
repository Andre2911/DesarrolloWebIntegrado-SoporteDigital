import { Component } from '@angular/core';
import {Card} from 'primeng/card';
import {PrimeTemplate} from 'primeng/api';
import {ButtonDirective} from 'primeng/button';
import {AuthService} from '../../../../service/auth.service';

@Component({
  selector: 'app-perfil',
  imports: [
    Card,
    PrimeTemplate,
    ButtonDirective
  ],
  templateUrl: './perfil.component.html',
  styleUrl: './perfil.component.css'
})
export class PerfilComponent {
  usuario: any;

  constructor(private authService: AuthService) {
    this.usuario = this.authService.obtenerUsuarioLocalStorage();
  }

  logout() {
    this.authService.logout();
  }
}
