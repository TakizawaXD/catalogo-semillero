import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Producto } from '../../models/producto';
import { TargetaProductoComponent } from './targeta-producto';

describe('TargetaProductoComponent', () => {
  let component: TargetaProductoComponent;
  let fixture: ComponentFixture<TargetaProductoComponent>;

  const producto: Producto = {
    id: 1,
    title: 'Producto de prueba',
    price: 10000,
    description: 'Descripción de prueba',
    images: ['imagen.jpg'],
    category: {
      id: 1,
      name: 'Categoría de prueba',
    },
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TargetaProductoComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(TargetaProductoComponent);
    component = fixture.componentInstance;

    component.producto = producto;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should show the product information', () => {
    const element = fixture.nativeElement as HTMLElement;

    expect(element.textContent).toContain('Producto de prueba');
    expect(element.textContent).toContain('Categoría de prueba');
    expect(element.querySelector('img')?.getAttribute('src')).toBe('imagen.jpg');
  });

  it('should emit the product when clicking the cart button', () => {
    const productosEmitidos: Producto[] = [];

    component.agregarAlCarrito.subscribe((productoEmitido) => {
      productosEmitidos.push(productoEmitido);
    });

    const button = fixture.nativeElement.querySelector('button') as HTMLButtonElement;
    button.click();

    expect(productosEmitidos).toEqual([producto]);
  });
});