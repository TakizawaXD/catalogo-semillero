import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { BehaviorSubject, Observable, finalize, shareReplay, switchMap } from 'rxjs';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

import { TargetaProductoComponent } from '../../components/targeta-producto/targeta-producto';
import { Categoria } from '../../models/categoria';
import { Producto } from '../../models/producto';
import { CarritoService } from '../../services/carrito.service';
import { CategoriaService } from '../../services/categoria.service';
import { ProductoService } from '../../services/producto.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-catalogo-page',
  standalone: true,
  imports: [
    CommonModule,
    TargetaProductoComponent,
    MatProgressSpinnerModule,
    MatFormFieldModule,
    MatSelectModule,
    MatButtonModule,
    MatIconModule,
    MatSnackBarModule,
  ],
  templateUrl: './catalogo-page.html',
  styleUrls: ['./catalogo-page.css'],
})
export class CatalogoPageComponent {
  private readonly productoService = inject(ProductoService);
  private readonly categoriaService = inject(CategoriaService);
  private readonly snackBar = inject(MatSnackBar);

  readonly carritoService = inject(CarritoService);
  readonly authService = inject(AuthService);

  readonly cargando = signal(false);

  private readonly categoriaSeleccionadaSubject = new BehaviorSubject<number | undefined>(undefined);

  readonly categorias$: Observable<Categoria[]> = this.categoriaService.obtenerTodos();

  readonly productos$: Observable<Producto[]> = this.categoriaSeleccionadaSubject.pipe(
    switchMap((categoryId) => {
      this.cargando.set(true);
      return this.productoService.obtenerTodos(categoryId).pipe(
        finalize(() => this.cargando.set(false))
      );
    }),
    shareReplay({
      bufferSize: 1,
      refCount: true,
    })
  );

  readonly errorCatalogo = this.productoService.error;

  cambiarCategoria(categoriaId: number | string): void {
    const id = categoriaId === 'todas' ? undefined : Number(categoriaId);
    this.categoriaSeleccionadaSubject.next(id);
  }

  agregarAlCarrito(producto: Producto): void {
    this.carritoService.agregar(producto);
    this.snackBar.open(`"${producto.title}" añadido al carrito`, 'OK', {
      duration: 2500,
    });
  }

  trackById(_indice: number, producto: Producto): number {
    return producto.id;
  }

  trackByCategoria(_indice: number, categoria: Categoria): number {
    return categoria.id;
  }
}