import { Routes } from '@angular/router';
import { CatalogoPageComponent } from './pages/catalogo-page/catalogo-page';
import { LoginPageComponent } from './pages/login-page/login-page';
import { authGuard } from './guards/auth.guard';
import { adminGuard } from './guards/admin.guard';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'productos',
    pathMatch: 'full',
  },
  {
    path: 'productos',
    component: CatalogoPageComponent,
    title: 'Productos',
  },
  {
    path: 'productos/:id',
    loadComponent: () =>
      import('./pages/detalle-producto/detalle-producto').then(
        (modulo) => modulo.DetalleProductoComponent,
      ),
    title: 'Detalle del producto',
  },
  {
    path: 'carrito',
    loadComponent: () =>
      import('./pages/carrito-page/carrito-page').then(
        (modulo) => modulo.CarritoPageComponent,
      ),
    canActivate: [authGuard],
    title: 'Carrito',
  },
  {
    path: 'login',
    component: LoginPageComponent,
    title: 'Iniciar sesión',
  },
  {
    path: 'admin',
    canMatch: [adminGuard],
    loadChildren: () =>
      import('./pages/admin-page/admin.routes').then(
        (modulo) => modulo.ADMIN_ROUTES,
      ),
    title: 'Administración',
  },
  {
    path: '**',
    loadComponent: () =>
      import('./pages/no-encontrado/no-encontrado').then(
        (modulo) => modulo.NoEncontradoComponent,
      ),
    title: 'Página no encontrada',
  },
];