import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http'; 
import { Observable, catchError, of } from 'rxjs';
import { Categoria } from '../models/categoria';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})

export class CategoriaService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = `${environment.apiUrl}/categories`;

  private readonly _error = signal<string | null>(null);
  readonly error = this._error.asReadonly();


  obtenerTodos(): Observable<Categoria[]> {
    this._error.set(null);

    return this.http.get<Categoria[]>(this.apiUrl).pipe(
      catchError(() => {
        this._error.set('No se pudo cargar la lista de categorías.');
        return of([]);
      }),
    );
  }

  obtenerPorId(id: number): Observable<Categoria> {
    return this.http.get<Categoria>(`${this.apiUrl}/${id}`);
  }
}
