import { CommonModule } from '@angular/common';
import { Component, computed, inject, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Producto } from '../../models/producto';
import { AuthService } from '../../services/auth.service';
import { ProductoService } from '../../services/producto.service';

@Component({
  selector: 'app-detalle-producto',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './detalle-producto.html',
})
export class DetalleProductoComponent {
  private readonly authService = inject(AuthService);
  private readonly route = inject(ActivatedRoute);
  private readonly productoService = inject(ProductoService);

  readonly producto = signal<Producto | null>(null);
  readonly productoId = computed(() => this.route.snapshot.paramMap.get('id'));

  constructor() {
    const id = Number(this.productoId());

    if (!Number.isNaN(id)) {
      this.productoService.obtenerPorId(id).subscribe({
        next: (producto) => this.producto.set(producto),
        error: () => this.producto.set(null),
      });
    }
  }

  puedeSalir(): boolean {
    return this.authService.tieneRol('admin');
  }
}