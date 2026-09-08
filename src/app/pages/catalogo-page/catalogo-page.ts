import { CommonModule } from '@angular/common';
import { Component, computed, signal } from '@angular/core';
import { TargetaProductoComponent } from '../../components/targeta-producto/targeta-producto';
import { TablaProductos } from '../../components/tabla.productos/tabla.productos';
import {
  Producto,
  productos as productosIniciales,
} from '../../models/producto';

type Rol = 'admin' | 'customer';

@Component({
  selector: 'app-catalogo-page',
  standalone: true,
  imports: [
    CommonModule,
    TargetaProductoComponent,
    TablaProductos,
  ],
  templateUrl: './catalogo-page.html',
  styleUrl: './catalogo-page.css',
})
export class CatalogoPageComponent {
  productos = signal<Producto[]>(productosIniciales);

  rol = signal<Rol>('customer');

  categoriaSeleccionada = signal('Todas');

  carrito = signal<Producto[]>([]);

  categorias = computed(() => {
    const nombres = this.productos().map(
      (producto) => producto.category.name,
    );

    return ['Todas', ...new Set(nombres)];
  });

  productosFiltrados = computed(() => {
    const categoria = this.categoriaSeleccionada();

    if (categoria === 'Todas') {
      return this.productos();
    }

    return this.productos().filter(
      (producto) => producto.category.name === categoria,
    );
  });

  cantidadCarrito = computed(() => {
    return this.carrito().length;
  });

  totalCarrito = computed(() => {
    return this.carrito().reduce(
      (total, producto) => total + producto.price,
      0,
    );
  });

  cambiarRol(): void {
    this.rol.update((rolActual) => {
      return rolActual === 'admin' ? 'customer' : 'admin';
    });
  }

  seleccionarCategoria(categoria: string): void {
    this.categoriaSeleccionada.set(categoria);
  }

  agregarAlCarrito(producto: Producto): void {
    this.carrito.update((productosEnCarrito) => {
      return [...productosEnCarrito, producto];
    });
  }

  editarProducto(producto: Producto): void {
    console.log('Editar producto:', producto);
  }

  eliminarProducto(producto: Producto): void {
    this.productos.update((productosActuales) => {
      return productosActuales.filter(
        (productoActual) => productoActual.id !== producto.id,
      );
    });
  }

  trackById(_indice: number, producto: Producto): number {
    return producto.id;
  }

  trackByCategoria(_indice: number, categoria: string): string {
    return categoria;
  }
}