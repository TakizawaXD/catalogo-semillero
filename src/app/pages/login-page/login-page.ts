import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login-page.html',
})
export class LoginPageComponent {
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);
  private readonly route = inject(ActivatedRoute);

  nombre = '';
  clave = '';
  error = '';

  iniciarSesion(): void {
    const correcto = this.authService.iniciarSesion(
      this.nombre,
      this.clave,
    );

    if (!correcto) {
      this.error = 'Usuario o clave incorrectos.';
      return;
    }

    const volverA =
      this.route.snapshot.queryParamMap.get('volverA') ??
      '/productos';

    this.router.navigateByUrl(volverA);
  }
}