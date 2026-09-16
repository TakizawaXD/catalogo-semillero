import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { CarritoService } from '../../services/carrito.service';

@Component({
  selector: 'app-carrito-page',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './carrito-page.html',
})
export class CarritoPageComponent {
  readonly carritoService = inject(CarritoService);

  quitar(productoId: number): void {
    this.carritoService.quitar(productoId);
  }

  vaciar(): void {
    this.carritoService.vaciar();
  }
}
