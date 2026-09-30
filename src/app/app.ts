import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { EncabezadoComponent } from './components/encabezado/encabezado';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, EncabezadoComponent],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  title = 'catalogo-semillero';
}