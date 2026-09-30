import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatSnackBarModule,
  ],
  templateUrl: './login-page.html',
  styleUrl: './login-page.css',
})
export class LoginPageComponent {
  private readonly fb = inject(FormBuilder);
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);
  private readonly route = inject(ActivatedRoute);
  private readonly snackBar = inject(MatSnackBar);

  readonly cargando = signal(false);
  readonly mensajeError = signal<string | null>(null);
  readonly ocultarClave = signal(true);

  readonly formulario = this.fb.nonNullable.group({
    correo: ['', [Validators.required, Validators.email]],
    clave: ['', [Validators.required, Validators.minLength(4)]],
  });

  iniciarSesion(): void {
    if (this.formulario.invalid) {
      this.formulario.markAllAsTouched();
      return;
    }

    this.cargando.set(true);
    this.mensajeError.set(null);

    const { correo, clave } = this.formulario.getRawValue();

    this.authService.iniciarSesion(correo, clave).subscribe({
      next: (exito) => {
        this.cargando.set(false);

        if (!exito) {
          this.mensajeError.set('Correo o contraseña incorrectos');
          this.snackBar.open('Correo o contraseña incorrectos', 'Cerrar', {
            duration: 4000,
            panelClass: ['alerta-error'],
          });
          return;
        }

        this.snackBar.open('¡Sesión iniciada con éxito!', 'OK', {
          duration: 2500,
        });

        const volverA =
          this.route.snapshot.queryParamMap.get('volverA') ?? '/productos';

        this.router.navigateByUrl(volverA);
      },
      error: () => {
        this.cargando.set(false);
        this.mensajeError.set('Correo o contraseña incorrectos');
      },
    });
  }
}