import { CommonModule, CurrencyPipe } from '@angular/common';
import { Component, computed, inject, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { Producto } from '../../models/producto';
import { AuthService } from '../../services/auth.service';
import { ProductoService } from '../../services/producto.service';
import { CarritoService } from '../../services/carrito.service';

@Component({
  selector: 'app-detalle-producto',
  standalone: true,
  imports: [
    CommonModule,
    CurrencyPipe,
    RouterLink,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatProgressSpinnerModule,
  ],
  templateUrl: './detalle-producto.html',
  styleUrl: './detalle-producto.css',
})
export class DetalleProductoComponent {
  private readonly authService = inject(AuthService);
  private readonly route = inject(ActivatedRoute);
  private readonly productoService = inject(ProductoService);
  readonly carritoService = inject(CarritoService);

  readonly producto = signal<Producto | null>(null);
  readonly cargando = signal(true);
  readonly productoId = computed(() => this.route.snapshot.paramMap.get('id'));

  constructor() {
    const id = Number(this.productoId());

    if (!Number.isNaN(id)) {
      this.productoService.obtenerPorId(id).subscribe({
        next: (producto) => {
          this.producto.set(producto);
          this.cargando.set(false);
        },
        error: () => {
          this.producto.set(null);
          this.cargando.set(false);
        },
      });
    } else {
      this.cargando.set(false);
    }
  }

  puedeSalir(): boolean {
    return this.authService.tieneRol('admin');
  }

  agregarAlCarrito(producto: Producto): void {
    this.carritoService.agregar(producto);
  }
}