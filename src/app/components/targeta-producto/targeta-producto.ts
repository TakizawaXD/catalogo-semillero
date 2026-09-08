import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Producto } from '../../models/producto';

@Component({
  selector: 'app-targeta-producto',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './targeta-producto.html',
  styleUrls: ['./targeta-producto.css'],
})
export class TargetaProductoComponent {
  @Input({ required: true }) producto!: Producto;

  @Output() agregarAlCarrito = new EventEmitter<Producto>();

  agregarProducto(): void {
    this.agregarAlCarrito.emit(this.producto);
  }
}