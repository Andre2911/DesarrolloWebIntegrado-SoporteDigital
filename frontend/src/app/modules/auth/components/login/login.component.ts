import { Component } from '@angular/core';
import {Card} from 'primeng/card';
import {Button, ButtonModule} from 'primeng/button';
import {FormsModule} from '@angular/forms';
import {InputText} from 'primeng/inputtext';
import {PasswordDirective} from 'primeng/password';
import {AuthService} from '../../../../service/auth.service';
import {Router} from '@angular/router';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [
    Card,
    ButtonModule,
    FormsModule,
    InputText,
    PasswordDirective,
    NgIf
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  usuario: string = '';
  clave: string = '';
  error: string | null = null;

  constructor(private authService: AuthService, private router: Router) {}


  login() {
    this.error = null;
    this.authService.login(this.usuario, this.clave).subscribe({
      next: (res: any) => {
        // Guarda token y usuario completo
        localStorage.setItem('token', res.token);
        localStorage.setItem('usuario', JSON.stringify(res.usuario));

        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        this.error = 'Credenciales incorrectas o error de servidor';
        console.error('Login error:', err);
      }
    });
  }
}
