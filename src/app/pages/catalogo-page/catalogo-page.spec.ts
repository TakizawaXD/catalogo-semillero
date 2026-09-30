import { TestBed } from '@angular/core/testing';
import { CatalogoPageComponent } from './catalogo-page';
import { ProductoService } from '../../services/producto.service';
import { CategoriaService } from '../../services/categoria.service';

describe('CatalogoPage', () => {
  let component: CatalogoPageComponent;
  let fixture: any;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CatalogoPageComponent],
      providers: [
        ProductoService,
        CategoriaService,
      ],
    });

    fixture = TestBed.createComponent(CatalogoPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});