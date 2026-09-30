import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, map, of } from 'rxjs';
import { environment } from '../../environments/environment';

export interface Usuario {
  email: string;
  nombre: string;
  rol: 'admin' | 'usuario';
}

interface JwtPayload {
  sub?: number | string;
  email?: string;
  role?: string;
  rol?: string;
  name?: string;
  exp?: number;
}

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly http = inject(HttpClient);
  private readonly TOKEN_KEY = 'auth_token';

  readonly usuario = signal<Usuario | null>(this.obtenerUsuarioDesdeToken());

  obtenerToken(): string | null {
    try {
      if (typeof window === 'undefined' || !window.localStorage) {
        return null;
      }
      return localStorage.getItem(this.TOKEN_KEY);
    } catch {
      return null;
    }
  }

  estaAutenticado(): boolean {
    const token = this.obtenerToken();
    if (!token) {
      return false;
    }

    const payload = this.decodificarToken(token);
    if (!payload || typeof payload.exp !== 'number') {
      return false;
    }

    const ahoraSegundos = Math.floor(Date.now() / 1000);
    return payload.exp > ahoraSegundos;
  }

  tieneRol(rol: 'admin' | 'usuario'): boolean {
    if (!this.estaAutenticado()) {
      return false;
    }
    const usuarioActual = this.usuario();
    return usuarioActual?.rol === rol;
  }

  iniciarSesion(correo: string, contrasena: string): Observable<boolean> {
    const emailNormalizado = correo.trim().toLowerCase();

    // 1. Usuarios mock para sustentación rápida
    if (emailNormalizado === 'admin@wposs.com' && contrasena === 'admin123') {
      const mockToken = this.generarTokenMock(emailNormalizado, 'Administrador Wposs', 'admin');
      this.guardarToken(mockToken);
      this.usuario.set(this.obtenerUsuarioDesdeToken());
      return of(true);
    }

    if (emailNormalizado === 'usuario@wposs.com' && contrasena === 'usuario123') {
      const mockToken = this.generarTokenMock(emailNormalizado, 'Usuario Demo', 'usuario');
      this.guardarToken(mockToken);
      this.usuario.set(this.obtenerUsuarioDesdeToken());
      return of(true);
    }

    // 2. Autenticación con endpoint de API (Platzi Fake Store API)
    return this.http
      .post<{ access_token: string }>(`${environment.apiUrl}/auth/login`, {
        email: emailNormalizado,
        password: contrasena,
      })
      .pipe(
        map((resp) => {
          if (resp?.access_token) {
            this.guardarToken(resp.access_token);
            this.usuario.set(this.obtenerUsuarioDesdeToken());
            return true;
          }
          return false;
        }),
        catchError(() => of(false))
      );
  }

  cerrarSesion(): void {
    try {
      if (typeof window !== 'undefined' && window.localStorage) {
        localStorage.removeItem(this.TOKEN_KEY);
      }
    } catch {
      // Ignorar error de acceso a storage
    }
    this.usuario.set(null);
  }

  private guardarToken(token: string): void {
    try {
      if (typeof window !== 'undefined' && window.localStorage) {
        localStorage.setItem(this.TOKEN_KEY, token);
      }
    } catch {
      // Manejo seguro ante modo incógnito o storage bloqueado
    }
  }

  private decodificarToken(token: string): JwtPayload | null {
    try {
      const partes = token.split('.');
      if (partes.length !== 3) {
        return null;
      }
      const base64 = partes[1].replace(/-/g, '+').replace(/_/g, '/');
      const payloadDecodificado = decodeURIComponent(
        atob(base64)
          .split('')
          .map((c) => '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2))
          .join('')
      );
      return JSON.parse(payloadDecodificado) as JwtPayload;
    } catch {
      return null;
    }
  }

  private obtenerUsuarioDesdeToken(): Usuario | null {
    const token = this.obtenerToken();
    if (!token) {
      return null;
    }

    const payload = this.decodificarToken(token);
    if (!payload || !payload.exp) {
      return null;
    }

    const ahoraSegundos = Math.floor(Date.now() / 1000);
    if (payload.exp <= ahoraSegundos) {
      return null;
    }

    const rolPayload = payload.role ?? payload.rol ?? '';
    const rol: 'admin' | 'usuario' = rolPayload.toLowerCase() === 'admin' ? 'admin' : 'usuario';

    return {
      email: payload.email ?? 'usuario@semillero.com',
      nombre: payload.name ?? (rol === 'admin' ? 'Administrador' : 'Usuario'),
      rol,
    };
  }

  private generarTokenMock(email: string, nombre: string, rol: 'admin' | 'usuario'): string {
    const header = btoa(JSON.stringify({ alg: 'HS256', typ: 'JWT' }));
    const payload = btoa(
      JSON.stringify({
        sub: 1,
        email,
        name: nombre,
        role: rol,
        rol,
        exp: Math.floor(Date.now() / 1000) + 3600, // Expira en 1 hora
      })
    );
    const firma = btoa('wposs-secret-signature');
    return `${header}.${payload}.${firma}`;
  }
}