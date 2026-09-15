import { Component } from '@angular/core';
import { CatalogoPageComponent } from './pages/catalogo-page/catalogo-page';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CatalogoPageComponent],
  templateUrl: './app.html',
  styleUrls: ['./app.css'],
})
export class App {
  title = 'catalogo-semillero';
}