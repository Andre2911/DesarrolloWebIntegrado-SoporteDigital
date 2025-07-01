import { Component } from '@angular/core';
import {Card} from 'primeng/card';
import {Button, ButtonModule} from 'primeng/button';
import {FormsModule} from '@angular/forms';
import {InputText} from 'primeng/inputtext';
import {PasswordDirective} from 'primeng/password';
import {AuthService} from '../../../../service/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [
    Card,
    ButtonModule,
    FormsModule,
    InputText,
    PasswordDirective
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
      next: (res) => {
        this.authService.guardarToken(res.token);
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        this.error = 'Credenciales incorrectas o error de servidor';
        console.error('Login error:', err);
      }
    });
  }
}
