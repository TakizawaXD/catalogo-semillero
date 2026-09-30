import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-no-encontrado',
  standalone: true,
  imports: [RouterLink, MatCardModule, MatButtonModule, MatIconModule],
  templateUrl: './no-encontrado.html',
  styles: [`
    .contenedor-404 {
      display: flex;
      justify-content: center;
      align-items: center;
      min-height: calc(100vh - 120px);
      padding: 16px;
    }
    .tarjeta-404 {
      text-align: center;
      max-width: 480px;
      padding: 32px 16px;
    }
    .icono-404 {
      font-size: 64px;
      width: 64px;
      height: 64px;
      margin-bottom: 16px;
      opacity: 0.6;
    }
    .acciones-centradas {
      display: flex;
      justify-content: center;
    }
  `]
})
export class NoEncontradoComponent {}
