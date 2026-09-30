import { CommonModule, CurrencyPipe } from '@angular/common';
import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

import { CarritoService } from '../../services/carrito.service';

@Component({
  selector: 'app-carrito-page',
  standalone: true,
  imports: [
    CommonModule,
    CurrencyPipe,
    RouterLink,
    MatCardModule,
    MatListModule,
    MatButtonModule,
    MatIconModule,
    MatSnackBarModule,
  ],
  templateUrl: './carrito-page.html',
  styleUrl: './carrito-page.css',
})
export class CarritoPageComponent {
  readonly carritoService = inject(CarritoService);
  private readonly snackBar = inject(MatSnackBar);

  quitar(id: number): void {
    this.carritoService.quitar(id);
    this.snackBar.open('Producto removido del carrito', 'OK', { duration: 2000 });
  }

  vaciar(): void {
    this.carritoService.vaciar();
    this.snackBar.open('Carrito vaciado', 'OK', { duration: 2000 });
  }
}
