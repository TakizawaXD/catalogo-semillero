import {
  Injectable,
  computed,
  signal,
} from '@angular/core';

import { Producto } from '../models/producto';

@Injectable({
  providedIn: 'root',
})
export class CarritoService {
  private readonly clave = 'carrito_wposs';

  private readonly items = signal<Producto[]>(
    this.cargar(),
  );

  readonly productos = this.items.asReadonly();

  readonly cantidad = computed(
    () => this.items().length,
  );

  readonly total = computed(() =>
    this.items().reduce(
      (suma, producto) => suma + producto.price,
      0,
    ),
  );

  agregar(producto: Producto): void {
    this.items.update((lista) => [
      ...lista,
      producto,
    ]);

    this.persistir();
  }

  quitar(id: number): void {
    this.items.update((lista) =>
      lista.filter((producto) => producto.id !== id),
    );

    this.persistir();
  }

  vaciar(): void {
    this.items.set([]);
    this.persistir();
  }

  private cargar(): Producto[] {
    try {
      if (typeof localStorage === 'undefined') {
        return [];
      }

      const guardado = localStorage.getItem(
        this.clave,
      );

      return guardado
        ? (JSON.parse(guardado) as Producto[])
        : [];
    } catch {
      return [];
    }
  }

  private persistir(): void {
    try {
      if (typeof localStorage === 'undefined') {
        return;
      }

      localStorage.setItem(
        this.clave,
        JSON.stringify(this.items()),
      );
    } catch {
      // No hace falta
    }
  }
}