import { CanDeactivateFn } from '@angular/router';

export interface PuedeSalir {
  puedeSalir(): boolean;
}

export const puedeSalirGuard: CanDeactivateFn<PuedeSalir> = (component) =>
  component.puedeSalir();
