package com.wposs.catalogo.repositorio;

import com.wposs.catalogo.modelo.Categoria;
import com.wposs.catalogo.modelo.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@SuppressWarnings("null")
public class ProductoRepositorioTest {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    private Categoria categoria;
    private Producto producto;

    @BeforeEach
    void setUp() {
        categoria = new Categoria("Tecnología");
        categoriaRepositorio.save(categoria);

        producto = new Producto("Laptop X", "Laptop de alta gama", new BigDecimal("1500.00"), 10, categoria);
        productoRepositorio.save(producto);
    }

    @Test
    void debeGuardarProducto() {
        Producto nuevoProducto = new Producto("Teclado", "Teclado mecánico", new BigDecimal("50.00"), 20, categoria);
        Producto guardado = productoRepositorio.save(nuevoProducto);

        assertThat(guardado).isNotNull();
        assertThat(guardado.getId()).isGreaterThan(0);
    }

    @Test
    void debeObtenerProductoPorId() {
        Optional<Producto> encontrado = productoRepositorio.findById(producto.getId());

        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getNombre()).isEqualTo("Laptop X");
    }

    @Test
    void debeObtenerTodosConCategoria() {
        List<Producto> productos = productoRepositorio.findAllConCategoria();

        assertThat(productos).hasSize(1);
        assertThat(productos.get(0).getCategoria().getNombre()).isEqualTo("Tecnología");
    }

    @Test
    void debeFiltrarPorNombreDeCategoria() {
        List<Producto> productos = productoRepositorio.findByCategoriaNombreIgnoreCase("TECNOLOGÍA");

        assertThat(productos).hasSize(1);
        assertThat(productos.get(0).getNombre()).isEqualTo("Laptop X");
    }

    @Test
    void debeActualizarProducto() {
        Producto encontrado = productoRepositorio.findById(producto.getId()).orElseThrow();
        encontrado.setPrecio(new BigDecimal("1200.00"));
        Producto actualizado = productoRepositorio.save(encontrado);

        assertThat(actualizado.getPrecio()).isEqualTo(new BigDecimal("1200.00"));
    }

    @Test
    void debeEliminarProducto() {
        productoRepositorio.deleteById(producto.getId());
        Optional<Producto> eliminado = productoRepositorio.findById(producto.getId());

        assertThat(eliminado).isEmpty();
    }
}
