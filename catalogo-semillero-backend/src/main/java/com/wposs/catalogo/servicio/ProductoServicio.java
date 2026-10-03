package com.wposs.catalogo.servicio;

import com.wposs.catalogo.modelo.Producto;
import com.wposs.catalogo.repositorio.ProductoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServicio {

    private final ProductoRepositorio repositorio;

    public ProductoServicio(ProductoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Producto> listarTodos() {
        return repositorio.obtenerTodos();
    }

    public Optional<Producto> buscarPorId(Long id) {
        return repositorio.obtenerPorId(id);
    }

    public Producto crear(Producto producto) {
        if (producto.precio().doubleValue() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        return repositorio.guardar(producto);
    }

    public Producto actualizar(Long id, Producto productoModificado) {
        return repositorio.obtenerPorId(id)
            .map(existente -> {
                Producto actualizado = new Producto(
                    existente.id(),
                    productoModificado.nombre(),
                    productoModificado.descripcion(),
                    productoModificado.precio(),
                    productoModificado.categoria(),
                    productoModificado.stock()
                );
                return repositorio.guardar(actualizado);
            }).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
    }

    public void eliminar(Long id) {
        repositorio.eliminar(id);
    }

    public List<Producto> filtrarPorCategoria(String categoria) {
        return repositorio.obtenerTodos().stream()
                .filter(p -> p.categoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }
}
