package com.wposs.catalogo.repositorio;

import com.wposs.catalogo.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {

    // Resuelve el problema de N+1
    @Query("SELECT p FROM Producto p JOIN FETCH p.categoria")
    List<Producto> findAllConCategoria();
    
    // Consulta derivada para buscar por nombre de categoría
    List<Producto> findByCategoriaNombreIgnoreCase(String categoriaNombre);
}
