import { Injectable, signal } from '@angular/core';

export interface Usuario {
  nombre: string;
  rol: 'admin' | 'usuario';
}

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly clave = 'usuario_wposs';

  usuario = signal<Usuario | null>(this.cargarUsuario());

  iniciarSesion(nombre: string, clave: string): boolean {
    if (clave !== 'wposs123') {
      return false;
    }

    const usuario: Usuario = {
      nombre,
      rol: nombre.toLowerCase() === 'admin' ? 'admin' : 'usuario',
    };

    localStorage.setItem(this.clave, JSON.stringify(usuario));
    this.usuario.set(usuario);

    return true;
  }

  cerrarSesion(): void {
    localStorage.removeItem(this.clave);
    this.usuario.set(null);
  }

  estaAutenticado(): boolean {
    return this.usuario() !== null;
  }

  tieneRol(rol: 'admin' | 'usuario'): boolean {
    return this.usuario()?.rol === rol;
  }

  private cargarUsuario(): Usuario | null {
    const usuarioGuardado = localStorage.getItem(this.clave);

    if (!usuarioGuardado) {
      return null;
    }

    try {
      return JSON.parse(usuarioGuardado) as Usuario;
    } catch {
      return null;
    }
  }
}