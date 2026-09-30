import { CommonModule, CurrencyPipe } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { Producto } from '../../models/producto';

@Component({
  selector: 'app-targeta-producto',
  standalone: true,
  imports: [
    CommonModule,
    CurrencyPipe,
    RouterLink,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
  ],
  templateUrl: './targeta-producto.html',
  styleUrl: './targeta-producto.css',
})
export class TargetaProductoComponent {
  @Input({ required: true }) producto!: Producto;
  @Output() agregarAlCarrito = new EventEmitter<Producto>();

  agregarProducto(): void {
    this.agregarAlCarrito.emit(this.producto);
  }
}