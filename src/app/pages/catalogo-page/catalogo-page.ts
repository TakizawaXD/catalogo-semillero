import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import {
  BehaviorSubject,
  Observable,
  finalize,
  shareReplay,
  switchMap,
} from 'rxjs';

import { TargetaProductoComponent } from '../../components/targeta-producto/targeta-producto';
import { TablaProductos } from '../../components/tabla.productos/tabla.productos';
import { Categoria } from '../../models/categoria';
import { Producto } from '../../models/producto';
import { CarritoService } from '../../services/carrito.service';
import { CategoriaService } from '../../services/categoria.service';
import { ProductoService } from '../../services/producto.service';

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
  styleUrls: ['./catalogo-page.css'],
})
export class CatalogoPageComponent{
  private readonly productoService = inject(ProductoService);
  private readonly categoriaService = inject(CategoriaService);

  readonly carritoService = inject(CarritoService);

  readonly rol = signal<Rol>('customer');
  readonly cargando = signal(false);

  private readonly categoriaSeleccionadaSubject =
    new BehaviorSubject<number | undefined>(undefined);

  readonly categorias$: Observable<Categoria[]> =
    this.categoriaService.obtenerTodos();

  readonly productos$: Observable<Producto[]> =
    this.categoriaSeleccionadaSubject.pipe(
      switchMap((categoryId) => {
        this.cargando.set(true);

        return this.productoService
          .obtenerTodos(categoryId)
          .pipe(
            finalize(() => this.cargando.set(false)),
          );
      }),
      shareReplay({
        bufferSize: 1,
        refCount: true,
      }),
    );

  readonly errorCatalogo = this.productoService.error;

  cambiarRol(): void {
    this.rol.update((rolActual) =>
      rolActual === 'admin' ? 'customer' : 'admin',
    );
  }

  cambiarCategoria(event: Event): void {
    const select = event.target as HTMLSelectElement;
    const valor = select.value;

    const categoryId =
      valor === 'todas' ? undefined : Number(valor);

    this.categoriaSeleccionadaSubject.next(categoryId);
  }

  agregarAlCarrito(producto: Producto): void {
    this.carritoService.agregar(producto);
  }

  editarProducto(producto: Producto): void {
    console.log('Editar producto:', producto);
  }

  eliminarProducto(producto: Producto): void {
    this.carritoService.quitar(producto.id);
  }

  trackById(_indice: number, producto: Producto): number {
    return producto.id;
  }

  trackByCategoria(
    _indice: number,
    categoria: Categoria,
  ): number {
    return categoria.id;
  }
}