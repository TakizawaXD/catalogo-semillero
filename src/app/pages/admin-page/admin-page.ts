import { CommonModule, CurrencyPipe } from '@angular/common';
import { AfterViewInit, Component, OnInit, ViewChild, inject, signal } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

import { Producto } from '../../models/producto';
import { ProductoService } from '../../services/producto.service';
import { ConfirmDialogComponent } from '../../components/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-admin-page',
  standalone: true,
  imports: [
    CommonModule,
    CurrencyPipe,
    MatTableModule,
    MatPaginatorModule,
    MatButtonModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatDialogModule,
    MatSnackBarModule,
  ],
  templateUrl: './admin-page.html',
  styleUrl: './admin-page.css',
})
export class AdminPageComponent implements OnInit, AfterViewInit {
  private readonly productoService = inject(ProductoService);
  private readonly dialog = inject(MatDialog);
  private readonly snackBar = inject(MatSnackBar);

  readonly columnas = ['id', 'miniatura', 'titulo', 'categoria', 'precio', 'acciones'];
  readonly dataSource = new MatTableDataSource<Producto>([]);
  readonly cargando = signal(true);

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  ngOnInit(): void {
    this.cargarProductos();
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  cargarProductos(): void {
    this.cargando.set(true);
    this.productoService.obtenerTodos().subscribe({
      next: (datos) => {
        this.dataSource.data = datos;
        this.cargando.set(false);
      },
      error: () => {
        this.cargando.set(false);
        this.snackBar.open('Error al cargar la lista de productos', 'Cerrar', {
          duration: 3000,
        });
      },
    });
  }

  confirmarEliminacion(producto: Producto): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '380px',
      data: {
        titulo: '¿Eliminar producto?',
        mensaje: `¿Estás seguro de que deseas eliminar "${producto.title}"? Esta acción no se puede deshacer.`,
      },
    });

    dialogRef.afterClosed().subscribe((confirmado) => {
      if (!confirmado) {
        return;
      }

      this.productoService.eliminar(producto.id).subscribe({
        next: () => {
          this.dataSource.data = this.dataSource.data.filter((p) => p.id !== producto.id);
          this.snackBar.open('Producto eliminado con éxito', 'OK', {
            duration: 3000,
          });
        },
        error: () => {
          this.snackBar.open('No se pudo eliminar el producto', 'Cerrar', {
            duration: 3000,
          });
        },
      });
    });
  }
}
