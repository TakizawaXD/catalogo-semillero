import { Component } from '@angular/core';
import { Routes } from '@angular/router';

@Component({
  selector: 'app-admin-page',
  standalone: true,
  template: '<main><h1>Administración</h1><p>Panel de administración.</p></main>',
})
export class AdminPageComponent {}

export const ADMIN_ROUTES: Routes = [
  {
    path: '',
    component: AdminPageComponent,
    title: 'Administración',
  },
];
