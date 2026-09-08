import { Component } from '@angular/core';
import { CatalogoPageComponent } from './pages/catalogo-page/catalogo-page';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CatalogoPageComponent],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {}