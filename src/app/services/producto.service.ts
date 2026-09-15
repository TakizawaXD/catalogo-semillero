import { Injectable, inject, signal } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, catchError, of } from 'rxjs';
import { Producto } from '../models/producto';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ProductoService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = `${environment.apiUrl}/products`;

  private readonly _error = signal<string | null>(null);
  readonly error = this._error.asReadonly();

  obtenerTodos(categoryId?: number): Observable<Producto[]> {
    this._error.set(null);

    let params = new HttpParams();

    if (categoryId !== undefined) {
      params = params.set('categoryId', categoryId);
    }

    return this.http.get<Producto[]>(this.apiUrl, { params }).pipe(
      catchError(() => {
        this._error.set('No se pudo cargar el catálogo.');
        return of([]);
      }),
    );
  }

  obtenerPorId(id: number): Observable<Producto> {
    return this.http.get<Producto>(`${this.apiUrl}/${id}`);
  }

  crear(producto: Partial<Producto>): Observable<Producto> {
    return this.http.post<Producto>(this.apiUrl, producto);
  }

  actualizar(
    id: number,
    cambios: Partial<Producto>,
  ): Observable<Producto> {
    return this.http.put<Producto>(
      `${this.apiUrl}/${id}`,
      cambios,
    );
  }

  eliminar(id: number): Observable<boolean> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      () => of(true),
    );
  }
}