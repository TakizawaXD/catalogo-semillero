package com.wposs.catalogo.repositorio;

import com.wposs.catalogo.modelo.Producto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductoRepositorio {
    private final ConcurrentHashMap<Long, Producto> baseDeDatos = new ConcurrentHashMap<>();
    private final AtomicLong generadorId = new AtomicLong(1);

    public ProductoRepositorio() {
        guardar(new Producto(null, "Laptop X", "Laptop de alta gama", new BigDecimal("1500.00"), "Tecnología", 10));
        guardar(new Producto(null, "Teclado Mecánico", "Teclado RGB", new BigDecimal("120.50"), "Tecnología", 25));
    }

    public Producto guardar(Producto producto) {
        Long id = producto.id() == null ? generadorId.getAndIncrement() : producto.id();
        Producto productoAGuardar = new Producto(id, producto.nombre(), producto.descripcion(), producto.precio(), producto.categoria(), producto.stock());
        baseDeDatos.put(id, productoAGuardar);
        return productoAGuardar;
    }

    public List<Producto> obtenerTodos() {
        return new ArrayList<>(baseDeDatos.values());
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return Optional.ofNullable(baseDeDatos.get(id));
    }

    public void eliminar(Long id) {
        baseDeDatos.remove(id);
    }
}
